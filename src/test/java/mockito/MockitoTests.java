package mockito;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.Properties;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class MockitoTests {
    @Test
    void ensureSpyForListWorks() {
        List<String> list = new ArrayList<>();
        List<String> spiedList = spy(list);
        doReturn("42").when(spiedList).get(99);
        assertEquals("42", spiedList.get(99));
    }

    @Test
    void ensureMockForListWorks() {
        List<String> mockedList = mock(List.class);
        when(mockedList.get(anyInt())).thenReturn("42");
        assertEquals("42", mockedList.get(99));
    }

    @Test
    void testMockitoThrows() {
        Properties properties = mock(Properties.class);
        when(properties.get(anyString())).thenThrow(new IllegalArgumentException("Stuff"));
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> properties.get("A"));
        assertEquals("Stuff", exception.getMessage());
    }

//    @Mock
//    List<String> mockedList;

    @Test
    public void whenUseMockAnnotation_thenMockIsInjected() {
        List<String> mockedList = mock(List.class);
        mockedList.add("one");
        verify(mockedList).add("one");
        assertEquals(0, mockedList.size());

        when(mockedList.size()).thenReturn(100);
        assertEquals(100, mockedList.size());
    }

    @Test
    public void whenNotUseSpyAnnotation_thenCorrect() {
        List<String> spyList = spy(new ArrayList<>());

        spyList.add("one");
        spyList.add("two");

        verify(spyList).add("one");
        verify(spyList).add("two");

        assertEquals(2, spyList.size());

        doReturn(100).when(spyList).size();
        assertEquals(100, spyList.size());
    }

}
