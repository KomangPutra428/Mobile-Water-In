package com.tvip.waterin_finaltujuan;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tvip.waterin_finaltujuan.list_item.HttpsTrustManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class login extends AppCompatActivity {
    EditText nikbaru, editpassword;
    Button login;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        HttpsTrustManager.allowAllSSL();

        nikbaru = findViewById(R.id.nikbaru);
        editpassword = findViewById(R.id.editpassword);

        login = findViewById(R.id.login);

        sharedPreferences = getSharedPreferences("user_details", MODE_PRIVATE);

        if (sharedPreferences.contains("nik_baru")) {
            Intent intent = new Intent(login.this, MainActivity.class);
            startActivity(intent);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nikbaru.getText().toString().length() == 0) {
                    nikbaru.setError("Silahkan Isi Nik Baru");
                } else if (editpassword.getText().toString().length() == 0) {
                    editpassword.setError("Silahkan Isi Password");
                } else {

                    final SweetAlertDialog pDialog = new SweetAlertDialog(login.this, SweetAlertDialog.PROGRESS_TYPE);
                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                    pDialog.setTitleText("Harap Menunggu");
                    pDialog.setCancelable(false);
                    pDialog.show();
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://hrd.tvip.co.id/rest_server/api/login/index?nik_baru=" + nikbaru.getText().toString(),
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject obj = new JSONObject(response);
                                        if (obj.getString("status").equals("true")) {
                                            JSONArray movieArray = obj.getJSONArray("data");
                                        for (int i = 0; i < movieArray.length(); i++) {

                                            JSONObject movieObject = movieArray.getJSONObject(i);
                                            pDialog.cancel();

                                            if (movieObject.getString("password").equals(md5(editpassword.getText().toString()))) {
                                                if (movieObject.getString("jabatan_struktur").equals("202") ||
                                                        (movieObject.getString("jabatan_struktur").equals("205") ||
                                                                (movieObject.getString("jabatan_struktur").equals("447") ||
                                                                        (movieObject.getString("jabatan_struktur").equals("183") ||
                                                                                (movieObject.getString("jabatan_struktur").equals("184") ||
                                                                                        (movieObject.getString("jabatan_struktur").equals("185") ||
                                                                                                (movieObject.getString("jabatan_struktur").equals("186") ||
                                                                                                        (movieObject.getString("jabatan_struktur").equals("187") ||
                                                                                                                (movieObject.getString("jabatan_struktur").equals("188") ||
                                                                                                                        (movieObject.getString("jabatan_struktur").equals("189") ||
                                                                                                                                (movieObject.getString("jabatan_struktur").equals("192") ||
                                                                                                                                        (movieObject.getString("jabatan_struktur").equals("193") ||
                                                                                                                                                (movieObject.getString("jabatan_struktur").equals("416") ||
                                                                                                                                                        (movieObject.getString("jabatan_struktur").equals("428"))))))))))))))) {
//                                                    new SweetAlertDialog(login.this, SweetAlertDialog.SUCCESS_TYPE)
//                                                            .setContentText("Selamat Datang")
//                                                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                                                @Override
//                                                                public void onClick(SweetAlertDialog sDialog) {
                                                                    Intent intent = new Intent(login.this, MainActivity.class);
                                                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                                                    editor.putString("nik_baru", nikbaru.getText().toString());
                                                                    editor.apply();
//                                                                    sDialog.dismissWithAnimation();
                                                                    startActivity(intent);
//                                                                }
//                                                            })
//                                                            .show();
                                                } else {
                                                    new SweetAlertDialog(login.this, SweetAlertDialog.ERROR_TYPE)
                                                            .setContentText("Maaf, Anda tidak memiliki hak akses")
                                                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                                @Override
                                                                public void onClick(SweetAlertDialog sDialog) {
                                                                    sDialog.dismissWithAnimation();
                                                                }
                                                            })
                                                            .show();

                                                }
                                            } else if (!movieObject.getString("password").equals(md5(editpassword.getText().toString()))) {
                                                new SweetAlertDialog(login.this, SweetAlertDialog.ERROR_TYPE)
                                                        .setContentText("Oops... NIK / Password Salah")
                                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                            @Override
                                                            public void onClick(SweetAlertDialog sDialog) {
                                                                sDialog.dismissWithAnimation();
                                                            }
                                                        })
                                                        .show();
                                            }

                                        }

                                        } else {
                                            new SweetAlertDialog(login.this, SweetAlertDialog.ERROR_TYPE)
                                                    .setContentText("Oops... NIK / Password Salah")
                                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                        @Override
                                                        public void onClick(SweetAlertDialog sDialog) {
                                                            sDialog.dismissWithAnimation();
                                                        }
                                                    })
                                                    .show();
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
                                    pDialog.cancel();
                                    if (error instanceof ServerError) {
                                        new SweetAlertDialog(login.this, SweetAlertDialog.ERROR_TYPE)
                                                .setContentText("Nik anda salah!")
                                                .show();
                                    } else {
                                        new SweetAlertDialog(login.this, SweetAlertDialog.ERROR_TYPE)
                                                .setContentText("Jaringan sedang bermasalah!")
                                                .show();
                                    }
                                }
                            }) {
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
                            ));
                    RequestQueue requestQueue = Volley.newRequestQueue(login.this);
                    requestQueue.add(stringRequest);
                }
            }
        });
    }

    public String md5(String s) {
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(s.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder(2*hash.length);
            for(byte b : hash)
            {
                sb.append(String.format("%02x", b&0xff));
            }
            digest = sb.toString();
        } catch (NoSuchAlgorithmException ex)
        {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return digest;
    }

    @Override
    public void onBackPressed() {
        new SweetAlertDialog(login.this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Apakah anda yakin?")
                .setContentText("Anda akan keluar dari aplikasi ini")
                .setConfirmText("Yes")
                .setCancelText("Cancel")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        finishAffinity();
                        finish();
                    }
                })
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                    }
                })
                .show();
    }
}