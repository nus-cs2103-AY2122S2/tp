package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.team.Skill;

/**
 * Jackson-friendly version of {@link Skill}.
 */
public class JsonAdaptedSkill {

    private final String skillName;
    private final int skillProficiency;

    /**
     * Constructs a {@code JsonAdaptedSkill} with the given {@code skillName}.
     */
    public JsonAdaptedSkill(String skillName, int skillProficiency) {
        this.skillName = skillName;
        this.skillProficiency = skillProficiency;
    }

    /**
     * Converts a given {@code Skill} into this class for Jackson use.
     */
    public JsonAdaptedSkill(Skill source) {
        skillName = source.skillName;
        skillProficiency = source.skillProficiency;
    }

    /**
     * Converts a given string into this class for Jackson use.
     */
    @JsonCreator
    public JsonAdaptedSkill(String source) {
        String[] skillInfo = source.split("_");
        skillName = skillInfo[0];
        skillProficiency = Integer.parseInt(skillInfo[1]);
    }

    @JsonValue
    public String getSkillValue() {
        return String.format("%s_%d", skillName, skillProficiency);
    }

    /**
     * Converts this Jackson-friendly adapted skill object into the model's {@code Skill} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted skill.
     */
    public Skill toModelType() throws IllegalValueException {
        if (!Skill.isValidSkillName(skillName)) {
            throw new IllegalValueException(Skill.NAME_CONSTRAINTS);
        }
        if (!Skill.isValidSkillProficiencyRange(skillProficiency)) {
            throw new IllegalValueException(Skill.PROFICIENCY_CONSTRAINTS_RANGE);
        }
        return new Skill(skillName, skillProficiency);
    }


}
