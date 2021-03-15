package edu.spring.prj.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.prj.domain.QAboardVO;
import edu.spring.prj.pageutil.PageCriteria;
import edu.spring.prj.persistence.QAboardDAO;

@Service
public class QAboardServiceImple implements QAboardService {
	private static final Logger logger = LoggerFactory.getLogger(QAboardServiceImple.class);

	@Autowired
	private QAboardDAO dao;

	@Override
	public List<QAboardVO> read() {
		logger.info("Service read() ȣ��");
		return dao.select();
	}

	@Override
	public QAboardVO read(int bno) {
		logger.info("Service read() ȣ�� : bno = " + bno);
		return dao.select(bno);
	}

	@Override
	public int create(QAboardVO vo) {
		logger.info("Service create() ȣ��");
		return dao.insert(vo);
	}

	@Override
	public int update(QAboardVO vo) {
		logger.info("Service update() ȣ��");
		return dao.update(vo);
	}

	@Override
	public int delete(int bno) {
		logger.info("Service delete() ȣ��");
		return dao.delete(bno);
	}

	@Override
	public int getTotalNumsOfRecords(PageCriteria criteria) {
		logger.info("Service getTotalNumsOfRecords() ȣ��");
		return dao.getTotalNumsOfRecords(criteria);
	}

	@Override
	public List<QAboardVO> read(PageCriteria criteria) {
		logger.info("Service read() ȣ�� : page = " + criteria.getPage());
		return dao.select(criteria);
	}

	@Override
	public List<QAboardVO> selectNotice() {
		logger.info("Service notice() ȣ��");
		return dao.selectNotice();
	}

}
