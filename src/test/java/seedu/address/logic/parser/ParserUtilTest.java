package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Preference;
import seedu.address.model.person.UserType;
import seedu.address.model.property.Price;
import seedu.address.model.property.Property;
import seedu.address.model.property.Region;
import seedu.address.model.property.Size;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_USERTYPE = "client";
    private static final String INVALID_REGION = "northwest";
    private static final String INVALID_SIZE = "6-room";
    private static final String INVALID_PRICE = "100000";
    private static final String INVALID_PRICE_2 = "200000";
    private static final String INVALID_PROPERTY =
            INVALID_REGION + "," + INVALID_ADDRESS + "," + INVALID_SIZE + "," + INVALID_PRICE;
    private static final String INVALID_PREFERENCE =
            INVALID_REGION + "," + INVALID_SIZE + "," + INVALID_PRICE + "," + INVALID_PRICE_2;

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS_1 = "123 Main Street #0505";
    private static final String VALID_ADDRESS_2 = "123 Main Avenue #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_USERTYPE_1 = "buyer";
    private static final String VALID_USERTYPE_2 = "seller";
    private static final String VALID_REGION_1 = "north";
    private static final String VALID_SIZE_1 = "1-room";
    private static final String VALID_PRICE_1 = "$100000";
    private static final String VALID_REGION_2 = "south";
    private static final String VALID_SIZE_2 = "2-room";
    private static final String VALID_PRICE_2 = "$200000";
    private static final String VALID_PROPERTY_1 =
            VALID_REGION_1 + "," + VALID_ADDRESS_1 + "," + VALID_SIZE_1 + "," + VALID_PRICE_1;
    private static final String VALID_PROPERTY_2 =
            VALID_REGION_2 + "," + VALID_ADDRESS_2 + "," + VALID_SIZE_2 + "," + VALID_PRICE_2;
    private static final String VALID_PREFERENCE =
            VALID_REGION_1 + "," + VALID_SIZE_1 + "," + VALID_PRICE_1 + "," + VALID_PRICE_2;

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS_1);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS_1));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS_1 + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS_1);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseUserType_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseUserType(INVALID_USERTYPE));
    }

    @Test
    public void parseUserType_validValueWithoutWhitespace_returnsUserType() throws Exception {
        UserType expectedUserType = new UserType(VALID_USERTYPE_2);
        assertEquals(expectedUserType, ParserUtil.parseUserType(VALID_USERTYPE_2));
    }

    @Test
    public void parseUserType_validValueWithWhitespace_returnsTrimmedUserType() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_USERTYPE_1 + WHITESPACE;
        UserType expectedUserType = new UserType(VALID_USERTYPE_1);
        assertEquals(expectedUserType, ParserUtil.parseUserType(tagWithWhitespace));
    }

    @Test
    public void parseUserType_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseUserType(null));
    }

    @Test
    public void parseRegion_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRegion(null));
    }

    @Test
    public void parseRegion_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRegion(INVALID_REGION));
    }

    @Test
    public void parseRegion_validValueWithoutWhitespace_returnsRegion() throws Exception {
        Region expectedRegion = Region.fromString(VALID_REGION_1);
        assertEquals(expectedRegion, ParserUtil.parseRegion(VALID_REGION_1));
    }

    @Test
    public void parseRegion_validValueWithWhitespace_returnsTrimmedRegion() throws Exception {
        String regionWithWhitespace = WHITESPACE + VALID_REGION_1 + WHITESPACE;
        Region expectedRegion = Region.fromString(VALID_REGION_1);
        assertEquals(expectedRegion, ParserUtil.parseRegion(regionWithWhitespace));
    }

    @Test
    public void parseSize_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSize(null));
    }

    @Test
    public void parseSize_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSize(INVALID_SIZE));
    }

    @Test
    public void parseSize_validValueWithoutWhitespace_returnsSize() throws Exception {
        Size expectedSize = Size.fromString(VALID_SIZE_1);
        assertEquals(expectedSize, ParserUtil.parseSize(VALID_SIZE_1));
    }

    @Test
    public void parseSize_validValueWithWhitespace_returnsTrimmedSize() throws Exception {
        String sizeWithWhitespace = WHITESPACE + VALID_SIZE_1 + WHITESPACE;
        Size expectedSize = Size.fromString(VALID_SIZE_1);
        assertEquals(expectedSize, ParserUtil.parseSize(sizeWithWhitespace));
    }

    @Test
    public void parsePrice_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePrice(null));
    }

    @Test
    public void parsePrice_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePrice(INVALID_PRICE));
    }

    @Test
    public void parsePrice_validValueWithoutWhitespace_returnsPrice() throws Exception {
        Price expectedPrice = new Price(VALID_PRICE_1);
        assertEquals(expectedPrice, ParserUtil.parsePrice(VALID_PRICE_1));
    }

    @Test
    public void parsePrice_validValueWithWhitespace_returnsTrimmedPrice() throws Exception {
        String priceWithWhitespace = WHITESPACE + VALID_PRICE_1 + WHITESPACE;
        Price expectedPrice = new Price(VALID_PRICE_1);
        assertEquals(expectedPrice, ParserUtil.parsePrice(priceWithWhitespace));
    }

    @Test
    public void parseProperty_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseProperty(null));
    }

    @Test
    public void parseProperty_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseProperty(INVALID_PROPERTY));
    }

    @Test
    public void parseProperty_validValueWithoutWhitespace_returnsProperty() throws Exception {
        Region region = Region.fromString(VALID_REGION_1);
        Address address = new Address(VALID_ADDRESS_1);
        Size size = Size.fromString(VALID_SIZE_1);
        Price price = new Price(VALID_PRICE_1);
        Property expectedProperty = new Property(region, address, size, price);
        assertEquals(expectedProperty, ParserUtil.parseProperty(VALID_PROPERTY_1));
    }

    @Test
    public void parseProperty_validValueWithWhitespace_returnsTrimmedProperty() throws Exception {
        String propertyWithWhitespace = WHITESPACE + VALID_PROPERTY_1 + WHITESPACE;
        Region region = Region.fromString(VALID_REGION_1);
        Address address = new Address(VALID_ADDRESS_1);
        Size size = Size.fromString(VALID_SIZE_1);
        Price price = new Price(VALID_PRICE_1);
        Property expectedProperty = new Property(region, address, size, price);
        assertEquals(expectedProperty, ParserUtil.parseProperty(propertyWithWhitespace));
    }

    @Test
    public void parseProperties_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseProperties(null));
    }

    @Test
    public void parseProperties_collectionWithInvalidProperties_throwsParseException() {
        assertThrows(ParseException.class, ()
            -> ParserUtil.parseProperties(Arrays.asList(VALID_PROPERTY_1, INVALID_PROPERTY)));
    }

    @Test
    public void parseProperties_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseProperties(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseProperties_collectionWithValidProperties_returnsPropertySet() throws Exception {
        Set<Property> actualPropertySet = ParserUtil.parseProperties(Arrays.asList(VALID_PROPERTY_1, VALID_PROPERTY_2));

        Region region1 = Region.fromString(VALID_REGION_1);
        Address address1 = new Address(VALID_ADDRESS_1);
        Size size1 = Size.fromString(VALID_SIZE_1);
        Price price1 = new Price(VALID_PRICE_1);
        Property expectedProperty1 = new Property(region1, address1, size1, price1);

        Region region2 = Region.fromString(VALID_REGION_2);
        Address address2 = new Address(VALID_ADDRESS_2);
        Size size2 = Size.fromString(VALID_SIZE_2);
        Price price2 = new Price(VALID_PRICE_2);
        Property expectedProperty2 = new Property(region2, address2, size2, price2);

        Set<Property> expectedPropertySet = new HashSet<>(Arrays.asList(expectedProperty1, expectedProperty2));

        assertEquals(expectedPropertySet, actualPropertySet);
    }

    @Test
    public void parsePreference_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePreference(null));
    }

    @Test
    public void parsePreference_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePreference(INVALID_PREFERENCE));
    }

    @Test
    public void parsePreference_validValueWithoutWhitespace_returnsPreference() throws Exception {
        Region region = Region.fromString(VALID_REGION_1);
        Size size = Size.fromString(VALID_SIZE_1);
        Price lowPrice = new Price(VALID_PRICE_1);
        Price highPrice = new Price(VALID_PRICE_2);
        Preference expectedPreference = new Preference(region, size, lowPrice, highPrice);
        assertEquals(expectedPreference, ParserUtil.parsePreference(VALID_PREFERENCE));
    }

    @Test
    public void parsePreference_validValueWithWhitespace_returnsTrimmedPreference() throws Exception {
        String preferenceWithWhitespace = WHITESPACE + VALID_PREFERENCE + WHITESPACE;
        Region region = Region.fromString(VALID_REGION_1);
        Size size = Size.fromString(VALID_SIZE_1);
        Price lowPrice = new Price(VALID_PRICE_1);
        Price highPrice = new Price(VALID_PRICE_2);
        Preference expectedPreference = new Preference(region, size, lowPrice, highPrice);
        assertEquals(expectedPreference, ParserUtil.parsePreference(preferenceWithWhitespace));
    }
}
