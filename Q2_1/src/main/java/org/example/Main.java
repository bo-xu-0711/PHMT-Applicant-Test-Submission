package org.example;

import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        MyFileReader reader = new MyFileReader();
        InputStream is = reader.getFileFromResourceAsStream("example.txt");
        reader.printInputStream(is);
    }
}