package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.ui.UiManager;
import seedu.address.ui.general.TagList;

/**
 * Clears the address book.
 */
public class AddTagCommand extends Command {

    public static final String COMMAND_WORD = "add_tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a tag to the address book. "
            + "Parameters: "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TAG + "friends ";

    public static final String MESSAGE_SUCCESS = "New tag added: %1$s";
    public static final String MESSAGE_DUPLICATE_TAG = "This tag already exists in the address book";

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

        TagList tagList = new TagList(model.getTagList());
        UiManager.getMainWindow().getGeneralDisplay().setTagList(tagList);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTagCommand // instanceof handles nulls
                && toAdd.equals(((AddTagCommand) other).toAdd));
    }
}
