package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Skill;
import seedu.address.testutil.PersonBuilder;

class PersonBySkillProficiencyComparatorTest {

    @Test
    public void equals() {
        Skill firstSkill = new Skill("C");
        Skill secondSkill = new Skill("Java");

        PersonBySkillProficiencyComparator firstComparator = new PersonBySkillProficiencyComparator(firstSkill);
        PersonBySkillProficiencyComparator secondComparator = new PersonBySkillProficiencyComparator(secondSkill);

        // same object -> returns true
        assertTrue(firstComparator.equals(firstComparator));

        PersonBySkillProficiencyComparator firstComparatorCopy = new PersonBySkillProficiencyComparator(firstSkill);
        // same skill reference -> return true
        assertTrue(firstComparator.equals(firstComparatorCopy));

        Skill secondSkillCopy = new Skill("Java");
        PersonBySkillProficiencyComparator secondComparatorCopy =
                new PersonBySkillProficiencyComparator(secondSkillCopy);
        // same skill name -> returns true
        assertTrue(secondComparator.equals(secondComparatorCopy));

        // different skill -> returns false
        assertFalse(firstComparator.equals(secondComparator));
    }

    @Test
    public void compareTo_personsWithEqualSkillProficiency_returnsZero() {
        Person person1 = new PersonBuilder().withSkillSet("C_50").build();
        Person person2 = new PersonBuilder().withSkillSet("C_50").build();

        Skill comparedSkill = new Skill("C");
        PersonBySkillProficiencyComparator comparator = new PersonBySkillProficiencyComparator(comparedSkill);
        assertEquals(comparator.compare(person1, person2), 0);
    }

    @Test
    public void compareTo_firstPersonWithLowerProficiency_returnsNegative() {
        Person person1 = new PersonBuilder().withSkillSet("Java_30").build();
        Person person2 = new PersonBuilder().withSkillSet("Java_60").build();

        Skill comparedSkill = new Skill("Java");
        PersonBySkillProficiencyComparator comparator = new PersonBySkillProficiencyComparator(comparedSkill);
        assertTrue(comparator.compare(person1, person2) < 0);
    }

    @Test
    public void compareTo_secondPersonWithLowerProficiency_returnsPositive() {
        Person person1 = new PersonBuilder().withSkillSet("Python_80").build();
        Person person2 = new PersonBuilder().withSkillSet("Python_10").build();

        Skill comparedSkill = new Skill("Python");
        PersonBySkillProficiencyComparator comparator = new PersonBySkillProficiencyComparator(comparedSkill);
        assertTrue(comparator.compare(person1, person2) > 0);
    }
}
