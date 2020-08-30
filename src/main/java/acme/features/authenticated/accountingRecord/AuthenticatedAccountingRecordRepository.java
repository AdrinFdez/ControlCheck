
package acme.features.authenticated.accountingRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.investmentRounds.InvestmentRound;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAccountingRecordRepository extends AbstractRepository {

	@Query("select a from AccountingRecord a where a.id = ?1")
	AccountingRecord findOneById(int id);

	@Query("select a from AccountingRecord a")
	AccountingRecord findAll(int id);

	@Query("select a from InvestmentRound a where a.id = ?1")
	InvestmentRound findInvestmentRoundById(int id);

	@Query("select ar from AccountingRecord ar where (ar.investmentRound.id = ?1 and ar.status = 'published')")
	Collection<AccountingRecord> findAllAccountingRecordsPublished(int investmentRoundId);

	@Query("select a.investmentRound from AccountingRecord a  	 where a.id = ?1")
	InvestmentRound findInvestmentRoundByAccountingRecordId(int accountingRecordId);
}
