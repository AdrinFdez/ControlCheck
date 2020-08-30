
package acme.features.authenticated.tecnologyrecord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.technologyRecords.TechnologyRecord;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedTechnologyRecordListBySectorService implements AbstractListService<Authenticated, TechnologyRecord> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedTechnologyRecordRepository repository;


	// AbstractListService<Authenticated, TechnologyRecord> interface ----------------

	@Override
	public boolean authorise(final Request<TechnologyRecord> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<TechnologyRecord> request, final TechnologyRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "sector", "inventorName", "description", "website", "email", "openSource", "rating");
	}

	@Override
	public Collection<TechnologyRecord> findMany(final Request<TechnologyRecord> request) {
		assert request != null;

		Collection<TechnologyRecord> result;

		result = this.repository.findAllBySector();

		return result;
	}

}
