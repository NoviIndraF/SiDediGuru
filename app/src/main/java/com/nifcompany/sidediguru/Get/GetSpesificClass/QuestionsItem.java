package com.nifcompany.sidediguru.Get.GetSpesificClass;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class QuestionsItem implements Parcelable {

	@SerializedName("question")
	private String question;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("category_question_id")
	private int categoryQuestionId;

    public QuestionsItem(String question) {
    }

    public void setQuestion(String question){
		this.question = question;
	}

	public String getQuestion(){
		return question;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setCategoryQuestionId(int categoryQuestionId){
		this.categoryQuestionId = categoryQuestionId;
	}

	public int getCategoryQuestionId(){
		return categoryQuestionId;
	}

	@Override
 	public String toString(){
		return 
			"QuestionsItem{" + 
			"question = '" + question + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",category_question_id = '" + categoryQuestionId + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.question);
		dest.writeString(this.updatedAt);
		dest.writeString(this.createdAt);
		dest.writeInt(this.id);
		dest.writeInt(this.categoryQuestionId);
	}

	protected QuestionsItem(Parcel in) {
		this.question = in.readString();
		this.updatedAt = in.readString();
		this.createdAt = in.readString();
		this.id = in.readInt();
		this.categoryQuestionId = in.readInt();
	}

	public static final Parcelable.Creator<QuestionsItem> CREATOR = new Parcelable.Creator<QuestionsItem>() {
		@Override
		public QuestionsItem createFromParcel(Parcel source) {
			return new QuestionsItem(source);
		}

		@Override
		public QuestionsItem[] newArray(int size) {
			return new QuestionsItem[size];
		}
	};
}