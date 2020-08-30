
package acme.entities.roles;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "firm,responsibilityStatement")
})
public class Bookkeeper extends UserRole {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Length(max = 255)
	private String				firm;

	@NotBlank
	@Length(max = 255)
	private String				responsibilityStatement;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
