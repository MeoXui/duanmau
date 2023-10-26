package fpoly.huynkph38086.duanmau.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import fpoly.huynkph38086.duanmau.model.PhieuMuon;

public class PhieuMuonDAO extends DAO{
    public PhieuMuonDAO(Context context) {
        super(context, "PhieuMuon", "maPM");
    }

    void putValues(@NonNull PhieuMuon pm){
        values = new ContentValues();
        values.put("maTT", pm.maTT);
        values.put("maTV", pm.maTV);
        values.put("maSach", pm.maSach);
        values.put("traSach", pm.traSach);
        values.put("tienThue", pm.tienThue);
        values.put("ngayMuon", pm.ngayMuon);
    }

    public long insert(@NonNull PhieuMuon pm) {
        putValues(pm);
        return insert();
    }

    public int update(@NonNull PhieuMuon pm) {
        putValues(pm);
        return update(String.valueOf(pm.maPM));
    }

    public List<PhieuMuon> getAll() {
        return getData(saf);
    }

    public PhieuMuon getID(String ID) {
        List<PhieuMuon> list = getData(saf_w, ID);
        return list.get(0);
    }

    public List<PhieuMuon> getData(String sql, String... selectionArgs) {
        List<PhieuMuon> list = new ArrayList<>();
        @SuppressLint("Recycle")
        Cursor cursor = database.rawQuery(sql, selectionArgs);
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            while (cursor.moveToNext())
                list.add(new PhieuMuon(cursor.getInt(0), cursor.getString(1),
                        cursor.getInt(2), cursor.getInt(3), cursor.getInt(4),
                        cursor.getInt(5), cursor.getInt(6)));
        } else Toast.makeText(context, "Dữ liệu thành viên trống", Toast.LENGTH_SHORT).show();
        return list;
    }
}
