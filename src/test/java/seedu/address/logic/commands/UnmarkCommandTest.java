package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;
import seedu.address.model.procedure.Completion;
import seedu.address.model.procedure.Cost;
import seedu.address.model.procedure.Date;
import seedu.address.model.procedure.Information;
import seedu.address.model.procedure.Procedure;
import seedu.address.testutil.ClientBuilder;

/**
 * Contains integration tests for UnmarkCommand and Model.
 */
public class UnmarkCommandTest {
    private Model model;
    private Model expectedModel;
    private List<Procedure> sampleProcedures = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        sampleProcedures.add(new Procedure(new Information("hello"), new Date("15/06/2022 10:15"),
                new Cost("1.05"), new Completion("true")));
        sampleProcedures.add(new Procedure(new Information("o no"), new Date("18/08/2022 11:30"),
                new Cost("1.10"), new Completion("true")));

        Client sampleClient = new ClientBuilder().withProcedures(sampleProcedures).build();
        model.addClient(sampleClient);
        model.setProcedures(sampleProcedures);
        model.updateFilteredProcedureList(sampleClient, Model.PREDICATE_SHOW_CLIENT_PROCEDURES);
        expectedModel.addClient(sampleClient);
        expectedModel.setProcedures(sampleProcedures);
        expectedModel.updateFilteredProcedureList(sampleClient, Model.PREDICATE_SHOW_CLIENT_PROCEDURES);
    }

    @Test
    public void execute_unmark() {
        // Testing a client that has procedures
        assertCommandSuccess(
                new UnmarkCommand(Index.fromZeroBased(1), Index.fromZeroBased(0)),
                model,
                UnmarkCommand.MESSAGE_SUCCESS,
                expectedModel
        );
        assertEquals(model.getFilteredClientList().get(1).getProcedures().get(0).getHasCompleted(),
                new Completion("false"));
    }

    @Test
    public void execute_invalidProcedureIndex() {
        // Testing a client with no procedures
        assertCommandFailure(new UnmarkCommand(Index.fromZeroBased(0), Index.fromZeroBased(0)),
                model,
                Messages.MESSAGE_INVALID_PROCEDURE_DISPLAYED_INDEX
        );
    }

    @Test
    public void execute_invalidClientIndex() {
        // Checking boundaries
        assertCommandFailure(new UnmarkCommand(Index.fromZeroBased(model.getFilteredClientList().size()),
                        Index.fromZeroBased(0)),
                model,
                Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX
        );
        assertCommandFailure(new UnmarkCommand(Index.fromZeroBased(model.getFilteredClientList().size() + 1),
                        Index.fromZeroBased(0)),
                model,
                Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX
        );
    }
}
