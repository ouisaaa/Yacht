package YachtDice;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class TotalMenu extends JFrame{
    public TotalMenu(){
        JFrame j = new JFrame("Yacht Dice");
        j.setLayout(new BorderLayout());
        JPanel titlePanel= new JPanel();
        JLabel title=new JLabel("Yacht Dice!");
        title.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.setBackground(Color.BLACK);
        title.setForeground(Color.WHITE);
        titlePanel.setOpaque(true);
        title.setFont(new Font("�ü�ü",Font.BOLD,60));
        titlePanel.add(title);
        JLabel subtitle = new JLabel("");
        subtitle.setHorizontalAlignment(JLabel.CENTER);
        subtitle.setFont(new Font("�ü�ü",30,30));
        subtitle.setForeground(Color.WHITE);
        titlePanel.setLayout(new GridLayout(3,0));
        titlePanel.add(subtitle);
        j.add(titlePanel,"Center");

        /*�޴�*/
        JPanel mainMenu = new JPanel();
        JButton yacht = new JButton("����!");
        JButton report = new JButton("��ϼ�");
        JButton help = new JButton("����");
        JButton exit = new JButton("����");
        yacht.setBackground(Color.YELLOW);
        exit.setBackground(Color.YELLOW);
        help.setBackground(Color.YELLOW);
        report.setBackground(Color.YELLOW);
        mainMenu.add(yacht); mainMenu.add(report); mainMenu.add(help);mainMenu.add(exit);
        mainMenu.setLayout(new GridLayout(0,4));
        mainMenu.setBackground(Color.BLACK);
        j.add(mainMenu,"South");

        j.setDefaultCloseOperation(EXIT_ON_CLOSE);
        j.setSize(600,600);
        j.setLocationRelativeTo(null);
        j.setVisible(true);


        yacht.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ask = JOptionPane.showConfirmDialog(null,"yacht�� ������ �˴ϴ�.","���� ��",JOptionPane.YES_NO_OPTION);
                if(ask==JOptionPane.CLOSED_OPTION){

                }
                else if(ask==JOptionPane.YES_OPTION){
                    YachtDice y = new YachtDice();

                }
                else {

                }
            }
        });
        yacht.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) { }
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) {
                yacht.setBackground(Color.ORANGE);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                yacht.setBackground(Color.YELLOW);
            }
        });
        report.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ask = JOptionPane.showConfirmDialog(null,"��ϼҰ� ������ �˴ϴ�.","���� ��",JOptionPane.YES_NO_OPTION);
                if(ask==JOptionPane.CLOSED_OPTION){

                }
                else if(ask==JOptionPane.YES_OPTION){
                    Reporter r= new Reporter();
                }
                else {

                }
            }
        });
        report.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) { }
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) {
                report.setBackground(Color.ORANGE);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                report.setBackground(Color.YELLOW);
            }
        });
        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ask = JOptionPane.showConfirmDialog(null,"���򸻰� ������ �˴ϴ�.","���� ��",JOptionPane.YES_NO_OPTION);
                if(ask==JOptionPane.CLOSED_OPTION){

                }
                else if(ask==JOptionPane.YES_OPTION){
                    Helper h= new Helper(0);
                }
                else {

                }
            }
        });
        help.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) { }
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) {
                help.setBackground(Color.ORANGE);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                help.setBackground(Color.YELLOW);
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ask = JOptionPane.showConfirmDialog(null,"������ �����մϱ�?.","����?",JOptionPane.YES_NO_OPTION);
                if(ask==JOptionPane.CLOSED_OPTION){

                }
                else if(ask==JOptionPane.YES_OPTION){
                    System.exit(0);
                }
                else {

                }
            }
        });
        exit.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) { }
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) {
                exit.setBackground(Color.ORANGE);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                exit.setBackground(Color.YELLOW);
            }
        });
    }
    public static void main(String[] args){
        new TotalMenu();
    }
}

