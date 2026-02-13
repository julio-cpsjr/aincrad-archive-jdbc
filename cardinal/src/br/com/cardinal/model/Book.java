package br.com.cardinal.model;

public class Book {
    private int id;
    private String title;
    private String author;
    private String description;
    private String category;
    private StatusBook available;

    //Constructor Full (Repository)
    public Book(int id, String title, String author, String description, String category, StatusBook available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.category = category;
        this.available = available;
    }

    //Constructor of Creation (UI)
    public Book(String title, String author, String description, String category, StatusBook available){
        this.title = title;
        this.author = author;
        this.description = description;
        this.category = category;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public StatusBook getAvailable() {
        return available;
    }

    public void setAvailable(StatusBook available) {
        this.available = available;
    }
}
