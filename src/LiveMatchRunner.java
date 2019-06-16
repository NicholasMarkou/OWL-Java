import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LiveMatchRunner {
	/**
	 * This will only run if there is an Overwatch League match going on.
	 * There is usually one starting on Thursday nights until Sunday.
	 * The data for the next game is usually loaded in as soon as the next game 
	 * is over unless it is Sunday where you usually need to wait until Thursday.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Match live = new Match(true);
		System.out.println(live);
		System.out.println(live.getScore1()+" : "+live.getScore2());
		Date date = new Date(live.getStartDate());
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		Date dates = new Date(live.getEndDate());
		System.out.println(format.format(date));
		System.out.println(format.format(dates));
		ArrayList<Game> games = live.getGames();
		for (Game i: games) {
			System.out.println(i.getMap());
			System.out.println(i.getPoints1()+" : "+i.getPoints2());
		}
	}

}
