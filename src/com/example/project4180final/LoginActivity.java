
package com.example.project4180final;

import java.util.List;

import com.parse.CountCallback;
import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity implements OnClickListener{
	
	EditText eu,ep;
	Button bt1, bt2;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		bt1 =(Button)findViewById(R.id.buttonLogin);
		bt2 = (Button)findViewById(R.id.buttonLCancel);
		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);
	}

	

	@Override
	public void onClick(View v) {
		
		switch(v.getId()){
		case R.id.buttonLogin:
			eu = (EditText) findViewById(R.id.editTextLUser);
			final String user = eu.getText().toString();
			
			ep = (EditText) findViewById(R.id.editTextLPassword);
			final String pass = ep.getText().toString();
			
			ParseUser.logInInBackground(user, pass, new LogInCallback() {
				  public void done(ParseUser users, ParseException e) {
				    if (users != null) {
				      // Hooray! The user is logged in.
				    	//Log.d("demo","Verdadero");
				    	Intent intent = new Intent(LoginActivity.this, OptionActivity.class);
				    	startActivity(intent);
				    } else {
				      // Signup failed. Look at the ParseException to see what happened.
				    	//Log.d("demo","Falso");
				    	showMessage();
				    	
				    }
				  }

				
				});
			break;
		case R.id.buttonLCancel:
			finish();
			break;
		}
		
	}

	private void showMessage() {
		AlertDialog.Builder db = new AlertDialog.Builder(this);
		db.setTitle("Coupons Movil App");
		db.setMessage("Wromg Username or Password");
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
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
