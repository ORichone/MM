package com.example.ozolirih.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    /* final File attac = new File(FILE); */

    // Set PDF document Properties
    public void addMetaData(Document document)
    {
        document.addTitle("RESUME");
        document.addSubject("Person Info");
        document.addKeywords("Personal, Education, Skills");
        document.addAuthor("TAG");
        document.addCreator("TAG");
    }

    @SuppressLint("LongLogTag")
    public void addTitlePage(Document document) throws DocumentException
    {
        // Font Style for Document
        Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
        Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD
                | Font.UNDERLINE, BaseColor.GRAY);
        Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        Font normal = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

        // Start New Paragraph
        Paragraph prHead = new Paragraph();
        // Set Font in this Paragraph
        prHead.setFont(titleFont);
        // Add item into Paragraph
        prHead.add("RESUME – Name\n");

        // Create Table into Document with 1 Row
        PdfPTable myTable = new PdfPTable(1);
        // 100.0f mean width of table is same as Document size
        myTable.setWidthPercentage(100.0f);

        // Create New Cell into Table
        PdfPCell myCell = new PdfPCell(new Paragraph(""));
        myCell.setBorder(Rectangle.BOTTOM);

        // Add Cell into Table
        myTable.addCell(myCell);

        prHead.setFont(catFont);
        prHead.add("\nName1 Name2\n");
        prHead.setAlignment(Element.ALIGN_CENTER);

        // Add all above details into Document
        document.add(prHead);
        document.add(myTable);
        document.add(myTable);

        // Now Start another New Paragraph
        Paragraph prPersinalInfo = new Paragraph();
        prPersinalInfo.setFont(smallBold);
        prPersinalInfo.add("Address 1\n");
        prPersinalInfo.add("Address 2\n");
        prPersinalInfo.add("City: SanFran.  State: CA\n");
        prPersinalInfo.add("Country: USA Zip Code: 000001\n");
        prPersinalInfo.add("Mobile: 9999999999 Fax: 1111111 Email: john_pit@gmail.com \n");

        prPersinalInfo.setAlignment(Element.ALIGN_CENTER);

        document.add(prPersinalInfo);
        document.add(myTable);
        document.add(myTable);

        Paragraph prProfile = new Paragraph();
        prProfile.setFont(smallBold);
        prProfile.add("\n \n Profile : \n ");
        prProfile.setFont(normal);
        prProfile.add("\nI am Mr. XYZ. I am Android Application Developer at TAG.");

        prProfile.setFont(smallBold);
        document.add(prProfile);
        document.add(new Paragraph("Hello World!"));

        // Create new Page in PDF
        document.newPage();
    }

    //public String FILE = "nekas";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_second);


        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            Log.d("Permisijas uzliktas", "Davai liekam kaut kā");
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }

            String xls = Environment.getExternalStorageDirectory().toString()+"/excel.xls";
            final File sss = new File(xls);
            Workbook wb = new HSSFWorkbook();
            FileOutputStream fileOut = null;

            CreationHelper createHelper = wb.getCreationHelper();
            Sheet sheet = wb.createSheet("new sheet");

            // Create a row and put some cells in it. Rows are 0 based.
            Row row = sheet.createRow((short)0);
            // Create a cell and put a value in it.
            Cell cell = row.createCell(0);
            cell.setCellValue(1);

            // Or do it on one line.
            row.createCell(1).setCellValue(1.2);
            row.createCell(2).setCellValue(
                    createHelper.createRichTextString("This is a string"));
            row.createCell(3).setCellValue(true);

            try {
                fileOut = new FileOutputStream(sss);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                wb.write(fileOut);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                assert fileOut != null;
                fileOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //File xxx = new File(String.valueOf(sss));



        // Destination Folder and File name
        //String FILE = Environment.getExternalStorageDirectory().toString()+"/PDF/"+"Name.pdf";
        //final String FILE = getDataDir().getAbsolutePath()+"/Name.pdf";
        final String FILE = Environment.getExternalStorageDirectory()+"/Name.pdf";
        final File attac = new File(FILE);
        String path = getFilesDir().getAbsolutePath();
        Log.d("PATHPATHPAPATHPATH",path);
        Log.d("!!!!!!!!!!!",FILE);
        //Log.d("myTag",FILE2);

        // Add Permission into Manifest.xml
        //

        // Create New Blank Document
        Document document = new Document(PageSize.A4);

        // Create Directory in External Storage
        //String root = Environment.getExternalStorageDirectory().toString();
        //File myDir = new File(root);
        //File myDir1 = new File("/storage/emulated/0/PDF");
        //toString(myDir);
        //Log.d("myTag",String.valueOf(myDir));
       //String a = "FALSE";
       //boolean booll = false;
       //try {
       //    booll = myDir.mkdirs();
       //    if (booll) {
       //       a = "TRUE";
       //    }
       //    Log.d("myTag",a);
       //} catch (SecurityException e){
       //    Log.d("mytag", String.valueOf(e));
       //}


        // Create Pdf Writer for Writting into New Created Document
        try {
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Open Document for Writting into document
        document.open();

        // User Define Method
        addMetaData(document);
        try {
            addTitlePage(document);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        // Close Document after writting all content
        document.close();

       Button sendButton = (Button) findViewById(R.id.sendButton);
       sendButton.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view) {


               //String filename="contacts_sid.vcf";
               //File filelocation = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), filename);
               //Uri path = Uri.fromFile(filelocation);
               Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
               emailIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

               // set the type to 'email'
               emailIntent.setType("vnd.android.cursor.dir/email");
               String to[] = {"rihards.ozolinsh@gmail.com"};
               emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
               // the attachment
               //Uri ppp =Uri.fromFile(attac);
               ArrayList<Uri> uris = new ArrayList<Uri>();
               Uri ppp = FileProvider.getUriForFile(MainActivity.this, BuildConfig.APPLICATION_ID + ".provider",attac);
               Uri xxx = FileProvider.getUriForFile(MainActivity.this, BuildConfig.APPLICATION_ID + ".provider",sss);
               uris.add(ppp);
               uris.add(xxx);
               emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
               //emailIntent.getParcelableArrayListExtra(Intent.EXTRA_STREAM, xxx);
               // the mail subject
               emailIntent.putExtra(Intent.EXTRA_SUBJECT, "What ever");
               startActivity(Intent.createChooser(emailIntent , "Send email..."));
           }
       });


        Button calcBtn = (Button) findViewById(R.id.calcBtn);
        calcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            // EditText noteksaNumb = (EditText) findViewById(R.id.noteksaNumb);
            // EditText jumtsNumb = (EditText) findViewById(R.id.jumtsNumb);
            // TextView summaNumb = (TextView) findViewById(R.id.summaNumb);
            // TextView notekasSumtextView = (TextView) findViewById(R.id.notekasSumtextView);
            // TextView jumtsSumtextView = (TextView) findViewById(R.id.jumtsSumtextView);

            // double num1 = Double.parseDouble(noteksaNumb.getText().toString());
            // double num2 = Double.parseDouble(jumtsNumb.getText().toString());

            // double notekaSum = 0;
            // double jumtsSum = 0;
            // if (num1 > 0)
            // {
            //     notekaSum = num1 * 2;
            //     notekasSumtextView.setText(notekaSum + "");
            // }
            // if (num2 > 0)
            // {
            //     jumtsSum = num2 * 5;
            //     jumtsSumtextView.setText(jumtsSum + "");
            // }
            // double sum = notekaSum + jumtsSum;
            // if (sum > 0)
            // {
            //     summaNumb.setText(sum + "");
            // }



            }
        });

    }
}
