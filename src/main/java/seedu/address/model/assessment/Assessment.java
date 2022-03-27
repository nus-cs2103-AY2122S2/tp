package seedu.address.model.assessment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.EntityType;
import seedu.address.model.student.Student;
import seedu.address.model.tamodule.TaModule;

/**
 * Represents an Assessment in the TAssist.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Assessment implements Entity {

    // Identity fields
    private final AssessmentName assessmentName;
    private final TaModule module;
    private final Optional<SimpleName> simpleName;
    private final ObservableMap<Student, Grade> attempts;

    /**
     * Minimum fields that must be present and not null.
     */
    public Assessment(AssessmentName assessmentName, TaModule module) {
        this(assessmentName, module, Optional.empty());
    }

    /**
     * Every field must be present and not null.
     * Used to initialize a new Assessment with no attempts.
     */
    public Assessment(AssessmentName assessmentName, TaModule module, Optional<SimpleName> simpleName) {
        this(assessmentName, module, simpleName, FXCollections.observableHashMap());
    }

    /**
     * Every field must be present and not null.
     * Used to initialize an Assessment from storage file.
     */
    public Assessment(AssessmentName assessmentName, TaModule module, Optional<SimpleName> simpleName,
                      Map<Student, Grade> attempts) {
        requireAllNonNull(assessmentName, module);
        this.assessmentName = assessmentName;
        this.simpleName = simpleName;
        this.module = module;
        this.attempts = FXCollections.observableMap(attempts);
    }

    public AssessmentName getAssessmentName() {
        return assessmentName;
    }

    public Optional<SimpleName> getSimpleName() {
        return simpleName;
    }

    public TaModule getTaModule() {
        return module;
    }

    public Map<Student, Grade> getAttempts() {
        return FXCollections.unmodifiableObservableMap(attempts);
    }

    public void addAttempt(Student student, Grade grade) {
        attempts.put(student, grade);
    }

    public Optional<Grade> getAttemptOfStudent(Student student) {
        return Optional.ofNullable(attempts.get(student));
    }

    /**
     * Returns true if both assessments have the same assessmentName and module.
     * This defines a weaker notion of equality between two assessments.
     */
    public boolean isSameAssessment(Assessment otherAssessment) {
        if (otherAssessment == this) {
            return true;
        }

        return otherAssessment != null
                && otherAssessment.getAssessmentName().equals(getAssessmentName())
                && otherAssessment.getTaModule().equals(getTaModule());
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.ASSESSMENT;
    }

    /**
     * Returns true if both modules have the same identity and data fields.
     * This defines a stronger notion of equality between two modules.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Assessment)) {
            return false;
        }

        Assessment otherAssessment = (Assessment) other;
        return otherAssessment.getAssessmentName().equals(getAssessmentName())
                && otherAssessment.getTaModule().equals(getTaModule())
                && otherAssessment.getSimpleName().equals(getSimpleName())
                && otherAssessment.getAttempts().equals(getAttempts());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(assessmentName, simpleName, module);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Assessment Name: ")
                .append(getAssessmentName())
                .append("; Module: ")
                .append(getTaModule().toUniqueRepresentation());
        getSimpleName().ifPresent(simpleName ->
                builder.append("; Simple Name: ")
                .append(simpleName));
        return builder.toString();
    }

}
