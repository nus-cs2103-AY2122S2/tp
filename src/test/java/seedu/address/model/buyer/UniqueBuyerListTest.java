package seedu.address.model.buyer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBuyers.BOB;
import static seedu.address.testutil.TypicalBuyers.CHAD;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.client.exceptions.ClientNotFoundException;
import seedu.address.model.client.exceptions.DuplicateClientException;
import seedu.address.testutil.BuyerBuilder;

// TODO: On a later date once buyer list is created
public class UniqueBuyerListTest {

    private final UniqueBuyerList uniquebuyerList = new UniqueBuyerList();

    @Test
    public void contains_nullbuyer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquebuyerList.contains(null));
    }

    @Test
    public void contains_buyerNotInList_returnsFalse() {
        assertFalse(uniquebuyerList.contains(CHAD));
    }

    @Test
    public void contains_buyerInList_returnsTrue() {
        uniquebuyerList.add(CHAD);
        assertTrue(uniquebuyerList.contains(CHAD));
    }

    @Test
    public void contains_buyerWithSameIdentityFieldsInList_returnsTrue() {
        uniquebuyerList.add(CHAD);
        Buyer editedChad = new BuyerBuilder(CHAD).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniquebuyerList.contains(editedChad));
    }

    @Test
    public void add_nullbuyer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquebuyerList.add(null));
    }

    @Test
    public void add_duplicatebuyer_throwsDuplicatebuyerException() {
        uniquebuyerList.add(CHAD);
        assertThrows(DuplicateClientException.class, () -> uniquebuyerList.add(CHAD));
    }

    @Test
    public void setBuyer_nullTargetbuyer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquebuyerList.setBuyer(null, CHAD));
    }

    @Test
    public void setBuyer_nullEditedbuyer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquebuyerList.setBuyer(CHAD, null));
    }

    @Test
    public void setBuyer_targetbuyerNotInList_throwsbuyerNotFoundException() {
        assertThrows(ClientNotFoundException.class, () -> uniquebuyerList.setBuyer(CHAD, CHAD));
    }

    @Test
    public void setBuyer_editedbuyerIsSamebuyer_success() {
        uniquebuyerList.add(CHAD);
        uniquebuyerList.setBuyer(CHAD, CHAD);
        UniqueBuyerList expectedUniquebuyerList = new UniqueBuyerList();
        expectedUniquebuyerList.add(CHAD);
        assertEquals(expectedUniquebuyerList, uniquebuyerList);
    }

    @Test
    public void setBuyer_editedbuyerHasSameIdentity_success() {
        uniquebuyerList.add(CHAD);
        Buyer editedChad = new BuyerBuilder(CHAD).withTags(VALID_TAG_HUSBAND)
                .build();
        uniquebuyerList.setBuyer(CHAD, editedChad);
        UniqueBuyerList expectedUniquebuyerList = new UniqueBuyerList();
        expectedUniquebuyerList.add(editedChad);
        assertEquals(expectedUniquebuyerList, uniquebuyerList);
    }

    @Test
    public void setBuyer_editedbuyerHasDifferentIdentity_success() {
        uniquebuyerList.add(CHAD);
        uniquebuyerList.setBuyer(CHAD, BOB);
        UniqueBuyerList expectedUniquebuyerList = new UniqueBuyerList();
        expectedUniquebuyerList.add(BOB);
        assertEquals(expectedUniquebuyerList, uniquebuyerList);
    }

    @Test
    public void setBuyer_editedbuyerHasNonUniqueIdentity_throwsDuplicatebuyerException() {
        uniquebuyerList.add(CHAD);
        uniquebuyerList.add(BOB);
        assertThrows(DuplicateClientException.class, () -> uniquebuyerList.setBuyer(CHAD, BOB));
    }

    @Test
    public void remove_nullbuyer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquebuyerList.remove(null));
    }

    @Test
    public void remove_buyerDoesNotExist_throwsbuyerNotFoundException() {
        assertThrows(ClientNotFoundException.class, () -> uniquebuyerList.remove(CHAD));
    }

    @Test
    public void remove_existingbuyer_removesbuyer() {
        uniquebuyerList.add(CHAD);
        uniquebuyerList.remove(CHAD);
        UniqueBuyerList expectedUniquebuyerList = new UniqueBuyerList();
        assertEquals(expectedUniquebuyerList, uniquebuyerList);
    }

    @Test
    public void setBuyers_nullUniquebuyerList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquebuyerList.setBuyers((UniqueBuyerList) null));
    }

    @Test
    public void setBuyers_uniquebuyerList_replacesOwnListWithProvidedUniquebuyerList() {
        uniquebuyerList.add(CHAD);
        UniqueBuyerList expectedUniquebuyerList = new UniqueBuyerList();
        expectedUniquebuyerList.add(BOB);
        uniquebuyerList.setBuyers(expectedUniquebuyerList);
        assertEquals(expectedUniquebuyerList, uniquebuyerList);
    }

    @Test
    public void setBuyers_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquebuyerList.setBuyers((List<Buyer>) null));
    }

    @Test
    public void setBuyers_list_replacesOwnListWithProvidedList() {
        uniquebuyerList.add(CHAD);
        List<Buyer> buyerList = Collections.singletonList(BOB);
        uniquebuyerList.setBuyers(buyerList);
        UniqueBuyerList expectedUniquebuyerList = new UniqueBuyerList();
        expectedUniquebuyerList.add(BOB);
        assertEquals(expectedUniquebuyerList, uniquebuyerList);
    }

    @Test
    public void setBuyers_listWithDuplicatebuyers_throwsDuplicatebuyerException() {
        List<Buyer> listWithDuplicateClients = Arrays.asList(CHAD, CHAD);
        assertThrows(DuplicateClientException.class, () -> uniquebuyerList.setBuyers(listWithDuplicateClients));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniquebuyerList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void sort_sortedBuyerList_sameSortOrder() {
        uniquebuyerList.add(CHAD);
        uniquebuyerList.add(BOB);
        uniquebuyerList.sortBuyers("name", "asc");
        UniqueBuyerList otherUniqueBuyerList = new UniqueBuyerList();
        otherUniqueBuyerList.add(BOB);
        otherUniqueBuyerList.add(CHAD);
        assertEquals(otherUniqueBuyerList, uniquebuyerList);
    }

    @Test
    public void sort_sortedBuyerList_diffSortOrder() {
        uniquebuyerList.add(CHAD);
        uniquebuyerList.add(BOB);
        uniquebuyerList.sortBuyers("name", "desc");
        UniqueBuyerList otherUniqueBuyerList = new UniqueBuyerList();
        otherUniqueBuyerList.add(BOB);
        otherUniqueBuyerList.add(CHAD);
        assertNotEquals(otherUniqueBuyerList, uniquebuyerList);
    }

    @Test
    public void sort_sortedBuyerList_sameSortOrderReverse() {
        uniquebuyerList.add(CHAD);
        uniquebuyerList.add(BOB);
        uniquebuyerList.sortBuyers("time", "asc");
        UniqueBuyerList otherUniqueBuyerList = new UniqueBuyerList();
        otherUniqueBuyerList.add(BOB);
        otherUniqueBuyerList.add(CHAD);
        assertEquals(otherUniqueBuyerList, uniquebuyerList);
    }

    @Test
    public void sort_sortedBuyerList_diffSortOrderReverse() {
        uniquebuyerList.add(CHAD);
        uniquebuyerList.add(BOB);
        uniquebuyerList.sortBuyers("time", "desc");
        UniqueBuyerList otherUniqueBuyerList = new UniqueBuyerList();
        otherUniqueBuyerList.add(BOB);
        otherUniqueBuyerList.add(CHAD);
        assertNotEquals(otherUniqueBuyerList, uniquebuyerList);
    }
}
