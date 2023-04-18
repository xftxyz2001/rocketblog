package com.xftxyz.rocketblog.pojo;

import java.util.Date;

public class FileInfo {
    private final String filePath;
    private final Long fileSize;
    private final Date lastModifiedTime;

    public FileInfo(String filePath, Long fileSize, Date lastModifiedTime) {
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.lastModifiedTime = lastModifiedTime;
    }

    public String getFilePath() {
        return filePath;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }
}
