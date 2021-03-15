package edu.spring.prj.persistence;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.prj.domain.ChatVO;

@Repository
public class ChatDAOImple implements ChatDAO {

	private static final Logger logger =
			LoggerFactory.getLogger(ChatDAOImple.class);
	
	private static final String NAMESPACE =
			"edu.spring.prj.ChatMapper";
	
	@Autowired
	public SqlSession sqlsession;
	
	// ���
	@Override
	public int insert(ChatVO vo) {
		logger.info("insert() ȣ��");
		return sqlsession.insert(NAMESPACE + ".chatInsert", vo);
	}

	// �ϳ� ��������(���̵��)
	@Override
	public ChatVO select(String chat_userid) {
		logger.info("select() ȣ�� chatUserid - " + chat_userid);
		return sqlsession.selectOne(NAMESPACE + ".chatSelectByUserid", chat_userid);
	}
	
	// ��ȣ�� �ϳ� ��������
	@Override
	public ChatVO select(int chat_bno)  {
		logger.info("select() ȣ�� chatBno - " + chat_bno);
		return sqlsession.selectOne(NAMESPACE + ".chatSelectByBno", chat_bno);
	}

	// ����
	// TODO ������ bno�� �ؾ߰ڴ�!
	// TODO layout-default > �޽��� �ڵ鸵 �����ؾ��Ѵ�!
	@Override
	public int update(ChatVO vo) {
		logger.info("update() ȣ�� vo - " + vo.toString());
		return sqlsession.update(NAMESPACE + ".chatUpdate", vo);
	}
	
	// ���� �ֱ� ���� ��������
	@Override
	public ChatVO selectDesc() {
		logger.info("selectDesc() ȣ��");
		return sqlsession.selectOne(NAMESPACE + ".chatSelectByDesc");
	}

}
