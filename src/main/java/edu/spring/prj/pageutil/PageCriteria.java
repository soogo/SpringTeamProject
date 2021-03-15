package edu.spring.prj.pageutil;

//���������� ������ ������ ��ȣ��
//�� ���������� ������ �Խñ��� ������ �����ϴ� Ŭ����
//-> paging ó���� �ʿ��� start�� end ��ȣ�� �� �� ����
public class PageCriteria {
	private int page; // ���� ������ ��ȣ
	private int numsPerPage; // �� �������� �Խñ� ����
	private String userid; // For Bookmark
	private String searchType; // For searching
	private String keyword; // For searching
	
	public PageCriteria() {
		this.page = 1;
		this.numsPerPage = 10;
	}
	
	public PageCriteria(int page, int numsPerPage) {
		this.page = page;
		this.numsPerPage = numsPerPage;
	}

	// getter/setter
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

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	
} // PageCriteria