import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Represents a player for a team.
 * @author nMM456
 *
 */
public class Player {
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
	    String s = "https://api.overwatchleague.com/player/"+Integer.toString(playerId);
	    URL url = new URL(s);
	    Scanner scan = new Scanner(url.openStream());
	    String str = new String();
	    while (scan.hasNext())
	        str += scan.nextLine();
	    scan.close();
	    JSONObject player = new JSONObject(str).getJSONObject("data").getJSONObject("player");
		id=playerId;
	    name = player.getString("name");
//	    Use .has() to avoid execption (some players dont have all these variables)
		if (player.has("homeLocation")) location = player.getString("homeLocation");
		if (player.has("familyName")) familyName=player.getString("familyName");
		if (player.has("givenName")) givenName=player.getString("givenName");
		if (player.has("nationality")) nationality=player.getString("nationality");
	    s = "https://api.overwatchleague.com/stats/players";
	    URL stat = new URL(s);
	    Scanner x = new Scanner(stat.openStream());
	    str="";
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
		JSONArray JSONAccount = player.getJSONArray("accounts");
		for (int i=0;i<JSONAccount.length();i++) {
			accounts.add(JSONAccount.getJSONObject(i).getString("value"));
		}
	}
	public Player(JSONObject player) throws IOException {
		id=player.getInt("id");
	    name = player.getString("name");
//	    Use .has() to avoid exception (some players don't have all these variables)
		if (player.has("homeLocation")) location = player.getString("homeLocation");
		if (player.has("familyName")) familyName=player.getString("familyName");
		if (player.has("givenName")) givenName=player.getString("givenName");
		if (player.has("nationality")) nationality=player.getString("nationality");
	    try {
		    String s = "https://api.overwatchleague.com/stats/players";
		    URL stat = new URL(s);
		    Scanner x = new Scanner(stat.openStream());
		    String str = new String();
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
		    str = new String();
		    while (scan.hasNext())
		        str += scan.nextLine();
		    scan.close();
		    player = new JSONObject(str);
			JSONArray JSONAccount = player.getJSONArray("accounts");
			for (int i=0;i<JSONAccount.length();i++) {
				accounts.add(JSONAccount.getJSONObject(i).getString("value"));
			}
	    }
	    catch(Exception JSONException) {
	    	
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
	    String str = new String();
	    while (scan.hasNext())
	        str += scan.nextLine();
	    scan.close();
	    JSONArray playerList = new JSONObject(str).getJSONArray("content");
	    int num = (int)(Math.random()*playerList.length());
	    return new Player(playerList.getJSONObject(num));		
	}
	/**
	 * DO NOT USE THIS METHOD FREQUENTLY! 
	 * IT MAKES A LOT OF REQUESTS TO THE API.
	 * IF USED TO MUCH YOU MIGHT GET BLOCKED!
	 * @return players - ArrayList of every player in the API.
	 * @throws IOException
	 */
	public static ArrayList<Player> getAllPlayers() throws IOException {
	    String s = "https://api.overwatchleague.com/players";
	    URL url = new URL(s);
	    Scanner scan = new Scanner(url.openStream());
	    String str = new String();
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
