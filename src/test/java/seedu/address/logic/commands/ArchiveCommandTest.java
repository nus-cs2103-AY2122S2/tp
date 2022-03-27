package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ArchiveCommandTest {
    private Model model;
    private Model expectedModel;
    private Path testFilePath;
    private Path testDirectoryPath;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        testFilePath = Paths.get("src\\test\\data\\ArchiveFilesTest\\testAddressBook.json");
        model.setAddressBookFilePath(testFilePath);
        expectedModel = model;
    }

    @Test
    public void execute_validDirectoryPath() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.setAddressBookFilePath(testFilePath);
        assertCommandSuccess(new ArchiveCommand(), model, ArchiveCommand.MESSAGE_SUCCESS, model);
    }
}
