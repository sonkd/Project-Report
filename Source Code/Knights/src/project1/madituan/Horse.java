
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public final class Horse extends JFrame implements ActionListener{
    private static final long serialVersionUID = 1L;
    
    private GridLayout g=new GridLayout(8,8);

    private JButton [][]btn=new JButton[8][8];
    private int BC[][]=new int[8][8] ;
    private int Di[] = { 1, -1, -2, -2, -1,  1, 2,  2};
    private int Dj[] = {-2, -2, -1,  1,  2,  2, 1, -1};
    private JPanel ptop,pcent;
    public Horse()
    {
        super("MA DI TUAN");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(550,550);
        int x=this.getToolkit().getScreenSize().width/2-300;
        int y=this.getToolkit().getScreenSize().height/2-300;
        setLocation(x,y);
        
        this.setLayout(g);
        khoitaobanco();
        XuatBC();
                
    }
    
    //tinh H
    public int  TinhH(int d,int c)
    {
        int h=0;
        //xet tren mang luu tam huong di cua ma
        for(int i=0;i<8;i++)
        {
            int dong=d+Di[i];
            int cot=c+Dj[i];
            //neu vi tri ke tiep cua con ma van con nam trong ban co
            if(dong>=0 && dong<8 &&cot>=0 && cot<8 && BC[dong][cot]==0) {
                h++;
            }
        }
        return h;
    }
    
    //xuat 1 o co
    public void xuat1oco(int d,int c,int value)
    {     int t=0;
    
        String flag=new String();
        flag=btn[d][c].getText();
        while(t<100000000) {
            t++;
        }
        
        btn[d][c].setText("  "+value);
        
        if(flag.equalsIgnoreCase("T")) {
            btn[d][c].setIcon(new ImageIcon("nguatrang.jpg"));
        }    
        else {
            btn[d][c].setIcon(new ImageIcon("nguaden.png"));
            
        }
        
        while(t<500000000) {
            t++;
        }
        btn[d][c].setIcon(new ImageIcon("o.gif"));
        btn[d][c].setForeground(Color.RED);
    }
    
    
    //ma di tuan
    public int madituan(int d,int c)
    {
        Graphics g = null;
        int buoc=1;
        BC[d][c]=buoc;
        //xuat1oco(d, c, BC[d][c]);
        while(buoc<64)
        {
            int min=8,viTriDong=-1,viTriCot=-1,h = 0;
            for(int i=0;i<8;i++)
            {
                int dt=d+Di[i];
                int ct=c+Dj[i];

                if(dt>=0 && dt<8 && ct>=0 && ct<8 && BC[dt][ct]==0)
                {
                    h=TinhH(dt,ct);
                    if(h<min)
                    {
                        min=h;
                        viTriDong=dt;
                        viTriCot=ct;
                    }
                }
            }
            if(viTriDong==-1 || viTriCot==-1) {
                return -1;
            }  //false

            xuat1oco(d, c, BC[d][c]);
            d=viTriDong;
            c=viTriCot;
            buoc++;
            BC[d][c]=buoc;
        }
        
        
        xuat1oco(d, c, BC[d][c]);
        //g.drawLine(a, b, d, c);
        return 1;//true
    }

    //khoi tao ban co
    public void khoitaobanco()
    {
        for(int i=0;i<8;i++) {
            for(int j=0;j<8;j++) {
                BC[i][j]=0;
            }
        }
    }
    
    //xuat ban co
    public void XuatBC()
    {
        
        btn=new JButton[8][8];
        g=new GridLayout(8,8);
        this.setLayout(g);
                
            int d=0;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                d++;
                btn[i][j]=new JButton();
                    
                if(i%2==0){
                if(d%2==0){        
                
                btn[i][j].setBackground(Color.WHITE);
                btn[i][j].setForeground(Color.WHITE);
                }else
                {
                    
                    btn[i][j].setBackground(Color.gray);
                    btn[i][j].setForeground(Color.gray);
                }
                            
                }else
                {if(d%2==1){        
                
                btn[i][j].setBackground(Color.WHITE);
                btn[i][j].setForeground(Color.WHITE);
                
                }else
                {
                    
                    btn[i][j].setBackground(Color.gray);
                    btn[i][j].setForeground(Color.gray);
                }
                    
                }
                                      
                this.add(btn[i][j]);
                
                }    
            }                
                    
    }
        
    public void kiemtra(int dong,int cot)
    {if(madituan(dong,cot)==-1)
    {
        JOptionPane.showMessageDialog(null, "That bai");
                
    }
    else
    {JOptionPane.showMessageDialog(null, "Thanh cong");
        
    }
        
    }
    
    @Override
        public void actionPerformed(ActionEvent arg0) {
            // TODO Auto-generated method stub
            
        }
        
        public static void main(String[] args) {
            Horse n=new Horse();
            n.setVisible(true);        
            Integer d=null,c=null;
            
            try {
                String s1=JOptionPane.showInputDialog("nhap dong(1->8): ");
                d=Integer.parseInt(s1);
                String s2=JOptionPane.showInputDialog("Nhap cot(1->8):");
                c=Integer.parseInt(s2);
                
                while (d>8||d<=0) 
                {    JOptionPane.showMessageDialog(null,"Nhap dong tu  1 -> 8 ","Nhap sai",0);
                    s1=JOptionPane.showInputDialog("Nhap dong(1->8):");
                    d=Integer.parseInt(s1);
                }
                
                while (c>8||c<=0) 
                {    JOptionPane.showMessageDialog(null,"Nhap cot tu  1 -> 8 ","Nhap sai",0);
                    s2=JOptionPane.showInputDialog("Nhap cot(1->8):");
                    c=Integer.parseInt(s2);
                }
                n.kiemtra(d-1, c-1);
            } catch (HeadlessException e) {
                System.exit(1);
                
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,"Nhap sai");
                n.kiemtra(0,0);    
            }
        }

}  
