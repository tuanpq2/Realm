package com.example.lap11799.realm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(getApplicationContext());
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("myRealm.realm")
                .build();
        Realm myRealm = Realm.getInstance(configuration);
        myRealm.beginTransaction();
        // Create an object
        Country country1 = myRealm.createObject(Country.class, UUID.randomUUID().toString());
        // Set its fields
        country1.setName("Norway");
        country1.setPopulation(5165800);
        myRealm.commitTransaction();

        //Query
        RealmResults<Country> results = myRealm.where(Country.class).findAll();
        for(int i = 0;i<results.size();i++){
            Log.d("Realm " + i,results.get(i).getName());
        }
    }
}
