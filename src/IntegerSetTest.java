//============================================================================
// Author		: 5718
// Date			: 2/26/2018
// Assignment	: HomeWork #1
// Class		: CEN 4072
// University	: Florida Gulf Coast University
//============================================================================

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.*;
import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IntegerSetTest {

    private IntegerSet set1;    // First list of numbers
    private IntegerSet set2;    // Unique set from first list
    private IntegerSet set3;    // Second list of numbers
    private IntegerSet set4;    // Unique set from second list
    private IntegerSet set5;    // Intersection of set1 and set3
    private IntegerSet set6;    // Union of set1 and set3

    public IntegerSetTest(Object[] test1, Object[] test2, Object[] test3,
                          Object[] test4, Object[] test5, Object[] test6) {
        this.set1 = new IntegerSet((Integer[]) test1);
        this.set2 = new IntegerSet((Integer[]) test2);
        this.set3 = new IntegerSet((Integer[]) test3);
        this.set4 = new IntegerSet((Integer[]) test4);
        this.set5 = new IntegerSet((Integer[]) test5);
        this.set6 = new IntegerSet((Integer[]) test6);
    }

    @Parameterized.Parameters
    public static List<Object> readData() {
        Object[] testSet = new Object[10];
        Integer[] set1;
        Integer[] set2;
        Integer[] set3;
        Integer[] set4;
        Integer[] set5;
        Integer[] set6;

        String line;
        String fileName = "test_input.txt";

        Scanner input = null;

        try {
            input = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        /*
        Iterate through text file, creating a testSet for each 6 lines of numbers
        Skips the blank line after check for end of file
         */
        for (int numTest = 0; numTest < 10; numTest++) {
            line = input.nextLine();
            String[] stringArray = line.split(",");
            set1 = new Integer[stringArray.length];
            for (int i = 0; i < stringArray.length; i++) {
                set1[i] = Integer.parseInt(stringArray[i]);
            }

            line = input.nextLine();
            stringArray = line.split(",");
            set2 = new Integer[stringArray.length];
            for (int i = 0; i < stringArray.length; i++) {
                set2[i] = Integer.parseInt(stringArray[i]);
            }

            line = input.nextLine();
            stringArray = line.split(",");
            set3 = new Integer[stringArray.length];
            for (int i = 0; i < stringArray.length; i++) {
                set3[i] = Integer.parseInt(stringArray[i]);
            }

            line = input.nextLine();
            stringArray = line.split(",");
            set4 = new Integer[stringArray.length];
            for (int i = 0; i < stringArray.length; i++) {
                set4[i] = Integer.parseInt(stringArray[i]);
            }

            line = input.nextLine();
            stringArray = line.split(",");
            set5 = new Integer[stringArray.length];
            for (int i = 0; i < stringArray.length; i++) {
                set5[i] = Integer.parseInt(stringArray[i]);
            }

            line = input.nextLine();
            stringArray = line.split(",");
            set6 = new Integer[stringArray.length];
            for (int i = 0; i < stringArray.length; i++) {
                set6[i] = Integer.parseInt(stringArray[i]);
            }

            Object[][] test = {set1, set2, set3, set4, set5, set6};
            testSet[numTest] = test;

            if (!input.hasNextLine()) {
                break;
            } else {
                input.nextLine();
            }
        }
        return Arrays.asList(testSet);
    }

    /*
    12 tests to be run, testing each method in the IntegerSet class
     */

    /*
     Test 1
     Tests null inputs for intersection
     */
    @Test(expected = NullPointerException.class)
    public void testIntersectionWithNullInput() {
        IntegerSet test1 = null;
        IntegerSet expected = new IntegerSet();
        Assert.assertArrayEquals(expected.toArray(), IntegerSet.intersection(test1, set1).toArray());
    }

    /*
     Test 2
     Tests if a value exists in a set
     */
    @Test
    public void testExists() {
        Assert.assertTrue(set3.exists(randomNumFromSet(set3)));
    }

    /*
     Test 3
     Tests if an IntegerSet is empty
     */
    @Test
    public void testIsEmpty() {
        IntegerSet empty = new IntegerSet();
        Assert.assertTrue(empty.isEmpty());
    }

    /*
     Test 4
     Tests the union of two sets
     */
    @Test
    public void testUnion() {
        Assert.assertArrayEquals(set6.toArray(), IntegerSet.union(set1, set3).toArray());
    }

    /*
     Test 5
     Tests the creation of a set from an array
     */
    @Test
    public void testCreateSetFromArray() {
        IntegerSet testSet1 = new IntegerSet(set1.toArray());
        Assert.assertArrayEquals(set1.toArray(), testSet1.toArray());

    }

    /*
     Test 6
     Tests the creation of a set from null
     */
    @Test(expected = NullPointerException.class)
    public void testCreateSetFromNull() {
        IntegerSet nullSet =  new IntegerSet(null);
        nullSet.insertAll(set1.toArray());
        Assert.assertArrayEquals(set1.toArray(), set2.toArray());
    }

    /*
     Test 7
     Tests the deletion of all elements in a set
     */
    @Test
    public void testDeleteAll() {
        IntegerSet testSet1 = new IntegerSet(set1.toArray());
        IntegerSet emptySet = new IntegerSet();
        testSet1.deleteAll();
        Assert.assertArrayEquals(testSet1.toArray(), emptySet.toArray());
    }

    /*
     Test 8
     Tests deleting one element from IntegerSet object
     */
    @Test
    public void testDeleteElement() {
        IntegerSet testSet1 = new IntegerSet(set1.toArray());
        int deleted = randomNumFromSet(set1);
        List<Integer> setList = Arrays.asList(testSet1.toArray());
        int indexOfDeleted = setList.indexOf(deleted);
        testSet1.deleteElement(indexOfDeleted);
        Assert.assertFalse(testSet1.exists(deleted));
    }

    /*
     Test 9
     Test inserting an entire Array in IntegerSet object
     */
    @Test
    public void testInsertAll() {
        IntegerSet test1 = new IntegerSet();
        test1.insertAll(set1.toArray());
        Assert.assertArrayEquals(set1.toArray(), test1.toArray());
    }

    /*
     Test 10
     Tests IntegerSet object for null data
     */
    @Test
    public void testAllNull() {
        Integer[] nullSet = new Integer[set1.size()];
        for (int i = 0; i>set1.size() - 1; i++){
            nullSet[i] = null;
        }

        Integer[] nullSet2 = new Integer[set1.size()];
        for (int i = 0; i>set1.size() - 1; i++){
            nullSet2[i] = null;
        }

        Assert.assertArrayEquals(nullSet, nullSet2);
    }


    /*
     Test 11
     Tests intersection of two IntegerSet's
     */
    @Test
    public void testIntersection() {
        Assert.assertArrayEquals(set5.toArray(), IntegerSet.intersection(set1, set3).toArray());
    }

    /*
     Test 12
     Tests null inputs for union
     */
    @Test(expected = NullPointerException.class)
    public void testUnionWithNullInput() {
        IntegerSet test = null;
        IntegerSet expected = new IntegerSet();
        Assert.assertArrayEquals(expected.toArray(), IntegerSet.union(test, set1).toArray());
    }

    /*
    Small method to retrieve a random number from the IntegerSet that is passed to it
     */
    private int randomNumFromSet(IntegerSet test) {
        Random rand = new Random();
        return test.toArray()[rand.nextInt(test.size())];

    }
}
