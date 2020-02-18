package com.nifcompany.sidediguru.Get.GetSpesificReport;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class AverageClassReport{

	@SerializedName("ekstream")
	private String ekstream;

	@SerializedName("kekerasan")
	private String kekerasan;

	@SerializedName("intoleran")
	private String intoleran;

	@SerializedName("ekslusif")
	private String ekslusif;

	@SerializedName("valEkstream")
	private int valEkstream;

	@SerializedName("valKekerasan")
	private int valKekerasan;

	@SerializedName("valIntoleran")
	private int valIntoleran;

	@SerializedName("valEkslusif")
	private int valEkslusif;

	public int getValEkstream() {
		return valEkstream;
	}

	public void setValEkstream(int valEkstream) {
		this.valEkstream = valEkstream;
	}

	public int getValKekerasan() {
		return valKekerasan;
	}

	public void setValKekerasan(int valKekerasan) {
		this.valKekerasan = valKekerasan;
	}

	public int getValIntoleran() {
		return valIntoleran;
	}

	public void setValIntoleran(int valIntoleran) {
		this.valIntoleran = valIntoleran;
	}

	public int getValEkslusif() {
		return valEkslusif;
	}

	public void setValEkslusif(int valEkslusif) {
		this.valEkslusif = valEkslusif;
	}


	public void setEkstream(String ekstream){
		this.ekstream = ekstream;
	}

	public String getEkstream(){
		return ekstream;
	}

	public void setKekerasan(String kekerasan){
		this.kekerasan = kekerasan;
	}

	public String getKekerasan(){
		return kekerasan;
	}

	public void setIntoleran(String intoleran){
		this.intoleran = intoleran;
	}

	public String getIntoleran(){
		return intoleran;
	}

	public void setEkslusif(String ekslusif){
		this.ekslusif = ekslusif;
	}

	public String getEkslusif(){
		return ekslusif;
	}

	@Override
 	public String toString(){
		return 
			"AverageClassReport{" + 
			"ekstream = '" + ekstream + '\'' + 
			",kekerasan = '" + kekerasan + '\'' + 
			",intoleran = '" + intoleran + '\'' + 
			",ekslusif = '" + ekslusif + '\'' + 
			"}";
		}
}