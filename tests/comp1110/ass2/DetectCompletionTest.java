package comp1110.ass2;
import comp1110.ass2.gui.Board;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;



import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;



/** Testing whether the code written to determine completion works,
 * i.e. it;
 *          - is 24 characters long
 *          - contains all 8 pieces (any order or rotation) once
 *          - passes isPlacementSequenceValid
 *
 */

public class DetectCompletionTest {

    @Rule
    public Timeout globalTimeout = Timeout.millis(2000);

    @Test
    public void simpleTest(){ String placement = "DFOGGQEDIBAkFHnHCiAALCAg";
        assertTrue("The placement " + placement + " is valid. but returned false", Board.detectCompletion(placement));
    }
    @Test
    public void tooShortTest() {
        String placement = "DFOGGQEDIBAk";
        String placement2 = "AALBDxCEQDBgEHuFGSGEOHEc";

        assertFalse("Should have returned false, as placement " + placement + " is too short" , Board.detectCompletion(placement));
        assertFalse("Should have returned false, as placement " + placement2 + " is too short", Board.detectCompletion(placement2));
    }
    @Test
    public void testGood() {
        String placement = "EFBCFlAFnGFSBFqFGhHANDDP";
        assertTrue("Should have returned true",Board.detectCompletion(placement));

    }
    @Test
    public void testBad(){
        String placement = "abcdefhij";
        assertFalse("This placement is not invalid and incomplete",Board.detectCompletion(placement));
    }

}
