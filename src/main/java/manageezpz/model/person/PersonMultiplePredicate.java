package manageezpz.model.person;

import java.util.List;
import java.util.function.Predicate;

import manageezpz.commons.util.StringUtil;

/**
 * Checks if the options are valid for finding tasks.
 */
public class PersonMultiplePredicate implements Predicate<Person> {
    private final List<String> names;
    private final String phone;
    private final String email;

    /**
     * The constructor for the multipredicate to search for employees with the stated options.
     * @param names Name of the employee
     * @param phone Phone number of the employee
     * @param email Email of the employee.
     */
    public PersonMultiplePredicate(List<String> names, String phone, String email) {
        this.names = names;
        this.phone = phone;
        this.email = email;

        boolean isAtLeastOneNotNull = (this.names != null) || (this.phone != null) || (this.email != null);
        assert isAtLeastOneNotNull : "At least one search option should be specified";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test(Person person) {
        // Checks if the specific search term is specified in the parameter, then check on the person provided.
        // Defaults to true if not specified.
        boolean hasName = names != null ? checkIfNameExists(person) : true;
        boolean hasPhone = phone != null ? checkIfPhoneExists(person) : true;
        boolean hasEmail = email != null ? checkIfEmailExists(person) : true;

        return hasName && hasPhone && hasEmail;
    }

    private boolean checkIfNameExists(Person person) {
        return names.stream().anyMatch(name -> StringUtil.containsWordIgnoreCase(person.getName().fullName, name));
    }

    private boolean checkIfPhoneExists(Person person) {
        Phone phone = new Phone(this.phone);
        return person.getPhone().equals(phone);
    }

    private boolean checkIfEmailExists(Person person) {
        Email email = new Email(this.email);
        return person.getEmail().equals(email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof PersonMultiplePredicate) {
            PersonMultiplePredicate otherPredicate = (PersonMultiplePredicate) other;
            boolean isNameEquals = checkIfOptionEqual(names, otherPredicate.names);
            boolean isPhoneEquals = checkIfOptionEqual(phone, otherPredicate.phone);
            boolean isEmailEquals = checkIfOptionEqual(email, otherPredicate.email);

            return isNameEquals && isPhoneEquals && isEmailEquals;
        } else {
            return false;
        }
    }

    private boolean checkIfOptionEqual(Object currentObj, Object otherObj) {
        if (otherObj != null) {
            return otherObj.equals(currentObj);
        } else {
            return currentObj == null;
        }
    }
}
