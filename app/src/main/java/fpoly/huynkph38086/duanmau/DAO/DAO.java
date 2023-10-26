package fpoly.huynkph38086.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import fpoly.huynkph38086.duanmau.OpenHelper;

abstract class DAO {
    final Context context;
    final OpenHelper helper;
    final SQLiteDatabase database;
    ContentValues values;
    String table, whereClause, saf, saf_w;

    public DAO(Context context, String table, String primaryKey) {
        this.context = context;
        this.helper = new OpenHelper(context);
        database = helper.getWritableDatabase();
        this.table = table;
        whereClause = primaryKey + "=?";
        saf = "Select * from "+table;
        saf_w = saf + " where " + whereClause;
    }

    public long insert() {
        return database.insert(table, null,values);
    }

    public int delete(String ID) {
        return database.delete(table, whereClause, new String[]{ID});
    }

    public int update(String whereArgs) {
        return database.update(table, values, whereClause, new String[]{whereArgs});
    }
}
