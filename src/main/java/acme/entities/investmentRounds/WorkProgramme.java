
package acme.entities.investmentRounds;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class WorkProgramme extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotNull
	@OneToOne(cascade = CascadeType.REMOVE)
	private InvestmentRound		investmentRound;

}
