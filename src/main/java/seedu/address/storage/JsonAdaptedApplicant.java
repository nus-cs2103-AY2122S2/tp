package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.ApplicantStatus;
import seedu.address.model.applicant.DateApplied;
import seedu.address.model.applicant.InterviewDate;
import seedu.address.model.applicant.JobId;
import seedu.address.model.applicant.Nric;
import seedu.address.model.applicant.Qualification;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class JsonAdaptedApplicant {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Applicant's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final String nric;
    private final String dateApplied;
    private final String interviewDate;
    private final String job;
    private final String qualification;
    private final String applicationStatus;

    /**
     * Constructs a {@code JsonAdaptedApplicant} with the given person details.
     * todo add applicant status
     */
    @JsonCreator
    public JsonAdaptedApplicant(@JsonProperty("name") String name,
                             @JsonProperty("phone") String phone, @JsonProperty("email") String email,
                             @JsonProperty("address") String address,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                             @JsonProperty("nric") String nric, @JsonProperty("dateapplied") String dateApplied,
                             @JsonProperty("interviewdate") String interviewDate, @JsonProperty("job") String job,
                             @JsonProperty("qualification") String qualification,
                             @JsonProperty("status") String status) {
        this.name = name;
        this.applicationStatus = status;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.nric = nric;
        this.dateApplied = dateApplied;
        this.interviewDate = interviewDate;
        this.job = job;
        this.qualification = qualification;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedApplicant(Applicant source) {
        name = source.getName().fullName;
        applicationStatus = source.getApplicantStatus().applicantStatus.toString();
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        nric = source.getNric().value;
        dateApplied = source.getDateApplied().toString();
        interviewDate = source.getInterviewDate().toString();
        job = source.getJobId().toString();
        qualification = source.getQualification().highestQualification;
    }

    /**
     * Converts this Jackson-friendly adapted applicant object into the model's {@code Applicant} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Applicant.
     */
    public Applicant toModelType() throws IllegalValueException {
        final List<Tag> applicantTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            applicantTags.add(tag.toModelType());
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

        final Set<Tag> modelTags = new HashSet<>(applicantTags);

        if (nric == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName()));
        }

        if (!Nric.isValidNric(nric)) {
            throw new IllegalValueException(Nric.MESSAGE_CONSTRAINTS);
        }
        final Nric modelNric = new Nric(nric);

        if (dateApplied == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DateApplied.class.getSimpleName()));
        }

        if (!DateApplied.isValidDateApplied(dateApplied)) {
            throw new IllegalValueException(DateApplied.MESSAGE_CONSTRAINTS);
        }
        final DateApplied modelDateApplied = new DateApplied(dateApplied);

        if (interviewDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    InterviewDate.class.getSimpleName()));
        }

        if (!InterviewDate.isValidInterviewDate(interviewDate)) {
            throw new IllegalValueException(InterviewDate.MESSAGE_CONSTRAINTS);
        }
        final InterviewDate modelInterviewDate = new InterviewDate(interviewDate);

        if (job == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, JobId.class.getSimpleName()));
        }

        if (!JobId.isValidJobId(job)) {
            throw new IllegalValueException(JobId.MESSAGE_CONSTRAINTS);
        }
        final JobId modelJobId = new JobId(job);

        if (qualification == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Qualification.class.getSimpleName()));
        }

        if (!Qualification.isValidQualification(qualification)) {
            throw new IllegalValueException(Qualification.MESSAGE_CONSTRAINTS);
        }
        final Qualification modelQualification = new Qualification(qualification);

        if (applicationStatus == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ApplicantStatus.class.getSimpleName()));
        }

        if (!ApplicantStatus.isValidStatus(applicationStatus)) {
            throw new IllegalValueException(ApplicantStatus.MESSAGE_CONSTRAINTS);
        }

        final ApplicantStatus modelApplicantStatus = new ApplicantStatus(applicationStatus);

        return new Applicant(modelName, modelPhone, modelEmail, modelAddress, modelTags, modelDateApplied,
                modelNric, modelJobId, modelInterviewDate, modelQualification, modelApplicantStatus);
    }

}
