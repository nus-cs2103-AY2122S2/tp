package unibook.storage.adaptedmodeltypes;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import unibook.commons.exceptions.IllegalValueException;
import unibook.model.UniBook;
import unibook.model.person.Person;
import unibook.model.person.Student;

public class JsonAdaptedStudent extends JsonAdaptedPerson {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Professor's %s field is missing!";

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name,
                              @JsonProperty("phone") String phone,
                              @JsonProperty("email") String email,
                              @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                              @JsonProperty("modules") List<JsonAdaptedModuleCode> modules) {
        super(name, phone, email, tagged, modules);
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        super(source);
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code Student} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public Student toModelType(UniBook uniBook) throws IllegalValueException {
        Person person = super.toModelType(uniBook);
        return new Student(person);
    }
}
