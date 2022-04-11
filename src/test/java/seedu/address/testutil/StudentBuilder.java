package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.student.Email;
import seedu.address.model.student.GitHub;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.model.student.Telegram;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_TELEGRAM = "@amybeeee14";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_GITHUB = "amy-bee-10";
    public static final String DEFAULT_TUTORIAL_GROUP = "CS2101 G08";

    private Name name;
    private Telegram telegram;
    private Email email;
    private GitHub gitHub;
    private Set<TutorialGroup> tutorialGroups;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        telegram = new Telegram(DEFAULT_TELEGRAM);
        email = new Email(DEFAULT_EMAIL);
        gitHub = new GitHub(DEFAULT_GITHUB);
        tutorialGroups = new HashSet<>();
        tutorialGroups.add(new TutorialGroup(DEFAULT_TUTORIAL_GROUP));
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        telegram = studentToCopy.getTelegram();
        email = studentToCopy.getEmail();
        gitHub = studentToCopy.getGitHub();
        tutorialGroups = new HashSet<>(studentToCopy.getTutorialGroups());
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tutorialGroups} into a {@code Set<TutorialGroup>}
     * and set it to the {@code Student} that we are building.
     */
    public StudentBuilder withTutorialGroup(String ... tutorialGroups) {
        this.tutorialGroups = SampleDataUtil.getTutorialGroupSet(tutorialGroups);
        return this;
    }

    /**
     * Sets the {@code GitHub} of the {@code Student} that we are building.
     */
    public StudentBuilder withGitHub(String gitHub) {
        this.gitHub = new GitHub(gitHub);
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
     * Sets the {@code Email} of the {@code Student} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Student build() {
        return new Student(name, telegram, email, gitHub, tutorialGroups);
    }

}
