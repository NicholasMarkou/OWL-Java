import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.JSONObject;
/**
 * Represents a Live Match in the Overwatch League.
 * Shows the current score, match id, teams playing, when it started.
 * @author Nicholas Markou
 *
 */
public class LiveMatch {
	private int id;
//	the score of team1 is score1, the score of team2 is score2.
	private Team team1;
	private Team team2;
	private int score1;
	private int score2;
	private int startDate;
	private ArrayList<Game> games = new ArrayList<Game>();
	/**
	 * Gets the data for the current game. 
	 * @throws IOException
	 */
	public LiveMatch() throws IOException {
	    String s = "https://api.overwatchleague.com/live-match";
	    URL url = new URL(s);
	    Scanner scan = new Scanner(url.openStream());
	    String str = "";
	    while (scan.hasNext())
	        str += scan.nextLine();
	    scan.close();
	    JSONObject match = new JSONObject(str).getJSONObject("data").getJSONObject("liveMatch");
	    id = match.getInt("id");
	    team1 = new Team(match.getJSONArray("competitors").getJSONObject(0).getInt("id"));
	    team2 = new Team(match.getJSONArray("competitors").getJSONObject(1).getInt("id"));
	    score1 = match.getJSONArray("scores").getJSONObject(0).getInt("value");
	    score2 = match.getJSONArray("scores").getJSONObject(1).getInt("value");
	    if (match.has("games")) {
		    for (int i=0;i<match.getJSONArray("games").length();i++) {
		    	games.add(new Game(match.getJSONArray("games").getJSONObject(i)));
		    }
	    }
	}
	/**
	 * returns the first team.
	 * @return
	 */
	public Team getTeam1() {
		return team1;
	}
	/**
	 * gets the second team
	 * @return team2 - second team
	 */
	public Team getTeam2() {
		return team2;
	}
	public int getScore1() {
		return score1;
	}
	public int getScore2() {
		return score2;
	}
	public int getId() {
		return id;
	}
	public int getStartDate() {
		return startDate;
	}
	public ArrayList<Game> getGames() {
		return games;
	}
	@Override
	public String toString() {
		return team1+" V. "+team2;
	}
	public boolean equals(LiveMatch live) {
		return id==live.getId();
	}
}
