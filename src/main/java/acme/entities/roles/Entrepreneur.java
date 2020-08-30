/*
 * Entrepreneur.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.entities.roles;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Entrepreneur extends UserRole {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	@Length(max = 255)
	@NotBlank
	private String				name;
	@Length(max = 255)
	@NotBlank
	private String				sector;
	@Length(max = 255)
	@NotBlank
	private String				qualification;
	@Length(max = 255)
	@NotBlank
	private String				skill;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
