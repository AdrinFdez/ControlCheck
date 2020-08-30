
package acme.features.entrepreneur.investmentRoundApplication;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRoundApplications.InvestmentRoundApplication;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class EntrepreneurInvestmentRoundApplicationListService implements AbstractListService<Entrepreneur, InvestmentRoundApplication> {

	@Autowired
	EntrepreneurInvestmentRoundApplicationRepository repository;


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

		request.unbind(entity, model, "ticker", "creationMoment", "offer", "statement");
	}

	@Override
	public Collection<InvestmentRoundApplication> findMany(final Request<InvestmentRoundApplication> request) {
		assert request != null;
		Collection<InvestmentRoundApplication> res;
		int idInvestmentRound = request.getModel().getInteger("id");
		res = this.repository.findManyByInvestmentRoundId(idInvestmentRound);
		return res;
	}

}
