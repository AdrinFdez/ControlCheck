
package acme.entities.inquiries;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Inquiry extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@Length(max = 255)
	@NotBlank
	private String				title;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				creation;

	@Temporal(TemporalType.TIMESTAMP)
	@Future
	@NotNull
	private Date				deadline;

	@Length(max = 1024)
	@NotBlank
	private String				description;

	@Valid
	@NotNull
	private Money				moneyMin;

	@Valid
	@NotNull
	private Money				moneyMax;

	@NotBlank
	@Email
	@Length(max = 320)
	private String				mail;

}
