package fpoly.huynkph38086.duanmau.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import fpoly.huynkph38086.duanmau.model.ThuThu;

public class ThuThuDAO extends DAO{
    public ThuThuDAO(Context context) {
        super(context, "ThuThu", "maTT");
    }

    void putValues(@NonNull ThuThu tt){
        values = new ContentValues();
        values.put("temTT", tt.tenTT);
        values.put("matKhau", tt.matKhau);
    }

    public long insert(@NonNull ThuThu tt) {
        putValues(tt);
        values.put("maTT", tt.maTT);
        return insert();
    }

    public int update(@NonNull ThuThu tt) {
        putValues(tt);
        return update(tt.maTT);
    }

    public List<ThuThu> getAll() {
        return getData(saf);
    }

    public ThuThu getID(String ID) {
        List<ThuThu> list = getData(saf_w, ID);
        return list.get(0);
    }

    public List<ThuThu> getData(String sql, String... selectionArgs) {
        List<ThuThu> list = new ArrayList<>();
        @SuppressLint("Recycle")
        Cursor cursor = database.rawQuery(sql, selectionArgs);
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            while (cursor.moveToNext())
                list.add(new ThuThu(cursor.getString(0), cursor.getString(1),
                        cursor.getString(2)));
        } else Toast.makeText(context, "Dữ liệu thủ thư trống", Toast.LENGTH_SHORT).show();
        return list;
    }

    public int checkLogin(String ID, String password){
        String sql = saf_w+" and matKhau=?";
        List<ThuThu> list = getData(sql, ID, password);
        if (list.size()==0) return -1;
        return 1;
    }
}
