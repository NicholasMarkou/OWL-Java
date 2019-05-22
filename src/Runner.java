import java.io.IOException;
import java.util.ArrayList;

public class Runner {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Team team = new Team(4524);
		System.out.println(team.getName());
		ArrayList<Player> players = team.getPlayers();
		Stats stat = new Stats(players.get(0));
		System.out.println(stat.getHeal());
		for (Player i:players) {
			System.out.println(i.getName());
		}
		
	}

}
