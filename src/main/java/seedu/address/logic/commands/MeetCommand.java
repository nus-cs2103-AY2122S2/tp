package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Flag;
import seedu.address.model.person.Info;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.PrevDateMet;
import seedu.address.model.person.Salary;
import seedu.address.model.person.ScheduledMeeting;
import seedu.address.model.tag.Tag;

public class MeetCommand extends Command {

    public static final String COMMAND_WORD = "meet";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Schedule meeting with the client identified by the name used in HustleBook.\n"
            + "Parameters: NAME (must be found in HustleBook), DATE (YYYY-MM-DD format), TIME (24HR Format)\n"
            + "Example: " + COMMAND_WORD + " John Doe" + " d/2022-02-23" + " t/1530";

    public static final String MESSAGE_MEETING_CLASH = "A meeting clash is detected!";
    public static final String MESSAGE_SCHEDULE_MEETING_PERSON_SUCCESS = "Updated a meeting with %1$s";

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

        if (model.hasSameMeeting(scheduledMeeting)) {
            throw new CommandException(MeetCommand.MESSAGE_MEETING_CLASH);
        }

        List<Person> lastShownList = model.getFilteredPersonList();
        Index targetIndex = model.getPersonListIndex(targetName);

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToScheduleMeeting = lastShownList.get(targetIndex.getZeroBased());
        Person editedPerson = createMeetEditedPerson(personToScheduleMeeting, scheduledMeeting);
        model.setPerson(personToScheduleMeeting, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SCHEDULE_MEETING_PERSON_SUCCESS, personToScheduleMeeting));
    }

    /**
     * Creates and returns a {@code Person} with only the details of {@code scheduledMeeting}
     * edited.
     */
    private static Person createMeetEditedPerson(Person personToEdit, ScheduledMeeting scheduledMeeting) {
        assert personToEdit != null;

        Name personName = personToEdit.getName();
        Phone personPhone = personToEdit.getPhone();
        Email personEmail = personToEdit.getEmail();
        Address personAddress = personToEdit.getAddress();
        Flag personFlag = personToEdit.getFlag();
        Set<Tag> personTags = personToEdit.getTags();
        Salary personSalary = personToEdit.getSalary();
        Info personInfo = personToEdit.getInfo();
        PrevDateMet personPrevDateMet = personToEdit.getPrevDateMet();

        return new Person(personName, personPhone, personEmail, personAddress, personFlag,
                personTags, personPrevDateMet, personSalary, personInfo, scheduledMeeting);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetCommand // instanceof handles nulls
                && targetName.equals(((MeetCommand) other).targetName)); // state check
    }

}
