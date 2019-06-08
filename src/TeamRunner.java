import java.io.IOException;
import java.util.ArrayList;

/**
 * Example of a team
 * team id 4402 is the Boston Uprising.
 * @author Nicholas Markou
 *
 */
public class TeamRunner {

	public static void main(String[] args) throws IOException {
		Team team = new Team(4402);
		System.out.println(team);
		System.out.println(team.getLocation()+"\n");
		ArrayList<Player> players = team.getPlayers();
		for (Player i:players) {
			System.out.println("Username:"+i);
			System.out.println("Name: "+i.getGivenName()+" "+i.getFamilyName());
			System.out.println("From: "+i.getlocation()+"\n");
		}
	}

}
