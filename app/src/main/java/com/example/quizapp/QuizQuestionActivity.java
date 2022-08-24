package com.example.quizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class QuizQuestionActivity extends AppCompatActivity {
     TextView countlabel;
     TextView questionlabel;
    Button ansbtn1;
    Button ansbtn2;
    Button ansbtn3;
    Button ansbtn4;




    private String rightAnswer;
    private int rightAnswerCount=0;
    private int quizCount=1;
    int i;
    static final private int Quiz_COUNT=15;


    ArrayList<ArrayList<String>>  quizArray = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_question);

        countlabel = (TextView) findViewById(R.id.countlabel);
        questionlabel = (TextView) findViewById(R.id.questionlabel);
        ansbtn1 =findViewById(R.id.ansbtn1);
        ansbtn2 = findViewById(R.id.ansbtn2);
        ansbtn3 = findViewById(R.id.ansbtn3);
        ansbtn4 = findViewById(R.id.ansbtn4);


        String [][]quizData={
                //{"gk","right Answer","Choice1","choice2","Choice
                {"Name The hardest substance available on earth?","Daimond","silver","Gold","plastic"},

                {"Who is the father of our Nation?","Mahatma gandhi","pandit nehru","Dr Babasaheb ambedkar","bhagat singh"},

                {"Who gave the universal law of gravitation?","Issac Newton","kepler","Robort hook","mendel"},
                {"Name a Natural satllite of Earth?","Moon","Mercury","Venus","Uranus"},
                {"Name The national game of the USA?","Baseball","Cricket","Hocky","basketball"},
                {"who invented watch?","Peter Henlin","Albert Eistein","marie curie","Issac Newton"},
                {"Name the largest'Democracy'in the World?","India","chaina","America","japan"},
                {"Who invented Bakelite?","Leo Hendrik Beakeland","Charles goodyear","Roy Goodyear","Henry Ford"},
                {"which containt is known as the Dark Containt?","Africa","India","japan","chaina",},
                {"Name the planet known as the Red planet?","mars","mercury","venus","earth"},
                {"who wrote malgudi days?","R.K.Narayan?","p.l deshpande","vikram seth","chetan bhagat"},
                {"Name the gas which is filled in ballons?","Helian","naitrogen","iron","carban"},
                {"who is known as the father of indian consitution?","Dr.B.R ambedkar","pandit nehru","mahatma gandhi","rajguru"},
                {"Giddha is the folk dance of?","Punjab","maharashtra","gujrat","telangana"},
                {"who invented the computer?","Charles Babbage","edison","Billgates","rancho"},
        };



        //create quizArray from quizData.
        for( i = 0; i < quizData.length; i++) {
            //prepare array.

            ArrayList<String> tmpArray = new ArrayList<>();
            tmpArray.add(quizData[i][0]);//gk
            tmpArray.add(quizData[i][1]);//right answer
            tmpArray.add(quizData[i][2]);//choice1
            tmpArray.add(quizData[i][3]);//choice2
            tmpArray.add(quizData[i][4]);//choice3

            //Add tmpArry to quizArray.
            quizArray.add(tmpArray);
        }
        showNextQuiz();
    }
    public void showNextQuiz() {
        // update quizcountlbl.
        for (int j=0; j<=quizCount; j++)
        countlabel.setText("Q" +j);

        // Genrate random number between 0 and 14 (quizArray,s size - 1).
        Random random = new Random();
        int randomnum = random.nextInt(quizArray.size());

        //pick one quiz set.
        ArrayList<String> quiz = quizArray.get(randomnum);

        //set question and right answer.
        //array format:{"gk","right answer","choice1","choice2","choice3"}.

        questionlabel.setText(quiz.get(0));
        rightAnswer = quiz.get(1);

        //remove "gk" from quiz and shuffle choices.

        quiz.remove(0);
        Collections.shuffle(quiz);

        //set choices.

        ansbtn1.setText(quiz.get(0));
        ansbtn2.setText(quiz.get(1));
        ansbtn3.setText(quiz.get(2));
        ansbtn4.setText(quiz.get(3));


        //remove this quiz from quizArray.
        quizArray.remove(randomnum);

    }

    public void checkanswer(View view) {

        //get pushed button.
        Button answerBtn= (Button) findViewById(view.getId());
        String btnText = answerBtn.getText().toString();

        String alertTitle;

        if(btnText.equals(rightAnswer)) {

            //correct!
            alertTitle = "Correct!";
            rightAnswerCount++;

        }
        else{
            //wrong!
            alertTitle= "Wrong...";

        }

        //create dailog.
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("Answer:"+rightAnswer);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (quizCount== Quiz_COUNT) {

                    //SHOW RESULT
                    Intent intent = new  Intent(getApplicationContext(),ResultActivity.class);
                    intent.putExtra("RIGHT_ANSWER_COUNT",rightAnswerCount);
                    startActivity(intent);
                }
                else {
                    quizCount++;
                    showNextQuiz();

                }

            }
        });
        builder.setCancelable(false);
        builder.show();


    }







        }

