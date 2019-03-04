package com.Mandel.swing;

public class FlashCard {

    private String question;
    private String answer;

    public FlashCard() {

    }

    public FlashCard(String x,String y){

        question=x;
        answer=y;

    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}