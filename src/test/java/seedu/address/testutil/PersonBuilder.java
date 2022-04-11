package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Email;
import seedu.address.model.person.GithubUsername;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.team.SkillSet;
import seedu.address.model.team.Team;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_USERNAME = "amybee99";

    private Name name;
    private Phone phone;
    private Email email;
    private GithubUsername githubUsername;
    private Set<Team> teams;
    private SkillSet skillSet;
    private boolean isPotentialTeammate;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        githubUsername = new GithubUsername(DEFAULT_USERNAME);
        teams = new HashSet<>();
        skillSet = new SkillSet();
        isPotentialTeammate = false;
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        githubUsername = personToCopy.getGithubUsername();
        isPotentialTeammate = personToCopy.isPotentialTeammate();
        teams = new HashSet<>(personToCopy.getTeams());
        skillSet = new SkillSet(personToCopy.getSkillSet());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code teams} into a {@code Set<Team>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTeams(String... teams) {
        this.teams = SampleDataUtil.getTeamSet(teams);
        return this;
    }

    /**
     * Parses the {@code teams} into a {@code Set<Team>} and add them to the {@code Person} that we are building.
     *
     * @param teams the teams to be appended to the current list of teams of a person
     * @return the set of teams this person is in
     */
    public PersonBuilder addTeams(String... teams) {
        Set<Team> teamsToAdd = SampleDataUtil.getTeamSet(teams);
        this.teams.addAll(teamsToAdd);
        return this;
    }

    /**
     * Parses the {@code skill} into a {@code Set<Skill>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withSkillSet(String... skillSet) {
        this.skillSet = SampleDataUtil.getSkillSet(skillSet);
        return this;
    }

    /**
     * Sets the {@code GithubUsername} of the {@code Person} that we are building.
     */
    public PersonBuilder withGithubUsername(String username) {
        this.githubUsername = new GithubUsername(username);
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
     * Sets the {@code isPotentialTeammate} of the {@code Person} that we are building.
     */
    public PersonBuilder isPotentialTeammate(boolean isPotentialTeammate) {
        this.isPotentialTeammate = isPotentialTeammate;
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, githubUsername, teams, skillSet, isPotentialTeammate);
    }

}
