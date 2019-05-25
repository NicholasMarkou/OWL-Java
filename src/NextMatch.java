import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import org.json.*;

public class NextMatch {
	private int id;
	private Team team1;
	private Team team2;
	
	public NextMatch() throws IOException {
	    String s = "https://api.overwatchleague.com/live-match";
	    URL url = new URL(s);
	    Scanner scan = new Scanner(url.openStream());
	    String str = new String();
	    while (scan.hasNext())
	        str += scan.nextLine();
	    scan.close();
	    JSONObject match = new JSONObject(str).getJSONObject("data").getJSONObject("nextMatch");
	    id = match.getInt("id");
	    team1 = new Team(match.getJSONArray("competitors").getJSONObject(0).getInt("id"));
	    team2 = new Team(match.getJSONArray("competitors").getJSONObject(1).getInt("id"));
	}
	public Team getTeam1() {
		return team1;
	}
	public Team getTeam2() {
		return team2;
	}
}
