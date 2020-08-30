
package acme.entities.banners;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.roles.Patron;
import acme.framework.entities.Administrator;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Banner extends DomainEntity {

	//Serialisation identifier ---------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes ----------------------------------------------------------

	@NotBlank
	@URL
	@Length(max = 2048)
	private String				imageurl;

	@NotBlank
	@Length(max = 255)
	private String				slogan;

	@NotBlank
	@URL
	@Length(max = 2048)
	private String				targeturl;

	// Relationship ----------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private CreditCard			creditCard;

	@Valid
	@ManyToOne(optional = true)
	private Administrator		administrator;

	@Valid
	@ManyToOne(optional = true)
	private Patron				patron;
}
