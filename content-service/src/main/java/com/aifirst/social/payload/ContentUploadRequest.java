package com.aifirst.social.payload;

import com.aifirst.social.model.ContentType;
import org.springframework.web.multipart.MultipartFile;

public class ContentUploadRequest {

    private MultipartFile file;
    private String caption;
    private ContentType type;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public ContentType getType() {
        return type;
    }

    public void setType(ContentType type) {
        this.type = type;
    }
}
