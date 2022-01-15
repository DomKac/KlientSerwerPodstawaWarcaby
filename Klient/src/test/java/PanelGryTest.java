import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class PanelGryTest {

    @Test
    public void wygrana_test(){
        PanelGry panelGry= new PanelGry(6,Color.BLUE);


        assertFalse(panelGry.check_win_any('B'));
        assertFalse(panelGry.check_win_any('P'));
        assertFalse(panelGry.check_win_any('Y'));
        assertFalse(panelGry.check_win_any('O'));
        assertFalse(panelGry.check_win_any('R'));
        assertFalse(panelGry.check_win_any('G'));

        panelGry.playable_boardfields[1][14].setBackground(Color.BLUE);
        panelGry.playable_boardfields[2][13].setBackground(Color.BLUE);
        assertFalse(panelGry.check_win_any('B'));
        panelGry.playable_boardfields[2][15].setBackground(Color.BLUE);
        panelGry.playable_boardfields[3][12].setBackground(Color.BLUE);
        assertFalse(panelGry.check_win_any('B'));
        panelGry.playable_boardfields[3][14].setBackground(Color.BLUE);
        panelGry.playable_boardfields[3][16].setBackground(Color.BLUE);
        assertFalse(panelGry.check_win_any('B'));
        panelGry.playable_boardfields[4][11].setBackground(Color.BLUE);
        panelGry.playable_boardfields[4][13].setBackground(Color.BLUE);
        assertFalse(panelGry.check_win_any('B'));
        assertFalse(panelGry.check_win_BLUE());
        panelGry.playable_boardfields[4][15].setBackground(Color.BLUE);
        panelGry.playable_boardfields[4][17].setBackground(Color.BLUE);
        assertTrue(panelGry.check_win_BLUE());
        assertTrue(panelGry.check_win_any('B'));

        panelGry.playable_boardfields[17][14].setBackground(Color.PINK);
        panelGry.playable_boardfields[16][13].setBackground(Color.PINK);
        panelGry.playable_boardfields[16][15].setBackground(Color.PINK);
        panelGry.playable_boardfields[15][12].setBackground(Color.PINK);
        panelGry.playable_boardfields[15][14].setBackground(Color.PINK);
        panelGry.playable_boardfields[15][16].setBackground(Color.PINK);
        panelGry.playable_boardfields[14][11].setBackground(Color.PINK);
        panelGry.playable_boardfields[14][13].setBackground(Color.PINK);
        panelGry.playable_boardfields[14][15].setBackground(Color.PINK);
        assertFalse(panelGry.check_win_PINK());
        assertFalse(panelGry.check_win_any('P'));
        panelGry.playable_boardfields[14][17].setBackground(Color.PINK);
        assertTrue(panelGry.check_win_PINK());
        assertTrue(panelGry.check_win_any('P'));
        assertFalse(panelGry.check_win_any('R'));

        panelGry.playable_boardfields[5][2].setBackground(Color.RED);
        panelGry.playable_boardfields[5][4].setBackground(Color.RED);
        panelGry.playable_boardfields[5][6].setBackground(Color.RED);
        panelGry.playable_boardfields[5][8].setBackground(Color.RED);
        panelGry.playable_boardfields[6][3].setBackground(Color.RED);
        panelGry.playable_boardfields[6][5].setBackground(Color.RED);
        panelGry.playable_boardfields[6][7].setBackground(Color.RED);
        panelGry.playable_boardfields[7][4].setBackground(Color.RED);
        panelGry.playable_boardfields[7][6].setBackground(Color.RED);
        panelGry.playable_boardfields[8][5].setBackground(Color.RED);
        assertTrue(panelGry.check_win_RED());
        assertTrue(panelGry.check_win_any('R'));

        panelGry.playable_boardfields[13][2].setBackground(Color.GREEN);
        assertFalse(panelGry.check_win_any('G'));
        panelGry.playable_boardfields[13][4].setBackground(Color.GREEN);
        assertFalse(panelGry.check_win_any('G'));
        panelGry.playable_boardfields[13][6].setBackground(Color.GREEN);
        assertFalse(panelGry.check_win_any('G'));
        panelGry.playable_boardfields[13][8].setBackground(Color.GREEN);
        assertFalse(panelGry.check_win_any('G'));
        panelGry.playable_boardfields[12][3].setBackground(Color.GREEN);
        assertFalse(panelGry.check_win_any('G'));
        panelGry.playable_boardfields[12][5].setBackground(Color.GREEN);
        assertFalse(panelGry.check_win_any('G'));
        panelGry.playable_boardfields[12][7].setBackground(Color.GREEN);
        assertFalse(panelGry.check_win_any('G'));
        panelGry.playable_boardfields[11][4].setBackground(Color.GREEN);
        assertFalse(panelGry.check_win_any('G'));
        panelGry.playable_boardfields[11][6].setBackground(Color.GREEN);
        assertFalse(panelGry.check_win_any('G'));
        panelGry.playable_boardfields[10][5].setBackground(Color.GREEN);
        assertTrue(panelGry.check_win_GREEN());
        assertTrue(panelGry.check_win_any('G'));

        panelGry.playable_boardfields[13][20].setBackground(Color.ORANGE);
        panelGry.playable_boardfields[13][22].setBackground(Color.ORANGE);
        panelGry.playable_boardfields[13][24].setBackground(Color.ORANGE);
        panelGry.playable_boardfields[13][26].setBackground(Color.ORANGE);
        panelGry.playable_boardfields[12][21].setBackground(Color.ORANGE);
        panelGry.playable_boardfields[12][23].setBackground(Color.ORANGE);
        panelGry.playable_boardfields[12][25].setBackground(Color.ORANGE);
        panelGry.playable_boardfields[11][22].setBackground(Color.ORANGE);
        panelGry.playable_boardfields[11][24].setBackground(Color.ORANGE);
        assertFalse(panelGry.check_win_ORANGE());
        assertFalse(panelGry.check_win_any('O'));
        panelGry.playable_boardfields[10][23].setBackground(Color.ORANGE);
        assertTrue(panelGry.check_win_ORANGE());
        assertTrue(panelGry.check_win_any('O'));

        panelGry.playable_boardfields[5][20].setBackground(Color.YELLOW);
        panelGry.playable_boardfields[5][22].setBackground(Color.YELLOW);
        panelGry.playable_boardfields[5][24].setBackground(Color.YELLOW);
        panelGry.playable_boardfields[5][26].setBackground(Color.YELLOW);
        panelGry.playable_boardfields[6][21].setBackground(Color.YELLOW);
        panelGry.playable_boardfields[6][23].setBackground(Color.YELLOW);
        panelGry.playable_boardfields[6][25].setBackground(Color.YELLOW);
        panelGry.playable_boardfields[7][22].setBackground(Color.YELLOW);
        panelGry.playable_boardfields[7][24].setBackground(Color.YELLOW);
        assertFalse(panelGry.check_win_YELLOW());
        assertFalse(panelGry.check_win_any('Y'));
        panelGry.playable_boardfields[8][23].setBackground(Color.YELLOW);
        assertTrue(panelGry.check_win_YELLOW());
        assertTrue(panelGry.check_win_any('Y'));

        assertTrue(panelGry.check_win_any('R'));
        assertTrue(panelGry.check_win_any('O'));
        assertTrue(panelGry.check_win_any('G'));
        assertTrue(panelGry.check_win_any('B'));
        assertTrue(panelGry.check_win_any('P'));
    }

    // Testujemy opdowiednie generowanie planszy (tworzenie przycisków na odpowiednich miejscach i odpowiednie ich pokolorowanie)
    @Test
    public void PanelGry_test(){
        PanelGry panel2 = new PanelGry(2,Color.BLUE); // panel gry dla 2 graczy
        PanelGry panel3 = new PanelGry(3,Color.RED); // panel gry dla 3 graczy
        PanelGry panel4 = new PanelGry(4,Color.YELLOW); // panel gry dla 4 graczy
        PanelGry panel6 = new PanelGry(6,Color.ORANGE); // panel gry dla 6 graczy

        assertSame(panel2.playable_boardfields[1][14].getBackground(), Color.PINK);
        assertSame(panel2.playable_boardfields[2][13].getBackground(), Color.PINK);
        assertNull(panel2.playable_boardfields[2][14]);
        assertSame(panel2.playable_boardfields[2][15].getBackground(), Color.PINK);

        assertSame(panel2.playable_boardfields[3][12].getBackground(), Color.PINK);
        assertNull(panel2.playable_boardfields[3][13]);
        assertSame(panel2.playable_boardfields[3][14].getBackground(), Color.PINK);
        assertNull(panel2.playable_boardfields[3][15]);
        assertSame(panel2.playable_boardfields[3][16].getBackground(), Color.PINK);

        assertSame(panel2.playable_boardfields[4][11].getBackground(), Color.PINK);
        assertNull(panel2.playable_boardfields[4][12]);
        assertSame(panel2.playable_boardfields[4][13].getBackground(), Color.PINK);
        assertNull(panel2.playable_boardfields[4][14]);
        assertSame(panel2.playable_boardfields[4][15].getBackground(), Color.PINK);
        assertNull(panel2.playable_boardfields[4][16]);
        assertSame(panel2.playable_boardfields[4][17].getBackground(), Color.PINK);

        // Gdyby plansza była stworzona dla 2 lub 6 graczy poniższe pola planszy powinny mieć kolor niebieski,
        // jednak w przypadku planszy dla 3 lub 4 graczy powinny mieć kolor biały.
        assertSame(panel2.playable_boardfields[17][14].getBackground(), Color.BLUE);
        assertSame(panel2.playable_boardfields[16][13].getBackground(), Color.BLUE);
        assertSame(panel2.playable_boardfields[16][15].getBackground(), Color.BLUE);
        assertSame(panel2.playable_boardfields[15][12].getBackground(), Color.BLUE);
        assertSame(panel2.playable_boardfields[15][14].getBackground(), Color.BLUE);
        assertSame(panel2.playable_boardfields[15][16].getBackground(), Color.BLUE);
        assertSame(panel2.playable_boardfields[14][11].getBackground(), Color.BLUE);
        assertSame(panel2.playable_boardfields[14][13].getBackground(), Color.BLUE);
        assertSame(panel2.playable_boardfields[14][15].getBackground(), Color.BLUE);

        assertSame(panel6.playable_boardfields[17][14].getBackground(), Color.BLUE);
        assertSame(panel6.playable_boardfields[16][13].getBackground(), Color.BLUE);
        assertSame(panel6.playable_boardfields[16][15].getBackground(), Color.BLUE);
        assertSame(panel6.playable_boardfields[15][12].getBackground(), Color.BLUE);
        assertSame(panel6.playable_boardfields[15][14].getBackground(), Color.BLUE);
        assertSame(panel6.playable_boardfields[15][16].getBackground(), Color.BLUE);
        assertSame(panel6.playable_boardfields[14][11].getBackground(), Color.BLUE);
        assertSame(panel6.playable_boardfields[14][13].getBackground(), Color.BLUE);
        assertSame(panel6.playable_boardfields[14][15].getBackground(), Color.BLUE);

        assertSame(panel3.playable_boardfields[17][14].getBackground(), Color.WHITE);
        assertSame(panel3.playable_boardfields[16][13].getBackground(), Color.WHITE);
        assertSame(panel3.playable_boardfields[16][15].getBackground(), Color.WHITE);
        assertSame(panel3.playable_boardfields[15][12].getBackground(), Color.WHITE);
        assertSame(panel3.playable_boardfields[15][14].getBackground(), Color.WHITE);
        assertSame(panel3.playable_boardfields[15][16].getBackground(), Color.WHITE);
        assertSame(panel3.playable_boardfields[14][11].getBackground(), Color.WHITE);
        assertSame(panel3.playable_boardfields[14][13].getBackground(), Color.WHITE);
        assertSame(panel3.playable_boardfields[14][15].getBackground(), Color.WHITE);

        assertSame(panel4.playable_boardfields[17][14].getBackground(), Color.WHITE);
        assertSame(panel4.playable_boardfields[16][13].getBackground(), Color.WHITE);
        assertSame(panel4.playable_boardfields[16][15].getBackground(), Color.WHITE);
        assertSame(panel4.playable_boardfields[15][12].getBackground(), Color.WHITE);
        assertSame(panel4.playable_boardfields[15][14].getBackground(), Color.WHITE);
        assertSame(panel4.playable_boardfields[15][16].getBackground(), Color.WHITE);
        assertSame(panel4.playable_boardfields[14][11].getBackground(), Color.WHITE);
        assertSame(panel4.playable_boardfields[14][13].getBackground(), Color.WHITE);
        assertSame(panel4.playable_boardfields[14][15].getBackground(), Color.WHITE);


        // Gdyby plansza była stworzona dla 4 lub 6 graczy poniższe pola planszy powinny mieć kolor zielony,
        // jednak w przypadku planszy dla 2 lub 3 graczy powinny mieć kolor biały.
        assertSame(panel6.playable_boardfields[5][20].getBackground(),Color.GREEN);
        assertSame(panel6.playable_boardfields[5][22].getBackground(),Color.GREEN);
        assertSame(panel6.playable_boardfields[5][24].getBackground(),Color.GREEN);
        assertSame(panel6.playable_boardfields[5][26].getBackground(),Color.GREEN);
        assertSame(panel6.playable_boardfields[6][21].getBackground(),Color.GREEN);
        assertSame(panel6.playable_boardfields[6][23].getBackground(),Color.GREEN);
        assertSame(panel6.playable_boardfields[6][25].getBackground(),Color.GREEN);
        assertSame(panel6.playable_boardfields[7][22].getBackground(),Color.GREEN);
        assertSame(panel6.playable_boardfields[7][24].getBackground(),Color.GREEN);

        assertSame(panel4.playable_boardfields[5][20].getBackground(),Color.GREEN);
        assertSame(panel4.playable_boardfields[5][22].getBackground(),Color.GREEN);
        assertSame(panel4.playable_boardfields[5][24].getBackground(),Color.GREEN);
        assertSame(panel4.playable_boardfields[5][26].getBackground(),Color.GREEN);
        assertSame(panel4.playable_boardfields[6][21].getBackground(),Color.GREEN);
        assertSame(panel4.playable_boardfields[6][23].getBackground(),Color.GREEN);
        assertSame(panel4.playable_boardfields[6][25].getBackground(),Color.GREEN);
        assertSame(panel4.playable_boardfields[7][22].getBackground(),Color.GREEN);
        assertSame(panel4.playable_boardfields[7][24].getBackground(),Color.GREEN);

        assertSame(panel2.playable_boardfields[5][20].getBackground(),Color.WHITE);
        assertSame(panel2.playable_boardfields[5][22].getBackground(),Color.WHITE);
        assertSame(panel2.playable_boardfields[5][24].getBackground(),Color.WHITE);
        assertSame(panel2.playable_boardfields[5][26].getBackground(),Color.WHITE);
        assertSame(panel2.playable_boardfields[6][21].getBackground(),Color.WHITE);
        assertSame(panel2.playable_boardfields[6][23].getBackground(),Color.WHITE);
        assertSame(panel2.playable_boardfields[6][25].getBackground(),Color.WHITE);
        assertSame(panel2.playable_boardfields[7][22].getBackground(),Color.WHITE);
        assertSame(panel2.playable_boardfields[7][24].getBackground(),Color.WHITE);

        assertSame(panel3.playable_boardfields[5][20].getBackground(),Color.WHITE);
        assertSame(panel3.playable_boardfields[5][22].getBackground(),Color.WHITE);
        assertSame(panel3.playable_boardfields[5][24].getBackground(),Color.WHITE);
        assertSame(panel3.playable_boardfields[5][26].getBackground(),Color.WHITE);
        assertSame(panel3.playable_boardfields[6][21].getBackground(),Color.WHITE);
        assertSame(panel3.playable_boardfields[6][23].getBackground(),Color.WHITE);
        assertSame(panel3.playable_boardfields[6][25].getBackground(),Color.WHITE);
        assertSame(panel3.playable_boardfields[7][22].getBackground(),Color.WHITE);
        assertSame(panel3.playable_boardfields[7][24].getBackground(),Color.WHITE);


        // Gdyby plansza była stworzona dla 3 lub 4 lub 6 graczy poniższe pola planszy powinny mieć kolor zielony,
        // jednak w przypadku planszy dla 2 graczy powinny mieć kolor biały.
        assertSame(panel2.playable_boardfields[13][20].getBackground(),Color.WHITE);
        assertSame(panel2.playable_boardfields[13][22].getBackground(),Color.WHITE);
        assertSame(panel2.playable_boardfields[13][24].getBackground(),Color.WHITE);
        assertSame(panel2.playable_boardfields[13][26].getBackground(),Color.WHITE);
        assertSame(panel2.playable_boardfields[12][21].getBackground(),Color.WHITE);
        assertSame(panel2.playable_boardfields[12][23].getBackground(),Color.WHITE);
        assertSame(panel2.playable_boardfields[12][25].getBackground(),Color.WHITE);
        assertSame(panel2.playable_boardfields[11][22].getBackground(),Color.WHITE);
        assertSame(panel2.playable_boardfields[11][24].getBackground(),Color.WHITE);
        assertSame(panel2.playable_boardfields[10][23].getBackground(),Color.WHITE);

        assertSame(panel3.playable_boardfields[13][20].getBackground(),Color.RED);
        assertSame(panel3.playable_boardfields[13][22].getBackground(),Color.RED);
        assertSame(panel3.playable_boardfields[13][24].getBackground(),Color.RED);
        assertSame(panel3.playable_boardfields[13][26].getBackground(),Color.RED);
        assertSame(panel3.playable_boardfields[12][21].getBackground(),Color.RED);
        assertSame(panel3.playable_boardfields[12][23].getBackground(),Color.RED);
        assertSame(panel3.playable_boardfields[12][25].getBackground(),Color.RED);
        assertSame(panel3.playable_boardfields[11][22].getBackground(),Color.RED);
        assertSame(panel3.playable_boardfields[11][24].getBackground(),Color.RED);
        assertSame(panel3.playable_boardfields[10][23].getBackground(),Color.RED);

        assertSame(panel4.playable_boardfields[13][20].getBackground(),Color.RED);
        assertSame(panel4.playable_boardfields[13][22].getBackground(),Color.RED);
        assertSame(panel4.playable_boardfields[13][24].getBackground(),Color.RED);
        assertSame(panel4.playable_boardfields[13][26].getBackground(),Color.RED);
        assertSame(panel4.playable_boardfields[12][21].getBackground(),Color.RED);
        assertSame(panel4.playable_boardfields[12][23].getBackground(),Color.RED);
        assertSame(panel4.playable_boardfields[12][25].getBackground(),Color.RED);
        assertSame(panel4.playable_boardfields[11][22].getBackground(),Color.RED);
        assertSame(panel4.playable_boardfields[11][24].getBackground(),Color.RED);
        assertSame(panel4.playable_boardfields[10][23].getBackground(),Color.RED);

        assertSame(panel6.playable_boardfields[13][20].getBackground(),Color.RED);
        assertSame(panel6.playable_boardfields[13][22].getBackground(),Color.RED);
        assertSame(panel6.playable_boardfields[13][24].getBackground(),Color.RED);
        assertSame(panel6.playable_boardfields[13][26].getBackground(),Color.RED);
        assertSame(panel6.playable_boardfields[12][21].getBackground(),Color.RED);
        assertSame(panel6.playable_boardfields[12][23].getBackground(),Color.RED);
        assertSame(panel6.playable_boardfields[12][25].getBackground(),Color.RED);
        assertSame(panel6.playable_boardfields[11][22].getBackground(),Color.RED);
        assertSame(panel6.playable_boardfields[11][24].getBackground(),Color.RED);
        assertSame(panel6.playable_boardfields[10][23].getBackground(),Color.RED);

    }

    @Test
    public void messMoveSer(){
        PanelGry panelGry = new PanelGry(6,Color.ORANGE);

        assertSame(panelGry.playable_boardfields[13][20].getBackground(),Color.RED);
        assertSame(panelGry.playable_boardfields[11][14].getBackground(),Color.WHITE);

        panelGry.makeMoveFromServer(13,20,11,14,Color.RED);

        assertSame(panelGry.playable_boardfields[13][20].getBackground(),Color.WHITE);
        assertSame(panelGry.playable_boardfields[11][14].getBackground(),Color.RED);

    }

    @Test
    public void get_current_X_test(){
        PanelGry panelGry = new PanelGry(6,Color.PINK);

        assertEquals(panelGry.playable_boardfields[6][25].getName(),"6,25");
        String coordinates = panelGry.playable_boardfields[6][25].getName();
        assertEquals(panelGry.get_current_X(coordinates),6);

    }

    @Test
    public void get_current_Y_test(){
        PanelGry panelGry = new PanelGry(4,Color.GREEN);

        assertEquals(panelGry.playable_boardfields[15][16].getName(),"15,16");
        String coordinates = panelGry.playable_boardfields[15][16].getName();
        assertEquals(panelGry.get_current_Y(coordinates),16);

    }

    @Test
    public void make_neighbour_grey_test(){
        PanelGry panelGry = new PanelGry(6,Color.PINK);

        //metoda nie powinna nic zmienić, bo dla 6 graczy to pole planszy jest zakolorowane na pomarańczowe
        // (stoi tam pionek pomarańczowy pionek, więc nie można się tam ruszyć)
        assertEquals(panelGry.playable_boardfields[7][6].getBackground(), Color.ORANGE);
        panelGry.make_neighbour_grey(7,6);
        assertEquals(panelGry.playable_boardfields[7][6].getBackground(), Color.ORANGE);

        //metoda make_neighbour_gray powinna zmienić pole 10,13 na kolor szary. ponieważ wcześniej było puste
        //(było koloru białego)
        assertEquals(panelGry.playable_boardfields[10][13].getBackground(), Color.WHITE);
        panelGry.make_neighbour_grey(10,13);
        assertEquals(panelGry.playable_boardfields[10][13].getBackground(), Color.GRAY);

    }

    @Test
    public void make_ALL_neighbours_grey_test(){
        PanelGry panelGry = new PanelGry(2,Color.PINK);

        assertEquals(panelGry.playable_boardfields[7][14].getBackground(),Color.WHITE);
        assertEquals(panelGry.playable_boardfields[7][18].getBackground(),Color.WHITE);
        assertEquals(panelGry.playable_boardfields[6][15].getBackground(),Color.WHITE);
        assertEquals(panelGry.playable_boardfields[6][17].getBackground(),Color.WHITE);
        assertEquals(panelGry.playable_boardfields[8][15].getBackground(),Color.WHITE);
        assertEquals(panelGry.playable_boardfields[8][17].getBackground(),Color.WHITE);
        panelGry.make_ALL_neighbours_grey(7,16);
        assertEquals(panelGry.playable_boardfields[7][14].getBackground(),Color.GRAY);
        assertEquals(panelGry.playable_boardfields[7][18].getBackground(),Color.GRAY);
        assertEquals(panelGry.playable_boardfields[6][15].getBackground(),Color.GRAY);
        assertEquals(panelGry.playable_boardfields[6][17].getBackground(),Color.GRAY);
        assertEquals(panelGry.playable_boardfields[8][15].getBackground(),Color.GRAY);
        assertEquals(panelGry.playable_boardfields[8][17].getBackground(),Color.GRAY);

    }

    @Test
    public void mark_possible_jumps_test(){
        PanelGry panelGry = new PanelGry(6,Color.RED);

        assertEquals(panelGry.playable_boardfields[12][19].getBackground(),Color.WHITE);
        assertNotEquals(panelGry.playable_boardfields[12][21].getBackground(),Color.WHITE);
        panelGry.mark_possible_jumps(12,21,12,19);
        assertEquals(panelGry.playable_boardfields[12][19].getBackground(),Color.GRAY);

    }

    @Test
    public void check_jump_ALL_test(){
        PanelGry panelGry = new PanelGry(6,Color.RED);
        panelGry.playable_boardfields[12][17].setBackground(Color.BLUE);
        panelGry.playable_boardfields[11][14].setBackground(Color.YELLOW);

        assertEquals(panelGry.playable_boardfields[12][23].getBackground(),Color.RED);
        assertEquals(panelGry.playable_boardfields[12][19].getBackground(),Color.WHITE);
        assertNotEquals(panelGry.playable_boardfields[12][21].getBackground(),Color.WHITE);
        assertNotEquals(panelGry.playable_boardfields[11][22].getBackground(),Color.WHITE);
        assertEquals(panelGry.playable_boardfields[10][21].getBackground(),Color.WHITE);
        assertNotEquals(panelGry.playable_boardfields[12][17].getBackground(),Color.WHITE);
        assertEquals(panelGry.playable_boardfields[12][15].getBackground(),Color.WHITE);
        assertNotEquals(panelGry.playable_boardfields[11][14].getBackground(),Color.WHITE);
        assertEquals(panelGry.playable_boardfields[10][13].getBackground(),Color.WHITE);

        panelGry.check_jump_ALL(12,23);

        assertEquals(panelGry.playable_boardfields[12][23].getBackground(),Color.RED);
        assertEquals(panelGry.playable_boardfields[12][19].getBackground(),Color.GRAY);
        assertNotEquals(panelGry.playable_boardfields[12][21].getBackground(),Color.WHITE);
        assertNotEquals(panelGry.playable_boardfields[11][22].getBackground(),Color.WHITE);
        assertEquals(panelGry.playable_boardfields[10][21].getBackground(),Color.GRAY);
        assertNotEquals(panelGry.playable_boardfields[12][17].getBackground(),Color.WHITE);
        assertEquals(panelGry.playable_boardfields[12][15].getBackground(),Color.GRAY);
        assertNotEquals(panelGry.playable_boardfields[11][14].getBackground(),Color.WHITE);
        assertEquals(panelGry.playable_boardfields[10][13].getBackground(),Color.GRAY);

    }

    @Test
    public void check_ALL_test(){
        PanelGry panelGry = new PanelGry(6,Color.RED);
        panelGry.playable_boardfields[12][17].setBackground(Color.BLUE);
        panelGry.playable_boardfields[11][14].setBackground(Color.YELLOW);

        assertEquals(panelGry.playable_boardfields[12][23].getBackground(),Color.RED);
        assertEquals(panelGry.playable_boardfields[12][19].getBackground(),Color.WHITE);
        assertNotEquals(panelGry.playable_boardfields[12][21].getBackground(),Color.WHITE);
        assertNotEquals(panelGry.playable_boardfields[11][22].getBackground(),Color.WHITE);
        assertEquals(panelGry.playable_boardfields[10][21].getBackground(),Color.WHITE);
        assertNotEquals(panelGry.playable_boardfields[12][17].getBackground(),Color.WHITE);
        assertEquals(panelGry.playable_boardfields[12][15].getBackground(),Color.WHITE);
        assertNotEquals(panelGry.playable_boardfields[11][14].getBackground(),Color.WHITE);
        assertEquals(panelGry.playable_boardfields[10][13].getBackground(),Color.WHITE);

        panelGry.check_ALL(12,23);

        assertEquals(panelGry.playable_boardfields[12][23].getBackground(),Color.RED);
        assertEquals(panelGry.playable_boardfields[12][19].getBackground(),Color.GRAY);
        assertNotEquals(panelGry.playable_boardfields[12][21].getBackground(),Color.WHITE);
        assertNotEquals(panelGry.playable_boardfields[11][22].getBackground(),Color.WHITE);
        assertEquals(panelGry.playable_boardfields[10][21].getBackground(),Color.GRAY);
        assertNotEquals(panelGry.playable_boardfields[12][17].getBackground(),Color.WHITE);
        assertEquals(panelGry.playable_boardfields[12][15].getBackground(),Color.GRAY);
        assertNotEquals(panelGry.playable_boardfields[11][14].getBackground(),Color.WHITE);
        assertEquals(panelGry.playable_boardfields[10][13].getBackground(),Color.GRAY);
        assertNotEquals(panelGry.playable_boardfields[12][21].getBackground(),Color.GRAY);
        assertNotEquals(panelGry.playable_boardfields[11][22].getBackground(),Color.GRAY);
    }

    @Test
    public void clear_grey_test(){
        PanelGry panelGry = new PanelGry(6,Color.RED);
        panelGry.playable_boardfields[12][17].setBackground(Color.GRAY);
        panelGry.playable_boardfields[11][14].setBackground(Color.GRAY);
        panelGry.playable_boardfields[10][9].setBackground(Color.GRAY);
        panelGry.playable_boardfields[5][16].setBackground(Color.GRAY);
        panelGry.playable_boardfields[9][20].setBackground(Color.GRAY);
        panelGry.playable_boardfields[8][9].setBackground(Color.GRAY);
        panelGry.playable_boardfields[13][10].setBackground(Color.GRAY);

        panelGry.clear_grey();

        assertEquals(panelGry.playable_boardfields[12][17].getBackground(),Color.WHITE);
        assertEquals(panelGry.playable_boardfields[11][14].getBackground(),Color.WHITE);
        assertEquals(panelGry.playable_boardfields[10][9].getBackground(),Color.WHITE);
        assertEquals(panelGry.playable_boardfields[5][16].getBackground(),Color.WHITE);
        assertEquals(panelGry.playable_boardfields[9][20].getBackground(),Color.WHITE);
        assertEquals(panelGry.playable_boardfields[8][9].getBackground(),Color.WHITE);
        assertEquals(panelGry.playable_boardfields[13][10].getBackground(),Color.WHITE);

    }
}
