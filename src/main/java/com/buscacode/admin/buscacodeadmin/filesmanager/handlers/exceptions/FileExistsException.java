package com.buscacode.admin.buscacodeadmin.filesmanager.handlers.exceptions;

public class FileExistsException extends RuntimeException {
  public FileExistsException() {
    super("File already exist.");
  }
  public FileExistsException(String message) {
    super(message);
  }
}
