package com.nifcompany.sidediguru.Post.PostPasswordForgot;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class PostPasswordForgot{

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
			"PostPasswordForgot{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}