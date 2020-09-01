
package acme.entities.customParams;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CustomParams extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@Column(length = 1024)
	@Length(max = 1024)
	@NotBlank
	String						spamWords;

	@NotNull
	@Min(0)
	@Max(100)
	Double						spamThreshold;

	@Length(max = 255)
	@NotBlank
	String						sectors;

}
