package com.example.ozolirih.myapplication;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsoluteLayout;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.apache.poi.hssf.util.HSSFColor;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.BLUE;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.WHITE;
import static android.view.Gravity.BOTTOM;
import static android.view.Gravity.CENTER;
import static android.view.Gravity.CENTER_HORIZONTAL;
import static android.view.Gravity.CENTER_VERTICAL;
import static android.view.Gravity.END;
import static android.view.Gravity.FILL;
import static android.view.Gravity.FILL_HORIZONTAL;
import static android.view.Gravity.LEFT;
import static android.view.Gravity.NO_GRAVITY;
import static android.view.Gravity.RIGHT;
import static android.view.Gravity.TOP;
import static android.view.Gravity.getAbsoluteGravity;
import static android.widget.ListPopupWindow.WRAP_CONTENT;
import static com.example.ozolirih.myapplication.R.layout.abc_activity_chooser_view;
import static com.example.ozolirih.myapplication.R.layout.activity_second;
import static java.security.AccessController.getContext;

/**
 * Created by ozolirih on 2017.12.22..
 */

public class dynamic extends Activity
{

	public EditText editQty;
	public EditText editCena;
	public TextView showText;
	public TextWatcher watcher;
	public LinearLayout linearlayout;
	public GridLayout gridLayout;
	int r;

/*##############################################################

	WATCHER FOR FILLING SUM COLUMN

##############################################################*/


	protected TextWatcher getWatcher(int rinda, int colona){
		final int row = rinda;
		final int col = colona;


		watcher = new TextWatcher()
		{

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count){}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after){}

			@Override
			public void afterTextChanged(Editable s)
			{

				GridLayout.LayoutParams layoutParam = new GridLayout.LayoutParams();

				String myString = s.toString();
				if (myString.length() == 0)
				{
					myString = "0";
				}
				double jaunaistxt1 = Double.parseDouble(myString);
				double jaunaistxt = 0;

				Log.d("DEBUG", "Iegutais jaunais txt no view = " + jaunaistxt1 );
				Log.d("ROW", "ROW = " + row);
				Log.d("COL", "COL = " + col);

				int viewID = 0;
				int summaid = ((5 * 100) + row);
				TextView summaView = gridLayout.findViewById(summaid);
				Log.d("DEBUG", "Summa ID = " + summaid );

				if (col == 3)
				{
					viewID = (((col+1) * 100) + row);
					TextView cenaView = gridLayout.findViewById(viewID);
					CharSequence ii = cenaView.getText();
					Log.d("DEBUG", "rrrrooooowww " + row );
					Log.d("DEBUG", "Text to col + 1 = " + ii );
					if (ii.length() == 0)
					{
						ii = "0";
					}

					String starpa = String.valueOf(ii);
					double number;
					number = Double.parseDouble(starpa);
					jaunaistxt = (number * jaunaistxt1);
					Log.d("DEBUG", "Cenaview ID  = " + viewID );
					Log.d("DEBUG", " CenaView number = " + number );

				}
				else if (col == 4)
				{
					viewID = (((col-1) * 100) + row);
					TextView qtyView = gridLayout.findViewById(viewID);
					CharSequence ii = qtyView.getText();
					Log.d("DEBUG", "II" + ii );
					if (ii.length() == 0)
					{
						ii = "0";
					}

					String starpa = String.valueOf(ii);
					double number;
					number = Double.parseDouble(starpa);
					jaunaistxt = (number * jaunaistxt1);
					Log.d("DEBUG", " qtyView ID  = " + viewID );
					Log.d("DEBUG", " qtyView number = " + number );

				}

				Log.d("DEBUG", "ViewID  = " + viewID );
				Log.d("DEBUG", "SummID = " + summaid);
				Log.d("DEBUG", "jaunaistxt = " + jaunaistxt);





				layoutParam.rowSpec = GridLayout.spec(row);
				layoutParam.columnSpec = GridLayout.spec(5);
				layoutParam.setGravity(CENTER|FILL_HORIZONTAL);
				layoutParam.setMargins(2, 2, 2, 2);
				layoutParam.height = 75;
				double roundOff = Math.round(jaunaistxt * 100.0) / 100.0;
				String jaunaistxt2 = String.valueOf(roundOff);

				Log.d("DEBUG", "jaunaistxt2 = " + jaunaistxt2);

				summaView.setPadding(10, 0, 10, 0);
				summaView.setGravity(CENTER|LEFT);
				summaView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
				summaView.setText(jaunaistxt2);
				summaView.setLayoutParams(layoutParam);

			}
		};
		return watcher;
	}

/*###########################################################

		WATCHER FOR FILLING TOTAL SUM

###########################################################*/

	protected TextWatcher getWatcherTotalSum(int list){
		final int lastrow = list;


		watcher = new TextWatcher()
		{

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count){}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after){}

			@Override
			public void afterTextChanged(Editable s)
			{
				double subsum = 0;

				for (int rr = 1; rr < lastrow; rr++)
				{
					int summaid = ((5 * 100) + rr);
					TextView totalSum = gridLayout.findViewById(summaid);

					CharSequence ii = totalSum.getText();
					String starp;
					starp = String.valueOf(ii);
					if (starp.length() > 0)
					{
						double number;
						number = Double.parseDouble(starp);
						subsum = subsum + number;
					}
				}
				String totalsubsum = String.valueOf(subsum);
				int asd =777;
				TextView totalSum = linearlayout.findViewById(asd);
				totalSum.setPadding(10, 0, 10, 0);
				totalSum.setGravity(CENTER|LEFT);
				totalSum.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
				totalSum.setText(totalsubsum);

			}
		};
		return watcher;
	}

/*###########################################################

		SUM COLUMN CALCULATION FUNCTION

###########################################################*/

	private static int calcsum()
	{

		return 0;
	}


	@SuppressLint({"WrongConstant", "RtlHardcoded", "LongLogTag", "ResourceType"})
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		int column = 6;
		int row = 20;

		gridLayout = new GridLayout(this);
		gridLayout.setColumnCount(column);
		gridLayout.setRowCount(row);
		gridLayout.setBackgroundColor(0xff000000);


		Resources res = getResources();
		String[] worktitle = res.getStringArray(R.array.maintitle);
		int listSize = worktitle.length;

		for ( int c = 0; c < 6; c++)
		{
			for (r = 0; r < listSize; r++)
			{
				TextView textView = new TextView(this);
				GridLayout.LayoutParams layoutParam = new GridLayout.LayoutParams();

				textView.setGravity(CENTER);
				textView.setBackgroundColor(WHITE);
				textView.setTextColor(BLUE);
				textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

				layoutParam.setMargins(2, 2, 2, 2);
				layoutParam.rowSpec = GridLayout.spec(r);
				layoutParam.columnSpec = GridLayout.spec(c);
				layoutParam.setGravity(CENTER);
				//layoutParam.height = (WRAP_CONTENT);
				final float scale = getResources().getDisplayMetrics().density;
				int pixels = (int) (50 * scale + 0.5f);

				layoutParam.height = pixels;

				if (c == 0)
				{

					if (r > 0)
					{
						String number = Integer.toString(r);
						textView.setText(number);
					}
					else
					{
						textView.setBackgroundColor(RED);
					}


					final float sscale = getResources().getDisplayMetrics().density;
					int pixelis = (int) (50 * sscale + 0.5f);

					layoutParam.width = pixels;

					textView.setLayoutParams(layoutParam);
					gridLayout.addView(textView);

				}

				if (c == 1)
				{textView.setGravity(CENTER_VERTICAL);
					textView.setPadding(25, 0, 0, 0);
					if (r == 0)
					{
						//textView.setGravity(CENTER_VERTICAL);
						textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 28);
						textView.setPadding(0, 0, 0, 0);
						layoutParam.setGravity(BOTTOM);
					}
					textView.setText(worktitle[r]);
					layoutParam.width = 450;
					//layoutParam.height = 150;

					textView.setLayoutParams(layoutParam);
					gridLayout.addView(textView);

				}

				if (c == 2)
				{
					layoutParam.width = 100;
					//layoutParam.height = 150;
					layoutParam.setGravity(CENTER|LEFT);
					textView.setLayoutParams(layoutParam);

					textView.setGravity(CENTER_HORIZONTAL);
					if (r != 0)
					{
						String m = "m2";
						textView.setText(m);
					}
					else
					{
						String merv = "Merv";
						textView.setText(merv);
					}
					gridLayout.addView(textView);
				}
				if (c == 3)
				{
					layoutParam.width = 180;
					//layoutParam.height = 150;
					layoutParam.setGravity(CENTER|RIGHT);

					if (r == 0)
					{
						String m = "Daudzums";
						textView.setGravity(CENTER_HORIZONTAL);
						textView.setText(m);
						textView.setLayoutParams(layoutParam);

						gridLayout.addView(textView);
					}
					else
					{
						editQty = new EditText(dynamic.this);

						editQty.setImeOptions(EditorInfo.IME_ACTION_NEXT |  EditorInfo.IME_FLAG_NO_EXTRACT_UI);
						editQty.setBackgroundColor(WHITE);
						editQty.setTextColor(RED);
						editQty.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
						editQty.setInputType(2 | 8192);
						editQty.setPadding(10, 0, 10, 0);
						editQty.setGravity(CENTER|RIGHT);
						editQty.addTextChangedListener(getWatcher(r,c));
						editQty.setLayoutParams(layoutParam);
						int qtyId = (c*100 + r);
						Log.d("QTY ID ", "QTY ID = " + qtyId);
						editQty.setId(qtyId);

						gridLayout.addView(editQty);
					}
				}

				if (c == 4)
				{
					layoutParam.width = 180;
					//layoutParam.height = 150;
					layoutParam.setGravity(CENTER|LEFT);
					if (r == 0)
					{
						String cena = "Cena";
						textView.setGravity(CENTER_HORIZONTAL);
						textView.setText(cena);

						textView.setLayoutParams(layoutParam);
						gridLayout.addView(textView);
					}
					else
					{
						editCena = new EditText(dynamic.this);
						editCena.setImeOptions(EditorInfo.IME_ACTION_NEXT | EditorInfo.IME_FLAG_NO_EXTRACT_UI);
						editCena.setBackgroundColor(WHITE);
						editCena.setTextColor(GREEN);
						editCena.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
						editCena.setInputType(2 | 8192);
						editCena.setPadding(10, 0, 10, 0);
						editCena.setText("2");
						editCena.addTextChangedListener(getWatcher(r,c));
						editCena.setLayoutParams(layoutParam);
						int cenaid = (c*100 + r);
						Log.d("CENA ID ", "CENA ID = " + cenaid);
						editCena.setId(cenaid);

						gridLayout.addView(editCena);
					}
				}

				if (c == 5)
				{
					layoutParam.setGravity(CENTER|FILL_HORIZONTAL);
					//layoutParam.height = 150;

					if (r == 0)
					{
						String cena = "Summa";
						textView.setGravity(CENTER_HORIZONTAL);
						textView.setText(cena);
						textView.setLayoutParams(layoutParam);
						gridLayout.addView(textView);
					}
					else
					{
						showText = new TextView(dynamic.this);
						showText.setBackgroundColor(WHITE);
						showText.setLayoutParams(layoutParam);
						int summaid = (c*100 + r);
						Log.d("SUMMAID", "Summas ID = " + summaid);
						showText.setId(summaid);
						showText.addTextChangedListener(getWatcherTotalSum(listSize));
						gridLayout.addView(showText);

					}
				}
			}
		}

		RelativeLayout relativelayout = new RelativeLayout(this);
		RelativeLayout.LayoutParams rlparams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
		rlparams.addRule(RelativeLayout.ALIGN_PARENT_TOP);

		ScrollView scrollView = new ScrollView(this);
		ScrollView.LayoutParams layoutParamsScroll = new ScrollView.LayoutParams( ScrollView.LayoutParams.MATCH_PARENT, ScrollView.LayoutParams.MATCH_PARENT);
		scrollView.setLayoutParams(layoutParamsScroll);
		scrollView.addView(gridLayout);

		linearlayout = new LinearLayout(this);
		linearlayout.setOrientation(LinearLayout.HORIZONTAL);
		linearlayout.setBackgroundColor(Color.GREEN);

		LinearLayout.LayoutParams llparam = new LinearLayout.LayoutParams(500, 90);
		llparam.setMargins(5,5,5,5);

		TextView lltext = new TextView(this);
		lltext.setText("kaut kas bus");
		lltext.setBackgroundColor(WHITE);
		lltext.setId(777);

		//int ssdassd;
		int ssdassd = lltext.getId();
		Log.d("TEXTVIEW ID", String.valueOf(ssdassd));



		lltext.setLayoutParams(llparam);
		linearlayout.addView(lltext);


		relativelayout.addView(scrollView);
		//relativelayout.addView(linearlayout,rlparams);
		setContentView(relativelayout);


	}
}
