package manageezpz.logic.commands;

import static manageezpz.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static manageezpz.logic.parser.CliSyntax.PREFIX_TASK;
import static manageezpz.testutil.TypicalPersons.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import manageezpz.model.Model;
import manageezpz.model.ModelManager;
import manageezpz.model.UserPrefs;
import manageezpz.model.task.TaskMultiplePredicate;

class FindTaskCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void equals() {
        TaskMultiplePredicate firstPredicate =
                new TaskMultiplePredicate(PREFIX_TASK, Collections.singletonList("Genshin"),
                        null, null, null, null);
        TaskMultiplePredicate secondPredicate =
                new TaskMultiplePredicate(PREFIX_TASK, Collections.singletonList("Impact"),
                        null, null, null, null);

        FindTaskCommand firstFindTaskCommand = new FindTaskCommand(firstPredicate);
        FindTaskCommand secondFindTaskCommand = new FindTaskCommand(secondPredicate);

        // same object -> returns true
        assertTrue(firstFindTaskCommand.equals(firstFindTaskCommand));

        // same predicate -> returns true
        FindTaskCommand copyFirstFindTaskCommand = new FindTaskCommand(firstPredicate);
        assertTrue(firstFindTaskCommand.equals(copyFirstFindTaskCommand));

        // Different types -> returns false
        assertFalse(firstFindTaskCommand.equals("predicate"));

        // null -> returns false
        assertFalse(firstFindTaskCommand.equals(null));

        // Different description -> return false
        assertFalse(firstFindTaskCommand.equals(secondFindTaskCommand));
    }

    // TODO: finish up testing for findTaskCommandTest.java
}
