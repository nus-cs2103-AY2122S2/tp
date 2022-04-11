package woofareyou.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static woofareyou.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import woofareyou.commons.exceptions.IllegalValueException;
import woofareyou.commons.util.JsonUtil;
import woofareyou.model.PetBook;
import woofareyou.testutil.SimilarPets;
import woofareyou.testutil.TypicalPets;

public class JsonSerializablePetBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializablePetBookTest");
    private static final Path TYPICAL_PETS_FILE = TEST_DATA_FOLDER.resolve("typicalPetsPetBook.json");
    private static final Path INVALID_PET_FILE = TEST_DATA_FOLDER.resolve("invalidPetsPetBook.json");
    private static final Path SIMILAR_PET_FILE = TEST_DATA_FOLDER.resolve("similarPetsPetBook.json");

    @Test
    public void toModelType_typicalPetsFile_success() throws Exception {
        JsonSerializablePetBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PETS_FILE,
                JsonSerializablePetBook.class).get();
        PetBook petBookFromFile = dataFromFile.toModelType();
        PetBook typicalPetsPetBook = TypicalPets.getTypicalPetBook();
        assertEquals(petBookFromFile, typicalPetsPetBook);
    }

    @Test
    public void toModelType_invalidPetFile_throwsIllegalValueException() throws Exception {
        JsonSerializablePetBook dataFromFile = JsonUtil.readJsonFile(INVALID_PET_FILE,
                JsonSerializablePetBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_sameNamePetsDifferentOwner_success() throws Exception {
        JsonSerializablePetBook dataFromFile = JsonUtil.readJsonFile(SIMILAR_PET_FILE,
                JsonSerializablePetBook.class).get();
        PetBook petBookFromFile = dataFromFile.toModelType();
        PetBook similarPetsPetBook = SimilarPets.getSimilarPetBook();
        assertEquals(petBookFromFile, similarPetsPetBook);
    }

}
