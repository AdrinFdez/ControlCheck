
package acme.features.authenticated.discussionForum;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.discussionForums.AuthenticatedDiscussionForum;
import acme.entities.discussionForums.DiscussionForum;
import acme.entities.discussionForums.Message;
import acme.entities.investmentRounds.InvestmentRound;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedDiscussionForumRepository extends AbstractRepository {

	@Query("select df from DiscussionForum df where df.id = ?1")
	DiscussionForum findOneById(int id);

	@Query("select a from Authenticated a where a.id = ?1")
	Authenticated findOneAuthenticatedById(int authenticatedId);

	@Query("select i from InvestmentRound i where i.id = ?1")
	InvestmentRound findOneInvestmentRoundById(int roundId);

	@Query("select count(p) from AuthenticatedDiscussionForum  p where p.user.id = ?1 and p.forum.id = ?2")
	Integer checkIfUserIsInTheForum(int userId, int forumId);

	@Query("select p.forum from AuthenticatedDiscussionForum p where p.user.id = ?1")
	Collection<DiscussionForum> findForumByUser(int authId);

	@Query("select p from DiscussionForum p where p.owner.id = ?1")
	Collection<DiscussionForum> findForumByOwner(int ownerId);

	@Query("select  p.user.id from AuthenticatedDiscussionForum p where p.forum.id = ?1")
	List<Integer> findUsersOnTheForum(int forumId);

	@Query("select a.owner from DiscussionForum a where a.id = ?1")
	Authenticated findOwnerOfTheForum(int idForum);

	@Query("select  p.user from AuthenticatedDiscussionForum p where p.forum.id = ?1")
	Collection<Authenticated> findUsersAuthenticatedOnTheForum(int forumId);

	@Query("select  p from AuthenticatedDiscussionForum p where p.forum.id = ?1 and p.user.id = ?2")
	AuthenticatedDiscussionForum findAuthenticatedByForum(int forumId, int idOwner);

	@Query("select a.username, a.identity.name , a.identity.surname from UserAccount a where a.id in (select b.userAccount.id from Authenticated b where b.id in ?1)")
	String[][] findDataFromUsers(List<Integer> idsAuthenticateds);

	@Query("select df from DiscussionForum df where df.investmentRound.id = ?1")
	Collection<DiscussionForum> findManyByInvestmentRoundId(int idInvestment);

	@Query("select df from DiscussionForum df where df.owner.id = ?1")
	Collection<DiscussionForum> findManyMine(int idOwner);

	@Query("select  m from Message m where m.forum.id = ?1")
	List<Message> findMessagesByForum(int forumId);

	@Query("select  m from AuthenticatedDiscussionForum m where m.forum.id = ?1")
	List<AuthenticatedDiscussionForum> findAuthenticatedForumByForum(int forumId);

	@Query("select count(p) from DiscussionForum  p where p.owner.id = ?1 and p.id = ?2")
	Integer checkIfUserIsOwner(int authId, int discussionForumId);

}
