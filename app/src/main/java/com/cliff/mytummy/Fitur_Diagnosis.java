package com.cliff.mytummy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Fitur_Diagnosis extends AppCompatActivity {
    private List<String> list = new ArrayList<>();
    private List<String> gejala = new ArrayList<>();
    private List<String> max_gejala = new ArrayList<>();
    private List<Double> tingkat_kemiripan;

    private CheckBox c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12;
    private RadioButton R13, R14, R15, R16, R17, R18, R19, R20, R21;
    private DatabaseReference database;
    private double kemiripan, jmlh_komponen_sama, jmlh_komponen_gejala, tingkat_kemiripan_tertinggi, treshold;
    private String text, hasil_akhir;
    private int index_tertinggi;


    private ArrayList<ambilData> cobaAmbil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitur_diagnosis);

        //get semua checkbox
        c1 = (CheckBox) findViewById(R.id.nyeri_ulu_hati);
        c2 = (CheckBox) findViewById(R.id.mual);
        c3 = (CheckBox) findViewById(R.id.muntah);
        c4 = (CheckBox) findViewById(R.id.nyeri_kanan_bawah);
        c5 = (CheckBox) findViewById(R.id.nyeri_kiri_bawah);
        c6 = (CheckBox) findViewById(R.id.nyeri_punggung);
        c7 = (CheckBox) findViewById(R.id.mulas);
        c8 = (CheckBox) findViewById(R.id.kram);
        c9 = (CheckBox) findViewById(R.id.kembung);
        c10 = (CheckBox) findViewById(R.id.dehidrasi);
        c11 = (CheckBox) findViewById(R.id.lemas);
        c12 = (CheckBox) findViewById(R.id.demam);
        R13 = (RadioButton) findViewById(R.id.encer);
        R14 = (RadioButton) findViewById(R.id.keras);
        R15 = (RadioButton) findViewById(R.id.berdarah);
        R16 = (RadioButton) findViewById(R.id.sendawa_1);
        R17 = (RadioButton) findViewById(R.id.sulit_bab1);
        R18 = (RadioButton) findViewById(R.id.sulit_bab3);
        R19 = (RadioButton) findViewById(R.id.buang_air_kecil1);
        R20 = (RadioButton) findViewById(R.id.buang_air_kecil3);
        R21 = (RadioButton) findViewById(R.id.buang_gas1);

        database = FirebaseDatabase.getInstance().getReference();

        Button tmbl_button = (Button) findViewById(R.id.tombol_hasil);
        tmbl_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(Fitur_Diagnosis.this);
                final View mView = getLayoutInflater().inflate(R.layout.hasil_diagnosis, null);
//                final View mHindari = getLayoutInflater().inflate(R.layout.hasil_diagnosis, null);
//                final View mLakukan = getLayoutInflater().inflate(R.layout.hasil_diagnosis, null);
//                final View mObat = getLayoutInflater().inflate(R.layout.hasil_diagnosis, null);


                final TextView hasil = mView.findViewById(R.id.hasil_diagnosis);
                if (c1.isChecked()) {
                    list.add("Nyeri di area ulu hati (perut kanan atas)");
                    c1.setChecked(false);
                }
                if (c2.isChecked()) {
                    list.add("Mual");
                    c2.setChecked(false);
                }
                if (c3.isChecked()) {
                    list.add("Muntah");
                    c3.setChecked(false);
                }
                if (c4.isChecked()) {
                    list.add("Nyeri pada perut bagian kanan bawah");
                    c3.setChecked(false);
                }
                if (c5.isChecked()) {
                    list.add("Nyeri pada perut bagian kiri bawah");
                    c3.setChecked(false);
                }
                if (c6.isChecked()) {
                    list.add("Nyeri pada punggung dan bahu");
                    c3.setChecked(false);
                }
                if (c7.isChecked()) {
                    list.add("Mulas");
                    c7.setChecked(false);
                }
                if (c8.isChecked()) {
                    list.add("Kram Perut");
                    c8.setChecked(false);
                }
                if (c9.isChecked()) {
                    list.add("Perut Kembung");
                    c9.setChecked(false);
                }
                if (c10.isChecked()) {
                    list.add("Dehidrasi");
                    c10.setChecked(false);
                }
                if (c11.isChecked()) {
                    list.add("Tubuh Menjadi Lemas");
                    c11.setChecked(false);
                }
                if (c12.isChecked()) {
                    list.add("Demam");
                    c12.setChecked(false);
                }
                if (R13.isChecked()) {
                    list.add("Tinja Menjadi Encer");
                    R13.setChecked(false);
                }
                if (R14.isChecked()) {
                    list.add("Feses Keras");
                    R14.setChecked(false);
                }
                if (R15.isChecked()) {
                    list.add("Feses Berdarah");
                    R15.setChecked(false);
                }
                if (R16.isChecked()) {
                    list.add("Sering Bersendawa");
                    R16.setChecked(false);
                }
                if (R17.isChecked()) {
                    list.add("Kesulitan Buang Air Besar");
                    R17.setChecked(false);
                }
                if (R18.isChecked()) {
                    list.add("Terlalu sering Buang Air Besar");
                    R18.setChecked(false);
                }
                if (R19.isChecked()) {
                    list.add("Kesulitan Buang Air Kecil");
                    R19.setChecked(false);
                }
                if (R20.isChecked()) {
                    list.add("Sering Buang Air Kecil");
                    R20.setChecked(false);
                }
                if (R21.isChecked()) {
                    list.add("Kesulitan Buang Gas");
                    R21.setChecked(false);
                }


                Collections.sort(list);

//                System.out.println(list);


                final Query query = database.child("penyakit");
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        cobaAmbil = new ArrayList<>();
                        tingkat_kemiripan = new ArrayList<>();


                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            ambilData cobaterus = ds.getValue(ambilData.class);
                            cobaAmbil.add(cobaterus);
                        }
                        int index = 0;
                        for (int i = 0; i < cobaAmbil.size(); i++) {
                            if (list.equals(gejala)) {
                                text = "Penyakit " + cobaAmbil.get(index).getNama();
                                hasil.setText(text);
                                break;
                            } else {
                                gejala = cobaAmbil.get(index).getGejala();
                                System.out.println(list);
//                                gejala.retainAll(list);
                                System.out.println(gejala);
                                if (list.size()>gejala.size()){
                                    gejala.retainAll(list);
                                    jmlh_komponen_sama = gejala.size();
                                    jmlh_komponen_gejala = list.size();
                                    kemiripan = jmlh_komponen_sama / jmlh_komponen_gejala;
                                }
                                else{
                                    jmlh_komponen_gejala = gejala.size();
                                    gejala.retainAll(list);
                                    jmlh_komponen_sama = gejala.size();
                                    kemiripan = jmlh_komponen_sama / jmlh_komponen_gejala;
                                }
//                                System.out.println(jmlh_komponen_sama = gejala.size());
//                                kemiripan = jmlh_komponen_sama / jmlh_komponen_gejala;
                                System.out.println(index);
                                System.out.println(jmlh_komponen_sama);
                                System.out.println(jmlh_komponen_gejala);
//                                 System.out.println(gejala);
//                                  System.out.println(kemiripan);
//                                 Collections.sort(tingkat_kemiripan,Collections.reverseOrder());
                                tingkat_kemiripan.add(kemiripan);
//                                System.out.println(index);
//                                System.out.println(tingkat_kemiripan);
                            }
//                            tingkat_kemiripan.clear();
                            index++;
                        }


                        System.out.println(tingkat_kemiripan);
                        Double max = Collections.max(tingkat_kemiripan);
                        int index_tertinggi = tingkat_kemiripan.indexOf(max);
                        if (max >= 0.6 && max <= 1.0) {
                            hasil_akhir =  "Penyakit " + cobaAmbil.get(index_tertinggi).getNama();
//                            List<String> dihindari = cobaAmbil.get(index_tertinggi).getDihindari();
//                            List<String>  dilakukan = cobaAmbil.get(index_tertinggi).getDilakukan();
//                            List<String> obat = cobaAmbil.get(index_tertinggi).getObat();
//
//                            ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(Fitur_Diagnosis.this,android.R.layout.simple_list_item_1,obat);
//                            list_obat.setAdapter(arrayAdapter1);
////                                listHasil.setAdapter(arrayAdapter);
//
//                            ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<String>(Fitur_Diagnosis.this,android.R.layout.simple_list_item_1,dihindari);
//                            list_dihindari.setAdapter(arrayAdapter3);
////                                listHasil.setAdapter(arrayAdapter);
//
//                            ArrayAdapter<String> arrayAdapter4 = new ArrayAdapter<String>(Fitur_Diagnosis.this,android.R.layout.simple_list_item_1,dilakukan);
//                            list_dilakukan.setAdapter(arrayAdapter4);
//
//                            Utility.setListViewHeightBasedOnChildren(list_dihindari);
//                            Utility.setListViewHeightBasedOnChildren(list_dilakukan);
//                            Utility.setListViewHeightBasedOnChildren(list_obat);
//
//                            mBuilder.setView(mHindari);
//                            mBuilder.setView(mLakukan);
//                            mBuilder.setView(mObat);
                        }
                        else{
                            hasil_akhir = "Belum ditemukan penyakit dengan gejala tersebut";
                        }

                        text = hasil_akhir;
                        hasil.setText(text);
                        list.clear();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();


            }
        });
    }


}
