
package acme.features.anonymous.bulletins.pinerobulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bulletins.PineroBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousPineroBulletinRepository extends AbstractRepository {

	@Query("select fb from PineroBulletin fb")
	Collection<PineroBulletin> findMany();
}
