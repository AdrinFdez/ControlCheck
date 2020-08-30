
package acme.features.patron.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Banner;
import acme.entities.banners.CreditCard;
import acme.entities.customParams.CustomParams;
import acme.entities.roles.Patron;
import acme.features.administrator.creditCard.AdministratorCreditCardRepository;
import acme.forms.SpamCheck;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class PatronBannerCreateService implements AbstractCreateService<Patron, Banner> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private PatronBannerRepository		repository;

	@Autowired
	AdministratorCreditCardRepository	repositoryCreditCard;


	// AbstractCreateService<Patron, Banner> interface ---------------

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

		request.bind(entity, errors, "creditCard");
	}

	@Override
	public void unbind(final Request<Banner> request, final Banner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		/*
		 * Principal principal;
		 * int userAccountId;
		 * Patron patron;
		 *
		 * principal = request.getPrincipal();
		 * userAccountId = principal.getAccountId();
		 * patron = this.repositoryCreditCard.findOnePatronByUserAccountId(userAccountId);
		 */

		request.unbind(entity, model, "slogan", "imageurl", "targeturl");

	}

	@Override
	public Banner instantiate(final Request<Banner> request) {
		assert request != null;

		Banner result;
		Principal principal;
		int userAccountId;
		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		Patron patron;
		Administrator admin;
		patron = this.repositoryCreditCard.findOnePatronByUserAccountId(userAccountId);
		admin = this.repositoryCreditCard.findOneAdministrator().get(0);

		CreditCard creditCard = new CreditCard();
		creditCard.setBrand("VISA");
		creditCard.setCardNumber("6011396602603793");
		creditCard.setCvv(999);
		creditCard.setExpirationMonth(12);
		creditCard.setExpirationYear(2020);
		creditCard.setHolder("John");
		creditCard.setPatron(patron);
		creditCard.setAdministrator(admin);

		result = new Banner();
		result.setPatron(patron);
		result.setCreditCard(creditCard);

		return result;
	}

	@Override
	public void validate(final Request<Banner> request, final Banner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		CustomParams c = this.repository.getCustomParams();

		if (!errors.hasErrors("imageurl")) {
			boolean imageurlSpam = SpamCheck.checkSpam(entity.getImageurl(), c);
			errors.state(request, !imageurlSpam, "imageurl", "patron.banner.error.imageurl.spam");
		}

		if (!errors.hasErrors("slogan")) {
			boolean sloganSpam = SpamCheck.checkSpam(entity.getSlogan(), c);
			errors.state(request, !sloganSpam, "slogan", "patron.banner.error.slogan.spam");
		}

		if (!errors.hasErrors("targeturl")) {
			boolean targeturlSpam = SpamCheck.checkSpam(entity.getTargeturl(), c);
			errors.state(request, !targeturlSpam, "targeturl", "patron.banner.error.targeturl.spam");
		}

		boolean countCreditCards = this.repository.countCreditCardsOfPatron(request.getPrincipal().getActiveRoleId()) > 0;
		errors.state(request, countCreditCards, "targeturl", "patron.banner.nocreditcard.error");

	}

	@Override
	public void create(final Request<Banner> request, final Banner entity) {
		assert request != null;
		assert entity != null;

		CreditCard creditCard;
		Integer patronId;
		Integer userAccountId;
		userAccountId = request.getPrincipal().getAccountId();

		patronId = this.repositoryCreditCard.findOnePatronByUserAccountId(userAccountId).getId();
		creditCard = this.repositoryCreditCard.findByPatronId(patronId);

		entity.setCreditCard(creditCard);
		this.repository.save(entity);
	}

}
