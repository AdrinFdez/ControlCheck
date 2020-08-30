
package acme.features.entrepreneur.investmentRoundApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRoundApplications.InvestmentRoundApplication;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class EntrepreneurInvestmentRoundApplicationCheckService implements AbstractShowService<Entrepreneur, InvestmentRoundApplication> {

	@Autowired
	private EntrepreneurInvestmentRoundApplicationRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRoundApplication> request) {
		//assert request != null;
		//boolean result;
		//int idInvestmentRoundApplication;
		//		InvestmentRoundApplication investmentRoundApplication;
		//		Entrepreneur entrepreneur;
		//		Principal principal;
		//
		//		idInvestmentRoundApplication = request.getModel().getInteger("id");
		//		investmentRoundApplication = this.repository.findOneById(idInvestmentRoundApplication);
		//		entrepreneur = investmentRoundApplication.getInvestmentRound().getEntrepreneur();
		//		principal = request.getPrincipal();
		//		result = entrepreneur.getUserAccount().getId() == principal.getAccountId();
		//		return result;
		return true;
	}

	@Override
	public void unbind(final Request<InvestmentRoundApplication> request, final InvestmentRoundApplication entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "statement", "link", "pass", "ticker", "creationMoment", "statement", "offer", "investmentRound");
	}

	@Override
	public InvestmentRoundApplication findOne(final Request<InvestmentRoundApplication> request) {
		assert request != null;
		int idAppl = request.getModel().getInteger("id");
		if (request.getModel().getString("check").equals(this.repository.findPass(idAppl))) {
			boolean checked = true;
			request.getModel().setAttribute("checked", checked);
		}

		InvestmentRoundApplication appl = this.repository.findOneById(idAppl);
		return appl;
	}

}
