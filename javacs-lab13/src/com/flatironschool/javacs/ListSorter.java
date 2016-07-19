/**
 * 
 */
package com.flatironschool.javacs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Provides sorting algorithms.
 *
 */
public class ListSorter<T> {

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void insertionSort(List<T> list, Comparator<T> comparator) {
	
		for (int i=1; i < list.size(); i++) {
			T elt_i = list.get(i);
			int j = i;
			while (j > 0) {
				T elt_j = list.get(j-1);
				if (comparator.compare(elt_i, elt_j) >= 0) {
					break;
				}
				list.set(j, elt_j);
				j--;
			}
			list.set(j, elt_i);
		}
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void mergeSortInPlace(List<T> list, Comparator<T> comparator) {
		List<T> sorted = mergeSort(list, comparator);
		list.clear();
		list.addAll(sorted);
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * Returns a list that might be new.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	
	//merges a sublist into final sort
	public List<T> mergeSortRec(List<T> list, Comparator<T> comparator, int rcount){
		System.out.println("YAY RECURSION!!!111!!1!!: " + rcount);
		if (list.size() == 1){
			return list;
		}
		
		List<T> final_list = new ArrayList<T>();
		List<T> print_list = new ArrayList<T>();
		for(int i = 0; i<list.size(); i++){
			print_list.add(list.get(i));
		}
		System.out.println(print_list);
		
		
		int start = 0;
        int middle = print_list.size()/2;
        System.out.println(middle);
        
        int end = print_list.size() -1;
        System.out.println(end);
        
        List<T> list_1 = print_list.subList(start, middle);
        System.out.println("LIST1:" + list_1);
        
        List<T> list_2 = print_list.subList(middle, end+1);
        System.out.println("LIST2: "+ list_2);
        
        System.out.println("AFTER SORTING");
        
        System.out.println("Get ready for recursion: "+ (rcount+1));
        list_1 = mergeSortRec(list_1,comparator, rcount + 1);
        System.out.println("Yay you did recursion: "+ rcount);
        System.out.println("LIST1:" + list_1);
        System.out.println("Get ready for recursion: "+ (rcount+1));
        list_2 = mergeSortRec(list_2,comparator, rcount + 1);
        System.out.println("Yay you did recursion: "+ rcount);
        System.out.println("LIST2: "+ list_2);
        
        int i = 0;
    	int j = 0;
        
        for(int k=start; k<=end; k++ )
        {
//        	System.out.println("I: " + i);
//        	System.out.println("J: " + j);
        	T element_1 = list_1.get(i);
        	T element_2 = list_2.get(j);
//        	System.out.println("Element of list 1: " + element_1);
//        	System.out.println("Element of list 2: " + element_2);
        	if(comparator.compare(element_1,element_2) >= 0){
//        		System.out.println("WENT TO IF: " + k);
        		final_list.add(k, element_2);
        		j = j+1;
        		if (j == list_2.size())
        		{
//        			System.out.println("MADE IT HERE");
        			for (int a= i; a<list_1.size(); a++ )
        			{
        			final_list.add(list_1.get(a));
        			}
        			break;
        		}
        			
//        		System.out.println("FINAL_LIST IS NOW: " + final_list);
        		
        	}
        	else
        	{
//        		System.out.println("WENT TO ELSE: " + k);
        		final_list.add(k, element_1);
//        		System.out.println("MADE IT HERE");
        		i = i+1;
        		if (i == list_1.size()){
        			System.out.println("MADE IT HERE");
//        			final_list.add(end,list_2.get(middle-1));
        			if (i == list_1.size()){
//            			System.out.println("MADE IT HERE");
            			for (int a= j; a<list_2.size(); a++ ){
            			final_list.add(list_2.get(a));
            			}
        			break;
        			}
//        		System.out.println("FINAL_LIST IS NOW: " + final_list);
        		}
        
        	
        
        	}
        }
        System.out.println("FINAL LIST" + final_list);
   
        return final_list;
	}
		
	
	public List<T> mergeSort(List<T> list, Comparator<T> comparator) {
		List<T> work_list = new ArrayList<T>();
		List <T> final_list = mergeSortRec(list, comparator, 0);
		return final_list;
		}
		
	

	
	
	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void heapSort(List<T> list, Comparator<T> comparator) {
		
		PriorityQueue<T> sort_queue = new PriorityQueue<T>(comparator);
		List<T> final_list = new ArrayList<T>();
		for (T element: list){
			sort_queue.offer(element);
		}
		for (int i= 0; i< list.size(); i++){
			final_list.add(sort_queue.poll());
		}
	
		for (int j = 0; j<list.size(); j++){
			T element = final_list.get(j);
			list.set(j, element);
		}
		

	}

	
	/**
	 * Returns the largest `k` elements in `list` in ascending order.
	 * 
	 * @param k
	 * @param list
	 * @param comparator
	 * @return 
	 * @return
	 */
	public List<T> topK(int k, List<T> list, Comparator<T> comparator) {
        heapSort(list,comparator);
        int size = list.size() - k;
 
        list = list.subList(size, list.size());
 
        return list;
		
        
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		
		Comparator<Integer> comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer n, Integer m) {
				return n.compareTo(m);
			}
		};
		
		ListSorter<Integer> sorter = new ListSorter<Integer>();
		sorter.insertionSort(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.mergeSortInPlace(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.heapSort(list, comparator);
		System.out.println(list);
	
		list = new ArrayList<Integer>(Arrays.asList(6, 3, 5, 8, 1, 4, 2, 7));
		List<Integer> queue = sorter.topK(4, list, comparator);
		System.out.println(queue);
	}
}
