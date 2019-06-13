import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Find info from a schedule.
 * @author Nicholas
 *
 */
public class Schedule {
	private int id;
	private String startDate;
	private String endDate;
	private JSONObject g;
	
	public Schedule() throws IOException {
	    String s = "https://api.overwatchleague.com/schedule";
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
	public Match findMatch(int id1, int id2) {
		JSONArray stages = g.getJSONArray("stages");
		ArrayList<Match> matches = new ArrayList<Match>();
		for (int i=0;i<stages.length();i++) {
			JSONArray matches = stages.getJSONObject(i).getJSONArray("matches");
			for (int k=0;k<matches.length();k++) {
				int mid1 = matches.getJSONObject(k).getJSONArray("competitors").getJSONObject(0).getInt("id");
				int mid2 = matches.getJSONObject(k).getJSONArray("competitors").getJSONObject(1).getInt("id");
				if (mid1==id1) {
					if (mid2==id2) 
				}
			}
		}
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
