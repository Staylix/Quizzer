package com.gentil.gregoire.insaturne.model;

import java.util.Collections;
import java.util.List;

/**
 * Created by Gregoire on 13/11/2017.
 */

public class QuestionBank {
    private List<Question> mQuestionList;
    private int mNextQuestionIndex;

    public QuestionBank(List<Question> questionList) {
        mQuestionList = questionList;
        // Shuffle the question list before storing it
        Collections.shuffle(mQuestionList);
        mNextQuestionIndex = 0;
    }

    public Question getQuestion() {
        if (mNextQuestionIndex == mQuestionList.size())
            mNextQuestionIndex = 0;
        return mQuestionList.get(mNextQuestionIndex++);
    }
}
