//package project1.xephau;

import java.util.Scanner;

/**
 *
 * @author KimDinhSon
 */
public class Queen {
    final int max = 100;
    public int n,count=0;
    int[] array = new int[1000];
    /*
    int[] hang = new int[1000];

    int[] cheoR = new int[2000];
    int[] cheoL = new int[2000];
    
    public void init(){
        int i = 0;
        //System.out.print(n+"\n");
        count = 0;
        //Hang 1,2,3,..n
        
        for(i=0;i<n;i++){
            hang[i]=1;
        }
        //PT duong cheo: x+y={2,4,6,...,2n}
        for(i=0;i<2*n;i++){
            cheoR[i]=1;
        }
        //PT duong cheo: x-y={1-n,2-n,...,n-1}
        for(i=0;i<2*n-1;i++){
            cheoL[i]=1;
        }      
    }
        
    //Thu tung cot 1,2,3,...,n
    public void Try(int i){
        int j=0;
        for(j=0;j<n;j++){
            if(hang[j]==1&&cheoR[i+j]==1&&cheoL[j-i+n]==1){
                array[i]=j;
                //Ghi nhan trang thai moi
                hang[j]=0;cheoR[i+j]=0;cheoL[j-i+n]=0;
            }
            
            if(i==n-1){
                resulf();
            }
            else {
                Try(i+1);
            }
        }
    }
    
    * 
    */
    
    public void resulf(){
        int i=0;
        count++;
        System.out.print(count+". ");
        for(i=0;i<n;i++){
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }
    
    public boolean ungCuVien(int j, int k){
        for(int i = 0;i<k;i++){
            if(j==array[i]||j-array[i]==(k-i)||array[i]-j==(k-i)){
                return true;
            }
        }
        return false;
    }
    
    public void Try(int i){
        for(int j=0;j<n;j++){
            if(!ungCuVien(j,i)){
                array[i]=j;
                if(i==n-1){
                    resulf();
                }
                else {
                    Try(i+1);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Queen queen = new Queen();
        System.out.print(" Nhap kich thuoc ban co nxn\n n = ");
        int n = new Scanner(System.in).nextInt();
        System.out.print(" Ket qua: \n");
        //hau.count = 0; 
        queen.n=n;
        /*
        queen.init();
        * 
        */
        queen.Try(0);
        //System.out.print(queen.count+"\n");
        if(queen.count == 0) {
            System.out.print(" Khong co loi giai \n");
        }   
    }
}
