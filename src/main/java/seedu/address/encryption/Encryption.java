package seedu.address.encryption;

import java.io.IOException;
import java.nio.file.Path;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

public interface Encryption {
    void encryptFile(Path sourceFile, Path targetFile) throws InvalidKeyException, IOException;

    void decryptFile(Path encryptedFile, Path targetFile) throws InvalidAlgorithmParameterException,
            InvalidKeyException, IOException;
}
