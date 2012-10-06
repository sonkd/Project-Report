
//package project1.sort;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author KimDinhSon
 */
public class Project1Sort {

    public static void main(String[] args) throws IOException {
        
        final InputStreamReader tInputStreamReader = new InputStreamReader(System.in);
        final BufferedReader tKeyboard = new BufferedReader(tInputStreamReader);
          
        System.out.println("CHUONG TRINH CAC THUAT TOAN SAP XEP CO BAN");
        System.out.println();
        boolean EXIT=false;
        int sizeArr = 0;
        long timeHeap = 0,timeMerge = 0,timeQuick = 0;// Bien gan dem thoi gian
        String fileName = null;
        while (!EXIT){
                
                int tOption=10;

                System.out.println();
                System.out.println(" 1 - Tao mang du lieu ngau nhien");
                System.out.println(" 2 - Sap xep Heap Sort, Merge Sort, Quick Sort");
                System.out.println(" 3 - Thoi gian");
                System.out.println(" 4 - Exit");
                
                System.out.print("=================================================\n");
                System.out.print("Chon thao tac: ");
                String tInput  = tKeyboard.readLine();
                System.out.println();
                if (tInput.equals("1")) {tOption = 1;}
                if (tInput.equals("2")) {tOption = 2;}
                if (tInput.equals("3")) {tOption = 3;}
                if (tInput.equals("4")) {tOption = 4;}
                System.out.println();
                switch (tOption)
                {
                    case 1:{
                        System.out.printf(" Nhap vao so phan tu cua mang: ");
                        sizeArr = new Scanner(System.in).nextInt();
                        System.out.printf("\n Nhap ten file khoi tao: ");
                        tInput  = tKeyboard.readLine();
                        fileName = tInput;
                        new RandomArray().makeArray(tInput, sizeArr);
                        System.out.printf(" Khoi tao thanh cong! ");
                        System.out.print("\n=================================================\n");
                        break;
                    }
                    case 2:
                    {
                        //ArrayList A = new ArrayList<Integer>();
                        int[] list = new int[sizeArr];
                        File file = new File(fileName);
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
                        QuickSort quick = new QuickSort();
                        quick.sortNumbers(list);
                        quick.outWithQuick("quick.txt",list);
                        timeQuick=quick.time;

                        HeapSort heap = new HeapSort();
                        //heap.readFile(fileName);
                        heap.sortNumbers(list);
                        heap.outWithHeap("heap.txt",list);
                        timeHeap=heap.time;
                        
                        MergeSort merge = new MergeSort();
                        merge.sortNumbers(list);
                        merge.outWithMerge("merge.txt",list); 
                        timeMerge=merge.time;
                        
                        break;
                    }
                    case 3:
                    {
                        System.out.println("Thoi gian thuc hien Heap Sort:  "+timeHeap+"  (nano time)");
                        System.out.println();
                        System.out.println("Thoi gian thuc hien Merge Sort: "+timeMerge+"  (nano time)");
                        System.out.println();
                        System.out.println("Thoi gian thuc hien Quick Sort: "+timeQuick+"  (nano time)");
                        System.out.println();
                    }
                    case 4:
                    {
                        System.exit(1);
                        break;
                        
                    }
                    
                }
            }
    }
}
