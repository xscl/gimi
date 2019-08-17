package com.selfspring.gimi.jv8.br;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by ckyang on 2019/7/7.
 */
public class br {
    //old
    public static String processFile() throws IOException {
        try (BufferedReader br =
                     new BufferedReader(new FileReader("data.txt"))) {
            return br.readLine();
        }
    }

    //new
    public static String processFile(BufferedReaderProcessor p) throws IOException {
        return p.process(new BufferedReader(new FileReader("data.txt")));
    }

    public static void main(String[] args) throws IOException {
        processFile((br) -> br.readLine());
        processFile((br) -> br.readLine() + br.readLine());
    }
}
