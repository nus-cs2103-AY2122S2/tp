package seedu.address.model.tamodule;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Jackson-friendly version of a module from NUSMods.
 */
class JsonAdaptedNusModule {

    private final String moduleCode;
    private final String title;
    private final List<Integer> semesters;

    /**
     * Constructs a {@code JsonAdaptedTaModule} with the given module details.
     */
    @JsonCreator
    public JsonAdaptedNusModule(@JsonProperty("moduleCode") String moduleCode,
                               @JsonProperty("title") String title,
                               @JsonProperty("semesters") List<Integer> semesters) {
        this.moduleCode = moduleCode;
        this.title = title;
        this.semesters = semesters;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getTitle() {
        return title;
    }

    public List<Integer> getSemesters() {
        return semesters;
    }
}
