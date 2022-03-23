package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.HustleBook;
import seedu.address.model.ReadOnlyHustleBook;
import seedu.address.model.person.Person;

/**
 * An Immutable HustleBook that is serializable to JSON format.
 */
@JsonRootName(value = "hustlebook")
class JsonSerializableHustleBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableHustleBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableHustleBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyHustleBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableHustleBook}.
     */
    public JsonSerializableHustleBook(ReadOnlyHustleBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this hustle book into the model's {@code HustleBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public HustleBook toModelType() throws IllegalValueException {
        HustleBook hustleBook = new HustleBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (hustleBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            hustleBook.addPerson(person);
        }
        return hustleBook;
    }

}
