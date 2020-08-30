
package acme.features.administrator.inquiry;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.inquiries.Inquiry;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorInquiryCreateService implements AbstractCreateService<Administrator, Inquiry> {

	@Autowired
	AdministratorInquiryRepository repository;


	@Override
	public boolean authorise(final Request<Inquiry> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Inquiry> request, final Inquiry entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creation");
	}

	@Override
	public void unbind(final Request<Inquiry> request, final Inquiry entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creation", "deadline", "description", "moneyMin", "moneyMax", "mail");
	}

	@Override
	public Inquiry instantiate(final Request<Inquiry> request) {
		Inquiry res;
		res = new Inquiry();
		Date t = new Date(System.currentTimeMillis() - 1);
		res.setCreation(t);
		return res;
	}

	@Override
	public void validate(final Request<Inquiry> request, final Inquiry entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		if (!errors.hasErrors("moneyMin")) {
			Boolean isEur = entity.getMoneyMin().getCurrency().matches("EUR|€|EUROS|Euros|euros|eur");
			errors.state(request, isEur, "moneyMin", "administrator.inquiry.error.wrong-currency", entity.getMoneyMin());

			Boolean moreThanMin = entity.getMoneyMax().getAmount() > entity.getMoneyMin().getAmount();
			errors.state(request, moreThanMin, "moneyMin", "administrator.inquiry.error.money-min-max");
		}
		if (!errors.hasErrors("moneyMax")) {
			Boolean isEur = entity.getMoneyMax().getCurrency().matches("EUR|€|EUROS|Euros|euros|eur");
			errors.state(request, isEur, "moneyMax", "administrator.inquiry.error.wrong-currency", entity.getMoneyMax());

			Boolean moreThanMin = entity.getMoneyMax().getAmount() > entity.getMoneyMin().getAmount();
			errors.state(request, moreThanMin, "moneyMax", "administrator.inquiry.error.money-min-max");
		}
		if (!errors.hasErrors("deadline")) {
			Boolean isFuture = entity.getDeadline().after(new Date());
			errors.state(request, isFuture, "deadline", "administrator.inquiry.error.past-deadline", entity.getDeadline());
		}
	}

	@Override
	public void create(final Request<Inquiry> request, final Inquiry entity) {
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreation(moment);
		this.repository.save(entity);
	}

}
