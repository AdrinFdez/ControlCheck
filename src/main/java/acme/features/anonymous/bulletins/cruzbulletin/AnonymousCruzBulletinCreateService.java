
package acme.features.anonymous.bulletins.cruzbulletin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.CruzBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousCruzBulletinCreateService implements AbstractCreateService<Anonymous, CruzBulletin> {

	// Internal state ----------------------------------------------------------------

	@Autowired
	AnonymousCruzBulletinRepository repository;


	@Override
	public boolean authorise(final Request<CruzBulletin> request) {
		assert request != null;

		return true;

	}

	@Override
	public CruzBulletin instantiate(final Request<CruzBulletin> request) {
		assert request != null;

		CruzBulletin result;

		result = new CruzBulletin();
		result.setName("Carlos");
		result.setGender("male");
		result.setEmail("carlos@email.com");
		result.setPhoneNumber("692945963");

		return result;
	}

	@Override
	public void unbind(final Request<CruzBulletin> request, final CruzBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "gender", "email", "phoneNumber");
	}

	@Override
	public void bind(final Request<CruzBulletin> request, final CruzBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<CruzBulletin> request, final CruzBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<CruzBulletin> request, final CruzBulletin entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
}
