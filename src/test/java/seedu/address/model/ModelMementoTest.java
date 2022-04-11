package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.NameFacultyRoleContainsAnyKeywordsPredicate;
import seedu.address.model.person.Person;

class ModelMementoTest {

    @Test
    void getModel() {
        Model expectedModel = new ModelManager();
        ModelMemento modelMemento = new ModelMemento();
        modelMemento.setModel(expectedModel);
        Model actualModel = modelMemento.getModel();
        assertEquals(expectedModel, actualModel);
    }

    @Test
    void getPredicate() {
        Predicate<Person> expectedPredicate = preparePredicate("TA");
        ModelMemento modelMemento = new ModelMemento();
        modelMemento.setPredicate(expectedPredicate);
        Predicate<Person> actualPredicate = modelMemento.getPredicate();
        assertEquals(expectedPredicate, actualPredicate);
    }

    @Test
    void setModel() {
        Model model = new ModelManager();
        ModelMemento actualMem = new ModelMemento();
        actualMem.setModel(model);
        assertEquals(model, actualMem.getModel());
    }

    @Test
    void setPredicate() {
        Predicate<Person> predicate = preparePredicate("TA");
        ModelMemento actualMem = new ModelMemento();
        actualMem.setPredicate(predicate);
        assertEquals(predicate, actualMem.getPredicate());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsAnyPredicate}.
     */
    private NameFacultyRoleContainsAnyKeywordsPredicate preparePredicate(String userInput) {
        return new NameFacultyRoleContainsAnyKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
