package swip.cmu.edu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import swip.cmu.edu.Application.Request;

public class PermissionManager
{
	static HashMap<String, Permission> permissionsById = new HashMap<String, Permission>();
	static List<Application> apps = new ArrayList<Application>();
	static List<Category> categories = new ArrayList<Category>();
	
	static void InitializeDB()
	{
		// Create all the permissions.
		loadPermissions();
		loadCategories();
		loadApps();
	}
	
	private static void loadPermissions()
	{
		permissionsById.put("TEST", new Permission("TEST", "Dar permiso", "Este es un permiso muy importante.", false, true));
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
		Application app = new Application("Angry Birds", "angrybirds2.png", "Some birds really, really angry.");
		app.addRequest(new Request(permissionsById.get("TEST"), false, "We need this permission to screw you..."));
	}
}
