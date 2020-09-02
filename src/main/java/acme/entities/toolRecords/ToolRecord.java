
package acme.entities.toolRecords;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ToolRecord extends DomainEntity {

	//Serialisation identifier ---------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes ----------------------------------------------------------
	@Length(max = 255)
	@NotBlank
	private String				title;

	@Length(max = 255)
	@NotBlank
	private String				sector;

	@Length(max = 255)
	@NotBlank
	private String				inventorName;

	@Column(length = 1024)
	@Length(max = 1024)
	@NotBlank
	private String				description;

	@Column(length = 2048)
	@Length(max = 2048)
	@NotBlank
	@URL
	private String				website;

	@Column(length = 320)
	@Length(max = 320)
	@NotBlank
	@Email
	private String				email;

	private Boolean				openSource;

	@Min(value = -5)
	@Max(value = 5)
	private Integer				rating;

}
