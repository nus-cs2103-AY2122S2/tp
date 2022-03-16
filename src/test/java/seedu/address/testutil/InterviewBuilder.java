package seedu.address.testutil;

import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * A utility class to help with building Person objects.
 */
public class InterviewBuilder {

    public static final String DEFAULT_STUDENT_ID = "E0123456";
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "E0123456@u.nus.edu";
    public static final String DEFAULT_COURSE = "Computer Science";

    private StudentID studentID;
    private Name name;
    private Phone phone;
    private Email email;
    private Course course;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public InterviewBuilder() {
        studentID = new StudentID(DEFAULT_STUDENT_ID);
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        course = new Course(DEFAULT_COURSE);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public InterviewBuilder(Person personToCopy) {
        studentID = personToCopy.getStudentID();
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        course = personToCopy.getCourse();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code StudentID} of the {@code Person} that we are building.
     */
    public InterviewBuilder withStudentID(String id) {
        this.studentID = new StudentID(id);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public InterviewBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public InterviewBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public InterviewBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public InterviewBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Course} of the {@code Person} that we are building.
     */
    public InterviewBuilder withCourse(String course) {
        this.course = new Course(course);
        return this;
    }

    public Person build() {
        return new Person(studentID, name, phone, email, course, tags);
    }

}
