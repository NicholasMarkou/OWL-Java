import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.JSONObject;

/**
 * Represents a match in the Overwatch League
 * https://api.overwatchleague.com/match/id
 * @author nMM456
 *
 */
public class Match {
	private int id;
//	team1's score is under score1.
//	team2's score is under score2.
	private Team team1;
	private Team team2;
	private int score1;
	private int score2;
//	State is if the game hasn't begun, is being played or has concluded.
	private String state;
//	startDate and endDate are in epoch time
	private long startDate;
	private long endDate;
	private Team winner;
	private ArrayList<Game> games = new ArrayList<Game>();
	/**
	 * This takes an id from the Overwatch league and creates a match object
	 * with the data it gets from the api. 
	 * https://api.overwatchleague.com/match/id
	 * @param matchId - id for a match in the Overwatch League
	 * @throws IOException
	 */
	public Match(int matchId) throws IOException {
		String s = "https://api.overwatchleague.com/match/"+Integer.toString(matchId);
	    URL url = new URL(s);
	    Scanner scan = new Scanner(url.openStream());
	    String str = "";
	    while (scan.hasNext())
	        str += scan.nextLine();
	    scan.close();
	    JSONObject match = new JSONObject(str);
	    id = match.getInt("id");
	    team1 = new Team(match.getJSONArray("competitors").getJSONObject(0));
	    team2 = new Team(match.getJSONArray("competitors").getJSONObject(1));
	    score1 = match.getJSONArray("scores").getJSONObject(0).getInt("value");
	    score2 = match.getJSONArray("scores").getJSONObject(1).getInt("value");
	    state = match.getString("state");
	    if (match.has("actualStartDate")) startDate = match.getLong("actualStartDate");
	    else startDate = match.getLong("startDate");
	    if (match.has("actualEndDate")) endDate = match.getLong("actualEndDate");
	    else endDate = match.getLong("endDate");
//	    Winner points to one of the 2 teams depending on who won.
//	    If the match has not happened yet, it sets winner to null.
	    if (!(match.has("winner"))) winner = null;
	    else {
	    	if (match.getJSONObject("winner").getString("name").equals(team1.getName())) winner = team1;
	    	else winner = team2;
	    }
	    if (match.has("games")) {
		    for (int i=0;i<match.getJSONArray("games").length();i++) {
		    	games.add(new Game(match.getJSONArray("games").getJSONObject(i)));
		    }
	    }
	}
	/**
	 * Use this constructor to access the live match.
	 * https://api.overwatchleague.com/live-match
	 * @throws IOException 
	 * @throws JSONException 
	 */
	public Match(boolean live) throws IOException {
		this(liveMatch());
	}
	public Match (String next) throws IOException {
		this(nextMatch());
	}
	private static int nextMatch() throws IOException {
	    URL url = new URL("https://api.overwatchleague.com/live-match");
	    Scanner scan = new Scanner(url.openStream());
	    String str = "";
	    while (scan.hasNext())
	        str += scan.nextLine();
	    scan.close();
	    return new JSONObject(str).getJSONObject("data").getJSONObject("nextMatch").getInt("id");
	}
	/**
	 * Gets id for the live match to run through the constructor.
	 * @return
	 * @throws IOException
	 */
	private static int liveMatch() throws IOException {
	    URL url = new URL("https://api.overwatchleague.com/live-match");
	    Scanner scan = new Scanner(url.openStream());
	    String str = "";
	    while (scan.hasNext())
	        str += scan.nextLine();
	    scan.close();
	    return new JSONObject(str).getJSONObject("data").getJSONObject("liveMatch").getInt("id");
	}
	public ArrayList<Game> getGames() {
		return games;
	}
	/**
	 * Start date is in epoch, miliseconds. 
	 * Used to find date.
	 * @return startDate - time the game is planned to start or started
	 */
	public long getStartDate() {
		return startDate;
	}
	/**
	 * End date is in epoch, milliseconds. 
	 * Used to find date.
	 * @return endDate - time the game is planned to end or ended
	 */
	public long getEndDate() {
		return endDate;
	}
	
	public int getId() {
		return id;
	}
	public Team getTeam1() {
		return team1;
	}
	public Team getTeam2() {
		return team2;
	}
	/**
	 * 
	 * @return score for team 1
	 */
	public int getScore1() {
		return score1;
	}
	/**
	 * 
	 * @return score for team 2.
	 */
	public int getScore2() {
		return score2;
	}
	public String getState() {
		return state;
	}
	public Team getWinner() {
		return winner;
	}
	@Override
	public String toString() {
		return team1+" V. "+team2;
	}
	public boolean equals(Match match) {
		return match.getId()==id;
	}
}
