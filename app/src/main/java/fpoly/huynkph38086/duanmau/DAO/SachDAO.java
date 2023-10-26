package fpoly.huynkph38086.duanmau.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import fpoly.huynkph38086.duanmau.model.Sach;

public class SachDAO extends DAO{
    public SachDAO(Context context) {
        super(context, "Sach", "maSach");
    }

    void putValues(@NonNull Sach sach){
        values = new ContentValues();
        values.put("temSach", sach.tenSach);
        values.put("giaThue", sach.giaThue);
        values.put("maLoai", sach.maLoai);
    }

    public long insert(@NonNull Sach sach) {
        putValues(sach);
        return insert();
    }

    public int update(@NonNull Sach sach) {
        putValues(sach);
        return update(String.valueOf(sach.maSach));
    }

    public List<Sach> getAll() {
        return getData(saf);
    }

    public Sach getID(String ID) {
        List<Sach> list = getData(saf_w, ID);
        return list.get(0);
    }

    public List<Sach> getData(String sql, String... selectionArgs) {
        List<Sach> list = new ArrayList<>();
        @SuppressLint("Recycle")
        Cursor cursor = database.rawQuery(sql, selectionArgs);
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            while (cursor.moveToNext())
                list.add(new Sach(cursor.getInt(0), cursor.getString(1),
                        cursor.getInt(2), cursor.getInt(3)));
        } else Toast.makeText(context, "Dữ liệu sách trống", Toast.LENGTH_SHORT).show();
        return list;
    }
}
