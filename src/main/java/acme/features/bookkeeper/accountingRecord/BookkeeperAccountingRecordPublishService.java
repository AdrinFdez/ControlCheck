
package acme.features.bookkeeper.accountingRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class BookkeeperAccountingRecordPublishService implements AbstractUpdateService<Bookkeeper, AccountingRecord> {

	@Autowired
	BookkeeperAccountingRecordRepository repository;


	@Override
	public boolean authorise(final Request<AccountingRecord> request) {
		assert request != null;
		int idAccountingRecord = request.getModel().getInteger("id");
		AccountingRecord accountingRecord = this.repository.findOneById(idAccountingRecord);
		int bookkeeperId = request.getPrincipal().getActiveRoleId();
		Boolean res = accountingRecord.getBookkeeper().getId() == bookkeeperId && accountingRecord.getStatus().equals("draft");
		return res;
	}

	@Override
	public void bind(final Request<AccountingRecord> request, final AccountingRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<AccountingRecord> request, final AccountingRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "body", "creationMoment", "bookkeeper.firm", "investmentRound.title", "investmentRound.ticker", "status");

	}

	@Override
	public AccountingRecord findOne(final Request<AccountingRecord> request) {
		assert request != null;
		int idAccountingRecord = request.getModel().getInteger("id");
		AccountingRecord accountingRecord = this.repository.findOneById(idAccountingRecord);
		return accountingRecord;

	}

	@Override
	public void validate(final Request<AccountingRecord> request, final AccountingRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void update(final Request<AccountingRecord> request, final AccountingRecord entity) {
		assert request != null;
		assert entity != null;
		entity.setStatus("published");
		this.repository.save(entity);
	}
}
