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

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class SkillSetTest {


    private SkillSet skillSet1 = getTypicalSkillSet();
    private Skill java100 = new Skill("Java", 100);
    private Skill java90 = new Skill("Java", 90);
    private Skill python100 = new Skill("python", 100);
    private Skill c20 = new Skill("C", 20);
    private Skill c1 = new Skill("C");
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

    @Test
    public void removeDuplicates() {
        Set<Skill> duplicatedSet = new HashSet<>();
        duplicatedSet.add(java100);
        duplicatedSet.add(c1);
        duplicatedSet.add(c20);
        duplicatedSet.add(c1);
        duplicatedSet.add(java90);
        skillSet3.add(c20);
        skillSet3.add(java100);
        assertEquals(new SkillSet(duplicatedSet), skillSet3);
    }
}
