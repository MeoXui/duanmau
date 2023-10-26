package fpoly.huynkph38086.duanmau.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import fpoly.huynkph38086.duanmau.DAO.ThanhVienDAO;
import fpoly.huynkph38086.duanmau.R;
import fpoly.huynkph38086.duanmau.adapter.ThanhVienAdapter;
import fpoly.huynkph38086.duanmau.model.ThanhVien;

public class ThanhVienFragment extends Fragment {
    ListView listView;
    List<ThanhVien> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edtMa, edtTen, edtNamSinh;
    Button btnSave, btnCancel;
    ThanhVienDAO tvDao;
    ThanhVienAdapter adater;
    ThanhVien thanhVien;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_thanh_vien, container, false);
        listView = v.findViewById(R.id.lvThanhVien);
        fab = v.findViewById(R.id.fab);
        tvDao = new ThanhVienDAO(getActivity());

        lamMoi();

        fab.setOnClickListener(view -> openDialog(getActivity(), 0));

        listView.setOnItemLongClickListener((adapterView, view, i, l) -> {
            thanhVien = list.get(i);
            openDialog(getActivity(), 1);
            return false;
        });

        return v;
    }

    private void openDialog(Context context, int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.thanh_vien_dialog);

        edtMa = dialog.findViewById(R.id.edtMaTV);
        edtTen = dialog.findViewById(R.id.edtTenTV);
        edtNamSinh = dialog.findViewById(R.id.edtNamSinh);
        btnSave = dialog.findViewById(R.id.btnSaveTV);
        btnCancel = dialog.findViewById(R.id.btnCancelTV);
        edtMa.setEnabled(false);

        if(type!=0){
            edtMa.setText(thanhVien.maTV);
            edtTen.setText(thanhVien.tenTV);
            edtNamSinh.setText(thanhVien.namSinh);
        }

        btnCancel.setOnClickListener(view -> dialog.dismiss());

        btnSave.setOnClickListener(view -> {
            thanhVien = new ThanhVien(
                    Integer.parseInt(edtMa.getText().toString()),
                    edtTen.getText().toString(),
                    Integer.parseInt(edtNamSinh.getText().toString()));
            if(validate()>0){
                if(type==0){
                    if(tvDao.insert(thanhVien)>0)
                        Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    else Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                } else {
                    if(tvDao.update(thanhVien)>0)
                        Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    else Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
            lamMoi();
            dialog.dismiss();
        });

        dialog.show();
    }

    private int validate() {
        if(edtTen.getText().length()>0 && edtNamSinh.getText().length()>0)
            return 1;
        Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        return -1;
    }

    private void lamMoi() {
        list = tvDao.getAll();
        if(getActivity()!=null)
            adater = new ThanhVienAdapter(getActivity(), this, list);
        listView.setAdapter(adater);
    }

    public void xoa(String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Có",(dialog, i) -> {
            tvDao.delete(String.valueOf(id));
            lamMoi();
            dialog.cancel();
        });

        builder.setNegativeButton("Không",(dialog, i) -> dialog.cancel());
        builder.create();
        builder.show();
    }
}
