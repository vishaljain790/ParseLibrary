package com.example.mychattapp;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;

public class ChattApp extends Application{
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		Parse.initialize(this,"BHwr0OHOeEP55G81sjkqTuL6nkZT1939P0muvzsz","aUACfVJeqp743CRBsfzdHhQ9vGoZyYMUlXigKZGW");
		 ParseInstallation.getCurrentInstallation().saveInBackground();
	}

}
