package seedu.address.storage;

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

/**
 * Jackson-friendly version of {@link Candidate}.
 */
class JsonAdaptedCandidate {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Candidate's %s field is missing!";

    private final String studentId;
    private final String name;
    private final String phone;
    private final String email;
    private final String course;
    private final String seniority;
    private final String applicationStatus;
    private final String interviewStatus;
    private final String availability;
    private final String remark;

    /**
     * Constructs a {@code JsonAdaptedCandidate} with the given candidate details.
     */
    @JsonCreator
    public JsonAdaptedCandidate(@JsonProperty("studentId") String studentId, @JsonProperty("name") String name,
            @JsonProperty("phone") String phone, @JsonProperty("email") String email,
            @JsonProperty("course") String course, @JsonProperty("seniority") String seniority,
            @JsonProperty("applicationStatus") String applicationStatus,
            @JsonProperty("interviewStatus") String interviewStatus,
            @JsonProperty("availability") String availability,
            @JsonProperty("remark") String remark) {
        this.studentId = studentId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.course = course;
        this.seniority = seniority;
        this.applicationStatus = applicationStatus;
        this.interviewStatus = interviewStatus;
        this.availability = availability;
        this.remark = remark;
    }

    /**
     * Converts a given {@code Candidate} into this class for Jackson use.
     */
    public JsonAdaptedCandidate(Candidate source) {
        studentId = source.getStudentId().studentId;
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        course = source.getCourse().course;
        seniority = source.getSeniority().seniority;
        applicationStatus = source.getApplicationStatus().toString();
        interviewStatus = source.getInterviewStatus().toString();
        availability = source.getAvailability().availability;
        remark = source.getRemark().value;

    }

    /**
     * Converts this Jackson-friendly adapted candidate object into the model's {@code Candidate} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted candidate.
     */
    public Candidate toModelType() throws IllegalValueException {
        if (studentId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    StudentId.class.getSimpleName()));
        }
        if (!StudentId.isValidId(studentId)) {
            throw new IllegalValueException(StudentId.MESSAGE_CONSTRAINTS);
        }
        final StudentId modelId = new StudentId(studentId);

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

        return new Candidate(modelId, modelName, modelPhone, modelEmail, modelCourse, modelSeniority,
                new ApplicationStatus(applicationStatus), new InterviewStatus(interviewStatus), modelAvailability,
                modelRemark);
    }
}
