package swip.cmu.edu.activity;

import swip.cmu.edu.Application;
import swip.cmu.edu.Application.Request;
import swip.cmu.edu.PermissionManager;
import swip.cmu.edu.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ModifyPermissionsActivity extends Activity
{
	class PermissionRow extends TableRow
	{
		ImageView risk;
		ImageView loss;
		TextView text;
//		ToggleButton grant;
		CheckBox check;
		Request request;
		
		public PermissionRow(final Context context, Request reqest)
		{
			super(context);
			this.request = reqest;
			
			// Set the images
			risk = new ImageView(context);
			loss = new ImageView(context);
			risk.setImageResource(R.drawable.warn20);
			loss.setImageResource(R.drawable.info20);
			
			text = new TextView(context);
			text.setText(request.getPermission().getName());
			text.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 20));
			text.setClickable(true);
			text.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					// TODO Launch details for this permission.
					Toast.makeText(context, PermissionRow.this.request.getReason(), Toast.LENGTH_SHORT).show();
				}
			});

//			grant = new ToggleButton(context);
//			grant.setTextOn("Yes");
//			grant.setTextOff("No");
//			grant.setChecked(true);	// TODO link
			
			check = new CheckBox(context);
			check.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					boolean granted = ((CheckBox) v).isChecked();
					PermissionRow.this.request.setGranted(granted);
					update();
				}
			});
			
			this.addView(check);
			this.addView(text);
			this.addView(risk);
			this.addView(loss);
			update();
		}
		
		void update()
		{
			risk.setVisibility(request.isRiskingPrivacy() ? VISIBLE : GONE);
			loss.setVisibility(request.isGranted() ? GONE : VISIBLE);
			check.setChecked(request.isGranted());
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
	}
	
	public void setApp(Application app)
	{
		beingInstalled = app;
	}
}