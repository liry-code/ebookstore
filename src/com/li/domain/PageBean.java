package com.li.domain;

import java.io.Serializable;

public class PageBean implements Serializable {
	private static final long serialVersionUID = -4049473527164273817L;

	private int currentPageNum; // 当前页码值
	private int totalRows; // 总行数
	private int perPageRow; // 每页行数
	private int maxPage; // 最大页数
	private static int showNum = 3; // 页面中显示页码个数(奇数)

	public int getCurrentPageNum() {
		return currentPageNum;
	}

	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getPerPageRow() {
		return perPageRow;
	}

	public void setPerPageRow(int perPageRow) {
		this.perPageRow = perPageRow;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getbeginPage() {
		// (1)当MaxNum最大页》》showNum
		// 如果当前页》showNum/2 --->end=first+showNum
		// 如果当前页《showNum/2 --->first=当前页 end=first+showNum
		// 如果当前页》MaxNum-showNum/2 --->first=MaxNum-showNum end=first+showNum
		// int beginpage = this.getCurrentPageNum()-showNum/2;
		// if(beginpage<1){
		// beginpage=1;
		// }

		// （2）当MaxNum最大页《《showNum

		int beginpage = 1;
		if ((this.getCurrentPageNum() <= (showNum / 2 + 1))
				|| (this.getMaxPage() < showNum)) {
			beginpage = 1;
		} else if ((this.getCurrentPageNum() > (showNum / 2 + 1))
				&& (this.getCurrentPageNum() < (this.getMaxPage() - showNum / 2))) {
			beginpage = this.getCurrentPageNum() - showNum / 2;
		} else if (this.getCurrentPageNum() >= (this.getMaxPage() - showNum / 2 - 1)) {
			beginpage = this.getMaxPage() - showNum + 1;
		}
		return beginpage;
	}

	// 1 2 3 4 5 6 7 8 9 10 11 shown=5 shown/2+1=3
	public int getendPage() {
		int endpage = this.getbeginPage() + showNum - 1;
		if (this.getMaxPage() < showNum) {
			endpage = this.getMaxPage();
		}
		return endpage;
	}
}
