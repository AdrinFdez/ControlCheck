
package acme.features.patron.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Banner;
import acme.entities.roles.Patron;
import acme.features.administrator.creditCard.AdministratorCreditCardRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class PatronBannerDeleteService implements AbstractDeleteService<Patron, Banner> {

	@Autowired
	PatronBannerRepository				repository;

	@Autowired
	AdministratorCreditCardRepository	repositoryCreditCard;


	@Override
	public boolean authorise(final Request<Banner> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Banner> request, final Banner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		/*
		 * Principal principal;
		 * int userAccountId;
		 * Patron patron;
		 *
		 * principal = request.getPrincipal();
		 * userAccountId = principal.getAccountId();
		 * patron = this.repositoryCreditCard.findOnePatronByUserAccountId(userAccountId);
		 *
		 * CreditCard card = this.repositoryCreditCard.findByPatronId(patron.getId());
		 * request.getModel().setAttribute("creditCard", card);
		 */

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Banner> request, final Banner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "slogan", "imageurl", "targeturl", "creditCard.cardNumber");
	}

	@Override
	public Banner findOne(final Request<Banner> request) {
		assert request != null;

		Banner result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findByid(id);

		return result;
	}

	@Override
	public void validate(final Request<Banner> request, final Banner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void delete(final Request<Banner> request, final Banner entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);
	}

}
