package com.nifcompany.sidediguru.Post.PostPasswordValidation;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Meta{

	@SerializedName("api_token")
	private String apiToken;

	public void setApiToken(String apiToken){
		this.apiToken = apiToken;
	}

	public String getApiToken(){
		return apiToken;
	}

	@Override
 	public String toString(){
		return 
			"Meta{" + 
			"api_token = '" + apiToken + '\'' + 
			"}";
		}
}