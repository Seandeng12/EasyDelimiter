package net.seandeng.delimiter.read.metadata;

public class ReadFile extends ReadBasicParameter {

    private String fileDir;
    private String fileName;

    public ReadFile() {}

    public ReadFile(String fileName) {
        this.fileName = fileName;
    }

    public ReadFile(String fileName, String fileDir) {
        this.fileName = fileName;
        this.fileDir = fileDir;
    }

    public String getFileDir() {
        return fileDir;
    }

    public void setFileDir(String fileDir) {
        this.fileDir = fileDir;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
