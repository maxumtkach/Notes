package com.example.notes;

public class ItemData {

    private String title;
    private String subtitle;
private String deadline;

    public ItemData(String title, String subtitle, String deadline) {
        this.title = title;
        this.subtitle = subtitle;
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "ItemData{" +
                "title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", deadline='" + deadline + '\'' +
                '}';
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

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}
