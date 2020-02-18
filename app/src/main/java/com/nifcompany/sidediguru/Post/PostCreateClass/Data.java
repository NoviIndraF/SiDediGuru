package com.nifcompany.sidediguru.Post.PostCreateClass;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Data implements Parcelable {

	@SerializedName("name_class")
	private String nameClass;

	@SerializedName("path_img_header")
	private String pathImgHeader;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("user_id")
	private int userId;

	@SerializedName("code_ref_class")
	private String codeRefClass;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("user")
	private User user;

	public void setNameClass(String nameClass){
		this.nameClass = nameClass;
	}

	public String getNameClass(){
		return nameClass;
	}

	public void setPathImgHeader(String pathImgHeader){
		this.pathImgHeader = pathImgHeader;
	}

	public String getPathImgHeader(){
		return pathImgHeader;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setCodeRefClass(String codeRefClass){
		this.codeRefClass = codeRefClass;
	}

	public String getCodeRefClass(){
		return codeRefClass;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"name_class = '" + nameClass + '\'' + 
			",path_img_header = '" + pathImgHeader + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",user_id = '" + userId + '\'' + 
			",code_ref_class = '" + codeRefClass + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",user = '" + user + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.nameClass);
		dest.writeString(this.pathImgHeader);
		dest.writeString(this.updatedAt);
		dest.writeInt(this.userId);
		dest.writeString(this.codeRefClass);
		dest.writeString(this.createdAt);
		dest.writeInt(this.id);
		dest.writeParcelable(this.user, flags);
	}

	public Data() {
	}

	protected Data(Parcel in) {
		this.nameClass = in.readString();
		this.pathImgHeader = in.readString();
		this.updatedAt = in.readString();
		this.userId = in.readInt();
		this.codeRefClass = in.readString();
		this.createdAt = in.readString();
		this.id = in.readInt();
		this.user = in.readParcelable(User.class.getClassLoader());
	}

	public static final Parcelable.Creator<Data> CREATOR = new Parcelable.Creator<Data>() {
		@Override
		public Data createFromParcel(Parcel source) {
			return new Data(source);
		}

		@Override
		public Data[] newArray(int size) {
			return new Data[size];
		}
	};
}