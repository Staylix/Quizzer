/* Version du 21/11 */

package com.gentil.gregoire.insaturne.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gentil.gregoire.insaturne.R;
import com.gentil.gregoire.insaturne.model.User;

public class MainActivity extends AppCompatActivity {


    // Attributes

    private TextView mHelloText;
    private TextView mHowText;
    private EditText mBlazeInput;
    private Button mPlayButton;

    public User mUser;

    private static final int GAME_ACTIVITY_REQUEST_CODE = 42;


    // Methods

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            // Fetch the score from the Intent
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelloText = findViewById(R.id.activity_main_hello_text);
        mHowText = findViewById(R.id.activity_main_how_text);
        mBlazeInput = findViewById(R.id.activity_main_blaze_input);
        mPlayButton = findViewById(R.id.activity_main_play_btn);

        mUser = new User();

        mPlayButton.setEnabled(false);
        
        mBlazeInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPlayButton.setEnabled(s.toString().length() > 0);

            }

            @Override
            public void afterTextChanged(Editable s) {
                String temp = "Welcome " + s.toString() + " !";
                mHelloText.setText(temp);
            }
        });



        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUser.setFirstName(mBlazeInput.getText().toString());
                Intent gameActivity = new Intent(MainActivity.this, GameActivity.class);
                gameActivity.putExtra("firstName", mUser.getFirstName());
                startActivityForResult(gameActivity, GAME_ACTIVITY_REQUEST_CODE);
            }
        });
    }
}
