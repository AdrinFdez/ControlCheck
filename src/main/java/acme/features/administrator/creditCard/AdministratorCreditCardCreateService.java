
package acme.features.administrator.creditCard;

import java.time.YearMonth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.CreditCard;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorCreditCardCreateService implements AbstractCreateService<Administrator, CreditCard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorCreditCardRepository repository;


	// AbstractCreateService<Administrator, CreditCard> interface ---------------

	@Override
	public boolean authorise(final Request<CreditCard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<CreditCard> request, final CreditCard entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<CreditCard> request, final CreditCard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "cardNumber", "holder", "cvv", "brand", "expirationMonth", "expirationYear");

	}

	@Override
	public CreditCard instantiate(final Request<CreditCard> request) {
		assert request != null;

		CreditCard result;
		Principal principal;
		int userAccountId;
		Administrator administrator;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		administrator = this.repository.findOneAdministratorByUserAccountId(userAccountId);

		result = new CreditCard();
		result.setAdministrator(administrator);

		return result;
	}

	@Override
	public void validate(final Request<CreditCard> request, final CreditCard entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("expirationYear") && !errors.hasErrors("expirationMonth")) {
			YearMonth ym = YearMonth.now();
			YearMonth introducido = YearMonth.of(entity.getExpirationYear(), entity.getExpirationMonth());
			boolean cmp = introducido.isBefore(ym);
			errors.state(request, !cmp, "expirationYear", "administrator.creditCard.error.expiration");
			errors.state(request, !cmp, "expirationMonth", "administrator.creditCard.error.expiration");
		}
		if (!errors.hasErrors("cvv")) {
			boolean rangoCVV = String.valueOf(entity.getCvv()).length() == 3;
			errors.state(request, rangoCVV, "cvv", "administrator.creditCard.error.cvv");
		}
	}

	@Override
	public void create(final Request<CreditCard> request, final CreditCard entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
