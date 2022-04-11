package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;

public class CreateTagCommand extends Command implements DetailedViewExecutable {
    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Creates a tag with a given tag name.\n"
            + "Parameters: TAG_NAME (case insensitive)\n"
            + "Example: " + COMMAND_WORD + " Friends";

    public static final String MESSAGE_CREATE_TAG_SUCCESS = "Created tag: %1$s";
    public static final String MESSAGE_DUPLICATE_TAG = "This tag already exists in the address book.";

    private final String tagName;

    /**
     * Creates a CreateTagCommand to add to {@code UniqueTagList}.
     * @param tagName the name of the Tag.
     */
    public CreateTagCommand(String tagName) {
        requireNonNull(tagName);
        this.tagName = tagName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Tag createdTag = new Tag(this.tagName);
        if (model.hasTag(createdTag)) {
            throw new CommandException(MESSAGE_DUPLICATE_TAG);
        }
        model.addTag(createdTag);
        return new CommandResult(String.format(MESSAGE_CREATE_TAG_SUCCESS, this.tagName));
    }

    @Override
    public CommandResult executeInDetailedView(Model model) throws CommandException {
        requireNonNull(model);
        Tag createdTag = new Tag(this.tagName);
        if (model.hasTag(createdTag)) {
            throw new CommandException(MESSAGE_DUPLICATE_TAG);
        }
        model.addTag(createdTag);
        return new CommandResult(String.format(MESSAGE_CREATE_TAG_SUCCESS, this.tagName),
                CommandResult.SpecialCommandResult.DETAILED_VIEW);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CreateTagCommand // instanceof handles nulls
                && tagName.equals(((CreateTagCommand) other).tagName));
    }
}
