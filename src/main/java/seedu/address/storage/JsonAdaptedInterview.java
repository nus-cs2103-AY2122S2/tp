package seedu.address.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.candidate.ApplicationStatus;
import seedu.address.model.candidate.Availability;
import seedu.address.model.candidate.Candidate;
import seedu.address.model.candidate.Course;
import seedu.address.model.candidate.Email;
import seedu.address.model.candidate.InterviewStatus;
import seedu.address.model.candidate.Name;
import seedu.address.model.candidate.Phone;
import seedu.address.model.candidate.Remark;
import seedu.address.model.candidate.Seniority;
import seedu.address.model.candidate.StudentId;
import seedu.address.model.interview.Interview;

/**
 * Jackson-friendly version of {@link Interview}.
 */
class JsonAdaptedInterview {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Interview's %s field is missing!";

    private final String studentID;
    private final String name;
    private final String phone;
    private final String email;
    private final String course;
    private final String seniority;
    private final String applicationStatus;
    private final String interviewStatus;
    private final String availability;
    private final String remark;
    private final String interviewDateTime;

    /**
     * Constructs a {@code JsonAdaptedInterview} with the given candidate and interviewDateTime details.
     */
    @JsonCreator
    public JsonAdaptedInterview(@JsonProperty("studentID") String studentID, @JsonProperty("name") String name,
                                @JsonProperty("phone") String phone, @JsonProperty("email") String email,
                                @JsonProperty("course") String course, @JsonProperty("seniority") String seniority,
                                @JsonProperty("applicationStatus") String applicationStatus,
                                @JsonProperty("interviewStatus") String interviewStatus,
                                @JsonProperty("availability") String availability,
                                @JsonProperty("interviewDateTime") String interviewDateTime,
                                @JsonProperty("remark") String remark) {
        this.studentID = studentID;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.course = course;
        this.seniority = seniority;
        this.applicationStatus = applicationStatus;
        this.interviewStatus = interviewStatus;
        this.availability = availability;
        this.remark = remark;
        this.interviewDateTime = interviewDateTime;
    }

    /**
     * Converts a given {@code Interview} into this class for Jackson use.
     */
    public JsonAdaptedInterview(Interview source) {
        studentID = source.getCandidate().getStudentId().studentId;
        name = source.getCandidate().getName().fullName;
        phone = source.getCandidate().getPhone().value;
        email = source.getCandidate().getEmail().value;
        course = source.getCandidate().getCourse().course;
        seniority = source.getCandidate().getSeniority().seniority;
        applicationStatus = source.getCandidate().getApplicationStatus().toString();
        interviewStatus = source.getCandidate().getInterviewStatus().toString();
        availability = source.getCandidate().getAvailability().availability;
        remark = source.getCandidate().getRemark().value;
        interviewDateTime = source.getInterviewDateTime().toString();
    }

    /**
     * Converts this Jackson-friendly adapted interview object into the model's {@code Interview} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted candidate or interview.
     */
    public Interview toModelType() throws IllegalValueException {
        if (studentID == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    StudentId.class.getSimpleName()));
        }
        if (!StudentId.isValidId(studentID)) {
            throw new IllegalValueException(StudentId.MESSAGE_CONSTRAINTS);
        }
        final StudentId modelId = new StudentId(studentID);

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

        if (course == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Course.class.getSimpleName()));
        }
        if (!Course.isValidCourse(course)) {
            throw new IllegalValueException(Course.MESSAGE_CONSTRAINTS);
        }
        final Course modelCourse = new Course(course);

        if (seniority == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Seniority.class.getSimpleName()));
        }
        if (!Seniority.isValidSeniority(seniority)) {
            throw new IllegalValueException(Seniority.MESSAGE_CONSTRAINTS);
        }
        final Seniority modelSeniority = new Seniority(seniority);

        if (availability == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Availability.class.getSimpleName()));
        }
        if (!Availability.isValidDay(availability)) {
            throw new IllegalValueException(Availability.MESSAGE_CONSTRAINTS);
        }
        final Availability modelAvailability = new Availability(availability);


        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }
        final Remark modelRemark = new Remark(remark);


        Candidate candidate = new Candidate(modelId, modelName, modelPhone, modelEmail, modelCourse,
                modelSeniority, new ApplicationStatus(applicationStatus), new InterviewStatus(interviewStatus),
                modelAvailability, modelRemark);

        if (interviewDateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "test"));
        }

        final LocalDateTime modelInterviewDateTime = LocalDateTime.parse(interviewDateTime);
        return new Interview(candidate, modelInterviewDateTime);
    }

}
