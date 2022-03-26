package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds/removes a person as a potential team member using its index in HackNet.
 */
public class MakeTeamCommand extends Command {

    /**
     * Actions supported by a {@code MakeTeamCommand}
     */
    public enum TeamAction {
        ADD,
        REMOVE,
    }

    public static final String COMMAND_WORD_ADD = "team";
    public static final String COMMAND_WORD_REMOVE = "unteam";

    public static final String MESSAGE_USAGE = COMMAND_WORD_ADD + "/" + COMMAND_WORD_REMOVE
            + ": Adds/removes the person as a potential teammate by the index number in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD_ADD + " 1";

    public static final String MESSAGE_ADD_TEAMMATE_SUCCESS = "Added person as potential teammate: %1$s";
    public static final String MESSAGE_REMOVE_TEAMMATE_SUCCESS = "Remove person from potential teammate: %1$s";
    public static final String MESSAGE_INVALID_TEAM_ACTION = "Invalid team action for command";
    public static final String MESSAGE_REDUNDANT_ADDING = "Person is already a potential teammate!";
    public static final String MESSAGE_REDUNDANT_REMOVAL = "Person is not part of your potential team!";

    private static final Logger logger = LogsCenter.getLogger(MakeTeamCommand.class);

    private final Index targetIndex;
    private final TeamAction action;

    /**
     * Constructs a MakeTeamCommand.
     *
     * @param targetIndex Index of person to execute command on.
     * @param action Action to perform when executing the command.
     */
    public MakeTeamCommand(Index targetIndex, TeamAction action) {
        this.targetIndex = targetIndex;
        this.action = action;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getDisplayPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToUpdate = lastShownList.get(targetIndex.getZeroBased());
        Person updatedPerson;
        String successMessage;
        switch (action) {
        case ADD:
            if (personToUpdate.isPotentialTeammate()) {
                // Attempting to add someone who is already a potential teammate
                throw new CommandException(MESSAGE_REDUNDANT_ADDING);
            }
            updatedPerson = createNewTeamPerson(personToUpdate, true);
            successMessage = MESSAGE_ADD_TEAMMATE_SUCCESS;
            break;
        case REMOVE:
            if (!personToUpdate.isPotentialTeammate()) {
                // Attempting to remove a non-potential teammate from team
                throw new CommandException(MESSAGE_REDUNDANT_REMOVAL);
            }
            updatedPerson = createNewTeamPerson(personToUpdate, false);
            successMessage = MESSAGE_REMOVE_TEAMMATE_SUCCESS;
            break;
        default:
            logger.warning("Invalid enum value: " + action.toString());
            throw new CommandException(MESSAGE_INVALID_TEAM_ACTION);
        }
        model.setPerson(personToUpdate, updatedPerson);
        model.commitAddressBook();
        return new CommandResult(String.format(successMessage, personToUpdate));
    }

    /**
     * Returns a new {@code Person} with the same fields, with its potential teammate field
     * set to the value of {@code isPotentialTeammate}.
     *
     * @param person Person to edit.
     * @param isPotentialTeammate Boolean value to set for isPotentialTeammate field of Person.
     */
    private static Person createNewTeamPerson(Person person, boolean isPotentialTeammate) {
        assert person != null : "Person to update should not be null";

        return new Person(person.getName(), person.getPhone(), person.getEmail(), person.getGithubUsername(),
                person.getTeams(), person.getSkillSet(), isPotentialTeammate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MakeTeamCommand // instanceof handles nulls
                && targetIndex.equals(((MakeTeamCommand) other).targetIndex) // same index
                && action.equals(((MakeTeamCommand) other).action)); // same action
    }
}
