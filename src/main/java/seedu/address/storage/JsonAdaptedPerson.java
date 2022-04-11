package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonAmount;
import seedu.address.model.person.PersonName;

public class JsonAdaptedPerson {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String personName;
    private final String personAmount;

    /**
     * Constructs a {@code JsonAdaptedExpense} with the given expense details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("personName") String personName,
                              @JsonProperty("personAmount") String personAmount) {
        this.personName = personName;
        this.personAmount = personAmount;
    }

    /**
     * Converts a given {@code Expense} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        personName = source.getPersonName().personName;
        personAmount = source.getPersonAmount().toString();
    }

    /**
     * Converts this Jackson-friendly adapted expense object into the model's {@code Expense} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted expense.
     */
    public Person toModelType() throws IllegalValueException {
        if (personName == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, PersonName.class.getSimpleName()));
        }
        if (!PersonName.isValidPersonName(personName)) {
            throw new IllegalValueException(PersonName.MESSAGE_CONSTRAINTS);
        }
        final PersonName modelPersonName = new PersonName(personName);

        if (personAmount == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, PersonAmount.class.getSimpleName()));
        }
        if (!PersonAmount.isValidPersonAmount(personAmount)) {
            throw new IllegalValueException(PersonAmount.MESSAGE_CONSTRAINTS);
        }
        final PersonAmount modelPersonAmount = new PersonAmount(personAmount);

        return new Person(modelPersonName, modelPersonAmount);
    }

}
