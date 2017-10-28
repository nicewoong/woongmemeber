package com.aurelhubert.ahbottomnavigation.demo.lounge;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.demo.R;

import java.util.ArrayList;

/**
 *
 */
public class LoungeRecyclerAdapter extends RecyclerView.Adapter<LoungeRecyclerAdapter.ViewHolder> {

	private ArrayList<String> mDataset = new ArrayList<>();

	public static class ViewHolder extends RecyclerView.ViewHolder {
		public TextView mTextView;
		public ViewHolder(View v) {
			super(v);
			mTextView = (TextView) v.findViewById(R.id.layout_item_demo_title);
		}
	}

	public LoungeRecyclerAdapter(ArrayList<String> dataset) {
		mDataset.clear();
		mDataset.addAll(dataset);
	}

	@Override
	public LoungeRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_lounge, parent, false);
		ViewHolder vh = new ViewHolder(v);
		return vh;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		holder.mTextView.setText(mDataset.get(position));

	}

	@Override
	public int getItemCount() {
		return mDataset.size();
	}

}
