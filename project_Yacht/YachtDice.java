package YachtDice;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.net.URL;

class YachtDice extends JFrame implements WindowListener {
    BGM test = new BGM();
    JFrame j;
    public YachtDice(){
        j = new JFrame("Yacht Dice");
        j.setLayout(new BorderLayout());

        JPanel title =new JPanel();
        JPanel helping=new JPanel(new FlowLayout());
        helping.setBackground(new Color(132,119,103));
        title.setLayout(new BorderLayout());
        title.setBackground(new Color(132,119,103));
        JLabel mainTitle= new JLabel("Yacht Dice");
        JButton help = new JButton("도움말");
        help.setForeground(new Color(76,63,55));
        help.setBackground(new Color(181,154,133));
        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Helper(0);
            }
        });
        JButton help2= new JButton("점수판 족보");
        help2.setForeground(new Color(76,63,55));
        help2.setBackground(new Color(181,154,133));
        help2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Helper(2);
            }
        });
        helping.add(help);helping.add(help2);
        title.add(mainTitle,"Center"); title.add(helping,"West");
        mainTitle.setHorizontalAlignment(JLabel.CENTER);
        mainTitle.setFont(new Font("SansSerif",Font.PLAIN,25));
        mainTitle.setForeground(Color.WHITE);
        JPanel p=new JPanel();
        p.setLayout(new FlowLayout());

        YDGamePanel game= new YDGamePanel();
        j.add(game,"Center");
        test.start();
        j.add(title,"North");
        j.setVisible(true);
        j.setSize(1500,1000);
        j.addWindowListener(this);
        j.setResizable(false);
    }
    @Override
    public void windowClosing(WindowEvent evt){
        test.interrupt();
        dispose();
    }
    @Override public void windowOpened(WindowEvent evt) { }
    @Override public void windowClosed(WindowEvent evt) { }
    @Override public void windowIconified(WindowEvent evt) {}
    @Override public void windowDeiconified(WindowEvent evt) {}
    @Override public void windowActivated(WindowEvent evt) {}
    @Override public void windowDeactivated(WindowEvent evt) {}

    class YDGamePanel extends JPanel implements ActionListener{
        private ImageIcon[] diceRed = {
                new ImageIcon(""),
                new ImageIcon("utill/diceRed/Dice1.gif"),
                new ImageIcon("utill/diceRed/Dice2.gif"),
                new ImageIcon("utill/diceRed/Dice3.gif"),
                new ImageIcon("utill/diceRed/Dice4.gif"),
                new ImageIcon("utill/diceRed/Dice5.gif"),
                new ImageIcon("utill/diceRed/Dice6.gif"),
                new ImageIcon("utill/diceRed/DiceMix.gif")
        };
        private ImageIcon[] diceBlue = {
                new ImageIcon(""),
                new ImageIcon("utill/diceBlue/Dice1.gif"),
                new ImageIcon("utill/diceBlue/Dice2.gif"),
                new ImageIcon("utill/diceBlue/Dice3.gif"),
                new ImageIcon("utill/diceBlue/Dice4.gif"),
                new ImageIcon("utill/diceBlue/Dice5.gif"),
                new ImageIcon("utill/diceBlue/Dice6.gif"),
                new ImageIcon("utill/diceBlue/DiceMix.gif")
        };
        private ImageIcon[] dicePuple = {
                new ImageIcon(""),
                new ImageIcon("utill/dicePuple/Dice1.gif"),
                new ImageIcon("utill/dicePuple/Dice2.gif"),
                new ImageIcon("utill/dicePuple/Dice3.gif"),
                new ImageIcon("utill/dicePuple/Dice4.gif"),
                new ImageIcon("utill/dicePuple/Dice5.gif"),
                new ImageIcon("utill/dicePuple/Dice6.gif"),
                new ImageIcon("utill/dicePuple/DiceMix.gif")
        };
        private ImageIcon[] diceOrange = {
                new ImageIcon(""),
                new ImageIcon("utill/diceOrange/Dice1.gif"),
                new ImageIcon("utill/diceOrange/Dice2.gif"),
                new ImageIcon("utill/diceOrange/Dice3.gif"),
                new ImageIcon("utill/diceOrange/Dice4.gif"),
                new ImageIcon("utill/diceOrange/Dice5.gif"),
                new ImageIcon("utill/diceOrange/Dice6.gif"),
                new ImageIcon("utill/diceOrange/DiceMix.gif")
        };
        private ImageIcon[] diceBrown = {
                new ImageIcon(""),
                new ImageIcon("utill/diceBrown/Dice1.gif"),
                new ImageIcon("utill/diceBrown/Dice2.gif"),
                new ImageIcon("utill/diceBrown/Dice3.gif"),
                new ImageIcon("utill/diceBrown/Dice4.gif"),
                new ImageIcon("utill/diceBrown/Dice5.gif"),
                new ImageIcon("utill/diceBrown/Dice6.gif"),
                new ImageIcon("utill/diceBrown/DiceMix.gif")
        };
        private Color col[] = {Color.RED,Color.BLUE,new Color(255,140,0),new Color(160,82,45),new Color(138,43,226) };
        private BufferedImage backImg,scoreImg,countBG,countBG2;
        private ImageIcon eff=new ImageIcon("utill/BG/YEffect.gif");
        private ImageIcon[][] diceUser={diceRed,diceBlue,diceOrange,diceBrown,dicePuple};
       // private ImageIcon selectB=new ImageIcon("BG/select.jpg");
        Random rand = new Random();
        private int mixCounter =3;
        private int turnCounter=1;
        private int totalScore=0;
        private int bouseScore=0;
        public float volume=0;
        private boolean scoreS[]={true,true,true,true,true,true,true,true,true,true,true,true,true,true};
        private int scoreDice[]=new int[5];
        private int random[] = new int[5];
        //주사위 던지는 애니메이션
        private int x1[] =new int[5];//Dice를 Mix시 X좌표를 저장할 배열
        private int x2[]=new int[5];//keepDice를 할시 이전의 x좌표를 저장할 배열
        private int y1[]=new int[5];//Dice를 Mix시 Y좌표를 저장할 배열
        private int y2[]=new int[5];//keepDice를 할시 이전의 y좌표를 저장할 배열
        private int setx[][]={ //row:150기준으로 나눔 cal:
                {13,22,59,75,95,113,134,150},
                {175,190,200,225,237,258,278,300},
                {321,339,357,378,394,404,428,450},
                {459,475,498,502,542,567,572,600},
                {615,639,657,662,675,691,701,750},
        };
        private int sety[]={0,70,140,210, 280,350,420,490,560};
        private int keepX[]={33,233,433,633,833};
        private int keepY=720;
        private  boolean ranX[]= {true,true,true,true,true,true};
        private  boolean ranY[]= {true,true,true,true,true,true,true,true,true};

        JLabel l[]={
                new JLabel("Aces"),//0
                new JLabel("Deuces"),
                new JLabel("Threes"),
                new JLabel("Fours"),
                new JLabel("Fives"),
                new JLabel("Sixes"),
                new JLabel("bonus"),
                new JLabel("Choice"),
                new JLabel("3 of kind"),
                new JLabel("4 of kind"),
                new JLabel("Full House"),
                new JLabel("SmallStraight"),
                new JLabel("LongStraight"),
                new JLabel("Yacht"),
                new JLabel("Total")//14
        };
        JButton randDice[]={
                new JButton(diceUser[0][0]),
                new JButton(diceUser[1][0]),
                new JButton(diceUser[2][0]),
                new JButton(diceUser[3][0]),
                new JButton(diceUser[4][0])
        };
        JLabel turnLabel,MixLabel;
        JButton b[]={
                new JButton("주사위눈 1의 합"),
                new JButton("주사위눈 2의 합"),
                new JButton("주사위눈 3의 합"),
                new JButton("주사위눈 4의 합"),
                new JButton("주사위눈 5의 합"),
                new JButton("주사위눈 6의 합"),
                new JButton("조건을 맞추면 추가점수"),
                new JButton("주사위눈들의 합"),
                new JButton("같은눈 3개일때 눈의 합"),
                new JButton("같은눈 4개일때 눈의 합"),
                new JButton("같은주사위눈 2개,3개의 합"),
                new JButton("눈이3개 연속될시 30점"),
                new JButton("눈이5개 연속될시 40점"),
                new JButton("모든 주사위눈이 같을시 50점"),
                new JButton("최종점수")
        };
        JButton mix;
        JCheckBox keepDice[]={
                new JCheckBox("diceRED",false),
                new JCheckBox("diceBlue",false),
                new JCheckBox("diceOrange",false),
                new JCheckBox("diceBrown",false),
                new JCheckBox("dicePuple",false)};
        JPanel dicetotalPanel =new BackpanelSub();
        JLayeredPane dicePanel = new Backpanel();
        //JLayeredPane eff= new EfBackPanel();
        String[] ender={"기록","종료"};
        DiceEffect e1= new DiceEffect();
        private int tester[]={1,2,3,4,5,6};
        public YDGamePanel(){
            try{
                backImg=ImageIO.read(new File("utill/BG/Dicebackground.jpg"));
                scoreImg=ImageIO.read(new File("utill/BG/scroeback.jpg"));
                countBG=ImageIO.read(new File("utill/BG/text.png"));
                countBG2=ImageIO.read(new File("utill/BG/text2.png"));
            }catch (IOException e){
                System.out.println("X");
            }
            //setSize(backImg.getWidth(null),backImg.getHeight(null));
            setLayout(new BorderLayout());

            JLayeredPane score = new SBackpanel();
            score.setLayout(new GridLayout(15,3));
            score.setPreferredSize(new Dimension(300,1000));

            dicePanel.setLayout(null);
            dicePanel.setPreferredSize(new Dimension(1050,1000));
            JPanel diceKeepPanel =new BackpanelSub();
            diceKeepPanel.setLayout(new FlowLayout());
            diceKeepPanel.setBackground(Color.BLACK);
            //eff.setPreferredSize(new Dimension(1050,1000));
            //eff.setOpaque(true);

            mix = new JButton("Rolling" );
            mix.setSize(new Dimension(150,500));
            mix.setBackground(new Color(242,252,218));

            JPanel option = new BackpanelSub();
            option.setLayout(new GridLayout(3,0));
            option.setPreferredSize(new Dimension(150,1000));
            JPanel countPanel= new countBackPanel();
            countPanel.setSize(new Dimension(150,500));
            countPanel.setLayout(new GridLayout(2,0));
            turnLabel= new JLabel("Turn: "+turnCounter+"/14",2);
            turnLabel.setHorizontalAlignment(JLabel.CENTER);
            MixLabel= new JLabel("MIX: "+mixCounter,2);
            MixLabel.setHorizontalAlignment(JLabel.CENTER);

            JPanel totalSCPanel = new JPanel(new BorderLayout());
            totalSCPanel.setSize(new Dimension(150,500));
            JPanel SCPanel = new JPanel(new GridLayout(0,2));
            SCPanel.setBackground(new Color(185,177,163));
            JPanel SCPanelSub= new JPanel(new GridLayout(0,2));
            SCPanelSub.setBackground(new Color(185,177,163));
            JLabel soundlabel[] = {new JLabel(" BGM"),new JLabel(" Effect")};
            JSlider backMusic[] = {new JSlider(0,100,80),new JSlider(0,100,80)};
            for(int i = 0;i<2;i++) {
                backMusic[i].setOrientation(JSlider.VERTICAL);
                backMusic[i].setMajorTickSpacing(40);
                backMusic[i].setMinorTickSpacing(20);
                backMusic[i].setPaintTicks(true);
                backMusic[i].setPaintLabels(true);
                soundlabel[i].setForeground(new Color(66,119,122));
                backMusic[i].setForeground(new Color(66,119,122));
                backMusic[i].setBackground(new Color(185,177,163));
                SCPanel.add(backMusic[i]);
                SCPanelSub.add(soundlabel[i]);
            }
            backMusic[0].addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    volume = backMusic[0].getValue();
                    System.out.println(volume);
                    test.ctrl.setValue((float) (-55.92+(0.5592*(volume))));//볼륨 컨트롤을 합니다.
                    test.setvol((float) (-55.92+(0.5592*(volume))));
                }
            });
            backMusic[1].addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    volume = backMusic[1].getValue();
                    System.out.println(volume);
                    e1.setvol((float)(-50+(0.56*(volume))));//볼륨 컨트롤을 합니다.
                }
            });
            totalSCPanel.add(SCPanel,"Center");
            totalSCPanel.add(SCPanelSub,"South");
            b[6].setEnabled(false);
            b[14].setEnabled(false);
            countPanel.add(turnLabel);
            countPanel.add(MixLabel);
            for(int i=0;i<15;i++){
                l[i].setHorizontalAlignment(JLabel.CENTER);
                l[i].setFont(new Font("Serif",Font.BOLD,20));
                l[i].setForeground(Color.WHITE);
                score.add(l[i]);
                score.add(b[i]);
                b[i].addActionListener(this);
                b[i].setContentAreaFilled(false);
                b[i].setBorderPainted(false);
                b[i].setForeground(Color.WHITE);
            }
            for(int i=0;i<5;i++){
                randDice[i].setContentAreaFilled(false);
                randDice[i].setBorderPainted(false);
                dicePanel.add(randDice[i]);
                diceKeepPanel.add(keepDice[i]);
                randDice[i].addActionListener(this);
                keepDice[i].setForeground(col[i]);
                keepDice[i].setBackground(Color.WHITE);
            }
            keepDice[0].addItemListener(e->{
                    if (e.getStateChange()==ItemEvent.SELECTED) {
                        Keeping(0);
                        keepDice[0].setForeground(Color.WHITE);
                        keepDice[0].setBackground(col[0]);
                    }
                    else if(e.getStateChange()==ItemEvent.DESELECTED){
                        deKeeping(0);
                        keepDice[0].setForeground(col[0]);
                        keepDice[0].setBackground(Color.WHITE);
                    }
            });
            keepDice[1].addItemListener(e->{
                if (e.getStateChange()==ItemEvent.SELECTED) {
                    Keeping(1);
                    keepDice[1].setForeground(Color.WHITE);
                    keepDice[1].setBackground(col[1]);
                }
                else if(e.getStateChange()==ItemEvent.DESELECTED){
                    deKeeping(1);
                    keepDice[1].setForeground(col[1]);
                    keepDice[1].setBackground(Color.WHITE);
                }
            });
            keepDice[2].addItemListener(e->{
                if (e.getStateChange()==ItemEvent.SELECTED) {
                    Keeping(2);
                    keepDice[2].setForeground(Color.WHITE);
                    keepDice[2].setBackground(col[2]);
                }
                else if(e.getStateChange()==ItemEvent.DESELECTED){
                    deKeeping(2);
                    keepDice[2].setForeground(col[2]);
                    keepDice[2].setBackground(Color.WHITE);
                }
            });
            keepDice[3].addItemListener(e->{
                if (e.getStateChange()==ItemEvent.SELECTED) {
                    Keeping(3);
                    keepDice[3].setForeground(Color.WHITE);
                    keepDice[3].setBackground(col[3]);
                }
                else if(e.getStateChange()==ItemEvent.DESELECTED){
                    deKeeping(3);
                    keepDice[3].setForeground(col[3]);
                    keepDice[3].setBackground(Color.WHITE);
                }
            });
            keepDice[4].addItemListener(e->{
                if (e.getStateChange()==ItemEvent.SELECTED) {
                    Keeping(4);
                    keepDice[4].setForeground(Color.WHITE);
                    keepDice[4].setBackground(col[4]);
                }
                else if(e.getStateChange()==ItemEvent.DESELECTED){
                    deKeeping(4);
                    keepDice[4].setForeground(col[4]);
                    keepDice[4].setBackground(Color.WHITE);
                }
            });
            add(score,"West");
            dicetotalPanel.add(diceKeepPanel,"North");
            dicetotalPanel.add(dicePanel,"South");
            add(dicetotalPanel,"Center");
            option.add(countPanel);
            option.add(mix);
            option.add(totalSCPanel);

            add(option,"East" );
            mix.addActionListener(this);
        }
        public void actionPerformed(ActionEvent e){
            int result[]=new int[14];

            if(e.getSource()==mix) {
                boolean check = true;
                for (int j = 0; j < 5; j++)
                    if (keepDice[j].isSelected() == true&&mixCounter==3) {
                        JOptionPane.showMessageDialog(null, "초기에는 keepDice를 할수없습니다. keepDice를 해제해 주세요!");
                        check = false;
                    }
                if(check){
                    e1.play();
                    if (mixCounter != 0) {
                        Rolling();
                        Clear();
                        dicePanel.repaint();
                        mixCounter -= 1;
                        MixLabel.setText("MIX: " + mixCounter);
                        b[6].setText(""+bouseScore+" /63");
                    } else {
                        JOptionPane.showMessageDialog(null, "더이상 돌릴수 없습니다.");
                    }
                    result[0] = scoreAces(scoreDice[0], scoreDice[1], scoreDice[2], scoreDice[3], scoreDice[4]);
                    result[1] = scoreDeuces(scoreDice[0], scoreDice[1], scoreDice[2], scoreDice[3], scoreDice[4]);
                    result[2] = scoreThrees(scoreDice[0], scoreDice[1], scoreDice[2], scoreDice[3], scoreDice[4]);
                    result[3] = scoreFours(scoreDice[0], scoreDice[1], scoreDice[2], scoreDice[3], scoreDice[4]);
                    result[4] = scoreFives(scoreDice[0], scoreDice[1], scoreDice[2], scoreDice[3], scoreDice[4]);
                    result[5] = scoreSixes(scoreDice[0], scoreDice[1], scoreDice[2], scoreDice[3], scoreDice[4]);
                    result[7] = scoreChoice(scoreDice[0], scoreDice[1], scoreDice[2], scoreDice[3], scoreDice[4]);
                    result[8] = scoreToker(scoreDice[0], scoreDice[1], scoreDice[2], scoreDice[3], scoreDice[4],scoreS[8]);
                    result[9] = scoreFoker(scoreDice[0], scoreDice[1], scoreDice[2], scoreDice[3], scoreDice[4],scoreS[9]);
                    result[10] = scoreFullHouse(scoreDice[0], scoreDice[1], scoreDice[2], scoreDice[3], scoreDice[4],scoreS[10]);
                    result[11] = scoreSmallStraight(scoreDice[0], scoreDice[1], scoreDice[2], scoreDice[3], scoreDice[4],scoreS[11]);
                    result[12] = scoreLargeStraight(scoreDice[0], scoreDice[1], scoreDice[2], scoreDice[3], scoreDice[4],scoreS[12]);
                    result[13] = scoreYacht(scoreDice[0], scoreDice[1], scoreDice[2], scoreDice[3], scoreDice[4],scoreS[13]);

                    //점수판 더하기
                    for (int i = 0; i < 14; i++) {
                        if(scoreS[i]&&i!=6)b[i].setText("" + result[i]);
                    }
                }
            }

            for(int i=0;i<14;i++)if(e.getSource()==b[i]) {
                if (mixCounter == 3) JOptionPane.showMessageDialog(null, "잘못선택하셨습니다. Rolling버튼을 먼저 누르세요");
                else {
                    if (scoreS[i]) {
                        b[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
                        b[i].setOpaque(false);
                        b[i].setForeground(Color.YELLOW);
                        scoreS[i] = false;
                        totalScore+=Integer.parseInt(b[i].getText());
                        if(i<6)bouseScore+=Integer.parseInt(b[i].getText());
                        turnCounter += 1;
                        turnLabel.setText("Turn: " + turnCounter + "/14");
                        mixCounter = 3;
                        MixLabel.setText("MIX: " + mixCounter);
                        b[6].setText(""+bouseScore+" /63");
                        if(bouseScore>=63&&scoreS[6]){
                            JOptionPane.showMessageDialog(null,"보너스 점수(35점) 획득!");
                            b[6].setText(""+(bouseScore+35)+"/("+bouseScore+"+35)");
                            b[6].setEnabled(true);
                            b[6].setForeground(Color.CYAN);
                            scoreS[6]=false;
                            bouseScore+=35;
                            totalScore+=35;
                            b[6].setFont(new Font("맑은 고딕", Font.BOLD, 15));
                        }
                        b[14].setText("" + totalScore);
                        for (int j = 0; j < 5; j++) {
                            randDice[j].setIcon(diceUser[j][0]);
                            keepDice[j].setSelected(false);
                        }
                    }else if(e.getSource()==b[6]){JOptionPane.showMessageDialog(null,"잘못된 선택입니다.)");
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"한번 선택한 점수는 다시 얻을수 없습니다.");
                    }
                }
                //엔딩 구현부 짜야됨
                if(turnCounter==14) {
                    int ending = JOptionPane.showOptionDialog(null, "게임이 끝났습니다. 최종점수는" + totalScore + "점입니다. 기록을 남기실려면 기록을 눌러주세요", "Game set", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,ender,"기록");
                    if (ending == 0) {
                        String name =JOptionPane.showInputDialog("기록할 이름을 입력해주세요");
                        try {
                            scoreWrite(name,totalScore);
                        }catch(IOException evt){
                            JOptionPane.showMessageDialog(null,"기록에 실패하였습니다.");
                            j.dispose();
                            test.interrupt();
                        }
                        JOptionPane.showMessageDialog(null,"기록을 성공하였습니다.");
                        j.dispose();
                        test.interrupt();
                    } else if (ending == 1) {
                        j.dispose();
                        test.interrupt();
                    }
                }
            }
            for(int i=0;i<5;i++){
                if(e.getSource()==randDice[i]){
                    if(keepDice[i].isSelected()==false) {
                        System.out.println("1");
                        Keeping(i);
                        keepDice[i].setSelected(true);
                    }
                    else{
                        deKeeping(i);
                        keepDice[i].setSelected(false);
                    }
                }
            }
        }
        public int scoreAces(int d1,int d2,int d3,int d4,int d5){
            int sum=0;
            if(d1==1){ sum+=1; }
            if(d2==1){ sum+=1;}
            if(d3==1){sum+=1;}
            if(d4==1){sum+=1;}
            if(d5==1){sum+=1;}
            return sum;
        }
        public int scoreDeuces(int d1,int d2,int d3,int d4,int d5){
            int sum=0;
            if(d1==2){ sum+=2; }
            if(d2==2){ sum+=2;}
            if(d3==2){sum+=2;}
            if(d4==2){sum+=2;}
            if(d5==2){sum+=2;}
            return sum;
        }
        public int scoreThrees(int d1,int d2,int d3,int d4,int d5){
            int sum=0;
            if(d1==3){ sum+=3; }
            if(d2==3){ sum+=3;}
            if(d3==3){sum+=3;}
            if(d4==3){sum+=3;}
            if(d5==3){sum+=3;}
            return sum;
        }
        public int scoreFours(int d1,int d2,int d3,int d4,int d5){
            int sum=0;
            if(d1==4){ sum+=4; }
            if(d2==4){ sum+=4;}
            if(d3==4){sum+=4;}
            if(d4==4){sum+=4;}
            if(d5==4){sum+=4;}
            return sum;
        }
        public int scoreFives(int d1,int d2,int d3,int d4,int d5){
            int sum=0;
            if(d1==5){ sum+=5; }
            if(d2==5){ sum+=5;}
            if(d3==5){sum+=5;}
            if(d4==5){sum+=5;}
            if(d5==5){sum+=5;}
            return sum;
        }
        public int scoreSixes(int d1,int d2,int d3,int d4,int d5){
            int sum=0;
            if(d1==6){ sum+=6; }
            if(d2==6){ sum+=6;}
            if(d3==6){sum+=6;}
            if(d4==6){sum+=6;}
            if(d5==6){sum+=6;}
            return sum;
        }
        public int scoreChoice(int d1,int d2,int d3,int d4,int d5){
            int sum=0;
            sum=sum+d1+d2+d3+d4+d5;
            return sum;
        }
        public int scoreToker(int d1,int d2,int d3,int d4,int d5,boolean a){
            int sum=0;
            if(a&&Tokercheck(d1,d2,d3,d4,d5)){
                sum=sum+d1+d2+d3+d4+d5;
            }
            return sum;
        }
        public boolean Tokercheck(int d1,int d2,int d3,int d4,int d5){
            int temp;
            int[] data=new int[5];
            for(int i=0;i<5;i++){
                if(i==0) data[i]=d1;
                if(i==1) data[i]=d2;
                if(i==2) data[i]=d3;
                if(i==3) data[i]=d4;
                if(i==4) data[i]=d5;
            }
            //정렬하는 코드
            for(int j =0;j<5;j++) {
                for (int i = j+1; i < 5; i++) {
                    if (data[j]> data[i]) {
                        temp=data[i];
                        data[i] = data[j];
                        data[j] = temp;
                    }
                }
            }
            for(int j =0;j<3;j++) {
                if(data[j]==data[j+1]&&data[j]==data[j+2]) {
                    return true;
                }
            }
            return false;
        }
        public int scoreFoker(int d1,int d2,int d3,int d4,int d5,boolean a){
            int sum=0;
            if(a&&Fokercheck(d1,d2,d3,d4,d5)){
                sum = sum+d1+d2+d3+d4+d5;
            }
            return sum;
        }
        public boolean Fokercheck(int d1,int d2,int d3,int d4,int d5){
            int temp=0;
            int[] data=new int[5];
            for(int i=0;i<5;i++){
                if(i==0) data[i]=d1;
                if(i==1) data[i]=d2;
                if(i==2) data[i]=d3;
                if(i==3) data[i]=d4;
                if(i==4) data[i]=d5;
            }
            //정렬하는 코드
            for(int j =0;j<5;j++) {
                for (int i = j+1; i < 5; i++) {
                    if (data[j]> data[i]) {
                        temp=data[i];
                        data[i] = data[j];
                        data[j] = temp;
                    }
                }
            }
            for(int j =0;j<2;j++) {
                if(data[j]==data[j+1]&&data[j]==data[j+2]&&data[j]==data[j+3]) {
                    return true;
                }
            }
            return false;
        }
        public int scoreFullHouse(int d1,int d2,int d3,int d4,int d5,boolean a){
            int sum=0;
            if(a&&FHcheck(d1,d2,d3,d4,d5)) {
                sum = sum+d1+d2+d3+d4+d5;
            }
            return sum;
        }
        public boolean FHcheck(int d1,int d2,int d3,int d4,int d5){
            int temp=0;
            int[] data=new int[5];
            for(int i=0;i<5;i++){
                if(i==0) data[i]=d1;
                if(i==1) data[i]=d2;
                if(i==2) data[i]=d3;
                if(i==3) data[i]=d4;
                if(i==4) data[i]=d5;
            }
            //정렬하는 코드
            for(int j =0;j<5;j++) {
                for (int i = j+1; i < 5; i++) {
                    if (data[j]> data[i]) {
                        temp=data[i];
                        data[i] = data[j];
                        data[j] = temp;
                    }
                }
            }

            if (data[0]==data[1]){
                if(data[0]==data[2]){
                    if(data[3]==data[4]) return true;
                }
                else{
                    if(data[2]==data[3]&&data[2]==data[4]) return true;
                }
            }

            return false;
        }
        public int scoreSmallStraight(int d1,int d2,int d3,int d4,int d5,boolean a){
            int sum=0;
            if(a&&SScheck(d1,d2,d3,d4,d5)){
                sum=30;
            }
            return sum;
        }
        public boolean SScheck(int d1,int d2,int d3,int d4,int d5){
            int temp;
            int[] data=new int[5];
            for(int i=0;i<5;i++){
                if(i==0) data[i]=d1;
                if(i==1) data[i]=d2;
                if(i==2) data[i]=d3;
                if(i==3) data[i]=d4;
                if(i==4) data[i]=d5;
            }
            //정렬하는 코드
            for(int j =0;j<5;j++) {
                for (int i = j+1; i < 5; i++) {
                    if (data[j]> data[i]) {
                        temp=data[i];
                        data[i] = data[j];
                        data[j] = temp;
                    }
                }
            }
            for(int i=0;i<3;i++){
                if(data[i]+1==data[i+1]&&data[i+1]+1==data[i+2]) return true;
            }
            return false;
        }
        public int scoreLargeStraight(int d1,int d2,int d3,int d4,int d5,boolean a){
            int sum=0;
            if(a&&LScheck(d1,d2,d3,d4,d5)){
                sum=40;
            }
            return sum;
        }
        public boolean LScheck(int d1,int d2,int d3,int d4,int d5){
            int temp;
            int[] data=new int[5];
            for(int i=0;i<5;i++){
                if(i==0) data[i]=d1;
                if(i==1) data[i]=d2;
                if(i==2) data[i]=d3;
                if(i==3) data[i]=d4;
                if(i==4) data[i]=d5;
            }
            //정렬하는 코드
            for(int j =0;j<5;j++) {
                for (int i = j+1; i < 5; i++) {
                    if (data[j]> data[i]) {
                        temp=data[i];
                        data[i] = data[j];
                        data[j] = temp;
                    }
                    else if(data[j]==data[i]) return false; //같은 값이 있으면 라스가 성립 불가
                }
            }
            if(4*data[2]==data[0]+data[1]+data[3]+data[4]){
                return true;
            }
            return false;
        }
        public int scoreYacht(int d1,int d2,int d3,int d4,int d5,boolean a){
            int score=0;
            if(a&&d1==d2&&d1==d3&&d1==d4&&d1==d5){
                score=50;
            }
            return score;
        }
        //특수한 점수을 얻을수 있을때 알림을 하는 역할을 합니다. 만약 얻을수 있는 특수한 점수가 여러개 존재할시 그중에서 가장 높은 점수인 종류를 알려줍니다.
        public void scoreNotice(){
            int result[]={
                    scoreToker(scoreDice[0], scoreDice[1], scoreDice[2], scoreDice[3], scoreDice[4],scoreS[8]),
                    scoreFoker(scoreDice[0], scoreDice[1], scoreDice[2], scoreDice[3], scoreDice[4],scoreS[9]),
                    scoreFullHouse(scoreDice[0], scoreDice[1], scoreDice[2], scoreDice[3], scoreDice[4],scoreS[10]),
                    scoreSmallStraight(scoreDice[0], scoreDice[1], scoreDice[2], scoreDice[3], scoreDice[4],scoreS[11]),
                    scoreLargeStraight(scoreDice[0], scoreDice[1], scoreDice[2], scoreDice[3], scoreDice[4],scoreS[12]),
                    scoreYacht(scoreDice[0], scoreDice[1], scoreDice[2], scoreDice[3], scoreDice[4],scoreS[13])
            };
            int tmp=0,notice=0;
            for(int i=0;i<6;i++){
                if(scoreS[i+8]&&result[i]>=tmp&&result[i]!=0){
                    tmp=result[i];
                    notice=i+1;
                }
            }
            if(notice==1)JOptionPane.showMessageDialog(null,"3 of Kind!!");
            if(notice==2)JOptionPane.showMessageDialog(null,"4 of Kind!!");
            if(notice==3)JOptionPane.showMessageDialog(null,"FullHouse!!");
            if(notice==4)JOptionPane.showMessageDialog(null,"Small Straight!!");
            if(notice==5)JOptionPane.showMessageDialog(null,"Large Straight!!");
            if(notice==6){
                Thread d=new YachtEffect();
                d.start();

            }
        }
        /*야추 달성시 effect*/
        class YachtEffect extends Thread{
            public void run() {
                for(int i=0;i<5;i++) {
                        x1[i] = keepX[i];
                        y1[i] = 280;
                        randDice[i].setBounds(x1[i], y1[i], 150, 150);
                        try {
                            Thread.sleep(250);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                }
                try{
                    JLabel Ya = new JLabel(eff);
                    Ya.setPreferredSize(new Dimension(600, 700));
                    Ya.setBounds(0, 0, 1000, 1000);
                    dicePanel.add(Ya);
                    JOptionPane.showMessageDialog(null,"Yacht!!");
                    Thread.sleep(500);
                    Ya.removeNotify();
                }catch(InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
       /* //주사위 애니메이션 메소드들
        public void testRolling(){
            int s=0;
            Timer timer =new Timer();
            TimerTask task =new TimerTask() {
                @Override
                public void run() {
                    for(int i=0;i<5;i++) randDice[i].setIcon(diceUser[i][random[i]]);
                    scoreNotice();
                    timer.cancel();
                }
            };
            for (int i = 0; i < 5; i++) {
                if (keepDice[i].isSelected() == false) {
                    random[i]=1;
                    s=Mix_X(s);
                    int x =setx[s][rand.nextInt(8)];
                    s=0;
                    s=Mix_Y(s);
                    int y = sety[s];
                    x1[i] = x;
                    y1[i] = y;
                    x2[i]=x;
                    y2[i]=y;
                    randDice[i].setBounds(x1[i],y1[i],150,150);
                    scoreDice[i]=random[i];
                    randDice[i].setIcon(diceUser[i][7]);
                }

            }
            timer.schedule(task,500);
        }*/
        //주사위 애니메이션 메소드들
        public void Rolling(){
            int s=0;
            Timer timer =new Timer();
            TimerTask task =new TimerTask() {
                @Override
                public void run() {
                    for(int i=0;i<5;i++) randDice[i].setIcon(diceUser[i][random[i]]);
                    scoreNotice();
                    timer.cancel();
                }
            };
            for (int i = 0; i < 5; i++) {
                if (keepDice[i].isSelected() == false) {
                    random[i]=rand.nextInt(6)+1;
                    s=Mix_X(s);
                    int x =setx[s][rand.nextInt(8)];
                    s=0;
                    s=Mix_Y(s);
                    int y = sety[s];
                    x1[i] = x;
                    y1[i] = y;
                    x2[i]=x;
                    y2[i]=y;
                    randDice[i].setBounds(x1[i],y1[i],150,150);
                    scoreDice[i]=random[i];
                    randDice[i].setIcon(diceUser[i][7]);
                }

            }
            timer.schedule(task,500);
        }
        public void Clear(){
            for(int i=0;i<5;i++){
                x1[i]=0;
                y1[i]=0;
                ranX[i]=true;
                ranY[i]=true;
            }
        }
        public int Mix_X(int d){
            d = rand.nextInt(5);
            if (ranX[d]){
                ranX[d]=false;
                return d;
            }
            return Mix_X(d);
        }
        public int Mix_Y(int d){
            d = rand.nextInt(8);
            if (ranY[d]){
                ranY[d] = false;
                return d;
            }
            return Mix_Y(d);
        }
        //keepDice애니메이션
        public void Keeping(int i){
            x1[i] = keepX[i];
            y1[i] = keepY;
            randDice[i].setBounds(x1[i], y1[i], 150, 150);
        }
        public void deKeeping(int i){
            randDice[i].setBounds(x2[i], y2[i], 150, 150);
        }
        /*기록을 하기위한 메소드*/
        public void scoreWrite(String name,int score)throws IOException{
            DataOutputStream write[] = {null,null,null};
            DataOutputStream write2= null;
            DataInputStream countFile= null;
            DataOutputStream countUP=null;
            int c=0;

            try{
                try{
                    countFile=new DataInputStream(new BufferedInputStream(new FileInputStream("utill/report/countFile.dat")));
                    c = countFile.readInt();
                }catch(IOException evt){
                    write2 = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("utill/report/countFile.dat")));
                    write2.writeInt(0);
                    c=0;
                }
               if(c>=50)c=c-50;
               write[0] = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("utill/report/name" + c + ".dat")));
               write[1] = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("utill/report/score" + c + ".dat")));
               write[2] = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("utill/report/date" + c + ".dat")));
               write[0].writeUTF(name);
               write[1].writeInt(score);
               write[2].writeUTF(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
               for (int i = 0; i < 3; i++) write[i].flush();
               countUP = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("utill/report/countFile.dat")));
               countUP.writeInt(c + 1);
               countUP.flush();
            }finally {
                if(write !=null) for(int i=0;i<3;i++) write[i].close();
                if(write2!=null) write2.close();
                if(countFile !=null)countFile.close();
                if(countUP !=null)countUP.close();
            }
        }
        class Backpanel extends JLayeredPane{
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(backImg,0,0,null);
                setOpaque(false);
                repaint();
            }
        }
        class BackpanelSub extends JPanel{
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(backImg,0,0,null);
                setOpaque(false);
                repaint();
            }
        }
        class SBackpanel extends JLayeredPane{
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(scoreImg,0,0,null);
                setOpaque(false);
                repaint();
            }
        }
        class countBackPanel extends JPanel{
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(countBG,0,0,null);
                g.drawImage(countBG2,0,150,null);
                setOpaque(false);
                repaint();
            }
        }
    }
}
