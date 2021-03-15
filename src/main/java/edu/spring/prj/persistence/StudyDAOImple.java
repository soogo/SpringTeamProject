package edu.spring.prj.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.prj.domain.StudyVO;
import edu.spring.prj.pageutil.PageCriteria;

@Repository
public class StudyDAOImple implements StudyDAO {

	private static final Logger logger =
			LoggerFactory.getLogger(StudyDAOImple.class);
	
	private static final String NAMESPACE =
			"edu.spring.prj.StudyMapper";
	
	@Autowired
	public SqlSession sqlSession;
	
	
	/* ��� - INSERT */
	@Override
	public int insert(StudyVO vo) {
		logger.info("insert() ȣ��");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}
	
	/* ��ü - SELECT */
	@Override
	public List<StudyVO> select() {
		logger.info("select() ȣ��");
		return sqlSession.selectList(NAMESPACE + ".selectAll");
	}

	/* ���� - SELECT */
	@Override
	public StudyVO select(int study_bno) {
		logger.info("select() ȣ��");
		return sqlSession.selectOne(NAMESPACE + ".selectByBno", study_bno);
	}

	/* ���� - UPDATE */
	@Override
	public int update(StudyVO vo) {
		logger.info("update() ȣ��");
		return sqlSession.update(NAMESPACE + ".update", vo);
	}

	/* ���� - DELETE */
	@Override
	public int delete(int study_bno) {
		logger.info("delete() ȣ��");
		return sqlSession.delete(NAMESPACE + ".delete", study_bno);
	}

	/* ������ ���� Ȯ�� */
	/* ��ü �������� */
	@Override
	public int getTotalNumsOfRecords() {
		logger.info("getTotalNumsOfRecords() ȣ��");
		return sqlSession.selectOne(NAMESPACE + ".totalCount");
	}

	/* ������ ������ŭ�� �Խù� �ҷ����� */
	@Override
	public List<StudyVO> select(PageCriteria criteria) {
		logger.info("select() ȣ�� : page = " + criteria.getPage());
		return sqlSession.selectList(NAMESPACE + ".paging", criteria);
	}

	/* USERID�� �˻� */
	@Override
	public List<StudyVO> select(String studyUserid) {
		logger.info("select() ȣ�� : userid = " + studyUserid);
		studyUserid = "%" + studyUserid + "%";
		return sqlSession.selectList(NAMESPACE + ".selectByUserid", studyUserid);
	}

	/* ����+�������� �˻� */
	@Override
	public List<StudyVO> selectByTitleOrContent(String keyword) {
		logger.info("selectByTitleOrContent() : keyword = " + keyword);
		keyword = "%" + keyword + "%";
		return sqlSession.
		selectList(NAMESPACE + ".selectByTitleContent", keyword);
	}

	/* ��� �˻� */
	@Override
	public List<StudyVO> selectByLocation(String keyword) {
		logger.info("selectByLocation() : keyword = " + keyword);
		keyword = "%" + keyword + "%";
		return sqlSession.selectList(NAMESPACE + ".selectByLocation", keyword);
	}
	
	/* ��ȸ�� ī���� */
	@Override
	public int updateReadCount(int study_bno) {
		logger.info("updateReadcount() ȣ��");
		return sqlSession.update(NAMESPACE + ".updateReadCount", study_bno);
	}
	
	/* �����ο� ������Ʈ */
	// ��� > ����
	@Override
	public int updateCrew(StudyVO vo) {
		logger.info("updateCrewCount() ȣ��");
		return sqlSession.update(NAMESPACE + ".updateToCrewCount", vo);
	}
	
	// ��� �ű�
	@Override
	public int updateCrewWait(StudyVO vo) {
		logger.info("updateCrewWait() ȣ��");
		return sqlSession.update(NAMESPACE + ".updateToCrewWait", vo);
	}
	
	// ����(����)
	@Override
	public int deleteCrew(StudyVO vo) {
		logger.info("deleteCrew() ȣ��");
		return sqlSession.update(NAMESPACE + ".deleteToCrewCount", vo);
	}
	
	// ���(����)
	@Override
	public int deleteWait(StudyVO vo) {
		logger.info("deleteWait() ȣ��");
		return sqlSession.update(NAMESPACE + ".deleteToCrewWait", vo);
	}
}
