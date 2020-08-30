
package acme.components;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;

import acme.datatypes.Email;
import acme.framework.helpers.MessageHelper;
import acme.framework.helpers.StringHelper;

public class EmailFormatter implements Formatter<Email> {

	@Override
	public String print(final Email object, final Locale locale) {
		assert object != null;
		assert locale != null;

		String result;

		String contact;
		contact = object.getDisplayName() == null ? String.format("%s", object.getEmail()) : String.format("%s <%s>", object.getDisplayName(), object.getEmail());
		result = String.format("%s", contact);

		return result;
	}

	@Override
	public Email parse(final String text, final Locale locale) throws ParseException {
		assert !StringHelper.isBlank(text);
		assert locale != null;

		Email result;
		String displayNameRegex, emailRegex, contactRegex;
		Pattern pattern;
		Matcher matcher;
		String errorMessage;
		String displayName, email;
		Boolean beacon;

		displayNameRegex = "[a-zA-ZÀ-ÿ-.,\\u00f1\\u00d1 ]*";
		emailRegex = "[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,4})";

		if (!(text.contains("<") || text.contains(">"))) {
			contactRegex = String.format("^(?<E>%1$s)$", emailRegex);
			beacon = false;
		} else {
			contactRegex = String.format("^(?<DN>%1$s) \\u003c(?<E>%2$s)\\u003e$", displayNameRegex, emailRegex);
			beacon = true;
		}

		pattern = Pattern.compile(contactRegex, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
		matcher = pattern.matcher(text);

		if (!matcher.find()) {
			errorMessage = MessageHelper.getMessage("default.error.email", null, "default.error.email", locale);
			throw new ParseException(0, errorMessage);
		} else {
			if (beacon) {
				displayName = matcher.group("DN");
			} else {
				displayName = null;
			}
			email = matcher.group("E");

			result = new Email();
			result.setDisplayName(displayName);
			result.setEmail(email);
		}

		return result;

	}

}
