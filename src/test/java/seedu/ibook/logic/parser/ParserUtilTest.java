package seedu.ibook.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.ibook.logic.parser.ParserUtil.MESSAGE_INVALID_COMPOUND_INDEX;
import static seedu.ibook.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.ibook.logic.parser.ParserUtil.parseCategory;
import static seedu.ibook.logic.parser.ParserUtil.parseCompoundIndex;
import static seedu.ibook.logic.parser.ParserUtil.parseDescription;
import static seedu.ibook.logic.parser.ParserUtil.parseExpiryDate;
import static seedu.ibook.logic.parser.ParserUtil.parseIndex;
import static seedu.ibook.logic.parser.ParserUtil.parseName;
import static seedu.ibook.logic.parser.ParserUtil.parsePrice;
import static seedu.ibook.logic.parser.ParserUtil.parsePriceRange;
import static seedu.ibook.logic.parser.ParserUtil.parseQuantity;
import static seedu.ibook.testutil.Assert.assertThrows;
import static seedu.ibook.testutil.TypicalIndexes.INDEX_FIRST_PRODUCT;
import static seedu.ibook.testutil.TypicalIndexes.INDEX_FIRST_PRODUCT_FIRST_ITEM;

import org.junit.jupiter.api.Test;

import seedu.ibook.logic.parser.exceptions.ParseException;
import seedu.ibook.model.item.ExpiryDate;
import seedu.ibook.model.item.Quantity;
import seedu.ibook.model.product.Category;
import seedu.ibook.model.product.Description;
import seedu.ibook.model.product.Name;
import seedu.ibook.model.product.Price;

public class ParserUtilTest {

    private static final String VALID_NAME = "apple";
    private static final String VALID_CATEGORY = "fruit";
    private static final String VALID_EXPIRY_DATE = "2022-05-05";
    private static final String VALID_DESCRIPTION = "import from South Africa";
    private static final String VALID_PRICE = "4.95";
    private static final String VALID_QUANTITY = "100";

    private static final String INVALID_NAME = " ";
    private static final String INVALID_EXPIRY_DATE = "2020-99-10";
    private static final String INVALID_PRICE = "-1.2";
    private static final String INVALID_QUANTITY = "1!!";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws ParseException {
        // No whitespaces
        assertEquals(INDEX_FIRST_PRODUCT, parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PRODUCT, parseIndex("  1  "));
    }

    @Test
    public void parseCompoundIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> parseCompoundIndex("10-a"));
    }

    @Test
    public void parseCompoundIndex_outOfRangeInput_throwsParseException() {
        // Index exceeding Integer max value
        assertThrows(ParseException.class, MESSAGE_INVALID_COMPOUND_INDEX, () -> parseCompoundIndex(
                String.format("1-%s", Long.toString(Integer.MAX_VALUE + 1))));
        assertThrows(ParseException.class, MESSAGE_INVALID_COMPOUND_INDEX, () -> parseCompoundIndex(
                String.format("%s-1", Long.toString(Integer.MAX_VALUE + 1))));

        // Zero indices
        assertThrows(ParseException.class, MESSAGE_INVALID_COMPOUND_INDEX, () -> parseCompoundIndex("1-0"));
        assertThrows(ParseException.class, MESSAGE_INVALID_COMPOUND_INDEX, () -> parseCompoundIndex("0-1"));
    }

    @Test
    public void parseCompoundIndex_validInput_success() throws ParseException {
        // No whitespaces
        assertEquals(INDEX_FIRST_PRODUCT_FIRST_ITEM, parseCompoundIndex("1-1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PRODUCT_FIRST_ITEM, parseCompoundIndex("  1-1"));
        assertEquals(INDEX_FIRST_PRODUCT_FIRST_ITEM, parseCompoundIndex("1-1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parseName(null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, parseName(nameWithWhitespace));
    }

    @Test
    public void parseCategory_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parseCategory(null));
    }

    @Test
    public void parseCategory_validValueWithoutWhitespace_returnsCategory() throws Exception {
        Category expectedCategory = new Category(VALID_CATEGORY);
        assertEquals(expectedCategory, parseCategory(VALID_CATEGORY));
    }

    @Test
    public void parseCategory_validValueWithWhitespace_returnsTrimmedCategory() throws Exception {
        String categoryWithWhitespace = WHITESPACE + VALID_CATEGORY + WHITESPACE;
        Category expectedCategory = new Category(VALID_CATEGORY);
        assertEquals(expectedCategory, parseCategory(categoryWithWhitespace));
    }

    @Test
    public void parseExpiryDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parseExpiryDate(null));
    }

    @Test
    public void parseExpiryDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> parseExpiryDate(INVALID_EXPIRY_DATE));
    }

    @Test
    public void parseExpiryDate_validValueWithoutWhitespace_returnsExpiryDate() throws Exception {
        ExpiryDate expectedExpiryDate = new ExpiryDate(VALID_EXPIRY_DATE);
        assertEquals(expectedExpiryDate, parseExpiryDate(VALID_EXPIRY_DATE));
    }

    @Test
    public void parseExpiryDate_validValueWithWhitespace_returnsTrimmedExpiryDate() throws Exception {
        String expiryDateWithWhitespace = WHITESPACE + VALID_EXPIRY_DATE + WHITESPACE;
        ExpiryDate expectedExpiryDate = new ExpiryDate(VALID_EXPIRY_DATE);
        assertEquals(expectedExpiryDate, parseExpiryDate(expiryDateWithWhitespace));
    }

    @Test
    public void parseDescription_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parseDescription(null));
    }

    @Test
    public void parseDescription_validValueWithoutWhitespace_returnsDescription() throws Exception {
        Description expectedDescription = new Description(VALID_DESCRIPTION);
        assertEquals(expectedDescription, parseDescription(VALID_DESCRIPTION));
    }

    @Test
    public void parseDescription_validValueWithWhitespace_returnsTrimmedDescription() throws Exception {
        String descriptionWithWhitespace = WHITESPACE + VALID_DESCRIPTION + WHITESPACE;
        Description expectedDescription = new Description(VALID_DESCRIPTION);
        assertEquals(expectedDescription, parseDescription(descriptionWithWhitespace));
    }

    @Test
    public void parsePrice_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parsePrice(null));
    }

    @Test
    public void parsePrice_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> parsePrice(INVALID_PRICE));
    }

    @Test
    public void parsePriceRange_invalidStartPrice_throwsParseException() {
        assertThrows(ParseException.class, () -> parsePriceRange(INVALID_PRICE, VALID_PRICE));
    }

    @Test
    public void parsePrice_invalidEndPrice_throwsParseException() {
        assertThrows(ParseException.class, () -> parsePriceRange(VALID_PRICE, INVALID_PRICE));
    }

    @Test
    public void parsePrice_validValueWithoutWhitespace_returnsPrice() throws Exception {
        Price expectedPrice = new Price(VALID_PRICE);
        assertEquals(expectedPrice, parsePrice(VALID_PRICE));
    }

    @Test
    public void parsePrice_validValueWithWhitespace_returnsTrimmedPrice() throws Exception {
        String priceWithWhitespace = WHITESPACE + VALID_PRICE + WHITESPACE;
        Price expectedPrice = new Price(VALID_PRICE);
        assertEquals(expectedPrice, parsePrice(priceWithWhitespace));
    }

    @Test
    public void parseQuantity_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parseQuantity((String) null));
    }

    @Test
    public void parseQuantity_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> parseQuantity(INVALID_QUANTITY));
    }

    @Test
    public void parseQuantity_validValueWithoutWhitespace_returnsExpiryDate() throws Exception {
        Quantity expectedQuantity = new Quantity(VALID_QUANTITY);
        assertEquals(expectedQuantity, parseQuantity(VALID_QUANTITY));
    }

    @Test
    public void parseQuantity_validValueWithWhitespace_returnsTrimmedExpiryDate() throws Exception {
        String quantityWithWhitespace = WHITESPACE + VALID_QUANTITY + WHITESPACE;
        Quantity expectedQuantity = new Quantity(VALID_QUANTITY);
        assertEquals(expectedQuantity, parseQuantity(quantityWithWhitespace));
    }
}
