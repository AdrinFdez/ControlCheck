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

package acme.features.patron.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Banner;
import acme.entities.customParams.CustomParams;
import acme.entities.roles.Patron;
import acme.features.administrator.creditCard.AdministratorCreditCardRepository;
import acme.forms.SpamCheck;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class PatronBannerUpdateService implements AbstractUpdateService<Patron, Banner> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private PatronBannerRepository		repository;

	@Autowired
	AdministratorCreditCardRepository	repositoryCreditCard;

	// AbstractUpdateService<Patron, CreditCard> interface ---------------


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

		request.unbind(entity, model, "slogan", "imageurl", "targeturl", "creditCard");

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
	public void update(final Request<Banner> request, final Banner entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
