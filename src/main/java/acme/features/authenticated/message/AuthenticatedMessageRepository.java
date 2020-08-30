
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.discussionForums.DiscussionForum;
import acme.entities.discussionForums.Message;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageRepository extends AbstractRepository {

	@Query("select m from Message m where m.id = ?1")
	Message findOneById(int id);

	@Query("select count(p) from AuthenticatedDiscussionForum  p where p.user.id = ?1 and p.forum.id = ?2")
	Integer checkIfUserIsInTheForum(int userId, int forumId);

	@Query("select m from Message m where m.forum.id = ?1")
	Collection<Message> findMessagesFromForum(int idForum);

	@Query("select df from DiscussionForum df where df.id = ?1")
	DiscussionForum findDiscussionForum(int idForum);

	@Query("select a from Authenticated a where a.id = ?1")
	Authenticated findUser(int idUser);

}
