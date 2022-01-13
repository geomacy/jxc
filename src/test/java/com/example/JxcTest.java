package com.example;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class JxcTest {

    @Test
    public void testProcess() throws IOException {
        Map<String, Integer> expected = new HashMap<>();
        expected.put("alice", 1);
        expected.put("cried", 1);
        expected.put("and", 1);
        expected.put("curiouser", 2);

        Map<String, Integer> actual = new HashMap<>();
        InputStream data = getClass().getClassLoader().getResourceAsStream("com/example/data.txt");
        Jxc.process(data, actual);
        assertEquals(expected, actual);
    }

    @Test
    public void testProcessAll() throws InterruptedException {
        Map<String, Integer> expected = new HashMap<>();
        expected.put("alice", 2);
        expected.put("cried", 2);
        expected.put("and", 2);
        expected.put("curiouser", 4);

        Map<String, Integer> actual = new HashMap<>();
        InputStream first = getClass().getClassLoader().getResourceAsStream("com/example/data.txt");
        InputStream second = getClass().getClassLoader().getResourceAsStream("com/example/data.txt");
        Jxc.processAll(new InputStream[]{first}, actual);
        Thread.sleep(1000);
        Jxc.processAll(new InputStream[]{second}, actual);
        Thread.sleep(1000);
        for (Map.Entry<String, Integer> e : actual.entrySet()) {
            System.out.printf("%s: %d\n", e.getKey(), e.getValue());
        }
        assertEquals(expected, actual);
    }
}
