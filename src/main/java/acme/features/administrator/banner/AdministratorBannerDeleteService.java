
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
import acme.framework.services.AbstractDeleteService;

@Service
public class AdministratorBannerDeleteService implements AbstractDeleteService<Administrator, Banner> {

	@Autowired
	AdministratorBannerRepository		repository;

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

		Principal principal;
		int userAccountId;
		Administrator administrator;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		administrator = this.repositoryCreditCard.findOneAdministratorByUserAccountId(userAccountId);

		Collection<CreditCard> cards = this.repositoryCreditCard.findByAdministratorId(administrator.getId());
		request.getModel().setAttribute("creditCard", cards);

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Banner> request, final Banner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "slogan", "imageurl", "targeturl", "creditCard.id");
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
