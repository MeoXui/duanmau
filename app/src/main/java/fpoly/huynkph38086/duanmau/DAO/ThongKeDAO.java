package fpoly.huynkph38086.duanmau.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import fpoly.huynkph38086.duanmau.model.SachTop;

public class ThongKeDAO extends DAO{
    SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
    public ThongKeDAO(Context context) {
        super(context, null, null);
    }

    public List<SachTop> getTop10(){
        String sql = "Select maSach, cuont(maSach) as soLuong from PhieuMuon" +
                " group by maSach order by soLuong desc limit 10";
        List<SachTop> list = new ArrayList<>();
        SachDAO sachDAO = new SachDAO(context);
        @SuppressLint("Recycle")
        Cursor cursor = database.rawQuery(sql, null);
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            while (cursor.moveToNext())
                list.add( new SachTop(
                        sachDAO.getID(cursor.getString(0)),
                        cursor.getInt(1)
                ));
        }
        return list;
    }

    public int getDoanhThu(String tuNgay, String denNgay){
        String sql = "Select sum(tienThue) from PhieuMuon where ngayMuon between ? and ?";
        int doanhThu = 0;
        @SuppressLint("Recycle")
        Cursor cursor = database.rawQuery(sql, new String[]{tuNgay, denNgay});
        try{
            if(cursor != null && cursor.getCount() > 0){
                cursor.moveToFirst();
                doanhThu = cursor.getInt(0);
            }
        } catch (Exception ignored) {}
        return doanhThu;
    }
}
