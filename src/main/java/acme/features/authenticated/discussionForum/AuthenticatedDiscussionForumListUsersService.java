
package acme.features.authenticated.discussionForum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedDiscussionForumListUsersService implements AbstractListService<Authenticated, Authenticated> {

	@Autowired
	AuthenticatedDiscussionForumRepository repository;


	@Override
	public boolean authorise(final Request<Authenticated> request) {
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
	public void unbind(final Request<Authenticated> request, final Authenticated entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "userAccount.username", "userAccount.identity.name", "userAccount.identity.surname");

	}

	@Override
	public Collection<Authenticated> findMany(final Request<Authenticated> request) {
		assert request != null;
		int discussionForumId;
		discussionForumId = request.getModel().getInteger("id");
		Collection<Authenticated> res = this.repository.findUsersAuthenticatedOnTheForum(discussionForumId);
		return res;
	}

}
