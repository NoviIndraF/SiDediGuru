package com.nifcompany.sidediguru.Get.GetSpesificClass;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Data implements Parcelable {

	@SerializedName("code_refferal")
	private String codeRefferal;

	@SerializedName("img_url")
	private String imgUrl;

	@SerializedName("author")
	private Author author;

	@SerializedName("class_id")
	private int classId;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("students")
	private int students;

	@SerializedName("class_name")
	private String className;

	public void setCodeRefferal(String codeRefferal){
		this.codeRefferal = codeRefferal;
	}

	public String getCodeRefferal(){
		return codeRefferal;
	}

	public void setImgUrl(String imgUrl){
		this.imgUrl = imgUrl;
	}

	public String getImgUrl(){
		return imgUrl;
	}

	public void setAuthor(Author author){
		this.author = author;
	}

	public Author getAuthor(){
		return author;
	}

	public void setClassId(int classId){
		this.classId = classId;
	}

	public int getClassId(){
		return classId;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setStudents(int students){
		this.students = students;
	}

	public int getStudents(){
		return students;
	}

	public void setClassName(String className){
		this.className = className;
	}

	public String getClassName(){
		return className;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"code_refferal = '" + codeRefferal + '\'' + 
			",img_url = '" + imgUrl + '\'' + 
			",author = '" + author + '\'' + 
			",class_id = '" + classId + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",students = '" + students + '\'' + 
			",class_name = '" + className + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.codeRefferal);
		dest.writeString(this.imgUrl);
		dest.writeParcelable(this.author, flags);
		dest.writeInt(this.classId);
		dest.writeString(this.createdAt);
		dest.writeInt(this.students);
		dest.writeString(this.className);
	}

	public Data() {
	}

	protected Data(Parcel in) {
		this.codeRefferal = in.readString();
		this.imgUrl = in.readString();
		this.author = in.readParcelable(Author.class.getClassLoader());
		this.classId = in.readInt();
		this.createdAt = in.readString();
		this.students = in.readInt();
		this.className = in.readString();
	}

	public static final Creator<Data> CREATOR = new Creator<Data>() {
		@Override
		public Data createFromParcel(Parcel source) {
			return new Data(source);
		}

		@Override
		public Data[] newArray(int size) {
			return new Data[size];
		}
	};
}