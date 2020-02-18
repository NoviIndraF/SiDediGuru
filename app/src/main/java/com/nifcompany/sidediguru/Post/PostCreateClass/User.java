package com.nifcompany.sidediguru.Post.PostCreateClass;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class User implements Parcelable {

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("api_token")
	private String apiToken;

	@SerializedName("name")
	private String name;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("email_verified_at")
	private Object emailVerifiedAt;

	@SerializedName("id")
	private int id;

	@SerializedName("email")
	private String email;

	@SerializedName("teacher_biodata")
	private TeacherBiodata teacherBiodata;

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setApiToken(String apiToken){
		this.apiToken = apiToken;
	}

	public String getApiToken(){
		return apiToken;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setEmailVerifiedAt(Object emailVerifiedAt){
		this.emailVerifiedAt = emailVerifiedAt;
	}

	public Object getEmailVerifiedAt(){
		return emailVerifiedAt;
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

	public void setTeacherBiodata(TeacherBiodata teacherBiodata){
		this.teacherBiodata = teacherBiodata;
	}

	public TeacherBiodata getTeacherBiodata(){
		return teacherBiodata;
	}

	@Override
 	public String toString(){
		return 
			"User{" + 
			"updated_at = '" + updatedAt + '\'' + 
			",api_token = '" + apiToken + '\'' + 
			",name = '" + name + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",email_verified_at = '" + emailVerifiedAt + '\'' + 
			",id = '" + id + '\'' + 
			",email = '" + email + '\'' + 
			",teacher_biodata = '" + teacherBiodata + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.updatedAt);
		dest.writeString(this.apiToken);
		dest.writeString(this.name);
		dest.writeString(this.createdAt);
		dest.writeParcelable((Parcelable) this.emailVerifiedAt, flags);
		dest.writeInt(this.id);
		dest.writeString(this.email);
		dest.writeParcelable(this.teacherBiodata, flags);
	}

	public User() {
	}

	protected User(Parcel in) {
		this.updatedAt = in.readString();
		this.apiToken = in.readString();
		this.name = in.readString();
		this.createdAt = in.readString();
		this.emailVerifiedAt = in.readParcelable(Object.class.getClassLoader());
		this.id = in.readInt();
		this.email = in.readString();
		this.teacherBiodata = in.readParcelable(TeacherBiodata.class.getClassLoader());
	}

	public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
		@Override
		public User createFromParcel(Parcel source) {
			return new User(source);
		}

		@Override
		public User[] newArray(int size) {
			return new User[size];
		}
	};
}