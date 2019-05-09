package cn.peng.studygodpath.java8.io;

import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ConsoleRead {


    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                String oneStr = reader.readLine();
                String twoStr = reader.readLine();
                Integer one = Integer.valueOf(oneStr);
                System.out.println(Math.pow(one, 2));
                System.out.println(Math.pow(one, 3));
                Integer two = Integer.valueOf(twoStr);
                System.out.println(Math.pow(two, 2));
                System.out.println(Math.pow(two, 3));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
