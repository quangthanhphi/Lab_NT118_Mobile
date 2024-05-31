package vn.dlu.truyencuoi.classes;

public class StoryEntity {
    private String title;
    private String thumbnail;
    private String content;

    public StoryEntity(String title, String thumbnailPath, String content) {
        this.title = title;
        this.thumbnail = thumbnailPath;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getContent() {
        return content;
    }
}