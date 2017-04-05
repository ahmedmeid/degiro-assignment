package nl.degiro.verweggistan;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Trade {
	
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int Z = scn.nextInt();
       do
        {
		    List<List<Integer>> piles = new ArrayList<>();
			int E =0;
			for(int i=0;i<Z;i++)
			{
				E = scn.nextInt();
				List<Integer> pile = new ArrayList<>();
				for(int j=0;j<E;j++)
				{
					pile.add(scn.nextInt());
				}
				piles.add(pile);
			}
			
			List<List<Integer>> result = new ArrayList<>();
			int maxProfit = 0;
			int[] maxProfits = new int[Z];
			for(int k=0;k<piles.size();k++)
			{
				List<Integer> subResult = new ArrayList<>();
				int[] arr = new int[1];
				getMaxProfitForPile(piles.get(k), subResult, arr);
				maxProfit += arr[0];
				maxProfits[k] = maxProfit;
				result.add(subResult);
			}
			
            HashSet<Integer> purlsTobuy = new HashSet<>();
            
            generatePermutations(result, purlsTobuy, 0, 0);
           
            
            int counter = 0;
            Iterator<Integer> iter = purlsTobuy.iterator();
            StringBuffer pruls = new StringBuffer("");
            while(counter < 10 && iter.hasNext())
            {
            	pruls.append(iter.next()+" ");
            	counter++;
            }
					
			System.out.println("Workyards "+Z);
			System.out.println("Maximum profit is "+maxProfit);
			System.out.println("Number of pruls to buy: "+pruls);
			System.out.println();
			Z = scn.nextInt();
        } while(Z > 0);
        scn.close();
	}
	
	public static void getMaxProfitForPile(List<Integer> pile, List<Integer> result, int[] maxProfitArray)
	{
		int sum = 0, profit = 0;
		int maxProfit = 0;
		int[] profitArray = new int[pile.size()];
		for(int i=0;i<pile.size();i++)
		{
           sum += pile.get(i);
           profit = (10 * (i+1)) - sum;
           profitArray[i] = profit;
           maxProfit = Math.max(maxProfit, profit);
		}

        maxProfitArray[0] = maxProfit;
        for(int j=0;j<profitArray.length;j++)
        {
        	if(profitArray[j] == maxProfit)
        	{
        		int purls = j + 1;
        		result.add(purls);
        	}
        }   
	}
	
	public static void generatePermutations(List<List<Integer>> Lists, HashSet<Integer> result, int depth, Integer current)
	{
	    if(depth == Lists.size())
	    {
	       result.add(current);
	       return;
	     }

	    for(int i = 0; i < Lists.get(depth).size(); ++i)
	    {
	        generatePermutations(Lists, result, depth + 1, current + Lists.get(depth).get(i));
	    }
	}
   
}
