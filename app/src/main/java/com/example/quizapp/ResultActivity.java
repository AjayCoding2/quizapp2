package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
Button btnreturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
btnreturn=findViewById(R.id.btnrtn);
        TextView resultlabel=(TextView) findViewById(R.id.resultlabel);
        TextView totalScorelabel= (TextView) findViewById(R.id.totalscorelabel);

        int score = getIntent().getIntExtra("RIGHT_ANSWER_COUNT",0);
        SharedPreferences settings = getSharedPreferences("quizapp", Context.MODE_PRIVATE);
        int totalScore = settings.getInt("totalScore", 0);
        totalScore += score;


        resultlabel.setText(score+"/15");
        totalScorelabel.setText("Total score:"+totalScore);


        //update total score
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("totalscore",totalScore);

        editor.commit();
        btnreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

}