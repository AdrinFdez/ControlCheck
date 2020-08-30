
package acme.features.bookkeeper.accountingRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Bookkeeper;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BookkeeperAccountingRecordRepository extends AbstractRepository {

	@Query("select a from AccountingRecord a where a.id = ?1")
	AccountingRecord findOneById(int id);

	@Query("select a from AccountingRecord a")
	AccountingRecord findAll(int id);

	@Query("select a from InvestmentRound a where a.id = ?1")
	InvestmentRound findInvestmentRoundById(int id);

	@Query("select a from Bookkeeper a where a.id = ?1")
	Bookkeeper findBookkeeperById(int id);

	@Query("select a from Bookkeeper a where a.userAccount = ?1")
	Bookkeeper findBookkeeperByUserAcountId(int id);

	@Query("select a from AccountingRecord a where (a.bookkeeper.id =?1 and a.investmentRound.id = ?2)")
	Collection<AccountingRecord> findMyAccountingRecords(int idBookkeeper, int investmentRoundId);

	@Query("select ar from AccountingRecord ar where (ar.investmentRound.id = ?1 and ar.status = 'published')")
	Collection<AccountingRecord> findAllAccountingRecordsPublished(int investmentRoundId);

	@Query("select a.investmentRound from AccountingRecord a  	 where a.id = ?1")
	InvestmentRound findInvestmentRoundByAccountingRecordId(int accountingRecordId);

}
