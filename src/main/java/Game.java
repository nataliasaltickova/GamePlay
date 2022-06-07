import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players;

    public Game() {
        this.players = new ArrayList<>();
    }

    public void register(Player player) {
        this.players.add(player);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player findByName(String name) {
        for (Player item : players) {
            if (item.getName() == name) {
                return item;
            }
        }
        return null;
    }

    public int round(String playerName1, String playerName2) {
        Player player1 = findByName(playerName1);
        Player player2 = findByName(playerName2);
        if (player1 == null || player2 == null) {
            throw new NotRegisteredException("players are not registered");
        }
        if (player1.getStrength() > player2.getStrength()) {
            return 1;
        } else if (player1.getStrength() < player2.getStrength()) {
            return 2;
        }
        return 0;
    }
}




