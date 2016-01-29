package com.sei.test;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DeDup {

	public int[] randomIntegers = {1,2,34,34,25,1,45,3,26,85,4,34,86,25,43,2,1,10000,11,16,19,1,18,4,9,3,
            20,17,8,15,6,2,5,10,14,12,13,7,8,9,1,2,15,12,18,10,14,20,17,16,3,6,19,
            13,5,11,4,7,19,16,5,9,12,3,20,7,15,17,10,6,1,8,18,4,14,13,2,11}; 

	public static void main(String[] args) {
		
		DeDup deDup = new DeDup();
		
		System.out.println("Original array \n"+ Arrays.toString(deDup.randomIntegers));
		
		Integer[] orgOrdrMaintain =  deDup.rmvDuplOrgOrdr(deDup);
		System.out.println("Method MrmvDuplOrgOrdr -- Duplicates remove + original order \n"+Arrays.toString(orgOrdrMaintain));
		
		int[] rmvDupArrSort =  deDup.rmvDuplicate(deDup);
		System.out.println("Method rmvDuplicate -- Duplicates remove using sorting \n"+Arrays.toString(rmvDupArrSort));
		
		int[] rmvDupArr = deDup.rmvDuplicate(deDup.randomIntegers);
		System.out.println("Method rmvDuplicate -- Duplicates remove using shifting \n"+Arrays.toString(rmvDupArr));
	}

	/**
	 *  Remove duplicates plus maintaining original order using java collection
	 *  
	 *  Positive Point -- code is more compact uses java collection.
	 *  Negative Point -- 
	 *  
	 * @param deDup
	 */
	protected Integer[] rmvDuplOrgOrdr(DeDup deDup){
		//convert to list, as list maintain insertion order.
		List<Integer> list = IntStream.of(deDup.randomIntegers).boxed().collect(Collectors.toList());

		//convert list to linkedHashSet, as it removes duplicates and maintain insertion order
		Set<Integer> linkHashSet = new LinkedHashSet<>(list);
		
		Integer[] array = linkHashSet.toArray(new Integer[linkHashSet.size()]);
	
		return array;
	}

	/**
	 * Remove duplicates by sorting
	 * 
	 * Negative point -- if array size increases it will take more time to remove duplicates. Considering more for-loop it is slow as compare to other functions. 
 	 * Positive point -- Used more familiar and simple syntax.
 	 * 
	 * @param deDup
	 * @return
	 */
	protected int[] rmvDuplicate(DeDup deDup){
		//sort array 
		Arrays.sort(deDup.randomIntegers);
		
		//iterate to remove duplicate elements.
		int j =0, i=1;
		int[] sortedArray = deDup.randomIntegers;
		while(i < sortedArray.length){
			if(sortedArray[i] == sortedArray[j]){
				i++;
			}else{
				sortedArray[++j] = sortedArray[i++];
			}
		}
	    int[] rmvDupArr = new int[j+1];
	    for(int k = 0; k < rmvDupArr.length; k++){
	    	rmvDupArr[k] = sortedArray[k];
	    }
	    return rmvDupArr;

	}

	
	/**
	 * This method takes int array and remove duplicates by shifting left every time finds duplicates in an array.
	 * for example int[] arr = {1,3,1,5,6,1}
	 * After shifting
	 * 1, 3, 5, 6, 1, 1 and value of arrEnd = 4,
	 * and after for loop completion rmvDupArr contains unique values.
	 * 
	 * Negative point -- if array size increases it will take more time to remove duplicates. Considering more for-loop it is slow as compare to other functions. 
 	 * Positive point -- Used more familiar and simple syntax.
 	 * 
 	 * @param randomArr
	 * @return
	 */
	protected int[] rmvDuplicate(int[] randomArr) {
	    
	    int arrEnd = randomArr.length;
	    for (int i = 0; i < arrEnd; i++) {
	        for (int j = i + 1; j < arrEnd; j++) {
	            if (randomArr[i] == randomArr[j]) {                  
	                int shiftLeft = j;
	                //shift rest of array to left if duplicate found
	                for (int k = j+1; k < arrEnd; k++, shiftLeft++) {
	                	randomArr[shiftLeft] = randomArr[k];
	                }
	                arrEnd--;
	                j--;
	            }
	        }
	    }

	    int[] rmvDupArr = new int[arrEnd];
	    for(int i = 0; i < arrEnd; i++){
	    	rmvDupArr[i] = randomArr[i];
	    }
	    
	    return rmvDupArr;
	}
}
