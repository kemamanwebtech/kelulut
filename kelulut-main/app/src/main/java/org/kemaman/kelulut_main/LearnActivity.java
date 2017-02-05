package org.kemaman.kelulut_main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class LearnActivity extends AppCompatActivity  implements View.OnClickListener {

    Button buttonFrie = null;
    Button buttonLest = null;
    Button buttonMeli = null;
    Button buttonTetra = null;
    Button buttonTrigo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


         buttonFrie = (Button) findViewById(R.id.Frieseomelitta);
         buttonLest = (Button) findViewById(R.id.Lestrimelitta);
         buttonMeli = (Button) findViewById(R.id.Melipona);
         buttonTetra = (Button) findViewById(R.id.Tetragonisca);
         buttonTrigo = (Button) findViewById(R.id.Trigona);

        buttonFrie.setOnClickListener(this);
        buttonLest.setOnClickListener(this);
        buttonMeli.setOnClickListener(this);
        buttonTetra.setOnClickListener(this);
        buttonTrigo.setOnClickListener(this);

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
                finish();
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

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(getApplicationContext(), GenusActivity.class);
        String genus = "";

        switch (v.getId()) {

            case R.id.Frieseomelitta:
                genus = "Frieseomelitta";
                intent.putExtra("GENUS", genus);
                startActivity(intent);
                break;

            case R.id.Lestrimelitta:
                genus = "Lestrimelitta";
                intent.putExtra("GENUS", genus);
                startActivity(intent);
                break;

            case R.id.Melipona:
                genus = "Melipona";
                intent.putExtra("GENUS", genus);
                startActivity(intent);
                break;

            case R.id.Tetragonisca:
                genus = "Tetragonisca";
                intent.putExtra("GENUS", genus);
                startActivity(intent);
                break;

            case R.id.Trigona:
                genus = "Trigona";
                intent.putExtra("GENUS", genus);
                startActivity(intent);
                break;

        }
    }

}
