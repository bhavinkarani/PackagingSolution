package com.mobiquity.packer;

import com.mobiquity.vo.Item;

import java.util.*;

class PackerCalculator {

    String calculateBestSolution(int maxWeight, List<Item> items) {
        if(items.size() == 0 || maxWeight == 0 ){
            return "-";
        }
        items.sort(Comparator.comparing(Item::getWeight));
        Integer[] price = items.stream().map(Item::getPrice).toArray(Integer[]::new);
        Double[] wt = items.stream().map(Item::getWeight).toArray(Double[]::new);

        List<Integer> solutionItems = getMaxAndItemsToInclude(maxWeight, wt, price, items);
        Collections.sort(solutionItems);
        return getPrintableString(solutionItems);

    }

    /**
     * this method generates the printable solution in required format
     * @param solutionItems item indexes
     * @return printable format string
     */
    private String getPrintableString(List<Integer> solutionItems) {
        StringBuilder sb = new StringBuilder();
        if(solutionItems.size()==0){
            return "-";
        }else{
            for(Integer i :solutionItems){
                sb.append(i).append(",");
            }
        }
        return sb.substring(0, sb.length()-1);
    }

    /**
     * this method calculates the max profit
     * @param maxWeight maxWeight
     * @param wt weights
     * @param price prices
     * @param items all items
     * @return list of included items
     */
    private List<Integer> getMaxAndItemsToInclude(int maxWeight, Double[] wt, Integer[] price, List<Item> items) {
        int[][] holder = new int[price.length+1][maxWeight+1];
        for(int i=0; i <= price.length; i++){
            for(int j=0; j <= maxWeight; j++){
                if(i == 0 || j == 0){
                    holder[i][j] = 0;
                    continue;
                }
                if(j - wt[i-1] >= 0){
                    holder[i][j] = Math.max(holder[i-1][j], holder[i-1][j-wt[i-1].intValue()] + price[i-1]);
                }else{
                    holder[i][j] = holder[i-1][j];
                }
            }
        }
        System.out.println("max price possible is : "+ holder[price.length][maxWeight]);
        return getIncludedRows(maxWeight, price, items, holder);
    }

    /**
     * this method calculates item indexes which needs to be included in the solution.
     * @param maxWeight maxWeight
     * @param price prices
     * @param items items
     * @param K matrix obtained from previous step
     * @return list of included items
     */
    private List<Integer> getIncludedRows(int maxWeight, Integer[] price, List<Item> items, int[][] K) {
        List<Integer> includedIndexs = new ArrayList<>();
        int col = maxWeight;
        int row = price.length;

        while(row > 0 && col>0) {
            if (K[row][col] == K[row - 1][col]){ // if this value is copied from top then dont include this item
                row --;
            }else{
                //this row is included in the answer
                includedIndexs.add(items.get(row-1).getIndex());
                col = col - items.get(row-1).getWeight().intValue();
                row--;
            }

        }
        return includedIndexs;
    }
}
