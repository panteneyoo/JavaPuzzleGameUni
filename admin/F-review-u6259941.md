Reviewer: Tingyu Pan (u6259941)
Component: <...>
Author: Taylor-James Thomas (u6382547)

Review Comments:

Board.java: 197 unused boolean variable detectionCompletion1.

Board.java: 210-217 Using the String structure to initialize the shape set, which is save more space.
                    By replace the Character with "\" to mark the piece shape as used.

Board.java: 198-210 methods and variables are not named properly Java naming conventions, 
                    some variables like h,z,k a little bit fuzzy,making the code hard to read

Board.java: The code could be better documented with specific comments with the while-loop structure

DetectionCompletionTest.java: All test passes with a range of different conditions

DetectionCompletionTest.java: 24-25 TimeBound Rules for this test is kind of redundancy or need to 
                                    reduce the time limited

 DetectionCompletionTest.java: Needs more test examples with every test only one or two String, Could use Arrays in TestUtility
                                to cover more conditions.

``