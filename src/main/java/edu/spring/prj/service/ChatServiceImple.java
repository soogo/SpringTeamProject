package edu.spring.prj.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.prj.domain.ChatVO;
import edu.spring.prj.persistence.ChatDAO;

@Service
public class ChatServiceImple implements ChatService {
	
	private static final Logger logger =
			LoggerFactory.getLogger(ChatServiceImple.class);
	
	@Autowired
	private ChatDAO dao;
	
	@Override
	public int create(ChatVO vo) {
		logger.info("create() ȣ��");
		return dao.insert(vo);
	}

	@Override
	public ChatVO read(String chat_userid) {
		logger.info("read() ȣ��");
		return dao.select(chat_userid);
	}
	
	@Override
	public ChatVO read(int chat_bno) {
		logger.info("read() ȣ��");
		return dao.select(chat_bno);
	}

	@Override
	public int update(ChatVO vo) {
		logger.info("update() ȣ��");
		return dao.update(vo);
	}
	
	@Override
	public ChatVO readDesc() {
		logger.info("readDesc() ȣ��");
		return dao.selectDesc();
	}

}
