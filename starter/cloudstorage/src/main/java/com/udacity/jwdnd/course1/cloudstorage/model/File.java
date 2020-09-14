package com.udacity.jwdnd.course1.cloudstorage.model;

public class File {

    private Integer fileId;
    private String fileName;
    private String contenttype;
    private String filesize;
    private Integer userId;
    private byte[] filedata;

    public File(String fileName, String contenttype, String filesize, Integer userId, byte[] filedata) {
        this.fileName = fileName;
        this.contenttype = contenttype;
        this.filesize = filesize;
        this.userId = userId;
        this.filedata = filedata;
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

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public byte[] getFiledata() {
        return filedata;
    }

    public void setFiledata(byte[] filedata) {
        this.filedata = filedata;
    }
}
