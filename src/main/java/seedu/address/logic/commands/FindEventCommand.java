package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.Model;
import seedu.address.model.event.DateTime;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.event.Information;
import seedu.address.model.person.Name;


import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public class FindEventCommand extends Command{
    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all events whose details contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " name/lunch appointment";

    public static final String MESSAGE_NO_PARAMETERS = "At least one field must be provided.";

    private final Predicate<Event> predicate;

    public FindEventCommand(Predicate<Event> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredEventList(predicate);

        return new CommandResult(
                String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW, model.getFilteredEventList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindEventCommand // instanceof handles nulls
                && predicate.equals(((FindEventCommand) other).predicate)); // state check
    }

    /**
     * Stores the details to find an event with. Each non-empty field value will
     * be added to the predicate to filter the contact list
     */
    public static class FindEventDescriptor {
        private List<EventName> names;
        private List<Information> informations;
        private List<Name> participants;
        private List<DateTime> dateTimes;

        public FindEventDescriptor() {
        }

        /**
         * Copy constructor.
         */
        public FindEventDescriptor(FindEventCommand.FindEventDescriptor toCopy) {
            setNames(toCopy.names);
            setInformations(toCopy.informations);
            setParticipants(toCopy.participants);
            setDateTimes(toCopy.dateTimes);
        }

        /**
         * Returns true if at least one field is searched for.
         */
        public boolean isAnyFieldPresent() {
            return CollectionUtil.isAnyNonNull(names, informations, participants, dateTimes);
        }

        public void setNames(List<EventName> names) {
            this.names = names;
        }

        public Optional<List<EventName>> getNames() {
            return Optional.ofNullable(names);
        }

        public Optional<List<String>> getStringNames() {
            return getNames().map(names ->
                    names.stream().map(name -> name.value).collect(Collectors.toList()));
        }

        public void setInformations(List<Information> informations) {
            this.informations = informations;
        }

        public Optional<List<Information>> getInformations() {
            return Optional.ofNullable(informations);
        }

        public Optional<List<String>> getStringInformations() {
            return getInformations().map(list ->
                    list.stream().map(info -> info.value).collect(Collectors.toList()));
        }

        public void setParticipants(List<Name> participants) {
            this.participants = participants;
        }

        public Optional<List<Name>> getParticipants() {
            return Optional.ofNullable(participants);
        }

        public Optional<List<String>> getStringParticipants() {
            return getParticipants().map(list ->
                    list.stream().map(participants -> participants.fullName).collect(Collectors.toList()));
        }

        public void setDateTimes(List<DateTime> dateTimes) {
            this.dateTimes = dateTimes;
        }

        public Optional<List<DateTime>> getDateTimes() {
            return Optional.ofNullable(dateTimes);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof FindEventCommand.FindEventDescriptor)) {
                return false;
            }

            // state check
            FindEventCommand.FindEventDescriptor e = (FindEventCommand.FindEventDescriptor) other;

            return getNames().equals(e.getNames())
                    && getNames().equals(e.getInformations())
                    && getInformations().equals(e.getParticipants());
        }
    }
}
