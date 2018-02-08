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
import static com.example.ozolirih.myapplication.R.layout.activity_second;

/**
 * Created by ozolirih on 2017.12.22..
 */

public class dynamic extends Activity
{

	//EditText resultTxt = new EditText(this);
	//EditText resultTxt1 = new EditText(this);
	//EditText showText = new EditText(this);

	public EditText resultTxt;
	public EditText resultTxt1;
	public EditText editQty;

	//public TextView showText;
	public TextWatcher watcher;

	public GridLayout gridLayout;
	int r, c, nr;

	protected TextWatcher getWatcher(int sss){
		final int id = sss;

		watcher = new TextWatcher()
		{

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count){}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after){}

			@Override
			public void afterTextChanged(Editable s)
			{

				String asd = s.toString();
//				Log.d("DEBUG", "Sākos strings = " + s.toString());
				Log.d("DEBUG", "Izmainītais strings = " + asd);


				GridLayout.LayoutParams layoutParam = new GridLayout.LayoutParams();

				TextView showText = new TextView(dynamic.this);

				Log.d("DEBUG", "Watcher ID = "+ id);
				int childInx = ((c-1) * (r)) + id;
				Log.d("DEBUG", "Child ID = "+ childInx + " r = " + r + " c = " + (c-1));



				showText.setPadding(25, 0, 0, 0);
				showText.setGravity(CENTER_VERTICAL);
				showText.setBackgroundColor(WHITE);
				showText.setTextColor(BLUE);
				showText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
				showText.setText(asd);

				layoutParam.setGravity(CENTER|FILL_HORIZONTAL);
				layoutParam.setMargins(2, 2, 2, 2);
				layoutParam.rowSpec = GridLayout.spec(id);
				layoutParam.columnSpec = GridLayout.spec(5);

				layoutParam.height = 150;


				showText.setLayoutParams(layoutParam);
				gridLayout.addView(showText);
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
		int total = column * row;


		ScrollView scrollView = new ScrollView(this);
		gridLayout = new GridLayout(this);
		scrollView.addView(gridLayout);

		setContentView(scrollView);


		gridLayout.setColumnCount(column);
		gridLayout.setRowCount(row);
		gridLayout.setBackgroundColor(0xff000000);


		//Paņemam stringu masīvu no string.xml
		Resources res = getResources();
		String[] worktitle = res.getStringArray(R.array.maintitle);

		for ( c = 0; c < 6; c++)
		{
			Log.d("DEBUG", "/////// pirmais cikls "+ c);

			int listSize = worktitle.length;
			//Set default price

			for (r = 0, nr = 0; r < listSize; r++, nr++)
			{
				Log.d("DEBUG", "/////// Otrais cikls " + r);

				EditText editCena = new EditText(this);
				TextView textView = new TextView(this);
				GridLayout.LayoutParams layoutParam = new GridLayout.LayoutParams();

				//textView.setTextAlignment(1);


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

					if (nr > 0)
					{
						String number = Integer.toString(nr);
						textView.setText(number);
					}
					else
					{
						textView.setBackgroundColor(RED);
					}

					layoutParam.width = 50;
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
						String m = "Dau";
						textView.setGravity(CENTER_HORIZONTAL);
						textView.setText(m);

						textView.setLayoutParams(layoutParam);
						gridLayout.addView(textView);

					}
					else
					{

						editQty = new EditText(dynamic.this);

						editQty.setId(r);
						editQty.setImeOptions(EditorInfo.IME_ACTION_DONE | EditorInfo.IME_FLAG_NO_EXTRACT_UI);
						editQty.setBackgroundColor(WHITE);
						editQty.setTextColor(RED);
						editQty.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
						editQty.setInputType(2 | 8192);
						editQty.setPadding(10, 0, 10, 0);
						//editQty.setText(Integer.toString(r));
						editQty.setGravity(CENTER|RIGHT);



						editQty.addTextChangedListener(getWatcher(r));

						editQty.setLayoutParams(layoutParam);
						gridLayout.addView(editQty);





						int s = editQty.getId();
						Log.d("DEBUG", "EditQty ID = "+ s);

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

						//editQty.setTextAlignment(3);
						editCena.setImeOptions(EditorInfo.IME_ACTION_DONE | EditorInfo.IME_FLAG_NO_EXTRACT_UI);
						editCena.setBackgroundColor(WHITE);
						editCena.setTextColor(GREEN);
						editCena.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
						editCena.setInputType(2 | 8192);
						editCena.setPadding(10, 0, 10, 0);
						editCena.setText("1");

						//layoutParam.setGravity(BOTTOM);

						editCena.setLayoutParams(layoutParam);
						gridLayout.addView(editCena);
					}
				}

				if (c == 5)
				{
					layoutParam.setGravity(CENTER|FILL_HORIZONTAL);

					//layoutParam.width = 250;
					//layoutParam.setGravity(7);
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
						textView.setBackgroundColor(WHITE);
						//textView.setGravity(CENTER_HORIZONTAL);
						//textView.setText(txt);

						textView.setLayoutParams(layoutParam);
						int childCount = gridLayout.getChildCount();
						Log.d("DEBUG", "childCount ID = "+ childCount);
						gridLayout.addView(textView);
					}
				}
			}
		}
	}
}
