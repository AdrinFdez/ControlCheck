
package acme.features.authenticated.discussionforumuser;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.discussionForums.AuthenticatedDiscussionForum;
import acme.entities.discussionForums.DiscussionForum;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedDiscussionForumUserRepository extends AbstractRepository {

	@Query("select  p from AuthenticatedDiscussionForum p where p.forum.id = ?1")
	Collection<AuthenticatedDiscussionForum> findUsersOnTheForum(int idForum);

	@Query("select a.owner from DiscussionForum a where a.id = ?1")
	Authenticated findOwnerOfTheForum(int idForum);

	@Query("select a from DiscussionForum a where a.id = ?1")
	DiscussionForum findDiscussionForumById(int idForum);

	@Query("select a from Authenticated a where a.id not in (select b.user.id from AuthenticatedDiscussionForum b where b.forum.id = ?1) ")
	List<Authenticated> findUserNotInTheForum(int idForum);

	@Query("select a from Authenticated a where a.id = ?1")
	Authenticated findAuthenticatedById(int idAuth);

	@Query("select a.user from AuthenticatedDiscussionForum a where a.forum.id = ?1 and a.user.id != ?2")
	List<Authenticated> findUsersInTheForum(int idForum, int idOwner);

	@Query("select a from AuthenticatedDiscussionForum a where a.user.id = ?1 and a.forum.id = ?2")
	AuthenticatedDiscussionForum findOneById(int idUser, int idForum);

}
