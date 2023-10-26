package fpoly.huynkph38086.duanmau.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import fpoly.huynkph38086.duanmau.R;
import fpoly.huynkph38086.duanmau.fragment.ThanhVienFragment;
import fpoly.huynkph38086.duanmau.model.ThanhVien;

public class ThanhVienAdapter extends ArrayAdapter<ThanhVien> {
    Context context;
    ThanhVienFragment fragment;
    List<ThanhVien> list;
    TextView tvMa, tvTen, tvNamSinh;
    ImageView imgDel;
    public ThanhVienAdapter(@NonNull Context context, ThanhVienFragment fragment, @NonNull List<ThanhVien> list) {
        super(context, 0, list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View v, @NonNull ViewGroup parent) {
        if(v == null){
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.thanh_vien_item, parent);
        }
        ThanhVien thanhVien = list.get(position);
        if(thanhVien!=null){
            tvMa = v.findViewById(R.id.tvMaTV);
            String s = "Mã thành viên: " + thanhVien.maTV;
            tvMa.setText(s);
            tvTen = v.findViewById(R.id.tvTenTV);
            s = "Tên thành viên: " + thanhVien.tenTV;
            tvTen.setText(s);
            tvNamSinh = v.findViewById(R.id.tvNamSinh);
            s = "Năm sinh: " + thanhVien.namSinh;
            tvNamSinh .setText(s);
            imgDel = v.findViewById(R.id.imgDelete);
            imgDel.setOnClickListener(view -> fragment.xoa(String.valueOf(thanhVien.maTV)));
        }

        return v;
    }
}
