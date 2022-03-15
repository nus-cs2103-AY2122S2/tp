package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.InterviewList;
import seedu.address.model.ReadOnlyInterviewList;
import seedu.address.model.interview.Interview;

/**
 * An Immutable InterviewList that is serializable to JSON format.
 */
@JsonRootName(value = "InterviewList")
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
     * Converts a given {@code ReadOnlyInterviewList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableInterviewList}.
     */
    public JsonSerializableInterviewList(ReadOnlyInterviewList source) {
        interviews.addAll(source.getInterviewList().stream().map(JsonAdaptedInterview::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code InterviewList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public InterviewList toModelType() throws IllegalValueException {
        InterviewList InterviewList = new InterviewList();
        for (JsonAdaptedInterview jsonAdaptedInterview : interviews) {
            Interview interview = jsonAdaptedInterview.toModelType();
            if (InterviewList.hasInterview(interview)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_INTERVIEW);
            }
            InterviewList.addInterview(interview);
        }
        return InterviewList;
    }

}
