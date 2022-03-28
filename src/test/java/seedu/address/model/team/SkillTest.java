package seedu.address.model.team;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SkillTest {

    private Skill java100 = new Skill("Java", 100);
    private Skill copyOfJava100 = new Skill("Java", 100);
    private Skill java90 = new Skill("Java", 90);
    private Skill python100 = new Skill("python", 100);
    private Skill c0 = new Skill("C");

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Skill(null));
    }

    @Test
    public void constructor_invalidSkillArgument_throwsIllegalArgumentException() {
        String invalidSkillArgument = "";
        assertThrows(IllegalArgumentException.class, () -> new Skill(invalidSkillArgument));
        assertThrows(IllegalArgumentException.class, () -> new Skill(invalidSkillArgument, 0));
    }

    @Test
    public void equals() {

        //same skill proficiency and name -> true
        assertTrue(java100.equals(copyOfJava100));

        //different skill proficiency -> false
        assertFalse(java100.equals(java90));

        //different skill name -> false
        assertFalse(java100.equals(python100));

        //different skill name and proficiency -> false
        assertFalse(java100.equals(c0));
    }

    @Test
    public void isSameSkill() {

        //same skill name with same skill proficiency -> true
        assertTrue(java100.isSameSkill(copyOfJava100));

        //same skill name with different skill proficiency -> true
        assertTrue(java100.isSameSkill(java90));

        //different skill name with different skill proficiency -> false
        assertFalse(java100.isSameSkill(c0));

        //different skill name with same skill level -> false
        assertFalse(java100.isSameSkill(python100));
    }

    @Test
    public void toStringTest() {
        assertEquals(java100.toString(), "[Java, 100]");
        assertEquals(c0.toString(), "[C, 0]");
    }

    @Test
    public void isValidSkillName() {
        // null skill argument
        assertThrows(NullPointerException.class, () -> Skill.isValidSkillName(null));
    }

    @Test void isValidSkillProficiency() {
        assertTrue(Skill.isValidSkillProficiencyRange(100));
        assertTrue(Skill.isValidSkillProficiencyRange(10));
        assertTrue(Skill.isValidSkillProficiencyRange(0));
        assertFalse(Skill.isValidSkillProficiencyRange(101));
        assertFalse(Skill.isValidSkillProficiencyRange(-1));
    }

}
