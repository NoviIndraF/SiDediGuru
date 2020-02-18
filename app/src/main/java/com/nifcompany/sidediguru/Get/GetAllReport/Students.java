package com.nifcompany.sidediguru.Get.GetAllReport;

import com.google.gson.annotations.SerializedName;

public class Students{

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
			"Students{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}