
package acme.entities.bulletins;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RuedaBulletin extends DomainEntity {

	private static final long	serialVersionUID	= 1L;
	@Length(max = 255)
	@NotBlank
	private String				name;
	@Length(max = 320)
	@NotBlank
	@Email
	private String				email;
	@Length(max = 25)
	@NotBlank
	private String				comment;

}
