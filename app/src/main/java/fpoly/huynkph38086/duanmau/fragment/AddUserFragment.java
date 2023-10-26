package fpoly.huynkph38086.duanmau.fragment;

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

public class AddUserFragment extends Fragment {
    EditText edtUser, edtName, edtPass, edtRePass;
    Button btnSvae, btnCancel;
    ThuThuDAO ttDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_user, container, false);
        edtUser = v.findViewById(R.id.edtUesrOfAddUser);
        edtName = v.findViewById(R.id.edtNameOfAddUser);
        edtPass = v.findViewById(R.id.edtPassOfAddUser);
        edtRePass = v.findViewById(R.id.edtRePassOfAddUser);
        btnSvae = v.findViewById(R.id.btnSaveOfAddUser);
        btnCancel = v.findViewById(R.id.btnCancelOfAddUser);

        ttDao = new ThuThuDAO(getActivity());

        btnCancel.setOnClickListener(view -> {
            edtUser.setText("");
            edtName.setText("");
            edtPass.setText("");
            edtRePass.setText("");
        });

        btnSvae.setOnClickListener(view -> {
            ThuThu tt = new ThuThu(edtUser.getText().toString(),
                    edtName.getText().toString(), edtPass.getText().toString());
            if (validate()>0) {
                if(ttDao.insert(tt)>0){
                    Toast.makeText(getActivity(), "Lưu thành công", Toast.LENGTH_SHORT).show();
                    edtUser.setText("");
                    edtName.setText("");
                    edtPass.setText("");
                    edtRePass.setText("");
                } else Toast.makeText(getActivity(), "Lưu thất bại", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

    private int validate() {
        if(edtUser.length()==0 || edtName.length()==0 || edtPass.length()==0 || edtRePass.length()==0){
            Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return -1;
        }
        String pass = edtPass.getText().toString(),
                rePass = edtRePass.getText().toString();
        if (!pass.equals(rePass)){
            Toast.makeText(getContext(), "Mật khẩu nhập lại chưa chính xác", Toast.LENGTH_SHORT).show();
            return -1;
        }
        return 1;
    }
}
