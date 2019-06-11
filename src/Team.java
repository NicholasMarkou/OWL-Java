import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 * Team Object in the Overwatch League
 * Taken from the Overwatch League API.
 * @author Nicholas Markou
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
	    String str = "";
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
	 * Creates a team object like above
	 * Uses a JSONObject instead of the id. 
	 * @param team
	 * @throws IOException
	 */
	public Team (JSONObject team) throws IOException {
		name = team.getString("name");
		id=team.getInt("id");
		homeLocation = team.getString("homeLocation");
		primaryColor = team.getString("primaryColor");
		secondaryColor = team.getString("secondaryColor");
		JSONArray thePlayers = team.getJSONArray("players");
		for(int i = 0; i < thePlayers.length(); i++) {
			JSONObject player = thePlayers.getJSONObject(i).getJSONObject("player");
			players.add(new Player(player));
		}
	    String s = "https://api.overwatchleague.com/team/"+Integer.toString(id);
	    URL url = new URL(s);
	    Scanner scan = new Scanner(url.openStream());
	    String str = "";
	    while (scan.hasNext())
	        str += scan.nextLine();
	    scan.close();
		team = new JSONObject(str);
		JSONArray JSONAccount = team.getJSONArray("accounts");
		for (int i=0;i<JSONAccount.length();i++) {
			accounts.add(JSONAccount.getJSONObject(i).getString("value"));
		}
	}
	/**
	 * Finds a random OWL team in the API.
	 * @return
	 * @throws IOException
	 */
	public static Team getRandomTeam() throws IOException {
	    String s = "https://api.overwatchleague.com/teams";
	    URL url = new URL(s);
	    Scanner scan = new Scanner(url.openStream());
	    String str = "";
	    while (scan.hasNext())
	        str += scan.nextLine();
	    scan.close();
	    JSONArray teamList = new JSONObject(str).getJSONArray("competitors");
	    int num = (int)(Math.random()*teamList.length());
	    return new Team(teamList.getJSONObject(num).getJSONObject("competitor"));	
	}
	/**
	 * Creates an ArrayList containing each team in the Overwatch League
	 * @return teams - each team in the Overwatch League
	 * @throws IOException
	 */
	public static ArrayList<Team> getAllTeams() throws IOException {
	    String s = "https://api.overwatchleague.com/teams";
	    URL url = new URL(s);
	    Scanner scan = new Scanner(url.openStream());
	    String str = "";
	    while (scan.hasNext())
	        str += scan.nextLine();
	    scan.close();
	    ArrayList<Team> teams = new ArrayList<Team>();
	    JSONArray teamList = new JSONObject(str).getJSONArray("competitors");
	    for (int i=0;i<teamList.length();i++) {
	    	teams.add(new Team(teamList.getJSONObject(i).getJSONObject("competitor")));
	    }
	    return teams;
	}
	/**
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	public String getPrimaryColor() {
		return primaryColor;
	}
	public String getSecondaryColor() {
		return secondaryColor;
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
	@Override
	public String toString() {
		return name;
	}
}
