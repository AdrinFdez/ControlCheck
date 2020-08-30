
package acme.features.authenticated.discussionForum;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.discussionForums.DiscussionForum;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedDiscussionForumShowService implements AbstractShowService<Authenticated, DiscussionForum> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedDiscussionForumRepository repository;


	// AbstractShowService<Authenticated, DiscussionForum> interface ----------------

	@Override
	public boolean authorise(final Request<DiscussionForum> request) {
		assert request != null;

		boolean result;
		int discussionForumId;
		Principal principal = request.getPrincipal();
		int authId = principal.getActiveRoleId();
		discussionForumId = request.getModel().getInteger("id");
		Integer cuenta = this.repository.checkIfUserIsInTheForum(authId, discussionForumId);
		result = cuenta > 0 ? true : false;

		return result;
	}

	@Override
	public void unbind(final Request<DiscussionForum> request, final DiscussionForum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationMoment", "owner");

		List<Integer> usersOnTheThread = this.repository.findUsersOnTheForum(request.getModel().getInteger("id"));
		String[][] datafromUsers = this.repository.findDataFromUsers(usersOnTheThread);
		model.setAttribute("usersData", datafromUsers);
		model.setAttribute("authId", request.getPrincipal().getActiveRoleId());
	}

	@Override
	public DiscussionForum findOne(final Request<DiscussionForum> request) {
		assert request != null;

		DiscussionForum result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
