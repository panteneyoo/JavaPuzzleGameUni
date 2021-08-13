package comp1110.ass2;

import org.junit.Test;

import comp1110.ass2.StepsGame;
import static comp1110.ass2.TestUtility.*;


import static org.junit.Assert.*;

public class SpecificValidSequenceTest {
    @Test
    public void testGoodPlacementSequence(){
        String test = "DFOGGQ";
        assertTrue("Placement '" + test + "' is valid, but was rejected.",StepsGame.isPlacementSequenceValid(test));
    }
    @Test
    public void testInvalidSequence(){
        String test = "DFQGGQ";
        assertFalse("Placement '" + test + "' is invalid, but was passed.",StepsGame.isPlacementSequenceValid(test));
    }
    @Test
    public void testBoarderPositiveFault(){
        String test = "DFOFHnEDI";
        assertFalse("Placement '" + test + "' is invalid, but was passed.",StepsGame.isPlacementSequenceValid(test));
    }
    @Test
    public void testPieceFPositiveFault(){
        String[] test = new String[]{"DFOGGQFHn","BFqFGh", "BGKFCNGAi"};
        for (String str : test){
            assertTrue("Placement '" + test + "' is invalid, but was rejected.",StepsGame.isPlacementSequenceValid(str));
        }
    }
    @Test
    public void ValidBoarderPiece(){
        String test = "BBHEEf";
        assertTrue("Placement '" + test + "' is invalid, but was rejected.",StepsGame.isPlacementSequenceValid(test));

    }
}