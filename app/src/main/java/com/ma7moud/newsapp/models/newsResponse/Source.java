package com.ma7moud.newsapp.models.newsResponse;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Source implements Serializable {

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private String id;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}
}