package com.yermakov.xplatform.lesson7.model;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yermakov.xplatform.lesson7.R;

import java.util.ArrayList;
import java.util.List;

public class TestRecyclerAdapter extends RecyclerView.Adapter<TestViewHolder> {

    private static final String TAG = "TestRecyclerAdapter";

    private List<Test> mTests;

    public TestRecyclerAdapter() {

        mTests = new ArrayList<>();
    }

    public TestRecyclerAdapter(List<Test> tests) {
        Log.v(TAG, "constructor, size: " + tests.size());

        mTests = tests;
    }

    public void setTests(List<Test> tests) {
        mTests = tests;

        notifyDataSetChanged();
    }

    @Override
    public TestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.test_item, parent, false);

        return new TestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TestViewHolder holder, int position) {
        holder.bindToTest(mTests.get(position));
    }

    @Override
    public int getItemCount() {
        return mTests.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
