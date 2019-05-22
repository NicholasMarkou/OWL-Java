import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Get basic stats from a player.
 * @author nMM456
 *
 */
public class Stats {
	private int id;
	private double elim;
	private double death;
	private double dmg;
	private double heal;
	private double ult;
	private double fBlows;
	private double time;
	public Stats(Player player) throws IOException {
		this(player.getId());
	}
	
	public Stats(int playerId) throws IOException {
		id=playerId;
	    String s = "https://api.overwatchleague.com/players/";
	    URL url = new URL(s);
	    Scanner scan = new Scanner(url.openStream());
	    String str = new String();
	    while (scan.hasNext())
	        str += scan.nextLine();
	    scan.close();
	    JSONArray all = new JSONObject(str).getJSONArray("data");
		for(int i = 0; i < all.length(); i++) {
			JSONObject player = all.optJSONObject(i);
			if (player.getString("playerId").equals(Integer.toString(id))) {
				elim = player.getDouble("eliminations_avg_per_10m");
				death = player.getDouble("deaths_avg_per_10m");
				dmg = player.getDouble("hero_damage_avg_per_10m");
				heal = player.getDouble("healing_avg_per_10m");
				ult = player.getDouble("ultimates_earned_avg_per_10m");
				fBlows = player.getDouble("final_blows_avg_per_10m");
				time = player.getDouble("time_played_total");
			}
			break;
		}
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
