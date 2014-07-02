package org.geekulcha.Beans;
import java.io.Serializable;
import java.util.ArrayList;


public class Snippet implements Serializable {

	String title;
	String description;
	Thumbnails thumbnails;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Thumbnails getThumbnails() {
		return thumbnails;
	}
	public void setThumbnails(Thumbnails thumbnails) {
		this.thumbnails = thumbnails;
	}
	
			
}
