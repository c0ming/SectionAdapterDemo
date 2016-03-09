package me.c0ming;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class SectionAdapter extends RecyclerView.Adapter {
    public class IndexPath {
        public int section;
        public int row;

        public IndexPath(int section, int row) {
            this.section = section;
            this.row = row;
        }
    }

    private List<IndexPath> mIndexPaths;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final RecyclerView.ViewHolder viewHolder = onCreateViewHolderInSection(parent, viewType);
        if (viewHolder != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getParent() instanceof RecyclerView) {
                        RecyclerView recyclerView = (RecyclerView) v.getParent();
                        int position = recyclerView.getLayoutManager().getPosition(v);
                        IndexPath indexPath = mIndexPaths.get(position);
                        onItemClick(viewHolder, indexPath);
                    }
                }
            });
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        IndexPath indexPath = mIndexPaths.get(position);
        onBindViewHolder(holder, indexPath);
    }

    @Override
    public int getItemCount() {
        if (mIndexPaths == null) {
            mIndexPaths = new ArrayList<>();

            updateIndexPaths();

            registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                @Override
                public void onChanged() {
                    super.onChanged();

                    updateIndexPaths();
                }

                // Maybe you should override other onChanged method.
            });
        }

        return mIndexPaths.size();
    }

    @Override
    public int getItemViewType(int position) {
        IndexPath indexPath = mIndexPaths.get(position);
        return indexPath.section;
    }

    private void updateIndexPaths() {
        mIndexPaths.clear();

        int sectionCount = getSectionCount();
        for (int i = 0; i < sectionCount; i++) {
            int itemCountInSection = getItemCountInSection(i);
            for (int j = 0; j < itemCountInSection; j++) {
                IndexPath indexPath = new IndexPath(i, j);
                mIndexPaths.add(indexPath);
            }
        }
    }

    public abstract int getSectionCount();

    public abstract int getItemCountInSection(int section);

    public abstract RecyclerView.ViewHolder onCreateViewHolderInSection(ViewGroup parent, int section);

    public abstract void onBindViewHolder(RecyclerView.ViewHolder holder, IndexPath indexPath);

    public abstract void onItemClick(RecyclerView.ViewHolder holder, IndexPath indexPath);
}
