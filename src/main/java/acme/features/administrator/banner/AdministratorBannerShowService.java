
package acme.features.administrator.banner;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Banner;
import acme.entities.banners.CreditCard;
import acme.features.administrator.creditCard.AdministratorCreditCardRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorBannerShowService implements AbstractShowService<Administrator, Banner> {

	@Autowired
	private AdministratorBannerRepository	repository;

	@Autowired
	AdministratorCreditCardRepository		repositoryCreditCard;


	@Override
	public boolean authorise(final Request<Banner> request) {
		assert request != null;
		boolean result;
		int bannerId;
		Banner b;
		Administrator administrator;
		Principal principal;

		bannerId = request.getModel().getInteger("id");
		b = this.repository.findByid(bannerId);
		administrator = b.getAdministrator();
		principal = request.getPrincipal();
		result = administrator.getUserAccount().getId() == principal.getAccountId();
		return result;
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

		Collection<CreditCard> cards = this.repositoryCreditCard.findByAdministratorId(administrator.getId());
		model.setAttribute("creditCard", cards);

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
