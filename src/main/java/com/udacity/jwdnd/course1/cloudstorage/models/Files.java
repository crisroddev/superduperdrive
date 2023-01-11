package com.udacity.jwdnd.course1.cloudstorage.models;

public class Files {
  private Integer fileId;
  private String fileName;
  private String fileType;
  private Long fileSize;
  private byte[] fileData;
  private Integer userId;

  public Files() {}

  public Files(String fileName, String fileType, Long fileSize, byte[] fileData, Integer userId) {
    this.fileName = fileName;
    this.fileType = fileType;
    this.fileSize = fileSize;
    this.fileData = fileData;
    this.userId = userId;
  }

  public Integer getFileId() {
    return fileId;
  }

  public void setFileId(Integer fileId) {
    this.fileId = fileId;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getFileType() {
    return fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }

  public Long getFileSize() {
    return fileSize;
  }

  public void setFileSize(Long fileSize) {
    this.fileSize = fileSize;
  }

  public byte[] getFileData() {
    return fileData;
  }

  public void setFileData(byte[] fileData) {
    this.fileData = fileData;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }
}
