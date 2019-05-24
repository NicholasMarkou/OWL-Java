import java.io.IOException;
import java.net.URL;
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
	    location = player.getString("homeLocation");
	    familyName=player.getString("familyName");
	    givenName=player.getString("givenName");
	    nationality=player.getString("nationality");
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
			}
			break;
		}
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
}
