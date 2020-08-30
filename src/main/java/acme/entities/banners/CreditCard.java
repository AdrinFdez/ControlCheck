
package acme.entities.banners;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;

import acme.entities.roles.Patron;
import acme.framework.entities.Administrator;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CreditCard extends DomainEntity {

	//Serialisation identifier ---------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes ----------------------------------------------------------

	@NotBlank
	@Length(max = 255)
	private String				holder;

	@CreditCardNumber
	private String				cardNumber;

	@NotBlank
	@Length(max = 255)
	private String				brand;

	@Min(1)
	@Max(12)
	@NotNull
	private int					expirationMonth;

	@NotNull
	private int					expirationYear;

	@Min(0)
	@Max(999)
	@NotNull
	private int					cvv;

	// Relationship ----------------------------------------------------------

	@Valid
	@ManyToOne(optional = true)//era necesario
	private Administrator		administrator;

	@Valid
	@OneToOne(optional = true)
	private Patron				patron;

}
