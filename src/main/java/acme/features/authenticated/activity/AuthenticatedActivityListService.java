
package acme.features.authenticated.activity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRounds.Activity;
import acme.entities.investmentRounds.InvestmentRound;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedActivityListService implements AbstractListService<Authenticated, Activity> {

	@Autowired
	AuthenticatedActivityRepository repository;


	@Override

	public boolean authorise(final Request<Activity> request) {

		assert request != null;
		boolean result;
		int investmentRoundId;
		InvestmentRound investmentRound;
		investmentRoundId = request.getModel().getInteger("idInvestmentRound");
		investmentRound = this.repository.findOneById(investmentRoundId);

		result = investmentRound.getStatus().equals("published") || !investmentRound.getStatus().equals("published");

		return result;

	}
	@Override

	public void unbind(final Request<Activity> request, final Activity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "startDate", "endDate", "budget");

	}

	@Override

	public Collection<Activity> findMany(final Request<Activity> request) {
		assert request != null;

		int investmentRoundId = request.getModel().getInteger("idInvestmentRound");
		Collection<Activity> res;
		res = this.repository.findMany(investmentRoundId);

		return res;

	}

}
