package com.xyh.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.xyh.service.MyAidl;
import com.xyh.service.Person;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bindService();
    }


    MyAidl myAidl;

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myAidl = MyAidl.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };


    private void bindService() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.xyh.service",
                "com.xyh.service.AidlService"));
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public void click(View view) {
        try {
            myAidl.addPerson(new Person("xyh", 16));

            List<Person> personList = myAidl.getPersonList();

            Toast.makeText(this, "" + personList, Toast.LENGTH_SHORT).show();

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
