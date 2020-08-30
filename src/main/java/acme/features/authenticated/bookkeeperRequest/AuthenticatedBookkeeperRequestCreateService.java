
package acme.features.authenticated.bookkeeperRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bookkeeperRequests.BookkeeperRequest;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedBookkeeperRequestCreateService implements AbstractCreateService<Authenticated, BookkeeperRequest> {

	@Autowired
	private AuthenticatedBookkeeperRequestRepository repository;


	@Override
	public boolean authorise(final Request<BookkeeperRequest> request) {
		assert request != null;
		return !request.getPrincipal().hasRole(Bookkeeper.class);
	}

	@Override
	public void bind(final Request<BookkeeperRequest> request, final BookkeeperRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "status", "user");

	}

	@Override
	public void unbind(final Request<BookkeeperRequest> request, final BookkeeperRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		int authenticatedId = request.getPrincipal().getActiveRoleId();
		int countPending = this.repository.findBookkeeperRequestByAuthenticatedId(authenticatedId);
		if (countPending > 0) {
			String alreadyRequested = "authenticated.bookkeeper-request.requested.error";
			model.setAttribute("requestedError", alreadyRequested);
		}
		request.unbind(entity, model, "firm", "responsibilityStatement");

	}

	@Override
	public BookkeeperRequest instantiate(final Request<BookkeeperRequest> request) {
		assert request != null;

		Principal principal;

		BookkeeperRequest br;
		Authenticated at;
		principal = request.getPrincipal();
		at = this.repository.findAuthenticatedById(principal.getActiveRoleId());
		br = new BookkeeperRequest();
		br.setUser(at);

		return br;

	}

	@Override
	public void validate(final Request<BookkeeperRequest> request, final BookkeeperRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<BookkeeperRequest> request, final BookkeeperRequest entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);

	}

}
