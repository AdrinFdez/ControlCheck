
package acme.features.investor.investmentRoundApplication;

import java.util.Date;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRoundApplications.InvestmentRoundApplication;
import acme.entities.roles.Investor;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class InvestorInvestmentRoundApplicationCreateService implements AbstractCreateService<Investor, InvestmentRoundApplication> {

	@Autowired
	InvestorInvestmentRoundApplicationRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRoundApplication> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<InvestmentRoundApplication> request, final InvestmentRoundApplication entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<InvestmentRoundApplication> request, final InvestmentRoundApplication entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "ticker", "creationMoment", "statement", "offer", "link", "pass", "investmentRound");

		if (request.isMethod(HttpMethod.GET)) {
			int idInvestmentRound = request.getModel().getInteger("idInvestmentRound");
			model.setAttribute("idInvestmentRound", idInvestmentRound);
		}

	}

	@Override
	public InvestmentRoundApplication instantiate(final Request<InvestmentRoundApplication> request) {
		assert request != null;
		InvestmentRoundApplication appl = new InvestmentRoundApplication();
		int idInvestmentRound = request.getModel().getInteger("idInvestmentRound");
		appl.setInvestmentRound(this.repository.findInvestmentRoundById(idInvestmentRound));
		Investor i = this.repository.findInvestorById(request.getPrincipal().getActiveRoleId());
		appl.setInvestor(i);
		String statement = "pending";
		appl.setStatement(statement);
		return appl;

	}

	@Override
	public void validate(final Request<InvestmentRoundApplication> request, final InvestmentRoundApplication entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Date date = new Date();

		if (!errors.hasErrors("offer")) {
			boolean moneyCurrencyMax = entity.getOffer().getCurrency().matches("EUR|€|EUROS|Euros|euros|eur");
			errors.state(request, moneyCurrencyMax, "offer", "investor.investmentRoundApplication.error.moneyCurrency");
			boolean noNegOffer = entity.getOffer().getAmount() <= 0.0;
			errors.state(request, !noNegOffer, "offer", "investor.investmentRoundApplication.error.noNegOffer");
			boolean noMayorOffer = entity.getOffer().getAmount() <= entity.getInvestmentRound().getMoney().getAmount();
			errors.state(request, noMayorOffer, "offer", "investor.investmentRoundApplication.error.noMayorOffer");
		}

		if (!errors.hasErrors("ticker")) {
			boolean repetido = this.repository.getTickers(entity.getTicker()) > 0;
			errors.state(request, !repetido, "ticker", "investor.InvestmentRoundApplication.error.ticker");

			String[] ticker = entity.getTicker().split("-");
			boolean comprobacion1 = ticker.length == 3;
			boolean alfin = false;
			if (comprobacion1) {
				String SSS = ticker[0];
				String sec = "";
				if (entity.getInvestor().getSector().length() >= 3) {
					sec = entity.getInvestor().getSector().substring(0, 3).toUpperCase();
				} else {
					if (entity.getInvestor().getSector().length() == 2) {
						sec = entity.getInvestor().getSector().substring(0, 2).toUpperCase();
						sec = "X" + sec;
					} else {
						sec = entity.getInvestor().getSector().substring(0, 1).toUpperCase();
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

			errors.state(request, alfin, "ticker", "investor.investmentRoundApplication.error.ticker1");

			if (!errors.hasErrors("pass") && !entity.getPass().isEmpty()) {
				int letters = 0;
				int digits = 0;
				int punctuation = 0;
				int i = 0;
				while (i < entity.getPass().length()) {
					String s = Character.toString(entity.getPass().charAt(i));
					if (s.matches("^([a-zA-Z]){1}$")) {
						letters++;
					} else if (s.matches("^([0-9]){1}$")) {
						digits++;
					} else if (s.matches("^([$-/:-?{-~!\"^_`\\[\\]]){1}$")) {
						punctuation++;
					}
					i++;
				}
				boolean lengthPassword = entity.getPass().length() >= 10 && letters >= 1 && digits >= 1 && punctuation >= 1;
				errors.state(request, lengthPassword, "pass", "investor.investmentRoundApplication.error.pass");
			}

			if (!errors.hasErrors("link") && !errors.hasErrors("pass")) {
				boolean notLink = entity.getLink().isEmpty() && !entity.getPass().isEmpty();
				errors.state(request, !notLink, "link", "investor.investmentRoundApplication.error.link");
			}

		}

	}

	@Override
	public void create(final Request<InvestmentRoundApplication> request, final InvestmentRoundApplication entity) {
		assert request != null;
		assert entity != null;
		Date fecha = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(fecha);
		if (entity.getPass() != null && entity.getPass() != "") {
			entity.setChecked(false);
		} else {
			entity.setChecked(true);
		}
		this.repository.save(entity);

	}

}
