package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.ApplicationStatus;
import seedu.address.model.person.Availability;
import seedu.address.model.person.Course;
import seedu.address.model.person.Email;
import seedu.address.model.person.InterviewStatus;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_STUDENT_ID = "E0123456";
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "E0123456@u.nus.edu";
    public static final String DEFAULT_COURSE = "Computer Science";
    public static final String DEFAULT_APPLICATION_STATUS = "Pending";
    public static final String DEFAULT_INTERVIEW_STATUS = "Pending";
    public static final String DEFAULT_AVAILABILITY = "1,2,3,4,5,6,7";

    private StudentId studentId;
    private Name name;
    private Phone phone;
    private Email email;
    private Course course;
    private Set<Tag> tags;
    private ApplicationStatus applicationStatus;
    private InterviewStatus interviewStatus;
    private Availability availability;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        studentId = new StudentId(DEFAULT_STUDENT_ID);
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        course = new Course(DEFAULT_COURSE);
        tags = new HashSet<>();
        applicationStatus = new ApplicationStatus(DEFAULT_APPLICATION_STATUS);
        interviewStatus = new InterviewStatus(DEFAULT_INTERVIEW_STATUS);
        availability = new Availability(DEFAULT_AVAILABILITY);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        studentId = personToCopy.getStudentId();
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        course = personToCopy.getCourse();
        tags = new HashSet<>(personToCopy.getTags());
        applicationStatus = personToCopy.getApplicationStatus();
        interviewStatus = personToCopy.getInterviewStatus();
        availability = personToCopy.getAvailability();
    }

    /**
     * Sets the {@code StudentId} of the {@code Person} that we are building.
     */
    public PersonBuilder withStudentId(String id) {
        this.studentId = new StudentId(id);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Course} of the {@code Person} that we are building.
     */
    public PersonBuilder withCourse(String course) {
        this.course = new Course(course);
        return this;
    }

    /**
     * Sets the {@code Application Status} of the {@code Person} that we are building.
     */
    public PersonBuilder withApplicationStatus(String applicationStatus) {
        this.applicationStatus = new ApplicationStatus(applicationStatus);
        return this;
    }

    /**
     * Sets the {@code Interview Status} of the {@code Person} that we are building.
     */
    public PersonBuilder withInterviewStatus(String interviewStatus) {
        this.interviewStatus = new InterviewStatus(interviewStatus);
        return this;
    }

    /**
     * Sets the {@code Availability} of the {@code Person} that we are building.
     */
    public PersonBuilder withAvailability(String availability) {
        this.availability = new Availability(availability);
        return this;
    }

    /**
     * Returns a new Person with specific fields.
     * @return a new Person.
     */
    public Person build() {
        return new Person(studentId, name, phone, email, course, tags, applicationStatus, interviewStatus,
                availability);
    }

}
