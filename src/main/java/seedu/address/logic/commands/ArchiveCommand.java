package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.storage.JsonSerializableAddressBook;

/**
 * Saves a copy of the stored data, into a separate json file.
 */
public class ArchiveCommand extends Command {

    public static final String COMMAND_WORD = "archive";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Saves a copy of the stored data, "
            + "into a separate json file\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_ARCHIVE_SUCCESS = "File saved at: ";
    public static final String MESSAGE_CREATE_FAILURE = "Error in creating file!";
    public static final String MESSAGE_WRITE_FAILURE = "Error in writing to file!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Extract the current path and address book.
        Path currentPath = model.getAddressBookFilePath();
        ReadOnlyAddressBook currentAddressBook = model.getAddressBook();

        // Generate the new path according to current date and time.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH.mm.ss.SSSSSS");
        Path parentPath = currentPath.getParent();
        String dateTime = LocalDateTime.now().format(formatter);
        Path suffix = Paths.get(dateTime + ".json");
        Path newPath = parentPath.resolve(suffix);

        // Create a new .json file based on the new path above.
        try {
            FileUtil.createIfMissing(newPath);
        } catch (IOException e) {
            throw new CommandException(MESSAGE_CREATE_FAILURE);
        }

        // Write to the new file that we have created.
        try {
            JsonUtil.saveJsonFile(new JsonSerializableAddressBook(currentAddressBook), newPath);
        } catch (IOException e) {
            throw new CommandException(MESSAGE_WRITE_FAILURE);
        }

        return new CommandResult(MESSAGE_ARCHIVE_SUCCESS + newPath.toString());
    }
}
