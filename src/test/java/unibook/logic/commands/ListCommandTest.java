//package unibook.logic.commands;
//
//import static unibook.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static unibook.logic.commands.CommandTestUtil.showPersonAtIndex;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import unibook.model.Model;
//import unibook.model.ModelManager;
//import unibook.model.UserPrefs;
//
///**
// * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
// */
//public class ListCommandTest {
//
//    private Model model;
//    private Model expectedModel;
//
//    @BeforeEach
//    public void setUp() {
//        model = new ModelManager(TypicalPersons.getTypicalUniBook(), new UserPrefs());
//        expectedModel = new ModelManager(model.getUniBook(), new UserPrefs());
//    }
//
//    @Test
//    public void execute_listIsNotFiltered_showsSameList() {
//        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
//    }
//
//    @Test
//    public void execute_listIsFiltered_showsEverything() {
//        showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);
//        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
//    }
//}
