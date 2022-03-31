package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.InterviewSchedule;
import seedu.address.model.ReadOnlyInterviewSchedule;
import seedu.address.model.interview.Interview;

/**
 * An Immutable InterviewSchedule that is serializable to JSON format.
 */
@JsonRootName(value = "InterviewSchedule")
class JsonSerializableInterviewSchedule {

    public static final String MESSAGE_DUPLICATE_INTERVIEW = "Interviews list contains duplicate interview(s).";
    public static final String MESSAGE_CONFLICTING_INTERVIEW =
            "Interviews list contains conflicting interview timeslots";
    private final List<JsonAdaptedInterview> interviews = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableInterviewSchedule} with the given interviews.
     */
    @JsonCreator
    public JsonSerializableInterviewSchedule(@JsonProperty("interviews") List<JsonAdaptedInterview> interviews) {
        this.interviews.addAll(interviews);
    }

    /**
     * Converts a given {@code ReadOnlyInterviewSchedule} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableInterviewSchedule}.
     */
    public JsonSerializableInterviewSchedule(ReadOnlyInterviewSchedule source) {
        interviews.addAll(source.getInterviewList().stream().map(JsonAdaptedInterview::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code InterviewSchedule} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public InterviewSchedule toModelType() throws IllegalValueException {
        InterviewSchedule interviewSchedule = new InterviewSchedule();
        for (JsonAdaptedInterview jsonAdaptedInterview : interviews) {
            Interview interview = jsonAdaptedInterview.toModelType();
            if (interviewSchedule.hasCandidate(interview)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_INTERVIEW);
            }
            if (interviewSchedule.hasConflictingInterview(interview)) {
                throw new IllegalValueException(MESSAGE_CONFLICTING_INTERVIEW);
            }
            interviewSchedule.addInterview(interview);
        }
        return interviewSchedule;
    }

}
