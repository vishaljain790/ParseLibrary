package com.example.mychattapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginAndRegister extends Activity {

	Button register,login;
	static String uname,passwordval;
	EditText edituname,editpassword;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_login_and_register);
		
		
		edituname=(EditText)findViewById(R.id.editTextforuname);
		editpassword=(EditText)findViewById(R.id.editTextforpassword);
		
		register=(Button)findViewById(R.id.buttonregister);
		register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(LoginAndRegister.this,RegisterClass.class));
				
			}
		});
		
		
		login=(Button)findViewById(R.id.buttonforlogin);
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				uname=edituname.getText().toString().trim();
				passwordval=editpassword.getText().toString().trim();
				
				
				if (uname.length() == 0 || passwordval.length() == 0)
				{
					Utils.showDialog(LoginAndRegister.this, R.string.err_fields_empty);
					return;
				}
				final ProgressDialog dia = ProgressDialog.show(LoginAndRegister.this, null,
						getString(R.string.alert_wait));
				ParseUser.logInInBackground(uname, passwordval, new LogInCallback() {

					@Override
					public void done(ParseUser pu, ParseException e)
					{
						dia.dismiss();
						if (pu != null)
						{
							
							//Toast.makeText(getApplicationContext(), "Work next", Toast.LENGTH_LONG).show();
							AfterLogin.user = pu;
							startActivity(new Intent(LoginAndRegister.this, AfterLogin.class));
							finish();
						}
						else
						{
							Utils.showDialog(
									LoginAndRegister.this,
									getString(R.string.err_login) + " "
											+ e.getMessage());
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_login_and_register, menu);
		return true;
	}

}
