package com.example.videogameclient;
import javax.persistence.*;
import java.util.Date;

@Entity
public class VideogameEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String game_name;
    private String game_description;
    private String publisher;
    private String developer;
    private String writer;
    private String category;
    private float price;
    private Date release_date;
    private boolean is_deleted;

    public VideogameEntity(){}

    public VideogameEntity(String game_name, String game_description, String publisher, String developer,
                           String writer,String category,float price, Date release_date){
        setName(game_name);
        setDescription(game_description);
        setPublisher(publisher);
        setDeveloper(developer);
        setWriter(writer);
        setCategory(category);
        setPrice(price);
        setInitialReleaseDate(release_date);
        this.is_deleted = false;
    }
    public void setId(Integer id){ this.id = id; }
    public Integer getId() { return this.id; }

    public String getName() { return game_name; }
    public void setName(String name) { this.game_name = name; }

    public String getDescription() { return game_description; }
    public void setDescription(String description) { game_description = description; }


    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    public String getDeveloper() { return developer; }
    public void setDeveloper(String developer) { this.developer = developer; }

    public String getWriter() { return writer; }
    public void setWriter(String writer) { this.writer = writer; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public float getPrice() { return price; }
    public void setPrice(float price) { this.price = price; }

    public Date getInitialReleaseDate() { return release_date; }
    public void setInitialReleaseDate(Date release_date) { this.release_date = release_date; }

    public boolean isDeleted() { return is_deleted; }
    public void setDeleted(boolean deleted) { is_deleted = deleted; }

    @Override
    public String toString() {
        return String.format("Game: ", this.getName(), this.getPrice(), this.getInitialReleaseDate());
    }
}

