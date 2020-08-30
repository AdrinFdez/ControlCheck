
package acme.entities.discussionForums;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import acme.framework.entities.Authenticated;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AuthenticatedDiscussionForum extends DomainEntity {

	//Serialisation identifier ---------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Relationship ----------------------------------------------------------

	@Valid
	@ManyToOne(optional = false)
	private Authenticated		user;

	@Valid
	@ManyToOne(optional = false)
	private DiscussionForum		forum;

	/*
	 * @NotNull
	 *
	 * @Valid
	 *
	 * @ManyToOne(optional = false)
	 * private MessageThread thread;
	 */

}
