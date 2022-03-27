package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
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
    private Path validTestFilePath;
    private Path invalidTestFilePath;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        validTestFilePath = Paths.get("src\\test\\data\\ArchiveFilesTest\\testAddressBook.json");
        invalidTestFilePath = Paths.get("src\\test\\data\\ArchiveFilesTest\\noSuchAddressBook.json");
        model.setAddressBookFilePath(validTestFilePath);
    }

    @Test
    public void execute_archiveValidFile_archiveSuccessful() {
        Model expectedModel;
        model.setAddressBookFilePath(validTestFilePath);
        expectedModel = model;
        assertCommandSuccess(new ArchiveCommand(), model, ArchiveCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_archiveInvalidFile_archiveUnsuccessful() {
        model.setAddressBookFilePath(invalidTestFilePath);
        assertCommandFailure(new ArchiveCommand(), model, ArchiveCommand.MESSAGE_FAILURE);
    }

}
