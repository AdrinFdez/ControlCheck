
package acme.features.entrepreneur.investmentRoundApplication;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.customParams.CustomParams;
import acme.entities.discussionForums.DiscussionForum;
import acme.entities.investmentRoundApplications.InvestmentRoundApplication;
import acme.entities.investmentRounds.InvestmentRound;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurInvestmentRoundApplicationRepository extends AbstractRepository {

	@Query("select a from InvestmentRoundApplication a where a.id = ?1")
	InvestmentRoundApplication findOneById(int id);

	@Query("select a from InvestmentRoundApplication a where a.investmentRound.id = ?1")
	Collection<InvestmentRoundApplication> findManyByInvestmentRoundId(int idInvestmentRound);

	@Query("select a from InvestmentRoundApplication a where a.investmentRound.entrepreneur.id = ?1 order by ticker")
	Collection<InvestmentRoundApplication> findAllByTicker(int idEntrepreneur);

	@Query("select a from InvestmentRoundApplication a where a.investmentRound.entrepreneur.id = ?1 order by creationMoment")
	Collection<InvestmentRoundApplication> findAllByCreation(int idEntrepreneur);

	@Query("select a from CustomParams a")
	CustomParams getCustomParams();

	@Query("select a from Authenticated a where a.userAccount.id = ?1")
	Authenticated findOneAuthenticatedById(int authenticatedId);

	@Query("select r from InvestmentRound r where r.id = ?1")
	InvestmentRound findInvestmentById(int idInvestmentRound);

	@Query("select df from DiscussionForum df where df.investmentRound.id = ?1")
	List<DiscussionForum> findDiscussionForumOfInvestment(int idRound);

	@Query("select r from DiscussionForum r where r.id = ?1")
	DiscussionForum findForumById(int idInvestmentRound);

	@Query("select a.pass from InvestmentRoundApplication a where a.id = ?1")
	String findPass(int idApplication);

}
