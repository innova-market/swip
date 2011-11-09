package swip.cmu.edu.activity;

import swip.cmu.edu.Application;
import swip.cmu.edu.Application.Request;
import swip.cmu.edu.PermissionManager;
import swip.cmu.edu.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * 
 * @author Daniel Langdon
 * TODO "Cancel" button functionality is not really working for modification of permissions. 
 */
public class ModifyPermissionsActivity extends Activity
{
	PermissionRow beingReviewed = null;

	class PermissionRow extends TableRow
	{
		ImageView risk;
		ImageView loss;
		TextView text;
		// ToggleButton grant;
		CheckBox check;
		Request request;

		public PermissionRow(final Context context, Request reqest, final int requestIndex)
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
			text.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT, 20));
			text.setClickable(true);
			text.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					beingReviewed = PermissionRow.this;
					Intent intent = new Intent(context, PermissionDetailsActivity.class);
					intent.putExtra("requestId", requestIndex);
					startActivityForResult(intent, 0);
				}
			});

			// grant = new ToggleButton(context);
			// grant.setTextOn("Yes");
			// grant.setTextOff("No");
			// grant.setChecked(true); // TODO link

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

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.modify_permissions);
		
		Application beingInstalled = PermissionManager.getSelectedApp();

		// Set the app icon and name
		ImageView imageView = (ImageView) findViewById(R.id.appImage);
		imageView.setImageResource(beingInstalled.getIcon());

		TextView textView = (TextView) findViewById(R.id.appName);
		textView.setText(beingInstalled.getName());

		TableLayout table = (TableLayout) findViewById(R.id.permissionTable);

		// Fill this screen with the information from the permissions, line by line.
		int i = 0;
		for (Request r : beingInstalled.getPermissionRequests())
		{
			// Add risk if present.
			table.addView(new PermissionRow(this, r, i++));
		}

		// Wait for actions on the buttons.
		Button modify = (Button) findViewById(R.id.modify);
		modify.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				finish();
			}
		});
		Button accept = (Button) findViewById(R.id.accept);
		accept.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				// TODO Plug Dev's screen
				// startActivity(new Intent(ShowRiskActivity.this,
				// ModifyPermissionsActivity.class));
				PermissionManager.installSelected();
				finish();
			}
		});
		
		switch (PermissionManager.getMode())
		{
			case INSTALLING:
				modify.setText("Abort Installation");
				accept.setText("Accept & Install");
				break;
			case MODIFYING:
				modify.setText("Cancel");
				accept.setText("Save & Exit");
				break;
		}
	}

	@Override
	public void onResume()
	{
		super.onResume();
	}

	/**
	 * Update any changes in the request being observed.
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (beingReviewed != null)
		{
			beingReviewed.update();
			beingReviewed = null;
		}
	}

}