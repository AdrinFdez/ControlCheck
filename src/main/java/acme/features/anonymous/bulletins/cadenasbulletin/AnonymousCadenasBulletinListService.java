
package acme.features.anonymous.bulletins.cadenasbulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.CadenasBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousCadenasBulletinListService implements AbstractListService<Anonymous, CadenasBulletin> {

	//Internal state --------------------------------------------------------

	@Autowired
	AnonymousCadenasBulletinRepository repository;


	//AbstractListService<Administrator, CadenasBulletin> interface -------

	@Override
	public boolean authorise(final Request<CadenasBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<CadenasBulletin> findMany(final Request<CadenasBulletin> request) {
		assert request != null;

		Collection<CadenasBulletin> result;

		result = this.repository.findMany();

		return result;
	}

	@Override
	public void unbind(final Request<CadenasBulletin> request, final CadenasBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "surname", "gender", "address", "city", "email");
	}

}
