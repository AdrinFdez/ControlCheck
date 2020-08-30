
package acme.features.authenticated.inquiry;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.inquiries.Inquiry;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedInquiryRepository extends AbstractRepository {

	@Query("select a from Inquiry a where a.id = ?1")
	Inquiry findOneById(int id);

	@Query("select a from Inquiry a where a.deadline > CURRENT_DATE")
	Collection<Inquiry> findManyAll();

}
