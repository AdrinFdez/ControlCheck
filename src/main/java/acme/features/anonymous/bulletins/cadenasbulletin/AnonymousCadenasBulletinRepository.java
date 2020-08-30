
package acme.features.anonymous.bulletins.cadenasbulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bulletins.CadenasBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousCadenasBulletinRepository extends AbstractRepository {

	@Query("select cb from CadenasBulletin cb")
	Collection<CadenasBulletin> findMany();
}
