import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Find info from a schedule.
 * @author nMM456
 *
 */
public class Schedule {
	private int id;
	private String startDate;
	private String endDate;
	private JSONObject g;
	
	public Schedule(String season) throws IOException {
	    String s = "https://api.overwatchleague.com/schedule?season="+season;
	    URL url = new URL(s);
	    Scanner scan = new Scanner(url.openStream());
	    String str = "";
	    while (scan.hasNext())
	        str += scan.nextLine();
	    scan.close();
	    g = new JSONObject(str).getJSONObject("data");
	    id = g.getInt("id");
	    startDate = g.getString("startDate");
	    endDate = g.getString("endDate");
	}
	public Schedule() throws IOException {
		this("");
	}
	public ArrayList<Match> findMatches(int id1, int id2) throws IOException {
		JSONArray stages = g.getJSONArray("stages");
		ArrayList<Match> matches = new ArrayList<Match>();
		for (int i=0;i<stages.length();i++) {
			JSONArray matchJSON = stages.getJSONObject(i).getJSONArray("matches");
			for (int k=0;k<matchJSON.length();k++) {
				if (!(matchJSON.getJSONObject(k).getJSONArray("competitors").isNull(0))) {
					int mid1 = matchJSON.getJSONObject(k).getJSONArray("competitors").getJSONObject(0).getInt("id");
					int mid2 = matchJSON.getJSONObject(k).getJSONArray("competitors").getJSONObject(1).getInt("id");
					if (mid1==id1) {
						if (mid2==id2) matches.add(new Match(matchJSON.getJSONObject(k).getInt("id")));
					}
					if (mid1==id2) {
						if (mid2==id1) matches.add(new Match(matchJSON.getJSONObject(k).getInt("id")));
					}
				}
			}
		}
		return matches;
	}
	public int getId() {
		return id;
	}
	public String getStartDate() {
		return startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public String toString() {
		return Integer.toString(id)+" Season";
	}
}
