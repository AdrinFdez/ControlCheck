
package acme.features.investor.investmentRoundApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRoundApplications.InvestmentRoundApplication;
import acme.entities.roles.Investor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class InvestorInvestmentRoundApplicationUpdateService implements AbstractUpdateService<Investor, InvestmentRoundApplication> {

	@Autowired
	private InvestorInvestmentRoundApplicationRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRoundApplication> request) {
		assert request != null;
		int idInvestmentRound = request.getModel().getInteger("id");
		InvestmentRoundApplication appl = this.repository.findOneById(idInvestmentRound);
		int entrepreneurId = request.getPrincipal().getActiveRoleId();
		boolean res = appl.getInvestor().getId() == entrepreneurId && appl.getStatement().equals("pending");
		return res;
	}

	@Override
	public void bind(final Request<InvestmentRoundApplication> request, final InvestmentRoundApplication entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "status");

	}

	@Override
	public void unbind(final Request<InvestmentRoundApplication> request, final InvestmentRoundApplication entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "ticker", "creationMoment", "kind", "title", "description", "money", "link", "status", "link", "pass");
	}

	@Override
	public InvestmentRoundApplication findOne(final Request<InvestmentRoundApplication> request) {
		assert request != null;
		InvestmentRoundApplication appl = this.repository.findOneById(request.getModel().getInteger("id"));
		return appl;
	}

	@Override
	public void validate(final Request<InvestmentRoundApplication> request, final InvestmentRoundApplication entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("money")) {
			boolean moneyCurrencyMax = entity.getOffer().getCurrency().equals("EUROS") || entity.getOffer().getCurrency().equals("€");
			errors.state(request, moneyCurrencyMax, "offer", "investor.investmentRoundApplication.error.moneyCurrency");
			boolean noNegSalary = entity.getOffer().getAmount() <= 0.0;
			errors.state(request, !noNegSalary, "offer", "investor.investmentRoundApplication.error.noNegMoney");
			boolean noMayorOffer = entity.getOffer().getAmount() <= entity.getInvestmentRound().getMoney().getAmount();
			errors.state(request, noMayorOffer, "offer", "investor.investmentRoundApplication.error.noMayorOffer");
		}

	}

	@Override
	public void update(final Request<InvestmentRoundApplication> request, final InvestmentRoundApplication entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);

	}
}
