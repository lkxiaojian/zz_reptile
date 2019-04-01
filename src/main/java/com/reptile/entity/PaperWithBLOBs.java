package com.reptile.entity;

public class PaperWithBLOBs extends Paper {
    private String imagePath;

    private byte[] detailsTxt;

    private byte[] detailsDiv;

    private String reference;

    private String txt;
    private int num;

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath == null ? null : imagePath.trim();
    }

    public byte[] getDetailsTxt() {
        return detailsTxt;
    }

    public void setDetailsTxt(byte[] detailsTxt) {
        this.detailsTxt = detailsTxt;
    }

    public byte[] getDetailsDiv() {
        return detailsDiv;
    }

    public void setDetailsDiv(byte[] detailsDiv) {
        this.detailsDiv = detailsDiv;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference == null ? null : reference.trim();
    }
}
