import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Represents a Game inside a Match. 
 * @author Nicholas Markou
 *
 */
public class Game {
	private int id;
	private int points1;
	private int points2;
//	state determines if the game has not started, in progress or ended.
	private String state;
	private String map;
//	guid is taken to find the name of the map
//	map name is only shown after a game has concluded, however
//	the guid is always given.
	private String mapGuid;
	private String mapType;
	/**
	 * Created in a match object represents each round (map)
	 * 4 - 5 games usually in a match.
	 * @param game
	 * @throws IOException
	 */
	public Game(JSONObject game) throws IOException {
		id = game.getInt("id");
		state = game.getString("state");
		if (game.has("points")) points1 = game.getJSONArray("points").getInt(0);
		if (game.has("points")) points2 = game.getJSONArray("points").getInt(1);
		if (game.getJSONObject("attributes").has("mapGuid") && 
				!(game.getJSONObject("attributes").isNull("mapGuid"))) mapGuid = game.getJSONObject("attributes").getString("mapGuid");
		else mapGuid = "Unknown";
	    URL url = new URL("https://api.overwatchleague.com/maps");
	    Scanner scan = new Scanner(url.openStream());
	    String str = "";
	    while (scan.hasNext())
	        str += scan.nextLine();
	    scan.close();
	    JSONArray maps = new JSONArray(str);
	    for (int i=0;i<maps.length();i++) {
	    	JSONObject mapJ = maps.getJSONObject(i);
	    	if (mapJ.getString("guid").equals(mapGuid)) {
	    		map = mapJ.getJSONObject("name").getString("en_US");
	    		mapType = mapJ.getString("type");
	    	}
	    }
	}
	public int getId() {
		return id;
	}
	public String getGuid() {
		return mapGuid;
	}
	public String getMapType() {
		return mapType;
	}
	public int getPoints1() {
		return points1;
	}
	public int getPoints2() {
		return points2;
	}
	public String getMap() {
		return map;
	}
	public String getState() {
		return state;
	}
	@Override
	public String toString() {
		return map;
	}
}
