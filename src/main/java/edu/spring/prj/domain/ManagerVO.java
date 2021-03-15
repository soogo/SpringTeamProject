package edu.spring.prj.domain;

import java.util.Arrays;

public class ManagerVO {
	private String[] searchPart;
	private String searchColumn;
	private String searchBlacklist;
	private String keyword;
	
	private int page; // ���� ������ ��ȣ
	private int numsPerPage; // �� �������� �Խñ� ����
	
	public ManagerVO() {
		this.page = 1;
		this.numsPerPage = 10;
	}

	public ManagerVO(int page, int numsPerPage) {
		this.page = page;
		this.numsPerPage = numsPerPage;
	}

	public ManagerVO(String[] searchPart, String searchColumn, String searchBlacklist, String keyword, int page,
			int numsPerPage) {
		super();
		this.searchPart = searchPart;
		this.searchColumn = searchColumn;
		this.searchBlacklist = searchBlacklist;
		this.keyword = keyword;
		this.page = page;
		this.numsPerPage = numsPerPage;
	}

	public String[] getSearchPart() {
		return searchPart;
	}

	public void setSearchPart(String[] searchPart) {
		this.searchPart = searchPart;
	}

	public String getSearchColumn() {
		return searchColumn;
	}

	public void setSearchColumn(String searchColumn) {
		this.searchColumn = searchColumn;
	}

	public String getSearchBlacklist() {
		return searchBlacklist;
	}

	public void setSearchBlacklist(String searchBlacklist) {
		this.searchBlacklist = searchBlacklist;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getNumsPerPage() {
		return numsPerPage;
	}

	public void setNumsPerPage(int numsPerPage) {
		this.numsPerPage = numsPerPage;
	}
	
	// ���� �������� �������� ���� �� �Ϸù�ȣ(rn)
	public int getStart() {
		return (this.page - 1) * this.numsPerPage + 1;
	}
	
	// ���� �������� �������� ������ �� �Ϸù�ȣ(rn)
	public int getEnd() {
		return this.page * this.numsPerPage;
	}

	@Override
	public String toString() {
		return "ManagerVO [searchPart=" + Arrays.toString(searchPart) + ", searchColumn=" + searchColumn
				+ ", searchBlacklist=" + searchBlacklist + ", keyword=" + keyword + ", page=" + page + ", numsPerPage="
				+ numsPerPage + "]";
	}



} // MemberVO
