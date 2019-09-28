package ch.hackzurich.zoozurich.core;


public class Info {
    private String text;
    private String filename;

    public Info(String _text, String _filename) {
        text = _text;
        filename = _filename;
    }

    public String getText() {
        return text;
    }

    public String getFilename() {
        return filename;
    }
}
