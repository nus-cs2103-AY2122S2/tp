package unibook.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import unibook.commons.exceptions.IllegalValueException;
import unibook.model.ReadOnlyUniBook;
import unibook.model.UniBook;
import unibook.model.module.Module;
import unibook.model.person.Person;
import unibook.model.person.Professor;
import unibook.model.person.Student;
import unibook.model.person.exceptions.PersonNoSubtypeException;
import unibook.storage.adaptedmodeltypes.JsonAdaptedModule;
import unibook.storage.adaptedmodeltypes.JsonAdaptedPerson;
import unibook.storage.adaptedmodeltypes.JsonAdaptedProfessor;
import unibook.storage.adaptedmodeltypes.JsonAdaptedStudent;

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
        for (Person p : source.getPersonList()) {
            if (p instanceof Student) {
                persons.add(new JsonAdaptedStudent((Student) p));
            } else if (p instanceof Professor) {
                persons.add(new JsonAdaptedProfessor((Professor) p));
            } else {
                //person should be either a student or a professor
               throw new PersonNoSubtypeException();
            }
        }
        //all JsonAdaptedGroups are instantiated within the instantiation of a JsonAdaptedModule
        modules.addAll(source.getModuleList().stream().map(JsonAdaptedModule::new).collect(Collectors.toList()));
    }

    /**
     * Converts this UniBook into the model's {@code UniBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public UniBook toModelType() throws IllegalValueException, PersonNoSubtypeException {
        UniBook uniBook = new UniBook();

        //add all stored modules and groups (part of module) to unibook first
        for (JsonAdaptedModule jsonAdaptedModule : modules) {
            Module module = jsonAdaptedModule.toModelType();
            if (uniBook.hasModule(module)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MODULE);
            }
            uniBook.addModule(module);
        }

        //add all stored people to unibook
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType(uniBook);
            if (uniBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            uniBook.addPerson(person);
        }

        //add all stored people to their associated modules in unibook
        //add all students to their groups
        for (Person p : uniBook.getPersonList()) {
            uniBook.addPersonToAllTheirModules(p);
            if (p instanceof Student) {
                uniBook.addStudentToAllTheirGroups((Student) p);
            }
        }

        return uniBook;
    }

}
