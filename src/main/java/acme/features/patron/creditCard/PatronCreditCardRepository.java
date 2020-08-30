
package acme.features.patron.creditCard;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.CreditCard;
import acme.entities.roles.Patron;
import acme.framework.entities.Administrator;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronCreditCardRepository extends AbstractRepository {

	@Query("select cc from CreditCard cc where cc.id = ?1")
	CreditCard findOneById(int id);

	@Query("select cc from CreditCard cc where cc.administrator.id = ?1")
	Collection<CreditCard> findByAdministratorId(int id);

	@Query("select cc from CreditCard cc")
	Collection<CreditCard> findMany();

	@Query("select a from Administrator a where a.userAccount.id = ?1")
	Administrator findOneAdministratorByUserAccountId(int id);

	@Query("select cc from CreditCard cc where cc.patron.id = ?1")
	CreditCard findByPatronId(int id);

	@Query("select cc from CreditCard cc where cc.patron.id = ?1")
	Collection<CreditCard> findManyByPatronId(int id);

	@Query("select p from Patron p where p.userAccount.id = ?1")
	Patron findOnePatronByUserAccountId(int id);

	@Query("select a from Administrator a")
	List<Administrator> findOneAdministrator();

	@Query("select count(c) from CreditCard c where c.patron.id = ?1")
	int countCreditCardsOfPatron(int id);

}
