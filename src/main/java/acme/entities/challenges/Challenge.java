
package acme.entities.challenges;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Challenge extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Length(max = 255)
	private String				title;

	@NotNull
	@Future
	@Temporal(TemporalType.TIMESTAMP)
	private Date				deadlineDate;

	@NotBlank
	@Length(max = 255)
	private String				description;

	@NotBlank
	@Length(max = 255)
	private String				goalExpert;

	@NotBlank
	@Length(max = 255)
	private String				goalAverage;

	@NotBlank
	@Length(max = 255)
	private String				goalRookie;

	@NotNull
	@Valid
	private Money				rewardExpert;

	@NotNull
	@Valid
	private Money				rewardAverage;

	@NotNull
	@Valid
	private Money				rewardRookie;

}
