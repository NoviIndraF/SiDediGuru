package com.nifcompany.sidediguru.Get.GetSpesificReport;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Report implements Parcelable {

	@SerializedName("eksklusif")
	private int eksklusif;

	@SerializedName("eksklusif_teks")
	private String eksklusifTeks;

	@SerializedName("ekstream")
	private int ekstream;

	@SerializedName("kekerasan_teks")
	private String kekerasanTeks;

	@SerializedName("ekstream_teks")
	private String ekstreamTeks;

	@SerializedName("average")
	private double average;

	@SerializedName("intoleran_teks")
	private String intoleranTeks;

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

	public void setEksklusifTeks(String eksklusifTeks){
		this.eksklusifTeks = eksklusifTeks;
	}

	public String getEksklusifTeks(){
		return eksklusifTeks;
	}

	public void setEkstream(int ekstream){
		this.ekstream = ekstream;
	}

	public int getEkstream(){
		return ekstream;
	}

	public void setKekerasanTeks(String kekerasanTeks){
		this.kekerasanTeks = kekerasanTeks;
	}

	public String getKekerasanTeks(){
		return kekerasanTeks;
	}

	public void setEkstreamTeks(String ekstreamTeks){
		this.ekstreamTeks = ekstreamTeks;
	}

	public String getEkstreamTeks(){
		return ekstreamTeks;
	}

	public void setAverage(double average){
		this.average = average;
	}

	public double getAverage(){
		return average;
	}

	public void setIntoleranTeks(String intoleranTeks){
		this.intoleranTeks = intoleranTeks;
	}

	public String getIntoleranTeks(){
		return intoleranTeks;
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
			",eksklusif_teks = '" + eksklusifTeks + '\'' + 
			",ekstream = '" + ekstream + '\'' + 
			",kekerasan_teks = '" + kekerasanTeks + '\'' + 
			",ekstream_teks = '" + ekstreamTeks + '\'' + 
			",average = '" + average + '\'' + 
			",intoleran_teks = '" + intoleranTeks + '\'' + 
			",kekerasan = '" + kekerasan + '\'' + 
			",report_id = '" + reportId + '\'' + 
			",intoleran = '" + intoleran + '\'' + 
			",submit_date = '" + submitDate + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.eksklusif);
		dest.writeString(this.eksklusifTeks);
		dest.writeInt(this.ekstream);
		dest.writeString(this.kekerasanTeks);
		dest.writeString(this.ekstreamTeks);
		dest.writeDouble(this.average);
		dest.writeString(this.intoleranTeks);
		dest.writeInt(this.kekerasan);
		dest.writeInt(this.reportId);
		dest.writeInt(this.intoleran);
		dest.writeString(this.submitDate);
	}

	public Report() {
	}

	protected Report(Parcel in) {
		this.eksklusif = in.readInt();
		this.eksklusifTeks = in.readString();
		this.ekstream = in.readInt();
		this.kekerasanTeks = in.readString();
		this.ekstreamTeks = in.readString();
		this.average = in.readDouble();
		this.intoleranTeks = in.readString();
		this.kekerasan = in.readInt();
		this.reportId = in.readInt();
		this.intoleran = in.readInt();
		this.submitDate = in.readString();
	}

	public static final Parcelable.Creator<Report> CREATOR = new Parcelable.Creator<Report>() {
		@Override
		public Report createFromParcel(Parcel source) {
			return new Report(source);
		}

		@Override
		public Report[] newArray(int size) {
			return new Report[size];
		}
	};
}