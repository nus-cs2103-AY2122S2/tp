package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Contains tests for parsing of multiple of indexes for MassOps commands.
 */
public class MassOpsParserTest {
    @Test
    public void parseMassOps_validArgs_returnsIndexesList() throws ParseException {
        String indexesStr = "1 2 3 6";
        List<Index> indexesList = List.of(Index.fromOneBased(1), Index.fromOneBased(2), Index.fromOneBased(3),
                Index.fromOneBased(6));
        assertTrue(MassOpsParser.massOpProcessor(indexesStr).equals(indexesList));
    }

    @Test
    public void parseMassOps_invalidLtrArg_throwsParseException() throws ParseException {
        String indexesStr = "1 2 3 a";
        assertThrows(ParseException.class, "Index is not a non-zero unsigned integer.", (
            ) -> MassOpsParser.massOpProcessor(indexesStr));
    }

    @Test
    public void parseMassOps_invalidIndexArg_throwsParseException() throws ParseException {
        String indexesStr = "-1 2 3 4";
        assertThrows(ParseException.class, "Index is not a non-zero unsigned integer.", (
            ) -> MassOpsParser.massOpProcessor(indexesStr));
    }

    @Test
    public void parseMassOps_emptyStr_throwsParseException() throws ParseException {
        String indexesStr = "";
        assertThrows(AssertionError.class, () -> MassOpsParser.massOpProcessor(indexesStr));
    }

    @Test
    public void sortAsc_validArgs() throws ParseException {
        List<Index> indexes = List.of(Index.fromOneBased(5), Index.fromOneBased(4), Index.fromOneBased(1),
                Index.fromOneBased(3));
        List<Index> expectedIndexes = List.of(Index.fromOneBased(1), Index.fromOneBased(3), Index.fromOneBased(4),
                Index.fromOneBased(5));
        assertTrue(MassOpsParser.sortInAsc(indexes).equals(expectedIndexes));
    }

    @Test
    public void sortAsc_invalidIndexArg_throwsParseException() throws ParseException {
        List<Index> indexes = List.of();
        assertThrows(AssertionError.class, ()-> MassOpsParser.sortInAsc(indexes));
    }

    @Test
    public void sortDesc_validArgs() throws ParseException {
        List<Index> indexes = List.of(Index.fromOneBased(5), Index.fromOneBased(4), Index.fromOneBased(1),
                Index.fromOneBased(3));
        List<Index> expectedIndexes = List.of(Index.fromOneBased(5), Index.fromOneBased(4), Index.fromOneBased(3),
                Index.fromOneBased(1));
        assertTrue(MassOpsParser.sortInDesc(indexes).equals(expectedIndexes));
    }

    @Test
    public void sortDesc_invalidIndexArg_throwsParseException() throws ParseException {
        List<Index> indexes = List.of();
        assertThrows(AssertionError.class, () -> MassOpsParser.sortInDesc(indexes));
    }
}
