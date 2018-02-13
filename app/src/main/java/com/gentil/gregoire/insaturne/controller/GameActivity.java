/* Version du 21/11 */

package com.gentil.gregoire.insaturne.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gentil.gregoire.insaturne.R;
import com.gentil.gregoire.insaturne.model.Question;
import com.gentil.gregoire.insaturne.model.QuestionBank;
import com.gentil.gregoire.insaturne.model.User;

import java.util.Arrays;



/**
 * Class that manage game logic
 */
public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    // Attributes

    private TextView mQuestionText;
    private Button mAnswer1Button;
    private Button mAnswer2Button;
    private Button mAnswer3Button;
    private Button mAnswer4Button;

    private String firstName;

    private QuestionBank mQuestionBank;

    private Question mCurrentQuestion;

    private int mNumberQuestions;
    private int mScore;

    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";

    public static final String BUNDLE_STATE_SCORE = "currentScore";
    public static final String BUNDLE_STATE_QUESTION = "currentQuestion";

    //  Methods

    private QuestionBank generateQuestions() {
        Question question1 = new Question("What is the name of the current french president?",
                Arrays.asList("François Hollande", "Emmanuel Macron", "Jacques Chirac", "François Mitterand"),
                1);

        Question question2 = new Question("How many countries are there in the European Union?",
                Arrays.asList("15", "24", "28", "32"),
                2);

        Question question3 = new Question("Who is the creator of the Android operating system?",
                Arrays.asList("Andy Rubin", "Steve Wozniak", "Jake Wharton", "Paul Smith"),
                0);

        Question question4 = new Question("When did the first man land on the moon?",
                Arrays.asList("1958", "1962", "1967", "1969"),
                3);

        Question question5 = new Question("What is the capital of Romania?",
                Arrays.asList("Bucarest", "Warsaw", "Budapest", "Berlin"),
                0);

        Question question6 = new Question("Who did the Mona Lisa paint?",
                Arrays.asList("Michelangelo", "Leonardo Da Vinci", "Raphael", "Carravagio"),
                1);

        Question question7 = new Question("In which city is the composer Frédéric Chopin buried?",
                Arrays.asList("Strasbourg", "Warsaw", "Paris", "Moscow"),
                2);

        Question question8 = new Question("What is the country top-level domain of Belgium?",
                Arrays.asList(".bg", ".bm", ".bl", ".be"),
                3);

        Question question9 = new Question("What is the house number of The Simpsons?",
                Arrays.asList("42", "101", "666", "742"),
                3);

        return new QuestionBank(Arrays.asList(question1,
                question2,
                question3,
                question4,
                question5,
                question6,
                question7,
                question8,
                question9));
    }

    private void displayQuestion(final Question question) {
        mQuestionText.setText(question.getQuestion());
        mAnswer1Button.setText(question.getChoiceList().get(0));
        mAnswer2Button.setText(question.getChoiceList().get(1));
        mAnswer3Button.setText(question.getChoiceList().get(2));
        mAnswer4Button.setText(question.getChoiceList().get(3));
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mQuestionText = findViewById(R.id.activity_game_question_text);
        mAnswer1Button = findViewById(R.id.activity_game_answer1_btn);
        mAnswer2Button = findViewById(R.id.activity_game_answer2_btn);
        mAnswer3Button = findViewById(R.id.activity_game_answer3_btn);
        mAnswer4Button = findViewById(R.id.activity_game_answer4_btn);

        mQuestionBank = this.generateQuestions();


        mCurrentQuestion = mQuestionBank.getQuestion();

        this.displayQuestion(mCurrentQuestion);


        mAnswer1Button.setOnClickListener(this);
        mAnswer2Button.setOnClickListener(this);
        mAnswer3Button.setOnClickListener(this);
        mAnswer4Button.setOnClickListener(this);

        mAnswer1Button.setTag(0);
        mAnswer2Button.setTag(1);
        mAnswer3Button.setTag(2);
        mAnswer4Button.setTag(3);

        if (savedInstanceState != null) {
            mScore = savedInstanceState.getInt(BUNDLE_STATE_SCORE);
            mNumberQuestions = savedInstanceState.getInt(BUNDLE_STATE_QUESTION);
        } else {
            mScore = 0;
            mNumberQuestions = 5;
        }

    }




    @Override
    public void onClick(View v) {

        int responseIndex = (int) v.getTag();

        if (responseIndex == mCurrentQuestion.getAnswerIndex()) {
            mScore++;
            if (mNumberQuestions > 1) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Well done!")
                        .setMessage("Your actual score is " + mScore)
                        .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                closeContextMenu();
                            }
                        })
                        .create()
                        .show();
            }
        }
        else {
            if (mNumberQuestions > 1) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Wrong!")
                        .setMessage("Your actual score is " + mScore)
                        .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                closeContextMenu();
                            }
                        })
                        .create()
                        .show();
            }
        }

        if (-- mNumberQuestions > 0)
        {
            mCurrentQuestion = mQuestionBank.getQuestion();
            displayQuestion(mCurrentQuestion);
        }
        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Finish !")
                    .setMessage("Your score is " + mScore)
                    .setPositiveButton("End", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent();
                            intent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    })
                    .create()
                    .show();
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putInt(BUNDLE_STATE_SCORE, mScore);
        outState.putInt(BUNDLE_STATE_QUESTION, mNumberQuestions);

        super.onSaveInstanceState(outState);
    }
}
