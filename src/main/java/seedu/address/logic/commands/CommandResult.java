package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.logic.CommandRemark;


/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    private final CommandRemark commandRemark;


    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, CommandRemark commandRemark) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.commandRemark = commandRemark;
        if (commandRemark != CommandRemark.HISTORY) {
            CommandList.getList().resetPointer();
        }
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, CommandRemark.UI_IRRELEVANT);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return commandRemark == CommandRemark.HELP;
    }

    public boolean isExit() {
        return commandRemark == CommandRemark.EXIT;
    }

    public boolean isHistory() {
        return commandRemark == CommandRemark.HISTORY;
    }


    /**
     * Gets what need to be filled in.
     * @return text.
     */
    public String getNewCommandTextField() {
        String text = "";
        if (isHistory()) {
            text = CommandList.getList().getCurrentCommand();
        }
        return text;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && commandRemark == ((CommandResult) other).commandRemark;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, commandRemark);
    }

}
