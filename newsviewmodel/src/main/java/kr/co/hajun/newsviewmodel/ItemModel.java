package kr.co.hajun.newsviewmodel;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "article")
public class ItemModel {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String author;
    public String title;
    public String description;
    public String urlToImage;
    public String publishedAt;
}
