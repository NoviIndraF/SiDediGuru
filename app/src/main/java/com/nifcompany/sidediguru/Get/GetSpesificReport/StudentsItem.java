package com.nifcompany.sidediguru.Get.GetSpesificReport;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class StudentsItem implements Parcelable {

	@SerializedName("student_name")
	private String studentName;

	@SerializedName("NISN")
	private String nISN;

	@SerializedName("gender")
	private String gender;

	@SerializedName("report")
	private Report report;

	@SerializedName("answers")
	private List<AnswersItem> answers;

	@SerializedName("student_id")
	private int studentId;

	@SerializedName("age")
	private int age;

	@SerializedName("religion")
	private String religion;

	public void setStudentName(String studentName){
		this.studentName = studentName;
	}

	public String getStudentName(){
		return studentName;
	}

	public void setNISN(String nISN){
		this.nISN = nISN;
	}

	public String getNISN(){
		return nISN;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setReport(Report report){
		this.report = report;
	}

	public Report getReport(){
		return report;
	}

	public void setAnswers(List<AnswersItem> answers){
		this.answers = answers;
	}

	public List<AnswersItem> getAnswers(){
		return answers;
	}

	public void setStudentId(int studentId){
		this.studentId = studentId;
	}

	public int getStudentId(){
		return studentId;
	}

	public void setAge(int age){
		this.age = age;
	}

	public int getAge(){
		return age;
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
			"StudentsItem{" + 
			"student_name = '" + studentName + '\'' + 
			",nISN = '" + nISN + '\'' + 
			",gender = '" + gender + '\'' + 
			",report = '" + report + '\'' + 
			",answers = '" + answers + '\'' + 
			",student_id = '" + studentId + '\'' + 
			",age = '" + age + '\'' + 
			",religion = '" + religion + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.studentName);
		dest.writeString(this.nISN);
		dest.writeString(this.gender);
		dest.writeParcelable(this.report, flags);
		dest.writeList(this.answers);
		dest.writeInt(this.studentId);
		dest.writeInt(this.age);
		dest.writeString(this.religion);
	}

	public StudentsItem() {
	}

	protected StudentsItem(Parcel in) {
		this.studentName = in.readString();
		this.nISN = in.readString();
		this.gender = in.readString();
		this.report = in.readParcelable(Report.class.getClassLoader());
		this.answers = new ArrayList<AnswersItem>();
		in.readList(this.answers, AnswersItem.class.getClassLoader());
		this.studentId = in.readInt();
		this.age = in.readInt();
		this.religion = in.readString();
	}

	public static final Parcelable.Creator<StudentsItem> CREATOR = new Parcelable.Creator<StudentsItem>() {
		@Override
		public StudentsItem createFromParcel(Parcel source) {
			return new StudentsItem(source);
		}

		@Override
		public StudentsItem[] newArray(int size) {
			return new StudentsItem[size];
		}
	};
}