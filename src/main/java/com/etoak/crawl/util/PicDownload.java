package com.etoak.crawl.util;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author shiyonghai
 * @version 1.0
 * @create 2018/2/14
 */
public class PicDownload {
    String urlLocation;
    String savePath;
    long start;
    long end;

    public PicDownload(String urlLocation, String savePath, long start, long end) {
        this.urlLocation = urlLocation;
        this.savePath = savePath;
        this.start = start;
        this.end = end;
    }

    public String getUrlLocation() {
        return urlLocation;
    }

    public void setUrlLocation(String urlLocation) {
        this.urlLocation = urlLocation;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public long getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}

