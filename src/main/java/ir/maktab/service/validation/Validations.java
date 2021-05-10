package ir.maktab.service.validation;

import ir.maktab.service.exceptions.checkes.InvalidEmailAddressException;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component
public class Validations {
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public boolean validateEmail(String email) throws InvalidEmailAddressException {
        return isDomainAndFormatValid(email) && validateByRegex("email", email);
    }

    private boolean isDomainAndFormatValid(String email) throws InvalidEmailAddressException {
        String[] validDomains = {"gmail.com", "yahoo.com", "outlook.com", "hotmail.com"};
        for (String validDomain : validDomains) {
            if (validDomain.equals(email.substring(email.indexOf("@") + 1))) {
                return true;
            }
        }
        throw new InvalidEmailAddressException("Domain is invalid.");
    }
    public static boolean validateByRegex(String wantToMatch, String input) {
        if (wantToMatch.equalsIgnoreCase("email")) {
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(input);
            if (matcher.find())
                return true;
            else
                throw new IllegalStateException("check input");
        }
        return false;
    }
}
