package com.example.ozolirih.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
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
import android.widget.GridLayout;
import android.widget.ImageView;
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
import static com.example.ozolirih.myapplication.R.layout.abc_activity_chooser_view;
import static com.example.ozolirih.myapplication.R.layout.activity_second;

/**
 * Created by ozolirih on 2017.12.22..
 */

public class dynamic extends Activity
{

	public EditText editQty;
	public EditText editCena;
	public TextView showText;
	public TextWatcher watcher;

	public GridLayout gridLayout;
	int r;

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

				int summaid = ((5 * 100) + row);
				TextView summaView = gridLayout.findViewById(summaid);

				String myString = s.toString();
				if (myString.length() == 0)
				{
					myString = "0";
				}

				double jaunaistxt = 0;
				double jaunaistxt1 = Double.parseDouble(myString);


				int viewID = 0;
				if (col == 3)
				{
					viewID = (((col+1) * 100) + row);
					TextView cenaView = gridLayout.findViewById(viewID);
					CharSequence ii = cenaView.getText();
					if (ii.length() == 0)
					{
						ii = "0";
					}

					String starpa = String.valueOf(ii);
					double number = Double.parseDouble(starpa);
					jaunaistxt = (number * jaunaistxt1);
				}
				else if (col == 4)
				{
					viewID = (((col-1) * 100) + row);
					TextView qtyView = gridLayout.findViewById(viewID);
					CharSequence ii = qtyView.getText();
					if (ii.length() == 0)
					{
						ii = "0";
					}

					String starpa = String.valueOf(ii);
					double number = Double.parseDouble(starpa);

					jaunaistxt = (number * jaunaistxt1);
				}

				summaView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

				summaView.setGravity(CENTER);
				summaView.setTextColor( BLACK );
				layoutParam.rowSpec = GridLayout.spec(row);
				layoutParam.columnSpec = GridLayout.spec(5);
				layoutParam.setGravity(CENTER|FILL_HORIZONTAL);
				layoutParam.setMargins(2, 2, 2, 2);
				layoutParam.height = 150;
				double roundOff = Math.round(jaunaistxt * 100.0) / 100.0;
				String jaunaistxt2 = String.valueOf(roundOff);
				summaView.setText(jaunaistxt2);
				summaView.setLayoutParams(layoutParam);

			}
		};
		return watcher;
	}
	@SuppressLint({"WrongConstant", "RtlHardcoded", "LongLogTag"})
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		int column = 6;
		int row = 20;
		//int total = column * row;

		ScrollView scrollView = new ScrollView(this);
		gridLayout = new GridLayout(this);
		scrollView.addView(gridLayout);
		setContentView(scrollView);

		/*GridLayout topLine = new GridLayout( this );
		TextView total = new TextView( this );
		total.setText( "RE kur IR" );
		GridLayout.LayoutParams topLayout= new GridLayout.LayoutParams();
		topLayout.setGravity( CENTER );
		total.setLayoutParams( topLayout );
		setContentView( topLine );*/

		gridLayout.setColumnCount(column);
		gridLayout.setRowCount(row);
		gridLayout.setBackgroundColor(0xff000000);

		Resources res = getResources();
		String[] worktitle = res.getStringArray(R.array.maintitle);

		for ( int c = 0; c < 6; c++)
		{
			int listSize = worktitle.length;
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

					layoutParam.width =100;
					layoutParam.height = 150;

					textView.setLayoutParams(layoutParam);
					gridLayout.addView(textView);

				}

				if (c == 1)
				{textView.setGravity(CENTER_VERTICAL);
					textView.setPadding(25, 0, 0, 0);
					if (r == 0)
					{
						//textView.setGravity(CENTER_VERTICAL);
						textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 28);
						textView.setPadding(0, 0, 0, 0);
						layoutParam.setGravity(BOTTOM);
					}
					textView.setText(worktitle[r]);
					layoutParam.width = 750;
					layoutParam.height = 150;

					textView.setLayoutParams(layoutParam);
					gridLayout.addView(textView);

				}

				if (c == 2)
				{
					layoutParam.width = 150;
					layoutParam.height = 150;
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
					layoutParam.width = 250;
					layoutParam.height = 150;
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

						editQty.setImeOptions(EditorInfo.IME_ACTION_DONE | EditorInfo.IME_FLAG_NO_EXTRACT_UI);
						editQty.setBackgroundColor(WHITE);
						editQty.setTextColor(RED);
						editQty.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
						editQty.setInputType(2 | 8192);
						editQty.setPadding(10, 0, 10, 0);
						editQty.setGravity(CENTER|RIGHT);
						editQty.addTextChangedListener(getWatcher(r,c));
						editQty.setLayoutParams(layoutParam);
						int qtyId = (c*100 + r);
						editQty.setId(qtyId);

						gridLayout.addView(editQty);
					}
				}

				if (c == 4)
				{
					layoutParam.width = 180;
					layoutParam.height = 150;
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
						editCena.setImeOptions(EditorInfo.IME_ACTION_DONE | EditorInfo.IME_FLAG_NO_EXTRACT_UI);
						editCena.setBackgroundColor(WHITE);
						editCena.setTextColor(0xFF004C00);
						editCena.setGravity( CENTER );
						editCena.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
						editCena.setInputType(2 | 8192);
						editCena.setPadding(10, 0, 10, 0);
						editCena.setText("2");
						editCena.addTextChangedListener(getWatcher(r,c));
						editCena.setLayoutParams(layoutParam);
						int cenaid = (c*100 + r);
						editCena.setId(cenaid);

						gridLayout.addView(editCena);
					}
				}

				if (c == 5)
				{
					layoutParam.setGravity(CENTER|FILL_HORIZONTAL);
					layoutParam.height = 150;

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
						gridLayout.addView(showText);

					}
				}
			}
		}
	}
}
