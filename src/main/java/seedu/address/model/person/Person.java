package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Skill;
import seedu.address.model.tag.Tag;

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

    // Data fields
    private final Set<Tag> tags = new HashSet<>();
    private final Set<Skill> skillSet = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, GithubUsername username, Set<Tag> tags, Set<Skill> skillSet) {
        requireAllNonNull(name, phone, email, username, tags, skillSet);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.githubUsername = username;
        this.tags.addAll(tags);
        this.skillSet.addAll(skillSet);
        //this.skillSet.add(new Skill("Python",30));
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

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Skill> getSkillSet() {
        return Collections.unmodifiableSet(skillSet);
    }

    /**
     * Returns the skill proficiency of the person for the given skill,
     * or 0 if the person does not have the skill.
     *
     * @param skill Skill of person to get proficiency of.
     * @return Skill proficiency of the person.
     */
    public int getSkillProficiency(Skill skill) {
        for (Skill s: skillSet) {
            if (s.isSameSkill(skill)) {
                return s.skillProficiency;
            }
        }
        return 0;
    }

    /**
     * Returns true if the person contains the skill, false otherwise.
     *
     * @param skill The skill to check if the person possess.
     * @return True if the person has the skill, false otherwise.
     */
    public boolean hasSkill(Skill skill) {
        requireAllNonNull(skill);
        for (Skill s : skillSet) {
            if (skill.isSameSkill(s)) {
                return true;
            }
        }
        return false;
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
                && otherPerson.getName().equals(getName());
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
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getSkillSet().equals(getSkillSet());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, githubUsername, tags, skillSet);
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

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        Set<Skill> skillSet = getSkillSet();
        if (!skillSet.isEmpty()) {
            builder.append("; Skills: ");
            skillSet.forEach(builder::append);
        }

        return builder.toString();
    }

}
