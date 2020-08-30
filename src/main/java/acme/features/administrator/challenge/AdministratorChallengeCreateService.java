
package acme.features.administrator.challenge;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenges.Challenge;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorChallengeCreateService implements AbstractCreateService<Administrator, Challenge> {

	@Autowired
	AdministratorChallengeRepository repository;


	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Challenge> request, final Challenge entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadlineDate", "description", "goalExpert", "goalAverage", "goalRookie", "rewardExpert", "rewardAverage", "rewardRookie");
	}

	@Override
	public Challenge instantiate(final Request<Challenge> request) {
		Challenge res;
		res = new Challenge();
		return res;
	}

	@Override
	public void validate(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("rewardRookie")) {
			Boolean isRewardRookieEur = entity.getRewardRookie().getCurrency().matches("EUR|€|EUROS|Euros|euros|eur");
			errors.state(request, isRewardRookieEur, "rewardRookie", "administrator.challenge.error.wrong-currency", entity.getRewardRookie());

			Boolean lessThanAverage = entity.getRewardAverage().getAmount() > entity.getRewardRookie().getAmount();
			errors.state(request, lessThanAverage, "rewardRookie", "administrator.challenge.error.average-more-than-rookie");
		}

		if (!errors.hasErrors("rewardAverage")) {
			Boolean isRewardAverageEur = entity.getRewardAverage().getCurrency().matches("EUR|€|EUROS|Euros|euros|eur");
			errors.state(request, isRewardAverageEur, "rewardAverage", "administrator.challenge.error.wrong-currency", entity.getRewardAverage());

			Boolean moreThanRookie = entity.getRewardAverage().getAmount() > entity.getRewardRookie().getAmount();
			errors.state(request, moreThanRookie, "rewardAverage", "administrator.challenge.error.average-more-than-rookie");
		}

		if (!errors.hasErrors("rewardExpert")) {
			Boolean isRewardExpertEur = entity.getRewardExpert().getCurrency().matches("EUR|€|EUROS|Euros|euros|eur");
			errors.state(request, isRewardExpertEur, "rewardExpert", "administrator.challenge.error.wrong-currency", entity.getRewardExpert());

			Boolean moreThanAverage = entity.getRewardExpert().getAmount() > entity.getRewardAverage().getAmount();
			errors.state(request, moreThanAverage, "rewardExpert", "administrator.challenge.error.expert-higher-than-average");
		}

		if (!errors.hasErrors("deadlineDate")) {
			Boolean isFuture = entity.getDeadlineDate().after(new Date());
			errors.state(request, isFuture, "deadlineDate", "administrator.challenge.error.past-deadline", entity.getDeadlineDate());
		}
		if (!errors.hasErrors("deadlineDate")) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.MONTH, 1);
			Boolean isFuture1Month = entity.getDeadlineDate().after(calendar.getTime());
			errors.state(request, isFuture1Month, "deadlineDate", "administrator.challenge.error.past-deadline-1month", entity.getDeadlineDate());
		}

	}

	@Override
	public void create(final Request<Challenge> request, final Challenge entity) {
		this.repository.save(entity);
	}

}
