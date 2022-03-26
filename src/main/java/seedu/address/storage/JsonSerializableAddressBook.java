package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.lab.Lab;
import seedu.address.model.student.Student;

/**
 * An Immutable TAddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_STUDENT = "Students list contains duplicate student(s).";
    public static final String MESSAGE_DUPLICATE_LAB = "Students list contains duplicate lab(s).";

    private final List<JsonAdaptedLab> masterList = new ArrayList<>();
    private final List<JsonAdaptedStudent> students = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("masterList") List<JsonAdaptedLab> masterList,
                                       @JsonProperty("students") List<JsonAdaptedStudent> students) {
        this.students.addAll(students);
        this.masterList.addAll(masterList);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
        masterList.addAll(source.getMasterLabList().getMasterList().stream()
                .map(JsonAdaptedLab::new).collect(Collectors.toList()));
    }

    /**
     * Converts this TAddress book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            if (addressBook.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            addressBook.addStudent(student);
        }

        for (JsonAdaptedLab jsonAdaptedLab : masterList) {
            Lab lab = jsonAdaptedLab.toModelType();
            if (addressBook.hasLab(lab)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_LAB);
            }
            addressBook.getMasterLabList().add(lab.of("UNSUBMITTED"));
        }
        return addressBook;
    }

}
