package edu.spring.prj.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.prj.domain.ShareboardVO;
import edu.spring.prj.pageutil.PageCriteria;
import edu.spring.prj.persistence.ShareboardDAO;

@Service
public class ShareboardServiceImple implements ShareboardService {
	private static final Logger logger = LoggerFactory.getLogger(ShareboardServiceImple.class);

	@Autowired
	private ShareboardDAO dao;

	@Override
	public List<ShareboardVO> read() {
		logger.info("Service read() ȣ��");
		return dao.select();
	}

	@Override
	public ShareboardVO read(int bno) {
		logger.info("Service read() ȣ�� : bno = " + bno);
		return dao.select(bno);
	}

	@Override
	public int create(ShareboardVO vo) {
		logger.info("Service create() ȣ��");
		return dao.insert(vo);
	}

	@Override
	public int update(ShareboardVO vo) {
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
	public List<ShareboardVO> read(PageCriteria criteria) {
		logger.info("Service read() ȣ�� : page = " + criteria.getPage());
		return dao.select(criteria);
	}

	@Override
	public List<ShareboardVO> selectNotice() {
		logger.info("Service notice() ȣ��");
		return dao.selectNotice();
	}

}
