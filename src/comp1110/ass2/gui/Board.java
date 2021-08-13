package comp1110.ass2.gui;

import comp1110.ass2.StepsGame;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.LinkedList;
import java.util.Random;
import java.util.Set;


/*This class takes inspiration from the Game class in COMP1110 Assignment 1
* NOTE: All tests we've written are in the "tests" folder, they are named DetectCompletionTest, symDifTest and
* SpecificValidSequenceTest*/

public class Board extends Application {
    private static final int SQUARE_SIZE = 60;
    private static final int PIECE_IMAGE_SIZE = (int) ((3*SQUARE_SIZE)*1.33);
    private static final int PIECE_IMAGE_SIZE_SMALL = (int) ((3*SQUARE_SIZE));
    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;

    private static final String URI_BASE = "assets/";

    public static final String START_PLACEMENTS[] = { "CFlAFnGFSBFq"}; //an array of set starting placements

    public static String currentPlacements = ""; //a string representing the current placement of pieces, updated
                                                 //passed into other methods and changed as the player places pieces

    private static LinkedList<UIDraggablePieces> pieceList = new LinkedList<>();
    private static LinkedList<UIDraggablePieces> pieceStore = new LinkedList<>();
    private static LinkedList<Circle> circStore = new LinkedList<>();
    private static LinkedList<Circle> blankStore = new LinkedList<>();
    private final Group root = new Group(); //setting up groups for pieces, controls, the board etc.
    private final Group pieces = new Group();
    private final Group board = new Group();

    /*Basic setup for pieces, including image and each piece's individual string representation
    * Written by Ziwei Liu (u6380075)
    */
    class UIPieces extends ImageView {
        char piece;
        String pieceStr = "";
        UIPieces () {}

        UIPieces (char piece) {
            this.piece = piece;
            setImage(new Image(Viewer.class.getResource(URI_BASE + piece + "A" + ".png").toString()));
            pieceStr = pieceStr + piece + "A";
            setFitHeight(PIECE_IMAGE_SIZE_SMALL);
            setFitWidth(PIECE_IMAGE_SIZE_SMALL);
        }
    }

    /*Adds additional functionality to pieces such as making them draggable and rotatable etc.
    * Written by Ziwei Liu (u6380075)
    */
    class UIDraggablePieces extends UIPieces {
        int homeX, homeY;
        double mouseX, mouseY;
        boolean mousePressed = false;

        UIDraggablePieces(char piece) {
            super(piece);
            homeX = 60 + (180 * ((piece - 65) % 4));
            homeY = piece < 69 ? 0 : 500;
            setLayoutX(homeX);
            setLayoutY(homeY);

            setOnMousePressed(event -> {
                pieceList.remove(this);
                makeCurrentPlacements();
                mouseX = event.getSceneX();
                mouseY = event.getSceneY();
                setFitHeight(PIECE_IMAGE_SIZE);
                setFitWidth(PIECE_IMAGE_SIZE);
                toFront();
                mousePressed = true;
            });

            setOnScroll(event -> {
                if (mousePressed) {
                    rotate();
                    event.consume();
                }
            });

            setOnMouseDragged(event -> {
                double movementX = event.getSceneX() - mouseX;
                double movementY = event.getSceneY() - mouseY;
                setLayoutX(getLayoutX() + movementX);
                setLayoutY(getLayoutY() + movementY);
                mouseX = event.getSceneX();
                mouseY = event.getSceneY();
                event.consume();
            });

            setOnMouseReleased(event -> {
                mousePressed = false;
                snapGrid();
            });
        }

        /*Snaps piece onto one of the pegs if its on the board and then updates its pieceStr and currentPlacements
        * Written by Ziwei Liu (u6380075)
        */
        private void snapGrid() {
            if (onBoard()) {
                Circle circ = this.findNearCirc();

                double coordX = circ.getLayoutX()-120;
                double coordY = circ.getLayoutY()-120;
                char pos;
                String testStr = new String (pieceStr);

                if ((coordX >= 360 && coordY == 210)) {
                    pos = (char) ('a' + ((coordX / 60) - 6));
                }
                else if (coordY > 210) {
                    pos = (char) ('f' + ((coordX / 60) - 1) + (10 * (((coordY - 90) / 60) - 3)));
                }
                else {
                    pos = (char) ('A' + ((coordX / 60) - 1) + (10 * (coordY - 90) / 60));
                }

                if (testStr.length() == 3) {
                    testStr = "" + testStr.charAt(0) + testStr.charAt(1) + pos;
                }
                else {
                    testStr = testStr + pos;
                }
                String testPlacements = currentPlacements + testStr;

                if (StepsGame.isPlacementSequenceValid(testPlacements)) {
                    pieceStr = "" + testStr;
                    pieceList.add(this);
                    makeCurrentPlacements();
                    setLayoutX(coordX);
                    setLayoutY(coordY);
                }
                else {
                    snapHome();
                }
            }
            else {
                snapHome();
            }
        }

        /*Snaps piece back to home coordinates and updates currentPlacements
        * Written by Ziwei Liu (u6380075)
        */
        private void snapHome() {
            pieceList.remove(this);
            makeCurrentPlacements();
            setFitHeight(PIECE_IMAGE_SIZE_SMALL);
            setFitWidth(PIECE_IMAGE_SIZE_SMALL);
            setLayoutX(homeX);
            setLayoutY(homeY);
        }

        /*Detects whether a piece is on the board
        * Written by Ziwei Liu (u6380075)
        */
        private boolean onBoard() {
            return (getLayoutX() > 40) && (getLayoutX() < 620) &&
                    (getLayoutY() > 60) && (getLayoutY() < 360);
        }

        /*Rotates a piece and updates pieceStr accordingly
        * Written by Ziwei Liu (u6380075)
        */
        private void rotate() {
            char rot = pieceStr.charAt(1);
            if (rot < 'D' && rot >= 'A') {
                pieceStr = "" + piece + (char) (rot + 1);
                setRotate((getRotate() + 90) % 360);
            }
            if (rot == 'D') {
                pieceStr = "" + piece + (char) (rot + 1);
                setImage(new Image(Viewer.class.getResource(URI_BASE + piece + "E" + ".png").toString()));
                setRotate((getRotate() + 90) % 360);
            }
            if (rot > 'D' && rot < 'H') {
                pieceStr = "" + piece + (char) (rot + 1);
                setRotate((getRotate() + 90) % 360);
            }
            if (rot == 'H') {
                pieceStr = "" + piece + "A";
                setImage(new Image(Viewer.class.getResource(URI_BASE + piece + "A" + ".png").toString()));
                setRotate((getRotate() + 90) % 360);
            }
        }

        /*Calculates distance between piece and a coordinate
        * NOTE: takes inspiration from COMP1110 lab 7
        * Written by Ziwei Liu (u6380075)
        * */
        private double distance (double x, double y) {
            return Math.sqrt(Math.pow(Math.abs(this.getLayoutX() - x), 2) + Math.pow(Math.abs(this.getLayoutY() - y), 2));
        }

        /*Finds nearest peg on the board, switches to find the empty spaces if piece is flipped
        * Written by Ziwei Liu (u6380075)*/
        Circle findNearCirc () {
            Circle current = circStore.get(0);
            if (pieceStr.charAt(1) >= 'A' && pieceStr.charAt(1) <= 'D') {
                for (Circle c : circStore) {
                    if (this.distance((c.getLayoutX() - 120), (c.getLayoutY() - 120)) < this.distance((current.getLayoutX() - 120), (current.getLayoutY() - 120))) {
                        current = c;
                    }
                }
            }
            else {
                for (Circle c : blankStore) {
                    if (this.distance((c.getLayoutX() - 120), (c.getLayoutY() - 120)) < this.distance((current.getLayoutX() - 120), (current.getLayoutY() - 120))) {
                        current = c;
                    }
                }
            }
            return current;
        }
    }

    /*Creates the currentPlacement string from the list of pieces currently placed on the board
    * Written by Ziwei Liu (u6380075)*/
    private void makeCurrentPlacements(){
        currentPlacements = "";
        for (int i = 0; i < pieceList.size(); i++) {
            currentPlacements += pieceList.get(i).pieceStr;
        }
    }

    /*selects starting placements and generates board accordingly
    * Written by Ziwei Liu (u6380075)*/
    private void getStartingPlacements(){
        Random rand = new Random();
        Button button = new Button("Start");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int randInt = rand.nextInt(START_PLACEMENTS.length);
                String stPlace = START_PLACEMENTS[randInt];
                String[] stArray = StepsGame.makePieceArray(stPlace);
                for (UIDraggablePieces p : pieceStore) {
                    p.snapHome();
                }

                for (int i = 0; i < stArray.length; i++) {
                    for (UIDraggablePieces pc : pieceStore) {
                        if (stArray[i].charAt(0) == pc.piece){
                            if (stArray[i].charAt(1) <= 'D' && stArray[i].charAt(1) >= 'A') {
                                pc.setImage(new Image(Viewer.class.getResource(URI_BASE + pc.piece + "A" + ".png").toString()));
                            } else {
                                pc.setImage(new Image(Viewer.class.getResource(URI_BASE + pc.piece + "E" + ".png").toString()));
                            }
                            pc.setRotate (90 *((stArray[i].charAt(1) + 3) % 4));

                            pc.setFitHeight(PIECE_IMAGE_SIZE);
                            pc.setFitWidth(PIECE_IMAGE_SIZE);

                            if (stArray[i].charAt(2) >= 'A' && stArray[i].charAt(2) <= 'J') {
                                pc.setLayoutX(60 + (60 * (stArray[i].charAt(2) - 65)));
                                pc.setLayoutY(90);
                            }
                            else if (stArray[i].charAt(2) >= 'K' && stArray[i].charAt(2) <= 'T') {
                                pc.setLayoutX(60 + (60 * (stArray[i].charAt(2) - 75)));
                                pc.setLayoutY(90 + 60);
                            }
                            else if (stArray[i].charAt(2) >= 'U' && stArray[i].charAt(2) <= 'Y') {
                                pc.setLayoutX(60 + (60 * (stArray[i].charAt(2) - 85)));
                                pc.setLayoutY(90 + (60 * 2));
                            }
                            else if (stArray[i].charAt(2) >= 'a' && stArray[i].charAt(2) <= 'e') {
                                pc.setLayoutX(60 + (60 * (stArray[i].charAt(2) - 92)));
                                pc.setLayoutY(90 + (60 * 2));
                            }
                            else if (stArray[i].charAt(2) >= 'f' && stArray[i].charAt(2) <= 'o') {
                                pc.setLayoutX(60 + (60 * (stArray[i].charAt(2) - 102)));
                                pc.setLayoutY(90 + (60 * 3));
                            }
                            else if (stArray[i].charAt(2) >= 'p' && stArray[i].charAt(2) <= 'y') {
                                pc.setLayoutX(60 + (60 * (stArray[i].charAt(2) - 112)));
                                pc.setLayoutY(90 + (60 * 4));
                            }
                            pc.toFront();
                            pc.pieceStr = stArray[i];
                            pieceList.add(pc);
                        }
                    }
                }
                makeCurrentPlacements();
            }
        });
        root.getChildren().add(button);
    }

    /*Makes the pegs and blank spaces for the board and adds them to linked lists
    * NOTE: this method takes inspiration from code used in COMP1110 lab 7
    * Written by Ziwei Liu (u6380075)
    */
    void makeBoard() {
        board.getChildren().clear();
        for (int i = 0; i < 50; i++) {
            if ((i / 10) % 2 == 0 && i % 2 == 0) {
                Circle circle = new Circle(20);
                circle.setFill(Color.GRAY);
                circle.setOpacity(0.5);
                circle.setLayoutX(180 + (60 * (i % 10)));
                circle.setLayoutY(210 + (60 * (i / 10)));
                circStore.add(circle);
                board.getChildren().add(circle);
            }
            else if ((i / 10) % 2 == 0 && i % 2 != 0) {
                Circle circle = new Circle(20);
                circle.setOpacity(0.0);
                circle.setLayoutX(180 + (60 * (i % 10)));
                circle.setLayoutY(210 + (60 * (i / 10)));
                blankStore.add(circle);
                board.getChildren().add(circle);
            }
            else if ((i / 10) % 2 != 0 && i % 2 == 0) {
                Circle circle = new Circle(20);
                circle.setOpacity(0.0);
                circle.setLayoutX(180 + (60 * (i % 10)));
                circle.setLayoutY(210 + (60 * (i / 10)));
                blankStore.add(circle);
                board.getChildren().add(circle);
            }
            else if ((i / 10) % 2 != 0 && i % 2 != 0) {
                Circle circle = new Circle(20);
                circle.setFill(Color.GRAY);
                circle.setOpacity(0.5);
                circle.setLayoutX(180 + (60 * (i % 10)));
                circle.setLayoutY(210 + (60 * (i / 10)));
                circStore.add(circle);
                board.getChildren().add(circle);
            }
        }
    }

    /*Makes all the pieces necessary and adds them into JavaFX group and a linked list
    * Written by Ziwei Liu (u6380075)
    */
    void makePieces() {
        pieces.getChildren().clear();
        for (char pc = 'A'; pc < 'I'; pc++) {
            pieceStore.add(new UIDraggablePieces(pc));
        }
        for (UIDraggablePieces piece : pieceStore) {
            pieces.getChildren().add(piece);
        }
    }

    /*Detects whether game is complete
    * Written by Taylor-James Taylor (u6382547)
    */
    public static boolean detectCompletion(String placement) {
        //boolean detectCompletion1 = false;
        boolean allEightPieces = false;
        String gh = "";
        int i = 0;
        if (placement.length() != 24)
            allEightPieces = false;
        else if (placement.length() == 24){
            while (i < 24) {
                String a = Character.toString(placement.charAt(i));
                gh = gh + a;
                i = i + 3;
                }
            int j = 0;
            String z = "ABCDEFGH";

            while (j < 8) {
                char h = gh.charAt(j);
                int k = 0;
                while (k < 8) {
                     if (h == z.charAt(k)) {
                        z.replace(h, '\'');
                            k++;
                    } else k++;

                } j++;
                if (z==null);
                allEightPieces = true;

                }}
        if (allEightPieces && StepsGame.isPlacementSequenceValid(placement) ){
            return true;}
        else return false;

    }

    /*Highlights a viable next piece as a hint given the current placements on the board
    * NOTE: this method takes inspiration from code used in COMP1110 lab 5
    * Written by Ziwei Liu (u6380075)*/
    void hints() {
        Button hint = new Button("Hint");
        hint.setLayoutY(50);
        Text noSol = new Text("No solutions");
        noSol.setLayoutX(5);
        noSol.setLayoutY(220);
        noSol.setOpacity(0);
        noSol.setFont(Font.font("IMPACT", FontWeight.BOLD, 25));
        noSol.setFill(Color.RED);
        root.getChildren().add(noSol);
        hint.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (currentPlacements.length() != 24) {
                    String sols[] = StepsGame.getSolutions(currentPlacements);
                    if (sols.length != 0) {
                        Set<String> place = StepsGame.getViablePiecePlacements(currentPlacements, sols[0]);
                        String[] pieces = place.toArray(new String[place.size()]);
                        String hintPiece = pieces[0];
                        for (UIDraggablePieces pc : pieceStore) {
                            if (hintPiece.charAt(0) == pc.piece) {
                                Timeline timeline = new Timeline(
                                        new KeyFrame(Duration.millis(500),
                                                on -> {
                                                    noSol.toFront();
                                                    pc.setEffect(new Glow(1));
                                                }),
                                        new KeyFrame(Duration.millis(1000),
                                                off -> {
                                                    pc.setEffect(null);
                                                }));
                                timeline.setCycleCount(3);
                                timeline.play();
                            }
                        }
                    }
                    else {
                        Timeline timeline = new Timeline(
                                new KeyFrame(Duration.millis(2000),
                                        on -> {
                                            noSol.setOpacity(1.0);
                                        }),
                                new KeyFrame(Duration.millis(3000),
                                        off -> {
                                            noSol.setOpacity(0.0);
                                        }));
                        timeline.setCycleCount(1);
                        timeline.play();
                    }
                }
            }
        });
        root.getChildren().add(hint);
    }

    // FIXME Task 7: Implement a basic playable Steps Game in JavaFX that only allows pieces to be placed in valid places

    // FIXME Task 8: Implement starting placements

    // FIXME Task 10: Implement hints

    // FIXME Task 11: Generate interesting starting placements

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("StepsGame");
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);

        makeBoard();
        makePieces();
        getStartingPlacements();
        hints();
        root.getChildren().add(board);
        root.getChildren().add(pieces);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
