package com.example.project4180final;

import java.io.ByteArrayOutputStream;

import com.parse.ParseFile;
import com.parse.ParseObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class CouponsActivity extends Activity implements OnClickListener {
	
	static String COUPONSUSER="USER";
	static String COMPANY="USER";
	static String EXPIRATION="USER";
	ImageView iv;
	EditText ec,ee;
	Button bt1, bt2, bt3;
	Bitmap thumbnail;
	private static final int CAMERA_PIC_REQUEST = 1111;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_coupons);
		
		bt1 = (Button) findViewById(R.id.buttonPhoto);
		bt2 = (Button) findViewById(R.id.buttonCSave);
		bt3 = (Button) findViewById(R.id.buttonCCancel);
		
		ec = (EditText) findViewById(R.id.editTextCompany);
		ee = (EditText) findViewById(R.id.editTextExpiration);
		Log.d("deni",""+COMPANY);
		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);
		bt3.setOnClickListener(this);
		
		
		
		if(getIntent().getExtras().toString()!=null){
			COUPONSUSER = getIntent().getExtras().getString(LoginActivity.USER_KEYL);
			Log.d("dema",""+COUPONSUSER);
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if (requestCode == CAMERA_PIC_REQUEST) {
			thumbnail = (Bitmap) data.getExtras().get("data");
			iv.setImageBitmap(thumbnail);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.coupons, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		
		switch(v.getId()){
		case R.id.buttonPhoto:
			iv =(ImageView) findViewById(R.id.imageViewPicture);
			Intent intent = new Intent(
					android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(intent, CAMERA_PIC_REQUEST);
			break;
		case R.id.buttonCSave:
			COMPANY = ec.getText().toString();
			EXPIRATION = ee.getText().toString();
			
			// 3
			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
			thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
			byte[] datas = bytes.toByteArray();

			ParseFile imgFile = new ParseFile("image.png", datas);
			imgFile.saveInBackground();
				
			ParseObject imageObj = new ParseObject("Coupons");
			imageObj.put("username", COUPONSUSER);
			imageObj.put("company", COMPANY);
			imageObj.put("expiration", EXPIRATION);
			imageObj.put("userFile", imgFile);
			imageObj.saveInBackground();

		}
	}

}
