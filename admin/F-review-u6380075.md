Reviewer: Ziwei Liu (u6380075)
Component: StepsGame.java - Task 3: isPlacementWellFormed
Author: Tingyu Pan (u6259941)

Review Comments:

1. The best feature of this code is that it splits the placement string into smaller strings representing the individual
pieces and stores them within an array. This makes accessing and manipulating individual pieces easy by simply referring
to the pieceString array instead of manipulating the placement string itself. It also has many conditional statements
which check a wide range of possible inputs.

2. The code could be better documented with comments stating what each of the if statements are checking with regards to
the placements string.

3. The first two if statements are also checks which assess the placement string, however they encompass the rest of the
code which can be difficult to read, they could be written as individual conditions to be checked first which only
need to return false if matched, that way it would be easier to read and interpret the code.

4. The entire block of code dedicated to converting the placement string into an array of piece strings could be written
as a seperate method to reduce clutter and make the entire method easier to interpret since its function is different than
the whole method's function and could cause confusion.

5. The code does not entirely follow Java naming conventions, the variables N and Pointer should not be capitalized.

6. isPiecePlacementWellFormed(pieceString[parameter]) == false could be changed to simply
   !(isPiecePlacementWellFormed(pieceString[parameter]));

