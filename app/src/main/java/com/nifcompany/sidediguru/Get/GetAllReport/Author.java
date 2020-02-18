package com.nifcompany.sidediguru.Get.GetAllReport;

import com.google.gson.annotations.SerializedName;

public class Author{

	@SerializedName("joined")
	private String joined;

	@SerializedName("name")
	private String name;

	@SerializedName("biodata")
	private Biodata biodata;

	@SerializedName("id")
	private int id;

	@SerializedName("email")
	private String email;

	public void setJoined(String joined){
		this.joined = joined;
	}

	public String getJoined(){
		return joined;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setBiodata(Biodata biodata){
		this.biodata = biodata;
	}

	public Biodata getBiodata(){
		return biodata;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	@Override
 	public String toString(){
		return 
			"Author{" + 
			"joined = '" + joined + '\'' + 
			",name = '" + name + '\'' + 
			",biodata = '" + biodata + '\'' + 
			",id = '" + id + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}