package hw_2_14;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static hw_2_14.constants.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

public class StringListImplTest {

    private final StringListImpl out = new StringListImpl();
    private final StringListImpl otherList = new StringListImpl();
    private final StringListImpl otherNullList = new StringListImpl();
    private String OUT_TO_STRING;
    private String OUT_TO_ARRAY;
    private String OUT_NULL_STRING;

    @BeforeEach
    public void setUp(){
        out.add(STRING_00);
        out.add(STRING_01);
        out.add(STRING_02);

        otherList.add(STRING_01);
        otherList.add(STRING_02);
        otherList.add(STRING_03);

        OUT_TO_STRING = "[00, 01, 02, null, null]";
        OUT_TO_ARRAY = "[00, 01, 02]";
        OUT_NULL_STRING = "[null, null, null, null, null]";
    }

    @Test
    public void shouldReturnCorrectStringAndSizeAfterAdd(){
        String result = out.add(STRING_03);
        assertEquals(STRING_03, result);
        assertEquals(4, out.size());
    }
    @Test
    public void shouldReturnCorrectStringAndSizAfterAddByIndex(){
        String result = out.add(INDEX_0, STRING_10);
        assertEquals(STRING_10, result);
        assertEquals(4, out.size());
    }
    @Test
    public void shouldThrowRuntimeExceptionWhenTryingToAddStringToIndexOutOfArraySize() {
        assertThrows(RuntimeException.class,
                () -> out.add(INDEX_5, STRING_05)
        );
    }
    @Test
    public void shouldThrowRuntimeExceptionWhenTryingToAddStringToIndexOutOfListSize() {
        assertThrows(RuntimeException.class,
                () -> out.add(INDEX_3, STRING_03)
        );
    }
    @Test
    public void shouldReturnCorrectStringAndSizAfterSet(){
        String result = out.set(INDEX_0, STRING_10);
        assertEquals(STRING_10, result);
        assertEquals(3, out.size());
    }
    @Test
    public void shouldThrowRuntimeExceptionWhenTryingToSetStringToIndexOutOfArraySize() {
        assertThrows(RuntimeException.class,
                () -> out.set(INDEX_5, STRING_05)
        );
    }
    @Test
    public void shouldThrowRuntimeExceptionWhenTryingToSetStringToIndexOutOfListSize() {
        assertThrows(RuntimeException.class,
                () -> out.set(INDEX_3, STRING_03)
        );
    }

    @Test
    public void shouldReturnCorrectStringAndSizAfterRemove(){
        String result = out.remove(STRING_00);
        assertEquals(STRING_00, result);
        assertEquals(2, out.size());
    }
    @Test
    public void shouldThrowRuntimeExceptionWhenTryingToRemoveNotExistElement() {
        assertThrows(RuntimeException.class,
                () -> out.remove(STRING_03)
        );
    }
    @Test
    public void shouldReturnCorrectStringAndSizAfterRemoveByIndex(){
        String result = out.remove(INDEX_0);
        assertEquals(STRING_00, result);
        assertEquals(2, out.size());
    }
    @Test
    public void shouldThrowRuntimeExceptionWhenTryingToRemoveStringToIndexOutOfArraySize() {
        assertThrows(RuntimeException.class,
                () -> out.remove(INDEX_5)
        );
    }
    @Test
    public void shouldThrowRuntimeExceptionWhenTryingToRemoveStringToIndexOutOfListSize() {
        assertThrows(RuntimeException.class,
                () -> out.remove(INDEX_3)
        );
    }

    @Test
    public void shouldReturnFalseIfListsNotEquals(){
        boolean result = out.equals(otherList);
        assertFalse(result);
    }
    @Test
    public void shouldThrowRuntimeExceptionWhenAnotherListIsNull() {
        assertThrows(RuntimeException.class,
                () -> out.equals(otherNullList)
        );
    }

    @Test
    public void shouldReturnCorrectSize(){
        int result = out.size();
        assertEquals(3, result);
    }

    @Test
    public void shouldReturnTrueIfListEmpty(){
        boolean result = otherNullList.isEmpty();
        assertTrue(result);
    }
    @Test
    public void shouldReturnCorrectString(){
        String result = out.toString();
        assertEquals(OUT_TO_STRING, result);
    }
    @Test
    public void shouldReturnTrueWhenListContainsItem(){
        boolean result = out.contains(STRING_00);
        assertTrue(result);
    }
    @Test
    public void shouldReturnCorrectIndexOfItemInList(){
        int result = out.indexOf(STRING_01);
        assertEquals(INDEX_1, result);
    }
    @Test
    public void shouldReturnCorrectLastIndexOfItemInList(){
        out.add(STRING_01);
        int result = out.lastIndexOf(STRING_01);
        assertEquals(INDEX_3, result);
    }

    @Test
    public void shouldReturnCorrectStringAndSizAfterGet(){
        String result = out.get(INDEX_0);
        assertEquals(STRING_00, result);
    }
    @Test
    public void shouldThrowRuntimeExceptionWhenTryingToGetStringByIndexOutOfArraySize() {
        assertThrows(RuntimeException.class,
                () -> out.get(INDEX_5)
        );
    }
    @Test
    public void shouldThrowRuntimeExceptionWhenTryingToGetStringByIndexOutOfListSize() {
        assertThrows(RuntimeException.class,
                () -> out.get(INDEX_3)
        );
    }
    @Test
    public void shouldClearList(){
        out.clear();
        String result = out.toString();
        assertEquals(OUT_NULL_STRING, result);
    }
    @Test
    public void shouldReturnStringArrayWithListItems(){
        String result = Arrays.toString(out.toArray());
        assertEquals(OUT_TO_ARRAY, result);
    }


}
