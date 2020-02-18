package com.nifcompany.sidediguru.Get.GetSpesificClass;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Meta implements Parcelable {

	@SerializedName("questions")
	private List<QuestionsItem> questions;

	public void setQuestions(List<QuestionsItem> questions){
		this.questions = questions;
	}

	public List<QuestionsItem> getQuestions(){
		return questions;
	}

	@Override
 	public String toString(){
		return 
			"Meta{" + 
			"questions = '" + questions + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeList(this.questions);
	}

	public Meta() {
	}

	protected Meta(Parcel in) {
		this.questions = new ArrayList<QuestionsItem>();
		in.readList(this.questions, QuestionsItem.class.getClassLoader());
	}

	public static final Parcelable.Creator<Meta> CREATOR = new Parcelable.Creator<Meta>() {
		@Override
		public Meta createFromParcel(Parcel source) {
			return new Meta(source);
		}

		@Override
		public Meta[] newArray(int size) {
			return new Meta[size];
		}
	};
}