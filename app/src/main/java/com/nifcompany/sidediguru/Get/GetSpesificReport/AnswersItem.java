package com.nifcompany.sidediguru.Get.GetSpesificReport;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class AnswersItem implements Parcelable {

	@SerializedName("question")
	private Question question;

	@SerializedName("answer_value")
	private int answerValue;

	@SerializedName("answer_id")
	private int answerId;

	public void setQuestion(Question question){
		this.question = question;
	}

	public Question getQuestion(){
		return question;
	}

	public void setAnswerValue(int answerValue){
		this.answerValue = answerValue;
	}

	public int getAnswerValue(){
		return answerValue;
	}

	public void setAnswerId(int answerId){
		this.answerId = answerId;
	}

	public int getAnswerId(){
		return answerId;
	}

	@Override
 	public String toString(){
		return 
			"AnswersItem{" + 
			"question = '" + question + '\'' + 
			",answer_value = '" + answerValue + '\'' + 
			",answer_id = '" + answerId + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(this.question, flags);
		dest.writeInt(this.answerValue);
		dest.writeInt(this.answerId);
	}

	public AnswersItem() {
	}

	protected AnswersItem(Parcel in) {
		this.question = in.readParcelable(Question.class.getClassLoader());
		this.answerValue = in.readInt();
		this.answerId = in.readInt();
	}

	public static final Parcelable.Creator<AnswersItem> CREATOR = new Parcelable.Creator<AnswersItem>() {
		@Override
		public AnswersItem createFromParcel(Parcel source) {
			return new AnswersItem(source);
		}

		@Override
		public AnswersItem[] newArray(int size) {
			return new AnswersItem[size];
		}
	};
}