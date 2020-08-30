
package acme.features.patron.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Banner;
import acme.entities.banners.CreditCard;
import acme.entities.roles.Patron;
import acme.features.administrator.creditCard.AdministratorCreditCardRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class PatronBannerShowService implements AbstractShowService<Patron, Banner> {

	@Autowired
	private PatronBannerRepository		repository;

	@Autowired
	AdministratorCreditCardRepository	repositoryCreditCard;


	@Override
	public boolean authorise(final Request<Banner> request) {
		assert request != null;
		boolean result;
		int bannerId;
		Banner b;
		Patron patron;
		Principal principal;

		bannerId = request.getModel().getInteger("id");
		b = this.repository.findByid(bannerId);
		patron = b.getPatron();
		principal = request.getPrincipal();
		result = patron.getUserAccount().getId() == principal.getAccountId();
		return result;
	}

	@Override
	public void unbind(final Request<Banner> request, final Banner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Principal principal;
		int userAccountId;
		Patron patron;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		patron = this.repositoryCreditCard.findOnePatronByUserAccountId(userAccountId);

		CreditCard card = this.repositoryCreditCard.findByPatronId(patron.getId());
		model.setAttribute("creditCard", card);

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

}
