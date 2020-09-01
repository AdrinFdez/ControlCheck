
package acme.entities.investmentRounds;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.roles.Entrepreneur;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class InvestmentRound extends DomainEntity {

	//Serialisation identifier ---------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes ----------------------------------------------------------

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^([A-Z]{3}-[0-9]{2}-[0-9]{6})$")
	private String				ticker;

	private Date				creationMoment;

	@Pattern(regexp = "^(SEED|ANGEL|SERIES-A|SERIES-B|SERIES-C|BRIDGE)$")
	@NotBlank
	private String				kind;

	@Length(max = 255)
	@NotBlank
	private String				title;

	@Length(max = 255)
	@NotBlank
	private String				description;

	@NotNull
	@Valid
	private Money				money;

	@Column(length = 2048)
	@Length(max = 2048)
	@URL
	private String				link;

	@Pattern(regexp = "^(draft|published)$")
	@NotBlank
	private String				status;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Entrepreneur		entrepreneur;

	//CONTROL CHECK

	@Length(max = 2048)
	@Column(length = 2048)
	private String				text;

}
