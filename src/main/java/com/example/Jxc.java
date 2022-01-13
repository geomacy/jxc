package com.example;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Jxc {
    public static void main(String[] args) {
        try {
            Map<String, Integer> counts = new HashMap<>();
            InputStream[] sources = new InputStream[args.length];
            for (int c = 0 ; c < args.length ; c++) {
                sources[c] = new FileInputStream(args[c]);
            }
            processAll(sources, counts);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void processAll(InputStream[] sources, Map<String, Integer> counts) {
        for (InputStream str : sources) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        process(str, counts);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            t.start();
        }
    }

    public static void process(InputStream input, Map<String, Integer> counts) throws IOException {
        try (
                InputStreamReader ir = new InputStreamReader(input);
                BufferedReader br = new BufferedReader(ir)
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if (!counts.containsKey(word)) {
                        counts.put(word, 0);
                    }
                    counts.put(word, counts.get(word) + 1);
                }
            }
        }
    }
}
