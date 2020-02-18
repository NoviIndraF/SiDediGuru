package com.nifcompany.sidediguru.Get.GetSpesificReport;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Meta{

	@SerializedName("kecenderunganNegatif")
	private KecenderunganNegatif kecenderunganNegatif;

	@SerializedName("averageClassReport")
	private AverageClassReport averageClassReport;

	@SerializedName("kecenderunganPositif")
	private KecenderunganPositif kecenderunganPositif;

	public void setKecenderunganNegatif(KecenderunganNegatif kecenderunganNegatif){
		this.kecenderunganNegatif = kecenderunganNegatif;
	}

	public KecenderunganNegatif getKecenderunganNegatif(){
		return kecenderunganNegatif;
	}

	public void setAverageClassReport(AverageClassReport averageClassReport){
		this.averageClassReport = averageClassReport;
	}

	public AverageClassReport getAverageClassReport(){
		return averageClassReport;
	}

	public void setKecenderunganPositif(KecenderunganPositif kecenderunganPositif){
		this.kecenderunganPositif = kecenderunganPositif;
	}

	public KecenderunganPositif getKecenderunganPositif(){
		return kecenderunganPositif;
	}

	@Override
 	public String toString(){
		return 
			"Meta{" + 
			"kecenderunganNegatif = '" + kecenderunganNegatif + '\'' + 
			",averageClassReport = '" + averageClassReport + '\'' + 
			",kecenderunganPositif = '" + kecenderunganPositif + '\'' + 
			"}";
		}
}