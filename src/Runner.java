import java.io.IOException;
import java.util.ArrayList;

public class Runner {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Team team = new Team(4524);
		System.out.println(team.getName());
		ArrayList<Player> players = team.getPlayers();
		for (Player i:players) {
			System.out.println(i.getName());
			System.out.println("Eliminations/10m: "+i.getElim());
			ArrayList<String> acc = i.getAccounts();
			for (String k:acc) System.out.println(k);
		}
		
	}

}
