package ch.hackzurich.zoozurich.core;


public class Info {
    private String text;
    private int imageResourceId;

    public Info(String _text, int _imageResourceId) {
        text = _text;
        imageResourceId = _imageResourceId;
    }

    public String getText() {
        return text;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
