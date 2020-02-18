package com.nifcompany.sidediguru.Get.GetAllReport;

import com.google.gson.annotations.SerializedName;

public class GetAllReport{

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
			"GetAllReport{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}