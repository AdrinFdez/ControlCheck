
package acme.features.administrator.customParams;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customParams.CustomParams;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorCustomParamsUpdateService implements AbstractUpdateService<Administrator, CustomParams> {

	@Autowired
	AdministratorCustomParamsRepository repository;


	@Override
	public boolean authorise(final Request<CustomParams> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<CustomParams> request, final CustomParams entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "spamWords", "spamThreshold", "sectors");
	}

	@Override
	public void bind(final Request<CustomParams> request, final CustomParams entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);
	}

	@Override
	public CustomParams findOne(final Request<CustomParams> request) {
		assert request != null;
		List<CustomParams> temp = (List<CustomParams>) this.repository.findMany();
		return temp.get(0);
	}

	@Override
	public void validate(final Request<CustomParams> request, final CustomParams entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(final Request<CustomParams> request, final CustomParams entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);

	}

}
