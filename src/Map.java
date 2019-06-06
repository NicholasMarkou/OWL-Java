import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class Map {
	private String name;
	private String guid;
	private String type;
	
	public Map(String guid) throws IOException {
		this.guid = guid;
	    URL url = new URL("https://api.overwatchleague.com/maps");
	    Scanner scan = new Scanner(url.openStream());
	    String str = "";
	    while (scan.hasNext())
	        str += scan.nextLine();
	    scan.close();
	    JSONArray maps = new JSONArray(str);
	    for (int i=0;i<maps.length();i++) {
	    	JSONObject map = maps.getJSONObject(i);
	    	if (map.getString("guid").equals(guid)) {
	    		name = map.getString("name");
	    		guid = map.getString("guid");
	    		type = map.getString("type");
	    	}
	    }
	}
	public String getGUID() {
		return guid;
	}
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public boolean equals(Map map) {
		return map.getGUID().equals(guid);
	}
	@Override
	public String toString() {
		return name;
	}
}
