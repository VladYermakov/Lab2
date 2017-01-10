package com.yermakov.xplatform.lesson7.helpers;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.yermakov.xplatform.lesson7.model.Test;
import com.yermakov.xplatform.lesson7.model.TestRecyclerAdapter;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FirebaseDatabaseHelper {

    private static final String TAG = "FirebaseDatabaseHelper";

    private static DatabaseReference sReference = FirebaseDatabase.getInstance().getReference();

    private static List<Test> sTests;

    public static Query getQuery() {
        Log.v(TAG, "getQuery: " + sReference.child("tests"));
        return sReference.child("tests");
    }

    public static List<Test> getData() {

        Log.v(TAG, "getData, size: " + sTests.size());

        return sTests;
    }

    public static void processData(final TestRecyclerAdapter adapter) {
        DatabaseReference testsReference = sReference.child("tests");

        sTests = new ArrayList<>();

        testsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v(TAG, "onDataChange: " + dataSnapshot.getKey());
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    sTests.add(snapshot.getValue(Test.class));
                    Log.v(TAG, "onDataChange: " + snapshot.getKey() + " - " + snapshot.getValue(Test.class));
                }
                adapter.setTests(sTests);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, databaseError.getMessage());
            }
        });
    }

    public static void addTest(String text, @NonNull String[] variants,
                                            @NonNull Boolean[] answers) {

        addTest(text, null, Arrays.asList(variants), Arrays.asList(answers));
    }

    public static void addTest(String text, @NonNull List<String> variants,
                                            @NonNull List<Boolean> answers) {

        addTest(text, null, variants, answers);
    }

    public static void addTest(String text, @Nullable String photoLink, @NonNull List<String> variants,
                                                                        @NonNull List<Boolean> answers) {
        Test test = new Test(text, photoLink, variants, answers);

        addTest(test);
    }

    private static void addTest(@NonNull Test test) {

        sReference.child("tests").child(generateHashcode()).setValue(test);
    }

    private static String generateHashcode() {
        SecureRandom random = new SecureRandom();

        return new BigInteger(130, random).toString(32);
    }

}
