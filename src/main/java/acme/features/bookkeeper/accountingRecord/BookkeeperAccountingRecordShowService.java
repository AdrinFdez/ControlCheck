
package acme.features.bookkeeper.accountingRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class BookkeeperAccountingRecordShowService implements AbstractShowService<Bookkeeper, AccountingRecord> {

	@Autowired
	BookkeeperAccountingRecordRepository repository;


	@Override
	public boolean authorise(final Request<AccountingRecord> request) {
		assert request != null;

		boolean res;
		int bookkeeperId = request.getPrincipal().getActiveRoleId();

		int idAccountingRecord = request.getModel().getInteger("id");
		AccountingRecord accountingRecord = this.repository.findOneById(idAccountingRecord);

		if (accountingRecord.getBookkeeper().getId() == bookkeeperId) {
			res = true;
		} else if (accountingRecord.getStatus().equals("published")) {
			res = true;
		} else {
			res = false;
		}
		return res;
	}

	@Override
	public void unbind(final Request<AccountingRecord> request, final AccountingRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "status", "creationMoment", "body", "bookkeeper.firm", "investmentRound.ticker", "investmentRound.title");
	}
	@Override
	public AccountingRecord findOne(final Request<AccountingRecord> request) {
		assert request != null;

		AccountingRecord result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}
}
