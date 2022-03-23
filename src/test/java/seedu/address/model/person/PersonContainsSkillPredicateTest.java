package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalSkills.SKILL_C_0;
import static seedu.address.testutil.TypicalSkills.SKILL_C_NULL;
import static seedu.address.testutil.TypicalSkills.SKILL_JAVA_40;
import static seedu.address.testutil.TypicalSkills.SKILL_JAVA_50;
import static seedu.address.testutil.TypicalSkills.SKILL_PYTHON_30;

import org.junit.jupiter.api.Test;

import seedu.address.model.team.SkillSet;

class PersonContainsSkillPredicateTest {

    @Test
    void testEquals() {
        SkillSet skillSet1 = new SkillSet();
        skillSet1.add(SKILL_JAVA_50);
        skillSet1.add(SKILL_C_0);

        SkillSet skillSet2 = new SkillSet();
        skillSet2.add(SKILL_JAVA_40);
        skillSet2.add(SKILL_C_NULL);

        SkillSet skillSet3 = new SkillSet();
        skillSet3.add(SKILL_JAVA_40);
        skillSet3.add(SKILL_C_NULL);
        skillSet3.add(SKILL_PYTHON_30);

        PersonContainsSkillPredicate firstPredicate = new PersonContainsSkillPredicate(skillSet1);
        PersonContainsSkillPredicate secondPredicate = new PersonContainsSkillPredicate(skillSet2);
        PersonContainsSkillPredicate thirdPredicate = new PersonContainsSkillPredicate(skillSet2);
        PersonContainsSkillPredicate fourthPredicate = new PersonContainsSkillPredicate(skillSet3);


        //same object -> return true
        assertTrue(firstPredicate.equals(firstPredicate));

        //same skillSet reference -> returns true
        assertTrue(secondPredicate.equals(thirdPredicate));

        //same skills in skillset with different proficiency -> returns true
        assertTrue(firstPredicate.equals(secondPredicate));

        //additional skill in one skillSet -> returns false
        assertFalse(skillSet2.equals(skillSet3));
    }
}
