package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINEUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLAYER;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.PutCommand;
import seedu.address.logic.commands.ThemeCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lineup.Lineup;
import seedu.address.model.person.Person;
import seedu.address.testutil.LineupBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class AddressBookParserTest {
    private AddressBookParser addressBookParser = new AddressBookParser();

    @Test
    public void parse_commands_success() throws ParseException {
        // parse add command when "add" command word is used
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) addressBookParser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);

        // parse delete command when "delete" command word is used
        assertTrue(addressBookParser.parseCommand(PersonUtil.getDeleteCommand(person)) instanceof DeleteCommand);

        // parse clear command when "clear" command word is used
        assertTrue(addressBookParser.parseCommand("clear") instanceof ClearCommand);

        // parse view command when "view" command word is used
        assertTrue(addressBookParser.parseCommand("view P/") instanceof ViewCommand);

        // parse exit command when "exit" command word is used
        assertTrue(addressBookParser.parseCommand("exit") instanceof ExitCommand);

        // parse help command when "help" command word is used
        assertTrue(addressBookParser.parseCommand("help") instanceof HelpCommand);

        // parse theme command when "theme" command word is used
        assertTrue(addressBookParser.parseCommand("theme T/light") instanceof ThemeCommand);

        // parse put command when "put" command word is used
        Lineup lineup = new LineupBuilder().build();
        assertTrue(addressBookParser.parseCommand("put " + PREFIX_PLAYER + person.getName().fullName
                + " " + PREFIX_LINEUP + lineup.getLineupName().lineupName) instanceof PutCommand);
    }

    @Test
    public void parse_unknownCommand_failure() throws ParseException {
        assertThrows(ParseException.class, () -> addressBookParser.parseCommand("invalid command"));
    }
}
