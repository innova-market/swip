package swip.cmu.edu.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import swip.cmu.edu.Application;
import swip.cmu.edu.Application.Request;
import swip.cmu.edu.Category;
import swip.cmu.edu.PermissionManager;
import swip.cmu.edu.R;
import swip.cmu.edu.activity.ModifyPermissionsActivity.PermissionRow;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Contacts.Groups;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ImageView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TableLayout;
import android.widget.TextView;

/**
 * @author Dev Gurjar 
 */
public class ModifyDefaultsActivity extends Activity {  

	//Global variables, we want to replace most of these with the global variables
	private static final String KEY1 = "GROUP";  
	private static final String KEY2 = "CHILD";  

	//private String[] GROUPS; /*= {"Group1", "Group2"};  */
	/*private String[][][] CHILDREN;/* = {  
			{{"Child11", "Text11"}},   
			{{"Child21", "Text21"}, {"Child22", "Text22"}},  
			{{"Child31", "Text31"}, {"Child32", "Text32"}, {"Child33", "Text33"}},   
	};  */

	private int lastGroupOpened;



	@Override  
	public void onCreate(Bundle savedInstanceState) {  

		super.onCreate(savedInstanceState);  
		setContentView(R.layout.modify_defaults);
		
		/* Initially we don't want to collapse a child */
		lastGroupOpened = -1;

		/* Access the global permission data structures */
		PermissionManager.initialize();
		List<Category> cats = PermissionManager.categories;
		
		/* Lists of Maps for storing the group and child data */
		List<Map<String, String>> groupData =  
			new ArrayList<Map<String, String>>();  
		List<List<Map<String, String>>> childData =  
			new ArrayList<List<Map<String, String>>>();  

		/* Populate a the group maps */
		for (int i = 0; i < cats.size(); i++) {  
			Map<String, String> curGroupMap =  
				new HashMap<String, String>();  
			groupData.add(curGroupMap);  
			curGroupMap.put(KEY1, cats.get(i).getName());  

			/* Populate the children maps */
			List<Map<String, String>> children =  
				new ArrayList<Map<String, String>>();  
			if (cats.size() > i) {  
				for (int j = 0; j < cats.get(i).getPermissions().size(); j++) {  
					Map<String, String> curChildMap =  
						new HashMap<String, String>();  
					children.add(curChildMap);  
					//TODO: Right now we are only storing the mapping to the name - change to the UI
					curChildMap.put(KEY1, cats.get(i).getPermissions().get(j).getName());  
				}  
			}  
			childData.add(children);  
		}
		
		/* Create a new ExpandableListAdapter to control the accordion effect */
		ExpandableListAdapter adapter =  
			new SimpleExpandableListAdapter(  
					this,  
					groupData,  
					android.R.layout.simple_expandable_list_item_1,  
					new String[] { KEY1},  
					new int[] { android.R.id.text1/*, android.R.id.text2*/ },  
					childData,  
					android.R.layout.simple_expandable_list_item_2,  
					new String[] { KEY1},  
					new int[] { android.R.id.text1/*, android.R.id.text2*/ }  
			);  

		/* Create an ExpandableListView to work with the Adapter */
		final ExpandableListView listView =   
			(ExpandableListView) findViewById(R.id.ExpandableListView);  
		listView.setAdapter(adapter);  

		/* Defines the behavior of the group when it is clicked
		 * At the moment this is undefiined */
		listView.setOnGroupClickListener(new OnGroupClickListener() {  
			@Override  
			public boolean onGroupClick(ExpandableListView parent,  
					View v, int groupPosition, long id) {  	    	  
				return false;  
			}        
		});  

		/* Defines reaction to the listView when a group is expanded
		 * We use this listener to implement the exclusivity of open groups */
		listView.setOnGroupExpandListener(new OnGroupExpandListener(){
			@Override
			public void onGroupExpand(int groupPosition) {
				/* Close all groups but the one that was clicked 
				 * Collapse if it is not the first time*/
				if(lastGroupOpened<0 || lastGroupOpened != groupPosition)
					listView.collapseGroup(lastGroupOpened);
				lastGroupOpened = groupPosition;
			}
		});

		/* Defines the behavior of the listView when a child object is clicked
		 * At the moment this does not do anything */
		listView.setOnChildClickListener(new OnChildClickListener() {  
			@Override  
			public boolean onChildClick(ExpandableListView parent, View v,  
					int groupPosition, int childPosition, long id) {  
				return false;  
			}        
		});  
	}    
}  