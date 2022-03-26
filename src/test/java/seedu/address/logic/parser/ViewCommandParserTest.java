package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_SORTKEY;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class ViewCommandParserTest {

    private ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void parse_emptyArg_returnsViewCommand() throws ParseException {
        assert (new ViewCommandParser().parse("     ")) != null;
    }

    @Test
    public void parse_validArgs_returnsViewCommand() throws ParseException {
        assert (new ViewCommandParser().parse("   \t  ")) != null;
        assert (new ViewCommandParser().parse("   all  ")) != null;
        assert(new ViewCommandParser().parse("")) != null;
        assert(new ViewCommandParser().parse("   today   ") != null);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() throws ParseException {
        // invalid args
        assertThrows(ParseException.class, () -> new ViewCommandParser().parse(PREFIX_SORTKEY + "   today   "));
        assertThrows(ParseException.class, () -> new ViewCommandParser().parse("   today  Week "));
    }
}
