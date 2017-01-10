package com.yermakov.xplatform.lesson7.model;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yermakov.xplatform.lesson7.R;

public class TestViewHolder extends RecyclerView.ViewHolder {

    private TextView testView;

    private View hr;

    public ImageView image;

    private Button[] variants = new Button[4];

    public TestViewHolder(View view) {
        super(view);

        testView = (TextView) view.findViewById(R.id.test);

        hr = view.findViewById(R.id.hr);

        image = (ImageView) view.findViewById(R.id.image);

        variants[0] = (Button) view.findViewById(R.id.variant1);
        variants[1] = (Button) view.findViewById(R.id.variant2);
        variants[2] = (Button) view.findViewById(R.id.variant3);
        variants[3] = (Button) view.findViewById(R.id.variant4);
    }

    public void bindToTest(final Test test) {
        testView.setText(test.getQuestion());

        // TODO: set the image

        for (int i = 0; i < 4; ++i) {
            variants[i].setText(test.getVariant(i));

            final int finalI = i;

            variants[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (test.getAnswer(finalI)) {
                        hr.setBackgroundColor(Color.GREEN);
                    } else {
                        hr.setBackgroundColor(Color.RED);
                    }
                }
            });
        }
    }
}

