package org.kemaman.kelulut_main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.kemaman.kelulut_main.dto.QuestionDTO;
import org.kemaman.kelulut_main.utils.CustomPagerAdapter;

import java.util.ArrayList;

public class ExamActivity extends AppCompatActivity {

    ArrayList<QuestionDTO> listQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // get list of Question object from GetQuestionActivity
        listQuestion = (ArrayList<QuestionDTO>) getIntent().getSerializableExtra("question_list");

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        // viewPager.setAdapter(new CustomPagerAdapter(this));











        FloatingActionButton fabLearn = (FloatingActionButton) findViewById(R.id.fab_learn);
        fabLearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LearnActivity.class);
                overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        FloatingActionButton fabReport = (FloatingActionButton) findViewById(R.id.fab_report);
        fabReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LocationActivity.class);
                overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        FloatingActionButton fabDiscover = (FloatingActionButton) findViewById(R.id.fab_discover);
        fabDiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DiscoverActivity.class);
                overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        FloatingActionButton fabExam = (FloatingActionButton) findViewById(R.id.fab_exam);
        fabExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GetQuestionActivity.class);
                overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });
    }

}
