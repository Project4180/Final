package com.example.project4180final;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class OptionActivity extends Activity {
	static String USEROPTION = "USER";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_option);
		
		if(getIntent().getExtras().toString()!=null){
			USEROPTION = getIntent().getExtras().getString(LoginActivity.USER_KEYL);
			Log.d("demo",""+USEROPTION);
		}

		
		findViewById(R.id.buttonOptionCancel).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		findViewById(R.id.buttonAddCoupons).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(OptionActivity.this, CouponsActivity.class);
				intent.putExtra(LoginActivity.USER_KEYL, USEROPTION);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.option, menu);
		return true;
	}

}
