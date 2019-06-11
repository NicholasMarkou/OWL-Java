import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Represents a player for a team.
 * Holds data from the API for a specific player.
 * Put in a player id in the constructor to choose a player
 * @author Nicholas Markou
 *
 */
public class Player {
//	Data found in the API
	private int id;
	private String name;
	private String location;
	private String familyName;
	private String givenName;
	private String nationality;
	private double elim;
	private double death;
	private double dmg;
	private double heal;
	private double ult;
	private double fBlows;
	private double time;
	private ArrayList<String> accounts = new ArrayList<String>();
	
	/**
	 * Constructor - creates player class and creates variables by using players ID.
	 * @param playerID
	 * @throws IOException
	 */
	public Player(int playerId) throws IOException {
//		Gets information about a player
	    String s = "https://api.overwatchleague.com/player/"+Integer.toString(playerId);
	    URL url = new URL(s);
	    Scanner scan = new Scanner(url.openStream());
	    String str = "";
	    while (scan.hasNext())
	        str += scan.nextLine();
	    scan.close();
	    JSONObject player = new JSONObject(str).getJSONObject("data").getJSONObject("player");
		id=playerId;
	    name = player.getString("name");
//	    Use .has() to avoid error (some players dont have all these variables)
		if (player.has("homeLocation")) location = player.getString("homeLocation");
		else location = "Unknown";
		if (player.has("familyName")) familyName=player.getString("familyName");
		else location = "Unknown";
		if (player.has("givenName")) givenName=player.getString("givenName");
		else location = "Unknown";
		if (player.has("nationality")) nationality=player.getString("nationality");
		else location = "Unknown";
//		this URL is used to get statistics of the player.
	    s = "https://api.overwatchleague.com/stats/players";
	    URL stat = new URL(s);
	    Scanner x = new Scanner(stat.openStream());
	    str="";
	    while (x.hasNext())
	        str += x.nextLine();
	    x.close();
	    JSONArray all = new JSONObject(str).getJSONArray("data");
		for(int i = 0; i < all.length(); i++) {
//			Finds the correct player by id.
			JSONObject playerStats = all.getJSONObject(i);
			if (playerStats.getInt("playerId") == id) {
				elim = playerStats.getDouble("eliminations_avg_per_10m");
				death = playerStats.getDouble("deaths_avg_per_10m");
				dmg = playerStats.getDouble("hero_damage_avg_per_10m");
				heal = playerStats.getDouble("healing_avg_per_10m");
				ult = playerStats.getDouble("ultimates_earned_avg_per_10m");
				fBlows = playerStats.getDouble("final_blows_avg_per_10m");
				time = playerStats.getDouble("time_played_total");
				break;
			}
		}
//		List of accounts the player has.
		JSONArray JSONAccount = player.getJSONArray("accounts");
		for (int i=0;i<JSONAccount.length();i++) {
			accounts.add(JSONAccount.getJSONObject(i).getString("value"));
		}
	}
	/**
	 * Constructor that takes a JSONObject instead. 
	 * @param player
	 * @throws IOException
	 */
	public Player(JSONObject player) throws IOException {
		id=player.getInt("id");
	    name = player.getString("name");
//	    Use .has() to avoid exception (some players don't have all these variables)
		if (player.has("homeLocation")) location = player.getString("homeLocation");
		else location = "Unknown";
		if (player.has("familyName")) familyName=player.getString("familyName");
		else location = "Unknown";
		if (player.has("givenName")) givenName=player.getString("givenName");
		else location = "Unknown";
		if (player.has("nationality")) nationality=player.getString("nationality");
		else location = "Unknown";
//		Contains stats about the player.
	    String s = "https://api.overwatchleague.com/stats/players";
	    URL stat = new URL(s);
	    Scanner x = new Scanner(stat.openStream());
	    String str="";
	    while (x.hasNext())
	        str += x.nextLine();
	    x.close();
	    JSONArray all = new JSONObject(str).getJSONArray("data");
		for(int i = 0; i < all.length(); i++) {
			JSONObject playerStats = all.getJSONObject(i);
			if (playerStats.getInt("playerId") == id) {
				elim = playerStats.getDouble("eliminations_avg_per_10m");
				death = playerStats.getDouble("deaths_avg_per_10m");
				dmg = playerStats.getDouble("hero_damage_avg_per_10m");
				heal = playerStats.getDouble("healing_avg_per_10m");
				ult = playerStats.getDouble("ultimates_earned_avg_per_10m");
				fBlows = playerStats.getDouble("final_blows_avg_per_10m");
				time = playerStats.getDouble("time_played_total");
				break;
			}
		}
	    s = "https://api.overwatchleague.com/player/"+Integer.toString(id);
	    URL url = new URL(s);
	    Scanner scan = new Scanner(url.openStream());
	    str = "";
	    while (scan.hasNext())
	        str += scan.nextLine();
	    scan.close();
	    player = new JSONObject(str);
	    if (player.has("accoutns")) {
			JSONArray JSONAccount = player.getJSONArray("accounts");
			for (int i=0;i<JSONAccount.length();i++) {
				accounts.add(JSONAccount.getJSONObject(i).getString("value"));
			}
	    }
    }
	/**
	 * Generates a random number(0 - amount of players in theAPI)
	 * Creates player based off number in array
	 * @return player - Player object, randomly chosen.
	 * @throws IOException
	 */
	public static Player getRandomPlayer() throws IOException {
	    String s = "https://api.overwatchleague.com/players";
	    URL url = new URL(s);
	    Scanner scan = new Scanner(url.openStream());
	    String str = "";
	    while (scan.hasNext())
	        str += scan.nextLine();
	    scan.close();
	    JSONArray playerList = new JSONObject(str).getJSONArray("content");
	    int num = (int)(Math.random()*playerList.length());
	    return new Player(playerList.getJSONObject(num));		
	}
	/**
	 * Gets all players in the Overwatch League
	 * Returns an ArrayList containing Player objects 
	 * for each player in the league.
	 * @return players - ArrayList of every player in the API.
	 * @throws IOException
	 */
	public static ArrayList<Player> getAllPlayers() throws IOException {
	    String s = "https://api.overwatchleague.com/players";
	    URL url = new URL(s);
	    Scanner scan = new Scanner(url.openStream());
	    String str = "";
	    ArrayList<Player> players = new ArrayList<Player>();
	    while (scan.hasNext())
	        str += scan.nextLine();
	    scan.close();
	    JSONArray playerList = new JSONObject(str).getJSONArray("content");
	    for (int i=0;i<playerList.length();i++) {
	    	players.add(new Player(playerList.getJSONObject(i)));
	    }
	    return players;
	}
	public ArrayList<String> getAccounts() {
		return accounts;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getlocation() {
		return location;
	}
	public String getFamilyName() {
		return familyName;
	}
	public String getGivenName() {
		return givenName;
	}
	public String getNationality() {
		return nationality;
	}
	public double getElim() {
		return elim;
	}
	public double getDeath() {
		return death;
	}
	public double getDmg() {
		return dmg;
	}
	public double getHeal() {
		return heal;
	}
	public double getUlt() {
		return ult;
	}
	public double getFBlows() {
		return fBlows;
	}
	public double getTime() {
		return time;
	}
	public boolean equals(Player player) {
		return player.getName().equals(name);
	}
	@Override
	public String toString() {
		return name;
	}
}
