package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Mod;

/**
 * Jackson-friendly version of {@link Mod}.
 */
class JsonAdaptedModule {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Module's %s field is missing!";

    private final String module;
    private final String defaultGroup;

    /**
     * Constructs a {@code JsonAdaptedModule} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedModule(@JsonProperty("module") String module,
                           @JsonProperty("defaultGroup") String defaultGroup) {
        this.module = module;
        this.defaultGroup = defaultGroup;
    }

    /**
     * Converts a given {@code Mod} into this class for Jackson use.
     */
    public JsonAdaptedModule(Mod source) {
        this.module = source.getMod();
        this.defaultGroup = source.getDefaultGroup();
    }

    /**
     * Converts this Jackson-friendly adapted mod object into the model's {@code Mod} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted mod.
     */
    public Mod toModelType() throws IllegalValueException {

        if (module == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Mod.class.getSimpleName()));
        }
        if (!Mod.isValidMod(module)) {
            throw new IllegalValueException(Mod.MESSAGE_CONSTRAINTS);
        }
        if (defaultGroup == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Mod.class.getSimpleName()));
        }
        if (!Mod.isValidDefaultGroup(defaultGroup)) {
            throw new IllegalValueException(Mod.MESSAGE_CONSTRAINTS);
        }

        return new Mod(module, defaultGroup);

    }

}

