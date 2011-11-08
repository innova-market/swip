/**
 * 
 */
package swip.cmu.edu.activity;

import swip.cmu.edu.AppListAdapter;
import swip.cmu.edu.PermissionManager;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

/**
 * @author Daniel Langdon
 *
 */
public class AppSelectActivity extends ListActivity
{
	/** Called when the activity is first created. */

	public void onCreate(Bundle icicle) 
	{
		super.onCreate(icicle);
		
		// Initialize the data layer
		PermissionManager.initialize();
		
		// Use your own layout and point the adapter to the UI elements which contains the label
		switch(PermissionManager.getMode())
		{
		case INSTALLING:
			this.setListAdapter(new AppListAdapter(PermissionManager.uninstalledApps, this));
			break;
		case MODIFYING:
			this.setListAdapter(new AppListAdapter(PermissionManager.installedApps, this));
			break;
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) 
	{
		super.onListItemClick(l, v, position, id);
		PermissionManager.setSelectedApp(position);
		
		// Get the item that was clicked
//		Application app = (Application) this.getListAdapter().getItem(position);
//		Toast.makeText(this, "You selected: " + app.getName(), Toast.LENGTH_LONG).show();
		
		Intent intent = new Intent(this, ShowRiskActivity.class);
		intent.putExtra("appId", position);
		startActivity(intent);
		
	}
}
