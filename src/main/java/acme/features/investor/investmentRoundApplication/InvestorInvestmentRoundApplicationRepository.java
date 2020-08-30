
package acme.features.investor.investmentRoundApplication;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.customParams.CustomParams;
import acme.entities.investmentRoundApplications.InvestmentRoundApplication;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Investor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InvestorInvestmentRoundApplicationRepository extends AbstractRepository {

	@Query("select a from InvestmentRoundApplication a where a.id = ?1")
	InvestmentRoundApplication findOneById(int id);

	@Query("select a from InvestmentRoundApplication a where a.investor.id = ?1")
	Collection<InvestmentRoundApplication> findManyMine(int idEntrepreneur);

	@Query("select a.id from Investor a where a.userAccount.id = ?1")
	int findInvestorId(int idUserAccount);

	@Query("select a from Investor a where a.id = ?1")
	Investor findInvestorById(int id);

	@Query("select count(a) from InvestmentRound a where a.ticker = ?1")
	Integer getTickers(String a);

	@Query("select a from InvestmentRound a where a.id = ?1")
	InvestmentRound findInvestmentRoundById(int id);

	@Query("select a from CustomParams a")
	CustomParams getCustomParams();
}
