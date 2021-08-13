package comp1110.ass2;


import java.util.*;

/**
 * This class provides the text interface for the Steps Game
 *
 * The game is based directly on Smart Games' IQ-Steps game
 * (http://www.smartgames.eu/en/smartgames/iq-steps)
 */
public class StepsGame {

    /**
     * Determine whether a piece placement is well-formed according to the following:
     * - it consists of exactly three characters
     * - the first character is in the range A .. H (shapes)
     * - the second character is in the range A .. H (orientations)
     * - the third character is in the range A .. Y and a .. y (locations)
     *
     * @param piecePlacement A string describing a piece placement
     * @return True if the piece placement is well-formed
     *
     * Concept by Ziwei Liu (u6380075), written by Tingyu Pan (u6259941)
     */
    static boolean isPiecePlacementWellFormed(String piecePlacement) {
        // FIXME Task 2: determine whether a piece placement is well-formed
        return ((piecePlacement.charAt(0) >= 'A' && piecePlacement.charAt(0) <= 'H')
                && (piecePlacement.charAt(1) >= 'A' && piecePlacement.charAt(1) <= 'H')
                && ((piecePlacement.charAt(2) >= 'A' && piecePlacement.charAt(2) <= 'Y')
                ||(piecePlacement.charAt(2) >= 'a' && piecePlacement.charAt(2) <= 'y')));
    }

    /**
     * Determine whether a placement string is well-formed:
     *  - it consists of exactly N three-character piece placements (where N = 1 .. 8);
     *  - each piece placement is well-formed
     *  - no shape appears more than once in the placement
     *
     * @param placement A string describing a placement of one or more pieces
     * @return True if the placement is well-formed
     *
     * Written by Tingyu Pan (u6259941)
     */
    static boolean isPlacementWellFormed(String placement) {
        // FIXME Task 3: determine whether a placement is well-formed
        boolean flag = true;
        if (placement == null){
            flag = false;
        }else {
            int Pointer = placement.length() % 3;
            int N = placement.length() / 3;
            int parameter = 0;
            char[][] pieceArray;
            String[] pieceString;
            pieceArray = new char[N][3];
            pieceString = new String[N];
            if (Pointer == 0) {
                if (N >= 1 && N <= 8) {
                    for (int i = 0; i < N; i++) {
                        for (int j = 0; j < 3; j++) {
                            pieceArray[i][j] = placement.charAt(i * 3 + j);
                        }
                        for (int m = 0; m < N; m++) {
                            pieceString[m] = String.valueOf(pieceArray[m]);
                        }
                    }
                    while (parameter < N) {
                        if (isPiecePlacementWellFormed(pieceString[parameter]) == false) {
                            flag = false;
                        }
                        parameter++;
                    }
                    for (int k1 = 0;k1 < N;k1++){
                        for (int k2=0;k2<k1;k2++){
                            if (pieceString[k1].charAt(0) == pieceString[k2].charAt(0)){
                                flag = false;
                            }
                        }
                    }
                } else {
                    flag = false;
                }

            } else {
                flag = false;
            }
        }
        return flag;
    }

    /**
     * Determine whether a placement sequence is valid.  To be valid, the placement
     * sequence must be well-formed and each piece placement must be a valid placement
     * (with the pieces ordered according to the order in which they are played).
     *
     * @param placement A placement sequence string
     * @return True if the placement sequence is valid
     *
     * Written by Tingyu Pan (u6259941)
     */
    public static boolean isPlacementSequenceValid(String placement) {
        // FIXME Task 5: determine whether a placement sequence is valid
        boolean ValidMark = true;
        if (isPlacementWellFormed(placement) == true) {
            int boardCovered[][] = new int[9][14];
            for (int i = 2; i < 7; i++) {
                for (int j = 2; j < 13; j++) {
                    boardCovered[i][j] = 0;
                }
            }
            int N = placement.length() / 3;
            String[] pieceString;
            pieceString = new String[N];
            for (int m = 0; m < N; m++) {
                pieceString[m] = String.valueOf(placement.substring(m * 3, m * 3 + 3));
            }
            //Double up
            for (int k1 = 0; k1 < N; k1++) {
                for (int k2 = 0; k2 < k1; k2++) {
                    if (pieceString[k1].charAt(2) == pieceString[k2].charAt(2)) {
                        ValidMark = false;
                    }
                }
            }
            //ValidPiece
            for (int i = 0; i < N; i++) {
                if (pieceString[i].charAt(2) == 'A' || pieceString[i].charAt(2) == 'J'|| pieceString[i].charAt(2) == 'p'|| pieceString[i].charAt(2) == 'y'){
                    ValidMark = false;
                }
                if (pieceString[i].charAt(0) != 'B' && pieceString[i].charAt(0) != 'E') {
                    if ((pieceString[i].charAt(2) >= 'A' && pieceString[i].charAt(2) <= 'J') || (pieceString[i].charAt(2) >= 'p' && pieceString[i].charAt(2) <= 'y')) {
                        ValidMark = false;
                    } else if ((pieceString[i].charAt(2) == 'K') || (pieceString[i].charAt(2) == 'T') || (pieceString[i].charAt(2) == 'U') ||
                            (pieceString[i].charAt(2) == 'e') || (pieceString[i].charAt(2) == 'f') || (pieceString[i].charAt(2) == 'o')) {
                        ValidMark = false;
                    }
                } else if (pieceString[i].charAt(0) == 'B') {
                    if (pieceString[i].charAt(2) > 'A' && pieceString[i].charAt(2) < 'J') {
                        if (pieceString[i].charAt(1) != 'B' && pieceString[i].charAt(1) != 'H') {
                            ValidMark = false;
                        }
                    }
                    if (pieceString[i].charAt(2) > 'p' && pieceString[i].charAt(2) < 'y') {
                        if (pieceString[i].charAt(1) != 'D' && pieceString[i].charAt(1) != 'F') {
                            ValidMark = false;
                        }
                    }
                    if ((pieceString[i].charAt(2) == 'K') || (pieceString[i].charAt(2) == 'f') || (pieceString[i].charAt(2) == 'U')) {
                        if (pieceString[i].charAt(1) != 'A' && pieceString[i].charAt(1) != 'G') {
                            ValidMark = false;
                        }
                    }
                    if ((pieceString[i].charAt(2) == 'e') || (pieceString[i].charAt(2) == 'T') || (pieceString[i].charAt(2) == 'o')) {
                        if (pieceString[i].charAt(1) != 'C' && pieceString[i].charAt(1) != 'E') {
                            ValidMark = false;
                        }
                    }
                } else if (pieceString[i].charAt(0) == 'E') {
                    if (pieceString[i].charAt(2) > 'A' && pieceString[i].charAt(2) < 'J') {
                        if (pieceString[i].charAt(1) != 'D' && pieceString[i].charAt(1) != 'F') {
                            ValidMark = false;
                        }
                    }
                    if (pieceString[i].charAt(2) > 'p' && pieceString[i].charAt(2) < 'y') {
                        if (pieceString[i].charAt(1) != 'B' && pieceString[i].charAt(1) != 'H') {
                            ValidMark = false;
                        }
                    }
                    if ((pieceString[i].charAt(2) == 'K') || (pieceString[i].charAt(2) == 'f') || (pieceString[i].charAt(2) == 'U')) {
                        if (pieceString[i].charAt(1) != 'C' && pieceString[i].charAt(1) != 'E') {
                            ValidMark = false;
                        }
                    }
                    if ((pieceString[i].charAt(2) == 'e') || (pieceString[i].charAt(2) == 'T') || (pieceString[i].charAt(2) == 'o')) {
                        if (pieceString[i].charAt(1) != 'A' && pieceString[i].charAt(1) != 'G') {
                            ValidMark = false;
                        }
                    }
                }
            }
            //Bad
            for (int i = 0; i < N; i++) {
                if (pieceString[i].charAt(2) >= 'A' && pieceString[i].charAt(2) <= 'Y') {
                    int x = ((pieceString[i].charAt(2) - 65) / 10)+2;
                    int y = ((pieceString[i].charAt(2) - 65) % 10)+2;


                    switch (pieceString[i].charAt(0)) {
                        case 'A': {
                            switch (pieceString[i].charAt(1)) {
                                case 'A': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                                if (boardCovered[m][n] == 1){
                                                    if (!((m == x+1 && n == y+1)||(m == x-1 && n == y+1))){
                                                        ValidMark = false;
                                                    }
                                                }
                                                boardCovered[m][n] = 1;
                                            }
                                        }
                                    boardCovered[x+1][y] = 0;
                                    boardCovered[x][y-2] = 1;
                                    boardCovered[x-2][y] = 1;
                                    boardCovered[x][y+2] = 1;
                                    break;
                                }
                                case 'B': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!((m == x+1 && n == y-1)||(m == x+1 && n == y+1))){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x][y-1] = 0;
                                    boardCovered[x-2][y] = 1;
                                    boardCovered[x][y+2] = 1;
                                    boardCovered[x+2][y] = 1;
                                    break;
                                }
                                case 'C': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!((m == x-1 && n == y-1)||(m == x+1 && n == y-1))){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x-1][y] = 0;
                                    boardCovered[x][y-2] = 1;
                                    boardCovered[x][y+2] = 1;
                                    boardCovered[x+2][y] = 1;
                                    break;
                                }
                                case 'D': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!((m == x-1 && n == y-1)||(m == x-1 && n == y+1))){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x][y+1] = 0;
                                    boardCovered[x][y-2] = 1;
                                    boardCovered[x-2][y] = 1;
                                    boardCovered[x+2][y] = 1;
                                    break;
                                }
                                case 'E': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!(m == x+1 && n == y)){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x-1][y-1] = 0;
                                    boardCovered[x+1][y-1] = 0;
                                    boardCovered[x-2][y+1] = 1;
                                    boardCovered[x-1][y+2] = 1;
                                    boardCovered[x+1][y+2] = 1;
                                    boardCovered[x+2][y+1] = 1;
                                    break;
                                }
                                case 'F': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!(m == x && n == y-1)){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x-1][y-1] = 0;
                                    boardCovered[x-1][y+1] = 0;
                                    boardCovered[x+1][y-2] = 1;
                                    boardCovered[x+2][y-1] = 1;
                                    boardCovered[x+1][y+2] = 1;
                                    boardCovered[x+2][y+1] = 1;
                                    break;
                                }
                                case 'G': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!(m == x-1 && n == y)){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x-1][y+1] = 0;
                                    boardCovered[x+1][y+1] = 0;
                                    boardCovered[x-2][y-1] = 1;
                                    boardCovered[x-1][y-2] = 1;
                                    boardCovered[x+1][y-2] = 1;
                                    boardCovered[x+2][y-1] = 1;
                                    break;
                                }
                                case 'H': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!(m == x && n == y+1)){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x+1][y-1] = 0;
                                    boardCovered[x+1][y+1] = 0;
                                    boardCovered[x-2][y-1] = 1;
                                    boardCovered[x-1][y-2] = 1;
                                    boardCovered[x-2][y+1] = 1;
                                    boardCovered[x-1][y+2] = 1;
                                    /*for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            System.out.println(boardCovered[m][n]);
                                        }
                                    }*/
                                    break;
                                }
                            }
                            break;
                        }
                        case 'B': {
                            switch (pieceString[i].charAt(1)) {
                                case 'A': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!((m == x-1 && n == y-1)||(m == x-1 && n == y+1)||(m == x+1 && n == y-1))){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x][y-1] = 0;
                                    boardCovered[x-2][y] = 1;
                                    boardCovered[x][y+2] = 1;
                                    boardCovered[x+2][y] = 1;
                                    break;
                                }
                                case 'B': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!((m == x-1 && n == y-1)||(m == x-1 && n == y+1)||(m == x+1 && n == y+1))){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x-1][y] = 0;
                                    boardCovered[x][y-2] = 1;
                                    boardCovered[x][y+2] = 1;
                                    boardCovered[x+2][y] = 1;
                                    break;
                                }
                                case 'C': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!((m == x-1 && n == y+1)||(m == x+1 && n == y-1)||(m == x+1 && n == y+1))){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x][y+1] = 0;
                                    boardCovered[x][y-2] = 1;
                                    boardCovered[x-2][y] = 1;
                                    boardCovered[x+2][y] = 1;
                                    break;
                                }
                                case 'D': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!((m == x-1 && n == y-1)||(m == x+1 && n == y-1)||(m == x+1 && n == y+1))){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x+1][y] = 0;
                                    boardCovered[x][y-2] = 1;
                                    boardCovered[x-2][y] = 1;
                                    boardCovered[x][y+2] = 1;
                                    break;
                                }
                                case 'E': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!(m == x && n == y+1)){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x-1][y-1] = 0;
                                    boardCovered[x-1][y+1] = 0;
                                    boardCovered[x+1][y+1] = 0;
                                    boardCovered[x+1][y-2] = 1;
                                    boardCovered[x+2][y-1] = 1;
                                    break;
                                }
                                case 'F': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!(m == x+1 && n == y)){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x+1][y-1] = 0;
                                    boardCovered[x-1][y+1] = 0;
                                    boardCovered[x+1][y+1] = 0;
                                    boardCovered[x-2][y-1] = 1;
                                    boardCovered[x-1][y-2] = 1;
                                    break;
                                }
                                case 'G': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!(m == x && n == y-1)){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x-1][y-1] = 0;
                                    boardCovered[x+1][y-1] = 0;
                                    boardCovered[x+1][y+1] = 0;
                                    boardCovered[x-2][y+1] = 1;
                                    boardCovered[x-1][y+2] = 1;
                                    break;
                                }
                                case 'H': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!(m == x-1 && n == y)){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x+1][y-1] = 0;
                                    boardCovered[x-1][y+1] = 0;
                                    boardCovered[x-1][y-1] = 0;
                                    boardCovered[x+1][y+2] = 1;
                                    boardCovered[x+2][y+1] = 1;
                                    break;
                                }
                            }
                            break;
                        }
                        case 'D': {
                            switch (pieceString[i].charAt(1)) {
                                case 'A': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!((m == x-1 && n == y-1)||(m == x-1 && n == y+1)||(m == x+1 && n == y-1))){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x][y+1] = 0;
                                    boardCovered[x][y-2] = 1;
                                    boardCovered[x-2][y] = 1;
                                    boardCovered[x+2][y] = 1;
                                    break;
                                }
                                case 'B': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!((m == x-1 && n == y-1)||(m == x-1 && n == y+1)||(m == x+1 && n == y+1))){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x][y-1] = 0;
                                    boardCovered[x][y-2] = 1;
                                    boardCovered[x-2][y] = 1;
                                    boardCovered[x][y+2] = 1;
                                    break;
                                }
                                case 'C': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!((m == x-1 && n == y+1)||(m == x+1 && n == y-1)||(m == x+1 && n == y+1))){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x][y-1] = 0;
                                    boardCovered[x-2][y] = 1;
                                    boardCovered[x][y+2] = 1;
                                    boardCovered[x+2][y] = 1;
                                    break;
                                }
                                case 'D': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!((m == x-1 && n == y-1)||(m == x+1 && n == y-1)||(m == x+1 && n == y+1))){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x-1][y] = 0;
                                    boardCovered[x][y-2] = 1;
                                    boardCovered[x][y+2] = 1;
                                    boardCovered[x+2][y] = 1;
                                    break;
                                }
                                case 'E': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!(m == x && n == y-1)){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x-1][y-1] = 0;
                                    boardCovered[x-1][y+1] = 0;
                                    boardCovered[x+1][y+1] = 0;
                                    boardCovered[x+1][y-2] = 1;
                                    boardCovered[x+2][y-1] = 1;
                                    break;
                                }
                                case 'F': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!(m == x-1 && n == y)){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x+1][y-1] = 0;
                                    boardCovered[x-1][y+1] = 0;
                                    boardCovered[x+1][y+1] = 0;
                                    boardCovered[x-2][y-1] = 1;
                                    boardCovered[x-1][y-2] = 1;
                                    break;
                                }
                                case 'G': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!(m == x && n == y+1)){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x-1][y-1] = 0;
                                    boardCovered[x+1][y-1] = 0;
                                    boardCovered[x+1][y+1] = 0;
                                    boardCovered[x-2][y+1] = 1;
                                    boardCovered[x-1][y+2] = 1;
                                    break;
                                }
                                case 'H': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!(m == x+1 && n == y)){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x+1][y-1] = 0;
                                    boardCovered[x-1][y+1] = 0;
                                    boardCovered[x-1][y-1] = 0;
                                    boardCovered[x+1][y+2] = 1;
                                    boardCovered[x+2][y+1] = 1;
                                    break;
                                }
                            }
                            break;
                        }
                        case 'C': {
                            switch (pieceString[i].charAt(1)) {
                                case 'A': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!((m == x-1 && n == y-1)||(m == x-1 && n == y+1)||(m == x+1 && n == y+1))){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x][y-1] = 0;
                                    boardCovered[x-2][y] = 1;
                                    boardCovered[x][y+2] = 1;
                                    boardCovered[x+2][y] = 1;
                                    break;
                                }
                                case 'B': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!((m == x+1 && n == y-1)||(m == x-1 && n == y+1)||(m == x+1 && n == y+1))){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x-1][y] = 0;
                                    boardCovered[x][y-2] = 1;
                                    boardCovered[x][y+2] = 1;
                                    boardCovered[x+2][y] = 1;
                                    break;
                                }
                                case 'C': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!((m == x-1 && n == y-1)||(m == x+1 && n == y-1)||(m == x+1 && n == y+1))){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x][y+1] = 0;
                                    boardCovered[x][y-2] = 1;
                                    boardCovered[x-2][y] = 1;
                                    boardCovered[x+2][y] = 1;
                                    break;
                                }
                                case 'D': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!((m == x-1 && n == y-1)||(m == x+1 && n == y-1)||(m == x-1 && n == y+1))){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x+1][y] = 0;
                                    boardCovered[x][y-2] = 1;
                                    boardCovered[x-2][y] = 1;
                                    boardCovered[x][y+2] = 1;
                                    break;
                                }
                                case 'E': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!(m == x && n == y+1)){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x-1][y-1] = 0;
                                    boardCovered[x-1][y+1] = 0;
                                    boardCovered[x+1][y-1] = 0;
                                    boardCovered[x+1][y+2] = 1;
                                    boardCovered[x+2][y+1] = 1;
                                    break;
                                }
                                case 'F': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!(m == x+1 && n == y)){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x-1][y-1] = 0;
                                    boardCovered[x-1][y+1] = 0;
                                    boardCovered[x+1][y+1] = 0;
                                    boardCovered[x+1][y-2] = 1;
                                    boardCovered[x+2][y-1] = 1;
                                    break;
                                }
                                case 'G': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!(m == x && n == y-1)){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x-1][y+1] = 0;
                                    boardCovered[x+1][y-1] = 0;
                                    boardCovered[x+1][y+1] = 0;
                                    boardCovered[x-2][y-1] = 1;
                                    boardCovered[x-1][y-2] = 1;
                                    break;
                                }
                                case 'H': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!(m == x-1 && n == y)){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x+1][y-1] = 0;
                                    boardCovered[x-1][y-1] = 0;
                                    boardCovered[x+1][y+1] = 0;
                                    boardCovered[x-2][y+1] = 1;
                                    boardCovered[x-1][y+2] = 1;
                                    /*for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            System.out.println(boardCovered[m][n]);
                                        }
                                    }*/
                                    break;
                                }
                            }
                            break;
                        }
                        case 'E': {
                            switch (pieceString[i].charAt(1)) {
                                case 'A': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!((m == x-1 && n == y+1)||(m == x-1 && n == y-1)||(m == x+1 && n == y+1))){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x][y+1] = 0;
                                    boardCovered[x][y-2] = 1;
                                    boardCovered[x-2][y] = 1;
                                    boardCovered[x+2][y] = 1;
                                    break;
                                }
                                case 'B': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!((m == x+1 && n == y-1)||(m == x+1 && n == y+1)||(m == x-1 && n == y+1))){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x+1][y] = 0;
                                    boardCovered[x][y-2] = 1;
                                    boardCovered[x-2][y] = 1;
                                    boardCovered[x][y+2] = 1;
                                    break;
                                }
                                case 'C': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!((m == x-1 && n == y-1)||(m == x+1 && n == y+1)||(m == x+1 && n == y-1))){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x][y-1] = 0;
                                    boardCovered[x-2][y] = 1;
                                    boardCovered[x][y+2] = 1;
                                    boardCovered[x+2][y] = 1;
                                    break;
                                }
                                case 'D': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!((m == x+1 && n == y-1)||(m == x-1 && n == y-1)||(m == x-1 && n == y+1))){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x-1][y] = 0;
                                    boardCovered[x][y-2] = 1;
                                    boardCovered[x][y+2] = 1;
                                    boardCovered[x+2][y] = 1;
                                    break;
                                }
                                case 'E': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!(m == x && n == y-1)){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x-1][y+1] = 0;
                                    boardCovered[x-1][y-1] = 0;
                                    boardCovered[x+1][y-1] = 0;
                                    boardCovered[x+1][y+2] = 1;
                                    boardCovered[x+2][y+1] = 1;
                                    break;
                                }
                                case 'F': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!(m == x-1 && n == y)){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x-1][y-1] = 0;
                                    boardCovered[x+1][y+1] = 0;
                                    boardCovered[x-1][y+1] = 0;
                                    boardCovered[x+1][y-2] = 1;
                                    boardCovered[x+2][y-1] = 1;
                                    break;
                                }
                                case 'G': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!(m == x && n == y+1)){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x-1][y+1] = 0;
                                    boardCovered[x+1][y+1] = 0;
                                    boardCovered[x+1][y-1] = 0;
                                    boardCovered[x-1][y-2] = 1;
                                    boardCovered[x-2][y-1] = 1;
                                    break;
                                }
                                case 'H': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!(m == x+1 && n == y)){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x-1][y-1] = 0;
                                    boardCovered[x+1][y+1] = 0;
                                    boardCovered[x+1][y-1] = 0;
                                    boardCovered[x-2][y+1] = 1;
                                    boardCovered[x-1][y+2] = 1;
                                    break;
                                }
                            }
                            break;
                        }
                        case 'F': {
                            switch (pieceString[i].charAt(1)) {
                                case 'A': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!((m == x+1 && n == y+1)||(m == x-1 && n == y-1))){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x-1][y] = 0;
                                    boardCovered[x][y-1] = 0;
                                    boardCovered[x-1][y-1] = 0;
                                    boardCovered[x][y+2] = 1;
                                    boardCovered[x+2][y] = 1;
                                    break;
                                }
                                case 'B': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!((m == x+1 && n == y-1)||(m == x-1 && n == y+1))){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x-1][y] = 0;
                                    boardCovered[x][y+1] = 0;
                                    boardCovered[x-1][y+1] = 0;
                                    boardCovered[x][y-2] = 1;
                                    boardCovered[x+2][y] = 1;
                                    break;
                                }
                                case 'C': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!((m == x-1 && n == y-1)||(m == x+1 && n == y+1))){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x+1][y] = 0;
                                    boardCovered[x][y+1] = 0;
                                    boardCovered[x+1][y+1] = 0;
                                    boardCovered[x][y-2] = 1;
                                    boardCovered[x-2][y] = 1;
                                    break;
                                }
                                case 'D': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!((m == x+1 && n == y-1)||(m == x-1 && n == y+1))){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x][y-1] = 0;
                                    boardCovered[x+1][y] = 0;
                                    boardCovered[x+1][y-1] = 0;
                                    boardCovered[x-2][y] = 1;
                                    boardCovered[x][y+2] = 1;
                                    break;
                                }
                                case 'E': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!((m == x-1 && n == y)||(m == x && n == y+1)||(m == x-1 && n == y+1))){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x-1][y+1] = 0;
                                    boardCovered[x+1][y-1] = 0;
                                    boardCovered[x-2][y-1] = 1;
                                    boardCovered[x-1][y-2] = 1;
                                    boardCovered[x+2][y+1] = 1;
                                    boardCovered[x+1][y+2] = 1;
                                    break;
                                }
                                case 'F': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!((m == x && n == y+1)||(m == x+1 && n == y)||(m == x+1 && n == y+1))){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x-1][y-1] = 0;
                                    boardCovered[x+1][y+1] = 0;
                                    boardCovered[x+2][y-1] = 1;
                                    boardCovered[x+1][y-2] = 1;
                                    boardCovered[x-2][y+1] = 1;
                                    boardCovered[x-1][y+2] = 1;
                                    break;
                                }
                                case 'G': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!((m == x && n == y-1)||(m == x+1 && n == y)||(m == x+1 && n == y-1))){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x-1][y+1] = 0;
                                    boardCovered[x+1][y-1] = 0;
                                    boardCovered[x-2][y-1] = 1;
                                    boardCovered[x-1][y-2] = 1;
                                    boardCovered[x+2][y+1] = 1;
                                    boardCovered[x+1][y+2] = 1;
                                    break;
                                }
                                case 'H': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!((m == x-1 && n == y)||(m == x && n == y-1)||(m == x-1 && n == y-1))){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x-1][y-1] = 0;
                                    boardCovered[x+1][y+1] = 0;
                                    boardCovered[x+2][y-1] = 1;
                                    boardCovered[x+1][y-2] = 1;
                                    boardCovered[x-2][y+1] = 1;
                                    boardCovered[x-1][y+2] = 1;
                                    break;
                                }
                            }
                            break;
                        }
                        case 'G': {
                            switch (pieceString[i].charAt(1)) {
                                case 'A': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!((m == x+1 && n == y+1)||(m == x-1 && n == y-1))){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x][y-1] = 0;
                                    boardCovered[x-2][y] = 1;
                                    boardCovered[x][y+2] = 1;
                                    boardCovered[x+2][y] = 1;
                                    break;
                                }
                                case 'B': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!((m == x+1 && n == y-1)||(m == x-1 && n == y+1))){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x-1][y] = 0;
                                    boardCovered[x][y-2] = 1;
                                    boardCovered[x][y+2] = 1;
                                    boardCovered[x+2][y] = 1;
                                    break;
                                }
                                case 'C': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!((m == x-1 && n == y-1)||(m == x+1 && n == y+1))){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x][y+1] = 0;
                                    boardCovered[x][y-2] = 1;
                                    boardCovered[x-2][y] = 1;
                                    boardCovered[x+2][y] = 1;
                                    break;
                                }
                                case 'D': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!((m == x+1 && n == y-1)||(m == x-1 && n == y+1))){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x+1][y] = 0;
                                    boardCovered[x][y-2] = 1;
                                    boardCovered[x-2][y] = 1;
                                    boardCovered[x][y+2] = 1;
                                    break;
                                }
                                case 'E': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!(m == x && n == y+1)){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x-1][y+1] = 0;
                                    boardCovered[x+1][y-1] = 0;
                                    boardCovered[x-2][y-1] = 1;
                                    boardCovered[x-1][y-2] = 1;
                                    boardCovered[x+2][y+1] = 1;
                                    boardCovered[x+1][y+2] = 1;
                                    break;
                                }
                                case 'F': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!(m == x+1 && n == y)){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x-1][y-1] = 0;
                                    boardCovered[x+1][y+1] = 0;
                                    boardCovered[x+2][y-1] = 1;
                                    boardCovered[x+1][y-2] = 1;
                                    boardCovered[x-2][y+1] = 1;
                                    boardCovered[x-1][y+2] = 1;
                                    break;
                                }
                                case 'G': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!(m == x && n == y-1)){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x-1][y+1] = 0;
                                    boardCovered[x+1][y-1] = 0;
                                    boardCovered[x-2][y-1] = 1;
                                    boardCovered[x-1][y-2] = 1;
                                    boardCovered[x+2][y+1] = 1;
                                    boardCovered[x+1][y+2] = 1;
                                    break;
                                }
                                case 'H': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!(m == x-1 && n == y)){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x-1][y-1] = 0;
                                    boardCovered[x+1][y+1] = 0;
                                    boardCovered[x+2][y-1] = 1;
                                    boardCovered[x+1][y-2] = 1;
                                    boardCovered[x-2][y+1] = 1;
                                    boardCovered[x-1][y+2] = 1;
                                    break;
                                }
                            }
                            break;
                        }
                        case 'H': {
                            switch (pieceString[i].charAt(1)) {
                                case 'A': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!((m == x-1 && n == y-1)||(m == x+1 && n == y-1))){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x][y+1] = 0;
                                    boardCovered[x][y-2] = 1;
                                    boardCovered[x-2][y] = 1;
                                    boardCovered[x+2][y] = 1;
                                    break;
                                }
                                case 'B': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!((m == x-1 && n == y-1)||(m == x-1 && n == y+1))){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x+1][y] = 0;
                                    boardCovered[x][y-2] = 1;
                                    boardCovered[x-2][y] = 1;
                                    boardCovered[x][y+2] = 1;
                                    break;
                                }
                                case 'C': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!((m == x-1 && n == y+1)||(m == x+1 && n == y+1))){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x][y-1] = 0;
                                    boardCovered[x-2][y] = 1;
                                    boardCovered[x][y+2] = 1;
                                    boardCovered[x+2][y] = 1;
                                    break;
                                }
                                case 'D': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!((m == x+1 && n == y-1)||(m == x+1 && n == y+1))){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x-1][y] = 0;
                                    boardCovered[x][y-2] = 1;
                                    boardCovered[x][y+2] = 1;
                                    boardCovered[x+2][y] = 1;
                                    break;
                                }
                                case 'E': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!(m == x && n == y-1)){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x-1][y+1] = 0;
                                    boardCovered[x+1][y+1] = 0;
                                    boardCovered[x-2][y-1] = 1;
                                    boardCovered[x-1][y-2] = 1;
                                    boardCovered[x+1][y-2] = 1;
                                    boardCovered[x+2][y-1] = 1;
                                    break;
                                }
                                case 'F': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!(m == x-1 && n == y)){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x+1][y-1] = 0;
                                    boardCovered[x+1][y+1] = 0;
                                    boardCovered[x-2][y-1] = 1;
                                    boardCovered[x-1][y-2] = 1;
                                    boardCovered[x-2][y+1] = 1;
                                    boardCovered[x-1][y+2] = 1;
                                    break;
                                }
                                case 'G': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!(m == x && n == y+1)){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x-1][y-1] = 0;
                                    boardCovered[x+1][y-1] = 0;
                                    boardCovered[x-2][y+1] = 1;
                                    boardCovered[x-1][y+2] = 1;
                                    boardCovered[x+1][y+2] = 1;
                                    boardCovered[x+2][y+1] = 1;
                                    break;
                                }
                                case 'H': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1){
                                                if (!(m == x+1 && n == y)){
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x-1][y+1] = 0;
                                    boardCovered[x-1][y-1] = 0;
                                    boardCovered[x+2][y+1] = 1;
                                    boardCovered[x+1][y+2] = 1;
                                    boardCovered[x+1][y-2] = 1;
                                    boardCovered[x+2][y-1] = 1;
                                    break;
                                }
                            }
                            break;
                        }

                    }
                } else if (pieceString[i].charAt(2) >= 'a' && pieceString[i].charAt(2) <= 'y') {
                    int x = (4 - (((154 - (pieceString[i].charAt(2) - 32)) - 65) / 10)) + 2;
                    int y = (9 - (((154 - (pieceString[i].charAt(2) - 32)) - 65) % 10)) + 2;

                    switch (pieceString[i].charAt(0)) {
                        case 'A': {
                            switch (pieceString[i].charAt(1)) {
                                case 'A': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!((m == x + 1 && n == y + 1) || (m == x - 1 && n == y + 1))) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x + 1][y] = 0;
                                    boardCovered[x][y - 2] = 1;
                                    boardCovered[x - 2][y] = 1;
                                    boardCovered[x][y + 2] = 1;
                                    break;
                                }
                                case 'B': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!((m == x + 1 && n == y - 1) || (m == x + 1 && n == y + 1))) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x][y - 1] = 0;
                                    boardCovered[x - 2][y] = 1;
                                    boardCovered[x][y + 2] = 1;
                                    boardCovered[x + 2][y] = 1;
                                    break;
                                }
                                case 'C': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!((m == x - 1 && n == y - 1) || (m == x + 1 && n == y - 1))) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x - 1][y] = 0;
                                    boardCovered[x][y - 2] = 1;
                                    boardCovered[x][y + 2] = 1;
                                    boardCovered[x + 2][y] = 1;
                                    break;
                                }
                                case 'D': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!((m == x - 1 && n == y - 1) || (m == x - 1 && n == y + 1))) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x][y + 1] = 0;
                                    boardCovered[x][y - 2] = 1;
                                    boardCovered[x - 2][y] = 1;
                                    boardCovered[x + 2][y] = 1;
                                    break;
                                }
                                case 'E': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!(m == x + 1 && n == y)) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x - 1][y - 1] = 0;
                                    boardCovered[x + 1][y - 1] = 0;
                                    boardCovered[x - 2][y + 1] = 1;
                                    boardCovered[x - 1][y + 2] = 1;
                                    boardCovered[x + 1][y + 2] = 1;
                                    boardCovered[x + 2][y + 1] = 1;
                                    break;
                                }
                                case 'F': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!(m == x && n == y - 1)) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x - 1][y - 1] = 0;
                                    boardCovered[x - 1][y + 1] = 0;
                                    boardCovered[x + 1][y - 2] = 1;
                                    boardCovered[x + 2][y - 1] = 1;
                                    boardCovered[x + 1][y + 2] = 1;
                                    boardCovered[x + 2][y + 1] = 1;
                                    break;
                                }
                                case 'G': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!(m == x - 1 && n == y)) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x - 1][y + 1] = 0;
                                    boardCovered[x + 1][y + 1] = 0;
                                    boardCovered[x - 2][y - 1] = 1;
                                    boardCovered[x - 1][y - 2] = 1;
                                    boardCovered[x + 1][y - 2] = 1;
                                    boardCovered[x + 2][y - 1] = 1;
                                    break;
                                }
                                case 'H': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!(m == x && n == y + 1)) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x + 1][y - 1] = 0;
                                    boardCovered[x + 1][y + 1] = 0;
                                    boardCovered[x - 2][y - 1] = 1;
                                    boardCovered[x - 1][y - 2] = 1;
                                    boardCovered[x - 2][y + 1] = 1;
                                    boardCovered[x - 1][y + 2] = 1;
                                    /*for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            System.out.println(boardCovered[m][n]);
                                        }
                                    }*/
                                    break;
                                }
                            }
                            break;
                        }
                        case 'B': {
                            switch (pieceString[i].charAt(1)) {
                                case 'A': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!((m == x - 1 && n == y - 1) || (m == x - 1 && n == y + 1) || (m == x + 1 && n == y - 1))) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x][y - 1] = 0;
                                    boardCovered[x - 2][y] = 1;
                                    boardCovered[x][y + 2] = 1;
                                    boardCovered[x + 2][y] = 1;
                                    break;
                                }
                                case 'B': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!((m == x - 1 && n == y - 1) || (m == x - 1 && n == y + 1) || (m == x + 1 && n == y + 1))) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x - 1][y] = 0;
                                    boardCovered[x][y - 2] = 1;
                                    boardCovered[x][y + 2] = 1;
                                    boardCovered[x + 2][y] = 1;
                                    break;
                                }
                                case 'C': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!((m == x - 1 && n == y + 1) || (m == x + 1 && n == y - 1) || (m == x + 1 && n == y + 1))) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x][y + 1] = 0;
                                    boardCovered[x][y - 2] = 1;
                                    boardCovered[x - 2][y] = 1;
                                    boardCovered[x + 2][y] = 1;
                                    break;
                                }
                                case 'D': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!((m == x - 1 && n == y - 1) || (m == x + 1 && n == y - 1) || (m == x + 1 && n == y + 1))) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x + 1][y] = 0;
                                    boardCovered[x][y - 2] = 1;
                                    boardCovered[x - 2][y] = 1;
                                    boardCovered[x][y + 2] = 1;
                                    break;
                                }
                                case 'E': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!(m == x && n == y + 1)) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x - 1][y - 1] = 0;
                                    boardCovered[x - 1][y + 1] = 0;
                                    boardCovered[x + 1][y + 1] = 0;
                                    boardCovered[x + 1][y - 2] = 1;
                                    boardCovered[x + 2][y - 1] = 1;
                                    break;
                                }
                                case 'F': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!(m == x + 1 && n == y)) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x + 1][y - 1] = 0;
                                    boardCovered[x - 1][y + 1] = 0;
                                    boardCovered[x + 1][y + 1] = 0;
                                    boardCovered[x - 2][y - 1] = 1;
                                    boardCovered[x - 1][y - 2] = 1;
                                    break;
                                }
                                case 'G': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!(m == x && n == y - 1)) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x - 1][y - 1] = 0;
                                    boardCovered[x + 1][y - 1] = 0;
                                    boardCovered[x + 1][y + 1] = 0;
                                    boardCovered[x - 2][y + 1] = 1;
                                    boardCovered[x - 1][y + 2] = 1;
                                    break;
                                }
                                case 'H': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!(m == x - 1 && n == y)) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x + 1][y - 1] = 0;
                                    boardCovered[x - 1][y + 1] = 0;
                                    boardCovered[x - 1][y - 1] = 0;
                                    boardCovered[x + 1][y + 2] = 1;
                                    boardCovered[x + 2][y + 1] = 1;
                                    break;
                                }
                            }
                            break;
                        }
                        case 'D': {
                            switch (pieceString[i].charAt(1)) {
                                case 'A': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!((m == x - 1 && n == y - 1) || (m == x - 1 && n == y + 1) || (m == x + 1 && n == y - 1))) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x][y + 1] = 0;
                                    boardCovered[x][y - 2] = 1;
                                    boardCovered[x - 2][y] = 1;
                                    boardCovered[x + 2][y] = 1;
                                    break;
                                }
                                case 'B': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!((m == x - 1 && n == y - 1) || (m == x - 1 && n == y + 1) || (m == x + 1 && n == y + 1))) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x][y - 1] = 0;
                                    boardCovered[x][y - 2] = 1;
                                    boardCovered[x - 2][y] = 1;
                                    boardCovered[x][y + 2] = 1;
                                    break;
                                }
                                case 'C': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!((m == x - 1 && n == y + 1) || (m == x + 1 && n == y - 1) || (m == x + 1 && n == y + 1))) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x][y - 1] = 0;
                                    boardCovered[x - 2][y] = 1;
                                    boardCovered[x][y + 2] = 1;
                                    boardCovered[x + 2][y] = 1;
                                    break;
                                }
                                case 'D': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!((m == x - 1 && n == y - 1) || (m == x + 1 && n == y - 1) || (m == x + 1 && n == y + 1))) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x - 1][y] = 0;
                                    boardCovered[x][y - 2] = 1;
                                    boardCovered[x][y + 2] = 1;
                                    boardCovered[x + 2][y] = 1;
                                    break;
                                }
                                case 'E': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!(m == x && n == y - 1)) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x - 1][y - 1] = 0;
                                    boardCovered[x - 1][y + 1] = 0;
                                    boardCovered[x + 1][y + 1] = 0;
                                    boardCovered[x + 1][y - 2] = 1;
                                    boardCovered[x + 2][y - 1] = 1;
                                    break;
                                }
                                case 'F': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!(m == x - 1 && n == y)) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x + 1][y - 1] = 0;
                                    boardCovered[x - 1][y + 1] = 0;
                                    boardCovered[x + 1][y + 1] = 0;
                                    boardCovered[x - 2][y - 1] = 1;
                                    boardCovered[x - 1][y - 2] = 1;
                                    break;
                                }
                                case 'G': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!(m == x && n == y + 1)) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x - 1][y - 1] = 0;
                                    boardCovered[x + 1][y - 1] = 0;
                                    boardCovered[x + 1][y + 1] = 0;
                                    boardCovered[x - 2][y + 1] = 1;
                                    boardCovered[x - 1][y + 2] = 1;
                                    break;
                                }
                                case 'H': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!(m == x + 1 && n == y)) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x + 1][y - 1] = 0;
                                    boardCovered[x - 1][y + 1] = 0;
                                    boardCovered[x - 1][y - 1] = 0;
                                    boardCovered[x + 1][y + 2] = 1;
                                    boardCovered[x + 2][y + 1] = 1;
                                    break;
                                }
                            }
                            break;
                        }
                        case 'C': {
                            switch (pieceString[i].charAt(1)) {
                                case 'A': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!((m == x - 1 && n == y - 1) || (m == x - 1 && n == y + 1) || (m == x + 1 && n == y + 1))) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x][y - 1] = 0;
                                    boardCovered[x - 2][y] = 1;
                                    boardCovered[x][y + 2] = 1;
                                    boardCovered[x + 2][y] = 1;
                                    break;
                                }
                                case 'B': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!((m == x + 1 && n == y - 1) || (m == x - 1 && n == y + 1) || (m == x + 1 && n == y + 1))) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x - 1][y] = 0;
                                    boardCovered[x][y - 2] = 1;
                                    boardCovered[x][y + 2] = 1;
                                    boardCovered[x + 2][y] = 1;
                                    break;
                                }
                                case 'C': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!((m == x - 1 && n == y - 1) || (m == x + 1 && n == y - 1) || (m == x + 1 && n == y + 1))) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x][y + 1] = 0;
                                    boardCovered[x][y - 2] = 1;
                                    boardCovered[x - 2][y] = 1;
                                    boardCovered[x + 2][y] = 1;
                                    break;
                                }
                                case 'D': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!((m == x - 1 && n == y - 1) || (m == x + 1 && n == y - 1) || (m == x - 1 && n == y + 1))) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x + 1][y] = 0;
                                    boardCovered[x][y - 2] = 1;
                                    boardCovered[x - 2][y] = 1;
                                    boardCovered[x][y + 2] = 1;
                                    break;
                                }
                                case 'E': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!(m == x && n == y + 1)) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x - 1][y - 1] = 0;
                                    boardCovered[x - 1][y + 1] = 0;
                                    boardCovered[x + 1][y - 1] = 0;
                                    boardCovered[x + 1][y + 2] = 1;
                                    boardCovered[x + 2][y + 1] = 1;
                                    break;
                                }
                                case 'F': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!(m == x + 1 && n == y)) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x - 1][y - 1] = 0;
                                    boardCovered[x - 1][y + 1] = 0;
                                    boardCovered[x + 1][y + 1] = 0;
                                    boardCovered[x + 1][y - 2] = 1;
                                    boardCovered[x + 2][y - 1] = 1;
                                    break;
                                }
                                case 'G': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!(m == x && n == y - 1)) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x - 1][y + 1] = 0;
                                    boardCovered[x + 1][y - 1] = 0;
                                    boardCovered[x + 1][y + 1] = 0;
                                    boardCovered[x - 2][y - 1] = 1;
                                    boardCovered[x - 1][y - 2] = 1;
                                    break;
                                }
                                case 'H': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!(m == x - 1 && n == y)) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x + 1][y - 1] = 0;
                                    boardCovered[x - 1][y - 1] = 0;
                                    boardCovered[x + 1][y + 1] = 0;
                                    boardCovered[x - 2][y + 1] = 1;
                                    boardCovered[x - 1][y + 2] = 1;
                                    /*for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            System.out.println(boardCovered[m][n]);
                                        }
                                    }*/
                                    break;
                                }
                            }
                            break;
                        }
                        case 'E': {
                            switch (pieceString[i].charAt(1)) {
                                case 'A': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!((m == x - 1 && n == y + 1) || (m == x - 1 && n == y - 1) || (m == x + 1 && n == y + 1))) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x][y + 1] = 0;
                                    boardCovered[x][y - 2] = 1;
                                    boardCovered[x - 2][y] = 1;
                                    boardCovered[x + 2][y] = 1;
                                    break;
                                }
                                case 'B': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!((m == x + 1 && n == y - 1) || (m == x + 1 && n == y + 1) || (m == x - 1 && n == y + 1))) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x + 1][y] = 0;
                                    boardCovered[x][y - 2] = 1;
                                    boardCovered[x - 2][y] = 1;
                                    boardCovered[x][y + 2] = 1;
                                    break;
                                }
                                case 'C': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!((m == x - 1 && n == y - 1) || (m == x + 1 && n == y + 1) || (m == x + 1 && n == y - 1))) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x][y - 1] = 0;
                                    boardCovered[x - 2][y] = 1;
                                    boardCovered[x][y + 2] = 1;
                                    boardCovered[x + 2][y] = 1;
                                    break;
                                }
                                case 'D': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!((m == x + 1 && n == y - 1) || (m == x - 1 && n == y - 1) || (m == x - 1 && n == y + 1))) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x - 1][y] = 0;
                                    boardCovered[x][y - 2] = 1;
                                    boardCovered[x][y + 2] = 1;
                                    boardCovered[x + 2][y] = 1;
                                    break;
                                }
                                case 'E': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!(m == x && n == y - 1)) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x - 1][y + 1] = 0;
                                    boardCovered[x - 1][y - 1] = 0;
                                    boardCovered[x + 1][y - 1] = 0;
                                    boardCovered[x + 1][y + 2] = 1;
                                    boardCovered[x + 2][y + 1] = 1;
                                    break;
                                }
                                case 'F': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!(m == x - 1 && n == y)) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x - 1][y - 1] = 0;
                                    boardCovered[x + 1][y + 1] = 0;
                                    boardCovered[x - 1][y + 1] = 0;
                                    boardCovered[x + 1][y - 2] = 1;
                                    boardCovered[x + 2][y - 1] = 1;
                                    break;
                                }
                                case 'G': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!(m == x && n == y + 1)) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x - 1][y + 1] = 0;
                                    boardCovered[x + 1][y + 1] = 0;
                                    boardCovered[x + 1][y - 1] = 0;
                                    boardCovered[x - 1][y - 2] = 1;
                                    boardCovered[x - 2][y - 1] = 1;
                                    break;
                                }
                                case 'H': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!(m == x + 1 && n == y)) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x - 1][y - 1] = 0;
                                    boardCovered[x + 1][y + 1] = 0;
                                    boardCovered[x + 1][y - 1] = 0;
                                    boardCovered[x - 2][y + 1] = 1;
                                    boardCovered[x - 1][y + 2] = 1;
                                    break;
                                }
                            }
                            break;
                        }
                        case 'F': {
                            switch (pieceString[i].charAt(1)) {
                                case 'A': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!((m == x + 1 && n == y + 1) || (m == x - 1 && n == y - 1))) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x - 1][y] = 0;
                                    boardCovered[x][y - 1] = 0;
                                    boardCovered[x - 1][y - 1] = 0;
                                    boardCovered[x][y + 2] = 1;
                                    boardCovered[x + 2][y] = 1;
                                    break;
                                }
                                case 'B': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!((m == x + 1 && n == y - 1) || (m == x - 1 && n == y + 1))) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x - 1][y] = 0;
                                    boardCovered[x][y + 1] = 0;
                                    boardCovered[x - 1][y + 1] = 0;
                                    boardCovered[x][y - 2] = 1;
                                    boardCovered[x + 2][y] = 1;
                                    break;
                                }
                                case 'C': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!((m == x - 1 && n == y - 1) || (m == x + 1 && n == y + 1))) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x + 1][y] = 0;
                                    boardCovered[x][y + 1] = 0;
                                    boardCovered[x + 1][y + 1] = 0;
                                    boardCovered[x][y - 2] = 1;
                                    boardCovered[x - 2][y] = 1;
                                    break;
                                }
                                case 'D': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!((m == x + 1 && n == y - 1) || (m == x - 1 && n == y + 1))) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x][y - 1] = 0;
                                    boardCovered[x + 1][y] = 0;
                                    boardCovered[x + 1][y - 1] = 0;
                                    boardCovered[x - 2][y] = 1;
                                    boardCovered[x][y + 2] = 1;
                                    break;
                                }
                                case 'E': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!((m == x - 1 && n == y) || (m == x && n == y + 1) || (m == x - 1 && n == y + 1))) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x - 1][y + 1] = 0;
                                    boardCovered[x + 1][y - 1] = 0;
                                    boardCovered[x - 2][y - 1] = 1;
                                    boardCovered[x - 1][y - 2] = 1;
                                    boardCovered[x + 2][y + 1] = 1;
                                    boardCovered[x + 1][y + 2] = 1;
                                    break;
                                }
                                case 'F': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!((m == x && n == y + 1) || (m == x + 1 && n == y) || (m == x + 1 && n == y + 1))) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x - 1][y - 1] = 0;
                                    boardCovered[x + 1][y + 1] = 0;
                                    boardCovered[x + 2][y - 1] = 1;
                                    boardCovered[x + 1][y - 2] = 1;
                                    boardCovered[x - 2][y + 1] = 1;
                                    boardCovered[x - 1][y + 2] = 1;
                                    break;
                                }
                                case 'G': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!((m == x && n == y - 1) || (m == x + 1 && n == y) || (m == x + 1 && n == y - 1))) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x - 1][y + 1] = 0;
                                    boardCovered[x + 1][y - 1] = 0;
                                    boardCovered[x - 2][y - 1] = 1;
                                    boardCovered[x - 1][y - 2] = 1;
                                    boardCovered[x + 2][y + 1] = 1;
                                    boardCovered[x + 1][y + 2] = 1;
                                    break;
                                }
                                case 'H': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!((m == x - 1 && n == y) || (m == x && n == y - 1) || (m == x - 1 && n == y - 1))) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x - 1][y - 1] = 0;
                                    boardCovered[x + 1][y + 1] = 0;
                                    boardCovered[x + 2][y - 1] = 1;
                                    boardCovered[x + 1][y - 2] = 1;
                                    boardCovered[x - 2][y + 1] = 1;
                                    boardCovered[x - 1][y + 2] = 1;
                                    break;
                                }
                            }
                            break;
                        }
                        case 'G': {
                            switch (pieceString[i].charAt(1)) {
                                case 'A': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!((m == x + 1 && n == y + 1) || (m == x - 1 && n == y - 1))) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x][y - 1] = 0;
                                    boardCovered[x - 2][y] = 1;
                                    boardCovered[x][y + 2] = 1;
                                    boardCovered[x + 2][y] = 1;
                                    break;
                                }
                                case 'B': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!((m == x + 1 && n == y - 1) || (m == x - 1 && n == y + 1))) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x - 1][y] = 0;
                                    boardCovered[x][y - 2] = 1;
                                    boardCovered[x][y + 2] = 1;
                                    boardCovered[x + 2][y] = 1;
                                    break;
                                }
                                case 'C': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!((m == x - 1 && n == y - 1) || (m == x + 1 && n == y + 1))) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x][y + 1] = 0;
                                    boardCovered[x][y - 2] = 1;
                                    boardCovered[x - 2][y] = 1;
                                    boardCovered[x + 2][y] = 1;
                                    break;
                                }
                                case 'D': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!((m == x + 1 && n == y - 1) || (m == x - 1 && n == y + 1))) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x + 1][y] = 0;
                                    boardCovered[x][y - 2] = 1;
                                    boardCovered[x - 2][y] = 1;
                                    boardCovered[x][y + 2] = 1;
                                    break;
                                }
                                case 'E': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!(m == x && n == y + 1)) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x - 1][y + 1] = 0;
                                    boardCovered[x + 1][y - 1] = 0;
                                    boardCovered[x - 2][y - 1] = 1;
                                    boardCovered[x - 1][y - 2] = 1;
                                    boardCovered[x + 2][y + 1] = 1;
                                    boardCovered[x + 1][y + 2] = 1;
                                    break;
                                }
                                case 'F': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!(m == x + 1 && n == y)) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x - 1][y - 1] = 0;
                                    boardCovered[x + 1][y + 1] = 0;
                                    boardCovered[x + 2][y - 1] = 1;
                                    boardCovered[x + 1][y - 2] = 1;
                                    boardCovered[x - 2][y + 1] = 1;
                                    boardCovered[x - 1][y + 2] = 1;
                                    break;
                                }
                                case 'G': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!(m == x && n == y - 1)) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x - 1][y + 1] = 0;
                                    boardCovered[x + 1][y - 1] = 0;
                                    boardCovered[x - 2][y - 1] = 1;
                                    boardCovered[x - 1][y - 2] = 1;
                                    boardCovered[x + 2][y + 1] = 1;
                                    boardCovered[x + 1][y + 2] = 1;
                                    break;
                                }
                                case 'H': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!(m == x - 1 && n == y)) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x - 1][y - 1] = 0;
                                    boardCovered[x + 1][y + 1] = 0;
                                    boardCovered[x + 2][y - 1] = 1;
                                    boardCovered[x + 1][y - 2] = 1;
                                    boardCovered[x - 2][y + 1] = 1;
                                    boardCovered[x - 1][y + 2] = 1;
                                    break;
                                }
                            }
                            break;
                        }
                        case 'H': {
                            switch (pieceString[i].charAt(1)) {
                                case 'A': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!((m == x - 1 && n == y - 1) || (m == x + 1 && n == y - 1))) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x][y + 1] = 0;
                                    boardCovered[x][y - 2] = 1;
                                    boardCovered[x - 2][y] = 1;
                                    boardCovered[x + 2][y] = 1;
                                    break;
                                }
                                case 'B': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!((m == x - 1 && n == y - 1) || (m == x - 1 && n == y + 1))) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x + 1][y] = 0;
                                    boardCovered[x][y - 2] = 1;
                                    boardCovered[x - 2][y] = 1;
                                    boardCovered[x][y + 2] = 1;
                                    break;
                                }
                                case 'C': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!((m == x - 1 && n == y + 1) || (m == x + 1 && n == y + 1))) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x][y - 1] = 0;
                                    boardCovered[x - 2][y] = 1;
                                    boardCovered[x][y + 2] = 1;
                                    boardCovered[x + 2][y] = 1;
                                    break;
                                }
                                case 'D': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!((m == x + 1 && n == y - 1) || (m == x + 1 && n == y + 1))) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x - 1][y] = 0;
                                    boardCovered[x][y - 2] = 1;
                                    boardCovered[x][y + 2] = 1;
                                    boardCovered[x + 2][y] = 1;
                                    break;
                                }
                                case 'E': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!(m == x && n == y - 1)) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x - 1][y + 1] = 0;
                                    boardCovered[x + 1][y + 1] = 0;
                                    boardCovered[x - 2][y - 1] = 1;
                                    boardCovered[x - 1][y - 2] = 1;
                                    boardCovered[x + 1][y - 2] = 1;
                                    boardCovered[x + 2][y - 1] = 1;
                                    break;
                                }
                                case 'F': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!(m == x - 1 && n == y)) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x + 1][y - 1] = 0;
                                    boardCovered[x + 1][y + 1] = 0;
                                    boardCovered[x - 2][y - 1] = 1;
                                    boardCovered[x - 1][y - 2] = 1;
                                    boardCovered[x - 2][y + 1] = 1;
                                    boardCovered[x - 1][y + 2] = 1;
                                    break;
                                }
                                case 'G': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!(m == x && n == y + 1)) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x - 1][y - 1] = 0;
                                    boardCovered[x + 1][y - 1] = 0;
                                    boardCovered[x - 2][y + 1] = 1;
                                    boardCovered[x - 1][y + 2] = 1;
                                    boardCovered[x + 1][y + 2] = 1;
                                    boardCovered[x + 2][y + 1] = 1;
                                    break;
                                }
                                case 'H': {
                                    for (int m = x - 1; m <= x + 1; m++) {
                                        for (int n = y - 1; n <= y + 1; n++) {
                                            if (boardCovered[m][n] == 1) {
                                                if (!(m == x + 1 && n == y)) {
                                                    ValidMark = false;
                                                }
                                            }
                                            boardCovered[m][n] = 1;
                                        }
                                    }
                                    boardCovered[x - 1][y + 1] = 0;
                                    boardCovered[x - 1][y - 1] = 0;
                                    boardCovered[x + 2][y + 1] = 1;
                                    boardCovered[x + 1][y + 2] = 1;
                                    boardCovered[x + 1][y - 2] = 1;
                                    boardCovered[x + 2][y - 1] = 1;
                                    break;
                                }
                            }
                            break;
                        }
                    }
                }
            }
        }
        else{
            ValidMark = false;
        }
        return ValidMark;
    }

    /*Converts a string into an array of string with each member being a 3 character string representing a piece
    * Concept by Tingyu Pan (u6259941), written by Ziwei Liu (u6380075)
    * */
    public static String[] makePieceArray (String placement) {
        int placeLength = placement.length() / 3;
        String[] pieceArray = new String[placeLength];
        for (int i = 0; i < placeLength; i++) {
            pieceArray[i] = String.valueOf(placement.substring(i * 3, i * 3 + 3));
        }
        return pieceArray;
    }

    /**
     * Given a string describing a placement of pieces and a string describing
     * an (unordered) objective, return a set of all possible next viable
     * piece placements.   A viable piece placement must be a piece that is
     * not already placed (ie not in the placement string), and which will not
     * obstruct any other unplaced piece.
     *
     * @param placement A valid sequence of piece placements where each piece placement is drawn from the objective
     * @param objective A valid game objective, but not necessarily a valid placement string
     * @return An set of viable piece placements
     *
     * Written by Ziwei Liu (u6380075)
     */
    public static Set<String> getViablePiecePlacements(String placement, String objective) {
        Set<String> pieceSet = new HashSet<String>();
        Set<String> objSet = new HashSet<>(symDif (placement, objective));
        String[] unplacedPieces = objSet.toArray(new String[objSet.size()]);

        for (String piece : unplacedPieces) {
            Set<String> next = new HashSet<>(objSet);
            String places = placement;
            places = places + piece;
            if (StepsGame.isPlacementSequenceValid(places)) {
                next.remove(piece);
                if (StepsGame.willPieceComplete(places, next)) {
                    pieceSet.add(piece);
                }
            }
        }
        // FIXME Task 6: determine the correct order of piece placements
        return pieceSet;
    }

    /*Detects whether the placement will remain valid with the addition of each of the pieces in the set
    *
    *Written by Ziwei Liu (u6380075)
    */
    static boolean willPieceComplete (String placement, Set<String> unplaced) {
        for (String s : unplaced) {
            if (!StepsGame.isPlacementSequenceValid(placement + s )){
                return false;
            }
        }
        return true;
    }

    /*Takes all pieces from the first string out of the second string
    * Written by Ziwei Liu (u6380075)
    */
    static Set<String> symDif (String placement, String objective) {
        String[] objArray = StepsGame.makePieceArray(objective);
        String[] placeArray = StepsGame.makePieceArray(placement);
        Set<String> objSet = new HashSet<>(Arrays.asList(objArray));
        Set<String> placeSet = new HashSet<>(Arrays.asList(placeArray));

        for (String str : placeSet) {
            if (objSet.contains(str)) {
                objSet.remove(str);
            }
        }
        return objSet;
    }


    /**
     * Return an array of all solutions to the game, given a starting placement.
     *
     * @param placement  A valid piece placement string.
     * @return An array of strings, each describing a solution to the game given the
     * starting point provided by placement.
     *
     * Written by Tingyu Pan (u6259941)
     */

    static ArrayList<String> Solutions = new ArrayList<String>();

    static Set<String> solutionpiece(String placement){
        Set<String> piece = new HashSet<>();
        Set<Character> Shape = new HashSet<>();
        Set<Character> Rotation = new HashSet<>();
        Set<Character> UpperpositionSet = new HashSet<>();
        Set<Character> LowerpositionSet = new HashSet<>();
        Set<Character> UpperpositionSet2 = new HashSet<>();
        Set<Character> LowerpositionSet2 = new HashSet<>();

        for (char shape = 'A'; shape <= 'H';shape ++){
            Shape.add(shape);
            Rotation.add(shape);
        }
        for (char position = 'B' ; position <= 'J'; position += 2){
            UpperpositionSet.add(position);
        }
        for (char position = 'K' ; position <= 'S'; position += 2){
            UpperpositionSet.add(position);
        }
        UpperpositionSet.add('V');UpperpositionSet.add('X');
        for (char position = 'a' ; position <= 'e'; position += 2){
            UpperpositionSet.add(position);
        }
        for (char position = 'f' ; position <= 'n'; position += 2){
            UpperpositionSet.add(position);
        }
        for (char position = 'q' ; position <= 'y'; position += 2){
            UpperpositionSet.add(position);
        }
        for (char position = 'A' ; position <= 'I'; position += 2){
            LowerpositionSet.add(position);
        }
        for (char position = 'L' ; position <= 'T'; position += 2){
            LowerpositionSet.add(position);
        }
        for (char position = 'U' ; position <= 'Y'; position += 2){
            LowerpositionSet.add(position);
        }
        for (char position = 'g' ; position <= 'o'; position += 2){
            LowerpositionSet.add(position);
        }
        for (char position = 'p' ; position <= 'x'; position += 2){
            LowerpositionSet.add(position);
        }
        LowerpositionSet.add('b');LowerpositionSet.add('d');
        for (char position = 'B' ; position <= 'J'; position += 2){
            UpperpositionSet2.add(position);
        }
        for (char position = 'K' ; position <= 'S'; position += 2){
            UpperpositionSet2.add(position);
        }
        UpperpositionSet2.add('V');UpperpositionSet2.add('X');
        for (char position = 'a' ; position <= 'e'; position += 2){
            UpperpositionSet2.add(position);
        }
        for (char position = 'f' ; position <= 'n'; position += 2){
            UpperpositionSet2.add(position);
        }
        for (char position = 'q' ; position <= 'y'; position += 2){
            UpperpositionSet2.add(position);
        }
        for (char position = 'A' ; position <= 'I'; position += 2){
            LowerpositionSet2.add(position);
        }
        for (char position = 'L' ; position <= 'T'; position += 2){
            LowerpositionSet2.add(position);
        }
        for (char position = 'U' ; position <= 'Y'; position += 2){
            LowerpositionSet2.add(position);
        }
        for (char position = 'g' ; position <= 'o'; position += 2){
            LowerpositionSet2.add(position);
        }
        for (char position = 'p' ; position <= 'x'; position += 2){
            LowerpositionSet2.add(position);
        }
        LowerpositionSet2.add('b');LowerpositionSet2.add('d');

        for (char str: unplacedShape(placement,Shape)) {
            if (str == 'E' || str == 'B') {
                for (char rot : Rotation) {
                    if (rot >= 'E' && rot <= 'H') {
                        for (char pos : UpperpositionSet2) {
                            piece.add(String.valueOf(str) + String.valueOf(rot) + String.valueOf(pos));
                        }
                    } else if (rot >= 'A' && rot <= 'D') {
                        for (char pos : LowerpositionSet2) {
                            piece.add(String.valueOf(str) + String.valueOf(rot) + String.valueOf(pos));
                        }
                    }
                }
            } else {
                for (char rot : Rotation) {
                    if (rot >= 'E' && rot <= 'H') {
                        for (char pos : PositionBlocked(placement, UpperpositionSet)) {
                            piece.add(String.valueOf(str) + String.valueOf(rot) + String.valueOf(pos));
                        }
                    } else if (rot >= 'A' && rot <= 'D') {
                        for (char pos : PositionBlocked(placement, LowerpositionSet)) {
                            piece.add(String.valueOf(str) + String.valueOf(rot) + String.valueOf(pos));
                        }
                    }
                }
            }
        }
        return piece;
    }


    static void getSolution(String placement) {
        Set<String> pieces = solutionpiece(placement);
        for (String s:pieces){
            String newplacement = placement+s;
            if (isPlacementSequenceValid(newplacement)){
                if (newplacement.length() == 24){
                    Solutions.add(normalize(newplacement));
                }else {
                    getSolution(newplacement);
                }
            }
        }
    }

    public static String[]getSolutions(String placement){
        // FIXME Task 9: determine all solutions to the game, given a particular starting placement

        getSolution(placement);

        removeDuplicate(Solutions);


        String[]solution = new String[Solutions.size()];

        for (int i = 0; i<Solutions.size();i++){
            solution[i] = Solutions.get(i);
        }

        Solutions.clear();

        return solution;

    }

    static void removeDuplicate(ArrayList arlList)
    {
        HashSet h = new HashSet(arlList);
        arlList.clear();
        arlList.addAll(h);
    }

    /*Remove usedPieceShape
    * Written by Tingyu Pan (u6259941)
    */
    static Set<Character> unplacedShape(String placement, Set<Character> Shape){
        for (int i = 0;i < placement.length()/3; i++){
            char usedShape = placement.charAt(i*3);
            if (Shape.contains(usedShape)){
                Shape.remove(usedShape);
            }
        }
        return Shape;
    }

    static Set<Character> PositionBlocked(String placement, Set<Character> positionset){
        String [] pieces = makePieceArray(placement);


        for (String piece:pieces){
            positionset.remove(piece.charAt(2));
            if (piece.charAt(0) != 'B' && piece.charAt(0) != 'E'){
                for (char Upper = 'A';Upper <= 'K';Upper++){
                    positionset.remove(Upper);
                }
                for (char Lower = 'o';Lower <='y';Lower++){
                    positionset.remove(Lower);
                }
                positionset.remove('T');positionset.remove('U');
                positionset.remove('e');positionset.remove('f');
            }
        }
        return positionset;
    }

    static String normalize(String placement) {
        String[] pp = new String[8];
        boolean flip = false;
        for (int i = 0; i < placement.length(); i += 3) {
            int idx = placement.charAt(i) - 'A';
            pp[idx] = placement.substring(i, i + 3);
        }
        String norm = "";
        for (int i = 0; i < pp.length; i++) {
            if (pp[i] != null) norm += pp[i];
        }
        return norm;
    }
}
