package fpoly.huynkph38086.duanmau.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import fpoly.huynkph38086.duanmau.DAO.ThuThuDAO;
import fpoly.huynkph38086.duanmau.R;
import fpoly.huynkph38086.duanmau.model.ThuThu;

public class ChangePassFragment extends Fragment {

    EditText edtOldPass, edtNewPass, edtReNewPass;
    Button btnSvae, btnCancel;
    ThuThuDAO ttDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chage_pass, container, false);
        edtOldPass = v.findViewById(R.id.edtOldPWOfChagePass);
        edtNewPass = v.findViewById(R.id.edtNewPWOfChagePass);
        edtReNewPass = v.findViewById(R.id.edtReNewPWOfChagePass);
        btnSvae = v.findViewById(R.id.btnSaveOfChagePass);
        btnCancel = v.findViewById(R.id.btnCancelOfChagePass);

        ttDao = new ThuThuDAO(getActivity());

        btnCancel.setOnClickListener(view -> {
            edtOldPass.setText("");
            edtNewPass.setText("");
            edtReNewPass.setText("");
        });

        btnSvae.setOnClickListener(view -> {
            SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", MODE_PRIVATE);
            String user = pref.getString("USERNAME", "");
            if (validate()>0) {
                ThuThu tt = ttDao.getID(user);
                tt.matKhau = edtNewPass.getText().toString();
                if(ttDao.update(tt)>0){
                    Toast.makeText(getActivity(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    edtOldPass.setText("");
                    edtNewPass.setText("");
                    edtReNewPass.setText("");
                } else Toast.makeText(getActivity(), "Thay dổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }

    private int validate() {
        if(edtOldPass.length()==0 || edtNewPass.length()==0 || edtReNewPass.length()==0){
            Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return -1;
        }
        SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String oldPass = pref.getString("PASSWORD", ""),
                newPass = edtNewPass.getText().toString(),
                reNewPass = edtReNewPass.getText().toString();
        if (!oldPass.equals(edtOldPass.getText().toString())) {
            Toast.makeText(getContext(), "Mật khẩu cũ không đúng", Toast.LENGTH_SHORT).show();
            return -1;
        }
        if (!newPass.equals(reNewPass)){
            Toast.makeText(getContext(), "Mật khẩu nhập lại chưa chính xác", Toast.LENGTH_SHORT).show();
            return -1;
        }
        return 1;
    }
}
