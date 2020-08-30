
package acme.features.anonymous.bulletins.cadenasbulletin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.CadenasBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousCadenasBulletinCreateService implements AbstractCreateService<Anonymous, CadenasBulletin> {

	// Internal state ----------------------------------------------------------------

	@Autowired
	AnonymousCadenasBulletinRepository repository;


	@Override
	public boolean authorise(final Request<CadenasBulletin> request) {
		assert request != null;
		return true;

	}

	@Override
	public CadenasBulletin instantiate(final Request<CadenasBulletin> request) {
		assert request != null;
		CadenasBulletin result;

		result = new CadenasBulletin();
		result.setName("Chus");
		result.setSurname("Cadenas");
		result.setGender("female");
		result.setAddress("Calle San Bernardo, 14, 1ºC");
		result.setCity("Sevilla");
		result.setEmail("marcadsan2@gmail.com");
		return result;
	}

	@Override
	public void unbind(final Request<CadenasBulletin> request, final CadenasBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "surname", "gender", "address", "city", "email");
	}

	@Override
	public void bind(final Request<CadenasBulletin> request, final CadenasBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<CadenasBulletin> request, final CadenasBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<CadenasBulletin> request, final CadenasBulletin entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
}
