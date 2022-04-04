package seedu.contax.commons.util.datetimeparser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DateParserPatternProviderTest {
    @Test
    public void testConversion() {
        assertEquals(12, DateParserPatternProvider.convertMonthStringToDecimal("dec"));
        assertEquals(10, DateParserPatternProvider.convertMonthStringToDecimal("oct"));
        assertEquals(1, DateParserPatternProvider.convertMonthStringToDecimal("jan"));

        assertEquals(12, DateParserPatternProvider.convertMonthStringToDecimal("december"));
        assertEquals(4, DateParserPatternProvider.convertMonthStringToDecimal("april"));
        assertEquals(1, DateParserPatternProvider.convertMonthStringToDecimal("january"));

        assertEquals(-1, DateParserPatternProvider.convertMonthStringToDecimal("DEX"));
    }
}
