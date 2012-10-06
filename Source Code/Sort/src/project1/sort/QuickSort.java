//package project1.sort;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author KimDinhSon
 */
public class QuickSort {
        long time=0,begin=0,end=0;
        public void sortNumbers(int[] list){
            begin=System.nanoTime();
            Quicksort(list, 0,list.length-1);
            end=System.nanoTime();
            time=(end-begin);
	}
        
	public void Quicksort(int[] A, int start, int end){
		if (start < end){
			
			int middle = Partition(A, start, end);
			Quicksort(A, start, middle-1);
			Quicksort(A, middle+1, end);
		}
	}
 
	public int Partition (int[] A, int start, int end){
		int pivot = A[end]; 
		int lo = start; 
 
		for (int hi = start; hi < end; hi++){
			if (A[hi] <= pivot){				
				int temp = A[lo];
				A[lo] = A[hi];
				A[hi] = temp;
                                lo++;
			}
		}
		int temp = A[lo];
		A[lo] = A[end];
		A[end] = temp;
 
		return lo;
	}
        
         public void outWithQuick(String filename,int[] A) throws IOException {
            BufferedWriter bufferedWriter = null;       
            try {          
                bufferedWriter = new BufferedWriter(new FileWriter(filename));       
                for(int i=0;i<A.length;i++){
                bufferedWriter.write(A[i] +" ");            
                }
            } catch (FileNotFoundException ex) {
                System.err.printf("...", ex);
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

	public static void main(String[] args){
		int[] list = new int[100000];
                File file = new File("test.txt");
                        try{
                        Scanner input;
                        try (FileInputStream is = new FileInputStream(file)) {
                            input = new Scanner(is,"UTF-8");
                            int n = 0;
                            while(input.hasNextInt()) {
                                //A.add(input.nextInt());
                                list[n]=(int)input.nextInt();
                                n++;
                            }
                        }
                        input.close();
                        }catch(FileNotFoundException e){
                            System.out.println("File not found" + e);
                        }catch(IOException ioe){
                            System.out.println("Exception while reading the file " + ioe);
                        }
		QuickSort qs = new QuickSort();
		qs.sortNumbers(list);
 
		for (int i = 0;i<list.length;i++){
			System.out.print(list[i]+" ");
                        if(i%30==0) System.out.println();
		}
	}


}
