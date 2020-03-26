package com.ale.collezione.utility;

import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service("carteUtility")
public class CarteUtility {

	static HashMap<String, String> set;
	static {
		set = new HashMap<String, String>();
		set.put("SET_BASE", "01");
		set.put("JUNGLE", "02");
		set.put("FOSSIL", "03");
		set.put("ROCKET", "04");
	}
	
	public String nomeCondizione(String cond) {
		String nomeCond = null;
		switch (Integer.parseInt(cond)) {
		case 1:
			nomeCond = "Mint";
			break;
		case 2:
			nomeCond = "Exc";
			break;
		case 3:
			nomeCond = "Good";
			break;
		case 4:
			nomeCond = "LightPlayed";
			break;
		case 5:
			nomeCond = "Played";
			break;
		}
		return nomeCond;
	}
	
	public String codiceSet(String nomeSet)
	{		
		return set.get(nomeSet.toUpperCase());
	}
	
	

}
