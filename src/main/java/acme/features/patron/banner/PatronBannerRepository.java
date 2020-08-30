
package acme.features.patron.banner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.Banner;
import acme.entities.customParams.CustomParams;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronBannerRepository extends AbstractRepository {

	@Query("select b from Banner b where b.id = ?1")
	Banner findByid(int id);

	@Query("select b from Banner b where b.administrator.id = ?1")
	Collection<Banner> findManyByAdministratorId(int administratorId);

	@Query("select b from Banner b where b.patron.id = ?1")
	Collection<Banner> findManyByPatronId(int patronId);

	@Query("select b from Banner b")
	Collection<Banner> findMany();

	@Query("select count(c) from CreditCard c where c.patron.id = ?1")
	int countCreditCardsOfPatron(int id);

	@Query("select a from CustomParams a")
	CustomParams getCustomParams();

}
