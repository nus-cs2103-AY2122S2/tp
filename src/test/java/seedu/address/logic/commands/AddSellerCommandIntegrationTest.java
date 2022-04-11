package seedu.address.logic.commands;

import static seedu.address.logic.commands.SellerCommandTestUtil.assertCommandSuccess;
//import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.BuyerAddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.SellerAddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.seller.Seller;
import seedu.address.testutil.SellerBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddSellerCommand}.
 */
public class AddSellerCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new UserPrefs(), new SellerAddressBook(),
                new BuyerAddressBook());
    }

    @Test
    public void execute_newSeller_success() {
        Seller validSeller = new SellerBuilder().build();

        Model expectedModel = new ModelManager(new UserPrefs(), model.getSellerAddressBook(),
                model.getBuyerAddressBook());
        expectedModel.addSeller(validSeller);

        assertCommandSuccess(new AddSellerCommand(validSeller), model,
                String.format(AddSellerCommand.MESSAGE_SUCCESS, validSeller), expectedModel);
    }

}

