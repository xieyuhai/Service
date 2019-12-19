// MyAidl.aidl
package com.xyh.service;

import com.xyh.service.Person;

// Declare any non-default types here with import statements

interface MyAidl {
   void addPerson(in Person person);

   List<Person> getPersonList();
}
