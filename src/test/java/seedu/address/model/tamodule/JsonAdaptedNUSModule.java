package seedu.address.model.tamodule;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Student;
import seedu.address.model.student.UniqueStudentList;
import seedu.address.model.tamodule.AcademicYear;
import seedu.address.model.tamodule.ModuleCode;
import seedu.address.model.tamodule.ModuleName;
import seedu.address.model.tamodule.TaModule;

/**
 * Jackson-friendly version of a module from NUSMods.
 */
class JsonAdaptedNUSModule {

    private final String moduleCode;
    private final String title;
    private final List<Integer> semesters;

    /**
     * Constructs a {@code JsonAdaptedTaModule} with the given module details.
     */
    @JsonCreator
    public JsonAdaptedNUSModule(@JsonProperty("moduleCode") String moduleCode,
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
