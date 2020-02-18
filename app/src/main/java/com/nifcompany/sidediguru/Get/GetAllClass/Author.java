package com.nifcompany.sidediguru.Get.GetAllClass;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Author implements Parcelable {

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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.joined);
		dest.writeString(this.name);
		dest.writeParcelable(this.biodata, flags);
		dest.writeInt(this.id);
		dest.writeString(this.email);
	}

	public Author() {
	}

	protected Author(Parcel in) {
		this.joined = in.readString();
		this.name = in.readString();
		this.biodata = in.readParcelable(Biodata.class.getClassLoader());
		this.id = in.readInt();
		this.email = in.readString();
	}

	public static final Parcelable.Creator<Author> CREATOR = new Parcelable.Creator<Author>() {
		@Override
		public Author createFromParcel(Parcel source) {
			return new Author(source);
		}

		@Override
		public Author[] newArray(int size) {
			return new Author[size];
		}
	};
}