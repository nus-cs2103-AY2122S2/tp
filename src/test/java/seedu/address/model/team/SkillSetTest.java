package seedu.address.model.team;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalSkills.SKILL_BASH_70;
import static seedu.address.testutil.TypicalSkills.SKILL_C_1;
import static seedu.address.testutil.TypicalSkills.SKILL_C_20;
import static seedu.address.testutil.TypicalSkills.SKILL_JAVA_40;
import static seedu.address.testutil.TypicalSkills.SKILL_JAVA_50;
import static seedu.address.testutil.TypicalSkills.SKILL_LINUX_60;
import static seedu.address.testutil.TypicalSkills.SKILL_PYTHON_30;
import static seedu.address.testutil.TypicalSkills.getTypicalSkillSet;

import org.junit.jupiter.api.Test;

public class SkillSetTest {

    private SkillSet skillSet1 = getTypicalSkillSet();
    private SkillSet skillSet2 = new SkillSet();
    private SkillSet skillSet3 = new SkillSet();

    @Test
    public void equals() {
        skillSet2.add(SKILL_PYTHON_30);
        SkillSet copyOfSkillSet1 = new SkillSet(skillSet1);
        assertFalse(skillSet3.equals(skillSet1));
        assertTrue(copyOfSkillSet1.equals(skillSet1));
        assertFalse(copyOfSkillSet1.equals(skillSet2));
    }

    @Test
    public void addSkill() {
        skillSet1.add(SKILL_BASH_70);
        assertTrue(skillSet1.hasSkill(SKILL_BASH_70));
        assertTrue(skillSet1.hasSkill(SKILL_JAVA_40));
        assertFalse(skillSet1.hasSkill(SKILL_LINUX_60));
    }

    @Test
    public void addSkillSet() {
        skillSet2.addAll(skillSet1);
        assertTrue(skillSet2.hasSkill(SKILL_JAVA_40));
        assertTrue(skillSet2.hasSkill(SKILL_PYTHON_30));
        assertTrue(skillSet2.hasSkill(SKILL_JAVA_50));
        assertFalse(skillSet2.hasSkill(SKILL_BASH_70));
    }

    @Test
    public void getSkillProficiency() {
        assertEquals(40, skillSet1.getSkillProficiency(SKILL_JAVA_50));
        assertEquals(40, skillSet1.getSkillProficiency(SKILL_JAVA_40));
        assertEquals(20, skillSet1.getSkillProficiency(SKILL_C_1));
        assertEquals(0, skillSet1.getSkillProficiency(SKILL_BASH_70));
    }

    @Test
    public void getSkill() {
        assertEquals(SKILL_C_20, skillSet1.getSkill(new Skill("C")));
        assertNull(skillSet1.getSkill(SKILL_BASH_70));
    }
}
