package com.nifcompany.sidediguru.Get.GetAllClass;

import com.google.gson.annotations.SerializedName;

public class GetAllClass{

	@SerializedName("data")
	private Data data;

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"GetAllClass{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}