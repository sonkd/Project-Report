//package project1.tsp.NhanhCan;

import java.util.Scanner;

/**
 *
 * @author KimDinhSon
 */
public class TSPNhanhCan {
    int infty = (int) Math.pow(10, 6),n;
    int numberCity=6;
    int matrix[][]={
        {0,3,93,13,33,9},
        {4,0,77,42,21,16},
        {47,17,0,36,16,28},
        {39,90,80,0,56,7},
        {28,46,88,33,0,25},
        {3,88,18,46,92,0}
    };
    private boolean B[] = {false,false,false,false,false,false};

    //public boolean[] B = new boolean[]{}; 
    
    int[] resulf=new int[10];//Mang tam luu giu cau hinh
    int[] bestConfig=new int[10];//Mang luu cau hinh tot nhat
    int cost=0,start;//chi phi hanh trinh va dinh xuat phat
    public void Try(int i){
        //Neu i=n, kiem tra cau hinh co la tot nhat khong
        if(i==numberCity){
            if(cost+matrix[resulf[i-1]][resulf[0]]<infty){
                infty=cost+matrix[resulf[i-1]][resulf[0]];
                /*
                for(int k=0;k<numberCity;k++){
                    bestConfig[k]=resulf[k];// Cap nhat cau hinh
                }
                */
                System.arraycopy(resulf, 0, bestConfig, 0, numberCity);
            }
        }
        else{
            for(int j=0;j<numberCity;j++){// Duyet cac dinh kha thi
                //Neu chua di qua ma thoa man
                if(getB()[j]==false&&cost+matrix[resulf[i-1]][j]<infty){
                    //Ghi nho lai ket qua
                    //Di theo nhanh chua dinh j
                    resulf[i]=j;
                    getB()[j]=true;
                    cost+=matrix[resulf[i-1]][j];
                    //Goi lai de qui de tiep tuc chia nhanh
                    Try(i+1);
                    //Neu khong di theo nhanh chua dinh j
                    getB()[j]=false;
                    cost-=matrix[resulf[i-1]][j];
                }
            }
        }
    
    }
    
    public void outPut(){
        for(int i=0;i<numberCity;i++){
            for(int j=0;j<numberCity;j++){
                System.out.print("\t"+matrix[i][j]);
            }
            System.out.println();
        }
        System.out.print("Lo trinh tot nhat: ");
        for(int i=0;i<numberCity;i++){
            System.out.print(bestConfig[i]+1+" -> ");
        }
        System.out.print(bestConfig[0]+1);
        System.out.print("\nChi phi la: "+infty+"\n");
        
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        TSPNhanhCan tsp = new TSPNhanhCan();
        //Vi tri xuat phat
        System.out.print("Nhap vi tri xuat phat: ");
        int start = new Scanner(System.in).nextInt();
        boolean[] B = new boolean[]{};
        //tsp.Check(B);
        start--;
        tsp.resulf[0]=start;       
        tsp.getB()[start]=true;
        tsp.Try(1);
        tsp.outPut();
    }

    /**
     * @return the B
     */
    public boolean[] getB() {
        return B;
    }

    /**
     * @param B the B to set
     */
    public void setB(boolean[] B) {
        this.B = B;
    }
    
}
