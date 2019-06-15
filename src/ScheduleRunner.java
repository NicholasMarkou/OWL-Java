import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ScheduleRunner {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Schedule schedule = new Schedule("2018");
		System.out.println(schedule);
		System.out.println(schedule.getStartDate());
		System.out.println(schedule.getEndDate()+"\n");
		ArrayList<Match> matches = schedule.findMatches(4402, 4525);
		for (Match match:matches) {
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
				System.out.println("\n");
			}
		}
	}

}
