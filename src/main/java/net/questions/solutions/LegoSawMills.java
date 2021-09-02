package net.questions.solutions;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LegoSawMills {
    /*
    •	Sawn wood of length of 1 has a profit of -1.
    •	Sawn wood of length 2 has a profit of +3.
    •	sawn wood of length 3 can still be sold for +1
   * */
    private final static Map<Integer,Integer> PROFITS = new HashMap<>(){{
        put(0,0);
        put(1, -1);
        put(2, 3);
        put(3, 1);
    }};

    private final static int THREE = 3;

    public LegoSawMills(String[] inputs) {
        for (int i = 0;i<inputs.length;i++) {
            //TODO get inputs form string by parsing the integers and the block array
            // and perform iteratively the below function
            System.out.println("Case " + (i+1));
            int[][] eLengths = new int[2][3];   //TODO derived from the input array
            calculateTotalMaxProfit(eLengths);
        }
    }

    public LegoSawMills() {
    }

    public int calculateTotalMaxProfit(int[][] eLengths){
        int totalProfit=0;
        for (int i = 0; i < eLengths.length; i++) {
            List<LengthOrderProfits> profits = calculateMaxProfitsTrunkOrder(eLengths[i]);

            profits.forEach(p->{
                System.out.print("[");
                p.trunkOrder.forEach(t->System.out.print(t+", "));
                System.out.print("], ");
            });

            totalProfit += profits.get(0).profit;
        }
        return totalProfit;
    }

    public List<LengthOrderProfits> calculateMaxProfitsTrunkOrder(int[] eLengths){
        List<LengthOrderProfits> result = new ArrayList<>();
        int maxProfit=Integer.MIN_VALUE;
        Set<List<Integer>> allPermutations = permute(eLengths);
        for (List<Integer> blockLengths:allPermutations) {
            int profit = calculateProfit(blockLengths);
            if(profit>maxProfit)
                maxProfit = profit;
            result.add(new LengthOrderProfits(blockLengths,profit));
        }

        final int maxProfitFinal = maxProfit;
        return result.stream().filter(f->f.profit == maxProfitFinal).collect(Collectors.toList());
    }

    public Set<List<Integer>> permute(int[] arr) {
        List<List<Integer>> list = new ArrayList<>();
        int[] indexes = new int[arr.length];
        IntStream.range(1,arr.length+1).forEach(val -> indexes[val-1] = val-1);
        permuteHelper(list, new ArrayList<>(), indexes);
        Set<List<Integer>> result = new HashSet<>();
        for (List<Integer> l: list) {
            List<Integer> blocks = new ArrayList<>(l.size());
            for (Integer i: l) {
                blocks.add(arr[indexes[i]]);
            }
            result.add(blocks);
        }
        return result;
    }

    private void permuteHelper(List<List<Integer>> list, List<Integer> resultList, int [] arr){

        // Base case
        if(resultList.size() == arr.length){
            list.add(new ArrayList<>(resultList));
        }
        else{
            for(int i = 0; i < arr.length; i++){

                if(resultList.contains(arr[i]))
                {
                    // If element already exists in the list then skip
                    continue;
                }
                // Choose element
                resultList.add(arr[i]);
                // Explore
                permuteHelper(list, resultList, arr);
                // Unchoose element
                resultList.remove(resultList.size() - 1);
            }
        }
    }

    public int calculateProfit(List<Integer> blockLengths){
        int profit = 0;
        int remainder = 0; // 0<= remainder < 3
        int tempLength = 0;
        for (int i = 0; i < blockLengths.size(); i++) {
            profit += PROFITS.get(remainder);

            if(blockLengths.get(i) < THREE){
                remainder = blockLengths.get(i);
                continue;
            }
            tempLength = blockLengths.get(i);
            if (remainder>0) {
                profit += PROFITS.get(THREE - remainder);
                tempLength =tempLength - (THREE - remainder);
            }
            remainder = tempLength % THREE;
            profit += PROFITS.get(THREE) * ((tempLength - remainder) / THREE);
        }

        profit += PROFITS.get(remainder);
        return profit;
    }
}

class LengthOrderProfits{
    public List<Integer> trunkOrder;
    public int profit;

    public LengthOrderProfits(List<Integer> trunkOrder, int profit) {
        this.trunkOrder = trunkOrder;
        this.profit = profit;
    }

    public LengthOrderProfits() {
    }
}
