package org.geekulcha.Beans;
import java.io.Serializable;
import java.util.ArrayList;


public class JsonResponse implements Serializable {

	ArrayList<Items> items;

	public ArrayList<Items> getItems() {
		return items;
	}

	public void setItems(ArrayList<Items> items) {
		this.items = items;
	}
}
