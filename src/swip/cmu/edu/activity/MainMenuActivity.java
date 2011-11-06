package swip.cmu.edu.activity;

import swip.cmu.edu.Application;
import swip.cmu.edu.Application.Request;
import swip.cmu.edu.R;
import swip.cmu.edu.activity.ModifyPermissionsActivity.PermissionRow;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

public class MainMenuActivity extends Activity
{
	View.OnClickListener modifyListener = new View.OnClickListener() 
	{
        public void onClick(View v) 
        {
        	// Call the next screen.
        }
    };
    
    View.OnClickListener acceptListener = new View.OnClickListener() 
	{
        public void onClick(View v) 
        {
        	// Call the next screen.
        }
    };
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
		
		// Wait for actions on the buttons.
		Button install = (Button) findViewById(R.id.install);
		install.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				MainMenuActivity.this.startActivity(new Intent(MainMenuActivity.this, AppSelectActivity.class));
			}
		});
		
		Button modify = (Button) findViewById(R.id.modify);
		modify.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				MainMenuActivity.this.startActivity(new Intent(MainMenuActivity.this, AppSelectActivity.class));
			}
		});
		
		Button defaults = (Button) findViewById(R.id.defaults);
		defaults.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				MainMenuActivity.this.startActivity(new Intent(MainMenuActivity.this, AppSelectActivity.class));
			}
		});
	}
}