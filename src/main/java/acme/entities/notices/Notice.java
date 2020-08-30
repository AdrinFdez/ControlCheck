
package acme.entities.notices;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Notice extends DomainEntity {

	private static final long	serialVersionUID	= 1L;
	@Length(max = 2048)
	@NotBlank
	@URL
	private String				headerPicture;
	@Length(max = 255)
	@NotBlank
	private String				title;

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	private Date				creationDate;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				deadlineDate;

	@Length(max = 255)
	@NotBlank
	private String				body;

	@Length(max = 2048)
	@URL
	private String				optionalLink1;

	@Length(max = 2048)
	@URL
	private String				optionalLink2;

	/*
	 * @ElementCollection
	 * private List<String> optionalLinks;
	 */

}
