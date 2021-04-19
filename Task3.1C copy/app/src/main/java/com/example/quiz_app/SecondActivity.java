package com.example.quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Locale;

public class SecondActivity extends AppCompatActivity
{
	private boolean didSubmit;
	private boolean isCorrect;
	private int selected;
	private int correctAnswer;

	private Button[] choices;
	private Button progressButton;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		TextView welcome = findViewById(R.id.name);
		TextView progress = findViewById(R.id.progress);
		ProgressBar bar = findViewById(R.id.bar);
		TextView category = findViewById(R.id.category);
		TextView question = findViewById(R.id.question);

		selected = -1;

		choices = new Button[3];
		choices[0] = findViewById(R.id.A);
		choices[1] = findViewById(R.id.B);
		choices[2] = findViewById(R.id.C);
		progressButton = findViewById(R.id.progressButton);

		Intent intent = getIntent();
		int current = intent.getIntExtra("Index", 0);
		int total = intent.getIntExtra("NumTotal", 0);
		correctAnswer = intent.getIntExtra("Answer", 0);

		welcome.setText(String.format("Welcome, %s", MainActivity.NAME));
		progress.setText(String.format(Locale.getDefault(), "%d/%d", current, total));
		bar.setMax(total);
		bar.setProgress(current);
		category.setText(intent.getStringExtra("Category"));
		question.setText(intent.getStringExtra("Question"));
		choices[0].setText(intent.getStringExtra("Choice0"));
		choices[1].setText(intent.getStringExtra("Choice1"));
		choices[2].setText(intent.getStringExtra("Choice2"));

		progressButton.setText(getResources().getString(R.string.submit));
	}

	public void onSubmit(View view)
	{
		if (!didSubmit)
		{
			choices[correctAnswer].setBackgroundColor(Color.GREEN);
			if (!isCorrect)
			{
				choices[selected].setBackgroundColor(Color.RED);
			}
			progressButton.setText(R.string.next);
			didSubmit = true;
		}
		else
		{
			if (isCorrect)
				MainActivity.NUM_CORRECT++;

			setResult(RESULT_OK);
			finish();
		}
	}

	public void onChoiceA(View view)
	{
		if (!didSubmit)
		{
			selected = 0;
			choices[0].setBackgroundColor(Color.LTGRAY);
			choices[1].setBackgroundColor(Color.WHITE);
			choices[2].setBackgroundColor(Color.WHITE);
			isCorrect = correctAnswer == 0;
		}
	}

	public void onChoiceB(View view)
	{
		if (!didSubmit)
		{
			selected = 1;
			choices[1].setBackgroundColor(Color.LTGRAY);
			choices[0].setBackgroundColor(Color.WHITE);
			choices[2].setBackgroundColor(Color.WHITE);
			isCorrect = correctAnswer == 1;
		}
	}

	public void onChoiceC(View view)
	{
		if (!didSubmit)
		{
			selected = 2;
			choices[2].setBackgroundColor(Color.LTGRAY);
			choices[0].setBackgroundColor(Color.WHITE);
			choices[1].setBackgroundColor(Color.WHITE);
			isCorrect = correctAnswer == 2;
		}
	}
}