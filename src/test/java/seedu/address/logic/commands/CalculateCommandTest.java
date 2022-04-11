package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_CALCULATE_COST_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.DESC_REPAIR;
import static seedu.address.logic.commands.CommandTestUtil.DESC_REPLACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BURGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COST_REPAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COST_REPLACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_REPLACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BURGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BURGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BURGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLAN_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLAN_BURGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FASTFOOD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TECH;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.datewithouttime.DateWithoutTime;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;
import seedu.address.model.procedure.Procedure;
import seedu.address.testutil.ClientBuilder;
import seedu.address.testutil.ProcedureBuilder;



public class CalculateCommandTest {

    private String noCost = "0";
    private Model newModel;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        newModel = new ModelManager();
        expectedModel = new ModelManager(new AddressBook(newModel.getAddressBook()), new UserPrefs());

        // client 1 has 2 procedures that are on different days 21/04/2022 and 18/03/2022
        List<Procedure> client1Proc = new ArrayList<>();
        client1Proc.add(DESC_REPLACE);
        client1Proc.add(DESC_REPAIR);

        // client 2 has 2 procedures that are on same day -- 18/03/2022
        List<Procedure> client2Proc = new ArrayList<>();
        client2Proc.add(DESC_REPLACE);
        Procedure repairWithReplaceDate = new ProcedureBuilder(DESC_REPAIR)
                .withDate(VALID_DATE_REPLACE).build();
        client2Proc.add(repairWithReplaceDate);

        Client firstSampleClient = new ClientBuilder().withName(VALID_NAME_APPLE).withPhone(VALID_PHONE_APPLE)
                .withEmail(VALID_EMAIL_APPLE).withAddress(VALID_ADDRESS_APPLE).withPlan(VALID_PLAN_APPLE)
                .withTags(VALID_TAG_TECH).withProcedures(client1Proc).build();

        Client secondSampleClient = new ClientBuilder().withName(VALID_NAME_BURGER).withPhone(VALID_PHONE_BURGER)
                .withEmail(VALID_EMAIL_BURGER).withAddress(VALID_ADDRESS_BURGER).withPlan(VALID_PLAN_BURGER)
                .withTags(VALID_TAG_FASTFOOD).withProcedures(client2Proc).build();

        newModel.addClient(firstSampleClient);
        newModel.addClient(secondSampleClient);

        expectedModel.addClient(firstSampleClient);
        expectedModel.addClient(secondSampleClient);
    }

    // executing calculate on a date with only 1 procedure
    @Test
    public void execute_dateWith1Procedure_success() {
        CalculateCommand calculateCommand = new CalculateCommand(new DateWithoutTime("21/04/2022"));
        String totalCostString = '$' + VALID_COST_REPAIR;
        String expectedMessage = String.format(MESSAGE_CALCULATE_COST_SUCCESS, totalCostString);
        assertCommandSuccess(calculateCommand, newModel, expectedMessage, expectedModel);
    }

    // executing calculate on a date with no procedure
    @Test
    public void execute_dateWith0Procedures_success() {
        CalculateCommand calculateCommand = new CalculateCommand(new DateWithoutTime("30/04/2022"));
        String totalCostString = '$' + noCost;
        String expectedMessage = String.format(MESSAGE_CALCULATE_COST_SUCCESS, totalCostString);
        assertCommandSuccess(calculateCommand, newModel, expectedMessage, expectedModel);
    }

    // executing calculate on a date with more than 1 procedure
    @Test
    public void execute_dateWith3Procedures_success() {
        CalculateCommand calculateCommand = new CalculateCommand(new DateWithoutTime("18/03/2022"));
        BigDecimal totalCost = new BigDecimal(VALID_COST_REPAIR).add(new BigDecimal(VALID_COST_REPLACE))
                .add(new BigDecimal(VALID_COST_REPLACE));
        String totalCostString = '$' + totalCost.toString();
        String expectedMessage = String.format(MESSAGE_CALCULATE_COST_SUCCESS, totalCostString);
        assertCommandSuccess(calculateCommand, newModel, expectedMessage, expectedModel);
    }
}
