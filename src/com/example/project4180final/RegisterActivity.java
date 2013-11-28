package com.example.project4180final;

import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends Activity implements OnClickListener {

	EditText en, ee, eu, ep;
	Button bt1,bt2;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		en = (EditText) findViewById(R.id.editTextName);
		en.requestFocus();
		

		bt1 = (Button) findViewById(R.id.buttonAccept);
		bt2 =(Button) findViewById(R.id.buttonCancel);
		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);
		

	}

	

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonAccept:
			en = (EditText) findViewById(R.id.editTextName);
			final String name=en.getText().toString();
			
			ee = (EditText) findViewById(R.id.editTextEmail);
			final String email=ee.getText().toString();
			
			eu = (EditText) findViewById(R.id.editTextUser);
			final String user=eu.getText().toString();
			
			ep = (EditText) findViewById(R.id.editTextPassword);
			final String pass=ep.getText().toString();
			
			ParseUser users = new ParseUser();
			users.setUsername(user);
			users.setPassword(pass);
			users.put("email", email);
			users.put("name", name);
			
			users.signUpInBackground(new SignUpCallback() {
				  public void done(ParseException e) {
				    if (e == null) {
				    	Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
				    	startActivity(intent);
				      // Hooray! Let them use the app now.
				    } else {
				    	//Log.d("demo","Error de datos");
				    	showMessage();
				      // Sign up didn't succeed. Look at the ParseException
				      // to figure out what went wrong
				    }
				  }
				});

		
			
			
			/*
			eu = (EditText) findViewById(R.id.editTextUser);
			final String user = eu.getText().toString();
			
		
			ParseQuery<ParseObject> query = ParseQuery.getQuery("Users");
			query.whereEqualTo("username", user);
			query.findInBackground(new FindCallback<ParseObject>() {
			
				public void done(List<ParseObject> scoreList, ParseException e) {
					if (scoreList.size() == 0) {

						en = (EditText) findViewById(R.id.editTextName);
						en.requestFocus();
						final String name = en.getText().toString();
						ee = (EditText) findViewById(R.id.editTextEmail);
						final String email = ee.getText().toString();
						ep = (EditText) findViewById(R.id.editTextPassword);
						final String pass = ep.getText().toString();
						
						ParseObject users = new ParseObject("Users");
						users.put("name", name);
						users.put("email", email);
						users.put("username", user);
						users.put("password", pass);
						users.saveInBackground();
						Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
						startActivity(intent);
					} else {
						showMessage();
					}
				}

			});


*/
			break;
		case R.id.buttonCancel:
			finish();
			break;

		}

	}

	private void showMessage() {
		AlertDialog.Builder db = new AlertDialog.Builder(this);
		db.setTitle("Coupons Movil App");
		db.setMessage("Select another username");
		db.setPositiveButton("OK", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				// Toast.makeText(getApplicationContext(),
				// "The Username is being used", Toast.LENGTH_SHORT).show();
			}
		});
		AlertDialog alertDialog = db.create();
		alertDialog.show();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

}
