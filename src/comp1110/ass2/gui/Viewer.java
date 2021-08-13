package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.Arrays;

/**
 * A very simple viewer for piece placements in the steps game.
 *
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various piece
 * placements.
 */
public class Viewer extends Application {

    /* board layout */
    private static final int SQUARE_SIZE = 60;
    private static final int PIECE_IMAGE_SIZE = (int) ((3*SQUARE_SIZE)*1.33);
    private static final int VIEWER_WIDTH = 750;
    private static final int VIEWER_HEIGHT = 500;

    private static final String URI_BASE = "assets/";

    private final Group root = new Group();
    private final Group controls = new Group();
    private final Group pieces = new Group();
    private final Group board = new Group();
    TextField textField;

    /*makes all the circles on the board
    * Written by Ziwei Liu (u6380075)
    */
    void makeCircles() {
        board.getChildren().clear();
        for (int i = 0; i < 50; i++) {
            if ((i / 10) % 2 == 0 && i % 2 == 0) {
                Circle circle = new Circle(20);
                circle.setFill(Color.GRAY);
                circle.setOpacity(0.5);
                circle.setLayoutX(120 + (60 * (i % 10)));
                circle.setLayoutY(120 + (60 * (i / 10)));
                board.getChildren().add(circle);
            }
            else if ((i / 10) % 2 != 0 && i % 2 != 0) {
                Circle circle = new Circle(20);
                circle.setFill(Color.GRAY);
                circle.setOpacity(0.5);
                circle.setLayoutX(120 + (60 * (i % 10)));
                circle.setLayoutY(120 + (60 * (i / 10)));
                board.getChildren().add(circle);
            }
        }
    }
    /*creates a piece according to the string entered
    * Written by Ziwei Liu (u6380075)
    */
    class Piece extends ImageView {
        Piece(String placementStr) {
            /*decides which image to use, flipped or normal*/
            if (placementStr.charAt(1) <= 'D' && placementStr.charAt(1) >= 'A') {
                setImage(new Image(Viewer.class.getResource(URI_BASE + placementStr.charAt(0) + "A" + ".png").toString()));
            } else {
                setImage(new Image(Viewer.class.getResource(URI_BASE + placementStr.charAt(0) + "E" + ".png").toString()));
            }
            /*decides which position on the board to place the piece*/
            if (placementStr.charAt(2) >= 'A' && placementStr.charAt(2) <= 'J') {
                setLayoutX(60 * (placementStr.charAt(2) - 65));
            }
            else if (placementStr.charAt(2) >= 'K' && placementStr.charAt(2) <= 'T') {
                setLayoutX(60 * (placementStr.charAt(2) - 75));
                setLayoutY(60);
            }
            else if (placementStr.charAt(2) >= 'U' && placementStr.charAt(2) <= 'Y') {
                setLayoutX(60 * (placementStr.charAt(2) - 85));
                setLayoutY(60 * 2);
            }
            else if (placementStr.charAt(2) >= 'a' && placementStr.charAt(2) <= 'e') {
                setLayoutX(60 * (placementStr.charAt(2) - 92));
                setLayoutY(60 * 2);
            }
            else if (placementStr.charAt(2) >= 'f' && placementStr.charAt(2) <= 'o') {
                setLayoutX(60 * (placementStr.charAt(2) - 102));
                setLayoutY(60 * 3);
            }
            else if (placementStr.charAt(2) >= 'p' && placementStr.charAt(2) <= 'y') {
                setLayoutX(60 * (placementStr.charAt(2) - 112));
                setLayoutY(60 * 4);
            }
            /*resizes the piece images*/
            setFitHeight(PIECE_IMAGE_SIZE);
            setFitWidth(PIECE_IMAGE_SIZE);
            /*rotates image according to the given character*/
            setRotate (90 *((placementStr.charAt(1) + 3) % 4));
        }
    }

    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement  A valid placement string
     *
     * Written by Ziwei Liu (u6380075)
     */
    void makePlacement(String placement) {
        /*This makes the array of strings each containing a 3 char string*/
            int N = placement.length() / 3;
            char[][] pieceArray = pieceArray = new char[N][3];
            String[] pieceString = pieceString = new String[N];
            root.getChildren().remove(pieces);
            pieces.getChildren().clear();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < 3; j++) {
                    pieceArray[i][j] = placement.charAt(i * 3 + j);
                }
                for (int m = 0; m < N; m++) {
                    pieceString[m] = String.valueOf(pieceArray[m]);
                }
            }
            /*this takes each slot of the array and feeds it into a piece constructor to make a piece and add to
            * the group*/
            for (int j = 0; j < pieceString.length; j++ ){
                pieces.getChildren().add(new Piece(pieceString[j]));
            }
        root.getChildren().add(pieces);
        // FIXME Task 4: implement the simple placement viewer
    }

    /**
     * Create a basic text field for input and a refresh button.
     *
     *
     */
    private void makeControls() {
        Label label1 = new Label("Placement:");
        textField = new TextField ();
        textField.setPrefWidth(300);
        Button button = new Button("Refresh");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                makePlacement(textField.getText());
                textField.clear();
            }
        });

        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(130);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("StepsGame Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        root.getChildren().add(controls);

        makeControls();

        makeCircles();
        root.getChildren().add(board);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
