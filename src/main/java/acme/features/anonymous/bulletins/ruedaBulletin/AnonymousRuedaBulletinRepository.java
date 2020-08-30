
package acme.features.anonymous.bulletins.ruedaBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bulletins.RuedaBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousRuedaBulletinRepository extends AbstractRepository {

	@Query("select r from RuedaBulletin r")
	Collection<RuedaBulletin> findMany();
}
