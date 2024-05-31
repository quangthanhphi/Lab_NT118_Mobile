package vn.dlu.quanlysinhvien;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;

public class UsersDatabaseAdapter {
    static ArrayList<UserModel> users = new ArrayList<>();
    static final String DATABASE_NAME = "UsersDatabase.db";
    static final String TABLE_NAME = "USERS";
    static final int DATABASE_VERSION = 1;
    // Câu lệnh SQL tạo mới cơ sở dữ liệu.
    static final String DATABASE_CREATE = "create table " + TABLE_NAME + "( ID integer primary key autoincrement,user_name  text,user_phone  text,user_email text); ";
    private static final String TAG = "UsersDatabaseAdapter";

    // Khai báo biên db kiểm SQLiteDatabase để thực thi các phương thức với cơ sở dữ liệu
    public static SQLiteDatabase db;
    // Khai báo đối tượng kiểu Context của ứng dụng sử dụng cơ sở dữ liệu này.
    private final Context context;
    // Database open/upgrade helper
    private static DataBaseHelper dbHelper;

    public UsersDatabaseAdapter(Context _context) {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Phương thức mở Database
    public UsersDatabaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    // Phương thức đóng Database
    public void close() {
        db.close();
    }

    // Phương thức trả về instance của Database
    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    // Phương thức insert bản ghi vào Table
    public String insertEntry(String user_name, String user_phone, String user_email) {

        try {


            ContentValues newValues = new ContentValues();
            // Gán dữ liệu cho mỗi cột.
            newValues.put("user_name", user_name);
            newValues.put("user_phone", user_phone);
            newValues.put("user_email", user_email);

            // Insert hàng dữ liệu vào table
            db = dbHelper.getWritableDatabase();
            long result = db.insert(TABLE_NAME, null, newValues);
            Log.i("Row Insert Result ", String.valueOf(result));
            Utils.showToast(this.context.getApplicationContext(), "User Info Saved! Total Row Count is " + getRowCount());
            db.close();

        } catch (Exception ex) {
        }
        return "ok";
    }

    // Phương thức lấy tất cả các hàng được lưu trong Table
    @SuppressLint("Range")
    public static ArrayList<UserModel> getRows() throws JSONException {

        users.clear();
        UserModel user;
        db = dbHelper.getReadableDatabase();
        Cursor projCursor = db.query(TABLE_NAME, null, null, null, null, null, null, null);
        while (projCursor.moveToNext()) {

            user = new UserModel();
            user.setID(projCursor.getString(projCursor.getColumnIndex("ID")));
            user.setUsername(projCursor.getString(projCursor.getColumnIndex("user_name")));
            user.setUserphone(projCursor.getString(projCursor.getColumnIndex("user_phone")));
            user.setUseremail(projCursor.getString(projCursor.getColumnIndex("user_email")));
            users.add(user);
        }
        projCursor.close();
        return users;
    }

    // phương thức xoá bản ghi trong Table sủw dụng khoá chính là ID
    public int deleteEntry(String ID) {
        String where = "ID=?";
        int numberOFEntriesDeleted = db.delete(TABLE_NAME, where, new String[]{ID});
        Toast.makeText(this.context.getApplicationContext(), "Number fo Entry Deleted Successfully : " + numberOFEntriesDeleted, Toast.LENGTH_SHORT).show();
        return numberOFEntriesDeleted;
    }

    // Phương thức đếm tổng số bản ghi trong Table
    public int getRowCount() {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        Toast.makeText(this.context.getApplicationContext(), "Row Count is " + cursor.getCount(), Toast.LENGTH_LONG).show();
        db.close();
        return cursor.getCount();
    }

    // Phương thức xoá tất cả các bản ghi trong bảng Table
    public void truncateTable() {
        db = dbHelper.getReadableDatabase();
        db.delete(TABLE_NAME, "1", null);
        db.close();
        Toast.makeText(context.getApplicationContext(), "Table Data Truncated!", Toast.LENGTH_LONG).show();
    }

    // Phương thức Update các bản ghi trong Table
    public void updateEntry(String ID, String Username, String Userphone, String Useremail) {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("user_name", Username);
        updatedValues.put("user_phone", Userphone);
        updatedValues.put("user_email", Useremail);
        String where = "ID = ?";
        db = dbHelper.getReadableDatabase();
        db.update(TABLE_NAME, updatedValues, where, new String[]{ID});
        db.close();
        Toast.makeText(context.getApplicationContext(), "Row Updated!", Toast.LENGTH_LONG).show();
    }
}
