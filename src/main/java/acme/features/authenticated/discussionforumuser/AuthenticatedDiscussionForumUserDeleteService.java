
package acme.features.authenticated.discussionforumuser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.discussionForums.AuthenticatedDiscussionForum;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractDeleteService;

@Service
public class AuthenticatedDiscussionForumUserDeleteService implements AbstractDeleteService<Authenticated, AuthenticatedDiscussionForum> {

	@Autowired
	private AuthenticatedDiscussionForumUserRepository repository;


	@Override
	public boolean authorise(final Request<AuthenticatedDiscussionForum> request) {
		assert request != null;

		int idForum = request.getModel().getInteger("idForum");
		Authenticated owner = this.repository.findOwnerOfTheForum(idForum);
		int idAuthenticatedPrincipal = request.getPrincipal().getActiveRoleId();
		return owner.getId() == idAuthenticatedPrincipal;
	}

	@Override
	public void bind(final Request<AuthenticatedDiscussionForum> request, final AuthenticatedDiscussionForum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<AuthenticatedDiscussionForum> request, final AuthenticatedDiscussionForum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		int id = entity.getForum().getId();
		request.unbind(entity, model, "user", "forum");
		List<Authenticated> usersInTheForum = this.repository.findUsersInTheForum(id, request.getPrincipal().getActiveRoleId());
		model.setAttribute("usersToRemove", usersInTheForum);

		if (request.isMethod(HttpMethod.GET)) {
			int idForum = request.getModel().getInteger("idForum");
			model.setAttribute("idForum", idForum);
		}

	}

	@Override
	public AuthenticatedDiscussionForum findOne(final Request<AuthenticatedDiscussionForum> request) {
		assert request != null;
		AuthenticatedDiscussionForum adf = new AuthenticatedDiscussionForum();
		int idForum = request.getModel().getInteger("idForum");
		adf = this.repository.findOneById(request.getPrincipal().getActiveRoleId(), idForum);
		return adf;
	}

	@Override
	public void validate(final Request<AuthenticatedDiscussionForum> request, final AuthenticatedDiscussionForum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void delete(final Request<AuthenticatedDiscussionForum> request, final AuthenticatedDiscussionForum entity) {
		assert request != null;
		assert entity != null;

		AuthenticatedDiscussionForum adf;

		int userToRemove = request.getModel().getInteger("userRemoved");
		int idForum = request.getModel().getInteger("idForum");

		adf = this.repository.findOneById(userToRemove, idForum);
		this.repository.delete(adf);

	}
}
