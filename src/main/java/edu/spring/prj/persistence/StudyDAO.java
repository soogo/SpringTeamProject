package edu.spring.prj.persistence;

import java.util.List;

import edu.spring.prj.domain.StudyVO;
import edu.spring.prj.pageutil.PageCriteria;

public interface StudyDAO {
	// �⺻ ���
	public abstract List<StudyVO> select();
	public abstract StudyVO select(int study_bno);
	public abstract int insert(StudyVO vo);
	public abstract int update(StudyVO vo);
	public abstract int delete(int study_bno);
	
	// ������ ���� Ȯ�ο�
	public abstract int getTotalNumsOfRecords();
	public abstract List<StudyVO> select(PageCriteria criteria);
	
	// �ۼ��� �˻�
	public abstract List<StudyVO> select(String userid);
	
	// ���� + �������� �˻�
	public abstract List<StudyVO> selectByTitleOrContent(String keyword);

	// ��� �˻�
	public abstract List<StudyVO> selectByLocation(String keyword);
	
	// ��ȸ�� ī����
	public abstract int updateReadCount(int study_bno);
	
	// �����ο� ������Ʈ
	// ����� > �������
	public abstract int updateCrew(StudyVO vo);
	// ����� �ű� ���
	public abstract int updateCrewWait(StudyVO vo);
	// ������ܿ��� ����
	public abstract int deleteCrew(StudyVO vo);
	// ����ܿ��� ����
	public abstract int deleteWait(StudyVO vo);
}
