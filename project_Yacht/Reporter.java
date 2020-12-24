package YachtDice;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.LinkedList;

public class Reporter extends JFrame implements MouseListener{
        String data[][] = new String[50][3];
        String best[][]=new String[1][3];
        String kinds[] = {"이름", "점수", "시간"};
        String Bkinds[]={"이름", "최고점수", "시간"};
        JButton make = new JButton("파일 생성");
        JButton exit = new JButton("닫기");
        JButton help = new JButton("도움말");
        public Reporter(){
              JFrame j = new JFrame("기록소");
            try{
                FlieRoad();
            }catch(IOException e){
            }
            JPanel total=new JPanel();
              total.setLayout(new BorderLayout());
              total.setPreferredSize(new Dimension(500,700));
              JPanel cen = new JPanel();
              cen.setPreferredSize(new Dimension(500,600));
              JPanel option = new JPanel();
              option.setPreferredSize(new Dimension(500,35));
              JTable table = new JTable(data,kinds);
              JTable table1 =new JTable(best,Bkinds);
              JPanel pane = new JPanel();
              JLabel la = new JLabel("기록소");
              la.setFont(new Font("SansSerif",Font.BOLD,20));
              la.setForeground(Color.WHITE);
              pane.setSize(new Dimension(500,50));
              JScrollPane scroll,scroll1;
              cen.setBackground(new Color(132,119,103));
              pane.setBackground(new Color(132,119,103));
              option.setBackground(new Color(132,119,103));
              table1.setEnabled(false);
              table1.setForeground(Color.BLACK);
              table1.setBackground(Color.YELLOW);
              table1.getTableHeader().setReorderingAllowed(false);
              table1.setRowHeight(40);

              table.setEnabled(false);
              table.setForeground(Color.YELLOW);
              table.setBackground(Color.BLACK);
              table.getTableHeader().setReorderingAllowed(false);
              make.setBorderPainted(false);
              exit.setBorderPainted(false);
              help.setBorderPainted(false);
              make.setBackground(new Color(185,177,163));
              exit.setBackground(new Color(185,177,163));
              help.setBackground(new Color(185,177,163));
              make.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      try {
                          FlieTXTMake();
                          int result=JOptionPane.showConfirmDialog(null,"파일생성에 성공하였습니다. 파일을 여시겠습니다.","파일생성 완료",JOptionPane.YES_NO_OPTION);
                          if(result==JOptionPane.YES_OPTION){
                              Desktop.getDesktop().edit(new File("utill/report/out/playRecrod.txt"));
                              j.dispose();
                          }else{
                              j.dispose();
                          }
                      } catch (IOException ioException) {
                          JOptionPane.showMessageDialog(null,"파일생성에 실패하였습니다.");
                      }
                  }
              });
              exit.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      j.dispose();
                  }
              });
              help.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      new Helper(3);
                  }
              });
              make.addMouseListener(this);
              exit.addMouseListener(this);
              help.addMouseListener(this);
              option.add(help);option.add(make);option.add(exit);
              pane.add(la);cen.add(table1,"North");
            cen.add(table,"Center");
            total.add(pane,"North");
            total.add(cen,"Center");
            total.add(option,"South");
            scroll1 = new JScrollPane(table1);
            scroll1.setPreferredSize(new Dimension(450,80));
            scroll = new JScrollPane(table);
            scroll.setPreferredSize(new Dimension(450,550));
            pane.add(scroll1);
            cen.add(scroll);

            j.add(total);
            j.setVisible(true);
            j.setSize(new Dimension(500,700));
            j.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            j.setLocation(1400,300);
        }
        @Override
        public void mouseClicked(MouseEvent e) { }
        @Override
        public void mousePressed(MouseEvent e) { }
        @Override
        public void mouseReleased(MouseEvent e) { }
        @Override
        public void mouseEntered(MouseEvent e) {
           if(e.getSource()==make) {
               make.setBackground(new Color(232,197,113));
           }
            if(e.getSource()==exit) exit.setBackground(new Color(232,197,113));
            if(e.getSource()==help)help.setBackground(new Color(232,197,113));
        }
        @Override
        public void mouseExited(MouseEvent e) {
            if(e.getSource()==make) make.setBackground(new Color(185,177,163));
            if(e.getSource()==exit) exit.setBackground(new Color(185,177,163));
            if(e.getSource()==help)help.setBackground(new Color(185,177,163));
        }
        public void FlieRoad()throws IOException{
            DataInputStream countFile= null;
            LinkedList<FileInputStream> pRname= new LinkedList<FileInputStream>();
            LinkedList<FileInputStream> pRscore= new LinkedList<FileInputStream>();
            LinkedList<FileInputStream> pRdate= new LinkedList<FileInputStream>();
            DataInputStream in[] = {null,null,null};
            int c;

            try{
                countFile =new DataInputStream(new BufferedInputStream(new FileInputStream("utill/report/countFile.dat")));
                c=countFile.readInt();

                for(int i=0;i<=c;i++){
                    pRname.add(new FileInputStream("utill/report/name"+i+".dat"));
                    pRscore.add(new FileInputStream("utill/report/score"+i+".dat"));
                    pRdate.add(new FileInputStream("utill/report/date"+i+".dat"));
                    in[0] = new DataInputStream(pRname.get(i));
                    in[1] =new DataInputStream(pRscore.get(i));
                    in[2] =new DataInputStream(pRdate.get(i));
                    data[i][0]=in[0].readUTF();
                    data[i][1]=""+in[1].readInt();
                    data[i][2]=in[2].readUTF();
                    if (i==0){
                        best[0][0]=data[i][0];
                        best[0][1]=data[i][1];
                        best[0][2]=data[i][2];
                    }
                    if(Integer.parseInt(data[i][1])>Integer.parseInt(best[0][1])){
                        best[0][0]=data[i][0];
                        best[0][1]=data[i][1];
                        best[0][2]=data[i][2];
                    }
                }
            }finally {
                if(countFile!=null)countFile.close();
                if(in!=null)for(int i=0;i<3;i++)in[i].close();
            }
        }
    public void FlieTXTMake() throws IOException {
        DataInputStream countFile= null;
        int c;
        try{
            countFile =new DataInputStream(new BufferedInputStream(new FileInputStream("utill/report/countFile.dat")));
            c=countFile.readInt();
            FileWriter writer = new FileWriter("utill/report/out/playRecrod.txt");
            PrintWriter print = new PrintWriter(writer);
            print.println("----------------------전적 기록-----------------------------");
            print.printf("최고기록\n");
            print.printf("********************************************************\n");
            print.printf(" 이름  |   점수   |  날짜 \n");
            print.printf(best[0][0]+" | "+best[0][1]+" | "+best[0][2]+"\n");
            print.printf("********************************************************\n");
            print.printf(" 이름  |   점수   |  날짜 \n");
            for(int i=0;i<c+1;i++) {
                if(data[i][0]==null) break;
                print.printf(data[i][0]+" | "+data[i][1]+" | "+data[i][2]+"\n");
            }
            print.printf("********************************************************\n");
            print.println("----------------------------------------------------------");
            print.println("파일 경로는 utill/report/out/playRecrod.txt 입니다.");
            print.close();
        }finally {
            if(countFile !=null)countFile.close();
            }
        }
    }
