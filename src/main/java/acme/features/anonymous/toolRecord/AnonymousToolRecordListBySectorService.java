
package acme.features.anonymous.toolRecord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolRecords.ToolRecord;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousToolRecordListBySectorService implements AbstractListService<Anonymous, ToolRecord> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AnonymousToolRecordRepository repository;


	// AbstractListService<Anonymous, ToolRecord> interface ----------------

	@Override
	public boolean authorise(final Request<ToolRecord> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<ToolRecord> request, final ToolRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "sector", "inventorName", "description", "website", "email", "openSource", "rating");
	}

	@Override
	public Collection<ToolRecord> findMany(final Request<ToolRecord> request) {
		assert request != null;

		Collection<ToolRecord> result;

		result = this.repository.findAllBySector();

		return result;
	}

}
