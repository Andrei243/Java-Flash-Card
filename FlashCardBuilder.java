package com.Mandel.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class FlashCardBuilder {
    private JTextArea question;
    private JTextArea answer;
    private ArrayList<FlashCard> flashCards;
    private JFrame jFrame;




    private FlashCardBuilder(){


        JPanel jPanel=new JPanel();
        jPanel.setLayout(new FlowLayout(FlowLayout.LEFT));





        flashCards=new ArrayList<>();
        jFrame=new JFrame("Flash Card Create");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(1200,1080);
        jFrame.setVisible(true);
        FlowLayout flowLayout=new FlowLayout(FlowLayout.LEFT);
        Font grit=new Font("Helvetica Neue",Font.BOLD,20);


        question=new JTextArea(15,15);
        question.setFont(grit);
        question.setText("");
        question.setLineWrap(true);
        question.setWrapStyleWord(true);

        answer=new JTextArea(15,15);
        answer.setFont(grit);
        answer.setText("");
        answer.setLineWrap(true);
        answer.setWrapStyleWord(true);

        jFrame.setLayout(flowLayout);

        JLabel jLabel=new JLabel("Intrebare");
        JLabel jLabel1=new JLabel("Raspuns");


        JScrollPane ajScrollPane=new JScrollPane(answer);
        ajScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        ajScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);


        JScrollPane bjScrollPane=new JScrollPane(question);
        bjScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        bjScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        jPanel.add(jLabel);
        jPanel.add(bjScrollPane);
        jPanel.add(jLabel1);
        jPanel.add(ajScrollPane);

        JButton jButton=new JButton("Adauga FlashCard");

        jButton.setSize(20,70);

        jPanel.add(jButton);

        JLabel jLabel2=new JLabel("");
        JLabel jLabel3=new JLabel("");


        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (question.getText().length() == 0) {
                    jLabel2.setText("Nu ai introdus nicio intrebare");
                }
                if (answer.getText().length() == 0) {
                    jLabel3.setText("Nu ai introdus niciun raspuns");
                }

                if (question.getText().length() > 0) {
                    jLabel2.setText("");
                }
                if (answer.getText().length() > 0) {
                    jLabel3.setText("");
                }

                if (answer.getText().length() > 0 && question.getText().length() > 0) {
                    FlashCard flashCard = new FlashCard(question.getText(), answer.getText());
                    flashCards.add(flashCard);
                    question.setText("");
                    answer.setText("");
                    question.requestFocus();

                }


            }
        });
        jPanel.add(jLabel2);
        jPanel.add(jLabel3);


        JMenuBar jMenuBar=new JMenuBar();
        JMenu jMenu=new JMenu("File");
        JMenuItem jMenuItem=new JMenuItem("New");
        JMenuItem sMenuItem=new JMenuItem("Save");
        jMenu.add(jMenuItem);
        jMenu.add(sMenuItem);
        jMenuBar.add(jMenu);



        sMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if (answer.getText().length() > 0 && question.getText().length() > 0) {
                    FlashCard flashCard = new FlashCard(question.getText(), answer.getText());
                    flashCards.add(flashCard);
                    question.setText("");
                    answer.setText("");


                }


                JFileChooser savefile = new JFileChooser();
                savefile.showSaveDialog(jFrame);

                FlashCardBuilder.this.SAVEFILE(savefile.getSelectedFile());


            }
        });


        jMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flashCards.clear();
            }
        });


        sMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        jFrame.setJMenuBar(jMenuBar);
        jFrame.getContentPane().add(BorderLayout.CENTER,jPanel);


    }

    private void SAVEFILE(File selectedFile) {

        try
        {
            BufferedWriter writer=new BufferedWriter(new FileWriter(selectedFile));
            flashCards.add(new FlashCard("",""));

            for (FlashCard x: flashCards
                 ) {

                writer.write(x.getQuestion()+'\\'+x.getAnswer()+'\n');


            }
            flashCards.remove(flashCards.size()-1);
                writer.close();


        }
        catch(Exception e)
        {
            System.out.println("nu s-a putut scrie");

        }

    }

    public static void main(String[] args)
    {


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FlashCardBuilder();
            }
        });
    }
}