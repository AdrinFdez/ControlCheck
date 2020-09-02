
package acme.features.authenticated.investmentRound;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.entities.roles.Investor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedInvestmentRoundShowService implements AbstractShowService<Authenticated, InvestmentRound> {

	@Autowired
	AuthenticatedInvestmentRoundRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "creationMoment", "kind", "title", "description", "money", "link");

		Principal principal = request.getPrincipal();
		Investor investor = this.repository.findInvestor(principal.getAccountId());
		Entrepreneur entrepreneur = this.repository.findEntrepreneur(principal.getAccountId());

		boolean hasApplied = false;

		if (investor != null) {
			Collection<InvestmentRound> iRApplied = this.repository.findManyById(principal.getAccountId());
			if (iRApplied.contains(entity)) {
				hasApplied = true;
			}
		} else if (entrepreneur != null) {
			Collection<InvestmentRound> irEntrepreneur = this.repository.findManyMine(entrepreneur.getId());
			if (!irEntrepreneur.isEmpty()) {
				hasApplied = true;
			}
		}
		model.setAttribute("application", hasApplied);
	}

	@Override
	public InvestmentRound findOne(final Request<InvestmentRound> request) {
		assert request != null;

		InvestmentRound result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}
}
