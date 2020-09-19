
package acme.features.entrepreneur.investmentRoundApplication;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customParams.CustomParams;
import acme.entities.discussionForums.AuthenticatedDiscussionForum;
import acme.entities.discussionForums.DiscussionForum;
import acme.entities.investmentRoundApplications.InvestmentRoundApplication;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.forms.SpamCheck;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurInvestmentRoundApplicationAcceptService implements AbstractUpdateService<Entrepreneur, InvestmentRoundApplication> {

	@Autowired
	private EntrepreneurInvestmentRoundApplicationRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRoundApplication> request) {
		assert request != null;
		boolean result;
		int idInvestmentRoundApplication;
		InvestmentRoundApplication investmentRoundApplication;
		Entrepreneur entrepreneur;
		Principal principal;

		idInvestmentRoundApplication = request.getModel().getInteger("id");
		investmentRoundApplication = this.repository.findOneById(idInvestmentRoundApplication);
		entrepreneur = investmentRoundApplication.getInvestmentRound().getEntrepreneur();
		principal = request.getPrincipal();
		result = entrepreneur.getUserAccount().getId() == principal.getAccountId();
		return result;
	}

	@Override
	public void bind(final Request<InvestmentRoundApplication> request, final InvestmentRoundApplication entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "ticker", "creationMoment", "statement", "offer", "investmentRound", "link");

	}

	@Override
	public void unbind(final Request<InvestmentRoundApplication> request, final InvestmentRoundApplication entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "statement");
	}

	@Override
	public InvestmentRoundApplication findOne(final Request<InvestmentRoundApplication> request) {
		assert request != null;
		int idAppl = request.getModel().getInteger("id");
		InvestmentRoundApplication appl = this.repository.findOneById(idAppl);
		return appl;
	}

	@Override
	public void validate(final Request<InvestmentRoundApplication> request, final InvestmentRoundApplication entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		CustomParams c = this.repository.getCustomParams();

		if (!errors.hasErrors("justification")) {
			boolean justificationSpam = SpamCheck.checkSpam(entity.getJustification(), c);
			errors.state(request, !justificationSpam, "justification", "entrepreneur.investmentRoundApplication.error.justification.spam");
		}

	}

	@Override
	public void update(final Request<InvestmentRoundApplication> request, final InvestmentRoundApplication entity) {
		assert request != null;
		assert entity != null;
		Date date = new Date();

		entity.setStatement("accepted");
		entity.setUpdateMoment(date);
		this.repository.save(entity);

		AuthenticatedDiscussionForum adf = new AuthenticatedDiscussionForum();
		//
		int idApp = request.getModel().getInteger("id");
		InvestmentRoundApplication ira = this.repository.findOneById(idApp);
		//
		int idAuth = ira.getInvestor().getUserAccount().getId();
		adf.setUser(this.repository.findOneAuthenticatedById(idAuth));
		//
		InvestmentRound round = ira.getInvestmentRound();
		List<DiscussionForum> df = this.repository.findDiscussionForumOfInvestment(round.getId());
		//
		for (DiscussionForum f : df) {
			adf.setForum(f);
			this.repository.save(adf);
		}

	}

}
