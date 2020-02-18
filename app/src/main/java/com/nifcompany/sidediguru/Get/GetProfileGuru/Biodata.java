package com.nifcompany.sidediguru.Get.GetProfileGuru;

import android.os.Parcel;
import android.os.Parcelable;

public class Biodata implements Parcelable {
	private String institution;
	private String NIP;
	private String gender;
	private String image_profile;
	private String religion;

	public void setInstitution(String institution){
		this.institution = institution;
	}

	public String getInstitution(){
		return institution;
	}

	public void setNIP(String NIP){
		this.NIP = NIP;
	}

	public String getNIP(){
		return NIP;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setImageProfile(String image_profile){
		this.image_profile = image_profile;
	}

	public String getImageProfile(){
		return image_profile;
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
			",NIP = '" + NIP + '\'' +
			",gender = '" + gender + '\'' + 
			",image_profile = '" + image_profile + '\'' +
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
		dest.writeString(this.NIP);
		dest.writeString(this.religion);
		dest.writeString(this.image_profile);
		dest.writeString(this.gender);
	}

	public Biodata() {
	}

	protected Biodata(Parcel in) {
		this.institution = in.readString();
		this.NIP = in.readString();
		this.religion = in.readString();
		this.image_profile = in.readString();
		this.gender = in.readString();
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
