package edu.spring.prj.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.prj.domain.ReplyVO;


@Repository
public class ReplyDAOImple implements ReplyDAO {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReplyDAOImple.class);
	private static final String NAMESPACE = "edu.spring.prj.ReplyMapper";
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(ReplyVO vo) {
		LOGGER.info("insert() ȣ��");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<ReplyVO> select(int reply_bno) {
		LOGGER.info("select() ȣ�� reply_bno : " + reply_bno);
		return sqlSession.selectList(NAMESPACE + ".select_all_by_reply_bno", reply_bno);
	}

	@Override
	public int update(ReplyVO vo) {
		LOGGER.info("update() ȣ��");
		return sqlSession.update(NAMESPACE + ".update", vo);
	}

	@Override
	public int delete(int replyNo) {
		LOGGER.info("delete() ȣ�� : replyNo = " + replyNo);
		return sqlSession.delete(NAMESPACE + ".delete", replyNo);
	}

} // ReplyDAOImple
