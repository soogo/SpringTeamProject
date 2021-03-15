package edu.spring.prj.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.prj.domain.StudyVO;
import edu.spring.prj.pageutil.PageCriteria;
import edu.spring.prj.persistence.StudyDAO;

@Service
public class StudyServiceImple implements StudyService{

	private static final Logger logger =
			LoggerFactory.getLogger(StudyServiceImple.class);
	
	@Autowired
	private StudyDAO dao;
	
	
	@Override
	public List<StudyVO> read() {
		logger.info("read() ȣ��");
		return dao.select();
	}

	@Override
	public StudyVO read(int study_bno) {
		logger.info("read() ȣ�� study_bno : " + study_bno);
		return dao.select(study_bno);
	}

	@Override
	public int create(StudyVO vo) {
		logger.info("create() ȣ��");
		return dao.insert(vo);
	}

	@Override
	public int update(StudyVO vo) {
		logger.info("update() ȣ��");
		return dao.update(vo);
	}

	@Override
	public int delete(int studyBno) {
		logger.info("delete() ȣ��");
		return dao.delete(studyBno);
	}

	@Override
	public int getTotalNumsofRecords() {
		logger.info("getTotalNumsOfRecords() ȣ��");
		return dao.getTotalNumsOfRecords();
	}

	@Override
	public List<StudyVO> read(PageCriteria criteria) {
		logger.info("read() ȣ�� : page = " + criteria.getPage());
		return dao.select(criteria);
	}
	
	/* USERID�� �˻� */
	@Override
	public List<StudyVO> read(String chat_userid) {
		logger.info("read() ȣ�� : study_userid = " + chat_userid);
		return dao.select(chat_userid);
	}
	
	/* ���� + �������� �˻� */
	@Override
	public List<StudyVO> readByKeyword(String keyword) {
		logger.info("read(keyword) ȣ�� : keyword = " + keyword);
		return dao.selectByTitleOrContent(keyword);
	}

	/* ��� �˻� */
	@Override
	public List<StudyVO> readByLocation(String keyword) {
		logger.info("read(location) ȣ�� : keyword = " + keyword);
		return dao.selectByLocation(keyword);
	}
	
	/* ��ȸ�� ī���� */
	@Override
	public int updateReadCount(int study_bno) {
		logger.info("updateReadcount() ȣ��");
		return dao.updateReadCount(study_bno);
	}
	
	/* �����ο� ī���� */
	// ��� > ����
	@Override
	public int updateCrew(StudyVO vo){
		// ��û�ڼ� ����
		logger.info("updateCrew() ȣ��");
		return dao.updateCrew(vo);
	}
	
	// > ���
	@Override
	public int updateCrewWait(StudyVO vo) {
		logger.info("updateCrewWait() ȣ��");
		return dao.updateCrewWait(vo);
	}
	
	// ����(����)
	@Override
	public int deleteCrew(StudyVO vo) {
		logger.info("deleteCrew() ȣ��");
		return dao.deleteCrew(vo);
	}
	
	// ���(����)
	@Override
	public int deleteCrewWait(StudyVO vo) {
		logger.info("deleteCrewWait() ȣ��");
		return dao.deleteWait(vo);
	}
}
