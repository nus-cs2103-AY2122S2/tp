package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.assessment.AssessmentName;
import seedu.address.model.assessment.AssessmentResults;
import seedu.address.model.assessment.FullMark;

/**
 * Jackson-friendly version of {@link AssessmentResults}.
 */
public class JsonAdaptedAssessmentResults {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "AssessmentResults' %s field is missing!";

    private final String assessmentName;
    private final List<JsonAdaptedStudentResult> results = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedAssessmentResults} from the given details.
     */
    @JsonCreator
    public JsonAdaptedAssessmentResults(@JsonProperty("assessmentName") String assessmentName,
                                        @JsonProperty("results") List<JsonAdaptedStudentResult> results) {
        this.assessmentName = assessmentName;
        if (results != null) {
            this.results.addAll(results);
        }
    }

    /**
     * Converts a given {@code AssessmentResults} into a {@code JsonAdaptedAssessmentResults} for Jackson use.
     */
    public JsonAdaptedAssessmentResults(AssessmentResults source) {
        assessmentName = source.getAssessmentName().name;
        results.addAll(source.asUnmodifiableStudentResultsList()
                .stream()
                .map(JsonAdaptedStudentResult::new)
                .collect(Collectors.toList()));
    }

    public AssessmentName getAssessmentName() throws IllegalValueException {
        if (assessmentName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    AssessmentName.class.getSimpleName()));
        }
        if (!AssessmentName.isValidAssessmentName(assessmentName)) {
            throw new IllegalValueException(AssessmentName.MESSAGE_CONSTRAINTS);
        }
        return new AssessmentName(assessmentName);
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code AssessmentResults} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted assessment results.
     */
    public AssessmentResults toModelType(FullMark fullMark) throws IllegalValueException {
        final AssessmentName modelAssessmentName = getAssessmentName();

        final AssessmentResults modelAssessmentResults = new AssessmentResults(modelAssessmentName);
        for (JsonAdaptedStudentResult studentResult : results) {
            modelAssessmentResults.add(studentResult.toModelType(fullMark));
        }

        return modelAssessmentResults;
    }
}
