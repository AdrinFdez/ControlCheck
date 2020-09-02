
package acme.features.authenticated.discussionForum;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.discussionForums.AuthenticatedDiscussionForum;
import acme.entities.discussionForums.DiscussionForum;
import acme.entities.discussionForums.Message;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class AuthenticatedDiscussionForumDeleteService implements AbstractDeleteService<Authenticated, DiscussionForum> {

	@Autowired
	AuthenticatedDiscussionForumRepository repository;


	@Override
	public boolean authorise(final Request<DiscussionForum> request) {
		assert request != null;

		boolean result = false;
		int discussionForumId;
		Principal principal = request.getPrincipal();
		int authId = principal.getActiveRoleId();
		discussionForumId = request.getModel().getInteger("idForum");
		Integer cuenta = this.repository.checkIfUserIsInTheForum(authId, discussionForumId);
		Integer plus = this.repository.checkIfUserIsOwner(authId, discussionForumId);

		if (cuenta > 0) {
			if (plus > 0) {
				result = true;
			}
		}

		return result;
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

		request.unbind(entity, model, "title", "creationMoment", "owner", "investmentRound");

		if (request.isMethod(HttpMethod.GET)) {
			int idForum = request.getModel().getInteger("idForum");
			model.setAttribute("idForum", idForum);
		}
	}

	@Override
	public DiscussionForum findOne(final Request<DiscussionForum> request) {
		assert request != null;
		DiscussionForum df = new DiscussionForum();
		int idForum = request.getModel().getInteger("idForum");
		df = this.repository.findOneById(idForum);
		return df;
	}

	@Override
	public void validate(final Request<DiscussionForum> request, final DiscussionForum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void delete(final Request<DiscussionForum> request, final DiscussionForum entity) {
		assert request != null;
		assert entity != null;

		List<Message> messagesInTheForum = this.repository.findMessagesByForum(entity.getId());
		List<AuthenticatedDiscussionForum> adfInTheForum = this.repository.findAuthenticatedForumByForum(entity.getId());

		for (Message m : messagesInTheForum) {
			this.repository.delete(m);
		}

		for (AuthenticatedDiscussionForum adf : adfInTheForum) {
			this.repository.delete(adf);
		}

		this.repository.delete(entity);

	}

}
