package com.nifcompany.sidediguru.Get.GetAllReport;

import com.google.gson.annotations.SerializedName;

public class Report{

	@SerializedName("eksklusif")
	private int eksklusif;

	@SerializedName("ekstream")
	private int ekstream;

	@SerializedName("kekerasan")
	private int kekerasan;

	@SerializedName("report_id")
	private int reportId;

	@SerializedName("intoleran")
	private int intoleran;

	@SerializedName("submit_date")
	private String submitDate;

	public void setEksklusif(int eksklusif){
		this.eksklusif = eksklusif;
	}

	public int getEksklusif(){
		return eksklusif;
	}

	public void setEkstream(int ekstream){
		this.ekstream = ekstream;
	}

	public int getEkstream(){
		return ekstream;
	}

	public void setKekerasan(int kekerasan){
		this.kekerasan = kekerasan;
	}

	public int getKekerasan(){
		return kekerasan;
	}

	public void setReportId(int reportId){
		this.reportId = reportId;
	}

	public int getReportId(){
		return reportId;
	}

	public void setIntoleran(int intoleran){
		this.intoleran = intoleran;
	}

	public int getIntoleran(){
		return intoleran;
	}

	public void setSubmitDate(String submitDate){
		this.submitDate = submitDate;
	}

	public String getSubmitDate(){
		return submitDate;
	}

	@Override
 	public String toString(){
		return 
			"Report{" + 
			"eksklusif = '" + eksklusif + '\'' + 
			",ekstream = '" + ekstream + '\'' + 
			",kekerasan = '" + kekerasan + '\'' + 
			",report_id = '" + reportId + '\'' + 
			",intoleran = '" + intoleran + '\'' + 
			",submit_date = '" + submitDate + '\'' + 
			"}";
		}
}