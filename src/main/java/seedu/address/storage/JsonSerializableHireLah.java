package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.HireLah;
import seedu.address.model.ReadOnlyHireLah;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.interview.Interview;
import seedu.address.model.position.Position;

/**
 * An Immutable HireLah that is serializable to JSON format.
 */
@JsonRootName(value = "HireLah")
class JsonSerializableHireLah {

    public static final String MESSAGE_DUPLICATE_APPLICANT = "Applicants list contains duplicate applicant(s).";
    public static final String MESSAGE_DUPLICATE_INTERVIEW = "Interviews list contains duplicate interview(s).";
    public static final String MESSAGE_DUPLICATE_POSITION = "Positions list contains duplicate position(s).";

    private final List<JsonAdaptedApplicant> applicants = new ArrayList<>();
    private final List<JsonAdaptedInterview> interviews = new ArrayList<>();
    private final List<JsonAdaptedPosition> positions = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableHireLah} with the given persons.
     */
    @JsonCreator
    public JsonSerializableHireLah(@JsonProperty("applicants") List<JsonAdaptedApplicant> applicants,
            @JsonProperty("interviews") List<JsonAdaptedInterview> interviews,
            @JsonProperty("positions") List<JsonAdaptedPosition> positions) {
        this.applicants.addAll(applicants);
        this.interviews.addAll(interviews);
        this.positions.addAll(positions);
    }

    /**
     * Converts a given {@code ReadOnlyHireLah} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableHireLah}.
     */
    public JsonSerializableHireLah(ReadOnlyHireLah source) {
        applicants.addAll(source.getApplicantList().stream().map(JsonAdaptedApplicant::new)
                .collect(Collectors.toList()));
        interviews.addAll(source.getInterviewList().stream().map(JsonAdaptedInterview::new)
                .collect(Collectors.toList()));
        positions.addAll(source.getPositionList().stream().map(JsonAdaptedPosition::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code HireLah} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public HireLah toModelType() throws IllegalValueException {
        HireLah hireLah = new HireLah();
        for (JsonAdaptedApplicant jsonAdaptedApplicant : applicants) {
            Applicant applicant = jsonAdaptedApplicant.toModelType();
            if (hireLah.hasApplicant(applicant)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_APPLICANT);
            }
            hireLah.addApplicant(applicant);
        }

        for (JsonAdaptedPosition jsonAdaptedPosition : positions) {
            Position position = jsonAdaptedPosition.toModelType();
            if (hireLah.hasPosition(position)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_POSITION);
            }
            hireLah.addPosition(position);
        }

        for (JsonAdaptedInterview jsonAdaptedInterview : interviews) {
            Interview interview = jsonAdaptedInterview.toModelType();
            Applicant interviewApplicant = interview.getApplicant();
            Position interviewPosition = interview.getPosition();

            if (hireLah.hasApplicant(interviewApplicant)) {
                interview.setApplicant(hireLah.getApplicantUsingStorage(interviewApplicant));
            }

            if (hireLah.hasPosition(interviewPosition)) {
                interview.setPosition(hireLah.getPositionUsingStorage(interviewPosition));
            }

            if (hireLah.hasInterview(interview)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_INTERVIEW);
            }
            hireLah.addInterview(interview);
        }

        return hireLah;
    }

}
