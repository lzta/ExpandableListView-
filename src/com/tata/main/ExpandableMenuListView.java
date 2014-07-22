package com.tata.main;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;


/**
 * 
 * 
 * 名称：ExpandableMenuListView
 * 
 * 描述：自定义弹出菜单listview
 * 
 * 修改时间：2013-12-3 下午5:32:52
 * 
 * @author tata
 * 
 */
public class ExpandableMenuListView extends ListView implements
		android.widget.AdapterView.OnItemClickListener {

	private static final String TAG = "ExpandableMenuListView";
	OnMenuItemClickListener onMenuItemClickListener;
	private Context context;

	/**
	 * 设置菜单事件监听
	 * 
	 * @param listener
	 * @modifiedTime 上午10:24:57
	 * @author lzt
	 */
	public void setOnMenuItemClickListener(OnMenuItemClickListener listener) {
		this.onMenuItemClickListener = listener;
	}

	public ExpandableMenuListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
		this.context = context;
	}

	public ExpandableMenuListView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init(context);
		this.context = context;
	}

	public ExpandableMenuListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
		this.context = context;
	}

	private LayoutInflater mInflater;

	private LinearLayout mMenu;
	private Button mMenuButtonOK;
	private Button mMenuButtonCancel;
	private Button mMenuButtonExit;

	private ViewStub mMenuStub; // 用于显示自定义菜单的控件
	private static final int mInflateId = 0x0000018;
	private static final int mStubId = 0x0000019;
	onMenuButtonClickListener mMenuBtnClickListener;

	private void init(Context context) {
		mInflater = LayoutInflater.from(context);
		initViewStubMenu();
		super.setOnItemClickListener(this);
		mCurrentState = MENU_CLOSED;
	}

	private void setButtonMenuListener(View v, int pos) {
		// bind view
		mMenuButtonOK = (Button) v.findViewById(R.id.btn_menu_ok);
		mMenuButtonCancel = (Button) v.findViewById(R.id.btn_menu_cancel);
		mMenuButtonExit = (Button) v.findViewById(R.id.btn_menu_exit);
		// set listener
		mMenuButtonOK.setOnClickListener(new onMenuButtonClickListener(pos));
		mMenuButtonCancel
				.setOnClickListener(new onMenuButtonClickListener(pos));
		mMenuButtonExit.setOnClickListener(new onMenuButtonClickListener(pos));
	}

	private ViewStub initViewStubMenu() {
		ViewStub mMenuStub = new ViewStub(context, R.layout.view_list_menu);
		mMenuStub.setId(mStubId);
		mMenuStub.setInflatedId(mInflateId);
		return mMenuStub;
	}

	class onMenuButtonClickListener implements OnClickListener {

		private int pos;

		public onMenuButtonClickListener(int pos) {
			super();
			this.pos = pos;
		}

		@Override
		public void onClick(View v) {
			if (onMenuItemClickListener != null) {
				onMenuItemClickListener.onMenuItemClick(v,pos);
			}
		}

	}

	private int mOldPos = -1;// record the old menu 's position
	private int mCurrentState; // record the current menu's state
	private int mOldState;// record the old menu's state
	private static final int MENU_CLOSED = 0;// menu closed
	private static final int MENU_OPENED = 1;// menu opened

	boolean hasAttached = true; // tag
	ViewGroup itemView = null;

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		final int mCurPos = position;
		ViewGroup mItemView = (ViewGroup) view;
		this.itemView = mItemView;
		if (mItemView.findViewById(mInflateId) == null) {
			mMenuStub = initViewStubMenu();
			mItemView.addView(mMenuStub);
		}
		mMenuStub = (ViewStub) mItemView.findViewById(mStubId);
		if (mMenuStub != null && mMenuStub.getTag() == null) {
			View inflateView = mMenuStub.inflate();
			setButtonMenuListener(inflateView, mCurPos);
			mMenuStub.setTag(hasAttached);
		}
		if (mOldPos == -1) {
			mOldPos = position;
		}
		// Close the previous menu if it has not been closed.
		if (mCurPos != mOldPos && mOldState == MENU_OPENED) {
			if (getChildAt(mOldPos) != null) {
				View oldView = getChildAt(mOldPos).findViewById(mInflateId);
				if (oldView != null) {
					oldView.setVisibility(View.GONE);
				}
			}
			mOldState = MENU_CLOSED;
			mCurrentState = MENU_CLOSED;
			mOldPos = position;
		}
		// show or hide
		if (mCurrentState == MENU_CLOSED) {
			showItemView(true);
			mCurrentState = MENU_OPENED;
			mOldState = MENU_OPENED;
			mOldPos = mCurPos;
		} else {
			showItemView(false);
			mCurrentState = MENU_CLOSED;
			mOldState = MENU_CLOSED;
		}
	}

	/**
	 * show
	 * 
	 * @param isShow
	 * @modifiedTime 下午5:36:28
	 * @author lzt
	 */
	private void showItemView(boolean isShow) {
		if (isShow) {
			this.itemView.findViewById(mInflateId).setVisibility(View.VISIBLE);
		} else {
			this.itemView.findViewById(mInflateId).setVisibility(View.GONE);
		}
	}
	
	interface OnMenuItemClickListener {
		/**
		 * 
		 *@param v
		 * @param pos TODO
		 *@modifiedTime 下午5:39:13
		 *@author lzt
		 */
		void onMenuItemClick(View v, int pos);
	}
}
