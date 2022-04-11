package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.assessment.AssessmentName;
import seedu.address.model.assessment.FullMark;
import seedu.address.model.assessment.Weightage;

public class JsonAdaptedAssessment {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Assessment's %s field is missing!";

    private final String name;
    private final String weightage;
    private final String fullMark;

    /**
     * Constructs a {@code JsonAdaptedAssessment} with the given assessment details.
     */
    @JsonCreator
    public JsonAdaptedAssessment(@JsonProperty("name") String name, @JsonProperty("weightage") String weightage,
                                 @JsonProperty("fullMark") String fullMark) {
        this.name = name;
        this.weightage = weightage;
        this.fullMark = fullMark;
    }

    /**
     * Converts a given {@code Assessment} into this class for Jackson use.
     */
    public JsonAdaptedAssessment(Assessment source) {
        name = source.getAssessmentName().name;
        weightage = String.valueOf(source.getWeightage().weightage);
        fullMark = String.valueOf(source.getFullMark().fullMark);
    }

    /**
     * Converts this Jackson-friendly adapted assessment object into the model's {@code Assessment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted assessment.
     */
    public Assessment toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    AssessmentName.class.getSimpleName()));
        }
        if (!AssessmentName.isValidAssessmentName(name)) {
            throw new IllegalValueException(AssessmentName.MESSAGE_CONSTRAINTS);
        }
        final AssessmentName modelAssessmentName = new AssessmentName(name);

        if (weightage == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Weightage.class.getSimpleName()));
        }
        if (!Weightage.isValidWeightage(weightage)) {
            throw new IllegalValueException(Weightage.MESSAGE_CONSTRAINTS);
        }
        final Weightage modelWeightage = new Weightage(weightage);

        if (fullMark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    FullMark.class.getSimpleName()));
        }
        if (!FullMark.isValidFullMark(fullMark)) {
            throw new IllegalValueException(FullMark.MESSAGE_CONSTRAINTS);
        }
        final FullMark modelFullMark = new FullMark(fullMark);

        return new Assessment(modelAssessmentName, modelWeightage, modelFullMark);
    }
}
