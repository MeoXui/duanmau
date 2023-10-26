package fpoly.huynkph38086.duanmau;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OpenHelper extends SQLiteOpenHelper {
    public static final String db_NAME = "PNLib";
    static final int db_Version = 1;

    public OpenHelper(Context context) {
        super(context, db_NAME, null, db_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("Create Table LoaiSach(" +
                "maLoai integer primary key autoincrement," +
                "temLoai text not null)");

        db.execSQL("Create Table Sach(" +
                "maSach integer primary key autoincrement," +
                "temSach text not null," +
                "giaThue integer not null," +
                "maLoai integer references LoaiSach(maLoai))");

        db.execSQL("Create Table ThuThu(" +
                "maTT text primary key," +
                "tenTT text not null," +
                "matKhau text not null)");

        db.execSQL("Create Table ThanhVien(" +
                "maTV integer primary key autoincrement," +
                "tenTV text not null," +
                "namSinh integer not null)");

        db.execSQL("Create Table PhieuMuon(" +
                "maPM integer primary key autoincrement," +
                "maTT text references ThuThu(maTT)," +
                "maTV integer references ThanhVien(maTV)," +
                "maSach integer references Sach(maSach)," +
                "traSach integer not null," +
                "tienThue integer not null," +
                "ngayMuon integer not null)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            db.execSQL("Drop Table If Exists LoaiSach");
            db.execSQL("Drop Table If Exists Sach");
            db.execSQL("Drop Table If Exists ThuThu");
            db.execSQL("Drop Table If Exists ThanhVien");
            db.execSQL("Drop Table If Exists PhieuMuon");
            onCreate(db);
        }
    }
}
