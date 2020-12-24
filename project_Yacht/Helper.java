package YachtDice;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class Helper extends JFrame implements MouseListener {
    private int page=0;
    private String howPlay ="야추다이스 기본 설명서\n\n"+"게임 시작을 누르면 야추다이스가 실행이 됩니다.\n"+"그런다음 Rolling를 누르면 본격적인 게임이 시작이 됩니다.\n\n"+
            "점수판에서 주사위를 돌리면 점수를 클릭을 하면 배경색이 노란색으로 바뀌면서 점수를 획득하게 됩니다.\n"+"이때 한번 획득한 점수는 다시 돌릴수가 없습니다.\n"+
            "게임 소리가 너무 크거나 너무 작으면 볼륨조절을 적당한 크기로 할수있습니다.";
    private String playRulls="야추다이스 기본적 룰\n 1. Rolling버튼을 눌러서 주사위 5개를 던진다 \n" +
            "2. 이중에서 원하는 주사위들은 남겨두고 다시 던질수가 있다 다시 던지기는 두번까지 가능하며 앞에서 던지지 않았던 주사위도 원하면 다시 던질 수 가 있다\n"+
            "3. 이렇게 해서 나온 값을 반드시 점수판에 기록해야한다. 기록할 칸이 없는 경우 아무칸에 0을 기록한다.(보너스 칸에는 채울수가 없다.)\n"
            +"4. 점수판이 13칸이나 총 14라운드를 하면 게임이 끝이난다. 이때 점수판의 점수 총합으로 기록이 나온다.";
    private String pedigree = "야추 다이스 족보\n"+"Aces: 1이 나온 주사위 눈의 합\n"+ "Twos: 2가 나온 주사위 눈의 합\n"+"Threes: 3이 나온 주사위 눈의 합\n"+"Fours: 4가 나온 주사위 눈의 합\n"+
            "Fives: 5가 나온 주사위 눈의 합\n"+"Sixes: 6이 나온 주사위의 눈의 합\n"+"Bouse: 상단 항목에서 합이 63점이 넘을 경우 35점을 추가한다.\n\n"+
            "Choice: 주사위 5개눈의 총합\n"+"3 of a kind: 주사위 3개의 눈이 동일할 때, 주사위 5개의 합\n"+"4 of a kind:주사위 4개의 눈이 동일할 때, 주사위 5개의 합\n"+
            "Full House 눈이 동일한 주사위가 각각 3개와 2개가 있을 때, 고정 25점\n"+"Small Straight: 주사위 4개의 눈이 이어지는 수 일때, 고정 30점\n"+
            "Large Straight: 주사위의 5개의 눈이 이어지는 수 일때, 고정 40점\n"+"Yacht: 주사위5개의 눈이 모두 같을 때 고정,50점";
    private String reportHelp= "기록소 도움말\n"+"기록소는 게임을 플레이하고 저장한 기록들을 보여줍니다.\n"+"기록은 이름/점수/시간 순으로 보여줍니다.\n"+"점수는 최대 50개 저장이 되고 기록이 50개 이상이 된다면 가장 옛날 기록을 삭제 후 기록을 합니다.\n"+
            "가장 위쪽에 존재하는 기록은 최고기록을 보여주며, 바로 밑쪽에 존재하는 기록은 그동한 저장을 한 기록을 보여주는 부분입니다.\n"+
            "파일 생성을 누를 시 그동안의 기록들을 txt파일로 저장을 합니다. 파일의 경로는 report/out/playRecord.txt가 되고 파일이름은 playRecord.txt입니다.";
    private String[] helpText = {howPlay,playRulls,pedigree,reportHelp};
    JButton next = new JButton("다음");
    JButton previous = new JButton("이전");
    JButton out = new JButton("닫기");
    public Helper(int p){
        page=p;
        JFrame j = new JFrame("도움말");
        j.setLayout(new BorderLayout());
        JPanel help = new JPanel();
        JPanel but = new JPanel(new BorderLayout());
        JTextArea commant= new JTextArea();
        commant.setPreferredSize(new Dimension(480,400));
        commant.setEnabled(false);
        commant.setLineWrap(true);
        commant.setText(helpText[p]);
        commant.setBackground(Color.BLACK);
        commant.setForeground(Color.WHITE);

        next.setPreferredSize(new Dimension(245,30));
        previous.setPreferredSize(new Dimension(245,30));

        j.setBackground(new Color(76,63,55));
        help.setBackground(new Color(76,63,55));
        but.setBackground(new Color(76,63,55));
        if(page==0){
            previous.setEnabled(false);
        }
        if(page==3){
            next.setEnabled(false);
        }
        but.add(previous,"West"); but.add(next,"East");
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                page+=1;
                if(page!=0){
                   previous.setEnabled(true);
                }
                if(page==3){
                    next.setEnabled(false);
                }
                commant.setText(helpText[page]);

            }
        });
        previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                page-=1;
                if(page!=0){
                    previous.setEnabled(true);
                }
                else{
                    previous.setEnabled(false);
                }
                if(page!=3){
                    next.setEnabled(true);
                }
                commant.setText(helpText[page]);
            }
        });
        help.add(commant);
        j.add(but,"South");
        out.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
            }
        });
        j.add(help,"Center");
        but.add(out,"South");
        next.addMouseListener(this);
        previous.addMouseListener(this);
        out.addMouseListener(this);
        previous.setBackground(new Color(247,252,185));
        next.setBackground(new Color(247,252,185));
        out.setBackground(new Color(247,252,185));
        j.setVisible(true);
        j.setSize(500,500);
        j.setLocationRelativeTo(null);
    }
    @Override
    public void mouseClicked(MouseEvent e) { }
    @Override
    public void mousePressed(MouseEvent e) { }
    @Override
    public void mouseReleased(MouseEvent e) { }
    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource()==previous&&page!=0)previous.setBackground(new Color(120,198,121));
        if(e.getSource()==next&&page!=3) next.setBackground(new Color(120,198,121));
        if(e.getSource()==out)out.setBackground(new Color(120,198,121));
    }
    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==previous) previous.setBackground(new Color(247,252,185));
        if(e.getSource()==next) next.setBackground(new Color(247,252,185));
        if(e.getSource()==out)out.setBackground(new Color(247,252,185));
    }
}
