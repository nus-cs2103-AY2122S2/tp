package seedu.address.testutil;

import java.util.Map;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.assessment.AssessmentName;
import seedu.address.model.assessment.Grade;
import seedu.address.model.assessment.SimpleName;
import seedu.address.model.student.Student;
import seedu.address.model.tamodule.TaModule;

/**
 * A utility class to help with building Assessment objects.
 */
public class AssessmentBuilder {

    public static final String DEFAULT_ASSESSMENT_NAME = "Class Participation";
    public static final TaModule DEFAULT_MODULE = TypicalModules.CS2103T_WITH_STUDENT;

    private AssessmentName assessmentName;
    private TaModule module;
    private Optional<SimpleName> simpleName;
    private ObservableMap<Student, Grade> attempts = FXCollections.observableHashMap();

    /**
     * Creates a {@code ModuleBuilder} with the default details.
     */
    public AssessmentBuilder() {
        assessmentName = new AssessmentName(DEFAULT_ASSESSMENT_NAME);
        simpleName = Optional.empty();
        module = DEFAULT_MODULE;
    }

    /**
     * Initializes the AssessmentBuilder with the data of {@code assessmentToCopy}.
     */
    public AssessmentBuilder(Assessment assessmentToCopy) {
        assessmentName = assessmentToCopy.getAssessmentName();
        simpleName = Optional.of(assessmentToCopy.getSimpleName());
        module = assessmentToCopy.getModule();
        attempts.putAll(assessmentToCopy.getAttempts());
    }

    /**
     * Sets the {@code AssessmentName} of the {@code Assessment} that we are building.
     */
    public AssessmentBuilder withAssessmentName(String assessmentName) {
        this.assessmentName = new AssessmentName(assessmentName);
        return this;
    }

    /**
     * Sets the {@code TaModule} of the {@code Assessment} that we are building.
     */
    public AssessmentBuilder withTaModule(TaModule module) {
        this.module = module;
        return this;
    }

    /**
     * Sets the {@code SimpleName} of the {@code Assessment} that we are building.
     */
    public AssessmentBuilder withSimpleName(String simpleName) {
        this.simpleName = Optional.of(new SimpleName(simpleName));
        return this;
    }

    /**
     * Sets the {@code studentList} of the {@code Module} that we are building.
     */
    public AssessmentBuilder withAttempts(Map<Student, Grade> attempts) {
        this.attempts.putAll(attempts);
        return this;
    }


    public Assessment build() {
        return new Assessment(assessmentName, module, simpleName, attempts);
    }

}
