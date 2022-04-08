package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.time.LocalDate;
import java.util.function.Predicate;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventDateIsAfterPredicate;
import seedu.address.model.event.EventFriendNamesContainSubstringPredicate;
import seedu.address.model.person.FriendName;
import seedu.address.model.person.Person;



public class ShowFriendCommand extends ByIndexByNameCommand {

    public static final String COMMAND_WORD = "showfriend";
    public static final String COMMAND_ALIAS = "sf";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows full details of a friend in"
            + " the address book. "
            + "Parameters: "
            + "INDEX ? "
            + PREFIX_NAME + " NAME \n"
            + "Example 1: " + COMMAND_WORD + " 1 \n"
            + "Example 2: " + COMMAND_WORD + " " + PREFIX_NAME + "John Doe\n";


    public static final String MESSAGE_SUCCESS = "Details of %1$s shown below";

    private final FriendName nameOfPersonToShow;
    private final Index targetIndex;
    private final boolean isShowByIndex;

    /**
     * Constructs a ShowFriend for show by name
     *
     * @param nameOfPersonToShow The name of the person to be shown.
     */
    public ShowFriendCommand(FriendName nameOfPersonToShow) {
        this.nameOfPersonToShow = nameOfPersonToShow;
        this.targetIndex = null;
        this.isShowByIndex = false;
    }

    /**
     * Constructs a ShowFriendCommand for show by index.
     *
     * @param targetIndex The index of the person to be shown on the expanded person card on GUI.
     */
    public ShowFriendCommand(Index targetIndex) {
        this.nameOfPersonToShow = null;
        this.targetIndex = targetIndex;
        this.isShowByIndex = true;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person personToShow;

        if (isShowByIndex) {
            personToShow = this.getPersonByFilteredIndex(model, targetIndex);
        } else { //show by name
            personToShow = this.getPersonByName(model, nameOfPersonToShow);
        }
        assert(personToShow != null);


        //updates UI to only show a single person
        model.updateFilteredPersonList(x -> x.isSamePerson(personToShow));

        // updates UI to show upcoming events tied to this person
        // TODO: Known bug, will show upcoming events for today that have already passed...
        Predicate<Event> upcomingEventPredicate = new EventDateIsAfterPredicate(LocalDate.now());
        Predicate<Event> friendPredicate = new EventFriendNamesContainSubstringPredicate(personToShow.getName());

        model.updateFilteredEventList(event -> upcomingEventPredicate.test(event) && friendPredicate.test(event));

        return new CommandResult(String.format(MESSAGE_SUCCESS, personToShow.getName()), false,
                false, false, true, false);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof ShowFriendCommand) {
            ShowFriendCommand otherShowFriendCommand = (ShowFriendCommand) other;
            if (otherShowFriendCommand.isShowByIndex && this.isShowByIndex) {
                //assertion to ensure that if it is show by index, then targetIndex will not be null
                assert(otherShowFriendCommand.targetIndex != null && this.targetIndex != null);
                return otherShowFriendCommand.targetIndex.equals(this.targetIndex);
            } else if (!otherShowFriendCommand.isShowByIndex && !this.isShowByIndex) {
                //assertion to ensure that if it is show by name, then name will not be null
                assert(otherShowFriendCommand.nameOfPersonToShow != null && this.nameOfPersonToShow != null);
                return otherShowFriendCommand.nameOfPersonToShow.equals(this.nameOfPersonToShow);
            }
        }
        return false;
    }
}
