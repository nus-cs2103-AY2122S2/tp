package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class FavouriteTest {

    @Test
    void valueOf_invalidBooleanString_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Favourite.valueOf("notBoolean"));
    }

    @Test
    void isValidFavourite() {
        // null favourite
        assertThrows(NullPointerException.class, () -> Favourite.isValidFavourite(null));

        // invalid favourite values
        assertFalse(Favourite.isValidFavourite("")); // empty string
        assertFalse(Favourite.isValidFavourite(" ")); // spaces only
        assertFalse(Favourite.isValidFavourite("no")); // non-boolean
        assertFalse(Favourite.isValidFavourite("yes")); // non-boolean
        assertFalse(Favourite.isValidFavourite("1")); // binary digit
        assertFalse(Favourite.isValidFavourite("0")); // binary digit
        assertFalse(Favourite.isValidFavourite("True")); // capitalized boolean string

        // valid favourite values
        assertTrue(Favourite.isValidFavourite("true"));
        assertTrue(Favourite.isValidFavourite("false"));
    }

    @Test
    void isFavourite() {
        // not favourite
        assertFalse(Favourite.valueOf("false").isFavourite());

        // is favourite
        assertTrue(Favourite.valueOf("true").isFavourite());
    }
}
