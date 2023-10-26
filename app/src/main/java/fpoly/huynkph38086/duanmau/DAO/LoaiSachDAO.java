package fpoly.huynkph38086.duanmau.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import fpoly.huynkph38086.duanmau.model.LoaiSach;

public class LoaiSachDAO extends DAO{
    public LoaiSachDAO(Context context) {
        super(context, "LoaiSach", "maLoai");
    }

    void putValues(@NonNull LoaiSach loai){
        values = new ContentValues();
        values.put("temLoai", loai.tenLoai);
    }

    public long insert(@NonNull LoaiSach loai) {
        putValues(loai);
        return insert();
    }

    public int update(@NonNull LoaiSach loai) {
        putValues(loai);
        return update(String.valueOf(loai.maLoai));
    }

    public List<LoaiSach> getAll() {
        return getData(saf);
    }

    public LoaiSach getID(String ID) {
        List<LoaiSach> list = getData(saf_w, ID);
        return list.get(0);
    }

    public List<LoaiSach> getData(String sql, String...selectionArgs) {
        List<LoaiSach> list = new ArrayList<>();
        @SuppressLint("Recycle")
        Cursor cursor = database.rawQuery(sql, selectionArgs);
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            while (cursor.moveToNext())
                list.add(new LoaiSach(cursor.getInt(0), cursor.getString(1)));
        } else Toast.makeText(context, "Dữ liệu thể loại sách trống", Toast.LENGTH_SHORT).show();
        return list;
    }
}
