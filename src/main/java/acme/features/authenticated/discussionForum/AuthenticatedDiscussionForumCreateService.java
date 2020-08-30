
package acme.features.authenticated.discussionForum;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.discussionForums.AuthenticatedDiscussionForum;
import acme.entities.discussionForums.DiscussionForum;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedDiscussionForumCreateService implements AbstractCreateService<Authenticated, DiscussionForum> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedDiscussionForumRepository repository;


	// AbstractCreateService<Authenticated, DiscussionForum> interface ---------------

	@Override
	public boolean authorise(final Request<DiscussionForum> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<DiscussionForum> request, final DiscussionForum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<DiscussionForum> request, final DiscussionForum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "investmentRound");

		if (request.isMethod(HttpMethod.GET)) {
			int idInvestmentRound = request.getModel().getInteger("idInvestmentRound");
			model.setAttribute("idInvestmentRound", idInvestmentRound);
		}
	}

	@Override
	public DiscussionForum instantiate(final Request<DiscussionForum> request) {
		assert request != null;

		DiscussionForum result;
		Date creationMoment = new Date();
		Authenticated owner = this.repository.findOneAuthenticatedById(request.getPrincipal().getActiveRoleId());

		result = new DiscussionForum();
		result.setCreationMoment(creationMoment);
		result.setOwner(owner);

		result.setInvestmentRound(this.repository.findOneInvestmentRoundById(request.getModel().getInteger("idInvestmentRound")));

		return result;
	}

	@Override
	public void validate(final Request<DiscussionForum> request, final DiscussionForum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<DiscussionForum> request, final DiscussionForum entity) {
		Date creationMoment;
		AuthenticatedDiscussionForum adf = new AuthenticatedDiscussionForum();
		adf.setForum(entity);
		adf.setUser(entity.getOwner());
		creationMoment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(creationMoment);
		this.repository.save(entity);
		this.repository.save(adf);
	}

}
