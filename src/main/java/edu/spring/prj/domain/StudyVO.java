package edu.spring.prj.domain;

import java.util.Date;

public class StudyVO {
	private int study_bno; 			// ������ȣ
	private String study_userid; 	// �ۼ���
	private String study_title; 		// �� ����
	private String study_content; 	// �� �Ұ�
	private Date study_date;			// �ۼ�����
	private int study_readcount; 	// ��ȸ��
	
	private String study_crew_user; 		// ���� ������ 		/* > �����ο� ���̵� ���ٷ� ��� */ // TODO : Crew���̺� ����
	private int study_crew_count;   		// ���� ���� �ο�  	/* quota */
	private int study_crew_limit;			// ���� ���� �ο�
	private String study_crew_wait;		// ���� ���� �����
	
	/* String dept ...? */
	
	private String study_location; 		// ���� �������
	private String study_detail_location; // ���� ���͵�ī��
	private String study_part; 			// ���� ����о�
	
	public StudyVO() {}


	public StudyVO(int study_bno, String study_title, String study_content, Date study_date,
			int study_readcount, String study_location, String study_part, String study_userid, 
			int study_crew_limit, String study_crew_wait, String study_detail_location) {
		super();
		this.study_bno = study_bno;
		this.study_userid = study_userid;
		this.study_title = study_title;
		this.study_content = study_content;
		this.study_date = study_date;
		this.study_readcount = study_readcount;
		this.study_location = study_location;
		this.study_part = study_part;
		this.study_crew_limit = study_crew_limit;
		this.study_crew_wait = study_crew_wait;
		this.study_detail_location = study_detail_location;
	}
	
	
	public int getStudy_bno() {
		return study_bno;
	}


	public void setStudy_bno(int study_bno) {
		this.study_bno = study_bno;
	}


	public String getStudy_userid() {
		return study_userid;
	}


	public void setStudy_userid(String study_userid) {
		this.study_userid = study_userid;
	}


	public String getStudy_title() {
		return study_title;
	}


	public void setStudy_title(String study_title) {
		this.study_title = study_title;
	}


	public String getStudy_content() {
		return study_content;
	}


	public void setStudy_content(String study_content) {
		this.study_content = study_content;
	}


	public Date getStudy_date() {
		return study_date;
	}


	public void setStudy_date(Date study_date) {
		this.study_date = study_date;
	}


	public int getStudy_readcount() {
		return study_readcount;
	}


	public void setStudy_readcount(int study_readcount) {
		this.study_readcount = study_readcount;
	}


	public String getStudy_location() {
		return study_location;
	}


	public void setStudy_location(String study_location) {
		this.study_location = study_location;
	}


	public String getStudy_part() {
		return study_part;
	}


	public void setStudy_part(String study_part) {
		this.study_part = study_part;
	}
	
	public String getStudy_crew_user() {
		return study_crew_user;
	}
	
	public void setStudy_crew_user(String study_crew_user) {
		this.study_crew_user = study_crew_user;
	}
	
	public int getStudy_crew_count() {
		return study_crew_count;
	}
	
	public void setStudy_crew_count(int study_crew_count) {
		this.study_crew_count = study_crew_count;
	}
	
	public int getStudy_crew_limit() {
		return study_crew_limit;
	}
	
	public void setStudy_crew_limit(int study_crew_limit) {
		this.study_crew_limit = study_crew_limit;
	}
	
	public String getStudy_crew_wait() {
		return study_crew_wait; 
	}
	
	public void setStudy_crew_wait(String study_crew_wait) {
		this.study_crew_wait = study_crew_wait;
	}
	
	public String getStudy_detail_location() {
		return study_detail_location;
	}
	
	public void setStudy_detail_location(String study_detail_location) {
		this.study_detail_location = study_detail_location;
	}


	@Override
	public String toString() {
		return "StudyVO [studyBno=" + study_bno + ", studyUserid=" + study_userid + ", studyTitle=" + study_title
				+ ", studyContent=" + study_content + ", studyDate=" + study_date + ", studyReadCount="
				+ study_readcount + ", studyLocation=" + study_location
				+ ", studyPart=" + study_part + ", studyCrewUser=" + study_crew_user 
				+ ", studyCrewCount=" + study_crew_count + ", studyCrewLimit=" + study_crew_limit + 
				", studyCrewWait=" + study_crew_wait + ", studyDetailLocation=" + study_detail_location + "]\n";
	}
	
	
}