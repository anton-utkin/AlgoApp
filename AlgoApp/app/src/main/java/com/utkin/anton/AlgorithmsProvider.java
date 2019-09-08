package com.utkin.anton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;

import android.database.Cursor;
import android.database.SQLException;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class AlgorithmsProvider extends ContentProvider {
    static final String PROVIDER_NAME = "com.utkin.anton.AlgorithmsProvider";
    static final String URL = "content://" + PROVIDER_NAME + "/data_structures";
    public static final Uri CONTENT_URI = Uri.parse(URL);

    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String IS_KNOWN = "is_known";
    public static final String CODE = "code";

    private static HashMap<String, String> ALGORITHMS_PROJECTION_MAP;

    static final int DATA_STRUCTURES = 1;
    static final int DATA_STRUCTURE_ID = 2;

    static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "data_structures", DATA_STRUCTURES);
        uriMatcher.addURI(PROVIDER_NAME, "data_structures/#", DATA_STRUCTURE_ID);
    }

    /**
     * Database specific constant declarations
     */

    private SQLiteDatabase db;
    static final String DATABASE_NAME = "Algorithms";
    static final String DATA_STRUCTURES_TABLE_NAME = "data_structures";
    static final int DATABASE_VERSION = 11;

    /**
     * Helper class that actually creates and manages
     * the provider's underlying data repository.
     */

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            mContext = context;
        }

        private Context mContext;

        private String readSqlScript(){
            StringBuffer result = new StringBuffer();
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(
                        new InputStreamReader(mContext.getAssets().open("script.sql")));
                String mLine;
                while ((mLine = reader.readLine()) != null) {
                    result.append(mLine + System.lineSeparator());
                }
            } catch (IOException e) {
                //log the exception
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        //log the exception
                    }
                }
                return result.toString();
            }
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i("TAG", "DatabaseHelper onCreate");
            String script = readSqlScript();

            try {
                for (String sqlStatement : script.split(System.lineSeparator())) {
                    Log.i("TAG", "sqlStatement=" + sqlStatement);
                    db.execSQL(sqlStatement);
                }
            }catch(SQLException ex) {
                Log.i("TAG", "SQLException " + ex.getMessage());
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.i("TAG", "onUpgrade");
            onCreate(db);
        }

        @Override
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.i("TAG", "onDowngrade");
            onCreate(db);
        }
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        Log.i("TAG", "AlgorithmsProvide onCreate");
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        /**
         * Create a write able database which will trigger its
         * creation if it doesn't already exist.
         */

        db = dbHelper.getWritableDatabase();
        Log.i("TAG", "AlgorithmsProvide onCreate" + db);
        return (db == null)? false:true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        long rowID = db.insert(	DATA_STRUCTURES_TABLE_NAME, "", values);

        /**
         * If record is added successfully
         */
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }

        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public Cursor query(Uri uri, String[] projection,
                        String selection,String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(DATA_STRUCTURES_TABLE_NAME);

        switch (uriMatcher.match(uri)) {
            case DATA_STRUCTURES:
                qb.setProjectionMap(ALGORITHMS_PROJECTION_MAP); // does it need?
                break;

            case DATA_STRUCTURE_ID:
                qb.appendWhere( _ID + "=" + uri.getPathSegments().get(1));
                break;

            default:
        }

        if (sortOrder == null || sortOrder == ""){

            sortOrder = NAME;
        }

        Cursor c = qb.query(db,	projection,	selection,
                selectionArgs,null, null, sortOrder);
        /**
         * register to watch a content URI for changes
         */
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)){
            case DATA_STRUCTURES:
                count = db.delete(DATA_STRUCTURES_TABLE_NAME, selection, selectionArgs);
                break;

            case DATA_STRUCTURE_ID:
                String id = uri.getPathSegments().get(1);
                count = db.delete( DATA_STRUCTURES_TABLE_NAME, _ID +  " = " + id +
                                (!TextUtils.isEmpty(selection) ?
                        "AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values,
                      String selection, String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case DATA_STRUCTURES:
                count = db.update(DATA_STRUCTURES_TABLE_NAME, values, selection, selectionArgs);
                break;

            case DATA_STRUCTURE_ID:
                count = db.update(DATA_STRUCTURES_TABLE_NAME, values,
                        _ID + " = " + uri.getPathSegments().get(1) +
                                (!TextUtils.isEmpty(selection) ?
                        "AND (" +selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri );
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){

            case DATA_STRUCTURES:
                return "vnd.android.cursor.dir/vnd.data_structures";

            case DATA_STRUCTURE_ID:
                return "vnd.android.cursor.item/vnd.data_structures";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }
}