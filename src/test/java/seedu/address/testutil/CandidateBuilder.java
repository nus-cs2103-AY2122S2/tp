package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.candidate.Candidate;
import seedu.address.model.candidate.Course;
import seedu.address.model.candidate.Email;
import seedu.address.model.candidate.Name;
import seedu.address.model.candidate.Phone;
import seedu.address.model.candidate.StudentID;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class CandidateBuilder {

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
     * Creates a {@code CandidateBuilder} with the default details.
     */
    public CandidateBuilder() {
        studentID = new StudentID(DEFAULT_STUDENT_ID);
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        course = new Course(DEFAULT_COURSE);
        tags = new HashSet<>();
    }

    /**
     * Initializes the CandidateBuilder with the data of {@code personToCopy}.
     */
    public CandidateBuilder(Candidate candidateToCopy) {
        studentID = candidateToCopy.getStudentID();
        name = candidateToCopy.getName();
        phone = candidateToCopy.getPhone();
        email = candidateToCopy.getEmail();
        course = candidateToCopy.getCourse();
        tags = new HashSet<>(candidateToCopy.getTags());
    }

    /**
     * Sets the {@code StudentID} of the {@code Person} that we are building.
     */
    public CandidateBuilder withStudentID(String id) {
        this.studentID = new StudentID(id);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public CandidateBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public CandidateBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public CandidateBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public CandidateBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Course} of the {@code Person} that we are building.
     */
    public CandidateBuilder withCourse(String course) {
        this.course = new Course(course);
        return this;
    }

    public Candidate build() {
        return new Candidate(studentID, name, phone, email, course, tags);
    }

}
