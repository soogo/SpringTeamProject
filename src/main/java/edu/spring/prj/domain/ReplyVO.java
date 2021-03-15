package edu.spring.prj.domain;

import java.util.Date;

public class ReplyVO {
	private String reply_table_name; // �Խ��� �̸�
	private int reply_bno; // �Խñ� ��ȣ
	private int reply_no; // ��� ��ȣ
	private String userid; // ��� �ۼ��� ���̵�
	private String reply_content; // ��� ����
	private Date reply_date; // ��� �ۼ�/���� �ð�

	public ReplyVO() {
	}

	public ReplyVO(String reply_table_name, int reply_bno, int reply_no, String userid, String reply_content,
			Date reply_date) {
		super();
		this.reply_table_name = reply_table_name;
		this.reply_bno = reply_bno;
		this.reply_no = reply_no;
		this.userid = userid;
		this.reply_content = reply_content;
		this.reply_date = reply_date;
	}

	public String getReply_table_name() {
		return reply_table_name;
	}

	public void setReply_table_name(String reply_table_name) {
		this.reply_table_name = reply_table_name;
	}

	public int getReply_bno() {
		return reply_bno;
	}

	public void setReply_bno(int reply_bno) {
		this.reply_bno = reply_bno;
	}

	public int getReply_no() {
		return reply_no;
	}

	public void setReply_no(int reply_no) {
		this.reply_no = reply_no;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getReply_content() {
		return reply_content;
	}

	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}

	public Date getReply_date() {
		return reply_date;
	}

	public void setReply_date(Date reply_date) {
		this.reply_date = reply_date;
	}

	@Override
	public String toString() {
		return "ReplyVO [reply_table_name=" + reply_table_name + ", reply_bno=" + reply_bno + ", reply_no=" + reply_no
				+ ", userid=" + userid + ", reply_content=" + reply_content + ", reply_date=" + reply_date + "]";
	}

	
} // class
