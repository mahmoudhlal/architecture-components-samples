package com.ma7moud.newsapp.models.SourcesResponse;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SourcesResponse{

	@SerializedName("sources")
	private List<SourcesItem> sources;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

	public void setSources(List<SourcesItem> sources){
		this.sources = sources;
	}

	public List<SourcesItem> getSources(){
		return sources;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}