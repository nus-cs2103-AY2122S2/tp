package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_PERSON_DOES_NOT_EXIST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;



public class ShowFriendCommand extends Command {

    public static final String COMMAND_WORD = "showfriend";

    public static final String MESSAGE_USAGE = "COMMAND_WORD : Shows full details of a friend in"
            + " the address book. "
            + "Parameters: "
            + PREFIX_NAME + " NAME \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe";


    public static final String MESSAGE_SUCCESS = "Details of %1$s shown below";

    private final Person toShow;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public ShowFriendCommand(Person person) {
        requireNonNull(person);
        toShow = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasPerson(toShow)) {
            throw new CommandException(MESSAGE_PERSON_DOES_NOT_EXIST);
        }

        //updates UI to only show a single person
        model.updateFilteredPersonList(x -> x.isSamePerson(toShow));

        return new CommandResult(String.format(MESSAGE_SUCCESS, toShow.getName()), false,
                false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShowFriendCommand // instanceof handles nulls
                && toShow.isSamePerson(((ShowFriendCommand) other).toShow));
    }
}
