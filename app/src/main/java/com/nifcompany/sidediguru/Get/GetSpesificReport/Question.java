package com.nifcompany.sidediguru.Get.GetSpesificReport;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Question implements Parcelable {

	@SerializedName("body")
	private String body;

	@SerializedName("category")
	private String category;

	@SerializedName("question_id")
	private int questionId;

	public void setBody(String body){
		this.body = body;
	}

	public String getBody(){
		return body;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	public void setQuestionId(int questionId){
		this.questionId = questionId;
	}

	public int getQuestionId(){
		return questionId;
	}

	@Override
 	public String toString(){
		return 
			"Question{" + 
			"body = '" + body + '\'' + 
			",category = '" + category + '\'' + 
			",question_id = '" + questionId + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.body);
		dest.writeString(this.category);
		dest.writeInt(this.questionId);
	}

	public Question() {
	}

	protected Question(Parcel in) {
		this.body = in.readString();
		this.category = in.readString();
		this.questionId = in.readInt();
	}

	public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {
		@Override
		public Question createFromParcel(Parcel source) {
			return new Question(source);
		}

		@Override
		public Question[] newArray(int size) {
			return new Question[size];
		}
	};
}