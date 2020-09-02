
package acme.features.authenticated.investmentRound;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.entities.roles.Investor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedInvestmentRoundRepository extends AbstractRepository {

	@Query("select a from InvestmentRound a where a.id = ?1")
	InvestmentRound findOneById(int id);

	@Query("select a from InvestmentRound a ")
	Collection<InvestmentRound> findMany();

	@Query("select a from InvestmentRound a where a.status = 'published' ")
	Collection<InvestmentRound> findManyActive();

	@Query("select i from InvestmentRound i where i.id in (SELECT r.investmentRound.id FROM InvestmentRoundApplication r where r.statement = 'accepted' and r.investor.userAccount.id = ?1)")
	Collection<InvestmentRound> findManyById(int idInvestor);

	@Query("select a from Investor a where a.userAccount.id = ?1")
	Investor findInvestor(int idAuth);

	@Query("select a from Entrepreneur a where a.userAccount.id = ?1")
	Entrepreneur findEntrepreneur(int idAuth);

	@Query("select a from InvestmentRound a where a.entrepreneur.id = ?1")
	Collection<InvestmentRound> findManyMine(int idEntrepreneur);
}
