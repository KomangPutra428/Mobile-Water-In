package com.tvip.waterin_finaltujuan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import com.tvip.waterin_finaltujuan.list_item.HttpsTrustManager;
import com.tvip.waterin_finaltujuan.list_item.comodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class update_co extends AppCompatActivity {
    TextView idco, hari, window, type, material, transporter, pabrik, tujualawal, nomor, divert2, pt, textnopol;
    TextView depopool, so;
    Button tujuan, batal, simpan, buttondivert;
    LinearLayout tujuanpengiriman, tujuanfinal, divert, buttonPanel2, formtujuan;
    EditText pilihtujuan, editdepo, editso, edittujuandivert, editdepodivert, editsodivert, editkategoridivert, editketerangandivert;
    SearchableSpinner choosedepo, chooseso, choosedepodivert, choosesodivert;
    TextInputLayout pilihso, pilihdepo, pilihdepodivert, pilihsodivert;
    SharedPreferences sharedPreferences;


    ArrayList<String> somodel;
    ArrayList<String> depo;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_co);
        HttpsTrustManager.allowAllSSL();

        formtujuan = findViewById(R.id.formtujuan);
        pilihdepodivert = findViewById(R.id.pilihdepodivert);
        editdepodivert = findViewById(R.id.editdepodivert);
        editsodivert = findViewById(R.id.editsodivert);
        pilihsodivert = findViewById(R.id.pilihsodivert);
        editkategoridivert = findViewById(R.id.editkategoridivert);
        idco = findViewById(R.id.idco);
        hari = findViewById(R.id.hari);
        window = findViewById(R.id.window);
        type = findViewById(R.id.type);
        material = findViewById(R.id.material);
        transporter = findViewById(R.id.transporter);
        pabrik = findViewById(R.id.pabrik);
        window = findViewById(R.id.window);
        tujualawal = findViewById(R.id.tujualawal);
        depopool = findViewById(R.id.depopool);
        so = findViewById(R.id.so);
        tujuanfinal = findViewById(R.id.tujuanfinal);
        tujuanpengiriman = findViewById(R.id.tujuanpengiriman);
        tujuan = findViewById(R.id.tujuan);
        pilihtujuan = findViewById(R.id.pilihtujuan);
        edittujuandivert = findViewById(R.id.edittujuandivert);
        choosedepo = findViewById(R.id.choosedepo);
        chooseso = findViewById(R.id.chooseso);
        editdepo = findViewById(R.id.editdepo);
        editso = findViewById(R.id.editso);
        pilihso = findViewById(R.id.pilihso);
        pilihdepo = findViewById(R.id.pilihdepo);
        editketerangandivert = findViewById(R.id.editketerangandivert);
        choosedepodivert = findViewById(R.id.choosedepodivert);
        choosesodivert = findViewById(R.id.choosesodivert);
        nomor = findViewById(R.id.nomor);
        batal = findViewById(R.id.batal);
        simpan = findViewById(R.id.simpan);
        divert2 = findViewById(R.id.divert2);
        pt = findViewById(R.id.pt);
        textnopol = findViewById(R.id.textnopol);

        somodel = new ArrayList<>();
        depo = new ArrayList<>();
        divert = findViewById(R.id.divert);
        idco.setText(getIntent().getStringExtra("id_co"));
        type.setText(getIntent().getStringExtra("type") + " " + getIntent().getStringExtra("muatan") );
        hari.setText(getIntent().getStringExtra("tanggal"));
        material.setText(getIntent().getStringExtra("material"));
        pabrik.setText(getIntent().getStringExtra("pabrik"));
        window.setText(getIntent().getStringExtra("window"));
        transporter.setText(getIntent().getStringExtra("transporter"));
        tujualawal.setText(getIntent().getStringExtra("tujuanco"));
        divert2.setText(getIntent().getStringExtra("divert"));
        pt.setText(getIntent().getStringExtra("depopt"));
        textnopol.setText(getIntent().getStringExtra("nopol"));
        buttonPanel2 = findViewById(R.id.buttonPanel2);

        buttondivert = findViewById(R.id.buttondivert);
        if(getIntent().getStringExtra("depopool").equals("null") || getIntent().getStringExtra("depopool").equals("")){
            depopool.setText("-");
        } else {
            depopool.setText(getIntent().getStringExtra("depopool"));
            tujuan.setText("Edit");

            if(textnopol.getText().toString().equals("")){
                tujuan.setVisibility(View.GONE);
            }
//
        }

        if(getIntent().getStringExtra("so").equals("null") || getIntent().getStringExtra("so").equals("")){
            so.setText("-");
        } else {
            so.setText(getIntent().getStringExtra("so"));
            tujuan.setText("Edit");
            if(textnopol.getText().toString().equals("")){
                tujuan.setVisibility(View.GONE);
            }
//            tujuan.setVisibility(View.GONE);
        }
        getso();
        getdepo();

        choosesodivert.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String bkb = chooseso.getItemAtPosition(chooseso.getSelectedItemPosition()).toString();
                editso.setText(bkb);

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        final SweetAlertDialog pDialog = new SweetAlertDialog(update_co.this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Harap Menunggu");
        pDialog.setCancelable(false);
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://waterin.tvip.co.id/rest_server_tujuanfinal/Tujuanfinal/index_mkbarcodearmada?input=" + idco.getText().toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray movieArray = obj.getJSONArray("data");

                            for (int i = 0; i < movieArray.length(); i++) {

                                JSONObject co = movieArray.getJSONObject(i);
                                nomor.setText(co.getString("mk_barcode"));

                                if(textnopol.getText().toString().equals("")){
                                    tujuan.setVisibility(View.GONE);
                                    buttondivert.setVisibility(View.GONE);
                                } else if(depopool.getText().toString().equals("-") && so.getText().toString().equals("-")){
                                    buttondivert.setVisibility(View.GONE);
                                } else if (!divert2.getText().toString().equals("")){
                                    tujuan.setVisibility(View.GONE);
                                } else if(!co.getString("mk_masuk_tujuan").equals("")){
                                    tujuan.setVisibility(View.GONE);
                                }
                                if(!co.getString("mk_bongkar_tgl").equals("0000-00-00") && !co.getString("mk_bongkar_jam").equals("00:00:00")){
                                    buttondivert.setVisibility(View.GONE);
                                }




                                pDialog.cancel();


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        if(textnopol.getText().toString().equals("")){
                            tujuan.setVisibility(View.GONE);
                            buttondivert.setVisibility(View.GONE);
                        } else if(depopool.getText().toString().equals("-") && so.getText().toString().equals("-")){
                            buttondivert.setVisibility(View.GONE);
                        }  else if (!divert2.getText().toString().equals("")){
                            tujuan.setVisibility(View.GONE);
                        }

                        pDialog.cancel();

                    }
                })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                String creds = String.format("%s:%s", "admin", "Databa53");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                params.put("Authorization", auth);
                return params;
            }
        };
        stringRequest.setRetryPolicy(
                new DefaultRetryPolicy(
                        500000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        choosedepodivert.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String bkb = choosedepodivert.getItemAtPosition(choosedepodivert.getSelectedItemPosition()).toString();
                editdepodivert.setText(bkb);

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        choosesodivert.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String bkb = choosesodivert.getItemAtPosition(choosesodivert.getSelectedItemPosition()).toString();
                editsodivert.setText(bkb);

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        tujuan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonPanel2.setVisibility(View.VISIBLE);
                    tujuanpengiriman.setVisibility(View.GONE);
                    tujuanfinal.setVisibility(View.VISIBLE);
                    divert.setVisibility(View.GONE);
                    simpan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(pilihtujuan.getText().toString().length() == 0){
                                new SweetAlertDialog(update_co.this, SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("Pilih Tujuan")
                                        .setConfirmText("OK")
                                        .show();
                            }
                            if(pilihtujuan.getText().toString().equals("Depo")) {
                                if (choosedepo.getSelectedItem().toString().equals("Pilih Tujuan")) {
                                    new SweetAlertDialog(update_co.this, SweetAlertDialog.WARNING_TYPE)
                                            .setTitleText("Pilih Tujuan")
                                            .setConfirmText("OK")

                                            .show();
                                } else {
                                    final SweetAlertDialog pDialog = new SweetAlertDialog(update_co.this, SweetAlertDialog.PROGRESS_TYPE);
                                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                                    pDialog.setTitleText("Harap Menunggu");
                                    pDialog.setCancelable(false);
                                    pDialog.show();
                                    StringRequest stringRequest = new StringRequest(Request.Method.PUT, "https://waterin.tvip.co.id/rest_server_tujuanfinal/Tujuanfinal/index_jadwal",
                                            new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {
                                                    updatedepo();
                                                }
                                            },
                                            new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                    updatedepo();

                                                }
                                            }
                                    ) {
                                        @Override
                                        public Map<String, String> getHeaders() throws AuthFailureError {
                                            HashMap<String, String> params = new HashMap<String, String>();
                                            String creds = String.format("%s:%s", "admin", "Databa53");
                                            String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                                            params.put("Authorization", auth);
                                            return params;
                                        }

                                        @Override
                                        protected Map<String, String> getParams() {
                                            Map<String, String> params = new HashMap<String, String>();


                                            params.put("js_co", idco.getText().toString());
                                            params.put("js_tujuan_depo", choosedepo.getSelectedItem().toString());
                                            params.put("js_tujuan_co", "");



                                            return params;
                                        }

                                    };
                                    stringRequest.setRetryPolicy(
                                            new DefaultRetryPolicy(
                                                    500000,
                                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                                            )
                                    );

                                    RequestQueue requestQueue = Volley.newRequestQueue(update_co.this);
                                    requestQueue.add(stringRequest);



                                }
                            } else if(pilihtujuan.getText().toString().equals("SO")) {
                                if (chooseso.getSelectedItem().toString().equals("Pilih Tujuan")) {
                                    new SweetAlertDialog(update_co.this, SweetAlertDialog.WARNING_TYPE)
                                            .setTitleText("Pilih Tujuan")
                                            .setConfirmText("OK")

                                            .show();
                                } else {
                                    final SweetAlertDialog pDialog = new SweetAlertDialog(update_co.this, SweetAlertDialog.PROGRESS_TYPE);
                                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                                    pDialog.setTitleText("Harap Menunggu");
                                    pDialog.setCancelable(false);
                                    pDialog.show();
                                    StringRequest stringRequest = new StringRequest(Request.Method.PUT, "https://waterin.tvip.co.id/rest_server_tujuanfinal/Tujuanfinal/index_jadwal",
                                            new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {
                                                    updateso();
                                                }
                                            },
                                            new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                    updateso();
                                                }
                                            }
                                    ) {
                                        @Override
                                        public Map<String, String> getHeaders() throws AuthFailureError {
                                            HashMap<String, String> params = new HashMap<String, String>();
                                            String creds = String.format("%s:%s", "admin", "Databa53");
                                            String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                                            params.put("Authorization", auth);
                                            return params;
                                        }

                                        @Override
                                        protected Map<String, String> getParams() {
                                            Map<String, String> params = new HashMap<String, String>();


                                            params.put("js_co", idco.getText().toString());
                                            params.put("js_tujuan_depo", "");
                                            params.put("js_tujuan_co", chooseso.getSelectedItem().toString());



                                            return params;
                                        }

                                    };
                                    stringRequest.setRetryPolicy(
                                            new DefaultRetryPolicy(
                                                    500000,
                                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                                            )
                                    );

                                    RequestQueue requestQueue = Volley.newRequestQueue(update_co.this);
                                    requestQueue.add(stringRequest);
                                }
                            }
                        }
                    });
                }
            });


            buttondivert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonPanel2.setVisibility(View.VISIBLE);
                    tujuanpengiriman.setVisibility(View.GONE);
                    tujuanfinal.setVisibility(View.GONE);
                    divert.setVisibility(View.VISIBLE);

                    simpan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (edittujuandivert.getText().toString().length() == 0) {
                                new SweetAlertDialog(update_co.this, SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("Pilih Tujuan Divert")
                                        .setConfirmText("OK")
                                        .show();
                            } else if (edittujuandivert.getText().toString().equals("Depo")) {
                                if (choosedepodivert.getSelectedItem().toString().equals("Pilih Tujuan")) {
                                    new SweetAlertDialog(update_co.this, SweetAlertDialog.WARNING_TYPE)
                                            .setTitleText("Pilih Tujuan")
                                            .setConfirmText("OK")

                                            .show();
                                } else {
                                    if (editkategoridivert.getText().toString().length() == 0) {
                                        new SweetAlertDialog(update_co.this, SweetAlertDialog.WARNING_TYPE)
                                                .setTitleText("Pilih Kategori Divert")
                                                .setConfirmText("OK")
                                                .show();
                                    } else if (editketerangandivert.getText().toString().length() == 0) {
                                        new SweetAlertDialog(update_co.this, SweetAlertDialog.WARNING_TYPE)
                                                .setTitleText("Isi Keterangan")
                                                .setConfirmText("OK")
                                                .show();
                                    } else {
                                        final SweetAlertDialog pDialog = new SweetAlertDialog(update_co.this, SweetAlertDialog.PROGRESS_TYPE);
                                        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                                        pDialog.setTitleText("Harap Menunggu");
                                        pDialog.setCancelable(false);
                                        pDialog.show();
                                        StringRequest stringRequest = new StringRequest(Request.Method.PUT, "https://waterin.tvip.co.id/rest_server_tujuanfinal/Tujuanfinal/index_MkBarcode",
                                                new Response.Listener<String>() {
                                                    @Override
                                                    public void onResponse(String response) {
                                                        postbarcode();
                                                    }
                                                },
                                                new Response.ErrorListener() {
                                                    @Override
                                                    public void onErrorResponse(VolleyError error) {
                                                        postbarcode();
                                                    }
                                                }
                                        ) {
                                            @Override
                                            public Map<String, String> getHeaders() throws AuthFailureError {
                                                HashMap<String, String> params = new HashMap<String, String>();
                                                String creds = String.format("%s:%s", "admin", "Databa53");
                                                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                                                params.put("Authorization", auth);
                                                return params;
                                            }

                                            @Override
                                            protected Map<String, String> getParams() {
                                                Map<String, String> params = new HashMap<String, String>();


                                                params.put("mk_co_real", idco.getText().toString());
                                                params.put("mk_divert_plan_depo", choosedepodivert.getSelectedItem().toString());
                                                params.put("mk_divert_plan_so", "");



                                                return params;
                                            }

                                        };
                                        stringRequest.setRetryPolicy(
                                                new DefaultRetryPolicy(
                                                        500000,
                                                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                                                )
                                        );

                                        RequestQueue requestQueue = Volley.newRequestQueue(update_co.this);
                                        requestQueue.add(stringRequest);
                                    }
                                }
                            } else if (edittujuandivert.getText().toString().equals("SO")) {
                                if (choosesodivert.getSelectedItem().toString().equals("Pilih Tujuan")) {
                                    new SweetAlertDialog(update_co.this, SweetAlertDialog.WARNING_TYPE)
                                            .setTitleText("Pilih Tujuan")
                                            .setConfirmText("OK")

                                            .show();
                                } else {
                                    if (editkategoridivert.getText().toString().length() == 0) {
                                        new SweetAlertDialog(update_co.this, SweetAlertDialog.WARNING_TYPE)
                                                .setTitleText("Silahkan Pilih Kategori Divert")
                                                .show();
                                    } else if (editketerangandivert.getText().toString().length() == 0) {
                                        new SweetAlertDialog(update_co.this, SweetAlertDialog.WARNING_TYPE)
                                                .setTitleText("Isi Keterangan")
                                                .setConfirmText("OK")
                                                .show();
                                    } else {
                                        final SweetAlertDialog pDialog = new SweetAlertDialog(update_co.this, SweetAlertDialog.PROGRESS_TYPE);
                                        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                                        pDialog.setTitleText("Harap Menunggu");
                                        pDialog.setCancelable(false);
                                        pDialog.show();
                                        StringRequest stringRequest = new StringRequest(Request.Method.PUT, "https://waterin.tvip.co.id/rest_server_tujuanfinal/Tujuanfinal/index_MkBarcode",
                                                new Response.Listener<String>() {
                                                    @Override
                                                    public void onResponse(String response) {
                                                        postbarcodeso();
                                                    }
                                                },
                                                new Response.ErrorListener() {
                                                    @Override
                                                    public void onErrorResponse(VolleyError error) {
                                                        postbarcodeso();
                                                    }
                                                }
                                        ) {
                                            @Override
                                            public Map<String, String> getHeaders() throws AuthFailureError {
                                                HashMap<String, String> params = new HashMap<String, String>();
                                                String creds = String.format("%s:%s", "admin", "Databa53");
                                                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                                                params.put("Authorization", auth);
                                                return params;
                                            }

                                            @Override
                                            protected Map<String, String> getParams() {
                                                Map<String, String> params = new HashMap<String, String>();


                                                params.put("mk_co_real", idco.getText().toString());
                                                params.put("mk_divert_plan_depo", "");
                                                params.put("mk_divert_plan_so", choosesodivert.getSelectedItem().toString());



                                                return params;
                                            }

                                        };
                                        stringRequest.setRetryPolicy(
                                                new DefaultRetryPolicy(
                                                        500000,
                                                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                                                )
                                        );

                                        RequestQueue requestQueue = Volley.newRequestQueue(update_co.this);
                                        requestQueue.add(stringRequest);
                                    }
                                }
                            }
                        }
                    });
                }
            });


        choosedepo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String bkb = choosedepo.getItemAtPosition(choosedepo.getSelectedItemPosition()).toString();
                editdepo.setText(bkb);

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        chooseso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String bkb = chooseso.getItemAtPosition(chooseso.getSelectedItemPosition()).toString();
                editso.setText(bkb);

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tujuanpengiriman.setVisibility(View.VISIBLE);
                tujuanfinal.setVisibility(View.GONE);
                divert.setVisibility(View.GONE);
                buttonPanel2.setVisibility(View.GONE);

            }
        });

        editkategoridivert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(update_co.this);
                alertDialog.setTitle("Pilih Tujuan");
                String[] items = {"DEPO ISSUE", "SUPPLY ISSUE", "TRANSPORT ISSUE", "FORCE MAJEUR"};
                int checkedItem = -1;
                alertDialog.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                editkategoridivert.setText("DEPO ISSUE");
                                dialog.dismiss();

                                break;
                            case 1:
                                editkategoridivert.setText("SUPPLY ISSUE");
                                dialog.dismiss();

                                break;
                            case 2:
                                editkategoridivert.setText("TRANSPORT ISSUE");
                                dialog.dismiss();

                                break;
                            case 3:
                                editkategoridivert.setText("FORCE MAJEUR");
                                dialog.dismiss();

                                break;
                        }
                    }
                });

                AlertDialog alert = alertDialog.create();
                alert.setCanceledOnTouchOutside(false);
                alert.show();
            }
        });

        edittujuandivert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(update_co.this);
                alertDialog.setTitle("Pilih Tujuan");
                String[] items = {"Depo", "SO"};
                int checkedItem = 2;
                alertDialog.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                pilihdepodivert.setVisibility(View.VISIBLE);
                                pilihsodivert.setVisibility(View.GONE);
                                edittujuandivert.setText("Depo");
                                dialog.dismiss();

                                break;
                            case 1:
                                pilihdepodivert.setVisibility(View.GONE);
                                pilihsodivert.setVisibility(View.VISIBLE);
                                edittujuandivert.setText("SO");
                                dialog.dismiss();

                                break;
                        }
                    }
                });

                AlertDialog alert = alertDialog.create();
                alert.setCanceledOnTouchOutside(false);
                alert.show();
            }
        });

        pilihtujuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(update_co.this);
                alertDialog.setTitle("Pilih Tujuan");
                String[] items = {"Depo", "SO"};
                int checkedItem = 2;
                alertDialog.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                pilihdepo.setVisibility(View.VISIBLE);
                                pilihso.setVisibility(View.GONE);
                                pilihtujuan.setText("Depo");
                                dialog.dismiss();
                                break;
                            case 1:
                                pilihdepo.setVisibility(View.GONE);
                                pilihso.setVisibility(View.VISIBLE);
                                pilihtujuan.setText("SO");
                                dialog.dismiss();
                                break;
                        }
                    }
                });

                AlertDialog alert = alertDialog.create();
                alert.setCanceledOnTouchOutside(false);
                alert.show();
            }
        });


        AtomicLong mLastClickTime = new AtomicLong();

        choosesodivert.setOnTouchListener((v, event) -> {
            if (SystemClock.elapsedRealtime() - mLastClickTime.get() < 1000) {
                choosesodivert.setEnabled(false);
                return false;
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    choosesodivert.setEnabled(true);
                }
            }, 3000);
            mLastClickTime.set(SystemClock.elapsedRealtime());
            event.setAction(MotionEvent.ACTION_UP);
            choosesodivert.onTouch(v,event);
            choosesodivert.setEnabled(false);
            return true;
        });

        choosedepodivert.setOnTouchListener((v, event) -> {
            if (SystemClock.elapsedRealtime() - mLastClickTime.get() < 1000) {
                choosedepodivert.setEnabled(false);
                return false;
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    choosedepodivert.setEnabled(true);
                }
            }, 3000);
            mLastClickTime.set(SystemClock.elapsedRealtime());
            event.setAction(MotionEvent.ACTION_UP);
            choosedepodivert.onTouch(v,event);
            choosedepodivert.setEnabled(false);
            return true;
        });


        chooseso.setOnTouchListener((v, event) -> {
            if (SystemClock.elapsedRealtime() - mLastClickTime.get() < 1000) {
                chooseso.setEnabled(false);
                return false;
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    chooseso.setEnabled(true);
                }
            }, 3000);
            mLastClickTime.set(SystemClock.elapsedRealtime());
            event.setAction(MotionEvent.ACTION_UP);
            chooseso.onTouch(v,event);
            chooseso.setEnabled(false);
            return true;
        });

        choosedepo.setOnTouchListener((v, event) -> {
            if (SystemClock.elapsedRealtime() - mLastClickTime.get() < 1000) {
                choosedepo.setEnabled(false);
                return false;
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    choosedepo.setEnabled(true);
                }
            }, 3000);
            mLastClickTime.set(SystemClock.elapsedRealtime());
            event.setAction(MotionEvent.ACTION_UP);
            choosedepo.onTouch(v,event);
            choosedepo.setEnabled(false);
            return true;
        });

    }

    private void postbarcodeso() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://waterin.tvip.co.id/rest_server_tujuanfinal/Tujuanfinal/index_barcodedivert",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        index_MkArmadaBarcodeSO();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        index_MkArmadaBarcodeSO();

                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                String creds = String.format("%s:%s", "admin", "Databa53");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                params.put("Authorization", auth);
                return params;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                Date c = Calendar.getInstance().getTime();
                System.out.println("Current time => " + c);
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                SimpleDateFormat jam = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

                String tanggal = date.format(c);
                String waktu = jam.format(c);

                params.put("mk_barcode", nomor.getText().toString());
                params.put("mk_masuk_tujuan", tujualawal.getText().toString());
                params.put("mk_masuk_tujuan_tgl", getIntent().getStringExtra("tanggaldate"));
                params.put("mk_masuk_tujuan_jam", getIntent().getStringExtra("jam"));
                params.put("mk_divert_plan_depo", "");
                params.put("mk_divert_plan_so", choosesodivert.getSelectedItem().toString());

                params.put("mk_divert_plan_tgl", tanggal);
                params.put("mk_divert_plan_jam", waktu);

                return params;
            }

        };
        stringRequest.setRetryPolicy(
                new DefaultRetryPolicy(
                        500000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
        );

        RequestQueue requestQueue = Volley.newRequestQueue(update_co.this);
        requestQueue.add(stringRequest);
    }

    private void index_MkArmadaBarcodeSO() {
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, "https://waterin.tvip.co.id/rest_server_tujuanfinal/Tujuanfinal/index_MkArmadaBarcode",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        updatesupply2();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        updatesupply2();

                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                String creds = String.format("%s:%s", "admin", "Databa53");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                params.put("Authorization", auth);
                return params;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();


                params.put("co", idco.getText().toString());
                return params;
            }

        };
        stringRequest.setRetryPolicy(
                new DefaultRetryPolicy(
                        500000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
        );

        RequestQueue requestQueue = Volley.newRequestQueue(update_co.this);
        requestQueue.add(stringRequest);

    }

    private void postbarcode() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://waterin.tvip.co.id/rest_server_tujuanfinal/Tujuanfinal/index_barcodedivert",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        index_MkArmadaBarcode();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        index_MkArmadaBarcode();
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                String creds = String.format("%s:%s", "admin", "Databa53");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                params.put("Authorization", auth);
                return params;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                Date c = Calendar.getInstance().getTime();
                System.out.println("Current time => " + c);
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                SimpleDateFormat jam = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

                String tanggal = date.format(c);
                String waktu = jam.format(c);

                params.put("mk_barcode", nomor.getText().toString());
                params.put("mk_masuk_tujuan", tujualawal.getText().toString());
                params.put("mk_masuk_tujuan_tgl", getIntent().getStringExtra("tanggaldate"));
                params.put("mk_masuk_tujuan_jam", getIntent().getStringExtra("jam"));
                params.put("mk_divert_plan_depo", choosedepodivert.getSelectedItem().toString());
                params.put("mk_divert_plan_so", "");

                params.put("mk_divert_plan_tgl", tanggal);
                params.put("mk_divert_plan_jam", waktu);

                return params;
            }

        };
        stringRequest.setRetryPolicy(
                new DefaultRetryPolicy(
                        500000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
        );

        RequestQueue requestQueue = Volley.newRequestQueue(update_co.this);
        requestQueue.add(stringRequest);
    }

    private void index_MkArmadaBarcode() {
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, "https://waterin.tvip.co.id/rest_server_tujuanfinal/Tujuanfinal/index_MkArmadaBarcode",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        updatesupply();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        updatesupply();
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                String creds = String.format("%s:%s", "admin", "Databa53");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                params.put("Authorization", auth);
                return params;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();


                params.put("co", idco.getText().toString());
                return params;
            }

        };
        stringRequest.setRetryPolicy(
                new DefaultRetryPolicy(
                        500000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
        );

        RequestQueue requestQueue = Volley.newRequestQueue(update_co.this);
        requestQueue.add(stringRequest);

    }

    private void updatesupply2() {
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, "https://waterin.tvip.co.id/rest_server_tujuanfinal/Tujuanfinal/index_updateJadwalSupply",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        logdivert2();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        logdivert2();

                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                String creds = String.format("%s:%s", "admin", "Databa53");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                params.put("Authorization", auth);
                return params;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();


                params.put("js_co", idco.getText().toString());
                params.put("js_tujuan_divert", choosesodivert.getSelectedItem().toString());



                return params;
            }

        };
        stringRequest.setRetryPolicy(
                new DefaultRetryPolicy(
                        500000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
        );

        RequestQueue requestQueue = Volley.newRequestQueue(update_co.this);
        requestQueue.add(stringRequest);
    }

    private void logdivert2() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://waterin.tvip.co.id/rest_server_tujuanfinal/Tujuanfinal/index_logdivert",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        notedivert2();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        notedivert2();

                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                String creds = String.format("%s:%s", "admin", "Databa53");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                params.put("Authorization", auth);
                return params;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                Date c = Calendar.getInstance().getTime();
                System.out.println("Current time => " + c);
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                SimpleDateFormat jam = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

                String tanggal = date.format(c);
                String waktu = jam.format(c);
                sharedPreferences = getSharedPreferences("user_details", MODE_PRIVATE);
                String nik_baru = sharedPreferences.getString("nik_baru", null);

                params.put("user_id", nik_baru);
                params.put("au_tgl", tanggal);
                params.put("au_jam", waktu);
                params.put("au_no_co", idco.getText().toString());


                return params;
            }

        };
        stringRequest.setRetryPolicy(
                new DefaultRetryPolicy(
                        500000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
        );

        RequestQueue requestQueue = Volley.newRequestQueue(update_co.this);
        requestQueue.add(stringRequest);
    }

    private void notedivert2() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://waterin.tvip.co.id/rest_server_tujuanfinal/Tujuanfinal/index_notedivert",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        new SweetAlertDialog(update_co.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Data berhasil disimpan")
                                .setConfirmText("OK")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        finish();
                                    }
                                })
                                .show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                String creds = String.format("%s:%s", "admin", "Databa53");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                params.put("Authorization", auth);
                return params;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                Date c = Calendar.getInstance().getTime();
                System.out.println("Current time => " + c);
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

                String tanggal = date.format(c);

                params.put("co", idco.getText().toString());
                params.put("plan", tujualawal.getText().toString());

                params.put("final", so.getText().toString());

                params.put("divert", choosesodivert.getSelectedItem().toString());
                params.put("category", editkategoridivert.getText().toString());
                params.put("note", editketerangandivert.getText().toString());
                params.put("timestamp", tanggal);





                return params;
            }

        };
        stringRequest.setRetryPolicy(
                new DefaultRetryPolicy(
                        500000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
        );

        RequestQueue requestQueue = Volley.newRequestQueue(update_co.this);
        requestQueue.add(stringRequest);
    }

    private void updatesupply() {
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, "https://waterin.tvip.co.id/rest_server_tujuanfinal/Tujuanfinal/index_updateJadwalSupply",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        logdivert();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        logdivert();
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                String creds = String.format("%s:%s", "admin", "Databa53");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                params.put("Authorization", auth);
                return params;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();


                params.put("js_co", idco.getText().toString());
                params.put("js_tujuan_divert", choosedepodivert.getSelectedItem().toString());



                return params;
            }

        };
        stringRequest.setRetryPolicy(
                new DefaultRetryPolicy(
                        500000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
        );

        RequestQueue requestQueue = Volley.newRequestQueue(update_co.this);
        requestQueue.add(stringRequest);

    }

    private void logdivert() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://waterin.tvip.co.id/rest_server_tujuanfinal/Tujuanfinal/index_logdivert",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        notedivert();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        notedivert();
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                String creds = String.format("%s:%s", "admin", "Databa53");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                params.put("Authorization", auth);
                return params;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                Date c = Calendar.getInstance().getTime();
                System.out.println("Current time => " + c);
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                SimpleDateFormat jam = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

                String tanggal = date.format(c);
                String waktu = jam.format(c);
                sharedPreferences = getSharedPreferences("user_details", MODE_PRIVATE);
                String nik_baru = sharedPreferences.getString("nik_baru", null);

                params.put("user_id", nik_baru);
                params.put("au_tgl", tanggal);
                params.put("au_jam", waktu);
                params.put("au_no_co", idco.getText().toString());


                return params;
            }

        };
        stringRequest.setRetryPolicy(
                new DefaultRetryPolicy(
                        500000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
        );

        RequestQueue requestQueue = Volley.newRequestQueue(update_co.this);
        requestQueue.add(stringRequest);
    }

    private void notedivert() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://waterin.tvip.co.id/rest_server_tujuanfinal/Tujuanfinal/index_notedivert",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        new SweetAlertDialog(update_co.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Data berhasil disimpan")
                                .setConfirmText("OK")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        finish();
                                    }
                                })
                                .show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                String creds = String.format("%s:%s", "admin", "Databa53");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                params.put("Authorization", auth);
                return params;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                Date c = Calendar.getInstance().getTime();
                System.out.println("Current time => " + c);
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

                String tanggal = date.format(c);

                params.put("co", idco.getText().toString());
                params.put("plan", tujualawal.getText().toString());
                params.put("final", depopool.getText().toString());

                params.put("divert", choosedepodivert.getSelectedItem().toString());
                params.put("category", editkategoridivert.getText().toString());
                params.put("note", editketerangandivert.getText().toString());
                params.put("timestamp", tanggal);

                return params;
            }

        };
        stringRequest.setRetryPolicy(
                new DefaultRetryPolicy(
                        500000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
        );

        RequestQueue requestQueue = Volley.newRequestQueue(update_co.this);
        requestQueue.add(stringRequest);
    }

    private void updatedepo() {
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, "https://waterin.tvip.co.id/rest_server_tujuanfinal/Tujuanfinal/index",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        postlog();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        postlog();

                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                String creds = String.format("%s:%s", "admin", "Databa53");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                params.put("Authorization", auth);
                return params;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();


                params.put("mk_co_real", idco.getText().toString());
                params.put("mk_depo_tujuan", choosedepo.getSelectedItem().toString());
                params.put("mk_so_tujuan", "");



                return params;
            }

        };
        stringRequest.setRetryPolicy(
                new DefaultRetryPolicy(
                        500000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
        );

        RequestQueue requestQueue = Volley.newRequestQueue(update_co.this);
        requestQueue.add(stringRequest);
    }

    private void updateso() {
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, "https://waterin.tvip.co.id/rest_server_tujuanfinal/Tujuanfinal/index",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        postlog();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        postlog();

                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                String creds = String.format("%s:%s", "admin", "Databa53");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                params.put("Authorization", auth);
                return params;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();


                params.put("mk_co_real", idco.getText().toString());
                params.put("mk_depo_tujuan", "");
                params.put("mk_so_tujuan", chooseso.getSelectedItem().toString());



                return params;
            }

        };
        stringRequest.setRetryPolicy(
                new DefaultRetryPolicy(
                        500000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
        );

        RequestQueue requestQueue = Volley.newRequestQueue(update_co.this);
        requestQueue.add(stringRequest);
    }

    private void postlog() {
        final StringRequest stringRequest2 = new StringRequest(Request.Method.POST, "https://waterin.tvip.co.id/rest_server_tujuanfinal/Tujuanfinal/index_log",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        new SweetAlertDialog(update_co.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Data berhasil disimpan")
                                .setConfirmText("OK")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        finish();
                                    }
                                })
                                .show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        new SweetAlertDialog(update_co.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Kesalahan dalam system")
                                .setConfirmText("Iya")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        finish();
                                    }
                                })
                                .show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                Date c = Calendar.getInstance().getTime();
                System.out.println("Current time => " + c);
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                SimpleDateFormat jam = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

                String tanggal = date.format(c);
                String waktu = jam.format(c);
                sharedPreferences = getSharedPreferences("user_details", MODE_PRIVATE);
                String nik_baru = sharedPreferences.getString("nik_baru", null);

                params.put("user_id", nik_baru);
                params.put("au_tgl", tanggal);
                params.put("au_jam", waktu);
                params.put("au_no_co", idco.getText().toString());



                return params;
            }
        };
        stringRequest2.setRetryPolicy(
                new DefaultRetryPolicy(
                        500000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
        );

        RequestQueue requestQueue2 = Volley.newRequestQueue(update_co.this);
        requestQueue2.add(stringRequest2);
    }

    private void getdepo() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://waterin.tvip.co.id/rest_server_tujuanfinal/Tujuanfinal/index_spinnerdepo", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                depo.add("Pilih Tujuan");
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("status").equals("true")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            String kodeid = jsonObject1.getString("depo_nama");
                            depo.add(kodeid);

                        }
                    }
                    choosedepodivert.setTitle("Pilih Tujuan");
                    choosedepo.setTitle("Pilih Tujuan");
                    choosedepodivert.setAdapter(new ArrayAdapter<String>(update_co.this, android.R.layout.simple_spinner_dropdown_item, depo));
                    choosedepo.setAdapter(new ArrayAdapter<String>(update_co.this, android.R.layout.simple_spinner_dropdown_item, depo));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        stringRequest.setRetryPolicy(
                new DefaultRetryPolicy(
                        500000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                ));
        RequestQueue requestQueue = Volley.newRequestQueue(update_co.this);
        requestQueue.add(stringRequest);
    }

    private void getso() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://waterin.tvip.co.id/rest_server_tujuanfinal/Tujuanfinal/index_spinnerso", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                somodel.add("Pilih Tujuan");
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("status").equals("true")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            String kodeid = jsonObject1.getString("so_nama");
                            somodel.add(kodeid);

                        }
                    }

                    choosesodivert.setTitle("Pilih Tujuan");
                    chooseso.setTitle("Pilih Tujuan");
                    chooseso.setAdapter(new ArrayAdapter<String>(update_co.this, android.R.layout.simple_spinner_dropdown_item, somodel));
                    choosesodivert.setAdapter(new ArrayAdapter<String>(update_co.this, android.R.layout.simple_spinner_dropdown_item, somodel));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        stringRequest.setRetryPolicy(
                new DefaultRetryPolicy(
                        500000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                ));
        RequestQueue requestQueue = Volley.newRequestQueue(update_co.this);
        requestQueue.add(stringRequest);
    }

//    public static String tanggal (String inputDate){
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy") || ;
//        Date date = null;
//        try {
//            date = simpleDateFormat.parse(inputDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        if (date == null) {
//            return "";
//        }
//        SimpleDateFormat convetDateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy");
//        return convetDateFormat.format(date);
//    }


}

//
//
//    }


//        dataco = findViewById(R.id.dataco);
//        type = findViewById(R.id.type);
//        muatan = findViewById(R.id.muatan);
//        hari = findViewById(R.id.hari);
//        tanggal = findViewById(R.id.tanggal);
//        material = findViewById(R.id.material);
//        pabrik = findViewById(R.id.pabrik);
//        window = findViewById(R.id.window);
//        tujuandepo = findViewById(R.id.tujuandepo);
//        transporter = findViewById(R.id.transporter);
//        choosedepo = findViewById(R.id.choosedepo);
//        depotext = findViewById(R.id.depotext);
//        edittexttujuan = findViewById(R.id.tujuan);
//

//
//        chooseso = findViewById(R.id.chooseso);
//        sotext = findViewById(R.id.sotext);
//        depo = new ArrayList<>();
//
//        update_final = findViewById(R.id.update_final);
//

//
//        transporter.setText(getIntent().getStringExtra("transporter"));
//
//        edittexttujuan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder alertDialog = new AlertDialog.Builder(update_co.this);
//                alertDialog.setTitle("Pilih Tujuan");
//                String[] items = {"Depo","SO"};
//                int checkedItem = 2;
//                alertDialog.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        switch (which) {
//                            case 0:
//                                editdepo.setVisibility(View.VISIBLE);
//                                editso.setVisibility(View.INVISIBLE);
//                                edittexttujuan.setText("Depo");
//                                break;
//                            case 1:
//                                editdepo.setVisibility(View.INVISIBLE);
//                                editso.setVisibility(View.VISIBLE);
//                                edittexttujuan.setText("SO");
//                                break;
//                        }
//                    }
//                });
//                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//                AlertDialog alert = alertDialog.create();
//                alert.setCanceledOnTouchOutside(false);
//                alert.show();
//            }
//        });
//
//        update_final.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {

//        });
//
//
//

//
//        getdepo();
//        getso();
//
//    }
//

//

//

//

