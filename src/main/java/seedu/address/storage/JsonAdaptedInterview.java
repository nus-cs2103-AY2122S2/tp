package seedu.address.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.interview.Interview;
import seedu.address.model.interview.Status;
import seedu.address.model.position.Position;

public class JsonAdaptedInterview {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Interview's %s field is missing!";

    private final JsonAdaptedApplicant applicant;
    private final LocalDateTime date;
    private final JsonAdaptedPosition position;
    private String status;

    /**
     * Constructs a {@code JsonAdaptedInterview} with the given interview details.
     */
    @JsonCreator
    public JsonAdaptedInterview(@JsonProperty("applicant") JsonAdaptedApplicant applicant,
            @JsonProperty("date") LocalDateTime date, @JsonProperty("position") JsonAdaptedPosition position,
            @JsonProperty("status") String status) {
        this.applicant = applicant;
        this.date = date;
        this.position = position;
        this.status = status;
    }

    /**
     * Converts a given {@code Interview} into this class for Jackson use.
     */
    public JsonAdaptedInterview(Interview source) {
        applicant = new JsonAdaptedApplicant(source.getApplicant());
        date = source.getDate();
        position = new JsonAdaptedPosition(source.getPosition());
        status = source.getStatus().toString();
    }

    /**
     * Converts this Jackson-friendly adapted interview object into the model's {@code Interview} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted interview.
     */
    public Interview toModelType() throws IllegalValueException {
        if (applicant == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    JsonAdaptedApplicant.class.getSimpleName()));
        }
        final Applicant modelApplicant = applicant.toModelType();

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LocalDateTime.class.getSimpleName()));
        }
        final LocalDateTime modelDate = date;

        if (position == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    JsonAdaptedPosition.class.getSimpleName()));
        }
        final Position modelPosition = position.toModelType();

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Status.class.getSimpleName()));
        }

        final Status status = new Status(this.status);

        return new Interview(modelApplicant, modelDate, modelPosition, status);
    }
}
