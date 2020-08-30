
package acme.features.authenticated.discussionForum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.discussionForums.DiscussionForum;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedDiscussionForumListMineService implements AbstractListService<Authenticated, DiscussionForum> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedDiscussionForumRepository repository;


	// AbstractListService<Authenticated, MessageThread> interface ----------------

	@Override
	public boolean authorise(final Request<DiscussionForum> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<DiscussionForum> request, final DiscussionForum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationMoment");
	}

	@Override
	public Collection<DiscussionForum> findMany(final Request<DiscussionForum> request) {
		assert request != null;
		Collection<DiscussionForum> participated;
		Collection<DiscussionForum> owned;

		Principal principal = request.getPrincipal();

		participated = this.repository.findForumByUser(principal.getActiveRoleId());
		owned = this.repository.findForumByOwner(principal.getActiveRoleId());

		for (DiscussionForum df : participated) {
			if (!owned.contains(df)) {
				owned.add(df);
			}
		}
		return owned;

	}

}
