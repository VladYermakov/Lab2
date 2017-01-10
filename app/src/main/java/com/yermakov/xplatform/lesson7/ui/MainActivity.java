package com.yermakov.xplatform.lesson7.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.yermakov.xplatform.lesson7.R;
import com.yermakov.xplatform.lesson7.helpers.FirebaseDatabaseHelper;
import com.yermakov.xplatform.lesson7.model.TestRecyclerAdapter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        list = (RecyclerView) findViewById(R.id.test_list);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.sign_out_item) {
            mAuth.signOut();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

            finish();

            return true;
        }
        else return super.onOptionsItemSelected(item);

    }

    private void displayList() {
        Log.v(TAG, "displayList");
        /*FirebaseRecyclerAdapter<Test, TestViewHolder> adapter =
                new FirebaseRecyclerAdapter<Test, TestViewHolder>(Test.class, R.layout.test_item,
                        TestViewHolder.class, FirebaseDatabaseHelper.getQuery()) {

            @Override
            protected void populateViewHolder(TestViewHolder viewHolder, Test test, int position) {
                Log.v(TAG, "populateViewHolder: " + position);
                viewHolder.bindToTest(test);
            }
        };*/

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        list.setLayoutManager(linearLayoutManager);

        TestRecyclerAdapter adapter = new TestRecyclerAdapter();
        FirebaseDatabaseHelper.processData(adapter);

        list.setAdapter(adapter);

    }

}
