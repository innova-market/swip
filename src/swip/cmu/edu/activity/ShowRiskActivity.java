package swip.cmu.edu.activity;

import swip.cmu.edu.Application;
import swip.cmu.edu.R;
import android.app.Activity;
import android.os.Bundle;

public class ShowRiskActivity extends Activity
{
	Application beingInstalled;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_risks);
	}
	
	public void setApp(Application app)
	{
		beingInstalled = app;
	}
}