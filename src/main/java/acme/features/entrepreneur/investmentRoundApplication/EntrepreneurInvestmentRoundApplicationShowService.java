
package acme.features.entrepreneur.investmentRoundApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRoundApplications.InvestmentRoundApplication;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class EntrepreneurInvestmentRoundApplicationShowService implements AbstractShowService<Entrepreneur, InvestmentRoundApplication> {

	@Autowired
	EntrepreneurInvestmentRoundApplicationRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRoundApplication> request) {
		assert request != null;
		int idInvestmentRoundApplication = request.getModel().getInteger("id");
		InvestmentRoundApplication j = this.repository.findOneById(idInvestmentRoundApplication);
		int entrepreneurId = request.getPrincipal().getActiveRoleId();
		boolean res = j.getInvestmentRound().getEntrepreneur().getId() == entrepreneurId;
		return res;
	}

	@Override
	public void unbind(final Request<InvestmentRoundApplication> request, final InvestmentRoundApplication entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "creationMoment", "statement", "offer", "justification", "investmentRound", "link", "pass", "checked");
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
