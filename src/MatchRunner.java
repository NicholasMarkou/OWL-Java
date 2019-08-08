import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Example of a past match, loaded by the id.
 * @author nMM456
 *
 */
public class MatchRunner {
	public static void main(String args[]) throws IOException {
		Match match = new Match(21293);
		System.out.println(match);
		System.out.println(match.getScore1()+" : "+match.getScore2());
		Date start = new Date(match.getStartDate());
		SimpleDateFormat startFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		String startStr = startFormat.format(start);
		Date end = new Date(match.getEndDate());
		SimpleDateFormat endFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		String endStr = endFormat.format(end);
		System.out.println("Started: "+startStr);
		System.out.println("Ended: "+endStr);
		if (match.getWinner()==null) System.out.println("No winner.");
		else System.out.println("Winner: "+match.getWinner().getName());
		ArrayList<Game> games = match.getGames();
		for (Game i: games) {
			System.out.println(i.getMap());
			System.out.println(i.getPoints1()+" : "+i.getPoints2());
		}
	}
}