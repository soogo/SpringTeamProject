package edu.spring.prj.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.spring.prj.domain.ReplyVO;
import edu.spring.prj.persistence.FreeboardDAO;
import edu.spring.prj.persistence.QAboardDAO;
import edu.spring.prj.persistence.ReplyDAO;
import edu.spring.prj.persistence.ShareboardDAO;

@Service
public class ReplyServiceImple implements ReplyService {
	private static final Logger logger = 
			LoggerFactory.getLogger(ReplyServiceImple.class);

	@Autowired
	private ReplyDAO replyDao;
	
	@Autowired 
	private FreeboardDAO freeboardDao;
	  
	@Autowired 
	private QAboardDAO qaboardDao;
	  
	@Autowired 
	private ShareboardDAO shareboardDao;
	
	// @Transactional
	// - �� ���� �����ͺ��̽� ������ ���� ��
	// ���� ������ ����Ǿ���, �Ʒ� ������ ������ �߻��ϸ�
	// ���� ������ rollback �Ǿ�� �Ѵ�.
	// �̷��� ����� ���ִ� ������̼��� Transactional �̴�.

	@Transactional
	// root-context.xml ���� ������ transactionManager�� weaving

	@Override
	public int create(ReplyVO vo) throws Exception {
		logger.info("Reply create() ȣ��");
		replyDao.insert(vo);
		logger.info("��� �Է� ����");
		logger.info("vo Ȯ�� : " + vo.toString());
		// ��� ī��Ʈ
		if (vo.getReply_table_name().equals("freeboard_table")) {
			logger.info("Reply update count : freeboard");
			freeboardDao.updateReplyCount(1, vo.getReply_bno()); 
		} else if (vo.getReply_table_name().equals("qaboard_table")) {
			logger.info("Reply update count : qaboard");
			qaboardDao.updateReplyCount(1, vo.getReply_bno());
		} else if (vo.getReply_table_name().equals("shareboard_table")) {
			logger.info("Reply update count : shareboard");
			shareboardDao.updateReplyCount(1, vo.getReply_bno());
		} else {
			logger.info("��� ���� �÷��ֱ� ����");
		}
		logger.info("��� ���� ������Ʈ ����");
		return 1;
	}

	@Override
	public List<ReplyVO> read(int reply_bno) {
		logger.info("read() ȣ��");
		return replyDao.select(reply_bno);
	}

	@Override
	public int update(ReplyVO vo) {
		logger.info("update() ȣ��");
		return replyDao.update(vo);
	}

	@Override
	public int delete(int reply_no, int reply_bno, String table_name) {
		logger.info("��� ���� ����");
		replyDao.delete(reply_no);
		logger.info("��� ���� ����");
		logger.info("table_name : " + table_name);
		// ��� ���� ����
		if (table_name.equals("freeboard_table")) {
			logger.info("Reply update count : freeboard");
			freeboardDao.updateReplyCount(-1, reply_bno); 
		} else if (table_name.equals("qaboard_table")) {
			logger.info("Reply update count : qaboard");
			qaboardDao.updateReplyCount(-1, reply_bno);
		} else if (table_name.equals("shareboard_table")) {
			logger.info("Reply update count : shareboard");
			shareboardDao.updateReplyCount(-1, reply_bno);
		} else {
			logger.info("��� ���� ���� ����");
		}
		logger.info("�Խ��� ��� ���� ���� ����");
		return 1;
	}

} // ReplyServiceImple
