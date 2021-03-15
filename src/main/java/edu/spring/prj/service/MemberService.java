package edu.spring.prj.service;

import edu.spring.prj.domain.MemberVO;

public interface MemberService {
	// ȸ������
	public abstract int create(MemberVO vo);
	
	// �α���
	public abstract MemberVO login(MemberVO vo);
	
	// Check Ajax ID & Email
	public abstract int checkAjax(MemberVO vo);

	// �α��� ���� ��ȸ
	public abstract MemberVO read(String userid);
	public abstract MemberVO readDetailPersonal(String userid);
	public abstract MemberVO readDetailCompany(String userid);

	// ���� ����
	public abstract int updateDetailPersonal(MemberVO vo);
	public abstract int updateDetailCompany(MemberVO vo);
	
	// �α��� ���� ����
	public abstract int updateLoginInfo(MemberVO vo);
	
	// ���̵� ã��
	public abstract String findId(MemberVO vo);
	
	// ��й�ȣ ã�� (ȸ���˻�)
	public abstract int findPwCheck(MemberVO vo);
		
	// �ӽ� ��й�ȣ (�̸�������) 
	public void newPassword(MemberVO vo);
	
}
