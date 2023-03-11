package hw7;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileNavigatorTest {

    @Test
    public void addTest() {
        FileData testFile1 = new FileData("Test name1.jpg", 23, "c/first/second/third");
        FileData testFile2 = new FileData("Test name2.jpg", 43, "c/first/second/third");
        FileData testFile3 = new FileData("Test name3.jpg", 83, "c/first/second/third/fourth");
        FileNavigator navigatorTest = new FileNavigator();
        navigatorTest.add(testFile1);
        navigatorTest.add(testFile2);
        navigatorTest.add(testFile3);
        List<FileData> pathThird = List.of(testFile1, testFile2);
        List<FileData> pathFourth = List.of(testFile3);
        assertArrayEquals(pathThird.toArray(), navigatorTest.getFilesMap().get("c/first/second/third").toArray());
        assertArrayEquals(pathFourth.toArray(), navigatorTest.getFilesMap().get("c/first/second/third/fourth").toArray());
    }

    @Test
    public void findTest() {
        FileData testFile1 = new FileData("Test name1.jpg", 23, "c/first/second/third");
        FileData testFile2 = new FileData("Test name2.jpg", 43, "c/first/second/third");
        FileNavigator navigatorTest = new FileNavigator();
        navigatorTest.add(testFile1);
        navigatorTest.add(testFile2);
        List<FileData> pathThird = List.of(testFile1, testFile2);
        assertArrayEquals(pathThird.toArray(), navigatorTest.find("c/first/second/third").toArray());
    }

    @Test
    public void filterBySizeTest() {
        FileData testFile1 = new FileData("Test name1.jpg", 23, "c/first/second/third");
        FileData testFile2 = new FileData("Test name2.jpg", 43, "c/first/second/third/fourth/sixth");
        FileData testFile3 = new FileData("Test name3.jpg", 83, "c/first/second/third/fourth");
        FileData testFile4 = new FileData("Test name4.jpg", 163, "c/first/second/third/fourth/fifth");
        FileData testFile5 = new FileData("Test name5.jpg", 13, "c/first/second/third");
        FileData testFile6 = new FileData("Test name6.jpg", 4, "c/first/second/third/fourth/sixth");
        FileNavigator navigatorTest = new FileNavigator();
        navigatorTest.add(testFile1);
        navigatorTest.add(testFile2);
        navigatorTest.add(testFile3);
        navigatorTest.add(testFile4);
        navigatorTest.add(testFile5);
        navigatorTest.add(testFile6);
        assertTrue(navigatorTest.filterBySize(80).contains(testFile1));
        assertTrue(navigatorTest.filterBySize(80).contains(testFile2));
        assertFalse(navigatorTest.filterBySize(80).contains(testFile3));
        assertFalse(navigatorTest.filterBySize(80).contains(testFile4));
        assertTrue(navigatorTest.filterBySize(80).contains(testFile5));
        assertTrue(navigatorTest.filterBySize(80).contains(testFile6));
    }

    @Test
    public void removeTest() {
        FileData testFile1 = new FileData("Test name1.jpg", 23, "c/first/second/third");
        FileData testFile2 = new FileData("Test name2.jpg", 43, "c/first/second/third/fourth/sixth");
        FileData testFile3 = new FileData("Test name3.jpg", 83, "c/first/second/third/fourth");
        FileData testFile4 = new FileData("Test name4.jpg", 163, "c/first/second/third/fourth/fifth");
        FileData testFile5 = new FileData("Test name5.jpg", 13, "c/first/second/third");
        FileData testFile6 = new FileData("Test name6.jpg", 4, "c/first/second/third/fourth/sixth");
        FileNavigator navigatorTest = new FileNavigator();
        navigatorTest.add(testFile1);
        navigatorTest.add(testFile2);
        navigatorTest.add(testFile3);
        navigatorTest.add(testFile4);
        navigatorTest.add(testFile5);
        navigatorTest.add(testFile6);
        navigatorTest.remove("c/first/second/third/fourth/sixth");
        assertTrue(navigatorTest.getFilesMap().containsKey("c/first/second/third"));
        assertTrue(navigatorTest.getFilesMap().containsKey("c/first/second/third/fourth/fifth"));
        assertFalse(navigatorTest.getFilesMap().containsKey("c/first/second/third/fourth/sixth"));
    }

    @Test
    public void sortBySizeTest() {
        FileData testFile1 = new FileData("Test name1.jpg", 23, "c/first/second/third");
        FileData testFile2 = new FileData("Test name2.jpg", 43, "c/first/second/third/fourth/sixth");
        FileData testFile3 = new FileData("Test name3.jpg", 83, "c/first/second/third/fourth");
        FileData testFile4 = new FileData("Test name4.jpg", 163, "c/first/second/third/fourth/fifth");
        FileData testFile5 = new FileData("Test name5.jpg", 13, "c/first/second/third");
        FileData testFile6 = new FileData("Test name6.jpg", 4, "c/first/second/third/fourth/sixth");
        FileNavigator navigatorTest = new FileNavigator();
        navigatorTest.add(testFile1);
        navigatorTest.add(testFile2);
        navigatorTest.add(testFile3);
        navigatorTest.add(testFile4);
        navigatorTest.add(testFile5);
        navigatorTest.add(testFile6);
        navigatorTest.sortBySize();
        assertEquals(4, navigatorTest.sortBySize().get(0).fileSize());
        assertEquals(13, navigatorTest.sortBySize().get(1).fileSize());
        assertEquals(23, navigatorTest.sortBySize().get(2).fileSize());
        assertEquals(43, navigatorTest.sortBySize().get(3).fileSize());
        assertEquals(83, navigatorTest.sortBySize().get(4).fileSize());
        assertEquals(163, navigatorTest.sortBySize().get(5).fileSize());
    }
}
