import java.io.IOException;

/**
 * Gets a random player and prints info about them.
 * @author nMM456
 *
 */
public class PlayerRunner {
	public static void main(String args[]) throws IOException {
		Player player = Player.getRandomPlayer();
		System.out.println("Username: "+player);
		System.out.println("Name: "+player.getGivenName()+" "+player.getFamilyName());
		System.out.println("From: "+player.getlocation());
		System.out.println("Stats:\n");
		System.out.println("Eliminations/10m: "+player.getElim());
		System.out.println("Damage/10m: "+player.getDmg());
		System.out.println("Deaths/10m: "+player.getDeath());
		System.out.println("Time played in seconds: "+player.getTime());
	}
}
