package edu.spring.prj.persistence;

import edu.spring.prj.domain.MemberVO;

public interface MemberDAO {
	// ȸ������
	public abstract int insert(MemberVO vo);
	
	// �α���
	public abstract MemberVO login(MemberVO vo);
	
	// Check Ajax ID & Email
	public abstract int checkAjax(MemberVO vo);

	// �α��� ���� ��ȸ
	public abstract MemberVO select(String userid);
	public abstract MemberVO selectDetailPersonal(String userid);
	public abstract MemberVO selectDetailCompany(String userid);
	
	// ���� ����
	public abstract int updateDetailPersonal(MemberVO vo);
	public abstract int updateDetailCompany(MemberVO vo);
	
	// �α������� ����
	public abstract int updateLoginInfo(MemberVO vo);

	// ���̵� ã��
	public abstract String findId(MemberVO vo);
	
	// ��й�ȣ ã�� (ȸ���˻�)
	public abstract int findPwCheck(MemberVO vo);
	
	// �ӽ� ��й�ȣ (�̸�������)
	public void newPassword(MemberVO vo);

}