package com.wiser.library.manager.activity;

import java.util.List;

import com.wiser.library.base.WISERActivity;
import com.wiser.library.model.WISERActivityModel;

/**
 * @author Wiser
 * @version 版本
 */
public interface IWISERActivityManage {

	/**
	 * 新建了一个activity
	 *
	 * @param model
	 */
	void addActivity(WISERActivityModel model);

	/**
	 * 结束指定的Activity
	 *
	 * @param model
	 */
	void finishActivity(WISERActivityModel model);

	/**
	 * 结束所有的Activity
	 */
	void finishAllActivity();

	/**
	 * 应用退出，结束所有的activity
	 */
	void exitFinishAllActivity();

	/**
	 * 结束指定类名的Activity
	 */
	void finishActivityClass(Class<?> cls);

	/**
	 * 结束除了某一个activity的所有activity
	 *
	 * @param cls
	 */
	void finishAllActivityExceptClass(Class<?> cls);

	/**
	 * 获取当前显示的Activity
	 * 
	 * @param <T>
	 * @return
	 */
	<T extends WISERActivity> T getCurrentActivity();

	/**
	 * 获取当前运行的
	 * 
	 * @param <T>
	 * @return
	 */
	<T extends WISERActivity> T getCurrentIsRunningActivity();

	/**
	 * 暂停
	 */
	void onPause(WISERActivity activity);

	/**
	 * 运行
	 */
	void onResume(WISERActivity activity);

	/**
	 * Activity集合size
	 */
	int size();

	/**
	 * 获取Activity集合对象
	 * 
	 * @return
	 */
	List<WISERActivityModel> getActivityModels();

	/**
	 * 打印Activity集合
	 */
	void logActivityList();

}
