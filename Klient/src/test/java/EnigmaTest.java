import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class EnigmaTest {

    @Test
    public void koduj_kolor_test(){
        Enigma enigma = new Enigma();
        assertEquals("B", enigma.koduj_kolor(Color.BLUE));
        assertEquals("P", enigma.koduj_kolor(Color.PINK));
        assertEquals("Y", enigma.koduj_kolor(Color.YELLOW));
        assertEquals("O", enigma.koduj_kolor(Color.ORANGE));
        assertEquals("G", enigma.koduj_kolor(Color.GREEN));
        assertEquals("R", enigma.koduj_kolor(Color.RED));
    }
    @Test
    public void odkoduj_kolor_test(){
        Enigma enigma = new Enigma();
        assertEquals(enigma.odkoduj_kolor("B"), Color.BLUE);
        assertEquals(enigma.odkoduj_kolor("R"), Color.RED);
        assertEquals(enigma.odkoduj_kolor("O"), Color.ORANGE);
        assertEquals(enigma.odkoduj_kolor("Y"), Color.YELLOW);
        assertEquals(enigma.odkoduj_kolor("P"), Color.PINK);
        assertEquals(enigma.odkoduj_kolor("G"), Color.GREEN);
    }

    @Test
    public void kolor_gracza_test(){
        Enigma enigma = new Enigma();
        assertEquals(enigma.kolorgracza('1', 2), Color.PINK);
        assertEquals(enigma.kolorgracza('2', 2), Color.BLUE);

        assertEquals(enigma.kolorgracza('1', 3), Color.PINK);
        assertEquals(enigma.kolorgracza('2', 3), Color.YELLOW);
        assertEquals(enigma.kolorgracza('3', 3), Color.RED);

        assertEquals(enigma.kolorgracza('1', 4), Color.ORANGE);
        assertEquals(enigma.kolorgracza('2', 4), Color.YELLOW);
        assertEquals(enigma.kolorgracza('3', 4), Color.RED);
        assertEquals(enigma.kolorgracza('4', 4), Color.GREEN);

        assertEquals(enigma.kolorgracza('1', 6), Color.PINK);
        assertEquals(enigma.kolorgracza('2', 6), Color.ORANGE);
        assertEquals(enigma.kolorgracza('3', 6), Color.YELLOW);
        assertEquals(enigma.kolorgracza('4', 6), Color.BLUE);
        assertEquals(enigma.kolorgracza('5', 6), Color.RED);
        assertEquals(enigma.kolorgracza('6', 6), Color.GREEN);
    }

    @Test
    public void idgracza_test(){

        Enigma enigma = new Enigma();
        assertEquals(enigma.idgracza('1', 2), ('P'));
        assertEquals(enigma.idgracza('2', 2), ('B'));

        assertEquals(enigma.idgracza('1', 3), ('P'));
        assertEquals(enigma.idgracza('2', 3), ('Y'));
        assertEquals(enigma.idgracza('3', 3), ('R'));

        assertEquals(enigma.idgracza('1', 4), ('O'));
        assertEquals(enigma.idgracza('2', 4), ('Y'));
        assertEquals(enigma.idgracza('3', 4), ('R'));
        assertEquals(enigma.idgracza('4', 4), ('G'));

        assertEquals(enigma.idgracza('1', 6), ('P'));
        assertEquals(enigma.idgracza('2', 6), ('O'));
        assertEquals(enigma.idgracza('3', 6), ('Y'));
        assertEquals(enigma.idgracza('4', 6), ('B'));
        assertEquals(enigma.idgracza('5', 6), ('R'));
        assertEquals(enigma.idgracza('6', 6), ('G'));

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

