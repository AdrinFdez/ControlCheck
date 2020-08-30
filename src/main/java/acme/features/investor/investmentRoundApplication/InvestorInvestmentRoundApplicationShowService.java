
package acme.features.investor.investmentRoundApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRoundApplications.InvestmentRoundApplication;
import acme.entities.roles.Investor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class InvestorInvestmentRoundApplicationShowService implements AbstractShowService<Investor, InvestmentRoundApplication> {

	@Autowired
	InvestorInvestmentRoundApplicationRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRoundApplication> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<InvestmentRoundApplication> request, final InvestmentRoundApplication entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "creationMoment", "statement", "offer", "justification", "investmentRound", "link", "pass");

	}

	@Override
	public InvestmentRoundApplication findOne(final Request<InvestmentRoundApplication> request) {
		assert request != null;
		InvestmentRoundApplication result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}
}
