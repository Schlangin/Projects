import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnitTest {
    @Test
    void test() {
        ToDoListEntry entry1 = new ToDoListEntry(true, 1,"Do Homework" );

        assertTrue(entry1.completeTask);
        assertEquals("Do Homework", entry1.getDescription());




    }

}
