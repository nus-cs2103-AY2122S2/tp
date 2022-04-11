package seedu.address.testutil;

import seedu.address.model.person.Person;
import seedu.address.model.person.PersonAmount;
import seedu.address.model.person.PersonName;

public class PersonBuilder {

    public static final String DEFAULT_PERSON_AMOUNT = "15";
    public static final String DEFAULT_PERSON_NAME = "Alex";

    private PersonName personName;
    private PersonAmount personAmount;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        personName = new PersonName(DEFAULT_PERSON_NAME);
        personAmount = new PersonAmount(DEFAULT_PERSON_AMOUNT);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        personName = personToCopy.getPersonName();
        personAmount = personToCopy.getPersonAmount();
    }

    /**
     * Sets the {@code PersonName} of the {@code Person} that we are building.
     */
    public PersonBuilder withPersonName(String personName) {
        this.personName = new PersonName(personName);
        return this;
    }

    /**
     * Sets the {@code PersonAmount} of the {@code Person} that we are building.
     */
    public PersonBuilder withPersonAmount(String personAmount) {
        this.personAmount = new PersonAmount(personAmount);
        return this;
    }



    public Person build() {
        return new Person(personName, personAmount);
    }

}

