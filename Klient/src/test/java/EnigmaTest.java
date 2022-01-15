import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class EnigmaTest {

    @Test
    public void koduj_kolor_test(){
        Enigma enigma = new Enigma();
        assertEquals("B", enigma.codePlayerColor(Color.BLUE));
        assertEquals("P", enigma.codePlayerColor(Color.PINK));
        assertEquals("Y", enigma.codePlayerColor(Color.YELLOW));
        assertEquals("O", enigma.codePlayerColor(Color.ORANGE));
        assertEquals("G", enigma.codePlayerColor(Color.GREEN));
        assertEquals("R", enigma.codePlayerColor(Color.RED));
    }
    @Test
    public void odkoduj_kolor_test(){
        Enigma enigma = new Enigma();
        assertEquals(enigma.decodePlayerColor("B"), Color.BLUE);
        assertEquals(enigma.decodePlayerColor("R"), Color.RED);
        assertEquals(enigma.decodePlayerColor("O"), Color.ORANGE);
        assertEquals(enigma.decodePlayerColor("Y"), Color.YELLOW);
        assertEquals(enigma.decodePlayerColor("P"), Color.PINK);
        assertEquals(enigma.decodePlayerColor("G"), Color.GREEN);
    }

    @Test
    public void kolor_gracza_test(){
        Enigma enigma = new Enigma();
        assertEquals(enigma.getPlayerColor('1', 2), Color.PINK);
        assertEquals(enigma.getPlayerColor('2', 2), Color.BLUE);

        assertEquals(enigma.getPlayerColor('1', 3), Color.PINK);
        assertEquals(enigma.getPlayerColor('2', 3), Color.YELLOW);
        assertEquals(enigma.getPlayerColor('3', 3), Color.RED);

        assertEquals(enigma.getPlayerColor('1', 4), Color.ORANGE);
        assertEquals(enigma.getPlayerColor('2', 4), Color.YELLOW);
        assertEquals(enigma.getPlayerColor('3', 4), Color.RED);
        assertEquals(enigma.getPlayerColor('4', 4), Color.GREEN);

        assertEquals(enigma.getPlayerColor('1', 6), Color.PINK);
        assertEquals(enigma.getPlayerColor('2', 6), Color.ORANGE);
        assertEquals(enigma.getPlayerColor('3', 6), Color.YELLOW);
        assertEquals(enigma.getPlayerColor('4', 6), Color.BLUE);
        assertEquals(enigma.getPlayerColor('5', 6), Color.RED);
        assertEquals(enigma.getPlayerColor('6', 6), Color.GREEN);
    }

    @Test
    public void idgracza_test(){

        Enigma enigma = new Enigma();
        assertEquals(enigma.getPlayerId('1', 2), ('P'));
        assertEquals(enigma.getPlayerId('2', 2), ('B'));

        assertEquals(enigma.getPlayerId('1', 3), ('P'));
        assertEquals(enigma.getPlayerId('2', 3), ('Y'));
        assertEquals(enigma.getPlayerId('3', 3), ('R'));

        assertEquals(enigma.getPlayerId('1', 4), ('O'));
        assertEquals(enigma.getPlayerId('2', 4), ('Y'));
        assertEquals(enigma.getPlayerId('3', 4), ('R'));
        assertEquals(enigma.getPlayerId('4', 4), ('G'));

        assertEquals(enigma.getPlayerId('1', 6), ('P'));
        assertEquals(enigma.getPlayerId('2', 6), ('O'));
        assertEquals(enigma.getPlayerId('3', 6), ('Y'));
        assertEquals(enigma.getPlayerId('4', 6), ('B'));
        assertEquals(enigma.getPlayerId('5', 6), ('R'));
        assertEquals(enigma.getPlayerId('6', 6), ('G'));

    }

    @Test
    public void set_desktop_x_test(){

        Enigma enigma = new Enigma();
        assertEquals(30, enigma.set_desktop_x('1'));
        assertEquals(520, enigma.set_desktop_x('2'));
        assertEquals(1010, enigma.set_desktop_x('3'));
        assertEquals(30, enigma.set_desktop_x('4'));
        assertEquals(520, enigma.set_desktop_x('5'));
        assertEquals(1010, enigma.set_desktop_x('6'));

    }

    @Test
    public void set_desktop_y_test(){

        Enigma enigma = new Enigma();
        assertEquals(0, enigma.set_desktop_y('1'));
        assertEquals(0, enigma.set_desktop_y('2'));
        assertEquals(0, enigma.set_desktop_y('3'));
        assertEquals(435, enigma.set_desktop_y('4'));
        assertEquals(435, enigma.set_desktop_y('5'));
        assertEquals(435, enigma.set_desktop_y('6'));

    }


}

