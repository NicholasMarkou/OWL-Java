import java.io.IOException;
import java.util.ArrayList;

public class Runner {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Team team = new Team(4524);
		System.out.println(team.getName());
		System.out.println(team.getLocation());
		System.out.println(team.getId());
		ArrayList<Player> players = team.getPlayers();
		for (Player i:players) {
			System.out.println(i.getName());
			System.out.println(i.getHeal());
		}
		
	}

}
