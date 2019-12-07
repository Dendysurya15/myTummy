package com.cliff.mytummy;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class FirebaseHelper {

    DatabaseReference db;
    Boolean saved=null;

    public FirebaseHelper(DatabaseReference db) {
        this.db = db;
    }

    public  Boolean save(ambilData ambilData)
    {
        if(ambilData==null)
        {
            saved=false;
        }else
        {
            try
            {
                db.child("Obat").push().setValue(ambilData);
                saved=true;
            }catch (DatabaseException e)
            {
                e.printStackTrace();
                saved=false;
            }
        }

        return saved;
    }
    //READ
    public ArrayList<String> retrieve()
    {
        final ArrayList<String> ambilData=new ArrayList<>();

        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot,ambilData);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot,ambilData);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return ambilData;
    }

    private void fetchData(DataSnapshot snapshot,ArrayList<String> ambilData)
    {
        ambilData.clear();
        for (DataSnapshot ds:snapshot.getChildren())
        {
            String obat=ds.getValue(ambilData.class).getObat();
            ambilData.add(obat);
        }

    }
}