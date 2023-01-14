import java.util.ArrayList;
import java.util.List;

public class Player implements Comparable<Player> {
	public int id;
	public String name;
	public int elo;
	public int pastMap;
	public List<Integer> pastTeam = new ArrayList<Integer>();

	public Player(int id, String a, int b, List<Integer> team, int map) {
		this.id = id;
		this.name = a;
		this.elo = b;
		this.pastTeam = team;
		this.pastMap = map;
	}

	@Override
	public int compareTo(Player o) {
		if (this.elo < o.elo)
			return 1;
		if (this.elo == o.elo)
			return 0;
		return -1;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("{id: ").append(this.id).append(", name: ").append(this.name)
				.append(", elo: ").append(this.elo).append("}").toString();
	}

}
