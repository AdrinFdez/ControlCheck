
package acme.features.investor.activity;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investmentRounds.Activity;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.investmentRounds.WorkProgramme;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InvestorActivityRepository extends AbstractRepository {

	@Query("select i from InvestmentRound i where i.id = ?1")
	InvestmentRound findOneById(int id);

	@Query("select i from Activity i where i.id = ?1")
	Activity findOne(int id);

	@Query("select a from Activity a where a.workProgramme.investmentRound.id = ?1")
	Collection<Activity> findMany(int id);

	@Query("select a.workProgramme.investmentRound from Activity a where a.id = ?1")
	InvestmentRound getInvestmentRounByIdActivity(int idActivity);

	@Query("select a from WorkProgramme a where a.investmentRound.id = ?1")
	WorkProgramme findInvestment(int idInvestmentRound);

	@Query("select a from Activity a where a.workProgramme.investmentRound.id =?1 ")
	List<Activity> findActivitiesOfTheInvestmentRound(int idInvestmentRound);
}
