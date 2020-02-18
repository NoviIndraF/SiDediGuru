package com.nifcompany.sidediguru.Get.GetProfileGuru;

public class GetProfileGuru{
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
			"GetProfileGuru{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}
