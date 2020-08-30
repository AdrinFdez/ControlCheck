
package acme.entities.bookkeeperRequests;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import acme.framework.entities.Authenticated;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "status")
})
public class BookkeeperRequest extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotNull
	@Valid
	@ManyToOne()
	private Authenticated		user;

	private Boolean				status;

	@Length(max = 255)
	@NotBlank
	private String				firm;

	@Length(max = 255)
	@NotBlank
	private String				responsibilityStatement;

}
