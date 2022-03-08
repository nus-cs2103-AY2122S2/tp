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
import unibook.model.module.Module;
import unibook.model.person.Person;

/**
 * An Immutable UniBook that is serializable to JSON format.
 */
@JsonRootName(value = "unibook")
class JsonSerializableUniBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_MODULE = "Module list contains duplicate module(s).";
    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedModule> modules = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableUniBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableUniBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                   @JsonProperty("modules") List<JsonAdaptedModule> modules) {
        this.persons.addAll(persons);
        this.modules.addAll(modules);
    }

    /**
     * Converts a given {@code ReadOnlyUniBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableUniBook}.
     */
    public JsonSerializableUniBook(ReadOnlyUniBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        modules.addAll(source.getModuleList().stream().map(JsonAdaptedModule::new).collect(Collectors.toList()));
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

        for (JsonAdaptedModule jsonAdaptedModule : modules) {
            Module module = jsonAdaptedModule.toModelType();
            if (uniBook.hasModule(module)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MODULE);
            }
            uniBook.addModule(module);
        }
        return uniBook;
    }

}
