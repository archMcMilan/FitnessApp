import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TestClass {

    @Test
    public void myTest(){
        Assert.assertFalse(false);
    }

    @Test
    public void myTestMockito(){
        // mock creation
        List mockedList = mock(List.class);
        //List mockedList = spy(List.class);

// using mock object - it does not throw any "unexpected interaction" exception
        mockedList.add("one");
        mockedList.clear();

        // selective, explicit, highly readable verification
        verify(mockedList).add("one");
        verify(mockedList).clear();

        assertThat(mockedList.size(),is(1));
    }

    @Test
    public void SpyTest() {
        // mock creation
        List mockedList = spy(LinkedList.class);

        when(mockedList.size()).thenReturn(1);
        // using mock object - it does not throw any "unexpected interaction" exception
        mockedList.add("one");
        mockedList.add("two");

        // selective, explicit, highly readable verification
        verify(mockedList).add("one");
        verify(mockedList).add("two");
        assertThat(mockedList.size(), is(1));
    }
}
