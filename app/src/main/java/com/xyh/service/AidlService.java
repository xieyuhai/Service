package com.xyh.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.ArrayList;
import java.util.List;

public class AidlService extends Service {

    private ArrayList<Person> personArrayList;

    @Override
    public IBinder onBind(Intent intent) {
        personArrayList = new ArrayList<>();
        return iBinder;
    }

    private IBinder iBinder = new MyAidl.Stub() {

        @Override
        public void addPerson(Person person) throws RemoteException {

            person.setName("新的了");

            personArrayList.add(person);
        }

        @Override
        public List<Person> getPersonList() throws RemoteException {
            return personArrayList;
        }
    };
}
