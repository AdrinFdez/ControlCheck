
package acme.features.administrator.notice;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.notices.Notice;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorNoticeCreateService implements AbstractCreateService<Administrator, Notice> {

	@Autowired
	AdministratorNoticeRepository repository;


	@Override
	public boolean authorise(final Request<Notice> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Notice> request, final Notice entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationDate");
	}

	@Override
	public void unbind(final Request<Notice> request, final Notice entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "headerPicture", "title", "deadlineDate", "body", "optionalLink1", "optionalLink2");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("aceptar", "false");
		} else {
			request.transfer(model, "aceptar");
		}
	}

	@Override
	public Notice instantiate(final Request<Notice> request) {
		Notice result;

		result = new Notice();

		return result;
	}

	@Override
	public void validate(final Request<Notice> request, final Notice entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("creationDate") && !errors.hasErrors("deadlineDate")) {

			Date now = new Date(System.currentTimeMillis() - 1);
			Date newDeadline = entity.getDeadlineDate();
			boolean cmp = newDeadline.before(now);

			errors.state(request, !cmp, "deadlineDate", "administrator.notice.error.deadlineDate");
		}

		boolean isAccepted = request.getModel().getBoolean("aceptar");
		errors.state(request, isAccepted, "aceptar", "administrator.notice.error.accept");

	}

	@Override
	public void create(final Request<Notice> request, final Notice entity) {

		Date creationDate;

		creationDate = new Date(System.currentTimeMillis() - 1);
		entity.setCreationDate(creationDate);
		this.repository.save(entity);
	}

}
