package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.InternApplyMemory;
import seedu.address.model.ReadOnlyInternApplyMemory;
import seedu.address.model.application.Application;

/**
 * An Immutable InternApply that is serializable to JSON format.
 */
@JsonRootName(value = "internapply")
class JsonSerializableInternApply {

    public static final String MESSAGE_DUPLICATE_APPLICATION = "Applications list contains duplicate application(s).";

    private final List<JsonAdaptedApplication> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableInternApply} with the given applications.
     */
    @JsonCreator
    public JsonSerializableInternApply(@JsonProperty("persons") List<JsonAdaptedApplication> applications) {
        this.persons.addAll(applications);
    }

    /**
     * Converts a given {@code ReadOnlyInternApplyMemory} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableInternApply}.
     */
    public JsonSerializableInternApply(ReadOnlyInternApplyMemory source) {
        persons.addAll(source.getApplicationList().stream().map(JsonAdaptedApplication::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this intern apply memory into the model's {@code InternApplyMemory} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public InternApplyMemory toModelType() throws IllegalValueException {
        InternApplyMemory internApplyMemory = new InternApplyMemory();
        for (JsonAdaptedApplication jsonAdaptedApplication : persons) {
            Application application = jsonAdaptedApplication.toModelType();
            if (internApplyMemory.hasApplication(application)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_APPLICATION);
            }
            internApplyMemory.addApplication(application);
        }
        return internApplyMemory;
    }

}
