
package acme.features.entrepreneur.investmentRound;

import java.util.Date;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customParams.CustomParams;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.investmentRounds.WorkProgramme;
import acme.entities.roles.Entrepreneur;
import acme.forms.SpamCheck;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class EntrepreneurInvestmentRoundCreateService implements AbstractCreateService<Entrepreneur, InvestmentRound> {

	@Autowired
	EntrepreneurInvestmentRoundRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "ticker", "kind", "title", "description", "money", "link", "status", "text");

	}

	@Override
	public InvestmentRound instantiate(final Request<InvestmentRound> request) {
		assert request != null;
		InvestmentRound j = new InvestmentRound();
		Entrepreneur e = this.repository.findOneEntrepreneurById(request.getPrincipal().getActiveRoleId());
		String status = "draft";
		j.setEntrepreneur(e);
		j.setStatus(status);
		return j;

	}

	@Override
	public void validate(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Date date = new Date();

		CustomParams c = this.repository.getCustomParams();

		if (!errors.hasErrors("money")) {
			boolean moneyCurrencyMax = entity.getMoney().getCurrency().equals("EUROS") || entity.getMoney().getCurrency().equals("€");
			errors.state(request, moneyCurrencyMax, "money", "entrepreneur.investmentRound.error.moneyCurrency");
			boolean noNegSalary = entity.getMoney().getAmount() <= 0.0;
			errors.state(request, !noNegSalary, "money", "entrepreneur.investmentRound.error.noNegMoney");
		}

		if (!errors.hasErrors("ticker")) {

			boolean repetido = this.repository.getTickers(entity.getTicker()) > 0;
			errors.state(request, !repetido, "ticker", "entrepreneur.investmentRound.error.ticker");
			String[] ticker = entity.getTicker().split("-");
			boolean comprobacion1 = ticker.length == 3;
			boolean alfin = false;
			if (comprobacion1) {
				String SSS = ticker[0];
				String sec = "";
				if (entity.getEntrepreneur().getSector().length() >= 3) {
					sec = entity.getEntrepreneur().getSector().substring(0, 3).toUpperCase();
				} else {
					if (entity.getEntrepreneur().getSector().length() == 2) {
						sec = entity.getEntrepreneur().getSector().substring(0, 2).toUpperCase();
						sec = "X" + sec;
					} else {
						sec = entity.getEntrepreneur().getSector().substring(0, 1).toUpperCase();
						sec = "XX" + sec;
					}

				}
				boolean comprobacionSSS = SSS.equals(sec);
				String YY = ticker[1];
				String fechita = String.valueOf(date.getYear()).substring(1, 3);
				boolean comprobacionYY = fechita.equals(YY);
				boolean comprobacionNNNNNN = ticker[2].length() == 6;
				boolean comprobacionNNNNNN2 = Pattern.matches("^([0-9]{6})$", ticker[2]);
				alfin = comprobacion1 && comprobacionNNNNNN && comprobacionNNNNNN2 && comprobacionSSS && comprobacionYY;
			}

			errors.state(request, alfin, "ticker", "entrepreneur.investmentRound.error.ticker1");

		}

		if (!errors.hasErrors("title")) {
			boolean titleSpam = SpamCheck.checkSpam(entity.getTitle(), c);
			errors.state(request, !titleSpam, "title", "entrepreneur.investmentRound.error.title.spam");
		}

		if (!errors.hasErrors("description")) {
			boolean descriptionSpam = SpamCheck.checkSpam(entity.getDescription(), c);
			errors.state(request, !descriptionSpam, "description", "entrepreneur.investmentRound.error.description.spam");
		}

		if (!errors.hasErrors("text")) {
			boolean textSpam = SpamCheck.checkSpam(entity.getText(), c);
			errors.state(request, !textSpam, "text", "entrepreneur.investmentRound.error.text.spam");
		}

	}

	@Override
	public void create(final Request<InvestmentRound> request, final InvestmentRound entity) {
		assert request != null;
		assert entity != null;
		Date fecha = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(fecha);
		WorkProgramme wp = new WorkProgramme();
		wp.setInvestmentRound(entity);
		this.repository.save(entity);
		this.repository.save(wp);

	}

}
