package edu.spring.prj.service;

import java.util.List;

import edu.spring.prj.domain.FreeboardVO;
import edu.spring.prj.domain.ManagerVO;
import edu.spring.prj.domain.MemberVO;

public interface ManagerService {
	// ������ ����
	public abstract int updateNotice(FreeboardVO vo);
	// ������Ʈ �߰�
	public abstract int updateBlacklist(MemberVO vo);
	// ȸ�� ������
	public abstract MemberVO detail(MemberVO vo);
	// ȸ���˻�
	public abstract List<MemberVO> searchMember(ManagerVO vo);
	// ȸ���˻� ����¡
	public abstract int getTotalNumsOfSearching(ManagerVO vo);
	// ȸ������
	public abstract int delete(String userid);
	// �����ڵ��
	public abstract int registerManager(MemberVO vo);
	
}