package org.geekulcha.Beans;
import java.io.Serializable;
import java.util.ArrayList;


public class Items implements Serializable {
	
	
	public Snippet getSnippet() {
		return snippet;
	}
	public void setSnippet(Snippet snippet) {
		this.snippet = snippet;
	}
	public VideoID getId() {
		return id;
	}
	public void setId(VideoID id) {
		this.id = id;
	}
	Snippet snippet;
	VideoID id;

}
