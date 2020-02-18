package com.nifcompany.sidediguru.PutUpdateProfile;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class PutUpdateProfile{

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
			"PutUpdateProfile{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}