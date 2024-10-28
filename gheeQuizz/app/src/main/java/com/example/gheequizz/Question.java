package com.example.gheequizz;

public class Question {
    private int ques;
    private boolean result;

    public void setQues(int ques) {
        this.ques = ques;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getQues() {
        return ques;
    }

    public boolean isResult() {
        return result;
    }

    public Question(int ques, boolean result) {
        this.ques = ques;
        this.result = result;
    }
}
