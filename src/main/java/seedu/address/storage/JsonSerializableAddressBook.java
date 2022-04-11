package seedu.address.storage;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javafx.collections.transformation.FilteredList;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.person.Person;
import seedu.address.model.tutorial.Tutorial;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_ASSESSMENT =
            "Assessments list contains duplicate assessment(s).";
    public static final String MESSAGE_DUPLICATE_TUTORIAL = "Tutorials list contains duplicate tutorial(s).";


    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedAssessment> assessments = new ArrayList<>();
    private final List<JsonAdaptedTutorial> tutorials = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons, tutorials and assessments.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                       @JsonProperty("tutorials") List<JsonAdaptedTutorial> tutorials,
                                       @JsonProperty("assessments") List<JsonAdaptedAssessment> assessments) {
        this.persons.addAll(persons);
        this.assessments.addAll(assessments);
        this.tutorials.addAll(tutorials);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        tutorials.addAll(source.getTutorialList().stream().map(JsonAdaptedTutorial::new).collect(Collectors.toList()));
        assessments.addAll(source.getAssessmentList().stream()
                .map(JsonAdaptedAssessment::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
            addressBook.addLastShownItem(person);
        }

        for (JsonAdaptedAssessment jsonAdaptedAssessment : assessments) {
            Assessment assessment = jsonAdaptedAssessment.toModelType();
            if (addressBook.hasAssessment(assessment)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ASSESSMENT);
            }
            addressBook.addAssessment(assessment);
        }

        for (JsonAdaptedTutorial jsonAdaptedTutorial : tutorials) {
            Tutorial tutorial = jsonAdaptedTutorial.toModelType(addressBook.getUniqueAssessmentList());
            if (addressBook.hasTutorial(tutorial)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TUTORIAL);
            }
            addressBook.addTutorial(tutorial);
            tutorial.setStudentsList(new FilteredList<>(addressBook.getPersonList(),
                    PREDICATE_SHOW_ALL_STUDENTS));
        }

        return addressBook;
    }

}
