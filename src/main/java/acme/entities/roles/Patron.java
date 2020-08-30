
package acme.entities.roles;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import acme.entities.banners.CreditCard;
import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Patron extends UserRole {

	private static final long	serialVersionUID	= 1L;

	@Length(max = 255)
	@NotBlank
	private String				organizationName;

	// Relationship ----------------------------------------------------------

	@Valid
	@OneToOne(optional = true)
	private CreditCard			creditCard;
}
