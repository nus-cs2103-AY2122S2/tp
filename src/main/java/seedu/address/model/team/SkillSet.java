package seedu.address.model.team;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class SkillSet {
    private Set<Skill> skillSet;

    public SkillSet() {
        this.skillSet = new HashSet<>();
    }

    /**
     * Construtor for SkillSet
     * @param skillSet Set containing Skills
     */
    public SkillSet(Set<Skill> skillSet) {
        requireAllNonNull(skillSet);
        this.skillSet = skillSet;
    }

    /**
     * Constructor for SkillSet
     * @param skillSet SkillSet of skills
     */
    public SkillSet(SkillSet skillSet) {
        requireAllNonNull(skillSet);
        this.skillSet = skillSet.getSkillSet();
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
     * Returns true if the current SkillSet has all the skills inside the provided SkillSet.
     *
     * @param skillset The SkillSet that contains all the skills that this SkillSet must possess.
     * @return True if this SkillSet contains all the skills, false otherwise.
     */
    public boolean hasSkills(SkillSet skillset) {
        requireAllNonNull(skillset);
        return skillset.getSkillSetInStream()
                .allMatch((this::hasSkill));
    }

    /**
     * Adds all of provided skillSet into this skillSet
     * @param additionalSkill skillSet to be added
     */
    public void addAll(SkillSet additionalSkill) {
        removeSkillSet(additionalSkill);
        Set<Skill> toAdd = additionalSkill.getSkillSet();
        skillSet.addAll(toAdd);
    }

    /**
     * Add a skill into this skillSet
     * @param additionalSkill Skill to be added
     */
    public void add(Skill additionalSkill) {
        removeSkill(additionalSkill);
        skillSet.add(additionalSkill);
    }

    /**
     * Remove SkillSet if present in current SkillSet
     * @param skillSetRemove SkillSet to remove
     */
    public void removeSkillSet(SkillSet skillSetRemove) {
        for (Skill s : skillSetRemove.getSkillSet()) {
            removeSkill(s);
        }
    }

    /**
     * Remove Skill if present in current SkillSet
     * @param skill SkillSet to remove
     */
    public void removeSkill(Skill skill) {
        Skill toRemove = getSkill(skill);
        if (toRemove == null) {
            return;
        }
        this.skillSet.remove(toRemove);
    }

    /**
     * Get the skill with provided Skill in SkillSet
     * @param skillToGet skill to get from SkillSet
     * @return Skill in SkillSet
     */
    public Skill getSkill(Skill skillToGet) {
        if (!this.hasSkill(skillToGet)) {
            for (Skill s : skillSet) {
                if (skillToGet.isSameSkill(s)) {
                    return s;
                }
            }
        }
        return null;
    }

    public Set<Skill> getSkillSet() {
        return skillSet;
    }

    /**
     * Returns Skill in stream
     * @return A stream of Skill
     */
    public Stream<Skill> getSkillSetInStream() {
        return skillSet.stream();
    }

    public int getSize() {
        return this.skillSet.size();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        if (!skillSet.isEmpty()) {
            builder.append("; Skills: ");
            skillSet.forEach(builder::append);
        }
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SkillSet // instanceof handles nulls
                && this.skillSet.equals(((SkillSet) other).getSkillSet())); // state check
    }

    /**
     * Checks if another SkillSet has the same Skill name as the current SkillSet
     * @param other SkillSet to compare to
     * @return true if entered SkillSet has the same Skill names as current, false otherwise
     */
    public boolean hasSameSkillSet(Object other) {
        if (other == this) {
            return true;
        } else if (!(other instanceof SkillSet)) {
            return false;
        }
        SkillSet otherSkillSet = (SkillSet) other;

        if (otherSkillSet.getSize() != this.getSize()) {
            return false;
        }
        int sameSkill = 0;
        for (Skill s : otherSkillSet.getSkillSet()) {
            if (this.hasSkill(s)) {
                sameSkill++;
            }
        }
        return sameSkill == this.getSize();
    }

}
