package com.buscacode.admin.buscacodeadmin.filesmanager.repositories;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

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

  //Get current working directory as root /
  String currentWorkingDirectory = System.getProperty("user.dir");
  //Get the user directory as root
  String userHome = System.getProperty("user.home");

  public File saveMultipartFileToAbsolutePathAndName(MultipartFile multiparFile, String absoluteDirectoryPath, String fileName) {
    if (!absoluteDirectoryPath.endsWith(File.separator)) {
      absoluteDirectoryPath = absoluteDirectoryPath + File.separator;
    }
    
    String fullPathFile = absoluteDirectoryPath + fileName;
    File fileInSystem = new File(fullPathFile);
    
    if(fileInSystem.exists()){
      throw new FileExistsException();
    }
    try {
      fileInSystem.getParentFile().mkdirs(); // this will create the necessary directories
      multiparFile.transferTo(fileInSystem);
    } catch (IllegalStateException | IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      if(fileInSystem.exists() && fileInSystem.delete()){
        fileInSystem = null;
      }
    }
    return fileInSystem;
  }

  public File saveMultipartFileToAbsolutePath(MultipartFile multiparFile, String absolutePath, String extension){
    String originalFilename = multiparFile.getOriginalFilename();
    if(originalFilename == null || originalFilename.isEmpty()) {
      String prefix = "file";
      String timestamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
      int randomNumber = new Random().nextInt(1000); // Adjust range based on your needs
      String filename = prefix + "_" + timestamp + "_" + randomNumber + "." + extension;
      originalFilename = filename;
    }
    return saveMultipartFileToAbsolutePathAndName(multiparFile, absolutePath, originalFilename);
  }
  public File saveMultipartFileToAbsolutePath(MultipartFile multiparFile, String absolutePath){
    return saveMultipartFileToAbsolutePath(multiparFile, absolutePath, "txt");
  }

  public File saveMultipartFile(MultipartFile multiparFile){
    String baseFilePath = "";
    String relativePath = "";
    

    if(this.baseFilePath != null && !this.baseFilePath.isEmpty()){
      baseFilePath = this.baseFilePath;
    }
    if(this.relativePath != null && !this.relativePath.isEmpty()){
      relativePath = this.relativePath;
    }

    if(baseFilePath.isBlank()){
      baseFilePath = this.userHome;
    }
    if(relativePath.isBlank()){
      relativePath = DEFAULT_RELATIVE_PATH;
    }

    String originalFilename = multiparFile.getOriginalFilename();
    String fullPathDirectory = baseFilePath + relativePath;
    // System.out.println("fullPathDirectory ===>  "+ fullPathDirectory);
    // System.out.println("originalFilename  ===>  "+ originalFilename);
    // System.out.println("currentWorkingDirectory  ===>  "+ currentWorkingDirectory);
    // System.out.println("userHome  ===>  "+ userHome);
    File fileSaved = null;
    if(originalFilename == null || originalFilename.isBlank()){
      fileSaved = saveMultipartFileToAbsolutePath(multiparFile,fullPathDirectory);
    } else {
      fileSaved = saveMultipartFileToAbsolutePathAndName(multiparFile,fullPathDirectory, originalFilename);
    }

    return fileSaved;
  }

}
