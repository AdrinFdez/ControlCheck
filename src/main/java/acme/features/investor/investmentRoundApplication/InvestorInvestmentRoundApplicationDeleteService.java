
package acme.features.investor.investmentRoundApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRoundApplications.InvestmentRoundApplication;
import acme.entities.roles.Investor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class InvestorInvestmentRoundApplicationDeleteService implements AbstractDeleteService<Investor, InvestmentRoundApplication> {

	@Autowired
	InvestorInvestmentRoundApplicationRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRoundApplication> request) {
		assert request != null;
		int idInvestmentRoundApplication = request.getModel().getInteger("id");
		InvestmentRoundApplication appl = this.repository.findOneById(idInvestmentRoundApplication);
		int idInvestor = request.getPrincipal().getActiveRoleId();
		boolean res = appl.getInvestor().getId() == idInvestor;
		return res;
	}

	@Override
	public void bind(final Request<InvestmentRoundApplication> request, final InvestmentRoundApplication entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<InvestmentRoundApplication> request, final InvestmentRoundApplication entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "ticker", "creationMoment", "statement", "offer", "investmentRound");

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

		boolean pending = entity.getStatement() == "pending";
		errors.state(request, !pending, "status", "investor.investorRoundApplication.error.status");

	}

	@Override
	public void delete(final Request<InvestmentRoundApplication> request, final InvestmentRoundApplication entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);

	}

}
