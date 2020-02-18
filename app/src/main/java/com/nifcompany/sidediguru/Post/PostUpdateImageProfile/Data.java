package com.nifcompany.sidediguru.Post.PostUpdateImageProfile;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Data{

	@SerializedName("join_at")
	private String joinAt;

	@SerializedName("user_id")
	private int userId;

	@SerializedName("name")
	private String name;

	@SerializedName("biodata")
	private Biodata biodata;

	@SerializedName("email")
	private String email;

	public void setJoinAt(String joinAt){
		this.joinAt = joinAt;
	}

	public String getJoinAt(){
		return joinAt;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
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

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"join_at = '" + joinAt + '\'' + 
			",user_id = '" + userId + '\'' + 
			",name = '" + name + '\'' + 
			",biodata = '" + biodata + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}