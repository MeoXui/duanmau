package fpoly.huynkph38086.duanmau.model;

public class PhieuMuon {
    public int maPM;
    public String maTT;
    public int maTV, maSach, traSach, tienThue, ngayMuon;

    public PhieuMuon(int maPM, String maTT, int maTV, int maSach, int traSach, int tienThue, int ngayMuon) {
        this.maPM = maPM;
        this.maTT = maTT;
        this.maTV = maTV;
        this.maSach = maSach;
        this.traSach = traSach;
        this.tienThue = tienThue;
        this.ngayMuon = ngayMuon;
    }
}
