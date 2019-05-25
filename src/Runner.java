import java.io.IOException;
import java.util.ArrayList;

public class Runner {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Match match = new Match(21211);
		System.out.println(match);
		ArrayList<Game> games = match.getGames();
		for (Game i: games) {
			System.out.println(i.getMap());
			System.out.println(i.getPoints1()+" : "+i.getPoints2());
		}
	}

}
