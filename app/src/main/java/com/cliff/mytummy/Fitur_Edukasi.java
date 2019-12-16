package com.cliff.mytummy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Fitur_Edukasi extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private DatabaseReference database;
    private String hasil;
    private ArrayList<ambilData> cobaAmbil;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitur__edukasi);


        database = FirebaseDatabase.getInstance().getReference();
        final ListView list_gejala = (ListView) findViewById(R.id.list_gejala);
        final ListView list_dilakukan = (ListView) findViewById(R.id.list_dilakukan);
        final ListView list_dihindari = (ListView) findViewById(R.id.list_dihindari);
        final ListView list_obat = (ListView) findViewById(R.id.list_obat);


        Spinner spinner = findViewById(R.id.label_spinner);

        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.nama_penyakit, android.R.layout.simple_spinner_item);


            adapter.setDropDownViewResource
                    (android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner.
            if (spinner != null) {
                spinner.setAdapter(adapter);
            }
        }
        btn = findViewById(R.id.cari);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Query query = database.child("penyakit");
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        cobaAmbil = new ArrayList<>();

                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            ambilData cobaterus = ds.getValue(ambilData.class);
                            cobaAmbil.add(cobaterus);
                        }

                        int index = 0;
                        for (int i = 0; i < cobaAmbil.size(); i++) {
                            if (cobaAmbil.get(index).getNama().equals(hasil)) {


                                //nampilin penyakit
//                                textHasil.setText("Berikut merupakan beberapa hal yang harus dihindari dan dilakukan gejala penyakit "+cobaAmbil.get(index).getNama());


                                String deskripsi = cobaAmbil.get(index).getDeskripsi();
//                              tampilin list gejala
                                List<String> gejala = cobaAmbil.get(index).getGejala();
//                                tampilin list gejala
                                List<String>  dilakukan = cobaAmbil.get(index).getDilakukan();
//                                tampilin list gejala
                                List<String> dihindari = cobaAmbil.get(index).getDihindari();
//                                tampilin list gejala
                                List<String> obat = cobaAmbil.get(index).getObat();



                                ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(Fitur_Edukasi.this,android.R.layout.simple_list_item_1,obat);
                                list_obat.setAdapter(arrayAdapter1);
//                                listHasil.setAdapter(arrayAdapter);

                                ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(Fitur_Edukasi.this,android.R.layout.simple_list_item_1,gejala);
                                list_gejala.setAdapter(arrayAdapter2);
//                                listHasil.setAdapter(arrayAdapter);

                                ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<String>(Fitur_Edukasi.this,android.R.layout.simple_list_item_1,dihindari);
                                list_dihindari.setAdapter(arrayAdapter3);
//                                listHasil.setAdapter(arrayAdapter);

                                ArrayAdapter<String> arrayAdapter4 = new ArrayAdapter<String>(Fitur_Edukasi.this,android.R.layout.simple_list_item_1,dilakukan);
                                list_dilakukan.setAdapter(arrayAdapter4);
//                                listHasil.setAdapter(arrayAdapter);

                                TextView DesPenyakit = (TextView) findViewById(R.id.DesPenyakit);
                                DesPenyakit.setText("Deskripsi Penyakit "+cobaAmbil.get(index).getNama());
                                DesPenyakit.setVisibility(View.VISIBLE);

                                TextView textDescription = (TextView) findViewById(R.id.textDes);
                                textDescription.setText(cobaAmbil.get(index).getDeskripsi());
                                textDescription.setVisibility(View.VISIBLE);


                                TextView tv1 = (TextView)findViewById(R.id.text1);
                                tv1.setVisibility(View.VISIBLE);
                                tv1.setText("Gejala penyakit "+cobaAmbil.get(index).getNama()+" :");

                                TextView tv2 = (TextView)findViewById(R.id.text2);
                                tv2.setVisibility(View.VISIBLE);
                                tv2.setText("Hal yang harus dilakukan seseorang yang menderita gejala penyakit "+cobaAmbil.get(index).getNama()+" :");

                                TextView tv3 = (TextView)findViewById(R.id.text3);
                                tv3.setVisibility(View.VISIBLE);
                                tv3.setText("Hal yang harus dihindari seseorang yang menderita gejala penyakit "+cobaAmbil.get(index).getNama()+" :");

                                TextView tv4 = (TextView)findViewById(R.id.text4);
                                tv4.setVisibility(View.VISIBLE);
                                tv4.setText("Rekomendasi obat gejala penyakit " + cobaAmbil.get(index).getNama()+" :");
//
                                RelativeLayout hide = (RelativeLayout) findViewById(R.id.cobaRelative);
                                hide.setVisibility(View.VISIBLE);

                                list_obat.setAdapter(arrayAdapter1);
                                list_gejala.setAdapter(arrayAdapter2);
                                list_dihindari.setAdapter(arrayAdapter3);
                                list_dilakukan.setAdapter(arrayAdapter4);

                                Utility.setListViewHeightBasedOnChildren(list_gejala);
                                Utility.setListViewHeightBasedOnChildren(list_dihindari);
                                Utility.setListViewHeightBasedOnChildren(list_dilakukan);
                                Utility.setListViewHeightBasedOnChildren(list_obat);
                            }
                            index++;
                        }

                    }




                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

    }

//
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev){
//        if(ev.getAction()==MotionEvent.ACTION_MOVE)
//            return true;
//        return super.dispatchTouchEvent(ev);
//    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        final ArrayList<List> arrayList = new ArrayList<>();
        if (parent.getItemAtPosition(position).equals("Pilih Penyakit . . .")) {
            System.out.println(position);
        } else {
            if (parent.getItemAtPosition(position).equals("Maag")) {
                hasil = "Maag";
            } else if (parent.getItemAtPosition(position).equals("Batu Empedu")) {
                hasil = "Batu Empedu";
            } else if (parent.getItemAtPosition(position).equals("Batu Ginjal")) {
                hasil = "Batu Ginjal";
            } else if (parent.getItemAtPosition(position).equals("Sembelit")) {
                hasil = "Sembelit";
            } else if (parent.getItemAtPosition(position).equals("Kolera")) {
                hasil = "Kolera";
            } else if (parent.getItemAtPosition(position).equals("Diare")) {
                hasil = "Diare";
            } else if (parent.getItemAtPosition(position).equals("Disentri")) {
                hasil = "Disentri";
            } else if (parent.getItemAtPosition(position).equals("Usus Buntu")) {
                hasil = "Usus Buntu";
            }


        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}