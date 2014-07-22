package com.tata.main;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tata.main.ExpandableMenuListView.OnMenuItemClickListener;

public class MainActivity extends Activity implements OnMenuItemClickListener {

	ArrayList<ViewModel> listModels;
	MyAdapter adapter;
	ExpandableMenuListView mExpandableListView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initListData();
		initListView();
	}

	/**
	 * 初始化数据
	 */
	private void initListView() {
		mExpandableListView = (ExpandableMenuListView) findViewById(R.id.listView1);
		adapter = new MyAdapter(this, listModels);
		mExpandableListView.setAdapter(adapter);
		mExpandableListView.setOnMenuItemClickListener(this);
	}

	/**
	 * 得到数据
	 * 
	 * @return
	 */
	private void initListData() {
		ArrayList<ViewModel> listModel = new ArrayList<ViewModel>();
		for (int i =0 ; i<20 ; i++) {
			listModel.add(new ViewModel("item"+i, false));
		}
		listModels = listModel;
	}

	@Override
	public void onMenuItemClick(View v, int pos) {
		switch (v.getId()) {
		case R.id.btn_menu_ok:
			t("OK");
			break;
		case R.id.btn_menu_cancel:
			t("cancel");
			break;
		case R.id.btn_menu_exit:
			t("exit");
			break;
		default:
			break;
		}
	}

	void t(String s) {
		Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
	}

}