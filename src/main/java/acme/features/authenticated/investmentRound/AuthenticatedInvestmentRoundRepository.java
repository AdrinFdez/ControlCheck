
package acme.features.authenticated.investmentRound;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investmentRounds.InvestmentRound;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedInvestmentRoundRepository extends AbstractRepository {

	@Query("select a from InvestmentRound a where a.id = ?1")
	InvestmentRound findOneById(int id);

	@Query("select a from InvestmentRound a ")
	Collection<InvestmentRound> findMany();
}
