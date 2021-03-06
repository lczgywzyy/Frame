package com.wiser.library.tab.top;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com.wiser.library.R;
import com.wiser.library.helper.WISERHelper;
import com.wiser.library.view.smart.SmartTabLayout;

/**
 * @author Wiser
 * 
 *         smart page
 */
public class SmartPageView extends ViewPager {

	private int								currentPage;

	private SmartTabAdapter					tabAdapter;

	private boolean							isResetHeight	= false;

	private OnSmartTabPageChangeListener	onSmartTabPageChangeListener;

	private int								smartId;

	private SmartTabLayout					mSmartTabLayout;

	public SmartPageView(@NonNull Context context) {
		super(context);
	}

	public SmartPageView(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		initTypedArray(attrs);
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public SmartTabAdapter getTabAdapter() {
		return tabAdapter;
	}

	public SmartTabLayout smartTabLayout() {
		return mSmartTabLayout;
	}

	public int getSmartId() {
		return smartId;
	}

	// 初始化
	private void initTypedArray(AttributeSet attrs) {
		TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SmartPageView);
		if (typedArray != null) {
			smartId = typedArray.getResourceId(R.styleable.SmartPageView_smartTabId, NO_ID);
			isResetHeight = typedArray.getBoolean(R.styleable.SmartPageView_isResetPageHeight, false);
			typedArray.recycle();
		}
	}

	// 初始化ViewPager
	public SmartPageView setPage(SmartTabInfo... mSmartTabInfos) {
		tabAdapter = new SmartTabAdapter(WISERHelper.getActivityManage().getCurrentActivity().getSupportFragmentManager(), getContext(), mSmartTabInfos);
		setAdapter(tabAdapter);
		setSmartId();
		addOnPageChangeListener(new OnPageChangeListener() {

			@Override public void onPageScrolled(int i, float v, int i1) {
				if (onSmartTabPageChangeListener != null) onSmartTabPageChangeListener.onPageScrolled(i, v, i1);
			}

			@Override public void onPageSelected(int i) {
				if (isResetHeight) resetHeight(i);
				if (onSmartTabPageChangeListener != null) onSmartTabPageChangeListener.onPageSelected(i);
			}

			@Override public void onPageScrollStateChanged(int i) {
				if (onSmartTabPageChangeListener != null) onSmartTabPageChangeListener.onPageScrollStateChanged(i);
			}
		});
		return this;
	}

	// 初始化ViewPager
	public SmartPageView setPage(FragmentManager fm, SmartTabInfo... mSmartTabInfos) {
		tabAdapter = new SmartTabAdapter(fm, getContext(), mSmartTabInfos);
		setAdapter(tabAdapter);
		setSmartId();
		addOnPageChangeListener(new OnPageChangeListener() {

			@Override public void onPageScrolled(int i, float v, int i1) {
				if (onSmartTabPageChangeListener != null) onSmartTabPageChangeListener.onPageScrolled(i, v, i1);
			}

			@Override public void onPageSelected(int i) {
				if (isResetHeight) resetHeight(i);
				if (onSmartTabPageChangeListener != null) onSmartTabPageChangeListener.onPageSelected(i);
			}

			@Override public void onPageScrollStateChanged(int i) {
				if (onSmartTabPageChangeListener != null) onSmartTabPageChangeListener.onPageScrollStateChanged(i);
			}
		});
		return this;
	}

	public void setSmartId(int smartId) {
		if (this.smartId == NO_ID) {
			this.smartId = smartId;
		}
		if (getRootView() == null) return;
		mSmartTabLayout = getRootView().findViewById(this.smartId);
		if (mSmartTabLayout != null) mSmartTabLayout.setViewPager(this);
	}

	public void setSmartId() {
		if (this.smartId == NO_ID) {
			return;
		}
		if (getRootView() == null) return;
		mSmartTabLayout = getRootView().findViewById(this.smartId);
		if (mSmartTabLayout != null) mSmartTabLayout.setViewPager(this);
	}

	@Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if (isResetHeight) {
			if (getChildCount() > 0 && getChildCount() > currentPage) {
				View child = getChildAt(currentPage);
				child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
				int h = child.getMeasuredHeight();
				heightMeasureSpec = MeasureSpec.makeMeasureSpec(h, MeasureSpec.EXACTLY);
			}
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		} else {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		}
	}

	/**
	 * 在切换tab的时候，重置ViewPager的高度
	 *
	 * @param current
	 */
	public void resetHeight(int current) {
		this.currentPage = current;
		MarginLayoutParams params = (MarginLayoutParams) getLayoutParams();
		setLayoutParams(params);
	}

	public void setOnSmartTabPageChangeListener(OnSmartTabPageChangeListener onSmartTabPageChangeListener) {
		this.onSmartTabPageChangeListener = onSmartTabPageChangeListener;
	}

	public interface OnSmartTabPageChangeListener {

		void onPageScrolled(int i, float v, int i1);

		void onPageSelected(int i);

		void onPageScrollStateChanged(int i);
	}
}
