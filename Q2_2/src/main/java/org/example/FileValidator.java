package org.example;
import java.io.File;
import java.io.IOException;
public class FileValidator {
    /**
     * Validates if the given file can be written to.
     * If the file doesn't exist, it tries to create and delete it to check permissions.
     * If the file exists, it checks if it is writable.
     *
     * @param file The file to validate.
     * @throws IOException If the file cannot be created, deleted, or written to.
     */
    public static void validatePermissions(File file) throws IOException {
        if (!file.exists()) {
            if (!file.createNewFile()) {
                throw new IOException("Permission Denied: Unable to create file.");
            }
            if (!file.delete()) {
                throw new IOException("Permission Denied: Unable to delete file.");
            }
        } else if (!file.canWrite()) {
            throw new IOException("Permission Denied: Unable to write file.");
        }
    }
}
