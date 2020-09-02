
package acme.entities.investmentRounds;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
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
public class Activity extends DomainEntity {

	//Serialisation identifier ---------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes ----------------------------------------------------------
	@Length(max = 255)
	@NotBlank
	private String				title;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	private Date				startDate;

	@NotNull
	@Future
	private Date				endDate;

	@NotNull
	@Valid
	private Money				budget;

	@ManyToOne(optional = true)
	private WorkProgramme		workProgramme;

}
