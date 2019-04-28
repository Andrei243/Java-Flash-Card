package com.Mandel.swing;


class FlashCard {

    private String question;
    private String answer;


    FlashCard(String x,String y){

        question=x;
        answer=y;

    }
    String getQuestion() {
        return question;
    }


    String getAnswer() {
        return answer;
    }

}