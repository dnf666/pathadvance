//package com.path.src;
//
//import util.MList;
//import util.MRandom;
//
//import java.io.IOException;
//import java.util.*;
//
//import static util.MCollections.getNewArrayListFromCollection;
//
//public class GeneticAl {
//
//    private List<Integer> servicesList;
//    private int servicesListStartIndex;
//    private int servicesListEndIndex;
//    private List<Solution> solutions = new LinkedList<>();
//
//    private static final int START_PLACE = 0;
//    public static final int SUM_OF_SERVICE_LIST = 50;
//
//    public List<Solution> getSolutions() {
//        return solutions;
//    }
//
//    public void setServices(int fromIndex, int toIndex) {
//        servicesListStartIndex = fromIndex;
//        servicesListEndIndex = toIndex;
//        servicesList = new LinkedList<>();
//        for (; fromIndex <= toIndex; fromIndex++) {
//            servicesList.add(fromIndex);
//        }
//    }
//
//    // 初始化
//    //得到50个方案
//    public void initial() {
//        for (int i = 1; i <= 50; i++) {
//            Solution solution = getOneSolution();
//            solutions.add(solution);
//        }
//    }
//
//    //得到一个方案，一个方案是覆盖了所有的服务点
//    public Solution getOneSolution() {
//        Solution solution = new Solution();
//        servicesList = MList.getRangeIntLinkedList(servicesListStartIndex, servicesListEndIndex);
//        while (servicesList.size() != 0) {
//            ArrayList<Integer> route = getOneRoute();
//            solution.getRoutes().add(route);
//        }
//        return solution;
//    }
//
//    // 路径规划一条路线
//    public ArrayList<Integer> getOneRoute() {
//        ArrayList<Integer> route = null;
//
//        if (servicesList.size() == 1) {
//            route = new ArrayList<>();
//            route.add(servicesList.get(0));
//            servicesList.remove(0);
//        } else {
//            int numOfServices = MRandom.getRangeRandomInt(1, servicesList.size() + 1);
//            if (numOfServices <= servicesList.size()) {
//                route = new ArrayList<>(numOfServices);
//                for (int i = 0; i < numOfServices; i++) {
//                    int index = MRandom.getRangeRandomInt(0, servicesList.size());
//                    route.add(servicesList.get(index));
//                    servicesList.remove(index);
//                }
//            } else {
//                for (int i = 0; i < (servicesList.size() - 1); i++) {
//                    route = new ArrayList<>(servicesList.size());
//                    route.add(servicesList.get(i));
//                    servicesList.remove(i);
//                }
//            }
//        }
//        return route;
//    }
//
//    //轮盘赌
//    //给每个方案赋值一个优先级
//    public void setAllSolutionsProbability() {
//        solutions.forEach(this::setOneSolutionProbability);
//        Collections.sort(solutions);
//    }
//
//    private void setOneSolutionProbability(Solution solution) {
//        solution.setProbability(1 / (getDistance(solution) / getSumOfDistance()));
//    }
//
//    public float getSumOfDistance() {
//        final float[] distance = {0};
//        solutions.forEach(solution -> distance[0] += getDistance(solution));
//        return distance[0];
//    }
//
//    private float getDistance(Solution solution) {
//        final float[] distance = {0};
//        for (int i = 0; i < solution.getRoutes().size(); i++) {
//
//            List<Integer> route = solution.getRoutes().get(i);
//            distance[0] += getDistanceOfTowService(START_PLACE, route.get(0));
//
//            for (int j = 0; j < route.size(); j++) {
//                if (j <= route.size() - 2) {
//                    distance[0] += getDistanceOfTowService(solution.getRoutes().get(i).get(j),
//                            solution.getRoutes().get(i).get(j + 1));
//                }
//            }
//            distance[0] += getDistanceOfTowService(route.get(route.size() - 1), START_PLACE);
//        }
//        return distance[0];
//    }
//
//    private float getDistanceOfTowService(int from, int to) {
//        try {
//            if (from == 1001 && to == 1002)
//                return 25f;
//            else if (from == 1001 && to == 1003)
//                return 35f;
//            else if (from == 1001 && to == 1004)
//                return 52f;
//            else if (from == 1002 && to == 1003)
//                return 11f;
//            else if (from == 1002 && to == 1004)
//                return 40f;
//            else if (from == 1003 && to == 1004)
//                return 78f;
//            else if (from == 1002 && to == 1001)
//                return 31f;
//            else if (from == 1003 && to == 1001)
//                return 22f;
//            else if (from == 1004 && to == 1001)
//                return 101f;
//            else if (from == 1003 && to == 1002)
//                return 29f;
//            else if (from == 1004 && to == 1002)
//                return 10f;
//            else if (from == 1004 && to == 1003)
//                return 152f;
//            else if (from == START_PLACE && to == 1001)
//                return 103f;
//            else if (from == START_PLACE && to == 1002)
//                return 99f;
//            else if (from == START_PLACE && to == 1003)
//                return 82f;
//            else if (from == START_PLACE && to == 1004)
//                return 44f;
//            else if (from == 1001 && to == START_PLACE)
//                return 21f;
//            else if (from == 1002 && to == START_PLACE)
//                return 66f;
//            else if (from == 1003 && to == START_PLACE)
//                return 88f;
//            else if (from == 1004 && to == START_PLACE)
//                return 133f;
//            else
//                throw new Exception("给的服务点有误！");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        }
//    }
//
//    //选择
//    //随机选择一个优先级比较大方案出来准备交叉和变异
//    public Solution getOneRandomSolution() {
//        int oneOfSolution = MRandom.getRangeRandomInt(0, SUM_OF_SERVICE_LIST);
//        return solutions.get(oneOfSolution);
//    }
//
//    //交叉
//    public boolean cross(Solution solution) {
//        //确定几率进行变异
///*        if (!checkIfInherited()){
//            return false;
//        }*/
//        //随机选择两个route， 准备进行交叉
//        int crossFromRouteIndex = MRandom.getRangeRandomInt(0, solution.getRoutes().size());
//        int crossToRouteIndex;
//        if (solution.getRoutes().size() != 1){
//            do {
//                crossToRouteIndex = MRandom.getRangeRandomInt(0, solution.getRoutes().size());
//            } while (crossFromRouteIndex == crossToRouteIndex);
//        }else
//            return false;
//
//        List<Integer> fromRoute = solution.getRoutes().get(crossFromRouteIndex);
//        List<Integer> toRoute = solution.getRoutes().get(crossToRouteIndex);
//
//        //从fromRoute里面选择两个点，准备截取这两个点之间
//        int crossFromRouteOneIndex = MRandom.getRangeRandomInt(0, fromRoute.size());
//        int crossFromRouteTwoIndex;
//        if (fromRoute.size() != 1) {
//            do {
//                crossFromRouteTwoIndex = MRandom.getRangeRandomInt(0, fromRoute.size());
//            } while (crossFromRouteOneIndex == crossFromRouteTwoIndex);
//        } else
//            crossFromRouteTwoIndex = 0;
//
//        //从toRoute里面选择两个点，准备截取这两个点之间呢
//        int crossToRouteOneIndex = MRandom.getRangeRandomInt(0, toRoute.size());
//        int crossToRouteTwoIndex;
//        if (toRoute.size() != 1) {
//            do {
//                crossToRouteTwoIndex = MRandom.getRangeRandomInt(0, toRoute.size());
//            } while (crossToRouteOneIndex == crossToRouteTwoIndex);
//        } else
//            crossToRouteTwoIndex = 0;
//
//        //确定从fromRoute和toRoute分别选取的两个点的先后关系
//        int crossFromRouteFromIndex;
//        int crossFromRouteToIndex;
//        if (crossFromRouteOneIndex < crossFromRouteTwoIndex) {
//            crossFromRouteFromIndex = crossFromRouteOneIndex;
//            crossFromRouteToIndex = crossFromRouteTwoIndex;
//        } else {
//            crossFromRouteFromIndex = crossFromRouteTwoIndex;
//            crossFromRouteToIndex = crossFromRouteOneIndex;
//        }
//
//        int crossToRouteFromIndex;
//        int crossTORouteTOIndex;
//        if (crossToRouteOneIndex < crossToRouteTwoIndex) {
//            crossToRouteFromIndex = crossToRouteOneIndex;
//            crossTORouteTOIndex = crossToRouteTwoIndex;
//        } else {
//            crossToRouteFromIndex = crossToRouteTwoIndex;
//            crossTORouteTOIndex = crossToRouteOneIndex;
//        }
//
//        List<Integer> newFromRoute = getNewArrayListFromCollection(fromRoute);
//        List<Integer> newToRoute = getNewArrayListFromCollection(toRoute);
//
//        //确定需要交叉的内容
//        ArrayList<Integer> fromRouteCrossArray = new ArrayList<>((crossFromRouteToIndex - crossFromRouteFromIndex));
//        ArrayList<Integer> toRouteCrossArray = new ArrayList<>(crossTORouteTOIndex - crossToRouteFromIndex);
//        int k = crossFromRouteFromIndex;
//        int j = crossToRouteFromIndex;
//        for (; k <= crossFromRouteToIndex; k++)
//            fromRouteCrossArray.add(newFromRoute.get(k));
//        for (; j <= crossTORouteTOIndex; j++)
//            toRouteCrossArray.add(newToRoute.get(j));
//
//        //进行交叉
//        newFromRoute.addAll(crossFromRouteFromIndex, toRouteCrossArray);
//        newFromRoute.removeAll(fromRouteCrossArray);
//        newToRoute.addAll(crossToRouteFromIndex, fromRouteCrossArray);
//        newToRoute.removeAll(toRouteCrossArray);
//
//        Solution newSolution = new Solution();
//        List<List<Integer>> routes = getNewArrayListFromCollection(solution.getRoutes());
//        routes.set(crossFromRouteIndex, newFromRoute);
//        routes.set(crossToRouteIndex, newToRoute);
//        newSolution.setRoutes(routes);
//
//        solutions.add(newSolution);
//        setAllSolutionsProbability();
//
//        Collections.sort(solutions);
//        solutions.remove(0);
//        return true;
//    }
//
//    //变异
//    public boolean mutation(Solution solution) {
///*        if (!checkIfInherited()){
//            return false;
//        }*/
//        //确定变异的route
//        int mutationRouteIndex;
//        List<List<Integer>> routes = solution.getRoutes();
//        //如果所有route都只有一个服务点那么就不对这个方案进行变异
//        if (routes.stream().allMatch(route -> route.size() == 1)) {
//            return false;
//        }
//
//        do {
//            mutationRouteIndex = MRandom.getRangeRandomInt(0, solution.getRoutes().size());
//        } while (routes.get(mutationRouteIndex).size() == 1);
//
//        List<Integer> mutationRoute = getNewArrayListFromCollection(routes.get(mutationRouteIndex));
//
//        //确定变异的两个点
//        int mutationOneIndex = MRandom.getRangeRandomInt(0, mutationRoute.size());
//        int mutationTwoIndex;
//        do {
//            mutationTwoIndex = MRandom.getRangeRandomInt(0, mutationRoute.size());
//        } while (mutationOneIndex == mutationTwoIndex);
//
//        //进行变异交换
//        int temp;
//        temp = mutationRoute.get(mutationOneIndex);
//        mutationRoute.set(mutationOneIndex, mutationRoute.get(mutationTwoIndex));
//        mutationRoute.set(mutationTwoIndex, temp);
//
//        Solution newSolution = new Solution();
//        List<List<Integer>> newRoutes = getNewArrayListFromCollection(solution.getRoutes());
//
//        newRoutes.set(mutationRouteIndex, mutationRoute);
//        newSolution.setRoutes(newRoutes);
//
//        solutions.add(newSolution);
//        setAllSolutionsProbability();
//        Collections.sort(solutions);
//        solutions.remove(0);
//        return true;
//    }
//
//    public boolean checkIfInherited() {
//        Random random = new Random();
//        double n = random.nextDouble();
//        return n > 0.5;
//    }
//
//    //重复前面轮盘赌、选择、交叉、变异直到达到一个平衡点
//    //1000-2000
//    public void toTerminal() throws IOException, InterruptedException {
////        Thread thread = new Thread(() -> {
//            int i = 0;
//            long startTime = System.currentTimeMillis();
//            while (i++<500) {
//                mutation(getOneRandomSolution());
//                cross(getOneRandomSolution());
//                System.out.println();
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            long endTime = System.currentTimeMillis();
//
//
//
//            System.out.println(endTime - startTime);
//
//            /*solutions.forEach(solution -> {
//                System.out.println("solution");
//                System.out.println("路径距离:" + getSumOfDistance());
//                System.out.println("probability:" + solution.getProbability());
//                List<List<Integer>> routes = solution.getRoutes();
//                routes.forEach(route ->{
//                    System.out.println("route");
//                    for (int j = 0; j<route.size(); j++){
//                        System.out.print(route.get(j));
//                    }
//                    System.out.println();
//                });
//                System.out.println();
//            });*/
////        });
////        thread.start();
//      /*  while (true) {
//            *//*FileWriter writer = new FileWriter("/home/may/Documents/temp.txt");
//            writer.append(String.valueOf(solutions.get(40).getProbability()));
//            writer.append("\n");
//            writer.append(String.valueOf(solutions.get(41).getProbability()));
//            writer.append("\n");
//            writer.append(String.valueOf(solutions.get(42).getProbability()));
//            writer.append("\n");
//            writer.append("\n");
//            writer.append("\n");
//            writer.flush();*//*
//            System.out.println(solutions.get(40).getProbability());
//            System.out.println(solutions.get(41).getProbability());
//            System.out.println(solutions.get(42).getProbability());
//            System.out.println();
//            System.out.println();
//            Thread.sleep(10);
//        }*/
//    }
//}
