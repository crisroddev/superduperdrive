package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.Data;

@Data
public class File {
  private Integer fileId;
  private String name;
  private String contentType;
  private String size;
  private Integer userId;
  private byte [] data;

  public File(){}
  public File(Integer fileId, String name, String contentType, String size, Integer userId, byte [] data) {
    this.fileId = fileId;
    this.name = name;
    this.contentType = contentType;
    this.size = size;
    this.userId = userId;
    this.data = data;
  }
}
