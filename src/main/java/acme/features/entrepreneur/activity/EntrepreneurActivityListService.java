
package acme.features.entrepreneur.activity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRounds.Activity;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class EntrepreneurActivityListService implements AbstractListService<Entrepreneur, Activity> {

	@Autowired
	EntrepreneurActivityRepository repository;


	@Override

	public boolean authorise(final Request<Activity> request) {

		assert request != null;
		boolean result;
		int investmentRoundId;
		InvestmentRound investmentRound;
		Entrepreneur entrepreneur;
		Principal principal;
		investmentRoundId = request.getModel().getInteger("idInvestmentRound");
		investmentRound = this.repository.findOneById(investmentRoundId);

		entrepreneur = investmentRound.getEntrepreneur();

		principal = request.getPrincipal();

		result = investmentRound.getStatus().equals("published") || !investmentRound.getStatus().equals("published") && entrepreneur.getUserAccount().getId() == principal.getAccountId();

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
