
package acme.features.authenticated.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.discussionForums.Message;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedMessageShowService implements AbstractShowService<Authenticated, Message> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedMessageRepository repository;


	// AbstractShowService<Authenticated, Message> interface ----------------

	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;
		/*
		 * boolean result;
		 * int discussionForumId;
		 * Principal principal = request.getPrincipal();
		 * int authId = principal.getActiveRoleId();
		 * int idmessage = request.getModel().getInteger("id");
		 * Message ms = this.repository.findOneById(idmessage);
		 * discussionForumId = ms.getForum().getId();
		 * Integer cuenta = this.repository.checkIfUserIsInTheForum(authId, discussionForumId);
		 * result = cuenta > 0 ? true : false;
		 */

		return true;
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationMoment", "tags", "body", "user.userAccount.username", "user.userAccount.identity.name", "user.userAccount.identity.surname");
	}

	@Override
	public Message findOne(final Request<Message> request) {
		assert request != null;
		Message result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
