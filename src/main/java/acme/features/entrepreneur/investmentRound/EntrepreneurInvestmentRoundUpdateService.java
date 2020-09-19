
package acme.features.entrepreneur.investmentRound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customParams.CustomParams;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.forms.SpamCheck;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurInvestmentRoundUpdateService implements AbstractUpdateService<Entrepreneur, InvestmentRound> {

	@Autowired
	private EntrepreneurInvestmentRoundRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;
		int idInvestmentRound = request.getModel().getInteger("id");
		InvestmentRound j = this.repository.findOneById(idInvestmentRound);
		int entrepreneurId = request.getPrincipal().getActiveRoleId();
		boolean res = j.getEntrepreneur().getId() == entrepreneurId && j.getStatus().equals("draft");
		return res;
	}

	@Override
	public void bind(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "status", "creationMoment");

	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "ticker", "kind", "title", "description", "money", "link", "status", "yomp");
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

		CustomParams c = this.repository.getCustomParams();

		if (!errors.hasErrors("money")) {
			boolean moneyCurrencyMax = entity.getMoney().getCurrency().matches("EUR|€|EUROS|Euros|euros|eur");
			errors.state(request, moneyCurrencyMax, "money", "entrepreneur.investmentRound.error.moneyCurrency");
			boolean noNegSalary = entity.getMoney().getAmount() <= 0.0;
			errors.state(request, !noNegSalary, "money", "entrepreneur.investmentRound.error.noNegMoney");
		}

		if (!errors.hasErrors("title")) {
			boolean titleSpam = SpamCheck.checkSpam(entity.getTitle(), c);
			errors.state(request, !titleSpam, "title", "entrepreneur.investmentRound.error.title.spam");
		}

		if (!errors.hasErrors("description")) {
			boolean descriptionSpam = SpamCheck.checkSpam(entity.getDescription(), c);
			errors.state(request, !descriptionSpam, "description", "entrepreneur.investmentRound.error.description.spam");
		}

		if (!errors.hasErrors("yomp")) {
			boolean yompSpam = SpamCheck.checkSpam(entity.getYomp(), c);
			errors.state(request, !yompSpam, "yomp", "entrepreneur.investmentRound.error.yomp.spam");
		}

	}

	@Override
	public void update(final Request<InvestmentRound> request, final InvestmentRound entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);

	}
}
