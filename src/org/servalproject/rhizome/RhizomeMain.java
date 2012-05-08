package org.servalproject.rhizome;

import java.math.BigDecimal;
import java.text.NumberFormat;

import org.servalproject.R;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Rhizome list activity.  Presents the contents of the Rhizome store as a list of names.
 *
 * @author Andrew Bettison <andrew@servalproject.com>
 */
public class RhizomeMain extends Activity {

	// some size constants
	BigDecimal gb = new BigDecimal(1073741824);
	BigDecimal mb = new BigDecimal(1048576);
	BigDecimal kb = new BigDecimal(1024);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.i(Rhizome.TAG, getClass().getName()+".onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rhizome_list);
	}

	@Override
	protected void onStart() {
		Log.i(Rhizome.TAG, getClass().getName()+".onStart()");
		super.onStart();
	}

	@Override
	protected void onResume() {
		Log.i(Rhizome.TAG, getClass().getName()+".onResume()");
		setUpUI();
		super.onResume();
	}

	@Override
	protected void onStop() {
		Log.i(Rhizome.TAG, getClass().getName()+".onStop()");
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		Log.i(Rhizome.TAG, getClass().getName()+".onDestroy()");
		super.onDestroy();
	}

	/**
	 * Set up the interface layout.
	 */
	private void setUpUI() {
		setContentView(R.layout.rhizome_main);
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			Button buttonShare = (Button) this.findViewById(R.id.rhizome_share);
			buttonShare.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						FolderPicker shareDialog = new FolderPicker(RhizomeMain.this, android.R.style.Theme, true);
						shareDialog.setOnClickListener(new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface di, int which) {
									if (which == DialogInterface.BUTTON_POSITIVE)
										Rhizome.addFile(((FolderPicker) di).getPath());
								}
							});
						shareDialog.show();
					}
				});
			Button buttonFind = (Button) this.findViewById(R.id.rhizome_find);
			buttonFind.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						RhizomeMain.this.startActivity(new Intent(RhizomeMain.this, RhizomeList.class));
					}
				});
		} else {
			// If there is not SD card present, grey out the buttons and the storage display.
			;
		}
		setupFreeSpace(state);

<<<<<<< HEAD
	}

	private void setupFreeSpace(String state) {
		ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
		TextView progressLabel = (TextView) findViewById(R.id.progress_label);

		// checks if the SD card is attached to the Android device
		if (state.equals(Environment.MEDIA_MOUNTED)
				|| state.equals(Environment.MEDIA_UNMOUNTED)
				|| state
						.equals(Environment.MEDIA_MOUNTED_READ_ONLY))
		{
			// obtain the stats from the root of the SD card.
			StatFs stats = new StatFs(Environment.getExternalStorageDirectory()
					.getPath());

			String outputInfo = "Total Size: ";

			BigDecimal blockCount = new BigDecimal(stats.getBlockCount());
			BigDecimal blockSize = new BigDecimal(stats.getBlockSize());
			BigDecimal availBlocks = new BigDecimal(stats.getAvailableBlocks());

			BigDecimal totalSize = blockCount.multiply(blockSize);
			BigDecimal freeSpace = availBlocks.multiply(blockSize);

			NumberFormat numberFormat = NumberFormat.getInstance();
			numberFormat.setGroupingUsed(false);
			numberFormat.setMaximumFractionDigits(2);

			// Output the SD card's total size in gigabytes, megabytes
			BigDecimal totalSizeGb = totalSize.divide(gb);
			BigDecimal totalSizeMb = totalSize.divide(mb);
			outputInfo += numberFormat.format(totalSizeGb) + " GB ("
					+ numberFormat.format(totalSizeMb) + " MB)\n";

			// Output the SD card's available free space in gigabytes,
			// megabytes
			BigDecimal freeSpaceGb = freeSpace.divide(gb);
			BigDecimal freeSpaceMb = freeSpace.divide(mb);
			outputInfo += "Free Space: "
					+ numberFormat.format(freeSpaceGb) + " GB ("
					+ numberFormat.format(freeSpaceMb) + " MB)";

			progressBar.setMax(totalSizeMb.intValue());
			progressBar.setProgress(totalSizeMb.subtract(freeSpaceMb)
					.intValue());

			progressLabel.setText(outputInfo);
		}
		else // external storage was not found
		{
			// output the SD card state
			progressLabel.setTextColor(Color.RED);
			progressLabel.setText("SD card not found! SD card is \""
					+ state + "\".");
		}
=======
>>>>>>> Connect main menu "Share Us" button
	}

}
