package edu.spring.prj.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.spring.prj.domain.FreeboardVO;
import edu.spring.prj.pageutil.PageCriteria;
import edu.spring.prj.pageutil.PageMaker;
import edu.spring.prj.service.FreeboardService;

@Controller
@RequestMapping(value = "/freeboard")
public class FreeboardController {
	private static final Logger logger = LoggerFactory.getLogger(FreeboardController.class);

	@Autowired
	private FreeboardService freeboardService;

	@GetMapping("/list")
	public void list(Model model, Integer page, Integer perPage, HttpServletRequest request, String searchType, String keyword) {
		logger.info("Freeboard Controller list ȣ��");
		logger.info("Freeboard Controller page = " + page + ", perPage = " + perPage);
		logger.info("Freeboard Controller searchType : " + searchType + ", keyword : " + keyword);

		// Paging ó��
		PageCriteria criteria = new PageCriteria();
		if (searchType != null) {
			logger.info("searchType");
			criteria.setSearchType(searchType);
		}
		if (keyword != null) {
			logger.info("keyword");
			criteria.setKeyword(keyword);
		}
		if (page != null) {
			logger.info("page");
			criteria.setPage(page);
		}
		if (perPage != null) {
			logger.info("perPage");
			criteria.setNumsPerPage(perPage);
		}
		
		logger.info("Freeboard Controller criteria set searchType : " + criteria.getSearchType() + ", " + criteria.getKeyword());

		//
		List<FreeboardVO> freeboardNotice = freeboardService.selectNotice();
		model.addAttribute("freeboardNotice", freeboardNotice);
		//
		List<FreeboardVO> list = freeboardService.read(criteria);
		model.addAttribute("freeboardList", list);

		PageMaker maker = new PageMaker();
		maker.setCriteria(criteria);
		maker.setTotalCount(freeboardService.getTotalNumsOfRecords(criteria));
		maker.setPageData();
		model.addAttribute("pageMaker", maker);

		// tableName Test
		HttpSession session = request.getSession();
		session.setAttribute("tableName", "freeboard_table");
		
		session.setAttribute("searchType", searchType);
		session.setAttribute("keyword", keyword);
	} // end list()

	@GetMapping("/register")
	public void registerGET() {
		logger.info("Freeboard Controller registerGET() ȣ��");
	} // end registerGET()

	@PostMapping("/register")
	public String registerPOST(FreeboardVO vo, RedirectAttributes reAttr) {
		// RedirectAttributes
		// - ���� ��ġ�� �Ӽ����� �����ϴ� ��ü
		logger.info("Freeboard Controller registerPOST() ȣ��");
		logger.info("Freeboard Controller register : " + vo.toString());
		int result = freeboardService.create(vo);
		System.out.println();
		if (result == 1) { // DB insert ����
			// "insert_result"�� Ű�̸��� ���� ������ ����
			reAttr.addFlashAttribute("resultAlert", "insertSuccess");
			return "redirect:/freeboard/list"; // /board/list ��η� �̵�(get)���
		} else { // DB insert ����
			reAttr.addFlashAttribute("resultAlert", "insertFail");
			return "redirect:/freeboard/register"; // /board/register ��η� �̵�
		}
	}

	@GetMapping("/detail")
	public void detailGET(int bno, Model model, @ModelAttribute("page") int page) {
		logger.info("Freeboard Controller detail() ȣ�� : bno = " + bno);
		FreeboardVO vo = freeboardService.read(bno);
		model.addAttribute("freeboardVO", vo);
	}

	@GetMapping("/update")
	public void updateGET(int bno, Model model, @ModelAttribute("page") int page) {
		logger.info("Freeboard Controller update() ȣ�� : bno = " + bno);
		FreeboardVO vo = freeboardService.read(bno);
		model.addAttribute("freeboardVO", vo);
	}

	@PostMapping("/update")
	public String updatePOST(FreeboardVO vo, int page, RedirectAttributes reAttr) {
		logger.info("Freeboard Controller updatePOST() ȣ�� : bno = " + vo.getFreeboard_bno());
		// vo ���� ����.
		int result = freeboardService.update(vo);
		if (result == 1) {
			reAttr.addFlashAttribute("resultAlert",	"updateSuccess");
			return "redirect:/freeboard/detail?bno=" + vo.getFreeboard_bno() + "&page=" + page;
		} else {
			reAttr.addFlashAttribute("resultAlert",	"updateFail");
			return "redirect:/freeboard/update?bno=" + vo.getFreeboard_bno();
		}
	}

	@GetMapping("/delete")
	public String delete(int bno, RedirectAttributes reAttr) {
		logger.info("Freeboard Controller delete() ȣ�� : bno = " + bno);
		int result = freeboardService.delete(bno);
		if (result == 1) {
			reAttr.addFlashAttribute("resultAlert", "deleteSuccess");
			return "redirect:/freeboard/list";
		} else {
			reAttr.addFlashAttribute("resultAlert", "deleteFail");
			return "redirect:/freeboard/detail?bno=" + bno;
		}
	}


} // FreeboardController
