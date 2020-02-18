package com.nifcompany.sidediguru.Get.GetAllReport;

import com.google.gson.annotations.SerializedName;

public class Question{

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
}