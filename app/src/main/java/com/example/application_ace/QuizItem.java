package com.example.application_ace;

public class QuizItem {
    private final String question;
    private final boolean answer; // true = O, false = X
    private final String explanation;

    public QuizItem(String question, boolean answer, String explanation) {
        this.question = question;
        this.answer = answer;
        this.explanation = explanation;
    }

    public String getQuestion() {
        return question;
    }

    public boolean getAnswer() {
        return answer;
    }

    public String getExplanation() {
        return explanation;
    }
}
