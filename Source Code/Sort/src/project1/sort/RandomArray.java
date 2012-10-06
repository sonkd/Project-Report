
//package project1.sort;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author KimDinhSon
 */
@SuppressWarnings("unchecked")
public class RandomArray {
    //File test=new File("test.txt");
    Random randNumber = new Random();
    /*
    public void makeArray(int size)throws IOException{
        int max=10 * size;
        try (FileWriter test = new FileWriter("test.txt")) {
            BufferedWriter out = new BufferedWriter(test);
            for(int i=0;i<size;i++){
               out.write(array.add(BigInteger.valueOf(randNumber.nextInt(max)))+"");
            }
        }catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }
    * 
    */
    /*
    @SuppressWarnings("unchecked")
    public void makeArray(int size)throws IOException{
        int max=10 * size;
        FileOutputStream out=new FileOutputStream("test.txt");
        PrintWriter output=new PrintWriter(out,true);
        for(int i=0;i<size;i++){
            output.println(array.add(BigInteger.valueOf(randNumber.nextInt(max))));
        }
        out.close();
        output.close();
    }
    */

    public void makeArray(String filename, int size) throws IOException {
        int max=10 * size,tmp;
        BufferedWriter bufferedWriter = null;       
        try {          
            //Construct the BufferedWriter object
            bufferedWriter = new BufferedWriter(new FileWriter(filename));       
            //Start writing to the output stream
            for(int i=0;i<size;i++){
               tmp = randNumber.nextInt(max); 
               bufferedWriter.write(tmp+" ");            
            }
            
        } catch (FileNotFoundException ex) {
        } finally {
            //Close the BufferedWriter
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.flush();
                    bufferedWriter.close();
                }
            } catch (IOException ex) {
            }
        }
    }
    /*
    public static void main(String[] args) throws IOException {
        new RandomArray().makeArray("test.txt",1000);
    }
    * 
    */
}
