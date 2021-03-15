package edu.spring.prj.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.prj.domain.MemberVO;
import edu.spring.prj.persistence.MemberDAO;

@Service
public class MemberServiceImple implements MemberService {
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImple.class);

	@Autowired
	private MemberDAO dao;

	
	@Override
	public int create(MemberVO vo) {
		logger.info("Service create() ȣ��");
		return dao.insert(vo);
	}
	
	@Override
	public MemberVO login(MemberVO vo) {
		logger.info("Service login() ȣ��");
		return dao.login(vo);
	}
	
	@Override
	public int checkAjax(MemberVO vo) {
		logger.info("Service checkAjax() ȣ��");
		return dao.checkAjax(vo);
	}
	
	@Override
	public MemberVO read(String userid) {
		logger.info("Service read() ȣ�� : userid = " + userid);
		return dao.select(userid);
	}
	
	@Override
	public MemberVO readDetailPersonal(String userid) {
		logger.info("Service readDetailPersonal() ȣ�� : userid = " + userid);
		return dao.selectDetailPersonal(userid);
	}

	@Override
	public MemberVO readDetailCompany(String userid) {
		logger.info("Service readDetailCompany() ȣ�� : userid = " + userid);
		return dao.selectDetailCompany(userid);
	}
	
	@Override
	public int updateDetailPersonal(MemberVO vo) {
		logger.info("Service updateDetailPersonal() ȣ��");
		return dao.updateDetailPersonal(vo);
	}

	@Override
	public int updateDetailCompany(MemberVO vo) {
		logger.info("Service updateDetailCompany() ȣ��");
		return dao.updateDetailCompany(vo);
	}

	@Override
	public int updateLoginInfo(MemberVO vo) {
		logger.info("Service updateLoginInfo() ȣ��");
		return dao.updateLoginInfo(vo);
	}

	@Override
	public String findId(MemberVO vo) {
		logger.info("Service findId() ȣ��");
		return dao.findId(vo);
	}

	@Override
	public int findPwCheck(MemberVO vo) {
		logger.info("Service findPwCheck() ȣ�� : ���ã�� ȸ���˻�");
		return dao.findPwCheck(vo);
	}
	
	@Override
	public void newPassword(MemberVO vo) {
		logger.info("Service newPassword() ȣ�� : �ӽú�й�ȣ ����");
		dao.newPassword(vo);
	}

} // MemberServiceImple