package seedu.address.model.person.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.person.Email;
import seedu.address.model.person.Person;

/**
 * Tests if the person email matches the specified domain name.
 * A {@link Person} passes the check if his domain (e.g. @gmail) matches the
 * user argument or if his entire domain name (e.g. @gmail.com) matches the user argument.
 */
public class DomainMatchPredicate implements Predicate<Person> {
    private final List<String> emailArg;

    /**
     * Constructor for DomainMatchPredicate.
     *
     * @param emailArg Email argument obtained from the user.
     */
    public DomainMatchPredicate(List<String> emailArg) {
        this.emailArg = emailArg;
    }

    /**
     * Tests if a person's email domain is identical to the domain (e.g. @gmail) or the entire domain name (e.g. @gmail
     * .com) of the user argument. This test is case-insensitive.
     *
     * @param person Person to be checked against.
     * @return True if domain name is identical to the email argument, false otherwise.
     */
    @Override
    public boolean test(Person person) {
        String domain = retrieveDomain(person.getEmail()).toLowerCase();
        String entireDomainName = retrieveEntireDomainName(person.getEmail()).toLowerCase();
        return emailArg.stream().anyMatch(email -> domain.equals(email.toLowerCase())
                || entireDomainName.equals(email.toLowerCase()));
    }

    /**
     * Retrieves the domain of the person. E.g. "@gmail".
     *
     * @param email Email to retrieve the domain from.
     * @return String representation of the domain.
     */
    private String retrieveDomain(Email email) {
        String emailString = email.toString();
        int startIndex = getStartIndex(emailString);
        int endIndex = getEndIndex(emailString, startIndex);
        return emailString.substring(startIndex, endIndex);
    }

    /**
     * Retrieves the entire domain name of the person including the domain name after ".". For e.g. "@gmail.com".
     *
     * @param email Email to retrieve the entire domain name from.
     * @return String representation of the entire domain name.
     */
    private String retrieveEntireDomainName(Email email) {
        String emailString = email.toString();
        int startIndex = getStartIndex(emailString);
        return emailString.substring(startIndex);
    }

    /**
     * Gets the ending index of the entire domain name.
     *
     * @param emailString String to retrieve the ending index from.
     * @param startIndex Starting index of the domain name.
     * @return Ending index of the entire domain name.
     */
    private int getEndIndex(String emailString, int startIndex) {
        int endIndex = startIndex + 1;
        while (endIndex < emailString.length() && emailString.charAt(endIndex) != '.') {
            endIndex++;
        }
        return endIndex;
    }

    /**
     * Gets the starting index of the domain name. This index is the index at which the '@' character is found.
     *
     * @param emailString String to retrieve the starting index from.
     * @return Starting index of the domain name.
     */
    private int getStartIndex(String emailString) {
        int startIndex = 0;
        while (startIndex < emailString.length() && emailString.charAt(startIndex) != '@') {
            startIndex++;
        }
        return startIndex;
    }
}
