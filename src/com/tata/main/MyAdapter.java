package com.tata.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	private Context context;
	public ArrayList<ViewModel> listModel;
	private LayoutInflater inflater;
	public HashMap<Integer, Boolean> isCheckedMap;
	private boolean isAllChecked;

	public MyAdapter(Context context, ArrayList<ViewModel> listModel) {
		super();
		this.context = context;
		this.listModel = listModel;
		inflater = LayoutInflater.from(context);
		isCheckedMap = new HashMap<Integer, Boolean>();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listModel.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listModel.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = convertView;
		final int pos = position;
		final ViewHolder viewHolder;
		if (view == null) {
			viewHolder = new ViewHolder();
			view = inflater.inflate(R.layout.list_item, null);
			viewHolder.textView = (TextView) view.findViewById(R.id.textView1);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		viewHolder.textView.setText(listModel.get(position).getText());
		return view;
	}

	static class ViewHolder {
		public TextView textView;
	}
}
