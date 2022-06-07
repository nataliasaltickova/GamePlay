import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GameTest {
    Player player1 = new Player(1, "Bill", 10);
    Player player2 = new Player(3, "Bob", 15);
    Player player3 = new Player(5, "Boss", 1);
    Player player4 = new Player(6, "Jak", 10);
    Game game;

    @BeforeEach
    void init() {
        game = new Game();
    }

    @Test
    void shouldRegisterOnePlayer() {
        game.register(player1);

        List<Player> actual = game.getPlayers();
        assertEquals(1, actual.size());
        assertEquals(player1, actual.get(0));
    }

    @Test
    void shouldRoundWhenFirstPlayerStrongerSecondPlayer() {
        game.register(player2);
        game.register(player3);

        game.round("Bob", "Boss");
        int expected = 1;
        int actual = game.round("Bob", "Boss");

        assertEquals(expected, actual);


    }

    @Test
    void shouldRoundWhenSecondPlayerStrongerFirstPlayer() {
        game.register(player1);
        game.register(player2);

        game.round("Bill", "Bob");
        int expected = 2;
        int actual = game.round("Bill", "Bob");

        assertEquals(expected, actual);
    }

    @Test
    void shouldRoundWhenPlayersEqual() {
        game.register(player1);
        game.register(player4);

        game.round("Bill", "Jak");
        int expected = 0;
        int actual = game.round("Bill", "Jak");

        assertEquals(expected, actual);
    }

    @Test
    void shouldRoundWhenFirstPlayerNotRegistered() {
        game.register(player4);

        game.findByName("Bill");
        game.findByName("Jak");

        assertThrows(NotRegisteredException.class, () -> {
            game.round("Bill", "Jak");
        });
    }

    @Test
    void shouldRoundWhenSecondPlayerNotRegistered() {
        game.register(player3);

        game.findByName("Boss");
        game.findByName("Jak");


        assertThrows(NotRegisteredException.class, () -> {
            game.round("Boss", "Jak");
        });
    }


}