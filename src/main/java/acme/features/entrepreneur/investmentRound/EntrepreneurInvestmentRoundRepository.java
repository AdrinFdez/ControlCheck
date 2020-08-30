
package acme.features.entrepreneur.investmentRound;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.customParams.CustomParams;
import acme.entities.investmentRounds.Activity;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.investmentRounds.WorkProgramme;
import acme.entities.roles.Entrepreneur;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurInvestmentRoundRepository extends AbstractRepository {

	@Query("select a from InvestmentRound a where a.id = ?1")
	InvestmentRound findOneById(int id);

	@Query("select a from InvestmentRound a where a.entrepreneur.id = ?1")
	Collection<InvestmentRound> findManyMine(int idEntrepreneur);

	@Query("select a.id from Entrepreneur a where a.userAccount.id = ?1")
	int findEntrepreneurId(int idUserAccount);

	@Query("select a from Entrepreneur a where a.id = ?1")
	Entrepreneur findOneEntrepreneurById(int id);

	@Query("select a from Activity a where a.workProgramme.investmentRound.id =?1 ")
	List<Activity> findActivitiesOfTheInvestmentRound(int idInvestmentRound);

	@Query("select a.ticker from InvestmentRound a where a.id = ?1")
	String SelectTickerById(int id);

	@Query("select count(a) from InvestmentRound a where a.ticker = ?1")
	Integer getTickers(String a);

	@Query("select a from CustomParams a")
	CustomParams getCustomParams();

	@Query("select a from WorkProgramme a where a.investmentRound.id = ?1")
	WorkProgramme findWorkProgrammeByInvestment(int idInvestmentRound);

}
