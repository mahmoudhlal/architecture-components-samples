package com.ma7moud.newsapp.models.SourcesResponse;

import com.google.gson.annotations.SerializedName;

public class SourcesItem{

	@SerializedName("country")
	private String country;

	@SerializedName("name")
	private String name;

	@SerializedName("description")
	private String description;

	@SerializedName("language")
	private String language;

	@SerializedName("id")
	private String id;

	@SerializedName("category")
	private String category;

	@SerializedName("url")
	private String url;

	@Override
	public String toString() {
		return getName();
	}

	public SourcesItem(String name, String id) {
		this.name = name;
		this.id = id;
	}

	public SourcesItem() {
	}

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setLanguage(String language){
		this.language = language;
	}

	public String getLanguage(){
		return language;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}
}