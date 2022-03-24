package seedu.contax.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.contax.logic.commands.exceptions.CommandException;
import seedu.contax.model.Model;
import seedu.contax.model.tag.Tag;


/**
 * Adds a tag to the address book.
 */
public class AddTagCommand extends Command {
    public static final String COMMAND_WORD = "addtag";

    public static final String MESSAGE_USAGE = "`" + COMMAND_WORD + "` : **Adds a tag in ContaX.** "
            + "Parameters: *" + PREFIX_NAME + "TAGNAME* \n"
            + "Example: `" + COMMAND_WORD + " " + PREFIX_NAME + "Potential Clients`";

    public static final String MESSAGE_SUCCESS = "New tag added: %s";
    public static final String MESSAGE_DUPLICATE_TAG = "This tag already exists in the address book";

    private final Tag toAdd;

    /**
     * Creates an AddTagCommand to add teh specified {@code Tag}.
     * @param tag The specified tag to be added.
     */
    public AddTagCommand(Tag tag) {
        requireNonNull(tag);
        toAdd = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTag(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TAG);
        }

        // Add tag to the model
        model.addTag(toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof AddTagCommand)) {
            return false;
        }

        return ((AddTagCommand) o).toAdd.equals(toAdd);
    }
}
