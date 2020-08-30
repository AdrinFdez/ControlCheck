
package acme.features.authenticated.message;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.discussionForums.DiscussionForum;
import acme.entities.discussionForums.Message;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageCreateService implements AbstractCreateService<Authenticated, Message> {

	@Autowired
	AuthenticatedMessageRepository repository;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;
		boolean result;
		Principal principal = request.getPrincipal();
		int authId = principal.getActiveRoleId();
		int idForum = request.getModel().getInteger("idForum");
		Integer cuenta = this.repository.checkIfUserIsInTheForum(authId, idForum);
		result = cuenta > 0 ? true : false;

		return result;
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		if (request.isMethod(HttpMethod.GET)) {
			int idForum = request.getModel().getInteger("idForum");
			model.setAttribute("idForum", idForum);
			model.setAttribute("aceptar", "false");
		} else {
			request.transfer(model, "aceptar");
		}

		request.unbind(entity, model, "title", "tags", "body");

		if (request.isMethod(HttpMethod.GET)) {
			int idForum = request.getModel().getInteger("idForum");
			model.setAttribute("idForum", idForum);
		}

	}

	@Override
	public void bind(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public Message instantiate(final Request<Message> request) {
		assert request != null;

		Message result;

		int idForum;
		DiscussionForum df;
		Authenticated user;
		Date creationMoment = new Date();

		Principal principal;

		idForum = request.getModel().getInteger("idForum");
		df = this.repository.findDiscussionForum(idForum);

		principal = request.getPrincipal();
		int idUser = principal.getActiveRoleId();
		user = this.repository.findUser(idUser);

		result = new Message();
		result.setForum(df);
		result.setUser(user);
		result.setCreationMoment(creationMoment);

		return result;
	}

	@Override
	public void validate(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isAccepted = request.getModel().getBoolean("aceptar");
		errors.state(request, isAccepted, "aceptar", "authenticated.message.error.aceptar");

	}

	@Override
	public void create(final Request<Message> request, final Message entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

	@Override
	public void onSuccess(final Request<Message> request, final Response<Message> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
