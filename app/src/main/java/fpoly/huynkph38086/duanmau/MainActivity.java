package fpoly.huynkph38086.duanmau;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import fpoly.huynkph38086.duanmau.DAO.ThuThuDAO;
import fpoly.huynkph38086.duanmau.fragment.AddUserFragment;
import fpoly.huynkph38086.duanmau.fragment.ChangePassFragment;
import fpoly.huynkph38086.duanmau.fragment.DoanhThuFragment;
import fpoly.huynkph38086.duanmau.fragment.LoaiSachFragment;
import fpoly.huynkph38086.duanmau.fragment.PhieuMuonFragment;
import fpoly.huynkph38086.duanmau.fragment.SachFragment;
import fpoly.huynkph38086.duanmau.fragment.ThanhVienFragment;
import fpoly.huynkph38086.duanmau.fragment.TopFragment;
import fpoly.huynkph38086.duanmau.model.ThuThu;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawer;
    Toolbar toolbar;
    View mHaederView;
    TextView edUser;
    ThuThuDAO ttDAO;

    @SuppressLint({"SetTextI18n", "NonConstantResourceId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.menu);
        ab.setDisplayHomeAsUpEnabled(true);

        FragmentManager manager = getSupportFragmentManager();
        PhieuMuonFragment pmFragment = new PhieuMuonFragment();
        manager.beginTransaction()
                .replace(R.id.content, pmFragment)
                .commit();

        NavigationView nav = findViewById(R.id.nav);
        mHaederView = nav.getHeaderView(0);
        edUser = mHaederView.findViewById(R.id.tv_User);
        Intent intent = getIntent();
        String user = intent.getStringExtra("user");
        ttDAO = new ThuThuDAO(this);
        ThuThu tt = ttDAO.getID(user);
        String username = tt.tenTT;
        edUser.setText("Welcome " + username + "!");

        if (user != null && user.equalsIgnoreCase("admin"))
            nav.getMenu().findItem(R.id.sub_addUser).setVisible(true);

        nav.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.nav_phieuMuon:
                    setTitle(R.string.quan_ly_phieu_muon);
                    PhieuMuonFragment phieuMuonFragment = new PhieuMuonFragment();
                    manager.beginTransaction().replace(R.id.content, phieuMuonFragment).commit();
                    break;

                case R.id.nav_loaiSach:
                    setTitle(R.string.quan_ly_loai_sach);
                    LoaiSachFragment loaiSachFragment = new LoaiSachFragment();
                    manager.beginTransaction().replace(R.id.content, loaiSachFragment).commit();
                    break;

                case R.id.nav_sach:
                    setTitle(R.string.quan_ly_sach);
                    SachFragment sachFragment = new SachFragment();
                    manager.beginTransaction().replace(R.id.content, sachFragment).commit();
                    break;

                case R.id.nav_thanhVien:
                    setTitle(R.string.quan_ly_thanh_vien);
                    ThanhVienFragment thanhVienFragment = new ThanhVienFragment();
                    manager.beginTransaction().replace(R.id.content, thanhVienFragment).commit();
                    break;

                case R.id.sub_top:
                    setTitle(R.string.top_10_sach_muon_nhieu_nhat);
                    TopFragment topFragment = new TopFragment();
                    manager.beginTransaction().replace(R.id.content, topFragment).commit();
                    break;

                case R.id.sub_doanhThu:
                    setTitle(R.string.doanh_thu);
                    DoanhThuFragment doanhThuFragment = new DoanhThuFragment();
                    manager.beginTransaction().replace(R.id.content, doanhThuFragment).commit();
                    break;

                case R.id.sub_addUser:
                    setTitle(R.string.them_nguoi_dung);
                    AddUserFragment addUserFragment = new AddUserFragment();
                    manager.beginTransaction().replace(R.id.content, addUserFragment).commit();
                    break;

                case R.id.sub_pass:
                    setTitle(R.string.doi_mat_khau);
                    ChangePassFragment changePassFragment = new ChangePassFragment();
                    manager.beginTransaction().replace(R.id.content, changePassFragment).commit();
                    break;

                case R.id.sub_logout:
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                    break;
                default:
                    drawer.openDrawer(GravityCompat.START);
            }

            drawer.closeDrawers();

            return false;
        });
    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        if(id == andriod.R.id.home)
//            drawer.openDrawer(GravityCompat.START);
//        return super.onOptionsItemSelected(item);
//    }
}