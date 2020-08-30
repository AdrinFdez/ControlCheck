
package acme.features.administrator.banner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.Banner;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorBannerRepository extends AbstractRepository {

	@Query("select b from Banner b where b.id = ?1")
	Banner findByid(int id);

	@Query("select b from Banner b where b.administrator.id = ?1")
	Collection<Banner> findManyByAdministratorId(int administratorId);

	@Query("select b from Banner b")
	Collection<Banner> findMany();

	@Query("select count(c) from CreditCard c where c.administrator.id = ?1")
	int countCreditCardsOfAdministrator(int id);

}
