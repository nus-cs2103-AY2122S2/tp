package unibook.storage.adaptedmodeltypes;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import unibook.commons.exceptions.IllegalValueException;
import unibook.model.UniBook;
import unibook.model.person.Office;
import unibook.model.person.Person;
import unibook.model.person.Professor;

public class JsonAdaptedProfessor extends JsonAdaptedPerson {
    private final String office;

    /**
     * Constructs a {@code JsonAdaptedProfessor} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedProfessor(@JsonProperty("name") String name,
                                @JsonProperty("phone") String phone,
                                @JsonProperty("email") String email,
                                @JsonProperty("tagged") Set<JsonAdaptedTag> tagged,
                                @JsonProperty("modules") Set<JsonAdaptedModuleCode> modules,
                                @JsonProperty("office") String office) {
        super(name, phone, email, tagged, modules);
        this.office = office;
    }

    /**
     * Converts a given {@code Professor} into this class for Jackson use.
     */
    public JsonAdaptedProfessor(Professor source) {
        super(source);
        office = source.getOffice().value;
    }

    /**
     * Converts this Jackson-friendly adapted professor object into the model's {@code Professor} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Professor toModelType(UniBook uniBook) throws IllegalValueException {
        Person person = super.toModelType(uniBook);

        final Office officeObj = new Office(office);

        return new Professor(person, officeObj);
    }

}
