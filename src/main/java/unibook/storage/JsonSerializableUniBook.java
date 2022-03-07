package unibook.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import unibook.commons.exceptions.IllegalValueException;
import unibook.model.ReadOnlyUniBook;
import unibook.model.UniBook;
import unibook.model.person.Person;

/**
 * An Immutable UniBook that is serializable to JSON format.
 */
@JsonRootName(value = "unibook")
class JsonSerializableUniBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableUniBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableUniBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyUniBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableUniBook}.
     */
    public JsonSerializableUniBook(ReadOnlyUniBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this UniBook into the model's {@code UniBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public UniBook toModelType() throws IllegalValueException {
        UniBook uniBook = new UniBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (uniBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            uniBook.addPerson(person);
        }
        return uniBook;
    }

}
