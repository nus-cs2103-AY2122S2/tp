package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.FriendName;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends ByIndexByNameCommand {

    public static final String COMMAND_WORD = "deletefriend";
    public static final String COMMAND_ALIAS = "df";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " / " + COMMAND_ALIAS
            + ": Deletes the friend identified by index or name.\n"
            + "Parameters: "
            + " INDEX ? "
            + PREFIX_NAME + "NAME \n"
            + "Example 1: " + COMMAND_WORD + " 1" + "\n"
            + "Example 2: " + COMMAND_WORD + " " + PREFIX_NAME + "John Doe\n";


    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted friend: %1$s";

    private final FriendName nameOfPersonToDelete;
    private final Index targetIndex;
    private final boolean isDeletionByIndex;

    /**
     * Constructs a DeleteCommand for deletion by name
     *
     * @param nameOfPersonToDelete The name of the person to be deleted
     */
    public DeleteCommand(FriendName nameOfPersonToDelete) {
        this.nameOfPersonToDelete = nameOfPersonToDelete;
        this.targetIndex = null;
        this.isDeletionByIndex = false;
    }

    /**
     * Constructs a DeleteCommand for deletion by index
     *
     * @param targetIndex The index of the person to be deleted on the filtered list on GUI
     */
    public DeleteCommand(Index targetIndex) {
        this.nameOfPersonToDelete = null;
        this.targetIndex = targetIndex;
        this.isDeletionByIndex = true;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        requireNonNull(model);
        Person personToDelete;

        if (isDeletionByIndex) { //deletion by index
            personToDelete = this.getPersonByFilteredIndex(model, targetIndex);

        } else { //deletion by name
            personToDelete = this.getPersonByName(model, nameOfPersonToDelete);
        }
        assert(personToDelete != null);
        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof DeleteCommand) {
            DeleteCommand otherDeleteCommand = (DeleteCommand) other;
            if (otherDeleteCommand.isDeletionByIndex && this.isDeletionByIndex) {
                //assertion to ensure that if it is deletion by index, then targetIndex will not be null
                assert(otherDeleteCommand.targetIndex != null && this.targetIndex != null);
                return otherDeleteCommand.targetIndex.equals(this.targetIndex);
            } else if (!otherDeleteCommand.isDeletionByIndex && !this.isDeletionByIndex) {
                //assertion to ensure that if it is deletion by name, then name will not be null
                assert(otherDeleteCommand.nameOfPersonToDelete != null && this.nameOfPersonToDelete != null);
                return otherDeleteCommand.nameOfPersonToDelete.equals(this.nameOfPersonToDelete);
            }
        }
        return false;
    }
}
