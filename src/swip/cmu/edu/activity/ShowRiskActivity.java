package swip.cmu.edu.activity;

import swip.cmu.edu.Application;
import swip.cmu.edu.PermissionManager;
import swip.cmu.edu.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

public class ShowRiskActivity extends Activity
{
	Application beingInstalled;
	int beingInstalledIndex;
	
	View.OnClickListener modifyListener = new View.OnClickListener() 
	{
        public void onClick(View v) 
        {
        	// Call the next screen.
        	Intent intent = new Intent(ShowRiskActivity.this, ModifyPermissionsActivity.class);
    		intent.putExtra("appId", beingInstalledIndex);
    		startActivity(intent);
        }
    };
    
    View.OnClickListener acceptListener = new View.OnClickListener() 
	{
        public void onClick(View v) 
        {
        	// Update the app status and go back to the first screen.
        	PermissionManager.install(beingInstalled);
        	finish();
        }
    };
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_risks);
		
		beingInstalledIndex = this.getIntent().getIntExtra("appId", 0);
		beingInstalled = PermissionManager.uninstalledApps.get(beingInstalledIndex);
		beingInstalled.revertToDefaults();
		
		// Set the app icon and name
		ImageView imageView = (ImageView) findViewById(R.id.appImage);
		imageView.setImageResource(beingInstalled.getIcon());
		
		TextView textView = (TextView) findViewById(R.id.appName);
		textView.setText(beingInstalled.getName());
		
		// Set the warnings
		TableRow fl = (TableRow) findViewById(R.id.featureLoss);
		if(!beingInstalled.isLoosingFeatures())
			fl.setVisibility(View.GONE);
		else
			fl.setVisibility(View.VISIBLE);

		TableRow pr = (TableRow) findViewById(R.id.privacyRisk);
		if(!beingInstalled.isRiskingPrivacy())
			pr.setVisibility(View.GONE);
		else
			pr.setVisibility(View.VISIBLE);
		
		// Wait for actions on the buttons.
		Button modify = (Button) findViewById(R.id.modify);
		modify.setOnClickListener(modifyListener);
		Button accept = (Button) findViewById(R.id.accept);
		accept.setOnClickListener(acceptListener);
	}
	
	public void setApp(Application app)
	{
		beingInstalled = app;
	}
}