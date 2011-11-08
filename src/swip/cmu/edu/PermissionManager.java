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
//	public static Application selectedApp = null;
//	public static Permission selectedPermission = null;
	
	public static boolean install(Application toInstall)
	{
		int index = uninsalledApps.indexOf(toInstall);
		if(index < 0)
			return false;
		uninsalledApps.remove(index);
		installedApps.add(toInstall);
		return true;
	}
	
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
		// Create permisions
		Permission p = new Permission("TEST", "Risky but accepted by default", "Very relevant permission.", true);
		p.setAcceptByDefault(true);
		permissionsById.put("TEST", p);
		
		permissionsById.put("RISKY", new Permission("RISKY", "very risky", "Very relevant permission.", true));
		permissionsById.put("NOT_RISKY", new Permission("NOT_RISKY", "not risky", "Very relevant permission.", false));
	}
	
	private static void loadCategories()
	{
		{
			Category c = new Category("Development tools");
			c.addPermission(permissionsById.get("TEST"));
			c.addPermission(permissionsById.get("RISKY"));
			c.addPermission(permissionsById.get("NOT_RISKY"));
			categories.add(c);
		}
		
		{
			Category c = new Category("Your personal information");
			c.addPermission(permissionsById.get("TEST"));
			c.addPermission(permissionsById.get("NOT_RISKY"));
			categories.add(c);
		}
		{
			Category c = new Category("Services that cost you money");
			c.addPermission(permissionsById.get("RISKY"));
			c.addPermission(permissionsById.get("NOT_RISKY"));
			categories.add(c);
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
		app.addRequest(new Request(permissionsById.get("RISKY"), "We need this permission to screw you..."));
		app.addRequest(new Request(permissionsById.get("NOT_RISKY"), "We need this permission to screw you..."));
		
		uninsalledApps.add(app);
		
		app = new Application("Bla", R.drawable.info80, "Yeah, very interesting...");
		app.addRequest(new Request(permissionsById.get("RISKY"), "We need this permission to screw you..."));
		uninsalledApps.add(app);
		
		for(int i=1; i<=10; i++)
		{
			app = new Application("App" + i, R.drawable.warn80, "Nop, nothing to say about this one, but HEY! its FREE!");
			app.addRequest(new Request(permissionsById.get("TEST"), "We need this permission to screw you..."));
			app.addRequest(new Request(permissionsById.get("NOT_RISKY"), "We need this permission to screw you..."));
			uninsalledApps.add(app);
		}
	}
}
