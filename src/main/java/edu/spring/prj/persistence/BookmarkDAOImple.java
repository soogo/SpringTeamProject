package edu.spring.prj.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.prj.domain.BookmarkVO;
import edu.spring.prj.domain.EmpBoardVO;
import edu.spring.prj.domain.FreeboardVO;
import edu.spring.prj.domain.QAboardVO;
import edu.spring.prj.domain.RecBoardVO;
import edu.spring.prj.domain.ShareboardVO;
import edu.spring.prj.pageutil.PageCriteria;

@Repository
public class BookmarkDAOImple implements BookmarkDAO {
	private static final Logger logger = LoggerFactory.getLogger(BookmarkDAOImple.class);
	private static final String NAMESPACE = "edu.spring.prj.BookmarkMapper";

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int delete(int bno) {
		logger.info("Bookmark DAO delete() ȣ��");
		return sqlSession.delete(NAMESPACE + ".delete", bno);
	}

	@Override
	public int getTotalNumsOfRecords(String userid) {
		logger.info("Bookmark DAO getTotalNumsOfRecords() ȣ��");
		return sqlSession.selectOne(NAMESPACE + ".total_count", userid);
	}

	@Override
	public List<BookmarkVO> select(PageCriteria criteria) {
		logger.info("Bookmark DAO select() ȣ�� : page = " + criteria.getPage());
		return sqlSession.selectList(NAMESPACE + ".paging", criteria);
	}

	@Override
	public int check(BookmarkVO vo) {
		logger.info("Bookmark DAO bookmarkCheck() ȣ��");
		return sqlSession.selectOne(NAMESPACE + ".check", vo);
	}

	@Override
	public int ajaxInsert(BookmarkVO vo) {
		logger.info("Bookmark DAO bookmark() ȣ�� : ajaxInsert");
		return sqlSession.insert(NAMESPACE + ".ajaxInsert", vo);
	}
	
	@Override
	public int ajaxDelete(BookmarkVO vo) {
		logger.info("Bookmark DAO bookmark() ȣ�� : ajaxDelete");
		return sqlSession.delete(NAMESPACE + ".ajaxDelete", vo);
	}

	@Override
	public FreeboardVO freeboardTitle(BookmarkVO vo) {
		logger.info("Bookmark DAO title() ȣ�� : freeboard");
		return sqlSession.selectOne(NAMESPACE + ".freeboard_title", vo);
	}

	@Override
	public QAboardVO qaboardTitle(BookmarkVO vo) {
		logger.info("Bookmark DAO title() ȣ�� : qaboard");
		return sqlSession.selectOne(NAMESPACE + ".qaboard_title", vo);
	}

	@Override
	public ShareboardVO shareboardTitle(BookmarkVO vo) {
		logger.info("Bookmark DAO title() ȣ�� : shareboard");
		return sqlSession.selectOne(NAMESPACE + ".shareboard_title", vo);
	}

	@Override
	public RecBoardVO recruitTitle(BookmarkVO vo) {
		logger.info("Bookmark DAO title() ȣ�� : recruit");
		return sqlSession.selectOne(NAMESPACE + ".recruit_title", vo);
	}

	@Override
	public EmpBoardVO employTitle(BookmarkVO vo) {
		logger.info("Bookmark DAO title() ȣ�� : employ");
		return sqlSession.selectOne(NAMESPACE + ".employ_title", vo);
	}


}
