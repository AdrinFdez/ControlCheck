
package acme.features.entrepreneur.activity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customParams.CustomParams;
import acme.entities.investmentRounds.Activity;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.forms.SpamCheck;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurActivityUpdateService implements AbstractUpdateService<Entrepreneur, Activity> {

	@Autowired
	EntrepreneurActivityRepository repository;


	@Override
	public boolean authorise(final Request<Activity> request) {
		assert request != null;
		int id = request.getModel().getInteger("id");
		InvestmentRound j = this.repository.getInvestmentRounByIdActivity(id);
		int entrepreneurId = request.getPrincipal().getActiveRoleId();
		boolean res = j.getEntrepreneur().getId() == entrepreneurId && j.getStatus().equals("draft");
		return res;
	}

	@Override
	public void bind(final Request<Activity> request, final Activity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Activity> request, final Activity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "title", "startDate", "endDate", "budget", "workProgramme.investmentRound.status");

	}

	@Override
	public Activity findOne(final Request<Activity> request) {
		assert request != null;
		int id = request.getModel().getInteger("id");
		Activity d = this.repository.findOne(id);
		return d;
	}

	@Override
	public void validate(final Request<Activity> request, final Activity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		CustomParams c = this.repository.getCustomParams();

		if (!errors.hasErrors("budget")) {
			boolean moneyCurrencyMax = entity.getBudget().getCurrency().equals("EUROS") || entity.getBudget().getCurrency().equals("€");
			errors.state(request, moneyCurrencyMax, "budget", "entrepreneur.activity.error.moneyCurrency");
			boolean noNegSalary = entity.getBudget().getAmount() <= 0.0;
			errors.state(request, !noNegSalary, "budget", "entrepreneur.activity.error.noNegMoney");
		}

		int id = request.getModel().getInteger("id");
		InvestmentRound j = this.repository.getInvestmentRounByIdActivity(id);
		List<Activity> activities = this.repository.findActivitiesOfTheInvestmentRound(j.getId());
		for (Activity activity : activities) {
			if (activity.getId() == id) {
				activity.getBudget().setAmount(entity.getBudget().getAmount());
			}
		}
		Money moneyInvestment = j.getMoney();
		Money total = new Money();
		total.setAmount(0.0);
		total.setCurrency("euros");
		for (Activity activity : activities) {
			Double i = total.getAmount() + activity.getBudget().getAmount();
			total.setAmount(i);
		}
		boolean totalActivityMoney = total.getAmount().compareTo(moneyInvestment.getAmount()) <= 0;
		errors.state(request, totalActivityMoney, "budget", "entrepreneur.activity.error.total");

		if (!errors.hasErrors("title")) {
			boolean titleSpam = SpamCheck.checkSpam(entity.getTitle(), c);
			errors.state(request, !titleSpam, "title", "entrepreneur.activity.error.title.spam");
		}

	}

	@Override
	public void update(final Request<Activity> request, final Activity entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);

	}
}
