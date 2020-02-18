package com.nifcompany.sidediguru.Post.PostPasswordForgot;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Biodata{

	@SerializedName("institution")
	private String institution;

	@SerializedName("NIP")
	private String nIP;

	@SerializedName("gender")
	private String gender;

	@SerializedName("image_profile")
	private String imageProfile;

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

	public void setImageProfile(String imageProfile){
		this.imageProfile = imageProfile;
	}

	public String getImageProfile(){
		return imageProfile;
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
			",image_profile = '" + imageProfile + '\'' + 
			",religion = '" + religion + '\'' + 
			"}";
		}
}