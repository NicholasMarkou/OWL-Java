import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
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
	
	/**
	 * Constructor - creates player class and creates variables by using players ID.
	 * @param playerID
	 * @throws IOException
	 */
	public Player(int playerID) throws IOException {
	    String s = "https://api.overwatchleague.com/player/"+Integer.toString(playerID);
	    URL url = new URL(s);
	    Scanner scan = new Scanner(url.openStream());
	    String str = new String();
	    while (scan.hasNext())
	        str += scan.nextLine();
	    scan.close();
	    JSONObject player = new JSONObject(str).getJSONObject("data").getJSONObject("player");
	    id = player.getInt("id");
	    name = player.getString("name");
	    location = player.getString("homeLocation");
	    familyName=player.getString("familyName");
	    givenName=player.getString("givenName");
	    nationality=player.getString("nationality");
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
}
