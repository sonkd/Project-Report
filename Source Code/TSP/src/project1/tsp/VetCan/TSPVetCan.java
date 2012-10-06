
//package project1.tsp.VetCan;

/**
 *
 * @author KimDinhSon
 */
public class TSPVetCan {
    int infty = (int) Math.pow(10, 6);
    int numberCity=6;
	int full=giaiThua(numberCity);
	int duyet[numberCity]={0,0,0,0,0,0};
	int permutationT[][] = new int[full][numberCity];
    //int[] cost = new int[numberCity];
    //Matrix chi phi
    int matrix[][]={
        {0,3,93,13,33,9},
        {4,0,77,42,21,16},
        {47,17,0,36,16,28},
        {39,90,80,0,56,7},
        {28,46,88,33,0,25},
        {3,88,18,46,92,0}
    };
      
    //Tinh chi phi cho lo trinh
    public int coutingCost(int[] tran){
        int chiPhi = 0;
        for(int i = 0;i<numberCity;i++){
            chiPhi+=matrix[tran[i]][tran[i+1]];
        }
        chiPhi+=matrix[tran[numberCity-1]][0];
        return chiPhi;
    }
    
	public void permT(int startCity,int next){
		permutationT[startCity][next]=matrix[startCity][next];
		duyet[next]=1;
		for(int i=0;i<full;i++){
				if(duyet[i]==0) permT(next,i);
		}
	}
	
    //Vet can cac truong hop
    public void vetCan(){
        
        //liet ke cac tap hoan vi (1,2,3,4,5,6)
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
        //Lộ trình
        }
        //Chi phí
        System.out.print("\nChi phi la: "+infty+"\n");
        
    }
    
    public static void main(String[] args) {
        
    }
}
