package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.team.Skill;
import seedu.address.model.team.SkillSet;
import seedu.address.model.team.Team;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final GithubUsername githubUsername;
    private final SkillSet skillSet = new SkillSet();

    // Data fields
    private final boolean isPotentialTeammate;
    private final Set<Team> teams = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, GithubUsername username,
                  Set<Team> teams, SkillSet skillSet, boolean isPotentialTeammate) {
        requireAllNonNull(name, phone, email, username, teams, skillSet);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.githubUsername = username;
        this.teams.addAll(teams);
        this.skillSet.addAll(skillSet);
        this.isPotentialTeammate = isPotentialTeammate;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public GithubUsername getGithubUsername() {
        return githubUsername;
    }

    public boolean isPotentialTeammate() {
        return isPotentialTeammate;
    }

    /**
     * Returns an immutable team set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Team> getTeams() {
        return Collections.unmodifiableSet(teams);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && (otherPerson.getPhone().equals(getPhone())
                || otherPerson.getEmail().equals(getEmail())
                || otherPerson.getGithubUsername().equals(getGithubUsername()));
    }

    /**
     * Returns value that are duplicate for person
     * @param otherPerson other person to compare
     * @return values that are duplicated
     */
    public String getDuplicateValue(Person otherPerson) {
        if (otherPerson.getPhone().equals(getPhone())) {
            return "Phone";
        } else if (otherPerson.getEmail().equals(getEmail())) {
            return "Email";
        } else if (otherPerson.getGithubUsername().equals(getGithubUsername())) {
            return "Github UserName";
        } else {
            return "Error no same duplicate values";
        }
    }

    /**
     * Returns an immutable skill set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public SkillSet getSkillSet() {
        return skillSet;
    }

    /**
     * Returns the skill proficiency of the person for the given skill,
     * or 0 if the person does not have the skill.
     *
     * @param skill Skill of person to get proficiency of.
     * @return Skill proficiency of the person.
     */
    public int getSkillProficiency(Skill skill) {
        return skillSet.getSkillProficiency(skill);
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getGithubUsername().equals(getGithubUsername())
                && otherPerson.getTeams().equals(getTeams())
                && otherPerson.getSkillSet().equals(getSkillSet())
                && otherPerson.isPotentialTeammate == isPotentialTeammate;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, githubUsername, isPotentialTeammate, teams, skillSet);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; GitHub Username: ")
                .append(getGithubUsername());

        Set<Team> teams = getTeams();
        if (!teams.isEmpty()) {
            builder.append("; Teams: ");
            teams.forEach(builder::append);
        }
        String skillString = this.skillSet.toString();

        return builder.toString().concat(skillString);
    }

}
