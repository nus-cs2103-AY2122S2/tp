package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalSkills.SKILL_C_0;
import static seedu.address.testutil.TypicalSkills.SKILL_C_20;
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
        skillSet2.add(SKILL_C_20);

        SkillSet skillSet3 = new SkillSet();
        skillSet3.add(SKILL_JAVA_40);
        skillSet3.add(SKILL_C_20);
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
        assertFalse(fourthPredicate.equals(thirdPredicate));
    }


    @Test
    void test_personContainsSomeSkills_returnFalse() {
        SkillSet skillSetPythonC = new SkillSet();
        skillSetPythonC.add(SKILL_PYTHON_30);
        skillSetPythonC.add(SKILL_C_20);

        PersonContainsSkillPredicate personContainsPythonCPredicate = new PersonContainsSkillPredicate(skillSetPythonC);

        assertFalse(personContainsPythonCPredicate.test(BENSON));
    }

    @Test
    void test_personContainsAllSkills_returnFalse() {
        SkillSet skillSetPythonC = new SkillSet();
        skillSetPythonC.add(SKILL_PYTHON_30);
        skillSetPythonC.add(SKILL_C_20);

        PersonContainsSkillPredicate personContainsPythonCPredicate = new PersonContainsSkillPredicate(skillSetPythonC);

        assertTrue(personContainsPythonCPredicate.test(ALICE));
    }

    @Test
    void test_personContainsSkill_returnTrue() {
        SkillSet skillSetC = new SkillSet();
        skillSetC.add(SKILL_C_20);

        SkillSet skillSetPython = new SkillSet();
        skillSetPython.add(SKILL_PYTHON_30);

        PersonContainsSkillPredicate personContainsCPredicate = new PersonContainsSkillPredicate(skillSetC);
        PersonContainsSkillPredicate personContainsPythonPredicate = new PersonContainsSkillPredicate(skillSetPython);

        assertTrue(personContainsCPredicate.test(ALICE));
        assertTrue(personContainsPythonPredicate.test(ALICE));
        assertTrue(personContainsPythonPredicate.test(BENSON));
    }

    @Test
    void test_personDoesNotContainsSkill_returnFalse() {
        SkillSet skillSetJava = new SkillSet();
        skillSetJava.add(SKILL_JAVA_40);

        PersonContainsSkillPredicate personContainsJavaPredicate = new PersonContainsSkillPredicate(skillSetJava);

        assertFalse(personContainsJavaPredicate.test(BENSON));
        assertFalse(personContainsJavaPredicate.test(ALICE));
    }

}
