package woofareyou.logic.commands;

import static woofareyou.logic.commands.CommandTestUtil.assertCommandFailure;
import static woofareyou.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import woofareyou.model.Model;
import woofareyou.model.ModelManager;
import woofareyou.model.UserPrefs;
import woofareyou.model.pet.Pet;
import woofareyou.testutil.PetBuilder;
import woofareyou.testutil.TypicalPets;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalPets.getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPet_success() {
        Pet validPet = new PetBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPet(validPet);

        assertCommandSuccess(new AddCommand(validPet), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validPet), expectedModel);
    }

    @Test
    public void execute_duplicatePet_throwsCommandException() {
        Pet petInList = model.getAddressBook().getPetList().get(0);
        assertCommandFailure(new AddCommand(petInList), model, AddCommand.MESSAGE_DUPLICATE_PET);
    }

}
