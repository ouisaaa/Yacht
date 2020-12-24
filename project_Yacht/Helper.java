package YachtDice;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class Helper extends JFrame implements MouseListener {
    private int page=0;
    private String howPlay ="���ߴ��̽� �⺻ ����\n\n"+"���� ������ ������ ���ߴ��̽��� ������ �˴ϴ�.\n"+"�׷����� Rolling�� ������ �������� ������ ������ �˴ϴ�.\n\n"+
            "�����ǿ��� �ֻ����� ������ ������ Ŭ���� �ϸ� ������ ��������� �ٲ�鼭 ������ ȹ���ϰ� �˴ϴ�.\n"+"�̶� �ѹ� ȹ���� ������ �ٽ� �������� �����ϴ�.\n"+
            "���� �Ҹ��� �ʹ� ũ�ų� �ʹ� ������ ���������� ������ ũ��� �Ҽ��ֽ��ϴ�.";
    private String playRulls="���ߴ��̽� �⺻�� ��\n 1. Rolling��ư�� ������ �ֻ��� 5���� ������ \n" +
            "2. ���߿��� ���ϴ� �ֻ������� ���ܵΰ� �ٽ� �������� �ִ� �ٽ� ������� �ι����� �����ϸ� �տ��� ������ �ʾҴ� �ֻ����� ���ϸ� �ٽ� ���� �� �� �ִ�\n"+
            "3. �̷��� �ؼ� ���� ���� �ݵ�� �����ǿ� ����ؾ��Ѵ�. ����� ĭ�� ���� ��� �ƹ�ĭ�� 0�� ����Ѵ�.(���ʽ� ĭ���� ä����� ����.)\n"
            +"4. �������� 13ĭ�̳� �� 14���带 �ϸ� ������ ���̳���. �̶� �������� ���� �������� ����� ���´�.";
    private String pedigree = "���� ���̽� ����\n"+"Aces: 1�� ���� �ֻ��� ���� ��\n"+ "Twos: 2�� ���� �ֻ��� ���� ��\n"+"Threes: 3�� ���� �ֻ��� ���� ��\n"+"Fours: 4�� ���� �ֻ��� ���� ��\n"+
            "Fives: 5�� ���� �ֻ��� ���� ��\n"+"Sixes: 6�� ���� �ֻ����� ���� ��\n"+"Bouse: ��� �׸񿡼� ���� 63���� ���� ��� 35���� �߰��Ѵ�.\n\n"+
            "Choice: �ֻ��� 5������ ����\n"+"3 of a kind: �ֻ��� 3���� ���� ������ ��, �ֻ��� 5���� ��\n"+"4 of a kind:�ֻ��� 4���� ���� ������ ��, �ֻ��� 5���� ��\n"+
            "Full House ���� ������ �ֻ����� ���� 3���� 2���� ���� ��, ���� 25��\n"+"Small Straight: �ֻ��� 4���� ���� �̾����� �� �϶�, ���� 30��\n"+
            "Large Straight: �ֻ����� 5���� ���� �̾����� �� �϶�, ���� 40��\n"+"Yacht: �ֻ���5���� ���� ��� ���� �� ����,50��";
    private String reportHelp= "��ϼ� ����\n"+"��ϼҴ� ������ �÷����ϰ� ������ ��ϵ��� �����ݴϴ�.\n"+"����� �̸�/����/�ð� ������ �����ݴϴ�.\n"+"������ �ִ� 50�� ������ �ǰ� ����� 50�� �̻��� �ȴٸ� ���� ���� ����� ���� �� ����� �մϴ�.\n"+
            "���� ���ʿ� �����ϴ� ����� �ְ����� �����ָ�, �ٷ� ���ʿ� �����ϴ� ����� �׵��� ������ �� ����� �����ִ� �κ��Դϴ�.\n"+
            "���� ������ ���� �� �׵����� ��ϵ��� txt���Ϸ� ������ �մϴ�. ������ ��δ� report/out/playRecord.txt�� �ǰ� �����̸��� playRecord.txt�Դϴ�.";
    private String[] helpText = {howPlay,playRulls,pedigree,reportHelp};
    JButton next = new JButton("����");
    JButton previous = new JButton("����");
    JButton out = new JButton("�ݱ�");
    public Helper(int p){
        page=p;
        JFrame j = new JFrame("����");
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
