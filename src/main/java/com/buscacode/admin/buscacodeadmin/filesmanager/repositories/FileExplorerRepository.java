package com.buscacode.admin.buscacodeadmin.filesmanager.repositories;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.buscacode.admin.buscacodeadmin.filesmanager.handlers.exceptions.FileExistsException;

@Repository
public class FileExplorerRepository {
  @Value("${files.base-file-path:}")
  private String baseFilePath;
  @Value("${files.relative-path:}")
  private String relativePath;
  private final String DEFAULT_RELATIVE_PATH = "/uploaded-files";

  // Get current working directory as root /
  String currentWorkingDirectory = System.getProperty("user.dir");
  // Get the user directory as root
  String userHome = System.getProperty("user.home");

  public File saveMultipartFileToAbsolutePathAndName(MultipartFile multipartFile, String absoluteDirectoryPath,
      String fileName) {
    if (!absoluteDirectoryPath.endsWith(File.separator)) {
      absoluteDirectoryPath = absoluteDirectoryPath + File.separator;
    }

    String fullPathFile = absoluteDirectoryPath + fileName;
    File fileInSystem = new File(fullPathFile);
    if (fileInSystem.exists()) {
      throw new FileExistsException();
    }
    try {
      fileInSystem.getParentFile().mkdirs(); // this will create the necessary directories
      multipartFile.transferTo(fileInSystem);
    } catch (IllegalStateException | IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      if (fileInSystem.exists() && fileInSystem.delete()) {
        fileInSystem = null;
      }
    }
    return fileInSystem;
  }

  public File saveMultipartFileToAbsolutePath(MultipartFile multipartFile, String absolutePath, String extension) {
    String originalFilename = multipartFile.getOriginalFilename();
    if (originalFilename == null || originalFilename.isEmpty()) {
      String prefix = "file";
      String timestamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
      int randomNumber = new Random().nextInt(1000); // Adjust range based on your needs
      String filename = prefix + "_" + timestamp + "_" + randomNumber + "." + extension;
      originalFilename = filename;
    }
    return saveMultipartFileToAbsolutePathAndName(multipartFile, absolutePath, originalFilename);
  }

  public File saveMultipartFileToAbsolutePath(MultipartFile multipartFile, String absolutePath) {
    return saveMultipartFileToAbsolutePath(multipartFile, absolutePath, "txt");
  }

  /*
   * This will make the file saved in a directory called "others" instead of the
   * username
   */
  public File saveMultipartFile(MultipartFile multipartFile) {
    return saveMultipartFile(multipartFile, multipartFile.getOriginalFilename(), null);
  }

  /*
   * This will save the file into a directory of the username
   */

  public File saveMultipartFile(MultipartFile multipartFile, String fileName, String relativePath) {

    String originalFilename = fileName;
    String fullPathDirectory = fullDirectoryPath(relativePath);

    if (originalFilename == null || originalFilename.isEmpty()) {
      String name = multipartFile.getOriginalFilename();
      if (name == null || name.isEmpty()) {
        name = "keep.keep";
      }
      String extension = name.substring(multipartFile.getName().lastIndexOf(".") + 1);
      originalFilename = UUID.randomUUID().toString() + "." + extension;
    }

    File fileSaved = null;
    if (originalFilename == null || originalFilename.isBlank()) {
      fileSaved = saveMultipartFileToAbsolutePath(multipartFile, fullPathDirectory);
    } else {
      fileSaved = saveMultipartFileToAbsolutePathAndName(multipartFile, fullPathDirectory, originalFilename);
    }

    return fileSaved;
  }

  public String fullDirectoryPath(String relativePathInput) {
    String baseFilePath = "";
    String relativePath = "";

    if (this.baseFilePath != null && !this.baseFilePath.isEmpty()) {
      baseFilePath = this.baseFilePath;
    }
    if (this.relativePath != null && !this.relativePath.isEmpty()) {
      relativePath = this.relativePath;
    }

    if (baseFilePath.isBlank()) {
      baseFilePath = this.userHome;
    }
    if (relativePath.isBlank()) {
      relativePath = DEFAULT_RELATIVE_PATH;
    }
    if (relativePathInput != null && !relativePathInput.isBlank()) {
      relativePath = relativePath.concat(File.separator).concat(relativePathInput);
    } else {
      relativePath = relativePath.concat(File.separator).concat("others");
    }

    return baseFilePath + relativePath;
  }

  public String changeUserDirectoryName(String currentUsername, String newUsername) throws IOException {
    String currentUserDirectoryPath = fullDirectoryPath(currentUsername);
    String newtUserDirectoryPath = fullDirectoryPath(newUsername);

    Path sourceDirectory = Paths.get(currentUserDirectoryPath);
    Path targetDirectory = Paths.get(newtUserDirectoryPath);

    Path movedDirectory = Files.move(sourceDirectory, targetDirectory, StandardCopyOption.REPLACE_EXISTING);

    return movedDirectory.toAbsolutePath().toString();
  }

  public String sanitizeFileName(String fileName) {
    // Normalize to ensure consistent Unicode representation
    String sanitized = Normalizer.normalize(fileName, Normalizer.Form.NFC);

    // Remove invalid characters for file systems
    sanitized = sanitized.replaceAll("[<>:\"/\\\\|?*]", "");

    // Trim leading/trailing whitespace
    sanitized = sanitized.trim();

    // Replace spaces with underscores
    sanitized = sanitized.replaceAll("\\s+", "-");

    // Ensure the file name is not too long (255 characters max)
    if (sanitized.length() > 255) {
      sanitized = sanitized.substring(0, 255);
    }

    if (sanitized.isEmpty()) {
      sanitized = "keep2.keep2";
    }

    return sanitized;
  }
}
