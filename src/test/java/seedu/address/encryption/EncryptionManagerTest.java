package seedu.address.encryption;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.FileUtil;

class EncryptionManagerTest {
    @Test
    public void whenEncryptingIntoFile_andDecryptingFileAgain_thenOriginalStringIsReturned()
            throws NoSuchAlgorithmException, NoSuchPaddingException, IOException, InvalidKeyException,
            InvalidAlgorithmParameterException {
        String originalContent = "foobar";
        Path encryptedFilePath = Path.of("baz.enc");
        Path sourceFilePath = Path.of("test.txt");
        FileUtil.writeToFile(sourceFilePath, originalContent);

        SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();

        EncryptionManager fileEncrypterDecrypter =
                new EncryptionManager(secretKey, "AES/CBC/PKCS5Padding");
        fileEncrypterDecrypter.encryptFile(sourceFilePath, encryptedFilePath);

        fileEncrypterDecrypter.decryptFile(encryptedFilePath, sourceFilePath);
        String decryptedContent = FileUtil.readFromFile(sourceFilePath);
        assertEquals(decryptedContent, originalContent);

        encryptedFilePath.toFile().delete(); // cleanup
        sourceFilePath.toFile().delete(); // cleanup
    }
}
