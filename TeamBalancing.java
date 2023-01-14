import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TeamBalancing {

	public static List<Player> players = new ArrayList<Player>();

	public static void main(String[] args) {
		players.add(new Player(1, "John", 160, Arrays.asList(2), 5));
		players.add(new Player(2, "Luke", 270, Arrays.asList(2), 4));
		players.add(new Player(3, "Daniel", 490, Arrays.asList(2), 5));
		players.add(new Player(4, "Mark", 200, Arrays.asList(2), 4));
		players.add(new Player(5, "Steven", 90, Arrays.asList(2), 5));
		players.add(new Player(6, "Raymond", 520, Arrays.asList(2), 5));
		players.add(new Player(7, "Louis", 330, Arrays.asList(2), 4));
		players.add(new Player(8, "Zach", 225, Arrays.asList(2), 5));
		players.add(new Player(9, "Robert", 360, Arrays.asList(2), 4));
		players.add(new Player(10, "Virgil", 120, Arrays.asList(2), 5));

		createTeams();
	}

	public static void createTeams() {
		List<Player> list = new ArrayList<Player>(players);
		Collections.sort(list);
		System.out.println("");
		System.out.println("Sorted players:");
		for (Player p : list) {
			System.out.println(p.toString());
		}
		System.out.println("");
		Player bestPlayer1 = list.remove(0);
		Player bestPlayer2 = list.remove(0);
		List<Player> blueTeam = new ArrayList<Player>();
		List<Player> redTeam = new ArrayList<Player>();
		blueTeam.add(bestPlayer1);
		redTeam.add(bestPlayer2);
		float blueElo = blueTeam.get(0).elo;
		float redElo = redTeam.get(0).elo;
		boolean addBlue = true;
		int pick_index = 0;
		for (Player player : list) {
			if (addBlue) {
				blueTeam.add(player);
				blueElo += player.elo;
				if (blueTeam.size() == 1 || pick_index > 0) {
					pick_index = 0;
					addBlue = false;
					continue;
				}
			} else {
				redTeam.add(player);
				redElo += player.elo;
				if (pick_index > 0) {
					pick_index = 0;
					addBlue = true;
					continue;
				}
			}
			pick_index++;
		}
		System.out.println("Team Blue: " + blueElo / blueTeam.size());
		for (Player p : blueTeam) {
			System.out.println(p.toString());
		}
		System.out.println("Team Red: " + redElo / redTeam.size());
		for (Player p : redTeam) {
			System.out.println(p.toString());
		}
		System.out.println("Balancing: ");
		int tries = 0;
		while (tries < 10) {
			if (blueElo > redElo && blueElo - redElo > 5) {
				float difference = blueElo - redElo;
				boolean changed = false;
				balancer: for (int i = 1; i < blueTeam.size(); i++) {
					Player player1 = blueTeam.get(i);
					for (int j = 1; j < redTeam.size(); j++) {
						Player player2 = redTeam.get(j);
						if (player1.elo > player2.elo && player1.elo - player2.elo < difference) {
							blueTeam.set(i, player2);
							redTeam.set(j, player1);
							blueElo -= player1.elo;
							blueElo += player2.elo;
							redElo -= player2.elo;
							redElo += player1.elo;
							changed = true;
							System.out.println("Swapped (Blue)" + player1.name + ": " + player1.elo + " with (Red)"
									+ player2.name + ": " + player2.elo);
							break balancer;
						}
					}
				}
				if (changed) {
					Collections.sort(blueTeam);
					Collections.sort(redTeam);
				}
			} else if (redElo > blueElo && redElo - blueElo > 5) {
				float difference = redElo - blueElo;
				boolean changed = false;
				balancer: for (int i = 1; i < redTeam.size(); i++) {
					Player player1 = redTeam.get(i);
					for (int j = 1; j < blueTeam.size(); j++) {
						Player player2 = blueTeam.get(j);
						if (player1.elo > player2.elo && player1.elo - player2.elo < difference) {
							redTeam.set(i, player2);
							blueTeam.set(j, player1);
							redElo -= player1.elo;
							redElo += player2.elo;
							blueElo -= player2.elo;
							blueElo += player1.elo;
							changed = true;
							System.out.println("Swapped (Red)" + player1.name + ": " + player1.elo + " with (Blue)"
									+ player2.name + ": " + player2.elo);
							break balancer;
						}
					}
				}
				if (changed) {
					Collections.sort(blueTeam);
					Collections.sort(redTeam);
				}
			} else {
				break;
			}
			tries++;
		}
		System.out.println("");
		System.out.println("Team Blue: " + blueElo / blueTeam.size());
		for (Player p : blueTeam) {
			System.out.println(p.toString());
		}
		System.out.println("Team Red: " + redElo / redTeam.size());
		for (Player p : redTeam) {
			System.out.println(p.toString());
		}
	}
}
