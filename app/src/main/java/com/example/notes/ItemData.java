package com.example.notes;

public class ItemData {

    private String title;
    private String subtitle;

    public ItemData(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    @Override
    public String toString() {
        return "ItemData{" +
                "title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                '}';
    }
}
