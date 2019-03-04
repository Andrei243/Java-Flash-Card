package com.Mandel.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.channels.FileLockInterruptionException;
import java.util.ArrayList;

public class FlashCardPlayer {

   private ArrayList<FlashCard> flashCards;
   private boolean este_intrebare;
   private FlashCard card_actual;
   private JTextArea jTextArea;

    FlashCardPlayer(){
        flashCards=new ArrayList<>();
        jTextArea=new JTextArea(10,15);


        JMenuBar jMenuBar=new JMenuBar();
        JMenu jMenu=new JMenu("File");
        JMenuItem jMenuItem=new JMenuItem("Open file");
        jMenu.add(jMenuItem);
        jMenuBar.add(jMenu);



        JFrame jframe=new JFrame("Flash Card Viewer");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setLayout(new FlowLayout(FlowLayout.LEFT));
        jframe.setSize(1200,1080);
        jframe.setVisible(true);
        jframe.setJMenuBar(jMenuBar);


        JPanel jPanel=new JPanel();
        JScrollPane jScrollPane=new JScrollPane(jTextArea);
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);


        jPanel.add(jScrollPane);

        JButton jButton=new JButton("Urmatoarea carte");

        jPanel.add(jButton);
        jframe.add(jPanel);





        jMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                JFileChooser jFileChooser=new JFileChooser();
                jFileChooser.showOpenDialog(jframe);
                OPENFILE(jFileChooser.getSelectedFile());


            }
        });








    }

    private void OPENFILE(File selectedFile) {


        try (FileReader fileReader = new FileReader(selectedFile)) {
            String aux;

            BufferedReader bufferedReader=new BufferedReader(fileReader);
            do{

                aux=bufferedReader.readLine();

                String aux1,aux2;
                aux1=aux.substring(0,aux.lastIndexOf("\\"));
                aux2=aux.substring(aux.lastIndexOf("\\")+1,aux.length());
                flashCards.add(new FlashCard(aux1,aux2));


            }while(aux!="/");

            for (FlashCard i: flashCards) {
                System.out.println(i.getQuestion()+"  "+i.getAnswer()+"\n");

            }


        }
        catch(Exception e){
            System.out.println(e.fillInStackTrace());
        }


    }


    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                new FlashCardPlayer();

            }
        });


    }



}
