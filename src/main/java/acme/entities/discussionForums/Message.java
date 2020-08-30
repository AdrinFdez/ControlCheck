
package acme.entities.discussionForums;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
public class Message extends DomainEntity {

	//Serialisation identifier ---------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes ----------------------------------------------------------
	@Length(max = 255)
	@NotBlank
	private String				title;

	@NotNull
	private Date				creationMoment;
	@Length(max = 255)
	private String				tags;
	@Length(max = 1024)
	@NotBlank
	private String				body;

	// Relationship ----------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private DiscussionForum		forum;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Authenticated		user;

}
