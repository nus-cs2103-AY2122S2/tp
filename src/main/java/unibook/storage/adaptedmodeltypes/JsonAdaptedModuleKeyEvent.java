package unibook.storage.adaptedmodeltypes;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import unibook.commons.exceptions.IllegalValueException;
import unibook.model.module.Module;
import unibook.model.module.ModuleKeyEvent;


/**
 * Jackson friendly version of ModuleKeyEvent.
 */
public class JsonAdaptedModuleKeyEvent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Group's %s field is missing!";

    private final String keyEventType;
    private final LocalDateTime keyEventTiming;

    /**
     * Creates a JsonAdaptedModuleKeyEvent using a keyEventType string and keyEventTiming.
     * @param keyEventType
     * @param keyEventTiming
     */
    @JsonCreator
    public JsonAdaptedModuleKeyEvent(@JsonProperty("keyEventType") String keyEventType,
                            @JsonProperty("keyEventTiming") LocalDateTime keyEventTiming) {
        this.keyEventType = keyEventType;
        this.keyEventTiming = keyEventTiming;
    }

    /**
     * Creates a JsonAdaptedModuleKeyEvent from a source ModuleKeyEvent.
     * @param source
     */
    public JsonAdaptedModuleKeyEvent(ModuleKeyEvent source) {
        this.keyEventType = source.getKeyEventType().toString();
        this.keyEventTiming = source.getKeyEventTiming();
    }

    /**
     * Converts a JsonAdaptedModuleKeyEvent to its respective model-compatible ModuleKeyEvent.
     * @param module
     * @return the module key event.
     * @throws IllegalValueException
     */
    public ModuleKeyEvent toModelType(Module module) throws IllegalValueException {
        if (keyEventType == null || keyEventTiming == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT));
        }

        return new ModuleKeyEvent(ModuleKeyEvent.KeyEventType.valueOf(keyEventType), keyEventTiming, module);
    }


}
