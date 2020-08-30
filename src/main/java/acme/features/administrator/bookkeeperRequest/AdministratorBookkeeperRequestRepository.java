
package acme.features.administrator.bookkeeperRequest;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bookkeeperRequests.BookkeeperRequest;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorBookkeeperRequestRepository extends AbstractRepository {

	@Query("select br from BookkeeperRequest br where br.id = ?1")
	BookkeeperRequest findOneById(int id);

	@Query("select br from BookkeeperRequest br where br.status is null")
	Collection<BookkeeperRequest> findNotSeen();

	@Query("select a from UserAccount a where a.id = ?1")
	UserAccount findUserAccountById(int id);
}
