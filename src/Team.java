import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.*;

/**
 * Team Object in the Overwatch League
 * Taken from the Overwatch League API.
 * @author nMM456
 *
 */
public class Team {
	private String name;
	private int id;
	private String homeLocation;
	private ArrayList<Player> players = new ArrayList<Player>();
	private String primaryColor;
	private String secondaryColor;
	private ArrayList<String> accounts = new ArrayList<String>();
	
	/**
	 * Constructor - sets all values as local variables. 
	 * @param team - JSON Object of the team you want this object to be.
	 * @throws IOException 
	 */
	public Team(int teamId) throws IOException {
	    String s = "https://api.overwatchleague.com/team/"+Integer.toString(teamId);
	    URL url = new URL(s);
	    Scanner scan = new Scanner(url.openStream());
	    String str = new String();
	    while (scan.hasNext())
	        str += scan.nextLine();
	    scan.close();
		JSONObject team = new JSONObject(str);
		name = team.getString("name");
		id=teamId;
		homeLocation = team.getString("homeLocation");
		primaryColor = team.getString("primaryColor");
		secondaryColor = team.getString("secondaryColor");
		JSONArray thePlayers = team.getJSONArray("players");
		for(int i = 0; i < thePlayers.length(); i++) {
			JSONObject player = thePlayers.getJSONObject(i);
			int playerId = player.getInt("id");
			players.add(new Player(playerId));
		}
		JSONArray JSONAccount = team.getJSONArray("accounts");
		for (int i=0;i<JSONAccount.length();i++) {
			accounts.add(JSONAccount.getJSONObject(i).getString("value"));
		}
	}
	/**
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	public ArrayList<String> getAccounts() {
		return accounts;
	}
	/**
	 * 
	 * @return team id
	 */
	public int getId() {
		return id;
	}
	/**
	 * 
	 * @return home location
	 */
	public String getLocation() {
		return homeLocation;
	}
	public ArrayList<Player> getPlayers() {
		return players;
	}
}
