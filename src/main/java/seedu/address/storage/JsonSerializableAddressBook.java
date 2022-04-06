package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.interview.Interview;
import seedu.address.model.position.Position;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "HireLah")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_APPLICANT = "Persons list contains duplicate applicant(s).";
    public static final String MESSAGE_DUPLICATE_INTERVIEW = "Interviews list contains duplicate interview(s).";
    public static final String MESSAGE_DUPLICATE_POSITION = "Positions list contains duplicate position(s).";

    private final List<JsonAdaptedApplicant> applicants = new ArrayList<>();
    private final List<JsonAdaptedInterview> interviews = new ArrayList<>();
    private final List<JsonAdaptedPosition> positions = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedApplicant> persons,
            @JsonProperty("interviews") List<JsonAdaptedInterview> interviews,
            @JsonProperty("positions") List<JsonAdaptedPosition> positions) {
        this.applicants.addAll(persons);
        this.interviews.addAll(interviews);
        this.positions.addAll(positions);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        applicants.addAll(source.getApplicantList().stream().map(JsonAdaptedApplicant::new).collect(Collectors.toList()));
        interviews.addAll(source.getInterviewList().stream().map(JsonAdaptedInterview::new)
                .collect(Collectors.toList()));
        positions.addAll(source.getPositionList().stream().map(JsonAdaptedPosition::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedApplicant jsonAdaptedApplicant : applicants) {
            Applicant applicant = jsonAdaptedApplicant.toModelType();
            if (addressBook.hasApplicant(applicant)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_APPLICANT);
            }
            addressBook.addApplicant(applicant);
        }

        for (JsonAdaptedPosition jsonAdaptedPosition : positions) {
            Position position = jsonAdaptedPosition.toModelType();
            if (addressBook.hasPosition(position)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_POSITION);
            }
            addressBook.addPosition(position);
        }

        for (JsonAdaptedInterview jsonAdaptedInterview : interviews) {
            Interview interview = jsonAdaptedInterview.toModelType();
            Applicant interviewApplicant = interview.getApplicant();
            Position interviewPosition = interview.getPosition();

            if (addressBook.hasApplicant(interviewApplicant)) {
                interview.setApplicant(addressBook.getApplicantUsingStorage(interviewApplicant));
            }

            if (addressBook.hasPosition(interviewPosition)) {
                interview.setPosition(addressBook.getPositionUsingStorage(interviewPosition));
            }

            if (addressBook.hasInterview(interview)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_INTERVIEW);
            }
            addressBook.addInterview(interview);
        }

        return addressBook;
    }

}
