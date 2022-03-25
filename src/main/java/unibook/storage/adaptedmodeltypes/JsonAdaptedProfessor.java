package unibook.storage.adaptedmodeltypes;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import unibook.commons.exceptions.IllegalValueException;
import unibook.model.UniBook;
import unibook.model.person.Office;
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
     * Returns the {@code Office} object to use for the converted model.
     * Performs checks on the validity of the field in the Json before returning.
     *
     * @throws IllegalValueException if email string being parsed is null or invalid.
     */
    public Office getModelOffice() throws IllegalValueException {
        Office modelOffice;
        if (office == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Office.class.getSimpleName()));
        } else if (office.isEmpty()) {
            modelOffice = new Office();
        } else if (!Office.isValidOffice(office)) {
            throw new IllegalValueException(Office.MESSAGE_CONSTRAINTS);
        } else {
            modelOffice = new Office(office);
        }
        return modelOffice;
    }

    /**
     * Converts this Jackson-friendly adapted professor object into the model's {@code Professor} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    @Override
    public Professor toModelType(UniBook uniBook) throws IllegalValueException {
        return new Professor(getModelName(), getModelPhone(), getModelEmail(),
                getModelTags(), getModelOffice(), getModelModules(uniBook));
    }

}
