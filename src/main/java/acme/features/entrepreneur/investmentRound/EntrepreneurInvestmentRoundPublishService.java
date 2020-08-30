
package acme.features.entrepreneur.investmentRound;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRounds.Activity;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurInvestmentRoundPublishService implements AbstractUpdateService<Entrepreneur, InvestmentRound> {

	@Autowired
	EntrepreneurInvestmentRoundRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;
		int idInvestmentRound = request.getModel().getInteger("id");
		InvestmentRound j = this.repository.findOneById(idInvestmentRound);
		int entrepreneurId = request.getPrincipal().getActiveRoleId();
		boolean res = j.getEntrepreneur().getId() == entrepreneurId;
		return res;
	}

	@Override
	public void bind(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "ticker", "creationMoment", "kind", "title", "description", "money", "link", "status", "text");
	}

	@Override
	public InvestmentRound findOne(final Request<InvestmentRound> request) {
		assert request != null;
		InvestmentRound j = this.repository.findOneById(request.getModel().getInteger("id"));
		return j;
	}

	@Override
	public void validate(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("money")) {
			boolean moneyCurrencyMax = entity.getMoney().getCurrency().equals("EUROS") || entity.getMoney().getCurrency().equals("€");
			errors.state(request, moneyCurrencyMax, "money", "entrepreneur.investmentRound.error.moneyCurrency");
			boolean noNegSalary = entity.getMoney().getAmount() <= 0.0;
			errors.state(request, !noNegSalary, "money", "entrepreneur.InvestmentRound.error.noNegMoney");
		}

		List<Activity> activities = this.repository.findActivitiesOfTheInvestmentRound(entity.getId());
		Money moneyInvestment = entity.getMoney();
		Money total = new Money();
		total.setAmount(0.0);
		total.setCurrency("euros");
		for (Activity activity : activities) {
			Double i = total.getAmount() + activity.getBudget().getAmount();
			total.setAmount(i);
		}
		boolean totalActivityMoney = moneyInvestment.getAmount().equals(total.getAmount());
		errors.state(request, totalActivityMoney, "money", "entrepreneur.InvestmentRound.error.total");

	}

	@Override
	public void update(final Request<InvestmentRound> request, final InvestmentRound entity) {
		assert request != null;
		assert entity != null;
		entity.setStatus("published");
		this.repository.save(entity);

	}

}
