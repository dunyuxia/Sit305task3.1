package com.example.quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class ReportActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report);

		TextView congratulations = findViewById(R.id.congratulation);
		TextView score = findViewById(R.id.score);

		Intent intent = getIntent();

		congratulations.setText(String.format("Congratulations, %s", MainActivity.NAME));
		score.setText(intent.getStringExtra("Statistics"));
	}

	public void newQuiZ(View view)
	{
		startActivity(new Intent(this, MainActivity.class));
	}

	public void onFinish(View view)
	{
		System.exit(0);
	}
}