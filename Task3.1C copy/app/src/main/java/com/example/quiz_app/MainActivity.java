package com.example.quiz_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity
{
	private EditText nameEditText;
	private String[][] questions;
	private int[] answers;
	private int index;
	public static int NUM_CORRECT;
	public static String NAME = "";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		nameEditText = findViewById(R.id.name);

		questions = new String[5][5];
		answers = new int[5];
		questions[0] = new String[]{"Gestures Conflicts", "Single tap and double-tap sometimes conflict, which of the following could resolve this problem?", "No way to resolve", "Avoid use at same time", "Pend single tap a bit"};
		answers[0] = 2;

		questions[1] = new String[]{"GPS Energy Consumption", "What would be the scenario if adopt higher GPS accuracy and update rate?", "No difference", "Higher power consumption", "Lower power consumption"};
		answers[1] = 1;

		questions[2] = new String[]{"Application Compatibility", "Which would be the best practice?", "Doesn't matter", "As higher API level as possible", "Depends on actual demands"};
		answers[2] = 2;

		questions[3] = new String[]{"Thread Issues", "Which thread would be best to do HTTP GET?", "Main thread", "A sub thread", "Doesn't matter"};
		answers[3] = 1;

		questions[4] = new String[]{"Challenges", "Which could be a challenge in Android development?", "Limited screen size", "Internet connectivity", "GPS access"};
		answers[4] = 0;

		NUM_CORRECT = 0;

		if (!NAME.isEmpty())
			nameEditText.setText(NAME);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent)
	{
		super.onActivityResult(requestCode, resultCode, intent);

		if (resultCode == RESULT_OK)
		{
			if (index == questions.length)
			{
				Intent i = new Intent(this, ReportActivity.class);
				i.putExtra("Statistics", String.format(Locale.UK, "%d/%d", NUM_CORRECT, questions.length));
				startActivity(i);
			}
			else
			{
				nextQuestion();
			}
		}
	}

	public void onStart(View view)
	{
		if (nameEditText.getText().toString().length() != 0)
		{
			NAME = nameEditText.getText().toString();
			nextQuestion();
		}
		else
		{
			Toast.makeText(getBaseContext(), "You have to input your name to start quiz!", Toast.LENGTH_SHORT).show();
		}
	}

	private void nextQuestion()
	{
		Intent intent = new Intent(this, SecondActivity.class);
		intent.putExtra("Category", questions[index][0]);
		intent.putExtra("Question", questions[index][1]);
		intent.putExtra("Choice0", questions[index][2]);
		intent.putExtra("Choice1", questions[index][3]);
		intent.putExtra("Choice2", questions[index][4]);
		intent.putExtra("Answer", answers[index]);
		intent.putExtra("Index", ++index);
		intent.putExtra("NumTotal", questions.length);

		startActivityForResult(intent, 1);
	}
}