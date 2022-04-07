package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.team.Skill;
import seedu.address.model.team.SkillSet;

/**
 * A utility class containing a list of {@code Skill} objects to be used in tests.
 */
public class TypicalSkills {
    public static final Skill SKILL_JAVA_50 = new Skill("Java", 50);
    public static final Skill SKILL_JAVA_40 = new Skill("Java", 40);
    public static final Skill SKILL_C_20 = new Skill("C", 20);
    public static final Skill SKILL_C_1 = new Skill("C", 1);
    public static final Skill SKILL_PYTHON_30 = new Skill("Python", 30);
    public static final Skill SKILL_PYTHON_100 = new Skill("Python", 100);
    public static final Skill SKILL_BASH_70 = new Skill("Bash", 70);
    public static final Skill SKILL_LINUX_60 = new Skill("LINUX", 60);

    public static SkillSet getTypicalSkillSet() {
        SkillSet skillSet = new SkillSet();
        for (Skill skill : getTypicalSkills()) {
            skillSet.add(skill);
        }
        return skillSet;
    }

    public static List<Skill> getTypicalSkills() {
        return new ArrayList<>(Arrays.asList(SKILL_JAVA_40, SKILL_C_20, SKILL_PYTHON_30));
    }
}
