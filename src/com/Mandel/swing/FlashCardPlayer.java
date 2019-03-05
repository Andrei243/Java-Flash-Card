package com.Mandel.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
//import java.nio.channels.FileLockInterruptionException;
import java.util.ArrayList;

public class FlashCardPlayer {

   private ArrayList<FlashCard> flashCards;
   private JTextArea jTextArea1;
   private JTextArea jTextArea2;
   private int flasCardActual;

    FlashCardPlayer(){
        flasCardActual = 0;
        flashCards=new ArrayList<>();
        jTextArea1=new JTextArea(10,15);
        jTextArea2=new JTextArea(10,15);


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


        JPanel jPanel1=new JPanel();
        JScrollPane jScrollPane=new JScrollPane(jTextArea1);
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);


        jPanel1.add(jScrollPane);

        JButton jButton1=new JButton("Afiseaza raspunsul");

        jPanel1.add(jButton1);
        jframe.add(jPanel1);
        JPanel jPanel2=new JPanel();
        JScrollPane jScrollPane2=new JScrollPane(jTextArea2);
        jScrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        JButton jButton2=new JButton("Urmatoarea carte");

        jPanel2.add(jScrollPane2);
        jPanel2.add(jButton2);
        jframe.add(jPanel2);




        jMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                JFileChooser jFileChooser=new JFileChooser();
                jFileChooser.showOpenDialog(jframe);
                OPENFILE(jFileChooser.getSelectedFile());
                jTextArea1.setText(flashCards.get(0).getQuestion());

            }
        });


        //int text_actual=1;

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextArea2.setText(flashCards.get(flasCardActual%(flashCards.size()-1)).getAnswer());


            }
        });

                jButton2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        flasCardActual++;
                        jTextArea1.setText(flashCards.get(flasCardActual%(flashCards.size()-1)).getQuestion());
                        jTextArea2.setText("");

                    }
                }

        );







    }

    private void OPENFILE(File selectedFile) {


        try (FileReader fileReader = new FileReader(selectedFile)) {
            String aux;

            BufferedReader bufferedReader=new BufferedReader(fileReader);
            do{

                aux=bufferedReader.readLine();

                String aux1,aux2;
                aux1=aux.substring(0,aux.lastIndexOf("\\"));
                aux2=aux.substring(aux.lastIndexOf("\\")+1);
                flashCards.add(new FlashCard(aux1,aux2));


            }while(!aux.equals("\\"));

            for (FlashCard i: flashCards) {
                System.out.println(i.getQuestion()+"  "+i.getAnswer());

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
