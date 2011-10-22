/**
 * 
 */
package swip.cmu.edu;

import java.util.ArrayList;
import java.util.List;

/**
 * AN application installed in the system.
 * @author Daniel Langdon
 */
public class Application
{
	/**
	 * Container as a basic tuple to store the permission information for this app.
	 */
	public static class Request
	{
		Permission permission;
		boolean granted;
		String reason;
		
		public Request(Permission permission, boolean granted, String reason)
		{
			super();
			this.permission = permission;
			this.granted = granted;
			this.reason = reason;
		}

		/**
		 * @return the granted
		 */
		public boolean isGranted()
		{
			return granted;
		}

		/**
		 * @param granted the granted to set
		 */
		public void setGranted(boolean granted)
		{
			this.granted = granted;
		}

		/**
		 * @return the permission
		 */
		public Permission getPermission()
		{
			return permission;
		}

		/**
		 * @return the reason
		 */
		public String getReason()
		{
			return reason;
		}
		
		
	}
	
	private String name;
	private String icon;
	private String description;
	private List<Request> requests;
	
	public Application(String name, String icon, String description)
	{
		super();
		this.name = name;
		this.icon = icon;
		this.description = description;
		this.requests = new ArrayList<Request>();
	}
	
	public void addRequest(Request r)
	{
		requests.add(r);
	}
	
	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * @return the icon
	 */
	public String getIcon()
	{
		return icon;
	}
	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}
	/**
	 * @return the permissions
	 */
	public List<Request> getPermissionRequests()
	{
		return requests;
	}
	
	

}
