package edu.spring.prj.persistence;

import edu.spring.prj.domain.ChatVO;

public interface ChatDAO {
	// ���
	public abstract int insert(ChatVO vo);
	
	// �˻�(���̵��)
	public abstract ChatVO select(String chat_userid);
	
	// �˻�(��ȣ��)
	public abstract ChatVO select(int chat_bno);
	
	// ����
	public abstract int update(ChatVO vo);
	
	// �ֱ� ���� ��������
	public abstract ChatVO selectDesc();
}
