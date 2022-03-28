package manageezpz.model.person;

import java.util.List;
import java.util.function.Predicate;

import manageezpz.commons.util.StringUtil;

/**
 * Checks if the options are valid for finding tasks.
 */
public class PersonMultiplePredicate implements Predicate<Person> {
    private final List<String> name;
    private final String phone;
    private final String email;

    /**
     * The constructor for the multipredicate to search for employees with the states options.
     * @param name Name of the employee
     * @param phone Phone number of the employee
     * @param email Email of the employee.
     */
    public PersonMultiplePredicate(List<String> name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test(Person person) {
        boolean hasName = name != null ? checkIfNameExists(person) : true;
        boolean hasPhone = phone != null ? checkIfPhoneExists(person) : true;
        boolean hasEmail = email != null ? checkIfEmailExists(person) : true;

        return  hasName && hasPhone && hasEmail;
    }

    private boolean checkIfNameExists(Person person) {
        return name.stream().anyMatch(name -> StringUtil.containsWordIgnoreCase(person.getName().fullName, name));
    }

    private boolean checkIfPhoneExists(Person person) {
        Phone phone = new Phone(this.phone);
        return person.getPhone().equals(phone);
    }

    private boolean checkIfEmailExists(Person person) {
        return person.getEmail().equals(email);
    }
}
