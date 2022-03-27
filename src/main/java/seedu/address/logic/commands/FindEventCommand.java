package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_NONEXISTENT_COMPANY;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EVENTS;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.model.Model;
import seedu.address.model.entry.*;
import seedu.address.model.entry.Date;
import seedu.address.model.tag.Tag;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Finds and lists all events in address book whose companyName contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindEventCommand extends Command {

    public static final String COMMAND_WORD = "finde";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all events whose companyName contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " shopee";

    public static final String MESSAGE_NOT_QUERIED = "At least one field to find must be provided.";

    private final ArgumentMultimap argumentMultimap;

//    /**
//     * @param findEventDescriptor details to find the event with
//     */
    public FindEventCommand(ArgumentMultimap argumentMultimap) {
        requireNonNull(argumentMultimap);
        this.argumentMultimap = argumentMultimap;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String[] nameKeywords = argumentMultimap.getValue(PREFIX_NAME).orElse("").split("\\s+");
        String[] companyNameKeywords = argumentMultimap.getValue(PREFIX_COMPANY).orElse("").split("\\s+");
        String[] dateKeywords = argumentMultimap.getValue(PREFIX_DATE).orElse("").split("\\s+");
        String[] timeKeywords = argumentMultimap.getValue(PREFIX_TIME).orElse("").split("\\s+");
        String[] tagKeywords = argumentMultimap.getValue(PREFIX_TAG).orElse("").split("\\s+");

        EventContainsKeywordsPredicate predicate = new EventContainsKeywordsPredicate(Arrays.asList(nameKeywords),
                Arrays.asList(companyNameKeywords),
                Arrays.asList(dateKeywords),
                Arrays.asList(timeKeywords),
                Arrays.asList(tagKeywords));

        model.updateFilteredEventList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW, model.getFilteredEventList().size()),
                false, false, false, false, true);
    }


//    @Override
//    public CommandResult execute(Model model) throws CommandException {
//        requireNonNull(model);
//
//        List<Event> lastShownList = model.getFilteredEventList();
//
//        if (index.getZeroBased() >= lastShownList.size()) {
//            throw new CommandException(Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
//        }
//
//        Event eventToEdit = lastShownList.get(index.getZeroBased());
//        Event editedEvent = createEditedEvent(eventToEdit, editEventDescriptor);
//
//        if (!eventToEdit.isSameEntry(editedEvent) && model.hasEvent(editedEvent)) {
//            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
//        }
//
//        if (!model.hasCompany(editedEvent.getCompanyName())) {
//            throw new CommandException(MESSAGE_NONEXISTENT_COMPANY);
//        }
//
//        model.setEvent(eventToEdit, editedEvent);
//        model.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
//        return new CommandResult(String.format(MESSAGE_EDIT_EVENT_SUCCESS, editedEvent));
//    }

//    private final CompanyNameContainsKeywordsPredicate predicate;
//
//    public FindEventCommand(CompanyNameContainsKeywordsPredicate predicate) {
//        this.predicate = predicate;
//    }

//    @Override
//    public CommandResult execute(Model model) {
//        requireNonNull(model);
//        model.updateFilteredEventList(predicate);
//        return new CommandResult(
//                String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW, model.getFilteredEventList().size()),
//                false, false, false, false, true);
//    }
//
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindEventCommand // instanceof handles nulls
                && argumentMultimap.equals(((FindEventCommand) other).argumentMultimap)); // state check
    }



//    /**
//     * Stores the details to find the event with. Each non-empty field value will decide which events to show.
//     */
//    public static class FindEventDescriptor {
//        private Name name;
//        private Name companyName;
//        private Date date;
//        private Time time;
//        private Location location;
//        private Set<Tag> tags;
//
//        public FindEventDescriptor() {
//        }
//
//        /**
//         * Copy constructor.
//         * A defensive copy of {@code tags} is used internally.
//         */
//        public FindEventDescriptor(FindEventCommand.FindEventDescriptor toCopy) {
//            setName(toCopy.name);
//            setCompanyName(toCopy.companyName);
//            setDate(toCopy.date);
//            setTime(toCopy.time);
//            setLocation(toCopy.location);
//            setTags(toCopy.tags);
//        }
//
//        /**
//         * Returns true if at least one field is queried by user.
//         */
//        public boolean isAnyFieldQueried() {
//            return CollectionUtil.isAnyNonNull(name, companyName, date, time, location, tags);
//        }
//
//        public void setName(Name name) {
//            this.name = name;
//        }
//
//        public Optional<Name> getName() {
//            return Optional.ofNullable(name);
//        }
//
//        public void setCompanyName(Name companyName) {
//            this.companyName = companyName;
//        }
//
//        public Optional<Name> getCompanyName() {
//            return Optional.ofNullable(companyName);
//        }
//
//        public void setDate(Date date) {
//            this.date = date;
//        }
//
//        public Optional<Date> getDate() {
//            return Optional.ofNullable(date);
//        }
//
//        public void setTime(Time time) {
//            this.time = time;
//        }
//
//        public Optional<Time> getTime() {
//            return Optional.ofNullable(time);
//        }
//
//        public void setLocation(Location location) {
//            this.location = location;
//        }
//
//        public Optional<Location> getLocation() {
//            return Optional.ofNullable(location);
//        }
//
//        /**
//         * Sets {@code tags} to this object's {@code tags}.
//         * A defensive copy of {@code tags} is used internally.
//         */
//        public void setTags(Set<Tag> tags) {
//            this.tags = (tags != null) ? new HashSet<>(tags) : null;
//        }
//
//        /**
//         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
//         * if modification is attempted.
//         * Returns {@code Optional#empty()} if {@code tags} is null.
//         */
//        public Optional<Set<Tag>> getTags() {
//            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
//        }
//
//        @Override
//        public boolean equals(Object other) {
//            // short circuit if same object
//            if (other == this) {
//                return true;
//            }
//
//            // instanceof handles nulls
//            if (!(other instanceof FindEventCommand.FindEventDescriptor)) {
//                return false;
//            }
//
//            // state check
//            FindEventCommand.FindEventDescriptor e = (FindEventCommand.FindEventDescriptor) other;
//
//            return getName().equals(e.getName())
//                    && getCompanyName().equals(e.getCompanyName())
//                    && getDate().equals(e.getDate())
//                    && getTime().equals(e.getTime())
//                    && getLocation().equals(e.getLocation())
//                    && getTags().equals(e.getTags());
//        }
//    }
}
