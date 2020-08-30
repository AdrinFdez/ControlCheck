
package acme.features.bookkeeper.investmentRound;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investmentRounds.InvestmentRound;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BookkeeperInvestmentRoundRepository extends AbstractRepository {

	@Query("select distinct a.investmentRound from AccountingRecord a where a.bookkeeper.id = ?1")
	List<InvestmentRound> listInvestmentRoundAccounted(int id);

	@Query("select a from InvestmentRound a where a.id = ?1")
	InvestmentRound findOneById(int id);

	@Query("select a from InvestmentRound a where id not in (select b.investmentRound.id from AccountingRecord b where b.bookkeeper.id =?1) and  a.status = 'published'")
	Collection<InvestmentRound> listInvestmentRoundNonAccounted(int id);
}
