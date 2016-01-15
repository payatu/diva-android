/*
 * DIVA Android - Damn Insecure and Vulnerable App for Android
 *
 * Copyright 2016 © Payatu
 * Author: Aseem Jakhar aseem[at]payatu[dot]com
 * Websites: www.payatu.com  www.nullcon.net  www.hardwear.io www.null.co.in
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING,
 * BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 */
package jakhar.aseem.diva;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.content.Context;
import android.text.TextUtils;

// Implementation reference from
// - http://examples.javacodegeeks.com/android/core/content/contentprovider/android-content-provider-example/

public class NotesProvider extends ContentProvider {

    // DB stuff
    SQLiteDatabase mDB;
    static final String DBNAME  = "divanotes.db";
    static final int DBVERSION  = 1;
    static final String TABLE   = "notes";
    static final String C_ID    = "_id";
    static final String C_TITLE = "title";
    static final String C_NOTE  = "note";

    static final String CREATE_TBL_QRY = " CREATE TABLE " + TABLE +
            " (" + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            C_TITLE + " TEXT NOT NULL, " +
            C_NOTE + " TEXT NOT NULL);";

    static final String DROP_TBL_QRY = "DROP TABLE IF EXISTS " +  TABLE;

    static final int PATH_TABLE = 1;
    static final int PATH_ID    = 2;

    static final String AUTHORITY = "jakhar.aseem.diva.provider.notesprovider";
    static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLE);

    static final UriMatcher urimatcher;
    static {
        urimatcher = new UriMatcher(UriMatcher.NO_MATCH);
        urimatcher.addURI(AUTHORITY, TABLE, PATH_TABLE);
        urimatcher.addURI(AUTHORITY, TABLE + "/#", PATH_ID);
    }

    private static class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, DBNAME, null, DBVERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DROP_TBL_QRY);
            db.execSQL(CREATE_TBL_QRY);
            db.execSQL("INSERT INTO " + TABLE + "(" + C_TITLE + "," + C_NOTE + ") VALUES ('office', '10 Meetings. 5 Calls. Lunch with CEO');");
            db.execSQL("INSERT INTO " + TABLE + "(" + C_TITLE + "," + C_NOTE + ") VALUES ('home', 'Buy toys for baby, Order dinner');");
            db.execSQL("INSERT INTO " + TABLE + "(" + C_TITLE + "," + C_NOTE + ") VALUES ('holiday', 'Either Goa or Amsterdam');");
            db.execSQL("INSERT INTO " + TABLE + "(" + C_TITLE + "," + C_NOTE + ") VALUES ('Expense', 'Spent too much on home theater');");
            db.execSQL("INSERT INTO " + TABLE + "(" + C_TITLE + "," + C_NOTE + ") VALUES ('Exercise', 'Alternate days running');");
            db.execSQL("INSERT INTO " + TABLE + "(" + C_TITLE + "," + C_NOTE + ") VALUES ('Weekend', 'b333333333333r');");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onCreate(db);
        }
     }

    public NotesProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        switch (urimatcher.match(uri)) {
            case PATH_TABLE:
                // delete all records
                count = mDB.delete(TABLE, selection, selectionArgs);
                break;
            case PATH_ID:
                // delete a single record
                String id = uri.getLastPathSegment();
                count = mDB.delete( TABLE, C_ID +  " = " + id +
                (!TextUtils.isEmpty(selection) ? " AND (" +
                selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Divanotes(delete): Unsupported URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        switch (urimatcher.match(uri)){
            // Get all notes
            case PATH_TABLE:
                return "vnd.android.cursor.dir/vnd.jakhar.notes";
            // Get a single note
            case PATH_ID:
                return "vnd.android.cursor.item/vnd.jakhar.notes";
            default:
                throw new IllegalArgumentException("Divanotes: Unsupported URI: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long row = mDB.insert(TABLE, "", values);
        if(row > 0) {
            // Record added
            Uri newUri = ContentUris.withAppendedId(CONTENT_URI, row);
            getContext().getContentResolver().notifyChange(newUri, null);
            return newUri;
        }
        throw new SQLException("Divanotes: Fail to add a new record into " + uri);
    }

    @Override
    public boolean onCreate() {
        DBHelper dbHelper = new DBHelper(getContext());
        mDB = dbHelper.getWritableDatabase();
        if (mDB == null) {
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        // The table to query
        queryBuilder.setTables(TABLE);
        switch (urimatcher.match(uri)) {
            // maps all database column names
            case PATH_TABLE:
                break;
            case PATH_ID:
                queryBuilder.appendWhere( C_ID + "=" + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Divanotes(query): Unknown URI " + uri);
        }
        if (sortOrder == null || sortOrder == "") {
            // If no sorting specified by called then sort on title by default
            sortOrder = C_TITLE;
        }
        Cursor cursor = queryBuilder.query(mDB, projection, selection,
                                           selectionArgs, null, null, sortOrder);

         // register to watch a content URI for changes and notify the content resolver
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int count = 0;

        switch (urimatcher.match(uri)) {
            case PATH_TABLE:
                count = mDB.update(TABLE, values, selection, selectionArgs);
                break;
            case PATH_ID:
                count = mDB.update(TABLE, values, C_ID +
                        " = " + uri.getLastPathSegment() +
                (!TextUtils.isEmpty(selection) ? " AND (" +
                selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Divanotes(update): Unsupported URI " + uri );
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}
