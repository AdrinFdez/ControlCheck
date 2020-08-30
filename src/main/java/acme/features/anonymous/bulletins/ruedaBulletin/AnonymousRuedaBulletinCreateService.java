
package acme.features.anonymous.bulletins.ruedaBulletin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.RuedaBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousRuedaBulletinCreateService implements AbstractCreateService<Anonymous, RuedaBulletin> {

	@Autowired
	AnonymousRuedaBulletinRepository repository;


	@Override
	public boolean authorise(final Request<RuedaBulletin> request) {
		assert request != null;
		return true;
	}

	@Override
	public RuedaBulletin instantiate(final Request<RuedaBulletin> request) {
		assert request != null;
		RuedaBulletin result;

		result = new RuedaBulletin();
		result.setName("Daniel Rueda López");
		result.setEmail("danruedalop@gmail.com");
		result.setComment("Hi im Daniel");
		return result;

	}

	@Override
	public void unbind(final Request<RuedaBulletin> request, final RuedaBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "email", "comment");
	}

	@Override
	public void bind(final Request<RuedaBulletin> request, final RuedaBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<RuedaBulletin> request, final RuedaBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<RuedaBulletin> request, final RuedaBulletin entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
}
