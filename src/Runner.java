import java.io.IOException;
import java.util.ArrayList;

public class Runner {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		LiveMatch live = new LiveMatch();
		System.out.println(live);
		System.out.println(live.getScore1()+" : "+live.getScore2());
		ArrayList<Game> games = live.getGames();
		for (Game i: games) {
			System.out.println(i.getMap());
			System.out.println(i.getPoints1()+" : "+i.getPoints2());
		}
	}

}
