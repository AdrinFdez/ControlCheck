
package acme.features.anonymous.bulletins.ruedaBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.RuedaBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousRuedaBulletinListService implements AbstractListService<Anonymous, RuedaBulletin> {

	@Autowired
	AnonymousRuedaBulletinRepository repository;


	@Override
	public boolean authorise(final Request<RuedaBulletin> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<RuedaBulletin> findMany(final Request<RuedaBulletin> request) {
		assert request != null;
		Collection<RuedaBulletin> result;
		result = this.repository.findMany();
		return result;

	}

	@Override
	public void unbind(final Request<RuedaBulletin> request, final RuedaBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "email", "comment");
	}

}
