import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "QuestionAndAnswer";

    // Contacts table name
    private static final String TABLE_QnA = "QnA";

    // Shops Table Columns names
    private static final String KEY_ID = "id";

    private static final String KEY_QUESTION = "question";

    private static final String KEY_ANSWER = "answer";

    public DBHandler(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_QnA + "("
                + KEY_ID + "INTEGER PRIMARY KEY," + KEY_QUESTION + "TEXT,"
                + KEY_ANSWER + "TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QnA);

    // Creating tables again
        onCreate(db);

    }

    // Adding new QnA
    public void addQna(QnA qna) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUESTION, qna.getQuestion()); // Shop Name
        values.put(KEY_ANSWER, qna.getAnswer()); // Shop Phone Number

    // Inserting Row
        db.insert(TABLE_QnA, null, values);
        db.close(); // Closing database connection

    }

    // Getting one QnA
    public QnA getQna(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_QnA, new String[] { KEY_ID,
                        KEY_QUESTION, KEY_ANSWER }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        QnA contact = new QnA(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));

        // return QnA
        return contact;

    }

    // Getting All QnAs
    public List<QnA> getAllQnas() {

        List<QnA> QnAList = new ArrayList<QnA>();

    // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_QnA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

    // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                QnA qnA = new QnA();
                qnA.setId(Integer.parseInt(cursor.getString(0)));
                qnA.setQuestion(cursor.getString(1));
                qnA.setAnswer(cursor.getString(2));

     // Adding contact to list
                QnAList.add(qnA);

            } while (cursor.moveToNext());
        }

    // return contact list
        return QnAList;

    }

    // Getting QnAs Count
    public int getQnasCount() {

        String countQuery = "SELECT * FROM " + TABLE_QnA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

    // return count
        return cursor.getCount();

    }

    // Updating a QnA
    public int updateQna(QnA qnA) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUESTION, qnA.getQuestion());
        values.put(KEY_ANSWER, qnA.getAnswer());

    // updating row
        return db.update(TABLE_QnA, values, KEY_ID + " = ?",
                new String[]{String.valueOf(qnA.getId())});

    }

    // Deleting a QnA
    public void deleteQna(QnA qnA) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_QnA, KEY_ID + " = ?",
                new String[] { String.valueOf(QnA.getId()) });
        db.close();

    }


}

