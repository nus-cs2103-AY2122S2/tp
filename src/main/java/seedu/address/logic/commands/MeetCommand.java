package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.HustleBook;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.ScheduledMeeting;

public class MeetCommand extends Command {

    public static final String COMMAND_WORD = "meet";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Schedule meeting with the person identified by the name used in HustleBook.\n"
            + "Parameters: NAME (must be found in HustleBook), DATE (YYYY-MM-DD format), TIME (24HR Format)\n"
            + "Example: " + COMMAND_WORD + " NAME" + " m/2022-02-23" + " t/1530";

    public static final String MESSAGE_SCHEDULE_MEETING_PERSON_SUCCESS = "Scheduled a meeting: %1$s";

    private final Name targetName;
    private final ScheduledMeeting scheduledMeeting;

    /**
     * @param targetName Name of person whose to schedule a meeting with.
     * @param scheduledMeeting Meeting to be scheduled.
     */
    public MeetCommand(Name targetName, ScheduledMeeting scheduledMeeting) {
        this.targetName = targetName;
        this.scheduledMeeting = scheduledMeeting;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        HustleBook tempHustleBook = new HustleBook();
        Index targetIndex = tempHustleBook.getPersonListIndex(lastShownList, targetName);

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToScheduleMeeting = lastShownList.get(targetIndex.getZeroBased());
        model.scheduleMeetingPerson(personToScheduleMeeting, scheduledMeeting);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SCHEDULE_MEETING_PERSON_SUCCESS, personToScheduleMeeting));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetCommand // instanceof handles nulls
                && targetName.equals(((MeetCommand) other).targetName)); // state check
    }

}
