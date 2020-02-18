package com.nifcompany.sidediguru.Get.GetSpesificClass;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Biodata implements Parcelable {

	@SerializedName("institution")
	private String institution;

	@SerializedName("NIP")
	private String nIP;

	@SerializedName("gender")
	private String gender;

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

	public void setReligion(String religion){
		this.religion = religion;
	}

	public String getReligion(){
		return religion;
	}

	@Override
 	public String toString(){
		return 
			"Biodata{" + 
			"institution = '" + institution + '\'' + 
			",nIP = '" + nIP + '\'' + 
			",gender = '" + gender + '\'' + 
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
		dest.writeString(this.religion);
	}

	public Biodata() {
	}

	protected Biodata(Parcel in) {
		this.institution = in.readString();
		this.nIP = in.readString();
		this.gender = in.readString();
		this.religion = in.readString();
	}

	public static final Parcelable.Creator<Biodata> CREATOR = new Parcelable.Creator<Biodata>() {
		@Override
		public Biodata createFromParcel(Parcel source) {
			return new Biodata(source);
		}

		@Override
		public Biodata[] newArray(int size) {
			return new Biodata[size];
		}
	};
}