```
public class DemoAdapter extends SectionAdapter {
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, IndexPath indexPath) {
        if (indexPath.section == 0 || indexPath.section == 3) {
            ViewHolder1 viewHolder = (ViewHolder1) holder;
            viewHolder.mTextView.setText("[section] == " + indexPath.section + ", [row] == " + indexPath.row);
        } else {
            ViewHolder2 viewHolder = (ViewHolder2) holder;
            viewHolder.mTextView.setText("[section] == " + indexPath.section + ", [row] == " + indexPath.row);
        }

        Log.i("c0ming", "[VH] = " + holder.toString());
    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder holder, IndexPath indexPath) {
        Log.i("c0ming", "[section] == " + indexPath.section + ", [row] == " + indexPath.row);
    }
}
```
