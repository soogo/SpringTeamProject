package edu.spring.prj.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.prj.domain.FreeboardVO;
import edu.spring.prj.domain.ManagerVO;
import edu.spring.prj.domain.MemberVO;
import edu.spring.prj.pageutil.PageCriteria;
import edu.spring.prj.pageutil.PageMaker;
import edu.spring.prj.service.ManagerService;

@RestController
@RequestMapping(value = "*/managerRest")
public class ManagerRESTController {
	private static final Logger logger = LoggerFactory.getLogger(ManagerRESTController.class);
	
	@Autowired
	private ManagerService managerService;
	
	/* ������ ���� */
	@PostMapping("/updateNotice")
	public ResponseEntity<Integer> updateNotice(FreeboardVO vo) {
		logger.info("ManagerRESTController updateNotice Call");
		logger.info(vo.toString());
		int result = managerService.updateNotice(vo);
		logger.info("result : " + result);
  	    return new ResponseEntity<Integer>(result, HttpStatus.OK); 
	}

	/* (ȸ������) ������Ʈ ���� */
	@PutMapping("/blacklist")
	public ResponseEntity<Integer> updateBlacklist(@RequestBody MemberVO vo) {
		logger.info("ManagerRESTController updateBlacklist Call");
		logger.info(vo.toString());
		int result = managerService.updateBlacklist(vo);
		logger.info("result : " + result);
		return new ResponseEntity<Integer>(result, HttpStatus.OK); 
	}
	
	/* (ȸ������) ������ */
	@PostMapping("/detail")
	public ResponseEntity<MemberVO> detail(@RequestBody MemberVO vo){
		logger.info("MenagerRESTController detail Call");
		logger.info(vo.toString());
		MemberVO detailVO = managerService.detail(vo);
		return new ResponseEntity<MemberVO>(detailVO, HttpStatus.OK);
	}
	
	/* (ȸ������) �˻� */
	@GetMapping("/search-member")
	public ResponseEntity<List<MemberVO>> searchMember(ManagerVO vo, Integer page, Integer perPage) {
		logger.info("ManagerRESTController search_member ȣ��");
		logger.info("ManagerRESTController page = " + page + ", perPage = " + perPage);
		// Paging ó��
		if (page != null) {
			vo.setPage(page);
		}
		if (perPage != null) {
			vo.setNumsPerPage(perPage);
		}
		logger.info(vo.toString());
		List<MemberVO> list = managerService.searchMember(vo);
		return new ResponseEntity<List<MemberVO>>(list, HttpStatus.OK);
	}
	
	/* (ȸ������) ����¡ */
	@GetMapping("/paging")
	public ResponseEntity<PageMaker> panging(ManagerVO vo, Integer page, Integer perPage){
		logger.info("ManagerRESTController paging Call");
		PageMaker maker = new PageMaker();
		PageCriteria criteria = new PageCriteria();
		if (page != null) {
			criteria.setPage(page);
		}
		if (perPage != null) {
			criteria.setNumsPerPage(perPage);
		}
		maker.setCriteria(criteria);
		maker.setTotalCount(managerService.getTotalNumsOfSearching(vo));
		maker.setPageData();
		return new ResponseEntity<PageMaker>(maker, HttpStatus.OK);
	}
	
	// (ȸ������) ��� ����
	@DeleteMapping("/{userid}")
	public ResponseEntity<Integer> deleteMember(@PathVariable("userid") String userid) {
		logger.info("ManagerRESTConroller delete userid : " + userid);
		int result = managerService.delete(userid);
		logger.info("ManagerRESTConroller delete result : " + result);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	} // deleteMember
	
	/* �����ڵ�� */ // ����
	@PostMapping("/register")
	public ResponseEntity<Integer> registerManager(MemberVO vo){
		logger.info("ManagerRESTController registerManager() CALL");
		logger.info("ManagerRESTController vo : " + vo.toString());
		int result = managerService.registerManager(vo);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
		
	
} // ManagerRESTController
