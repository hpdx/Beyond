package com.anbetter.beyond.host;

import android.support.v4.app.Fragment;
import android.view.View;

public interface PageFragmentHost {

	/**
	 * 是否要显示顶部的导航栏（ActionBar）
	 *
	 * @param visible
	 */
	void toggleActionBar(boolean visible);

	/**
	 * 是否要显示底部菜单栏
	 * @param visible
	 */
	void toggleTabBar(boolean visible);

	/**
	 * 导航栏上要显示的Title（文本）
	 *
	 * @param title
	 */
	void setActionBarTitle(CharSequence title);

	/**
	 * 导航栏上要显示的Title（图片）
	 *
	 * @param resId
	 */
	void setActionBarImageTitle(int resId);

	/**
	 * 导航栏上是否要显示返回图标
	 *
	 * @param displayBack
	 */
	void displayActionBarBack(boolean displayBack);

	void setHomeAsUpIndicator(int resId);

	void displayActionBarLeftText(CharSequence title, View.OnClickListener listener);

	void displayActionBarRightText(CharSequence title, View.OnClickListener listener);

	void displayActionBarLeftText(CharSequence title, int resId, final View.OnClickListener listener);

	/**
	 * 设置Actionbar上右侧的文字
	 * @param title 文字
	 * @param resId 字体颜色ID
	 * @param listener 点击事件监听器
	 */
	void displayActionBarRightText(CharSequence title, int resId, final View.OnClickListener listener);

	/**
	 * 设置Actionbar上右侧的图标和文字
	 * @param title 文字
	 * @param resId 图标ID
	 * @param listener 点击事件监听器
	 */
	void displayActionBarRightIconText(CharSequence title, int resId, final View.OnClickListener listener);

	void displayActionBarRightIcon(int resId, View.OnClickListener listener);

	void setActionBarLeftTextColor(int resId);

	void setActionBarRightTextColor(int resId);

	void setTitleOnClickListener(View.OnClickListener onClickListener);

	/**
	 * 显示从上往下飘动的提示信息（全局的，可以跨越任何界面而存在）
	 *
	 * @param message String
	 */
	void showGlobalBannerTips(String message);

	/**
	 * 全屏显示
	 */
	void showFullscreen();

	/**
	 * 去掉全屏显示
	 */
	void clearFullscreen();

	/**
	 * 设置软键盘弹起的模式
	 *
	 * @param mode WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
	 *             WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
	 */
	void setSoftInputMode(int mode);

	/**
	 * 获取当前界面的名称
	 * @return
	 */
	String getCurrentPage();

	/**
	 * 返回当前的HomePage
	 * @return
	 */
	Fragment getCurrentHomePage();

	/**
	 * 返回当前back stack count
	 * @return
	 */
	int getBackStackCount();

	/**
	 * statusBar透明还是白色
	 *
	 * @param translucent
	 */
	void translucentStatusBar(boolean translucent);

	/**
	 * 切换发现内容的展示方式
	 */
	void switchDiscoverMod(boolean isVideo);

}
