package com.nifcompany.sidediguru.Get.GetAllReport;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("items")
	private List<ItemsItem> items;

	public void setItems(List<ItemsItem> items){
		this.items = items;
	}

	public List<ItemsItem> getItems(){
		return items;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"items = '" + items + '\'' + 
			"}";
		}
}