package seedu.address.logic.commands.schedule;

import seedu.address.logic.commands.Command;

public abstract class ScheduleCommand extends Command {
    public static final String COMMAND_WORD = "schedule";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + "add: CANDIDATE/ AT/"
            + "edit: INDEX "
            + "delete: INDEX";

    public static final String MESSAGE_INVALID_FORMAT_DATETIME =
            "Date and/or Time is not in the following format: dd-MM-yyyy HH:mm";

    public static final String MESSAGE_INVALID_PAST_DATETIME =
            "Date and/or Time must not be in the past!";
}
