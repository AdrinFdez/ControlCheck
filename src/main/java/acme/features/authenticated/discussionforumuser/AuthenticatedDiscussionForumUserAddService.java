
package acme.features.authenticated.discussionforumuser;

import java.util.List;

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
public class AuthenticatedDiscussionForumUserAddService implements AbstractCreateService<Authenticated, AuthenticatedDiscussionForum> {

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
		List<Authenticated> userNotInTheForum = this.repository.findUserNotInTheForum(id);
		model.setAttribute("usersToAdd", userNotInTheForum);

		if (request.isMethod(HttpMethod.GET)) {
			int idForum = request.getModel().getInteger("idForum");
			model.setAttribute("idForum", idForum);
		}

	}

	@Override
	public AuthenticatedDiscussionForum instantiate(final Request<AuthenticatedDiscussionForum> request) {
		assert request != null;
		AuthenticatedDiscussionForum adf = new AuthenticatedDiscussionForum();
		int idForum = request.getModel().getInteger("idForum");
		DiscussionForum df = this.repository.findDiscussionForumById(idForum);
		adf.setForum(df);

		//ESTE NO ES EL USUARIO FINAL QUE SE AGREGA A LA TABLA ES UN PLACEHOLDER
		Authenticated aprovisional = this.repository.findAuthenticatedById(request.getPrincipal().getActiveRoleId());
		adf.setUser(aprovisional);

		return adf;
	}

	@Override
	public void validate(final Request<AuthenticatedDiscussionForum> request, final AuthenticatedDiscussionForum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<AuthenticatedDiscussionForum> request, final AuthenticatedDiscussionForum entity) {
		assert request != null;
		assert entity != null;

		int idAuth = request.getModel().getInteger("userAdded");
		Authenticated a = this.repository.findAuthenticatedById(idAuth);
		entity.setUser(a);
		this.repository.save(entity);
	}

}
