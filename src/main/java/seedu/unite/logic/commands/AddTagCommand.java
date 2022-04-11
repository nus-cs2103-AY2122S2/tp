package seedu.unite.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.unite.logic.commands.exceptions.CommandException;
import seedu.unite.model.Model;
import seedu.unite.model.tag.Tag;

/**
 * Adds a tag into UNite
 */
public class AddTagCommand extends Command {

    public static final String COMMAND_WORD = "add_tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a tag to the UNite. "
            + "Parameters: "
            + PREFIX_TAG + "TAG...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TAG + "friends ";

    public static final String MESSAGE_SUCCESS = "New tag added: %1$s";
    public static final String MESSAGE_DUPLICATE_TAG = "This tag already exists in the UNite";

    private static Logger logger = Logger.getLogger("AddTag");

    private final Tag toAdd;

    /**
     * Creates an AddTagCommand to add the specified {@code Tag}
     */
    public AddTagCommand(Tag tag) {
        requireNonNull(tag);
        toAdd = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTag(toAdd)) {
            logger.log(Level.WARNING, "Add tag error: duplicate tag");
            throw new CommandException(MESSAGE_DUPLICATE_TAG);
        }

        model.addTag(toAdd);
        model.showTagList();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTagCommand // instanceof handles nulls
                && toAdd.equals(((AddTagCommand) other).toAdd));
    }
}
