package com.tvip.waterin_finaltujuan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tvip.waterin_finaltujuan.list_item.HttpsTrustManager;
import com.tvip.waterin_finaltujuan.list_item.comodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class listco extends AppCompatActivity {
    SearchView caridata;
    ListView listco;
    List<comodel> comodelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HttpsTrustManager.allowAllSSL();

        setContentView(R.layout.activity_listco);
        caridata = findViewById(R.id.caridata);
        listco = findViewById(R.id.listco);

        caridata.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String nextText) {
                return false;
            }
            @Override
            public boolean onQueryTextSubmit(String query) {
                getCO();

                return false;
            }
        });

    }

    public void getCO() {
        comodelList.clear();
        final SweetAlertDialog pDialog = new SweetAlertDialog(listco.this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Harap Menunggu");
        pDialog.setCancelable(false);
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://waterin.tvip.co.id/rest_server_tujuanfinal/Tujuanfinal?input=" + caridata.getQuery(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray movieArray = obj.getJSONArray("data");

                            for (int i = 0; i < movieArray.length(); i++) {

                                JSONObject co = movieArray.getJSONObject(i);

                                comodel coitem = new comodel(
                                        co.getString("js_window"),
                                        co.getString("js_date"),
                                        co.getString("js_co"),
                                        co.getString("material_nama"),
                                        co.getString("varian_label"),
                                        co.getString("varian_muatan"),
                                        co.getString("transporter_kode"),
                                        co.getString("pabrik_nama"),
                                        co.getString("depo_pt"),
                                        co.getString("js_nopol_pool"),
                                        co.getString("armada_driver"),
                                        co.getString("js_tujuan_depo"),
                                        co.getString("js_tujuan_co"),
                                        co.getString("js_type"),
                                        co.getString("js_tujuan_depo"),
                                        co.getString("js_tujuan_so"),
                                        co.getString("js_tgl"),
                                        co.getString("js_jam"),
                                        co.getString("js_tujuan_divert"));

                                comodelList.add(coitem);
                            }
                            pDialog.cancel();

                            ListViewAdapterCO adapter = new ListViewAdapterCO(comodelList, getApplicationContext());
                            listco.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

                            listco.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                                    Intent i = new Intent(listco.this, update_co.class);
                                    String id_co = ((comodel) parent.getItemAtPosition(position)).getJs_co();
                                    String type = ((comodel) parent.getItemAtPosition(position)).getVarian_label();
                                    String muatan = ((comodel) parent.getItemAtPosition(position)).getVarian_muatan();
                                    String tanggal = ((comodel) parent.getItemAtPosition(position)).getJs_date();

                                    String material = ((comodel) parent.getItemAtPosition(position)).getMaterial_nama();
                                    String pabrik = ((comodel) parent.getItemAtPosition(position)).getPabrik_nama();
                                    String window = ((comodel) parent.getItemAtPosition(position)).getJs_window();
                                    String tujuanco = ((comodel) parent.getItemAtPosition(position)).getJs_tujuan_co();
                                    String transporter = ((comodel) parent.getItemAtPosition(position)).getTransporter_kode();
                                    String depopt = ((comodel) parent.getItemAtPosition(position)).getDepo_pt();

                                    String depopool = ((comodel) parent.getItemAtPosition(position)).getMk_depo_tujuan();
                                    String so = ((comodel) parent.getItemAtPosition(position)).getMk_so_tujuan();

                                    String js_tgl = ((comodel) parent.getItemAtPosition(position)).getJs_tgl();
                                    String js_jam = ((comodel) parent.getItemAtPosition(position)).getJs_jam();
                                    String js_tujuan_divert = ((comodel) parent.getItemAtPosition(position)).getJs_tujuan_divert();
                                    String nopol = ((comodel) parent.getItemAtPosition(position)).getJs_nopol_pool();


                                    i.putExtra("id_co", id_co);
                                    i.putExtra("type", type);
                                    i.putExtra("muatan", muatan);
                                    i.putExtra("tanggal", tanggal);

                                    i.putExtra("material", material);
                                    i.putExtra("pabrik", pabrik);
                                    i.putExtra("window", window);
                                    i.putExtra("tujuanco", tujuanco);
                                    i.putExtra("transporter", transporter);
                                    i.putExtra("depopool", depopool);
                                    i.putExtra("so", so);
                                    i.putExtra("depopt", depopt);


                                    i.putExtra("tanggaldate", js_tgl);
                                    i.putExtra("jam", js_jam);
                                    i.putExtra("divert", js_tujuan_divert);
                                    i.putExtra("nopol", nopol);

                                    startActivity(i);
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.cancel();
                        new SweetAlertDialog(listco.this, SweetAlertDialog.ERROR_TYPE)
                                .setContentText("Data tidak ditemukan")
                                .show();
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
    }

    public class ListViewAdapterCO extends ArrayAdapter<comodel> {

        private class ViewHolder {

            TextView idco, code, produk, lokasi;

        }

        List<comodel> comodels;
        private Context context;

        public ListViewAdapterCO(List<comodel> movieItemList, Context context) {
            super(context, R.layout.list_item_co, movieItemList);
            this.comodels = movieItemList;
            this.context = context;

        }

        public int getCount() {
            return comodels.size();
        }

        public comodel getItem(int position) {
            return comodels.get(position);
        }

        @Override
        public int getViewTypeCount() {
            return getCount();
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        public long getItemId(int position) {
            return 0;
        }

        @SuppressLint("NewApi")
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            comodel ItemCO = getItem(position);

            final ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.list_item_co, parent, false);


                viewHolder.idco = (TextView) convertView.findViewById(R.id.idco);
                viewHolder.code = (TextView) convertView.findViewById(R.id.code);
                viewHolder.produk = (TextView) convertView.findViewById(R.id.produk);
                viewHolder.lokasi = (TextView) convertView.findViewById(R.id.lokasi);

                convertView.setTag(viewHolder);

                viewHolder.idco.setText(ItemCO.getJs_co() + " | " + ItemCO.getJs_type());
                viewHolder.code.setText(ItemCO.getVarian_label() + " " + ItemCO.getVarian_muatan() + " | " +ItemCO.getTransporter_kode());
                viewHolder.produk.setText(ItemCO.getMaterial_nama());

                if(!ItemCO.getJs_tujuan_divert().equals("")){
                    viewHolder.lokasi.setText(ItemCO.getJs_tujuan_divert());

                }else if(!ItemCO.getMk_so_tujuan().equals("")){
                    viewHolder.lokasi.setText(ItemCO.getMk_so_tujuan());
                } else if (!ItemCO.getMk_depo_tujuan().equals("")){
                    viewHolder.lokasi.setText(ItemCO.getMk_depo_tujuan());
                } else {
                    viewHolder.lokasi.setText("Tujuan Final Set");
                }






            }
            return convertView;
        }
    }

    public static String convertFormat(String inputDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = simpleDateFormat.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date == null) {
            return "";
        }
        SimpleDateFormat convetDateFormat = new SimpleDateFormat("dd MMMM yyyy");
        return convetDateFormat.format(date);
    }

    @Override
    public void onRestart() {
        super.onRestart();
        getCO();
    }
}