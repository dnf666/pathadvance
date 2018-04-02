//package com.path.src.util;
//
//import java.io.FileNotFoundException;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Random;
//import java.util.Scanner;
//
//public class Arrays {
//    public static int[] getRangeArray(int from, int to){
//        int length = to - from + 1;
//        int[] array = new int[length];
//        for(int i = 0; i< length; i++){
//            array[i] = from++;
//        }
//        return array;
//    }
//
//
//    public static void main(String[] args) {
////            Scanner scanner= new Scanner(System.in);
////            int size = scanner.nextInt();
////            int array[] = new int[size];
////            for (int i = 0; i < size; i++) {
////                for(int j = 0;j< array.length;j++)
////                    array[i] = scanner.nextInt();
////            }
//            int matrix[][] = {{1,2,3},{4,5,6},{7,8,9}};
//            int n = matrix.length;
//            int limit = (n-1)/2;
//            for (int i = 0; i <= limit; i++) {
//                for (int j = i; j <n-1-i ; j++) {
//                    int temp = matrix[i][j];
//                    matrix[i][j] = matrix[n-1-j][i];
//                    matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
//                    matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
//                    matrix[j][n-1-i] = temp;
//                }
//            }
//            for(int i = 0 ; i<matrix.length;i++){
//                for(int j = 0;j<matrix[i].length;j++){
//                    System.out.print(matrix[i][j]);
//                }
//                System.out.println();
//            }
//    }
//}
//    Scanner scanner= new Scanner(System.in);
//    int size = scanner.nextInt();
//    int matrix[][] = new int[size][size];
//        for (int i = 0; i < size; i++) {
//        for(int j = 0;j< matrix.length;j++)
//        matrix[i][j] = scanner.nextInt();
//        }