/*
 * AuthenticatedCreditCardUpdateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

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
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorBannerUpdateService implements AbstractUpdateService<Administrator, Banner> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorBannerRepository	repository;

	@Autowired
	AdministratorCreditCardRepository		repositoryCreditCard;

	// AbstractUpdateService<Administrator, CreditCard> interface ---------------


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
		result = this.repository.findByid(request.getModel().getInteger("id"));

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
	public void update(final Request<Banner> request, final Banner entity) {
		assert request != null;
		assert entity != null;

		CreditCard creditCard;

		creditCard = this.repositoryCreditCard.findOneById(request.getModel().getInteger("creditCardId"));

		entity.setCreditCard(creditCard);

		this.repository.save(entity);
	}

}
