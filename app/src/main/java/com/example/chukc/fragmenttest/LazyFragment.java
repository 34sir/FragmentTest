package com.example.chukc.fragmenttest;

import android.support.v4.app.Fragment;

/**
 * 懒加载fragment
 * @author congmf
 *
 */
public abstract class LazyFragment extends Fragment {

	protected boolean isVisible;

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);

		if (getUserVisibleHint()) {
			isVisible = true;
			onVisible();
		} else {
			isVisible = false;
			onInvisible();
		}
	}

	protected void onVisible() {
		lazyLoad();
	}

	protected abstract void lazyLoad();

	protected void onInvisible() {

	}

}
