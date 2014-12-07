package com.musictour;

import org.json.simple.JSONObject;

public class DBOperation {
	public String getMethod(int cmd) {
		String out = null;
		JSONObject outObj = new JSONObject();
		switch (cmd){
			case 101 :
				
				break;
			default: break;
		}
		outObj.put("outcome", out);
		return outObj.toJSONString();
	}
}
