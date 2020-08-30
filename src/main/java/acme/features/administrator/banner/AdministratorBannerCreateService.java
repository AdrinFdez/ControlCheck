
package acme.features.administrator.banner;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Banner;
import acme.entities.banners.CreditCard;
import acme.features.administrator.creditCard.AdministratorCreditCardRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorBannerCreateService implements AbstractCreateService<Administrator, Banner> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorBannerRepository	repository;

	@Autowired
	AdministratorCreditCardRepository		repositoryCreditCard;


	// AbstractCreateService<Administrator, Banner> interface ---------------

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

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Banner> request, final Banner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Principal principal;
		int userAccountId;
		Administrator administrator;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		administrator = this.repositoryCreditCard.findOneAdministratorByUserAccountId(userAccountId);

		request.unbind(entity, model, "slogan", "imageurl", "targeturl");

		Collection<CreditCard> cards = this.repositoryCreditCard.findByAdministratorId(administrator.getId());
		model.setAttribute("creditCard", cards);

	}

	@Override
	public Banner instantiate(final Request<Banner> request) {
		assert request != null;

		Banner result;
		Principal principal;
		int userAccountId;
		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		Administrator administrator;
		administrator = this.repositoryCreditCard.findOneAdministratorByUserAccountId(userAccountId);
		CreditCard creditCard = new CreditCard();
		creditCard.setBrand("VISA");
		creditCard.setCardNumber("6011396602603793");
		creditCard.setCvv(999);
		creditCard.setExpirationMonth(12);
		creditCard.setExpirationYear(2020);
		creditCard.setHolder("John");
		creditCard.setAdministrator(administrator);

		result = new Banner();
		result.setAdministrator(administrator);
		result.setCreditCard(creditCard);

		Collection<CreditCard> cards = this.repositoryCreditCard.findByAdministratorId(administrator.getId());
		request.getModel().setAttribute("creditCard", cards);

		return result;
	}

	@Override
	public void validate(final Request<Banner> request, final Banner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean countCreditCards = this.repository.countCreditCardsOfAdministrator(request.getPrincipal().getActiveRoleId()) > 0;
		errors.state(request, countCreditCards, "creditCardId", "administrator.banner.nocreditcard.error");
	}

	@Override
	public void create(final Request<Banner> request, final Banner entity) {
		assert request != null;
		assert entity != null;

		CreditCard creditCard;

		creditCard = this.repositoryCreditCard.findOneById(request.getModel().getInteger("creditCardId"));

		entity.setCreditCard(creditCard);

		this.repository.save(entity);
	}

}
