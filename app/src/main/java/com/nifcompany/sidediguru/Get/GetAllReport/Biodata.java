package com.nifcompany.sidediguru.Get.GetAllReport;

import com.google.gson.annotations.SerializedName;

public class Biodata{

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
}