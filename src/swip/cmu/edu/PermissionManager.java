package swip.cmu.edu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import swip.cmu.edu.Application.Request;

public class PermissionManager
{
	private static boolean initialized = false;
	public static HashMap<String, Permission> permissionsById = new HashMap<String, Permission>();
	public static List<Application> installedApps = new ArrayList<Application>();
	public static List<Application> uninsalledApps = new ArrayList<Application>();
	public static List<Category> categories = new ArrayList<Category>();
	
	public static void initialize()
	{
		// Don't initialize more than once. (just in case)
		if(initialized)
			return;
		initialized = true;
		
		// Create all the permissions.
		loadPermissions();
		loadCategories();
		loadApps();
	}
	
	private static void loadPermissions()
	{
		permissionsById.put("TEST", new Permission("TEST", "Give permission", "Very relevant permission.", false, true));
	}
	
	private static void loadCategories()
	{
		{
			Category c = new Category("Development tools");
			c.addPermission(permissionsById.get("TEST"));
		}
		
		{
			Category c = new Category("Your personal information");
		}
		{
			Category c = new Category("Services that cost you money");
		}
		{
			Category c = new Category("Your location");
		}
		{
			Category c = new Category("Your messages");
		}
		{
			Category c = new Category("Network communication");
		}
		{
			Category c = new Category("Your accounts");
		}
		{
			Category c = new Category("Storage");
		}
		{
			Category c = new Category("Phone calls");
		}
		{
			Category c = new Category("Hardware controls");
		}
		{
			Category c = new Category("System tools");
		}
	}
	
	private static void loadApps()
	{
		Application app = new Application("Angry Birds", R.drawable.angrybirds2, "Some birds really, really angry.");
		app.addRequest(new Request(permissionsById.get("TEST"), "We need this permission to screw you..."));
		uninsalledApps.add(app);
		
		app = new Application("Bla", R.drawable.info80, "Yeah, very interesting...");
		app.addRequest(new Request(permissionsById.get("TEST"), "We need this permission to screw you..."));
		uninsalledApps.add(app);
		
		for(int i=1; i<=10; i++)
		{
			app = new Application("App" + i, R.drawable.warn80, "Nop, nothing to say about this one, but HEY! its FREE!");
			app.addRequest(new Request(permissionsById.get("TEST"), "We need this permission to screw you..."));
			uninsalledApps.add(app);
		}
	}
}
