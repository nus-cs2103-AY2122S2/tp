package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class HelpCommandParserTest {

    private HelpCommandParser parser = new HelpCommandParser();

    @Test
    public void parse_validArgs() throws ParseException {

        //Exact same input
        assertEquals(new HelpCommand("add"), parser.parse("add"));

        //Input with spacing
        assertEquals(new HelpCommand("delete"), parser.parse(" delete "));

        //Input with uppercase characters
        assertEquals(new HelpCommand("sort"), parser.parse("SoRT"));
    }

    @Test
    public void parse_invalidArgs() {
        assertThrows(ParseException.class, ()-> parser.parse("NotInsideHelp"));
        assertThrows(ParseException.class, ()-> parser.parse("***"));
    }

}
