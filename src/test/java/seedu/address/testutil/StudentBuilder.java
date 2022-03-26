package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.lab.LabList;
import seedu.address.model.student.Email;
import seedu.address.model.student.GithubUsername;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;
import seedu.address.model.student.Telegram;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.LabTriplet;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_GITHUB = "amyB123";
    public static final String DEFAULT_TELEGRAM = "amy_B";
    public static final String DEFAULT_STUDENTID = "A1123456B";

    private Name name;
    private Email email;
    private Set<Tag> tags;
    private GithubUsername githubUsername;
    private Telegram telegram;
    private StudentId studentId;
    private LabList labs;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        email = new Email(DEFAULT_EMAIL);
        tags = new HashSet<>();
        githubUsername = new GithubUsername(DEFAULT_GITHUB);
        telegram = new Telegram(DEFAULT_TELEGRAM);
        studentId = new StudentId(DEFAULT_STUDENTID);
        labs = new LabList();
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        email = studentToCopy.getEmail();
        tags = new HashSet<>(studentToCopy.getTags());
        githubUsername = studentToCopy.getGithubUsername();
        telegram = studentToCopy.getTelegram();
        studentId = studentToCopy.getStudentId();
        labs = studentToCopy.getLabs();
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Student} that we are building.
     */
    public StudentBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Student} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code GithubUsername} of the {@code Student} that we are building.
     */
    public StudentBuilder withGithub(String githubUsername) {
        this.githubUsername = new GithubUsername(githubUsername);
        return this;
    }

    /**
     * Sets the {@code Telegram} of the {@code Student} that we are building.
     */
    public StudentBuilder withTelegram(String telegram) {
        this.telegram = new Telegram(telegram);
        return this;
    }

    /**
     * Sets the {@code StudentId} of the {@code Student} that we are building.
     */
    public StudentBuilder withStudentId(String studentId) {
        this.studentId = new StudentId(studentId);
        return this;
    }

    /**
     * Parses the {@code labs} into a {@code LabList} and set it to the {@code Student} that we are building.
     */
    @SafeVarargs
    public final StudentBuilder withLabs(LabTriplet... labs) {
        this.labs = SampleDataUtil.getLabSet(labs);
        return this;
    }

    /**
     * Parses the {@code Student}.
     */
    public Student build() {
        Student s = new Student(name, email, tags, githubUsername, telegram, studentId);
        s.setLabs(labs);
        return s;
    }

}
