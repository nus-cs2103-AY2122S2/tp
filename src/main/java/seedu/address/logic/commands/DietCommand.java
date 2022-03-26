package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PETS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.pet.Diet;
import seedu.address.model.pet.Pet;


/**
 * Adds diet details to a pet identified using its displayed index from the address book.
 */
public class DietCommand extends Command {

    public static final String COMMAND_WORD = "diet";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the diet of the pet identified "
            + "by the index number used in the last pet listing. "
            + "Existing diet will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "d/ [diet]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "d/ No seafood.";

    public static final String MESSAGE_ADD_DIET_SUCCESS = "Added diet to Pet: %1$s";
    public static final String MESSAGE_DELETE_DIET_SUCCESS = "Removed diet from Pet: %1$s";
    private final Index index;
    private final Diet diet;

    /**
     * @param index of the pet in the filtered pets list to edit the diet
     * @param diet of the pet to be updated to
     */
    public DietCommand(Index index, Diet diet) {
        requireAllNonNull(index, diet);

        this.index = index;
        this.diet = diet;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Pet> lastShownList = model.getFilteredPetList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
        }

        Pet petToEdit = lastShownList.get(index.getZeroBased());
        Pet editedPet = new Pet(
                petToEdit.getName(), petToEdit.getOwnerName(), petToEdit.getPhone(),
                petToEdit.getAddress(), petToEdit.getTags(), diet, petToEdit.getAppointment(),
                petToEdit.getAttendanceHashMap());

        model.setPet(petToEdit, editedPet);
        model.updateFilteredPetList(PREDICATE_SHOW_ALL_PETS);

        return new CommandResult(generateSuccessMessage(editedPet));
    }

    /**
     * Generates a command execution success message based on whether
     * the diet is added to or removed from
     * {@code petToEdit}.
     */
    private String generateSuccessMessage(Pet petToEdit) {
        String message = !diet.value.isEmpty() ? MESSAGE_ADD_DIET_SUCCESS : MESSAGE_DELETE_DIET_SUCCESS;
        return String.format(message, petToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DietCommand)) {
            return false;
        }

        // state check
        DietCommand e = (DietCommand) other;
        return index.equals(e.index)
                && diet.equals(e.diet);
    }
}
