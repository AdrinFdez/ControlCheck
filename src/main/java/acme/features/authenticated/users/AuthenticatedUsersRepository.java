
package acme.features.authenticated.users;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedUsersRepository extends AbstractRepository {

	@Query("select  p.user from AuthenticatedDiscussionForum p where p.forum.id = ?1")
	Collection<Authenticated> findUsersAuthenticatedOnTheForum(int forumId);

	@Query("select count(p) from AuthenticatedDiscussionForum  p where p.user.id = ?1 and p.forum.id = ?2")
	Integer checkIfUserIsInTheForum(int userId, int forumId);

}
