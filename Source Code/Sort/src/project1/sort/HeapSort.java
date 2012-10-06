//package project1.sort;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class HeapSort {

	private static int heapSize;
        long time=0,begin=0,end=0;
	public void sortNumbers(int[] A){
            begin=System.nanoTime();
            HeapSort(A);
            end=System.nanoTime();
            time=(end-begin);         
	}
	private void HeapSort(int[] A){
		heapSize = A.length; 
		BuildMaxHeap(A); 
		for (int i = A.length-1; i>0; i--){
			int temp = A[0];
			A[0] = A[i];
			A[i] = temp;
			heapSize--;
			MaxHeapify(A,0);
		}
	}
	private void BuildMaxHeap(int[] A){
		for(int i = A.length/2-1;i>=0;i--){
			MaxHeapify(A, i);
		}
	}
	private void MaxHeapify(int[] A, int i){
		int l = leftChild(i); 
		int r = rightChild(i);
		int max;
		if (l < heapSize){
			if (A[l] > A[i]){ 
				max = l; 
			}
			else {
				max = i;
			}
		}
		else {
			max = i;
		}
 
		if (r < heapSize){
			if(A[r] > A[max]){ 
				max = r;
			}
		}
 
		if (max != i){ 
			int temp = A[i];
			A[i] = A[max];
			A[max] = temp;

			MaxHeapify(A, max);
		}
	}
 
	private int leftChild(int i){
		return 2 * i;
	}
 
	private int rightChild(int i){
		return (2 * i) + 1;
	}
        public void outWithHeap(String filename, int[] A) throws IOException {
            BufferedWriter bufferedWriter = null;       
        try {          
            //Construct the BufferedWriter object
            bufferedWriter = new BufferedWriter(new FileWriter(filename));       
            //Start writing to the output stream
            for(int i=0;i<A.length;i++){
               bufferedWriter.write(A[i]+" ");            
            }
            
        } catch (FileNotFoundException ex) {
        } finally {
          try {
                if (bufferedWriter != null) {
                    bufferedWriter.flush();
                    bufferedWriter.close();
                }
          } catch (IOException ex) {
              System.err.printf("...", ex);
          }
        }
       }
       /*
       public static void main(String[] args){
		int[] list = {187, 187, 146, 60, 11, 156, 143 ,101 ,151, 180, 64, 10 ,24 ,199 ,163, 25 ,139 ,47 ,138, 162 };
		HeapSort hs = new HeapSort();
		hs.sortNumbers(list);
 
		for (int i = 0;i<list.length;i++){
			System.out.println(list[i]);
		}
        }
        * 
        */
}