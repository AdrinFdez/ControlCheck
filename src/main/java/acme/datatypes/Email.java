
package acme.datatypes;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import acme.framework.datatypes.DomainDatatype;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Email extends DomainDatatype {

	private static final long	serialVersionUID	= 1L;

	String						displayName;

	@NotBlank
	@Pattern(regexp = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$")
	String						email;


	@Override
	public String toString() {
		StringBuilder result;

		result = new StringBuilder();
		result.append("<<");
		if (this.displayName == null) {
			result.append(this.email);
		} else {
			result.append(this.displayName);
			result.append(" <");
			result.append(this.email);
			result.append(">");
		}
		result.append(">>");
		return result.toString();
	}

}
