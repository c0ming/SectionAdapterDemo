package me.c0ming.sectionadapterdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.c0ming.SectionAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mListView;
    private int mItemCountInSection1 = 0;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (RecyclerView) findViewById(R.id.list_view);
        mListView.setLayoutManager(new LinearLayoutManager(this));
        mListView.setAdapter(new DemoAdapter());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mItemCountInSection1 = 6;

                mListView.getAdapter().notifyDataSetChanged();
            }
        }, 3000);
    }

    public class DemoAdapter extends SectionAdapter {
        private class ViewHolder1 extends RecyclerView.ViewHolder {

            private TextView mTextView;

            public ViewHolder1(View itemView) {
                super(itemView);

                mTextView = (TextView) itemView.findViewById(R.id.text_view);
            }
        }

        private class ViewHolder2 extends RecyclerView.ViewHolder {
            private TextView mTextView;

            public ViewHolder2(View itemView) {
                super(itemView);

                mTextView = (TextView) itemView.findViewById(R.id.text_view);
            }
        }

        @Override
        public int getSectionCount() {
            return 4;
        }

        @Override
        public int getItemCountInSection(int section) {
            if (section == 0) {
                return 3;
            } else if (section == 1) {
                return mItemCountInSection1; // Can be zero.
            } else if (section == 3) {
                return 1;
            }
            return 20;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolderInSection(ViewGroup parent, int section) {
            if (section == 0 || section == 3) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_type1, parent, false);
                return new ViewHolder1(view);
            } else {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_type2, parent, false);
                return new ViewHolder2(view);
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int section, int row) {
            if (section == 0 || section == 3) {
                ViewHolder1 viewHolder = (ViewHolder1) holder;
                viewHolder.mTextView.setText("[section] == " + section + ", [row] == " + row);
            } else {
                ViewHolder2 viewHolder = (ViewHolder2) holder;
                viewHolder.mTextView.setText("[section] == " + section + ", [row] == " + row);
            }

            Log.i("c0ming", "[VH] = " + holder.toString());
        }
    }
}
