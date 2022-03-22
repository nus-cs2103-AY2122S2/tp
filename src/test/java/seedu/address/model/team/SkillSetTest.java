package seedu.address.model.team;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SkillSetTest {

    private Skill java100 = new Skill("Java", 100);
    private Skill java90 = new Skill("Java", 90);
    private Skill python100 = new Skill("python", 100);
    private Skill c0 = new Skill("C");
    private SkillSet skillSet1 = new SkillSet();
    private SkillSet skillSet2 = new SkillSet();

    @Test
    public void equals() {
        skillSet1.add(java90);
        skillSet2.add(python100);
        SkillSet copyOfSkillSet1 = new SkillSet(skillSet1);
        assertTrue(copyOfSkillSet1.equals(skillSet1));
        assertFalse(copyOfSkillSet1.equals(skillSet2));
    }

    @Test
    public void adding_skill_to_SkillSet_success() {
        skillSet1.add(java100);
        assertTrue(skillSet1.hasSkill(java90));
        assertTrue(skillSet1.hasSkill(java100));
        assertFalse(skillSet1.hasSkill(python100));
    }

    @Test
    public void adding_skillSet_to_SkillSet_success() {
        skillSet1.add(java100);
        skillSet1.add(python100);
        skillSet2.addAll(skillSet1);
        assertTrue(skillSet2.hasSkill(java100));
        assertTrue(skillSet2.hasSkill(python100));
        assertTrue(skillSet2.hasSkill(java90));
        assertFalse(skillSet2.hasSkill(c0));
    }

    @Test
    public void get_SkillProficiency_success() {
        skillSet1.add(java90);
        skillSet1.add(c0);
        skillSet1.add(python100);
        assertEquals(90, skillSet1.getSkillProficiency(java100));
        assertEquals(90, skillSet1.getSkillProficiency(java90));
        assertEquals(0, skillSet1.getSkillProficiency(c0));
    }
}
