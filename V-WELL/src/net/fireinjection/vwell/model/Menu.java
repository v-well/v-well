package net.fireinjection.vwell.model;

import android.graphics.drawable.Drawable;

public class Menu {
	public static final int ID_HOME = 0;
	public static final int ID_NOTE = 1;
	public static final int ID_LOCATION = 2;
	public static final int ID_SEARCH = 3;
	
	private int id;
	private Drawable drawable;
	
	public Menu(int id, Drawable drawable){
		this.id = id;
		this.drawable = drawable;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Drawable getDrawable() {
		return drawable;
	}
	public void setDrawable(Drawable drawable) {
		this.drawable = drawable;
	}
	
}
