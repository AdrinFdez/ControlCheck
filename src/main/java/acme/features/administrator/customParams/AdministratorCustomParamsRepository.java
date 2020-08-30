
package acme.features.administrator.customParams;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.customParams.CustomParams;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorCustomParamsRepository extends AbstractRepository {

	@Query("select a from CustomParams a")
	Collection<CustomParams> findMany();

}
