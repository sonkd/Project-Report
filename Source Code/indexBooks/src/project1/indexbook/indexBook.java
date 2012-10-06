//package project1.indexbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 *
 * @author KimDinh
 */

public class indexBook {
	
    Hashtable<String, ArrayList<Integer>> ds;
	
    public indexBook() {
        ds=new Hashtable<String, ArrayList<Integer>>();
    }
	
    public void CreateIndex(String sourceFile,String indexFile) {
        try {
            Scanner sc=new Scanner(new FileInputStream(sourceFile),"UTF-8");
            String line="";
            int lineNo=0;
            while(sc.hasNextLine()) {
                line=sc.nextLine();
                lineNo++;
                StringTokenizer tk=new StringTokenizer(line," ");
                while(tk.hasMoreTokens()) {
                    String w=tk.nextToken();
                    xuly(w,lineNo);
                }
            }
            //Xuat ra file index
            xuatFile(indexFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void xuly(String key,int lineNo) {
        //chưa có từ thì tạo 1 mục mới
        if(!ds.containsKey(key)) {
            ArrayList<Integer> lst=new ArrayList<Integer>();
            lst.add(new Integer(lineNo));
            ds.put(key, lst);
        }
        else //da co tu trong danh sach
        {
            ArrayList<Integer>lst= ds.get(key);
            lst.add(new Integer(lineNo));
        }
    }
    public boolean keyWord(String key){
        return (key.startsWith("$")&&key.endsWith("$"));
            
    }
     
    public void xuatFile(String indexFile) {
        try {
            try (PrintWriter out = new PrintWriter(indexFile,"UTF-8")) {
                Set<String> setKey=ds.keySet();
                Iterator<String>it=setKey.iterator();
                while(it.hasNext()) {
                    String key=it.next();
                    if(keyWord(key)==true){
                        ArrayList<Integer>arr=ds.get(key);
                        String idx=arr.get(0).toString()+", ",past = "";
                        int count = 0;
                    //String tmp = key.substring(1, key.length()-1);
                        
                        for(int i = 1;i<arr.size();i++){
                            count++;
                            past=arr.get(i).toString();
                            if(past.compareTo(arr.get(i-1).toString())>0){
                                if(count%25==0){                                
                                    idx+=past+"\n";                        
                                }
                                else{
                                    idx+=past+", ";  
                                }
                            }
                        }
                       
                        past=null;
                        out.println(key.substring(1, key.length()-1)+" "+idx+"\n");
                                
                    }
                }
                out.flush();
            }
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
        
    public static void main(String[] args){
        indexBook idxBook = new indexBook();
        idxBook.CreateIndex("source.txt","indexBook.txt");
    }
}