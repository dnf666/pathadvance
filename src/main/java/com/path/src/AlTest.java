//package com.path.src;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class AlTest {
//    GeneticAl al = new GeneticAl();
//
//    public void beforeTest(){
//        al.setServices(1001, 1004);
//    }
//
//    public void getOneRouteTest(){
//        ArrayList<Integer> list = al.getOneRoute();
//        list.forEach(System.out::println);
//    }
//
//    public void getOneSolutionTest(){
//        Solution solution = al.getOneSolution();
//        final int[] i = new int[]{1};
//        solution.getRoutes().forEach(route -> {
//            System.out.println("第" + i[0] + "组：");
//            i[0] = i[0] + 1;
//            route.forEach(System.out::println);
//        });
//    }
//
//    public void initailTest(){
//        int[] i = new int[1];
//        i[0] = 1;
//        al.initial();
//        al.getSolutions().forEach(solution -> {
//            System.out.println("第" + i[0] + "种方案");
//            i[0] = i[0] + 1;
//            int[] a = new int[1];
//            a[0] = 1;
//            solution.getRoutes().forEach(route ->{
//                System.out.println("第" + a[0] + "组：");
//                a[0] = a[0] + 1;
//                route.forEach(System.out::println);
//            });
//        });
//    }
//
//    public void getSumOfDistanceTest(){
//        System.out.println(al.getSumOfDistance());
//    }
//
//    public void setAllSolutionsProbabilityTest(){
//        al.setAllSolutionsProbability();
//    }
//
//    public void testCross(){
//        if (!al.cross(al.getOneRandomSolution()))
//            System.out.println("没有进行变异");
//        else
//            System.out.println("进行了变异");
//    }
//
//    public void mutationTest(){
//        if (!al.mutation(al.getOneRandomSolution()))
//            System.out.println("没有进行变异");
//        else
//            System.out.println("进行了变异");
//    }
//
//    public GeneticAl getAl() {
//        return al;
//    }
//
//    public static void main(String[] args) throws IOException, InterruptedException {
//        AlTest test = new AlTest();
//        GeneticAl al = test.getAl();
//        List<Solution> solutions = al.getSolutions();
//        test.beforeTest();
//        test.getAl().initial();
//        test.setAllSolutionsProbabilityTest();
//        long startTime = System.currentTimeMillis();
////        al.cross(al.getOneRandomSolution());//16
//        al.mutation(al.getOneRandomSolution());
//        System.out.println("cross方法的时间为：" + String. valueOf(System.currentTimeMillis() - startTime));
//        Thread.sleep(1000);
//
//    }
//}
