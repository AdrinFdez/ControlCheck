
package acme.features.administrator.overture;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.overtures.Overture;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorOvertureRepository extends AbstractRepository {

	@Query("select a from Overture a where a.id = ?1")
	Overture findOneById(int id);

	@Query("select a from Overture a where a.deadline > CURRENT_DATE")
	Collection<Overture> findManyAll();

}
