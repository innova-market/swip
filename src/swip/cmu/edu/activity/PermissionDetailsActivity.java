package swip.cmu.edu.activity;

import swip.cmu.edu.Application;
import swip.cmu.edu.Application.Request;
import swip.cmu.edu.Permission;
import swip.cmu.edu.PermissionManager;
import swip.cmu.edu.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PermissionDetailsActivity extends Activity
{
	Application beingInstalled;
	Request req;
	Permission permission;
	
	/**
	 * Listens to the grant button, applies the action and closes.
	 */
	View.OnClickListener grantListener = new View.OnClickListener() 
	{
        public void onClick(View v) 
        {
        	if(beingInstalled != null)
        		req.setGranted(true);
        	else
        		permission.setAcceptByDefault(true);
        	finish();
        }
    };

	/**
	 * Listens to the deny button, applies the action and closes.
	 */
    View.OnClickListener denyListener = new View.OnClickListener() 
	{
        public void onClick(View v) 
        {
        	if(beingInstalled != null)
        		req.setGranted(false);
        	else
        		permission.setAcceptByDefault(false);
        	finish();
        }
    };
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.permission_details);
		
		// TODO Generalize for already uninstalled apps.
		int permissionId = this.getIntent().getIntExtra("requestId", -1);
		
		if(permissionId < 0)
		{
			int appId = this.getIntent().getIntExtra("appId", -1);
			int requestId = this.getIntent().getIntExtra("requestId", -1);
			beingInstalled = appId < 0 ? null : PermissionManager.uninstalledApps.get(appId);
			req = requestId < 0 ? null : beingInstalled.getPermissionRequests().get(requestId);
			permission = req.getPermission();
		}
		
		TextView textView = (TextView) findViewById(R.id.permission);
		textView.setText(permission.getName());
	
		textView = (TextView) findViewById(R.id.explanation);
		if(req.getReason() == null || req.getReason().length() == 0)
			textView.setText("Nothing.");
		else
			textView.setText(req.getReason());

		ImageView riskImg = (ImageView) findViewById(R.id.privacyImg);
		if(permission.isRisky())
			riskImg.setVisibility(View.VISIBLE);
		else
			riskImg.setVisibility(View.INVISIBLE);
		
		textView = (TextView) findViewById(R.id.risk);
		textView.setText(permission.getDescription());
		
		// Wait for actions on the buttons.
		Button modify = (Button) findViewById(R.id.grant);
		modify.setOnClickListener(grantListener);
		Button accept = (Button) findViewById(R.id.deny);
		accept.setOnClickListener(denyListener);
	}
}