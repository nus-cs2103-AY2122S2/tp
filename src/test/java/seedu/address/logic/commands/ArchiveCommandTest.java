package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ArchiveCommandTest {

    private Path invalidTestFilePath;

    @BeforeEach
    public void setUp() {
        invalidTestFilePath = Paths.get("src\\test\\data\\ArchiveFilesTest\\noSuchAddressBook.json");
    }

    // Note: Manual testing is needed to test if an existing database file can be archived
    // As automated testing will cause CI/CD tests in GitActions to fail

    @Test
    public void invalidTestFileDoesNotExist() {
        assertFalse(Files.exists(invalidTestFilePath));
    }

    @Test
    public void execute_archiveInvalidFile_archiveUnsuccessful() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.setAddressBookFilePath(invalidTestFilePath);
        assertCommandFailure(new ArchiveCommand(), model, ArchiveCommand.MESSAGE_FAILURE);
    }

    @Test
    public void directoryNameIsCorrectFormat() {
        assertTrue(ArchiveCommand.ARCHIVE_DIRECTORY_NAME_FORMAT.toPattern().matches("ddMMyy"));
    }

    @Test
    public void archivedFileNameIsCorrectFormat() {
        assertTrue(ArchiveCommand.ARCHIVE_FILE_NAME_FORMAT.toPattern().matches("ddMMyy_HHmmssSSS"));
    }

    @Test
    public void test_archiveCommandWordIsCorrect() {
        assertTrue(ArchiveCommand.COMMAND_WORD.equals("archive"));
    }

}
