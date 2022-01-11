import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelGry extends JPanel {

    /** * Tablica guzikow, ktore beda naszymi polami planszy**/
    public JButton[][] pola_planszy = new JButton[19][29];
    public JPanel[][] niegrywalne_pola = new JPanel[19][29];
    public  Color kolor;

    final int[][] plansza = { //zakodowanie pól graczy, pustych pól i przestrzeni
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 1,-1, 1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 1,-1, 1,-1, 1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 1,-1, 1,-1, 1,-1, 1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1, 2,-1, 2,-1, 2,-1, 2,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 6,-1, 6,-1, 6,-1, 6,-1,-1},
            {-1,-1,-1, 2,-1, 2,-1, 2,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 6,-1, 6,-1, 6,-1,-1,-1},
            {-1,-1,-1,-1, 2,-1, 2,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 6,-1, 6,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1, 2,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 6,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1, 3,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 5,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1, 3,-1, 3,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 5,-1, 5,-1,-1,-1,-1},
            {-1,-1,-1, 3,-1, 3,-1, 3,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 5,-1, 5,-1, 5,-1,-1,-1},
            {-1,-1, 3,-1, 3,-1, 3,-1, 3,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 5,-1, 5,-1, 5,-1, 5,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 4,-1, 4,-1, 4,-1, 4,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 4,-1, 4,-1, 4,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 4,-1, 4,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 4,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}
    };

    /**
     * funkcja wyciąga koordynat x guzika z jego nazwy
     * @param coordinates nazwa
     * @return koordynat x
     */
    public int get_current_X(String coordinates){

        String x = "";
        int i = 0;
        while (coordinates.charAt(i) != ','){
            x = x + coordinates.charAt(i);
            i++;
        }
        return Integer.parseInt(x);
    }

    /**
     * funkcja wyciąga koordynat y guzika z jego nazwy
     * @param coordinates nazwa
     * @return koordynat y
     */
    public int get_current_Y(String coordinates){

        String y = "";
        int i = 0;
        while (coordinates.charAt(i) != ','){
            i++;
        }
        i++;
        while (i<coordinates.length()){
            y = y + coordinates.charAt(i);
            i++;
        }
        return Integer.parseInt(y);
    }

    /**
     * funkcja zaznacza na szaro sąsiednie pole ruchu
     * @param neighbourX sąsiad x
     * @param neighbourY sąsiad y
     */
    public void make_neighbour_grey(int neighbourX, int neighbourY){

        if(pola_planszy[neighbourX][neighbourY] != null){
            if(pola_planszy[neighbourX][neighbourY].getBackground() == Color.WHITE) {
                pola_planszy[neighbourX][neighbourY].setBackground(Color.GRAY);
            }
        }
    }

    /**
     * uogólnienie powyższej funkcji
     * @param currentX x
     * @param currentY y
     */
    public void make_ALL_neighbours_grey(int currentX, int currentY){
        make_neighbour_grey(currentX,currentY-2); // koloruje sąsiada na zachód
        make_neighbour_grey(currentX,currentY+2); // koloruje sąsiada na wschód
        make_neighbour_grey(currentX-1,currentY-1); // koloruje sąsiada na północny-zachód
        make_neighbour_grey(currentX-1,currentY+1); // koloruje sąsiada na północny-wschód
        make_neighbour_grey(currentX+1,currentY-1); // koloruje sąsiada na południowy-zachód
        make_neighbour_grey(currentX+1,currentY+1); // koloruje sąsiada na południowy-wschód
    }

    /**
     * funkcja oznacza na szaro pola do skoków
     * @param neighbourX x sąsiada
     * @param neighbourY y sąsiada
     * @param next_neighbourX x dalszego sąsiada
     * @param next_neighbourY y dalszego sąsiada
     */
    public void mark_possible_jumps(int neighbourX, int neighbourY, int next_neighbourX, int next_neighbourY){

        if(pola_planszy[neighbourX][neighbourY] != null && pola_planszy[next_neighbourX][next_neighbourY] != null){
            if (pola_planszy[neighbourX][neighbourY].getBackground() != Color.WHITE && pola_planszy[neighbourX][neighbourY].getBackground() != Color.GRAY){
                if(pola_planszy[next_neighbourX][next_neighbourY].getBackground()==Color.WHITE) {
                    pola_planszy[next_neighbourX][next_neighbourY].setBackground(Color.GRAY);
                    check_jump_ALL(next_neighbourX, next_neighbourY);
                }
            }
        }
    }

    /**
     * wykonanie oznaczenia szarego na legalnych polach
     * @param currentX x
     * @param currentY y
     */
    public void check_jump_ALL(int currentX, int currentY){
        mark_possible_jumps(currentX,currentY-2,currentX,currentY-4); // jump na zachód
        mark_possible_jumps(currentX,currentY+2,currentX,currentY+4); // jump na wschód
        mark_possible_jumps(currentX-1,currentY-1,currentX-2,currentY-2); // jump na północny-zachód
        mark_possible_jumps(currentX-1,currentY+1,currentX-2,currentY+2); // jump na północny-wschód
        mark_possible_jumps(currentX+1,currentY-1,currentX+2,currentY-2); // jump na południowy-zachód
        mark_possible_jumps(currentX+1,currentY+1,currentX+2,currentY+2); // jump na południowy-wschód
    }

    /**
     * koorowanie wszystkich możliwych skoków i sąsiadów
     * @param currentX x
     * @param currentY y
     */
    public void check_ALL(int currentX, int currentY){

        make_ALL_neighbours_grey(currentX,currentY);
        check_jump_ALL(currentX,currentY);

    }

    /**
     * czyści oznaczenie pola
     */
    void clear_grey(){
        for(int x=1; x<=17; x++){
            for (int y=2; y<=26; y++){
                if(pola_planszy[x][y] != null){
                    if(pola_planszy[x][y].getBackground()==Color.GRAY){
                        pola_planszy[x][y].setBackground(Color.WHITE);
                    }
                }
            }
        }
    }

    /**
     * dodanie obsługi guzików
     * @param dupa obsługa
     */
    public void dodaj_wlasciwosci_guzikom(ActionListener dupa){

        for(int x=1; x<=17; x++){
            for (int y=2; y<=26; y++){
                if(pola_planszy[x][y] != null){
                    pola_planszy[x][y].addActionListener(dupa);
                }
            }
        }
    }

    /**
     * sprawdzamy czy gracz wygrał
     * @return wygrał lub nie
     */
    public boolean check_win_BLUE(){
        return (pola_planszy[1][14].getBackground() == Color.BLUE && pola_planszy[2][13].getBackground() == Color.BLUE && pola_planszy[2][15].getBackground() == Color.BLUE &&
                pola_planszy[3][12].getBackground() == Color.BLUE && pola_planszy[3][14].getBackground() == Color.BLUE && pola_planszy[3][16].getBackground() == Color.BLUE &&
                pola_planszy[4][11].getBackground() == Color.BLUE && pola_planszy[4][13].getBackground() == Color.BLUE && pola_planszy[4][15].getBackground() == Color.BLUE &&
                pola_planszy[4][17].getBackground() == Color.BLUE);
    }
    public boolean check_win_PINK(){
        return (pola_planszy[17][14].getBackground() == Color.PINK && pola_planszy[16][13].getBackground() == Color.PINK && pola_planszy[16][15].getBackground() == Color.PINK &&
                pola_planszy[15][12].getBackground() == Color.PINK && pola_planszy[15][14].getBackground() == Color.PINK && pola_planszy[15][16].getBackground() == Color.PINK &&
                pola_planszy[14][11].getBackground() == Color.PINK && pola_planszy[14][13].getBackground() == Color.PINK && pola_planszy[14][15].getBackground() == Color.PINK &&
                pola_planszy[14][17].getBackground() == Color.PINK);
    }
    public boolean check_win_RED(){
        return (pola_planszy[5][2].getBackground() == Color.RED && pola_planszy[5][4].getBackground() == Color.RED && pola_planszy[5][6].getBackground() == Color.RED &&
                pola_planszy[5][8].getBackground() == Color.RED && pola_planszy[6][3].getBackground() == Color.RED && pola_planszy[6][5].getBackground() == Color.RED &&
                pola_planszy[6][7].getBackground() == Color.RED && pola_planszy[7][4].getBackground() == Color.RED && pola_planszy[7][6].getBackground() == Color.RED &&
                pola_planszy[8][5].getBackground() == Color.RED);
    }
    public boolean check_win_GREEN(){
        return (pola_planszy[13][2].getBackground() == Color.GREEN && pola_planszy[13][4].getBackground() == Color.GREEN && pola_planszy[13][6].getBackground() == Color.GREEN &&
                pola_planszy[13][8].getBackground() == Color.GREEN && pola_planszy[12][3].getBackground() == Color.GREEN && pola_planszy[12][5].getBackground() == Color.GREEN &&
                pola_planszy[12][7].getBackground() == Color.GREEN && pola_planszy[11][4].getBackground() == Color.GREEN && pola_planszy[11][6].getBackground() == Color.GREEN &&
                pola_planszy[10][5].getBackground() == Color.GREEN);
    }
    public boolean check_win_ORANGE(){
        return (pola_planszy[13][20].getBackground() == Color.ORANGE && pola_planszy[13][22].getBackground() == Color.ORANGE && pola_planszy[13][24].getBackground() == Color.ORANGE &&
                pola_planszy[13][26].getBackground() == Color.ORANGE && pola_planszy[12][21].getBackground() == Color.ORANGE && pola_planszy[12][23].getBackground() == Color.ORANGE &&
                pola_planszy[12][25].getBackground() == Color.ORANGE && pola_planszy[11][22].getBackground() == Color.ORANGE && pola_planszy[11][24].getBackground() == Color.ORANGE &&
                pola_planszy[10][23].getBackground() == Color.ORANGE);
    }
    public boolean check_win_YELLOW(){
        return (pola_planszy[5][20].getBackground() == Color.YELLOW && pola_planszy[5][22].getBackground() == Color.YELLOW && pola_planszy[5][24].getBackground() == Color.YELLOW &&
                pola_planszy[5][26].getBackground() == Color.YELLOW && pola_planszy[6][21].getBackground() == Color.YELLOW && pola_planszy[6][23].getBackground() == Color.YELLOW &&
                pola_planszy[6][25].getBackground() == Color.YELLOW && pola_planszy[7][22].getBackground() == Color.YELLOW && pola_planszy[7][24].getBackground() == Color.YELLOW &&
                pola_planszy[8][23].getBackground() == Color.YELLOW);
    }

    /**
     * sprawdzanie czy dany gracz wygrał
     * @param markergracza jego kolor
     * @return sprawdzanie zwycięstwa dla koloru
     */
    public boolean wygrana(char markergracza){
        return switch (markergracza) {
            case 'P' -> check_win_PINK();
            case 'B' -> check_win_BLUE();
            case 'O' -> check_win_ORANGE();
            case 'R' -> check_win_RED();
            case 'Y' -> check_win_YELLOW();
            case 'G' -> check_win_GREEN();
            default -> false;
        };
    }

    /**
     * dodawanie na planszę guzików i pól w zależności od ilości graczy
     * @param liczba_graczy liczba graczy
     * @param marker_gracza kolor danego gracza
     */
    PanelGry(int liczba_graczy, Color marker_gracza){

        setLayout(new GridLayout(19, 29));
        this.kolor = marker_gracza;

        for(int x=0;x<19;x++)
        {
            for(int y=0; y<29 ; y++) {

                if (plansza[x][y] == -1){

                    niegrywalne_pola[x][y] = new JPanel();
                    if(y == 0 || y == 28){
                        niegrywalne_pola[x][y].setBackground(marker_gracza);
                    }
                    this.add(niegrywalne_pola[x][y]);
                }
                else {
                    pola_planszy[x][y] = new JButton();
                    pola_planszy[x][y].setName(x + "," + y);

                    this.add(pola_planszy[x][y]);

                    koloruj_pole_w_zaleznosci_od_liczby_graczy(x,y,liczba_graczy);
                }
            }
        }
    }

    /**
     * siłowe wykonanie ruchu u innych graczy
     * @param x1 współrzędna xowa początkowa
     * @param y1 współrzędna yowa początkowa
     * @param x2 współrzędna xowa końcowa
     * @param y2 współrzędna yowa końcowa
     * @param pionek kolor pionka ruchu
     */
    public void messMoveSer(int x1, int y1, int x2, int y2, Color pionek){
        pola_planszy[x1][y1].setBackground(Color.WHITE);
        pola_planszy[x2][y2].setBackground(pionek);
    }

    /**
     * kolorowanie pól graczy
     * @param x współrzędna xowa pola
     * @param y współrzędna yowa pola
     * @param liczba_graczy ilość graczy
     */
    public void koloruj_pole_w_zaleznosci_od_liczby_graczy(int x, int y, int liczba_graczy){

        if(liczba_graczy==2){
            if (plansza[x][y] == 1) {
                pola_planszy[x][y].setBackground(Color.PINK);
            }
            else if (plansza[x][y] == 4) {
                pola_planszy[x][y].setBackground(Color.BLUE);
            }
            else {
                pola_planszy[x][y].setBackground(Color.WHITE);
            }
        }
        else if (liczba_graczy==3){
            if (plansza[x][y] == 1) {
                pola_planszy[x][y].setBackground(Color.PINK);
            }
            else if (plansza[x][y] == 3) {
                pola_planszy[x][y].setBackground(Color.YELLOW);
            }
            else if (plansza[x][y] == 5) {
                pola_planszy[x][y].setBackground(Color.RED);
            }
            else {
                pola_planszy[x][y].setBackground(Color.WHITE);
            }
        }
        else if (liczba_graczy==4){
            if (plansza[x][y] == 2) {
                pola_planszy[x][y].setBackground(Color.ORANGE);
            }
            else if (plansza[x][y] == 3) {
                pola_planszy[x][y].setBackground(Color.YELLOW);
            }
            else if (plansza[x][y] == 5) {
                pola_planszy[x][y].setBackground(Color.RED);
            }
            else if (plansza[x][y] == 6) {
                pola_planszy[x][y].setBackground(Color.GREEN);
            }
            else {
                pola_planszy[x][y].setBackground(Color.WHITE);
            }
        }
        else if (liczba_graczy==6){
            if (plansza[x][y] == 1) {
                pola_planszy[x][y].setBackground(Color.PINK);
            }
            else if (plansza[x][y] == 2) {
                pola_planszy[x][y].setBackground(Color.ORANGE);
            }
            else if (plansza[x][y] == 3) {
                pola_planszy[x][y].setBackground(Color.YELLOW);
            }
            else if (plansza[x][y] == 4) {
                pola_planszy[x][y].setBackground(Color.BLUE);
            }
            else if (plansza[x][y] == 5) {
                pola_planszy[x][y].setBackground(Color.RED);
            }
            else if (plansza[x][y] == 6) {
                pola_planszy[x][y].setBackground(Color.GREEN);
            }
            else {
                pola_planszy[x][y].setBackground(Color.WHITE);
            }
        }
    }
}
