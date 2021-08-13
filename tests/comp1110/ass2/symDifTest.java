package comp1110.ass2;

import org.junit.Test;
import comp1110.ass2.StepsGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;


/**
 * Created by Afternoon Tea on 25/09/2017.
 */
public class symDifTest {
    @Test
    //Should have removed "AAA" and "BBB" since they are both strings.
    public void containsTest() {
        String str1 = "AAABBB";
        String str2 = "AAABBBCCCDDDEEE";

        assertFalse("Contains members of previous string", StepsGame.symDif(str1, str2).contains("AAA"));
        assertFalse("Contains members of previous string", StepsGame.symDif(str1, str2).contains("BBB"));
    }

    @Test
    //Should remove nothing since there's nothing in the first string.
    public void noChangeTest() {
        String str1 = "";
        String str2 = "AAABBBCCCDDDEEE";
        HashSet<String> str2Set = new HashSet<>(StepsGame.symDif(str1, str2));
        String[] str2Array = str2Set.toArray(new String[str2Set.size()]);
        Arrays.sort(str2Array);
        String difStr = "";
        for (int i = 0; i < str2Array.length; i++) {
            difStr += str2Array[i];
        }

        assertFalse("Contains members of previous string", !(difStr.equals(str2)));
    }

    @Test
    //Should remove everything since everything in string 1 is included in string 2.
    public void allChangeTest() {
        String str1 = "AAABBBCCCDDDEEE";
        String str2 = "AAABBBCCCDDDEEE";

        assertFalse("Contains members of previous string", StepsGame.symDif(str1, str2).size() > 0);
    }
}
