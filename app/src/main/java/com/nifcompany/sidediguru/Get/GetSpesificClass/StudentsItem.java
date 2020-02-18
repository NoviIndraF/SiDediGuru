package com.nifcompany.sidediguru.Get.GetSpesificClass;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class StudentsItem implements Parcelable {

	@SerializedName("student_name")
	private String studentName;

	@SerializedName("join_at")
	private String joinAt;

	@SerializedName("NISN")
	private String nISN;

	@SerializedName("gender")
	private String gender;

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

	public void setJoinAt(String joinAt){
		this.joinAt = joinAt;
	}

	public String getJoinAt(){
		return joinAt;
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
			",join_at = '" + joinAt + '\'' + 
			",nISN = '" + nISN + '\'' + 
			",gender = '" + gender + '\'' + 
			",student_id = '" + studentId + '\'' + 
			",age = '" + age + '\'' + 
			",religion = '" + religion + '\'' + 
			"}";
		}

	public StudentsItem() {
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.studentName);
		dest.writeString(this.joinAt);
		dest.writeString(this.nISN);
		dest.writeString(this.gender);
		dest.writeInt(this.studentId);
		dest.writeInt(this.age);
		dest.writeString(this.religion);
	}

	protected StudentsItem(Parcel in) {
		this.studentName = in.readString();
		this.joinAt = in.readString();
		this.nISN = in.readString();
		this.gender = in.readString();
		this.studentId = in.readInt();
		this.age = in.readInt();
		this.religion = in.readString();
	}

	public static final Creator<StudentsItem> CREATOR = new Creator<StudentsItem>() {
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