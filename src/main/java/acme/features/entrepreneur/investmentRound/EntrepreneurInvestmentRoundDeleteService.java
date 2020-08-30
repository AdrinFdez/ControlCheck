
package acme.features.entrepreneur.investmentRound;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRounds.Activity;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.investmentRounds.WorkProgramme;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class EntrepreneurInvestmentRoundDeleteService implements AbstractDeleteService<Entrepreneur, InvestmentRound> {

	@Autowired
	EntrepreneurInvestmentRoundRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;
		int investmentRoundid = request.getModel().getInteger("id");
		InvestmentRound j = this.repository.findOneById(investmentRoundid);
		int entrepreneurId = request.getPrincipal().getActiveRoleId();
		boolean res = j.getEntrepreneur().getId() == entrepreneurId;
		return res;
	}

	@Override
	public void bind(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "ticker", "creationMoment", "kind", "title", "description", "money", "link", "status", "text");

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

		if (!this.repository.findActivitiesOfTheInvestmentRound(entity.getId()).isEmpty()) {
			boolean empty = this.repository.findActivitiesOfTheInvestmentRound(entity.getId()).isEmpty();
			errors.state(request, empty, "link", "entrepreneur.investmentRound.error.update");
		}

	}

	@Override
	public void delete(final Request<InvestmentRound> request, final InvestmentRound entity) {
		assert request != null;
		assert entity != null;

		List<Activity> activitiesOfTheInvestmentRound = this.repository.findActivitiesOfTheInvestmentRound(entity.getId());
		WorkProgramme wp = this.repository.findWorkProgrammeByInvestment(entity.getId());

		this.repository.delete(wp);
		this.repository.deleteAll(activitiesOfTheInvestmentRound);

	}

}
