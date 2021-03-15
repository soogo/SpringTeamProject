package edu.spring.prj.domain;

public class ChatVO {
	private int chat_bno;		// chat ������ȣ
	private String chat_content;	// chat ��ȭ����
	private String chat_userid;	// chat ���� ���̵�
	
	public ChatVO() {}
	
	public ChatVO(int chat_bno, String chat_content, String chat_userid) {
		this.chat_bno = chat_bno;
		this.chat_content = chat_content;
		this.chat_userid = chat_userid;
	}
	
	public int getChat_bno() {
		return chat_bno;
	}
	
	public void setChat_bno(int chat_bno) {
		this.chat_bno = chat_bno;
	}
	
	public String getChat_content() {
		return chat_content;
	}
	
	public void setChat_content(String chat_content) {
		this.chat_content = chat_content;
	}
	
	public String getChat_userid() {
		return chat_userid;
	}
	
	public void setChat_userid(String chat_userid) {
		this.chat_userid = chat_userid;
	}
	
	@Override
	public String toString() {
		return "ChatVO [chatBno=" + chat_bno + ", chatContent=" + chat_content + ", chatUserid=" + chat_userid
				+ "]\n";
	}
}
