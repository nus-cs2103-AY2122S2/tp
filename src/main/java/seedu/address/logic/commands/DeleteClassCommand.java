package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_TUTORIAL_NOT_FOUND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALNAME;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Displayable;
import seedu.address.model.Model;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.model.tutorial.TutorialName;

/**
 * Deletes a class identified using it's displayed index from the address book, or using its tutorial name.
 */
public class DeleteClassCommand extends Command {

    public static final String COMMAND_WORD = "delete_class";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the class identified by the index number used in the displayed class list,"
            + "or deletes the class identified by tutorial name.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + PREFIX_TUTORIALNAME + "TUTORIAL NAME \n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TUTORIAL_SUCCESS = "Deleted Class: %1$s";
    public static final String MESSAGE_INDEX_USAGE = "Try listing a class e.g. list_class";

    private final Index targetIndex;
    private final TutorialName tutorialName;
    private final boolean isDeleteByTutorialName;

    /**
     * Constructor for a DeleteClassCommand.
     * @param targetIndex The index of the class in the list.
     * @param tutorialName The name of the class.
     * @param isDeleteByTutorialName To choose whether to delete by name or index.
     */
    public DeleteClassCommand(Index targetIndex, TutorialName tutorialName, boolean isDeleteByTutorialName) {
        this.targetIndex = targetIndex;
        this.tutorialName = tutorialName;
        this.isDeleteByTutorialName = isDeleteByTutorialName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!isDeleteByTutorialName) {
            return deletebyIndex(model);
        } else {
            return deleteByTutorialName(model);
        }
    }

    private CommandResult deleteByTutorialName(Model model) throws CommandException {
        Tutorial tutorialToDelete = null;
        for (Tutorial tutorial : model.getAddressBook().getTutorialList()) {
            if (tutorial.getTutorialName().equals(tutorialName)) {
                tutorialToDelete = tutorial;
                break;
            }
        }

        if (tutorialToDelete == null) {
            throw new CommandException(MESSAGE_TUTORIAL_NOT_FOUND);
        } else {
            model.deleteTutorial(tutorialToDelete);
            return CommandResult.createClassCommandResult(String.format(MESSAGE_DELETE_TUTORIAL_SUCCESS,
                    tutorialToDelete));
        }
    }

    private CommandResult deletebyIndex(Model model) throws CommandException {
        List<Displayable> lastShownList = model.getLastShownList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TUTORIAL_DISPLAYED_INDEX);
        }

        if (!(lastShownList.get(targetIndex.getZeroBased()) instanceof Tutorial)) {
            throw new CommandException(Messages.MESSAGE_INDEX_LIST_MISMATCH + MESSAGE_INDEX_USAGE);
        }

        Tutorial tutorialToDelete = (Tutorial) lastShownList.get(targetIndex.getZeroBased());
        model.deleteTutorial(tutorialToDelete);
        return CommandResult.createClassCommandResult(String.format(MESSAGE_DELETE_TUTORIAL_SUCCESS, tutorialToDelete));
    }

    @Override
    public boolean equals(Object other) {
        if (isDeleteByTutorialName) {
            return other == this // short circuit if same object
                    || (other instanceof DeleteClassCommand // instanceof handles nulls
                    && tutorialName.equals(((DeleteClassCommand) other).tutorialName)); // state check
        } else {
            return other == this // short circuit if same object
                    || (other instanceof DeleteClassCommand // instanceof handles nulls
                    && targetIndex.equals(((DeleteClassCommand) other).targetIndex)); // state check
        }
    }
}

