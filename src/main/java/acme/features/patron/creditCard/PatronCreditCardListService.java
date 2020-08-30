
package acme.features.patron.creditCard;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.CreditCard;
import acme.entities.roles.Patron;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class PatronCreditCardListService implements AbstractListService<Patron, CreditCard> {

	@Autowired
	PatronCreditCardRepository repository;


	@Override
	public boolean authorise(final Request<CreditCard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<CreditCard> request, final CreditCard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "cardNumber", "holder", "cvv", "brand", "expirationMonth", "expirationYear");

	}

	@Override
	public Collection<CreditCard> findMany(final Request<CreditCard> request) {
		assert request != null;

		Principal principal;
		int userAccountId;
		Patron patron;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		patron = this.repository.findOnePatronByUserAccountId(userAccountId);

		Collection<CreditCard> result;
		result = this.repository.findManyByPatronId(patron.getId());
		return result;

	}

}
