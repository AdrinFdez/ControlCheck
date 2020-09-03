
package acme.entities.investmentRoundApplications;

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

import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Investor;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class InvestmentRoundApplication extends DomainEntity {

	//Serialisation identifier ---------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes ----------------------------------------------------------

	@NotBlank
	@Column(unique = true)
	private String				ticker;

	private Date				creationMoment;

	private Date				updateMoment;

	@NotBlank
	@Pattern(regexp = "^(pending|accepted|rejected)$")
	private String				statement;

	@Valid
	@NotNull
	private Money				offer;

	@Column(length = 1024)
	@Length(max = 1024)
	private String				justification;

	@NotNull
	@ManyToOne(optional = false)
	private InvestmentRound		investmentRound;

	@NotNull
	@ManyToOne(optional = false)
	private Investor			investor;

	//CONTROL CHECK

	@URL
	@Column(length = 2048)
	@Length(max = 2048)
	private String				link;

	@Length(max = 255)
	private String				pass;

	private Boolean				checked;

}
