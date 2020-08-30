
package acme.features.anonymous.bulletins.pinerobulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.PineroBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousPineroBulletinCreateService implements AbstractCreateService<Anonymous, PineroBulletin> {

	// Internal state ----------------------------------------------------------------

	@Autowired
	AnonymousPineroBulletinRepository repository;


	@Override
	public boolean authorise(final Request<PineroBulletin> request) {
		assert request != null;

		return true;

	}

	@Override
	public PineroBulletin instantiate(final Request<PineroBulletin> request) {
		assert request != null;

		PineroBulletin result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new PineroBulletin();
		result.setName("Mari");
		result.setDescription("Esto es la descripcion");
		result.setEmail("marp@gmail.com");
		result.setDate(moment);
		result.setAddress("Aguditas Carmona");

		return result;
	}

	@Override
	public void unbind(final Request<PineroBulletin> request, final PineroBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "description", "email", "address");
	}

	@Override
	public void bind(final Request<PineroBulletin> request, final PineroBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<PineroBulletin> request, final PineroBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<PineroBulletin> request, final PineroBulletin entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setDate(moment);
		this.repository.save(entity);
	}
}
