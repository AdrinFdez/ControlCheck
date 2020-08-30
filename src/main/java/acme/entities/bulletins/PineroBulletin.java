
package acme.entities.bulletins;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PineroBulletin extends DomainEntity {

	//Serialisation identifier ------------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	@Length(max = 255)
	@NotBlank
	private String				name;
	@Length(max = 255)
	@NotBlank
	private String				address;
	@Length(max = 320)
	@NotBlank
	@Email
	private String				email;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				date;
	@Length(max = 255)
	@NotBlank
	private String				description;

}
