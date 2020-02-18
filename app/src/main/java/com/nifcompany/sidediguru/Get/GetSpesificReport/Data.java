package com.nifcompany.sidediguru.Get.GetSpesificReport;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Data{

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
			",class_name = '" + className + '\'' + 
			"}";
		}
}