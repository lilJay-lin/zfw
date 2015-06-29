//package com.mimi.zfw.util.pageUtil;
//
//import java.util.List;
//
//public class PageUtil {
//	/**
//	 * 不关心总记录数
//	 * 
//	 * @param pageNumber
//	 * @param pageSize
//	 * @return
//	 */
//	public static int getPageStart(int pageNumber, int pageSize) {
//		return pageNumber * pageSize;
//	}
//
//	/**
//	 * 计算分页获取数据时游标的起始位置
//	 * 
//	 * @param totalCount
//	 *            所有记录总和
//	 * @param pageNumber
//	 *            页码,从1开始
//	 * @return
//	 */
//	public static int getPageStart(int totalCount, int pageNumber, int pageSize) {
//		int start = getPageStart(pageNumber, pageSize);
//		if (start >= totalCount) {
//			start = 0;
//		}
//		return start;
//	}
//
//	public static <E> CommonPageObject<E> getPage(int totalCount,
//			int pageNumber, List<E> items, int pageSize) {
//		CommonPageObject<E> page = new CommonPageObject<E>();
//		page.setItemTotalNum(totalCount);
//		page.setCurPage(pageNumber);
//		page.setPageSize(pageSize);
//		page.setItems(items);
//		return page;
//	}
//
//}
