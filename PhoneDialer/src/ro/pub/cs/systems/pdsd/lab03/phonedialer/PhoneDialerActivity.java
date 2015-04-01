package ro.pub.cs.systems.pdsd.lab03.phonedialer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class PhoneDialerActivity extends Activity {
	
	//vector cu id-urile pentru butoane
	final protected int buttons[] = {
			R.id.button0,
			R.id.button1,
			R.id.button2,
			R.id.button3,
			R.id.button4,
			R.id.button5,
			R.id.button6,
			R.id.button7,
			R.id.button8,
			R.id.button9,
			R.id.buttondiez,
			R.id.buttonsteluta
	};
		
	//vector cu id-urile pentru button_images
	final protected int buttons_images[] = {
			R.id.caller,
			R.id.backspace,
			R.id.reject
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phone_dialer);
		
		//constructor listener
		MyListener listener = new MyListener();
		
		//listener pentru vectorul de butoane
		for(int i=0; i<buttons.length; i++) {
			Button button_listener = (Button)findViewById(buttons[i]);
			button_listener.setOnClickListener(listener);	
		}
		
		//listener pt vectorul de butoane cu imagini
		for(int i=0; i<buttons_images.length; i++) {
			ImageButton image_listener = (ImageButton)findViewById(buttons_images[i]);
			image_listener.setOnClickListener(listener);
		}
	}
	
	//clasa pentru listener
	protected class MyListener implements View.OnClickListener {
			
		@Override
		public void onClick(View view) {
			
			//daca se apasa pe un buton
			if (view instanceof Button) {
				String value = ((Button) view).getText().toString();
				EditText phone_text = (EditText)findViewById(R.id.editText1);
				phone_text.setText(phone_text.getText().toString() + value);
			}
			
			//daca se apasa pe un buton cu imagini
			else if (view instanceof ImageButton) {
					
					//backspace
					if (view.getId() == R.id.backspace) {
						EditText phone_text = (EditText)findViewById(R.id.editText1);
						String text = phone_text.getText().toString();
						if (text != "") {
							text = text.substring(0, text.length()-1);
						}
						phone_text.setText(text);
					}
					
					//caller
					if (view.getId() == R.id.caller) {
						EditText phone_text = (EditText)findViewById(R.id.editText1);
						String phoneNumber = phone_text.getText().toString();
						
						Intent intent = new Intent(Intent.ACTION_CALL);
						intent.setData(Uri.parse("tel:"+phoneNumber));
						startActivity(intent);
					}
					
					//hangup
					else if (view.getId() == R.id.reject) {
							finish();
					}
			}
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.phone_dialer, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
