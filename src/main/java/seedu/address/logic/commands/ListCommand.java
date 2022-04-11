package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EXPENSES;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Lists all expenses in the expense expert to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all expenses";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all expenses\n" + "Example: "
            + COMMAND_WORD + "\n\n";

    private static final String INVALID_COMMAND_USAGE = "Invalid command usage";

    private final String commandWordArg;

    public ListCommand(String commandWordArg) {
        this.commandWordArg = commandWordArg;
    }

    /**
     * Converts the observable list into a string.
     * @param list the list to be converted to a string.
     * @return the converted string.
     */
    public String observableToString(ObservableList<Person> list) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 1;
        if (list.size() == 0) {
            return ("No person who owes you money can be found");
        }
        stringBuilder.append("People Who Owe You Money:\n\n");
        for (Person p: list) {
            String string = p.toString();
            String name = string.split(";")[0];
            String amount = string.split(":")[1].split(" ")[1];
            stringBuilder.append(i).append(". ").append(name).append(": $").append(amount).append("\n");
            i++;
        }
        return stringBuilder.toString();
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredExpenseList(PREDICATE_SHOW_ALL_EXPENSES);
        if (commandWordArg.trim().isEmpty()) {
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            switch (commandWordArg.trim()) {
            case PersonOwesCommand.COMMAND_WORD:
                model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
                return new CommandResult(observableToString(model.getFilteredPersonList()));


            default:
                throw new CommandException(INVALID_COMMAND_USAGE);
            }
        }
    }
}

