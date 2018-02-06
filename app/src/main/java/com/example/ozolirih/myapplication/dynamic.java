package com.example.ozolirih.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
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
import static android.view.Gravity.FILL;
import static android.view.Gravity.LEFT;
import static android.view.Gravity.NO_GRAVITY;
import static android.view.Gravity.TOP;
import static com.example.ozolirih.myapplication.R.layout.activity_second;

/**
 * Created by ozolirih on 2017.12.22..
 */

public class dynamic extends Activity
{

	@SuppressLint({"WrongConstant", "RtlHardcoded", "LongLogTag"})
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		int column = 6;
		int row = 20;
		int total = column * row;


		ScrollView scrollView = new ScrollView(this);
		GridLayout gridLayout = new GridLayout(this);
		scrollView.addView(gridLayout);

		setContentView(scrollView);


		gridLayout.setColumnCount(column);
		gridLayout.setRowCount(row);
		gridLayout.setBackgroundColor(0xff000000);


		//Paņemam stringu masīvu no string.xml
		Resources res = getResources();
		String[] worktitle = res.getStringArray(R.array.maintitle);


		for (int c = 0; c < 6; c++)
		{

			int listSize = worktitle.length;
			//Set default price

			for (int r = 0, nr = 0; r < listSize; r++, nr++)
			{
				EditText editCena = new EditText(this);
				EditText editQty = new EditText(this);
				TextView textView = new TextView(this);
				GridLayout.LayoutParams layoutParam = new GridLayout.LayoutParams();

				//textView.setTextAlignment(1);


				textView.setGravity(TOP);
				textView.setBackgroundColor(WHITE);
				textView.setTextColor(BLUE);
				textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

				layoutParam.setMargins(2, 2, 2, 2);
				layoutParam.rowSpec = GridLayout.spec(r);
				layoutParam.columnSpec = GridLayout.spec(c);

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
				{
					textView.setPadding(25, 0, 0, 0);
					if (r == 0)
					{
						textView.setGravity(CENTER_VERTICAL);
						textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 28);
						textView.setPadding(0, 0, 0, 0);
						layoutParam.setGravity(BOTTOM);
					}
					textView.setText(worktitle[r]);
					layoutParam.width = 650;
					layoutParam.height = 150;

					textView.setLayoutParams(layoutParam);
					gridLayout.addView(textView);

				}

				if (c == 2)
				{
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

					layoutParam.width = 150;
					layoutParam.height = 150;

					textView.setLayoutParams(layoutParam);
					gridLayout.addView(textView);

				}

				if (c == 3)
				{

					layoutParam.width = 250;
					layoutParam.height = 150;
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
						//EditText editQty = new EditText(this);

						//editQty.setTextAlignment(3);
						editQty.setImeOptions(EditorInfo.IME_ACTION_DONE | EditorInfo.IME_FLAG_NO_EXTRACT_UI);
						editQty.setBackgroundColor(WHITE);
						editQty.setTextColor(RED);
						editQty.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
						editQty.setInputType(2 | 8192);
						editQty.setPadding(10, 0, 10, 0);
						editQty.setText(Integer.toString(r));

						layoutParam.setGravity(BOTTOM);

						editQty.setLayoutParams(layoutParam);
						gridLayout.addView(editQty);
					}
				}

				if (c == 4)
				{
					layoutParam.width = 250;
					layoutParam.height = 150;
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

						layoutParam.setGravity(BOTTOM);

						editCena.setLayoutParams(layoutParam);
						gridLayout.addView(editCena);
					}
				}

				if (c == 5)
				{
					layoutParam.width = 250;
					layoutParam.height = 150;
					if (r == 0)
					{
						String cena = "Summa";
						textView.setGravity(CENTER_HORIZONTAL);
						textView.setText(cena);

						textView.setLayoutParams(layoutParam);
						gridLayout.addView(textView);
					}
					/*else
					{



						layoutParam.columnSpec = GridLayout.spec(3);
						editQty.setLayoutParams(layoutParam);
						currRow = editQty.getLayoutParams();

						//Log.d("",);

						//int sum = 0;

						editQty.getLayoutParams(GridLayout);
						String sum1 = "777";
						sum1 = editQty.getText().toString();
						Log.d("SUM1 = ","" + sum1);
						int sum1int = 333;
						//sum1int = Integer.parseInt(sum1);
						//Log.d("MYINT", "111 = " + sum1int);
						Log.d("MYINT", "Shis ir sum1 strings = " + sum1);


						layoutParam.columnSpec = GridLayout.spec(4);
						editCena.setLayoutParams(layoutParam);

						String sum2 = "Kautkas";
						sum2 = editCena.getText().toString();
						Log.d("SUM2 ", sum2);
						int sum2int = 777;
						//int sum2int = Integer.parseInt(sum2);
						Log.d("MYINT", "222 = " + sum2int);


						String txt = String.valueOf(sum1int * sum2int);
						textView.setGravity(CENTER_HORIZONTAL);
						textView.setText(txt);


						layoutParam.columnSpec = GridLayout.spec(c);
						textView.setLayoutParams(layoutParam);
						gridLayout.addView(textView);
					}*/



				}

			}
		}
	}
}