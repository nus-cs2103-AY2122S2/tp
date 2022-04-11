package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.DESC_REPAIR;
import static seedu.address.logic.commands.CommandTestUtil.DESC_REPLACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BURGER;
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

public class ListProcOnCommandTest {
    private Model newModel;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        newModel = new ModelManager();
        expectedModel = new ModelManager(new AddressBook(newModel.getAddressBook()), new UserPrefs());

        List<Procedure> sampleProcedures = new ArrayList<>();
        sampleProcedures.add(DESC_REPLACE);
        sampleProcedures.add(DESC_REPAIR);

        Client firstSampleClient = new ClientBuilder().withName(VALID_NAME_APPLE).withPhone(VALID_PHONE_APPLE)
                .withEmail(VALID_EMAIL_APPLE).withAddress(VALID_ADDRESS_APPLE).withPlan(VALID_PLAN_APPLE)
                .withTags(VALID_TAG_TECH).withProcedures(sampleProcedures).build();
        Client secondSampleClient = new ClientBuilder().withName(VALID_NAME_BURGER).withPhone(VALID_PHONE_BURGER)
                .withEmail(VALID_EMAIL_BURGER).withAddress(VALID_ADDRESS_BURGER).withPlan(VALID_PLAN_BURGER)
                .withTags(VALID_TAG_FASTFOOD).withProcedures(sampleProcedures).build();

        newModel.addClient(firstSampleClient);
        newModel.addClient(secondSampleClient);

        expectedModel.addClient(firstSampleClient);
        expectedModel.addClient(secondSampleClient);
    }

    @Test
    public void execute_dateWithProcedures_success() {
        ListProcOnCommand listProcOnCommand = new ListProcOnCommand(new DateWithoutTime("21/04/2022"));
        String expectedMessage = "Listing Procedures on requested date:\n"
                + "1. Information: Repair Router; Date: 21/04/2022 11:50; Cost: $13.50; Completed: true\n"
                + "Apple, located at Block 312, Apple Street 1\n"
                + "2. Information: Repair Router; Date: 21/04/2022 11:50; Cost: $13.50; Completed: true\n"
                + "Burger King, located at Block 123, Burger Street 3\n";
        assertCommandSuccess(listProcOnCommand, newModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_dateWithoutProcedures_success() {
        ListProcOnCommand listProcOnCommand = new ListProcOnCommand(new DateWithoutTime("30/03/2022"));
        String expectedMessage = ListProcOnCommand.MESSAGE_NO_PROCEDURES;
        assertCommandSuccess(listProcOnCommand, newModel, expectedMessage, expectedModel);
    }
}
