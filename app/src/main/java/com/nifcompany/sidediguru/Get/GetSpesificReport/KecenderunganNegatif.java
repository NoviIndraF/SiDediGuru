package com.nifcompany.sidediguru.Get.GetSpesificReport;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class KecenderunganNegatif implements Parcelable {

	@SerializedName("students_count")
	private int studentsCount;

	@SerializedName("students")
	private List<StudentsItem> students;

	public void setStudentsCount(int studentsCount){
		this.studentsCount = studentsCount;
	}

	public int getStudentsCount(){
		return studentsCount;
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
			"KecenderunganNegatif{" + 
			"students_count = '" + studentsCount + '\'' + 
			",students = '" + students + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.studentsCount);
		dest.writeList(this.students);
	}

	public KecenderunganNegatif() {
	}

	protected KecenderunganNegatif(Parcel in) {
		this.studentsCount = in.readInt();
		this.students = new ArrayList<StudentsItem>();
		in.readList(this.students, StudentsItem.class.getClassLoader());
	}

	public static final Parcelable.Creator<KecenderunganNegatif> CREATOR = new Parcelable.Creator<KecenderunganNegatif>() {
		@Override
		public KecenderunganNegatif createFromParcel(Parcel source) {
			return new KecenderunganNegatif(source);
		}

		@Override
		public KecenderunganNegatif[] newArray(int size) {
			return new KecenderunganNegatif[size];
		}
	};
}