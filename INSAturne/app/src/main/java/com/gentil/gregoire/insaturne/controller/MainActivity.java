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

    User mUser;


    // Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelloText = (TextView) findViewById(R.id.activity_main_hello_text);
        mHowText = (TextView) findViewById(R.id.activity_main_how_text);
        mBlazeInput = (EditText) findViewById(R.id.activity_main_blaze_input);
        mPlayButton = (Button) findViewById(R.id.activity_main_play_btn);

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

            }
        });


        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameActivity = new Intent(MainActivity.this, GameActivity.class);
                startActivity(gameActivity);
                mUser.setFirstName(mBlazeInput.getText().toString());
            }
        });
    }
}
