package com.nifcompany.sidediguru.Get.GetProfileGuru;

import android.os.Parcel;
import android.os.Parcelable;

public class Data implements Parcelable {
	private String joinAt;
	private int userId;
	private String name;
	private Biodata biodata;
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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.joinAt);
		dest.writeInt(this.userId);
		dest.writeString(this.name);
		dest.writeParcelable(this.biodata, flags);
		dest.writeString(this.email);
	}

	public Data() {
	}

	protected Data(Parcel in) {
		this.joinAt = in.readString();
		this.userId = in.readInt();
		this.name = in.readString();
		this.biodata = in.readParcelable(Biodata.class.getClassLoader());
		this.email = in.readString();
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
