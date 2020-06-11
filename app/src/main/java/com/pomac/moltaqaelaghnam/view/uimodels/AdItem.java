package com.pomac.moltaqaelaghnam.view.uimodels;

public class AdItem {

    private String title;

    private String phone;

    private String adDateTime;

    private String area;

    private String imagePath;

    public AdItem(String title, String phone, String adDateTime, String area, String imagePath) {
        this.title = title;
        this.phone = phone;
        this.adDateTime = adDateTime;
        this.area = area;
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdDateTime() {
        return adDateTime;
    }

    public void setAdDateTime(String adDateTime) {
        this.adDateTime = adDateTime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
