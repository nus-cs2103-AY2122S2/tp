package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_SUCCESS = "Sorted all pets!";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the pet list by the column specified.\n"
            + "Columns that can be sorted: OwnerName (o), Name (n) \n"
            + "Parameters: FIRST LETTER OF COLUMN NAME \n"
            + "Example: " + COMMAND_WORD + "o";

    private final String columnName;

    /**
     * @param columnName first letter of the column to sort the pet list by
     */
    public SortCommand(String columnName) {
        this.columnName = columnName;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortPetList(columnName);
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
