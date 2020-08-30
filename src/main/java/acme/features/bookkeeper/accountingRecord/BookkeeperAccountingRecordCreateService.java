
package acme.features.bookkeeper.accountingRecord;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class BookkeeperAccountingRecordCreateService implements AbstractCreateService<Bookkeeper, AccountingRecord> {

	@Autowired
	BookkeeperAccountingRecordRepository repository;


	@Override
	public boolean authorise(final Request<AccountingRecord> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<AccountingRecord> request, final AccountingRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "creationMoment");

	}

	@Override
	public void unbind(final Request<AccountingRecord> request, final AccountingRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		if (request.isMethod(HttpMethod.GET)) {
			int idInvestmentRound = request.getModel().getInteger("idInvestmentRound");
			model.setAttribute("idInvestmentRound", idInvestmentRound);
		}

		request.unbind(entity, model, "title", "body");

	}

	@Override
	public AccountingRecord instantiate(final Request<AccountingRecord> request) {
		AccountingRecord res;
		res = new AccountingRecord();
		Date t = new Date(System.currentTimeMillis() - 1);
		res.setCreationMoment(t);

		int idInvestmentRound = request.getModel().getInteger("idInvestmentRound");
		InvestmentRound i = this.repository.findInvestmentRoundById(idInvestmentRound);
		res.setInvestmentRound(i);
		Bookkeeper a = this.repository.findBookkeeperById(request.getPrincipal().getActiveRoleId());
		res.setBookkeeper(a);

		res.setStatus("draft");

		return res;
	}

	@Override
	public void validate(final Request<AccountingRecord> request, final AccountingRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<AccountingRecord> request, final AccountingRecord entity) {
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(moment);
		this.repository.save(entity);

	}

}
