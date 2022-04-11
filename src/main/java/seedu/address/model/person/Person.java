package seedu.address.model.person;

import java.util.Objects;

public class Person {
    private final PersonName personName;
    private final PersonAmount personAmount;

    /**
     * Constructor for the person class.
     * @param personName the name of the person.
     * @param personAmount the amount the person owes.
     */
    public Person(PersonName personName, PersonAmount personAmount) {
        this.personName = personName;
        this.personAmount = personAmount;
    }

    /**
     * Getter for the name of the person.
     * @return the name of the person
     */
    public PersonName getPersonName() {
        return this.personName;
    }

    /**
     * Getter for the amount the person owes.
     * @return the amount the person owes.
     */
    public PersonAmount getPersonAmount() {
        return this.personAmount;
    }

    /**
     * To compare the equivalence of two persons.
     * @param otherPerson the person whose equivalence is checked.
     * @return boolean result of the check.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getPersonName().equals(getPersonName())
                && otherPerson.getPersonAmount().equals(getPersonAmount());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getPersonName().equals(getPersonName())
                && otherPerson.getPersonAmount().equals(getPersonAmount());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(personName, personAmount);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getPersonName())
                .append(" Amount: $")
                .append(getPersonAmount());

        return builder.toString();
    }
}
