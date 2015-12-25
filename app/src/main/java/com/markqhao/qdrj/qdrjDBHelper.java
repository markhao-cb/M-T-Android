package com.markqhao.qdrj;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class qdrjDBHelper extends SQLiteOpenHelper {

    //The Android's default system path of your application database.
    private static String DB_PATH = "/data/data/com.markqhao.qdrj/databases/";
    private static String DB_NAME = "new5qdrj.db";
//    public static String TABLE_SCHEDULE = "schedule";
//    public static String TABLE_ROSTER = "roster";
//    public static final String SCHEDULE_HTEAM = "hteam";
//    public static final String SCHEDULE_VTEAM = "vteam";
//    public static final String SCHEDULE_DTTM = "dttm";
//    public static final String ROSTER_TEAM = "team";
//    public static final String ROSTER_NUMBER = "number";
//    public static final String ROSTER_NAME = "name";
//    public static final String ROSTER_AGE = "age";
//    public static final String ROSTER_POSITION = "position";
//    public static final String ROSTER_HEIGHT = "height";
//    public static final String ROSTER_WEIGHT = "weight";
//    public static final String ROSTER_COLLEGE = "college";
//    public static final String ROSTER_SALARY = "salary";

    private static final String TAG = "qdrjDBHelper";

    private SQLiteDatabase myDataBase;

    private final Context myContext;

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     *
     * @param context
     */


    public qdrjDBHelper(Context context) {

        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    public SQLiteDatabase getDatabase() {
        return myDataBase;
    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     */
    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if (dbExist) {
            //do nothing - database already exist
            Log.v(TAG, "DB already exists");

        } else {

            //By calling this method an empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                Log.e(TAG, e.getMessage());

            }
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {

        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DB_NAME;
            if (!new File(myPath).exists()) return false;

            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

        } catch (SQLiteException e) {

            //database does't exist yet.

        }

        if (checkDB != null) {

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     */
    private void copyDataBase() throws IOException {

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        Log.v(TAG, "copyDataBase: from " + DB_NAME + ", to: " + outFileName);

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {

        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        Log.v("DBHelper", myDataBase.getPath());
    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void copyDBForDebugging() throws IOException {
        FileInputStream is = new FileInputStream(new File(DB_PATH + DB_NAME));
        OutputStream os = new FileOutputStream(new File("/mnt/sdcard/new3qdjr.db"));
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = is.read(buffer)) > 0)
            os.write(buffer, 0, bytesRead);

        is.close();
        os.close();

        Log.v(TAG, "copyDBForDebugging done");
    }


    // Add your public helper methods to access and get content from the database.
    // You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
    // to you to create adapters for your views.



    public Cursor fetchMenu(String category) {
        return myDataBase.rawQuery("select name, ename, price, info, image from "+category, null);
    }

    public Cursor fetchDish(String category, String dish) {
        return myDataBase.rawQuery("select name, ename, price, info from "+category+ " where name = '"+dish+"'", null);
    }

    public Cursor fetchAvlPromo() {
        return myDataBase.rawQuery("select title, detail, issue_date, exp_date, image from promotion where exp_date > datetime(CURRENT_TIMESTAMP,'localtime') order by issue_date", null);
    }

    public Cursor fetchExpPromo() {
        return myDataBase.rawQuery("select title, detail, issue_date, exp_date, image from promotion where exp_date < datetime(CURRENT_TIMESTAMP,'localtime') order by issue_date", null);
    }

    public void insertPro(String title, String issue_date, String exp_date, String detail, int type, String image, String others) {
        Log.v("TAG", detail);

        myDataBase.execSQL("insert into promotion(ID, title, issue_date, exp_date, detail, type, image, others) values (null,'"+title+"','"+issue_date+"', '"+exp_date+"', '"+detail+"','"+type+"','"+image+"','"+others+"')");
    }

}
