package seedu.contax.commons.util.datetimeparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;

public class DateParserTest {
    @Test
    public void parseDate_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> DateParser.parseDate(null));
    }

    @Test
    public void parseStandardDate() {
        // Missing Components
        assertEquals(Optional.empty(), DateParser.parseDate("-10-2022"));
        assertEquals(Optional.empty(), DateParser.parseDate("10--2022"));
        assertEquals(Optional.empty(), DateParser.parseDate("10-10-"));
        assertEquals(Optional.empty(), DateParser.parseDate("10-10"));
        assertEquals(Optional.empty(), DateParser.parseDate("10-2022"));

        // Invalid Components
        assertEquals(Optional.empty(), DateParser.parseDate("32-10-2022")); // Invalid Day
        assertEquals(Optional.empty(), DateParser.parseDate("0-10-2022")); // Invalid Day
        assertEquals(Optional.empty(), DateParser.parseDate("ab-10-2022")); // Invalid Day
        assertEquals(Optional.empty(), DateParser.parseDate("20-13-2022")); // Invalid Month
        assertEquals(Optional.empty(), DateParser.parseDate("20-0-2022")); // Invalid Month
        assertEquals(Optional.empty(), DateParser.parseDate("20-ab-2022")); // Invalid Month
        assertEquals(Optional.empty(), DateParser.parseDate("20-10-abcd")); // Invalid Year
        assertEquals(Optional.empty(), DateParser.parseDate("20-10-212")); // Invalid Year

        // Different delimiters
        assertEquals(Optional.empty(), DateParser.parseDate("32/10/2022")); // Invalid Day
        assertEquals(Optional.empty(), DateParser.parseDate("ab/10/2022")); // Invalid Day
        assertEquals(Optional.empty(), DateParser.parseDate("20/13/2022")); // Invalid Month
        assertEquals(Optional.empty(), DateParser.parseDate("20/ab/2022")); // Invalid Month
        assertEquals(Optional.empty(), DateParser.parseDate("20/10/abcd")); // Invalid Year
        assertEquals(Optional.empty(), DateParser.parseDate("20/10/212")); // Invalid Year

        // Successful parsing
        assertEquals(LocalDate.parse("2022-10-20"), DateParser.parseDate("20-10-2022").get());
        assertEquals(LocalDate.parse("1971-03-30"), DateParser.parseDate("30-03-1971").get());

        // Different delimiters
        assertEquals(LocalDate.parse("1971-03-30"), DateParser.parseDate("30/03/1971").get());
        assertEquals(LocalDate.parse("1971-03-30"), DateParser.parseDate("30/03-1971").get());
        assertEquals(LocalDate.parse("1971-03-30"), DateParser.parseDate("30-03/1971").get());
    }

    @Test
    public void parseNaturalDate() {
        // Missing Components
        assertEquals(Optional.empty(), DateParser.parseDate("Jan 2022")); // Missing Day
        assertEquals(Optional.empty(), DateParser.parseDate("10 2022")); // Missing Month
        assertEquals(Optional.empty(), DateParser.parseDate("23 Jan")); // Missing Year

        // Invalid Components
        assertEquals(Optional.empty(), DateParser.parseDate("32 Jan 2022")); // Invalid Day
        assertEquals(Optional.empty(), DateParser.parseDate("0 March 2021")); // Invalid Day
        assertEquals(Optional.empty(), DateParser.parseDate("ab Dec 2020")); // Invalid Day
        assertEquals(Optional.empty(), DateParser.parseDate("20 Dex 2020")); // Invalid Month
        assertEquals(Optional.empty(), DateParser.parseDate("20 Mays 2021")); // Invalid Month
        assertEquals(Optional.empty(), DateParser.parseDate("20 02 2022")); // Invalid Month
        assertEquals(Optional.empty(), DateParser.parseDate("20 Mar abcd")); // Invalid Year
        assertEquals(Optional.empty(), DateParser.parseDate("20 April 212")); // Invalid Year

        // Successful parsing, standard forms
        assertEquals(LocalDate.parse("2022-10-20"), DateParser.parseDate("20 Oct 2022").get());
        assertEquals(LocalDate.parse("2022-10-20"), DateParser.parseDate("20 October 2022").get());
        assertEquals(LocalDate.parse("1971-03-30"), DateParser.parseDate("30 March 1971").get());
        assertEquals(LocalDate.parse("1971-03-30"), DateParser.parseDate("30 Mar 1971").get());

        // Successful parsing, different capitalization
        assertEquals(LocalDate.parse("2022-11-20"), DateParser.parseDate("20 nov 2022").get());
        assertEquals(LocalDate.parse("2022-10-20"), DateParser.parseDate("20 OCT 2022").get());
        assertEquals(LocalDate.parse("1971-03-30"), DateParser.parseDate("30 MaRcH 1971").get());
        assertEquals(LocalDate.parse("1971-04-30"), DateParser.parseDate("30 april 1971").get());

        // Successful parsing, different ordering
        assertEquals(LocalDate.parse("2022-10-20"), DateParser.parseDate("20 Oct 2022").get());
        assertEquals(LocalDate.parse("2022-10-20"), DateParser.parseDate("Oct 20 2022").get());
        assertEquals(LocalDate.parse("2022-10-20"), DateParser.parseDate("Oct 2022 20").get());
        assertEquals(LocalDate.parse("2022-10-20"), DateParser.parseDate("2022 Oct 20").get());
        assertEquals(LocalDate.parse("2022-10-20"), DateParser.parseDate("2022 20 Oct").get());
        assertEquals(LocalDate.parse("2022-10-20"), DateParser.parseDate("20 2022 Oct").get());
    }

    @Test
    public void parseNaturalDate_duplicateComponent_emptyOptional() {
        // Alternate cases between sharing a whitespace and fully disjoint sections
        assertEquals(Optional.empty(), DateParser.parseDate("20 2022 Mar Oct"));
        assertEquals(Optional.empty(), DateParser.parseDate("20 Oct 2022 Mar"));

        assertEquals(Optional.empty(), DateParser.parseDate("21 20 2022 Oct"));
        assertEquals(Optional.empty(), DateParser.parseDate("20 2022 21 Oct"));

        assertEquals(Optional.empty(), DateParser.parseDate("20 2020 2022 Oct"));
        assertEquals(Optional.empty(), DateParser.parseDate("2020 20 Oct 2022"));

        assertEquals(Optional.empty(), DateParser.parseDate("21 20 2021 2022 Apr Oct"));
        assertEquals(Optional.empty(), DateParser.parseDate("21 Apr 2021 2022 30 Oct"));
    }
}
