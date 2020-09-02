
package acme.features.investor.investmentRound;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investmentRounds.InvestmentRound;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InvestorInvestmentRoundRepository extends AbstractRepository {

	@Query("select a from InvestmentRound a where a.id = ?1")
	InvestmentRound findOneById(int id);

	@Query("select a from InvestmentRound a where a.status = 'published'")
	Collection<InvestmentRound> findManyPublished();

	@Query("select a.id from Investor a where a.userAccount.id = ?1")
	int findInvestorId(int idUserAccount);

	@Query("select i from InvestmentRound i where i.id in (SELECT r.investmentRound.id FROM InvestmentRoundApplication r where r.statement = 'accepted' and r.investor.id = ?1)")
	Collection<InvestmentRound> findManyById(int idInvestor);
}
