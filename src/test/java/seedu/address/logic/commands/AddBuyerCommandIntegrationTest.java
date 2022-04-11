package seedu.address.logic.commands;

import static seedu.address.logic.commands.BuyerCommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.BuyerAddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.SellerAddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.buyer.Buyer;
import seedu.address.testutil.BuyerBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddBuyerCommand}.
 */
public class AddBuyerCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new UserPrefs(), new SellerAddressBook(),
                new BuyerAddressBook());
    }

    @Test
    public void execute_newBuyer_success() {
        Buyer validBuyer = new BuyerBuilder().build();

        Model expectedModel = new ModelManager(new UserPrefs(), new SellerAddressBook(),
                new BuyerAddressBook());
        expectedModel.addBuyer(validBuyer);

        assertCommandSuccess(new AddBuyerCommand(validBuyer), model,
                String.format(AddBuyerCommand.MESSAGE_SUCCESS, validBuyer), expectedModel);
    }

}

