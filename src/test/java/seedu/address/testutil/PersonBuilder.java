package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Email;
import seedu.address.model.person.GithubUsername;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.Telegram;
import seedu.address.model.person.lab.LabList;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

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
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        email = new Email(DEFAULT_EMAIL);
        tags = new HashSet<>();
        githubUsername = new GithubUsername(DEFAULT_GITHUB);
        telegram = new Telegram(DEFAULT_TELEGRAM);
        studentId = new StudentId(DEFAULT_STUDENTID);
        labs = new LabList();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        email = personToCopy.getEmail();
        tags = new HashSet<>(personToCopy.getTags());
        githubUsername = personToCopy.getGithubUsername();
        telegram = personToCopy.getTelegram();
        studentId = personToCopy.getStudentId();
        labs = personToCopy.getLabs();
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
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code GithubUsername} of the {@code Person} that we are building.
     */
    public PersonBuilder withGithub(String githubUsername) {
        this.githubUsername = new GithubUsername(githubUsername);
        return this;
    }

    /**
     * Sets the {@code Telegram} of the {@code Person} that we are building.
     */
    public PersonBuilder withTelegram(String telegram) {
        this.telegram = new Telegram(telegram);
        return this;
    }

    /**
     * Sets the {@code StudentId} of the {@code Person} that we are building.
     */
    public PersonBuilder withStudentId(String studentId) {
        this.studentId = new StudentId(studentId);
        return this;
    }

    /**
     * Parses the {@code labs} into a {@code LabList} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withLabs(String ... labs) {
        this.labs = SampleDataUtil.getLabSet(labs);
        return this;
    }

    /**
     * Parses the {@code Person}.
     */
    public Person build() {
        Person p = new Person(name, email, tags, githubUsername, telegram, studentId);
        p.setLabs(labs);
        return p;
    }

}
