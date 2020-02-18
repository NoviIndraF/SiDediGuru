package com.nifcompany.sidediguru.Post.PostCreateClass;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class TeacherBiodata implements Parcelable {

	@SerializedName("institution")
	private String institution;

	@SerializedName("NIP")
	private String nIP;

	@SerializedName("gender")
	private String gender;

	@SerializedName("user_id")
	private int userId;

	@SerializedName("id")
	private int id;

	@SerializedName("religion")
	private String religion;

	public void setInstitution(String institution){
		this.institution = institution;
	}

	public String getInstitution(){
		return institution;
	}

	public void setNIP(String nIP){
		this.nIP = nIP;
	}

	public String getNIP(){
		return nIP;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setReligion(String religion){
		this.religion = religion;
	}

	public String getReligion(){
		return religion;
	}

	@Override
 	public String toString(){
		return 
			"TeacherBiodata{" + 
			"institution = '" + institution + '\'' + 
			",nIP = '" + nIP + '\'' + 
			",gender = '" + gender + '\'' + 
			",user_id = '" + userId + '\'' + 
			",id = '" + id + '\'' + 
			",religion = '" + religion + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.institution);
		dest.writeString(this.nIP);
		dest.writeString(this.gender);
		dest.writeInt(this.userId);
		dest.writeInt(this.id);
		dest.writeString(this.religion);
	}

	public TeacherBiodata() {
	}

	protected TeacherBiodata(Parcel in) {
		this.institution = in.readString();
		this.nIP = in.readString();
		this.gender = in.readString();
		this.userId = in.readInt();
		this.id = in.readInt();
		this.religion = in.readString();
	}

	public static final Parcelable.Creator<TeacherBiodata> CREATOR = new Parcelable.Creator<TeacherBiodata>() {
		@Override
		public TeacherBiodata createFromParcel(Parcel source) {
			return new TeacherBiodata(source);
		}

		@Override
		public TeacherBiodata[] newArray(int size) {
			return new TeacherBiodata[size];
		}
	};
}