package com.example.mychattapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class RegisterClass extends Activity{

	Button forregister;
	static String emailval,usernameval,passwordval;
	EditText edittestforemail,edittextforusername,edittextforpassword;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.registerlayout);
		
		
		edittestforemail=(EditText)findViewById(R.id.editTextforemail);
		edittextforusername=(EditText)findViewById(R.id.editTextforusername);
		edittextforpassword=(EditText)findViewById(R.id.editTextforpassword);
		
		forregister=(Button)findViewById(R.id.buttonforregister);
		
		forregister.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				emailval=edittestforemail.getText().toString().trim();
				usernameval=edittextforusername.getText().toString().trim();
				passwordval=edittextforpassword.getText().toString().trim();
				
				
				if (usernameval.length() == 0 || passwordval.length() == 0 ||emailval.length() == 0)
				{
					Utils.showDialog(RegisterClass.this, R.string.err_fields_empty);
					return;
				}
				final ProgressDialog dia = ProgressDialog.show(RegisterClass.this, null,
						getString(R.string.alert_wait));

				final ParseUser pu = new ParseUser();
				pu.setEmail(emailval);
				pu.setPassword(passwordval);
				pu.setUsername(usernameval);
				pu.signUpInBackground(new SignUpCallback() {

					@Override
					public void done(ParseException e)
					{
						dia.dismiss();
						if (e == null)
						{
							//Toast.makeText(getApplicationContext(), "Work next", Toast.LENGTH_LONG).show();
							
							
							AfterLogin.user = pu;
							startActivity(new Intent(RegisterClass.this, AfterLogin.class));
							setResult(RESULT_OK);
							finish();
						}
						else
						{
							Utils.showDialog(
									RegisterClass.this,
									getString(R.string.err_singup) + " "
											+ e.getMessage());
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		
		
	
	}

}
