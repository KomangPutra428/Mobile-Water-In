package com.tvip.waterin_finaltujuan.list_item;

public class comodel {
    String js_window;
    String js_date;
    String js_co;
    String material_nama;
    String varian_label;
    String varian_muatan;
    String transporter_kode;
    String pabrik_nama;
    String depo_pt;
    String js_nopol_pool;
    String armada_driver;
    String js_tujuan_depo;
    String js_tujuan_co;
    String js_type;
    String mk_depo_tujuan;
    String mk_so_tujuan;
    String js_tgl;
    String js_jam;
    String js_tujuan_divert;

    public comodel(String js_window, String js_date, String js_co, String material_nama, String varian_label,
                   String varian_muatan, String transporter_kode, String pabrik_nama, String depo_pt, String js_nopol_pool,
                   String armada_driver, String js_tujuan_depo, String js_tujuan_co, String js_type, String mk_depo_tujuan,
                   String mk_so_tujuan, String js_tgl, String js_jam, String js_tujuan_divert) {
        this.js_window = js_window;
        this.js_date = js_date;
        this.js_co = js_co;
        this.material_nama = material_nama;
        this.varian_label = varian_label;
        this.varian_muatan = varian_muatan;
        this.transporter_kode = transporter_kode;
        this.pabrik_nama = pabrik_nama;
        this.depo_pt = depo_pt;
        this.js_nopol_pool = js_nopol_pool;
        this.armada_driver = armada_driver;
        this.js_tujuan_depo = js_tujuan_depo;
        this.js_tujuan_co = js_tujuan_co;
        this.js_type = js_type;
        this.mk_depo_tujuan = mk_depo_tujuan;
        this.mk_so_tujuan = mk_so_tujuan;
        this.js_tgl = js_tgl;
        this.js_jam = js_jam;
        this.js_tujuan_divert = js_tujuan_divert;
    }

    public String getJs_window() { return js_window; }
    public String getJs_date() { return js_date; }
    public String getJs_co() { return js_co; }
    public String getMaterial_nama() { return material_nama; }
    public String getVarian_label() { return varian_label; }

    public String getVarian_muatan() { return varian_muatan; }
    public String getTransporter_kode() { return transporter_kode; }
    public String getPabrik_nama() { return pabrik_nama; }
    public String getDepo_pt() { return depo_pt; }

    public String getJs_nopol_pool() { return js_nopol_pool; }
    public String getArmada_driver() { return armada_driver; }
    public String getJs_tujuan_depo() { return js_tujuan_depo; }

    public String getJs_tujuan_co() { return js_tujuan_co; }
    public String getJs_type() { return js_type; }

    public String getMk_depo_tujuan() {
        return mk_depo_tujuan;
    }

    public String getMk_so_tujuan() {
        return mk_so_tujuan;
    }

    public String getJs_tgl() {
        return js_tgl;
    }
    public String getJs_jam() {
        return js_jam;
    }

    public String getJs_tujuan_divert() {
        return js_tujuan_divert;
    }
}
