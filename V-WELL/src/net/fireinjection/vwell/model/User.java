package net.fireinjection.vwell.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="User")
public class User {
	@DatabaseField(columnName = "userId", generatedId=true) private Integer id;
	@DatabaseField private String name;
	@DatabaseField private String imageUrl;
	
	public User(){}
	
	public User(int id, String name, String imageUrl){
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
}
