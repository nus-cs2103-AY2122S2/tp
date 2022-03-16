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
class JsonSerializableInterviewList {

    public static final String MESSAGE_DUPLICATE_INTERVIEW = "Interviews list contains duplicate interview(s).";

    private final List<JsonAdaptedInterview> interviews = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableInterviewList} with the given interviews.
     */
    @JsonCreator
    public JsonSerializableInterviewList(@JsonProperty("interviews") List<JsonAdaptedInterview> interviews) {
        this.interviews.addAll(interviews);
    }

    /**
     * Converts a given {@code ReadOnlyInterviewSchedule} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableInterviewList}.
     */
    public JsonSerializableInterviewList(ReadOnlyInterviewSchedule source) {
        interviews.addAll(source.getInterviewList().stream().map(JsonAdaptedInterview::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code InterviewSchedule} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public InterviewSchedule toModelType() throws IllegalValueException {
        InterviewSchedule InterviewSchedule = new InterviewSchedule();
        for (JsonAdaptedInterview jsonAdaptedInterview : interviews) {
            Interview interview = jsonAdaptedInterview.toModelType();
            if (InterviewSchedule.hasInterview(interview)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_INTERVIEW);
            }
            InterviewSchedule.addInterview(interview);
        }
        return InterviewSchedule;
    }

}
