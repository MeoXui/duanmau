package fpoly.huynkph38086.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import fpoly.huynkph38086.duanmau.DAO.ThuThuDAO;

public class LoginActivity extends AppCompatActivity {
    Admin ad = new Admin();
    EditText edtUN, edtPW;
    String sUN, sPW;
    CheckBox chkRe;
    Button btnLogin, btnCancel;
    ThuThuDAO daoTT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle(R.string.dang_nhap);
        edtUN = findViewById(R.id.edtUN);
        edtPW = findViewById(R.id.edtPW);
        chkRe = findViewById(R.id.chkRe);
        btnLogin = findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnCancel);

        daoTT = new ThuThuDAO(this);

        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        edtUN.setText(sharedPreferences.getString("USERNAME", ""));
        edtPW.setText(sharedPreferences.getString("PASSWORD", ""));
        chkRe.setChecked(sharedPreferences.getBoolean("REMEMBER", false));

        btnLogin.setOnClickListener(view -> checkLogin());
        btnCancel.setOnClickListener(view -> finish());
    }

    private void checkLogin() {
        sUN = edtUN.getText().toString();
        sPW = edtPW.getText().toString();
        if (sUN.isEmpty()) Toast.makeText(this, "Vui vòng nhập Tên đăng nhập",
                Toast.LENGTH_SHORT).show();
        else if (sPW.isEmpty()) Toast.makeText(this, "Vui vòng nhập Mật khẩu",
                Toast.LENGTH_SHORT).show();
        else if (daoTT.checkLogin(sUN,sPW)>0 ||
                (sUN.equals(ad.ADMINNAME) && sPW.equals(ad.ADMINPASSWORD))){
            Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            remember(sUN, sPW, chkRe.isChecked());
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            intent.putExtra("user", sUN);
            startActivity(intent);
        } else Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không đúng",
                Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("ApplySharedPref")
    private void remember(String username, String password, boolean status) {
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (status) {
            editor.putString("USERNAME", username);
            editor.putString("PASSWORD", password);
        } else editor.clear();
        editor.putBoolean("REMEMBER", status);
        editor.commit();
    }

    private static class Admin {
        private final String ADMINNAME = "admin",
                ADMINPASSWORD = "admin";
    }
}