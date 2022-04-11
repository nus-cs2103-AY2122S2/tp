package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalContactList;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ContactList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Group;
import seedu.address.model.person.Mod;
import seedu.address.model.person.Name;
import seedu.address.model.tasks.PriorityTaskList;

public class ImportCsvCommandTest {
    private Model model = new ModelManager(getTypicalContactList(), new UserPrefs(), new PriorityTaskList());
    private Model emptyModel = new ModelManager(new ContactList(), new UserPrefs(), new PriorityTaskList());

    @Test
    public void execute_personAcceptedByModel_importSuccessful() throws Exception {
        ImportCsvCommand importCsvCommand = new ImportCsvCommand(Paths
                .get("src/test/data/ImportCsvTest/sample-tutorial-data.csv"));

        importCsvCommand.execute(emptyModel);

        assertTrue(emptyModel.getFilteredPersonList()
                .stream()
                .anyMatch(p -> p.getName().equals(new Name("Bruce Wayne"))));

        assertEquals(3, emptyModel.getFilteredPersonList()
                .stream()
                .filter(p -> p.getMod().equals(new Mod("CS2434"))).count());

        assertEquals(2, emptyModel.getFilteredPersonList()
                .stream()
                .filter(p -> p.getGroup().equals(new Group("T17"))).count());
    }

    @Test
    public void fileNotFound_exceptionThrown() {
        assertThrows(CommandException.class, () ->
            new ImportCsvCommand(Paths.get("path")).execute(model),
            ImportCsvCommand.FILE_DOES_NOT_EXIST);
    }

    @Test
    public void notACsvFile_exceptionThrown() {
        assertThrows(CommandException.class, () ->
                        new ImportCsvCommand(Paths.get("src/test/data/ImportCsvTest")).execute(model),
                ImportCsvCommand.NOT_A_CSV_FILE);
    }

    @Test
    public void equals() {
        Path pathOne = Paths.get("path1");
        Path pathTwo = Paths.get("path2");

        ImportCsvCommand importCsvCommandOne = new ImportCsvCommand(pathOne);
        ImportCsvCommand importCsvCommandTwo = new ImportCsvCommand(pathTwo);

        // same object -> returns true
        assertEquals(importCsvCommandOne, importCsvCommandOne);

        // same values -> returns true
        ImportCsvCommand importCsvCommandOneCopy = new ImportCsvCommand(pathOne);
        assertEquals(importCsvCommandOne, importCsvCommandOneCopy);

        // different types -> returns false
        assertNotEquals(1, importCsvCommandOne);

        // null -> returns false
        assertNotEquals(null, importCsvCommandOne);

        // different person -> returns false
        assertNotEquals(importCsvCommandOne, importCsvCommandTwo);
    }
}
