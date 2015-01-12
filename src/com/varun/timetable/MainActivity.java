package com.varun.timetable;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	String s = "file:///android_asset/";
	String sec = "index_sec.htm";
	String fac = "index_fac.htm";
	String room = "index_room.htm";
	String load = "tt_load.htm";

	int flag = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		final Context context = this;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toast.makeText(getApplicationContext(), "loaded",
				Toast.LENGTH_LONG).show();

		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle("Select an option")
				.setItems(R.array.ReadMethod,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								if (which == 5) {
									AlertDialog.Builder ExitDb = new AlertDialog.Builder(
											MainActivity.this);
									ExitDb.setTitle("Are you sure?")
											.setCancelable(false)
											.setPositiveButton(
													"Yes",
													new DialogInterface.OnClickListener() {
														public void onClick(
																DialogInterface dialog,
																int id) {
															finish();
															System.exit(0);
															return;
														}
													})
											.setNegativeButton(
													"No",
													new DialogInterface.OnClickListener() {
														public void onClick(
																DialogInterface dialog,
																int id) {
															onCreate(null);
														}
													});
									ExitDb.create();
									ExitDb.show();

								}

								if (which == 1) {
									s = "file:///android_asset/";
								} else if (which == 0) {
									s = "old";
									Intent intent = new Intent(context,
											Varun_web.class);
									intent.putExtra("loadurl", s);
									intent.putExtra("old", 1);
									startActivity(intent);
								} else if (which == 2) {
									s = "http://www.gbuonline.in/timetables/";
								} else if (which == 3) {
									s = "http://tech-league.tk/blog/index.php";
									flag = 2;

								} else if (which == 4) {

									AlertDialog.Builder UpdateDb = new AlertDialog.Builder(
											MainActivity.this);
									UpdateDb.setTitle("GBU timetable")
											.setMessage(
													"This is an android port of timetables available at gbuonline.in\n\n"
															+ "Developed By Varun Garg, code available on-\n"
															+ "github.com/opengbu/timtable-android/"
															+ "\n\nWould you like to update now?")
											.setCancelable(false)
											.setNegativeButton(
													"Yes",
													new DialogInterface.OnClickListener() {
														public void onClick(
																DialogInterface dialog,
																int id) {
															Intent browserIntent = new Intent(
																	Intent.ACTION_VIEW,
																	Uri.parse("https://googledrive.com/host/0Bwy2XRCWrXCEVS1HLWhiVjY5LVE/TimeTable.apk"));
															startActivity(browserIntent);
															finish();
															System.exit(0);
															return;
														}
													})
											.setPositiveButton(
													"No",
													new DialogInterface.OnClickListener() {
														public void onClick(
																DialogInterface dialog,
																int id) {
															onCreate(null);
														}
													});
									UpdateDb.create();
									UpdateDb.show();
								}

								if (flag == 2) {
									Intent intent = new Intent(context,
											Varun_web.class);
									intent.putExtra("loadurl", s);
									intent.putExtra("old", 0);
									startActivity(intent);
								}
								if (which == 1 || which == 2) {
									AlertDialog.Builder b2 = new AlertDialog.Builder(
											MainActivity.this);
									b2.setTitle("Select Timetable Type")
											.setItems(
													R.array.Timetabletype,
													new DialogInterface.OnClickListener() {
														@Override
														public void onClick(
																DialogInterface dialogs,
																int which2) {
															if (which2 == 4)
																onCreate(null);
															else {
																flag = 1;
																if (which2 == 0)
																	s = s + sec;
																else if (which2 == 1)
																	s = s + fac;
																else if (which2 == 2)
																	s = s
																			+ room;
																else if (which2 == 3)
																	s = s
																			+ load;
																else
																	flag = 0;
																Intent intent = new Intent(
																		context,
																		Varun_web.class);
																intent.putExtra(
																		"loadurl",
																		s);
																intent.putExtra(
																		"old",
																		0);
																startActivity(intent);
															}
														}
													}).setCancelable(false);
									b2.create();
									b2.show();
								}
							}
						}).setCancelable(false);
		builder.create();
		builder.show();

	}

	public void onBackPressed() {

		// fixes the bug when app is restarted after exiting it with back button
		finish();
		System.exit(0);
		return;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}