package seedu.address.storage;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.application.Address;
import seedu.address.model.application.Application;
import seedu.address.model.application.Details;
import seedu.address.model.application.Email;
import seedu.address.model.application.InterviewSlot;
import seedu.address.model.application.JobTitle;
import seedu.address.model.application.Name;
import seedu.address.model.application.Phone;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Application}.
 */
class JsonAdaptedApplication {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Application's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String interviewSlot;
    private final String details;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final String jobTitle;

    /**
     * Constructs a {@code JsonAdaptedApplication} with the given application details.
     */
    @JsonCreator

    public JsonAdaptedApplication(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                                  @JsonProperty("email") String email, @JsonProperty("address") String address,
                                  @JsonProperty("interviewSlot") String interviewSlot,
                                  @JsonProperty("details") String details,
                                  @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                                  @JsonProperty("jobTitle") String jobTitle) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.interviewSlot = interviewSlot;
        this.details = details;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.jobTitle = jobTitle;
    }

    /**
     * Converts a given {@code Application} into this class for Jackson use.
     */
    public JsonAdaptedApplication(Application source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        interviewSlot = source.getInterviewSlot().value.format(DateTimeFormatter
                .ofPattern(InterviewSlot.FORMAT_DATETIME_INPUT));
        details = source.getDetails().details;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        jobTitle = source.getJobTitle().value;
    }

    /**
     * Converts this JSON-friendly adapted application object into the model's {@code Application} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted application.
     */
    public Application toModelType() throws IllegalValueException {
        final List<Tag> applicationTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            applicationTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }

        final Address modelAddress = new Address(address);

        if (interviewSlot == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    InterviewSlot.class.getSimpleName()));
        }

        if (!InterviewSlot.isValidDateTime(interviewSlot)) {
            throw new IllegalValueException(InterviewSlot.MESSAGE_CONSTRAINTS);
        }

        final InterviewSlot modelInterviewSlot = InterviewSlot.isNotSet(interviewSlot)
                ? new InterviewSlot()
                : new InterviewSlot(interviewSlot);

        final Set<Tag> modelTags = new HashSet<>(applicationTags);
        if (jobTitle == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    JobTitle.class.getSimpleName()));
        }

        if (!JobTitle.isValidJobTitle(jobTitle)) {
            throw new IllegalValueException(JobTitle.MESSAGE_CONSTRAINTS);
        }
        final JobTitle modelJobTitle = new JobTitle(jobTitle);

        if (details == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Details.class.getSimpleName()));
        }

        final Details modelDetails = Details.isNotSet(details)
            ? new Details()
            : new Details(details);

        return new Application(modelName, modelJobTitle, modelPhone, modelEmail, modelAddress, modelInterviewSlot,
            modelDetails, modelTags);
    }
}
