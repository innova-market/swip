package swip.cmu.edu.activity;

import swip.cmu.edu.Application;
import swip.cmu.edu.Application.Request;
import swip.cmu.edu.PermissionManager;
import swip.cmu.edu.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ModifyPermissionsActivity extends Activity
{
	class PermissionRow extends TableRow
	{
		ImageView risk;
		ImageView loss;
		TextView text;
		ToggleButton grant;
		Request reqest;
		
		public PermissionRow(Context context, Request reqest)
		{
			super(context);
			this.reqest = reqest;
			
			// Set the images
			risk = new ImageView(context);
			risk.setImageResource(R.drawable.warn40);
			
			this.addView(risk);
		}
	}
	
	Application beingInstalled;
	
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
		setContentView(R.layout.modify_permissions);
		
		beingInstalled = PermissionManager.uninsalledApps.get(this.getIntent().getIntExtra("appId", 0));
		
		// Set the app icon and name
		ImageView imageView = (ImageView) findViewById(R.id.appImage);
		imageView.setImageResource(beingInstalled.getIcon());
		
		TextView textView = (TextView) findViewById(R.id.appName);
		textView.setText(beingInstalled.getName());
		
		TableLayout table = (TableLayout) findViewById(R.id.permissionTable);
		
		// Fill this screen with the information from the permissions, line by line.
		for(Request r: beingInstalled.getPermissionRequests())
		{
			// Add risk if present.
			table.addView(new PermissionRow(this, r));
		}
		
//		TableRow fl = (TableRow) findViewById(R.id.featureLoss);
//		if(!beingInstalled.isLoosingFeatures())
//			fl.setVisibility(View.GONE);
//
//		TableRow pr = (TableRow) findViewById(R.id.privacyRisk);
//		if(!beingInstalled.isRiskingPrivacy())
//			pr.setVisibility(View.GONE);
//		
//		// Wait for actions on the buttons.
//		Button modify = (Button) findViewById(R.id.modify);
//		modify.setOnClickListener(modifyListener);
//		Button accept = (Button) findViewById(R.id.accept);
//		accept.setOnClickListener(acceptListener);
	}
	
	public void setApp(Application app)
	{
		beingInstalled = app;
	}
}