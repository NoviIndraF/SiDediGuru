package com.nifcompany.sidediguru.Get.GetSpesificClass;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class GetSpesificClass implements Parcelable {

	@SerializedName("data")
	private Data data;

	@SerializedName("meta")
	private Meta meta;

	@SerializedName("students")
	private List<StudentsItem> students;

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	public void setMeta(Meta meta){
		this.meta = meta;
	}

	public Meta getMeta(){
		return meta;
	}

	public void setStudents(List<StudentsItem> students){
		this.students = students;
	}

	public List<StudentsItem> getStudents(){
		return students;
	}

	@Override
 	public String toString(){
		return 
			"GetSpesificClass{" + 
			"data = '" + data + '\'' + 
			",meta = '" + meta + '\'' + 
			",students = '" + students + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(this.data, flags);
		dest.writeParcelable(this.meta, flags);
		dest.writeTypedList(this.students);
	}

	public GetSpesificClass() {
	}

	protected GetSpesificClass(Parcel in) {
		this.data = in.readParcelable(Data.class.getClassLoader());
		this.meta = in.readParcelable(Meta.class.getClassLoader());
		this.students = in.createTypedArrayList(StudentsItem.CREATOR);
	}

	public static final Parcelable.Creator<GetSpesificClass> CREATOR = new Parcelable.Creator<GetSpesificClass>() {
		@Override
		public GetSpesificClass createFromParcel(Parcel source) {
			return new GetSpesificClass(source);
		}

		@Override
		public GetSpesificClass[] newArray(int size) {
			return new GetSpesificClass[size];
		}
	};
}