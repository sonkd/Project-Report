//package project1.sort;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author KimDinhSon
 */
public class MergeSort {
        long time=0,begin=0,end=0;
	private static void Merge(int[] A, int[] temp, int lo, int mid, int hi){
		int i = lo;
		int j = mid;
 
		for (int k = lo; k < hi; k++){
			if (i == mid) temp[k] = A[j++];// Neu mang lo-mid rong
			else if (j == hi) temp[k] = A[i++]; // Neu mang mid-hi rong
			else if (A[j] > A[i]) temp[k] = A[i++]; //Neu A[i] i nho hon, gan no cho temp[i];
			else temp[k] = A[j++]; //Neu nguoc lai, gan A[j] vao day dau <lo-mid>
		}
                //Gan lai Mang A boi mang temp
		for (int k = lo; k < hi; k++)
			A[k] = temp[k];
 
	}
 
	private static void MergeSort(int[] A, int lo, int hi){
		if (hi - lo == 1) return;
		int mid = lo + (hi - lo) / 2;
		MergeSort(A, lo, mid);// Sort nua dau
		MergeSort(A, mid, hi);// Sort nua cuoi
		Merge(A, new int[A.length], lo, mid, hi);// Tron hai phan lai
	}
	public void sortNumbers(int[] A){
                begin=System.nanoTime();
		MergeSort(A, 0, A.length);
                end=System.nanoTime();
                time=(end-begin);
	}
    public void outWithMerge(String filename,int[] A) throws IOException {
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
}