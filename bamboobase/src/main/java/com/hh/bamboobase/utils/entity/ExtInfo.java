package com.hh.bamboobase.utils.entity;


import com.alibaba.fastjson.JSON;
import com.hh.bamboobase.utils.json.JsonWriter;

/**
 * Created by andy on 2016/12/28.
 */

public class ExtInfo {

    public static class AudioExtInfo {

        private int duration;

        public static String getAudioExtInfoField() {
            return "duration";
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }
    }

    public static class FileExtInfo {
        private String fileTitle;
        private long fileSize;
        private String downloadName;

        public String getDownloadName() {
            return downloadName;
        }

        public void setDownloadName(String downloadName) {
            this.downloadName = downloadName;
        }

        public String getFileTitle() {
            return fileTitle;
        }

        public void setFileTitle(String fileTitle) {
            this.fileTitle = fileTitle;
        }

        public long getFileSize() {
            return fileSize;
        }

        public void setFileSize(long fileSize) {
            this.fileSize = fileSize;
        }

    }

    public static class PicExtInfo{
        private String ext;
        private String filename;
        private String hash;
        private float width;
        private float height;

        public float getWidth() {
            return width;
        }

        public void setWidth(float width) {
            this.width = width;
        }

        public float getHeight() {
            return height;
        }

        public void setHeight(float height) {
            this.height = height;
        }

        public String getExt() {
            return ext;
        }

        public void setExt(String ext) {
            this.ext = ext;
        }

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }
    }

    public static String createAudioExtInfo(String time) {
        JsonWriter jsonWriter = new JsonWriter();
        jsonWriter.beginObject();
        jsonWriter.name(AudioExtInfo.getAudioExtInfoField()).value(time);
        jsonWriter.endObject();
        return jsonWriter.close();
    }

    public static AudioExtInfo parseAudioExtInfo(String text) {
        return JSON.parseObject(text, AudioExtInfo.class);
    }

    public static FileExtInfo parseFileExtInfo(String text) {
        return JSON.parseObject(text, FileExtInfo.class);
    }

    public static PicExtInfo parsePicExtInfo(String text){
        return JSON.parseObject(text, PicExtInfo.class);
    }

}
