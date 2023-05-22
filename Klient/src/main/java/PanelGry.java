import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * zawartość ramki - plansza i metody ruchu
 */
public class PanelGry extends JPanel {

    /**
     * Tablica guzikow, ktore beda naszymi polami planszy
     */
    public JButton[][] playable_boardfields = new JButton[19][29];
    /**
     * puste pola planszy
     */
    public JPanel[][] unplayable_boardfields = new JPanel[19][29];
    /**
     * kolor pionka
     */
    public  Color color;

    final int[][] board_code = { //zakodowanie pól graczy, pustych pól i przestrzeni
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

        if(playable_boardfields[neighbourX][neighbourY] != null){
            if(playable_boardfields[neighbourX][neighbourY].getBackground() == Color.WHITE) {
                playable_boardfields[neighbourX][neighbourY].setBackground(Color.GRAY);
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

        if(playable_boardfields[neighbourX][neighbourY] != null && playable_boardfields[next_neighbourX][next_neighbourY] != null){
            if (playable_boardfields[neighbourX][neighbourY].getBackground() != Color.WHITE && playable_boardfields[neighbourX][neighbourY].getBackground() != Color.GRAY){
                if(playable_boardfields[next_neighbourX][next_neighbourY].getBackground()==Color.WHITE) {
                    playable_boardfields[next_neighbourX][next_neighbourY].setBackground(Color.GRAY);
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
                if(playable_boardfields[x][y] != null){
                    if(playable_boardfields[x][y].getBackground()==Color.GRAY){
                        playable_boardfields[x][y].setBackground(Color.WHITE);
                    }
                }
            }
        }
    }

    /**
     * dodanie obsługi guzików
     * @param button_click obsługa
     */
    public void add_funcionality_for_fields(ActionListener button_click){

        for(int x=1; x<=17; x++){
            for (int y=2; y<=26; y++){
                if(playable_boardfields[x][y] != null){
                    playable_boardfields[x][y].addActionListener(button_click);
                }
            }
        }
    }

    /**
     * sprawdzamy czy gracz wygrał
     * @return wygrał lub nie
     */
    public boolean check_win_BLUE(){
        return (playable_boardfields[1][14].getBackground() == Color.BLUE && playable_boardfields[2][13].getBackground() == Color.BLUE && playable_boardfields[2][15].getBackground() == Color.BLUE &&
                playable_boardfields[3][12].getBackground() == Color.BLUE && playable_boardfields[3][14].getBackground() == Color.BLUE && playable_boardfields[3][16].getBackground() == Color.BLUE &&
                playable_boardfields[4][11].getBackground() == Color.BLUE && playable_boardfields[4][13].getBackground() == Color.BLUE && playable_boardfields[4][15].getBackground() == Color.BLUE &&
                playable_boardfields[4][17].getBackground() == Color.BLUE);
    }
    public boolean check_win_PINK(){
        return (playable_boardfields[17][14].getBackground() == Color.PINK && playable_boardfields[16][13].getBackground() == Color.PINK && playable_boardfields[16][15].getBackground() == Color.PINK &&
                playable_boardfields[15][12].getBackground() == Color.PINK && playable_boardfields[15][14].getBackground() == Color.PINK && playable_boardfields[15][16].getBackground() == Color.PINK &&
                playable_boardfields[14][11].getBackground() == Color.PINK && playable_boardfields[14][13].getBackground() == Color.PINK && playable_boardfields[14][15].getBackground() == Color.PINK &&
                playable_boardfields[14][17].getBackground() == Color.PINK);
    }
    public boolean check_win_RED(){
        return (playable_boardfields[5][2].getBackground() == Color.RED && playable_boardfields[5][4].getBackground() == Color.RED && playable_boardfields[5][6].getBackground() == Color.RED &&
                playable_boardfields[5][8].getBackground() == Color.RED && playable_boardfields[6][3].getBackground() == Color.RED && playable_boardfields[6][5].getBackground() == Color.RED &&
                playable_boardfields[6][7].getBackground() == Color.RED && playable_boardfields[7][4].getBackground() == Color.RED && playable_boardfields[7][6].getBackground() == Color.RED &&
                playable_boardfields[8][5].getBackground() == Color.RED);
    }
    public boolean check_win_GREEN(){
        return (playable_boardfields[13][2].getBackground() == Color.GREEN && playable_boardfields[13][4].getBackground() == Color.GREEN && playable_boardfields[13][6].getBackground() == Color.GREEN &&
                playable_boardfields[13][8].getBackground() == Color.GREEN && playable_boardfields[12][3].getBackground() == Color.GREEN && playable_boardfields[12][5].getBackground() == Color.GREEN &&
                playable_boardfields[12][7].getBackground() == Color.GREEN && playable_boardfields[11][4].getBackground() == Color.GREEN && playable_boardfields[11][6].getBackground() == Color.GREEN &&
                playable_boardfields[10][5].getBackground() == Color.GREEN);
    }
    public boolean check_win_ORANGE(){
        return (playable_boardfields[13][20].getBackground() == Color.ORANGE && playable_boardfields[13][22].getBackground() == Color.ORANGE && playable_boardfields[13][24].getBackground() == Color.ORANGE &&
                playable_boardfields[13][26].getBackground() == Color.ORANGE && playable_boardfields[12][21].getBackground() == Color.ORANGE && playable_boardfields[12][23].getBackground() == Color.ORANGE &&
                playable_boardfields[12][25].getBackground() == Color.ORANGE && playable_boardfields[11][22].getBackground() == Color.ORANGE && playable_boardfields[11][24].getBackground() == Color.ORANGE &&
                playable_boardfields[10][23].getBackground() == Color.ORANGE);
    }
    public boolean check_win_YELLOW(){
        return (playable_boardfields[5][20].getBackground() == Color.YELLOW && playable_boardfields[5][22].getBackground() == Color.YELLOW && playable_boardfields[5][24].getBackground() == Color.YELLOW &&
                playable_boardfields[5][26].getBackground() == Color.YELLOW && playable_boardfields[6][21].getBackground() == Color.YELLOW && playable_boardfields[6][23].getBackground() == Color.YELLOW &&
                playable_boardfields[6][25].getBackground() == Color.YELLOW && playable_boardfields[7][22].getBackground() == Color.YELLOW && playable_boardfields[7][24].getBackground() == Color.YELLOW &&
                playable_boardfields[8][23].getBackground() == Color.YELLOW);
    }

    /**
     * sprawdzanie czy dany gracz wygrał
     * @param literal_color_id jego kolor
     * @return sprawdzanie zwycięstwa dla koloru
     */
    public boolean check_win_any(char literal_color_id){
        return switch (literal_color_id) {
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
     * @param count_of_players liczba graczy
     * @param player_colormark kolor danego gracza
     */
    PanelGry(int count_of_players, Color player_colormark){

        setLayout(new GridLayout(19, 29));
        this.color = player_colormark;

        for(int x=0;x<19;x++)
        {
            for(int y=0; y<29 ; y++) {

                if (board_code[x][y] == -1){

                    unplayable_boardfields[x][y] = new JPanel();
                    if(y == 0 || y == 28){
                        unplayable_boardfields[x][y].setBackground(player_colormark);
                    }
                    this.add(unplayable_boardfields[x][y]);
                }
                else {
                    playable_boardfields[x][y] = new JButton();
                    playable_boardfields[x][y].setName(x + "," + y);

                    this.add(playable_boardfields[x][y]);

                    paint_board_corners(x,y,count_of_players);
                }
            }
        }
    }

    /**
     * siłowe wykonanie ruchu u innych graczy, gdy serwer zwaliduje ruch jednego gracza
     * @param x1 współrzędna xowa początkowa
     * @param y1 współrzędna yowa początkowa
     * @param x2 współrzędna xowa końcowa
     * @param y2 współrzędna yowa końcowa
     * @param pionek kolor pionka ruchu
     */
    public void makeMoveFromServer(int x1, int y1, int x2, int y2, Color pionek){
        playable_boardfields[x1][y1].setBackground(Color.WHITE);
        playable_boardfields[x2][y2].setBackground(pionek);
    }

    /**
     * kolorowanie pól graczy
     * @param x współrzędna xowa pola
     * @param y współrzędna yowa pola
     * @param liczba_graczy ilość graczy
     */
    public void paint_board_corners(int x, int y, int liczba_graczy){

        if(liczba_graczy==2){
            if (board_code[x][y] == 1) {
                playable_boardfields[x][y].setBackground(Color.PINK);
            }
            else if (board_code[x][y] == 4) {
                playable_boardfields[x][y].setBackground(Color.BLUE);
            }
            else {
                playable_boardfields[x][y].setBackground(Color.WHITE);
            }
        }
        else if (liczba_graczy==3){
            if (board_code[x][y] == 1) {
                playable_boardfields[x][y].setBackground(Color.PINK);
            }
            else if (board_code[x][y] == 3) {
                playable_boardfields[x][y].setBackground(Color.YELLOW);
            }
            else if (board_code[x][y] == 5) {
                playable_boardfields[x][y].setBackground(Color.RED);
            }
            else {
                playable_boardfields[x][y].setBackground(Color.WHITE);
            }
        }
        else if (liczba_graczy==4){
            if (board_code[x][y] == 2) {
                playable_boardfields[x][y].setBackground(Color.ORANGE);
            }
            else if (board_code[x][y] == 3) {
                playable_boardfields[x][y].setBackground(Color.YELLOW);
            }
            else if (board_code[x][y] == 5) {
                playable_boardfields[x][y].setBackground(Color.RED);
            }
            else if (board_code[x][y] == 6) {
                playable_boardfields[x][y].setBackground(Color.GREEN);
            }
            else {
                playable_boardfields[x][y].setBackground(Color.WHITE);
            }
        }
        else if (liczba_graczy==6){
            if (board_code[x][y] == 1) {
                playable_boardfields[x][y].setBackground(Color.PINK);
            }
            else if (board_code[x][y] == 2) {
                playable_boardfields[x][y].setBackground(Color.ORANGE);
            }
            else if (board_code[x][y] == 3) {
                playable_boardfields[x][y].setBackground(Color.YELLOW);
            }
            else if (board_code[x][y] == 4) {
                playable_boardfields[x][y].setBackground(Color.BLUE);
            }
            else if (board_code[x][y] == 5) {
                playable_boardfields[x][y].setBackground(Color.RED);
            }
            else if (board_code[x][y] == 6) {
                playable_boardfields[x][y].setBackground(Color.GREEN);
            }
            else {
                playable_boardfields[x][y].setBackground(Color.WHITE);
            }
        }
    }
}
