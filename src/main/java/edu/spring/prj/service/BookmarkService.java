package edu.spring.prj.service;

import java.util.List;

import edu.spring.prj.domain.BookmarkVO;
import edu.spring.prj.domain.EmpBoardVO;
import edu.spring.prj.domain.FreeboardVO;
import edu.spring.prj.domain.QAboardVO;
import edu.spring.prj.domain.RecBoardVO;
import edu.spring.prj.domain.ShareboardVO;
import edu.spring.prj.pageutil.PageCriteria;

public interface BookmarkService {
	// �ϸ�ũ �Խ��� ����
	public abstract int delete(int bno);
	// ������
	public abstract int getTotalNumsOfRecords(String userid);
	public abstract List<BookmarkVO> read(PageCriteria criteria);
	// �ϸ�ũ ajax �ߺ�Ȯ�� 
	public abstract int check(BookmarkVO vo);
	// �ϸ�ũ ajax �Է�
	public abstract int ajaxInsert(BookmarkVO vo);
	// �ϸ�ũ ajax ����
	public abstract int ajaxDelete(BookmarkVO vo);
	// ���� ��������
	public abstract FreeboardVO freeboardTitle(BookmarkVO vo);
	public abstract QAboardVO qaboardTitle(BookmarkVO vo);
	public abstract ShareboardVO shareboardTitle(BookmarkVO vo);
	public abstract RecBoardVO recruitTitle(BookmarkVO vo);
	public abstract EmpBoardVO employTitle(BookmarkVO vo);
	 
}