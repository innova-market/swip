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
		Button modify = (Button) findViewById(R.id.install);
		modify.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				MainMenuActivity.this.startActivity(new Intent(MainMenuActivity.this, AppSelectActivity.class));
			}
		});
		
		// Set the app icon and name
//		ImageView imageView = (ImageView) findViewById(R.id.appImage);
//		imageView.setImageResource(beingInstalled.getIcon());
//		
//		TextView textView = (TextView) findViewById(R.id.appName);
//		textView.setText(beingInstalled.getName());
//		
//		TableLayout table = (TableLayout) findViewById(R.id.permissionTable);
//		
//		// Fill this screen with the information from the permissions, line by line.
//		for(Request r: beingInstalled.getPermissionRequests())
//		{
//			// Add risk if present.
//			table.addView(new PermissionRow(this, r));
//		}
		
//		TableRow fl = (TableRow) findViewById(R.id.featureLoss);
//		if(!beingInstalled.isLoosingFeatures())
//			fl.setVisibility(View.GONE);
//
//		TableRow pr = (TableRow) findViewById(R.id.privacyRisk);
//		if(!beingInstalled.isRiskingPrivacy())
//			pr.setVisibility(View.GONE);
//		
//		
	}
}