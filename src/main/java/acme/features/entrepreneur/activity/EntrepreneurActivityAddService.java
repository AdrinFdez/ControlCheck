
package acme.features.entrepreneur.activity;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customParams.CustomParams;
import acme.entities.investmentRounds.Activity;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.forms.SpamCheck;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;

@Service
public class EntrepreneurActivityAddService implements AbstractCreateService<Entrepreneur, Activity> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private EntrepreneurActivityRepository repository;

	// AbstractCreateService<Entrepreneur, Activity> ---------------------------


	@Override
	public boolean authorise(final Request<Activity> request) {
		assert request != null;
		int id = request.getModel().getInteger("idInvestmentRound");
		InvestmentRound i = this.repository.findOneById(id);
		int entrepreneurId = request.getPrincipal().getActiveRoleId();
		Integer id1 = i.getEntrepreneur().getId();
		boolean res = id1 == entrepreneurId && i.getStatus().equals("draft");
		return res;
	}

	@Override
	public void bind(final Request<Activity> request, final Activity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "startDate");

	}

	@Override
	public void unbind(final Request<Activity> request, final Activity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "title", "endDate", "budget");
		if (request.isMethod(HttpMethod.GET)) {
			int idInvestmentRound = request.getModel().getInteger("idInvestmentRound");
			model.setAttribute("idInvestmentRound", idInvestmentRound);
		}
	}

	@Override
	public Activity instantiate(final Request<Activity> request) {
		assert request != null;
		int idInvestmentRound = request.getModel().getInteger("idInvestmentRound");
		Activity a = new Activity();
		Date t = new Date(System.currentTimeMillis() - 1);
		a.setStartDate(t);
		a.setWorkProgramme(this.repository.findByInvestment(idInvestmentRound));
		return a;
	}

	@Override
	public void validate(final Request<Activity> request, final Activity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		CustomParams c = this.repository.getCustomParams();

		if (!errors.hasErrors("budget")) {
			boolean moneyCurrencyMax = entity.getBudget().getCurrency().matches("EUR|€|EUROS|Euros|euros|eur");
			errors.state(request, moneyCurrencyMax, "budget", "entrepreneur.activity.error.moneyCurrency");
			boolean noNegSalary = entity.getBudget().getAmount() <= 0.0;
			errors.state(request, !noNegSalary, "budget", "entrepreneur.activity.error.noNegMoney");
		}

		InvestmentRound j = entity.getWorkProgramme().getInvestmentRound();
		List<Activity> activities = this.repository.findActivitiesOfTheInvestmentRound(j.getId());
		if (entity.getBudget() != null) {
			activities.add(entity);
		}
		Money moneyInvestment = j.getMoney();
		Money total = new Money();
		total.setAmount(0.0);
		total.setCurrency("euros");

		for (Activity activity : activities) {
			Double i = total.getAmount() + activity.getBudget().getAmount();
			total.setAmount(i);
		}
		boolean totalActivityMoney = total.getAmount().compareTo(moneyInvestment.getAmount()) <= 0;
		errors.state(request, totalActivityMoney, "budget", "entrepreneur.activity.error.total");

		if (!errors.hasErrors("title")) {
			boolean titleSpam = SpamCheck.checkSpam(entity.getTitle(), c);
			errors.state(request, !titleSpam, "title", "entrepreneur.activity.error.title.spam");
		}

	}

	@Override
	public void create(final Request<Activity> request, final Activity entity) {
		assert request != null;
		assert entity != null;
		Date fecha = new Date(System.currentTimeMillis() - 1);
		entity.setStartDate(fecha);
		this.repository.save(entity);
	}
}
