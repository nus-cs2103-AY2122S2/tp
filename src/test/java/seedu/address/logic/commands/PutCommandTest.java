package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.lineup.Lineup;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class PutCommandTest {
    private static final Person VALID_PERSON = new PersonBuilder().withName("Joel")
            .withEmail("joel@example.com").withPhone("1267912")
            .withHeight("221").withJerseyNumber("83").withWeight("120")
            .withTags("C").build();
    private static final Lineup VALID_LINEUP = new Lineup(new seedu.address.model.lineup.LineupName("Dummy"));
    private static final Lineup VALID_LINEUP_2 = new Lineup(new seedu.address.model.lineup.LineupName("Dummy2"));
    private static final Name VALID_NAME = VALID_PERSON.getName();
    private static final seedu.address.model.lineup.LineupName VALID_LINEUP_NAME = VALID_LINEUP.getLineupName();
    private static final seedu.address.model.lineup.LineupName VALID_LINEUP_NAME_2 = VALID_LINEUP_2.getLineupName();

    private Model model = new ModelManager();

    @Test
    public void execute_put_success() throws CommandException {
        model.addPerson(VALID_PERSON);
        model.addLineup(VALID_LINEUP);
        PutCommand putCommand = new PutCommand(VALID_NAME, VALID_LINEUP_NAME);
        String expectedMessage = String.format(PutCommand.MESSAGE_PUT_PERSON_SUCCESS, VALID_NAME, VALID_LINEUP_NAME);
        String res = putCommand.execute(model).getFeedbackToUser();
        assertEquals(res, expectedMessage);
    }

    @Test
    public void execute_noSuchPlayer_failure() {
        model.addLineup(VALID_LINEUP);
        PutCommand putCommand = new PutCommand(VALID_NAME, VALID_LINEUP_NAME);
        try {
            putCommand.execute(model);
        } catch (CommandException e) {
            assertEquals(e.getMessage(), PutCommand.MESSAGE_NO_SUCH_PERSON);
        }
    }

    @Test
    public void execute_noSuchLineup_failure() {
        model.addPerson(VALID_PERSON);
        PutCommand putCommand = new PutCommand(VALID_NAME, VALID_LINEUP_NAME);
        try {
            putCommand.execute(model);
        } catch (CommandException e) {
            assertEquals(e.getMessage(), PutCommand.MESSAGE_NO_SUCH_LINEUP);
        }
    }

    @Test
    public void execute_lineupFull_failure() {
        Model newModel = new ModelManager();
        newModel.addPerson(VALID_PERSON);
        for (int i = 0; i < 5; i++) {
            VALID_LINEUP_2.addPlayer(new PersonBuilder().build());
        }
        newModel.addLineup(VALID_LINEUP_2);
        PutCommand putCommand = new PutCommand(VALID_NAME, VALID_LINEUP_NAME_2);
        try {
            putCommand.execute(newModel);
        } catch (CommandException e) {
            assertEquals(e.getMessage(), PutCommand.MESSAGE_LINEUP_FULL);
        }
    }
}
