import org.json.JSONObject;

/**
 * Represents a Game inside a Match. 
 * @author nMM456
 *
 */
public class Game {
	private int id;
	private int points1;
	private int points2;
	private String map;
	private String state;
	
	public Game(JSONObject game) {
		id = game.getInt("id");
		state = game.getString("state");
		if (game.has("points")) points1 = game.getJSONArray("points").getInt(0);
		if (game.has("points")) points2 = game.getJSONArray("points").getInt(1);
		if (game.has("attributes") && game.getJSONObject("attributes").has("map") && 
				!(game.getJSONObject("attributes").isNull("map"))) map = game.getJSONObject("attributes").getString("map");
		else map = "Unknown Map";
	}
	public int getId() {
		return id;
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
