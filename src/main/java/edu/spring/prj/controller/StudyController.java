package edu.spring.prj.controller;

import java.util.List;

import javax.servlet.ServletRequest;
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

import edu.spring.prj.domain.StudyVO;
import edu.spring.prj.pageutil.PageCriteria;
import edu.spring.prj.pageutil.PageMaker;
import edu.spring.prj.service.StudyService;


@Controller
@RequestMapping(value = "/studyBoard")
public class StudyController {
	private static final Logger logger 
			= LoggerFactory.getLogger(StudyController.class);

	@Autowired
	private StudyService studyService;
	
	/* ����¡ ó�� */
	@GetMapping("/list")
	public void list(Model model, Integer page, Integer perPage, 
			String keyword, String select) {
		logger.info("list ȣ��");
		logger.info("page = " + page + ", perPage = " + perPage);

		// Page ó��
		PageCriteria criteria = new PageCriteria();
		if (page != null) {
			criteria.setPage(page);
		}
		if (perPage != null) {
			criteria.setNumsPerPage(perPage);
		}

		List<StudyVO> list = studyService.read(criteria);

		model.addAttribute("boardList", list);

		logger.info(list.toString());
		
		logger.info("page = " + page);
		PageMaker maker = new PageMaker();
		maker.setCriteria(criteria);
		maker.setTotalCount(studyService.getTotalNumsofRecords());
		maker.setPageData();
		model.addAttribute("pageMaker", maker);
	}

	
	@PostMapping("/list")
	public void listPOST(String keyword, String select_keyword, Model model, RedirectAttributes reAttr
			, Integer page, Integer perPage, HttpServletRequest request) {
		logger.info("listPOST-Search ȣ�� : keyword = " + keyword + ", select = " + select_keyword);
		
		logger.info("page : " + page);
		if (select_keyword.equals("userid")) { // userid�� �˻�
			List<StudyVO> list = studyService.read(keyword);
			model.addAttribute("boardList", list);
			logger.info(list.toString());

		} else if (select_keyword.equals("keyword")) { // keyword�� �˻�
			List<StudyVO> list = studyService.readByKeyword(keyword);
			model.addAttribute("boardList", list);
			logger.info(list.toString());
			
		} else if (select_keyword.equals("location")) { // �������� �˻�
			List<StudyVO> list = studyService.readByLocation(keyword);
			model.addAttribute("boardList", list);
			logger.info(list.toString());
		}
		
		// Page ó��
		PageCriteria criteria = new PageCriteria();
		if (page != null) {
			criteria.setPage(page);
		}
		if (perPage != null) {
			criteria.setNumsPerPage(perPage);
		}
		
		PageMaker maker = new PageMaker();
		maker.setCriteria(criteria);
		maker.setTotalCount(studyService.getTotalNumsofRecords());
		maker.setPageData();
		model.addAttribute("pageMaker", maker);
		
		HttpSession session = request.getSession();
		session.setAttribute("keyword", keyword);
		session.setAttribute("select_keyword", select_keyword);
	}

	/* register ------------------------------------------------ */
	// register.jsp�� GET������� �ҷ�����
	@GetMapping("/register")
	public void registerGET() {
		logger.info("registerGET() ȣ��");

	}

	// register.jsp�� POST������� ������ �޼ҵ�
	@PostMapping("/register")
	public String registerPOST(StudyVO vo, RedirectAttributes reAttr, HttpServletRequest request) {
		logger.info("registerPOST() ȣ��");
		logger.info(vo.toString());

		vo.setStudy_crew_user(vo.getStudy_userid());
		vo.setStudy_crew_count(Integer.parseInt("1"));
		
		int result = studyService.create(vo);
		
		if (result == 1) {
			reAttr.addFlashAttribute("insert_result", "success");
			return "redirect:/studyBoard/list";
		} else {
			reAttr.addFlashAttribute("insert_result", "fail");
			return "redirect:/studyBoard/list";
		}
	}

	/* detail -------------------------------------------------- */
	@GetMapping("/detail")
	public void detailGET(Integer bno, Model model, @ModelAttribute("page") int page) {
		logger.info("detail() ȣ�� : bno = " + bno);
		
		// ��ϵ� ���� �ۼ���
		StudyVO vo = studyService.read(bno);	// vo�� �� ���� �Ϸ�
		logger.info(vo.toString());
		model.addAttribute("studyVO", vo);
		
		// �� �ۼ����� �̸��� �������� ������
//		HttpSession session = request.getSession(); // ���� ����
//		session.setAttribute("makerUserid", arr[0]);
//		logger.info("makerUserid : " + arr[0]);
		
		int result = studyService.updateReadCount(bno);
		
		if (result == 1) {
			logger.info("��ȸ�� ���� ���� : readcount = " + vo.getStudy_readcount());
		} else {
			logger.info("��ȸ�� ���� ����");
		}
		
	}
	
	@GetMapping("/detail-admin")
	public void detailAdminGET(Integer bno, Model model, @ModelAttribute("page") int page) {
		logger.info("detail-admin() ȣ�� : bno = " + bno);
		
		// ��ϵ� ���� �ۼ���
		StudyVO vo = studyService.read(bno);
		logger.info(vo.toString());
		model.addAttribute("studyVO", vo);
		String attendUserid = vo.getStudy_crew_user();
		String arr[] = attendUserid.split(",");
		logger.info("�ش� �� �ۼ��� userid : " + arr[0]);
		
		logger.info("detail-adimin() ���� ȣ�� �Ϸ�");
	}

	/* update -------------------------------------------------- */
	// update.jsp�� GET������� �ҷ�����
	@GetMapping("/update")
	public void updateGET(Integer bno, Model model, @ModelAttribute("page") int page) {
		logger.info("update() ȣ�� : bno = " + bno);
		StudyVO vo = studyService.read(bno);
		model.addAttribute("studyVO", vo);
	}

	// update.jsp�� POST������� �ҷ�����
	@PostMapping("/update")
	public String updatePOST(StudyVO vo, Integer page, RedirectAttributes reAttr) {
		logger.info("updatePOST() ȣ�� : bno = " + vo.getStudy_bno());
		int result = studyService.update(vo);
		if (result == 1) {
			reAttr.addFlashAttribute("update_result", "success");
			return "redirect:/studyBoard/detail?bno=" + vo.getStudy_bno() + "&page=" + page;
			// page�� �ԷµǾ��� ��, ���� �۵� / bno�� �޾Ƽ� detail�� �̵� �Ұ�
		} else {
			logger.info("update() ����");
			reAttr.addFlashAttribute("update_result", "fail");
			return "redirect:/studyBoard/update?bno=" + vo.getStudy_bno();
		}
	}

	/* delete -------------------------------------------------- */
	@GetMapping("/delete")
	public String delete(Integer bno, RedirectAttributes reAttr) {
		logger.info("delete() ȣ�� : bno = " + bno);
		
		int result = studyService.delete(bno);
		if (result == 1) {
			reAttr.addFlashAttribute("delete_result", "success");
			return "redirect:/studyBoard/list";
		} else {
			reAttr.addFlashAttribute("delete_result", "fail");
			return "redirect:/studyBoard/detail?bno=" + bno;
		}
	}
	
	

	 
	  /* ���� attend ---------------------------------------------- */
	  // �޾ƿ� userid�� ����ܿ� �߰�!
	  // member = ���� ��� �� / userid = ������ / bno = studybno
	  @GetMapping("/attend") 
	  public String attend(
			  RedirectAttributes reAttr, String userid, Integer bno,
			  @ModelAttribute("page") int page){ 
		  int result = 0;
		  boolean attendOverlap = false;
		  boolean waitOverlap = false;
		  logger.info("userid = " + userid);
		  StudyVO vo = studyService.read(bno);							// bno�� ������ �ش� �Խñ��� vo
		  
		  // userid�� studyCrewUser�� �� > �ߺ��Ǹ� ���ӿ� ������ �� ������
		  String users[] = vo.getStudy_crew_user().split(",");
		  for (int i = 0; i < users.length; i++) {
			  if (userid.equals(users[i])) {
				  attendOverlap = true;    // ���̵� �ߺ�!
			  }
		  }
		  logger.info("attend() ���̵� �ߺ� test ��� - " + attendOverlap);
		  
		  // userid�� studyCrewWait�� ��
		  String usersArr[] = vo.getStudy_crew_wait().split(",");
		  for (int i = 0; i < usersArr.length; i++) {
			  if (userid.equals(usersArr[i])) {
				  waitOverlap = true;
			  }
		  }
		  logger.info("attend() ���̵� �ߺ� test ��� - " + waitOverlap);
		  
		  // service.updateCrewWait - bno / studyCrewWait
		  if (attendOverlap == false && waitOverlap == false) {
			  if (vo.getStudy_crew_wait().equals("0")) {
				  vo.setStudy_crew_wait(userid);
			  } else {
				  vo.setStudy_crew_wait(vo.getStudy_crew_wait() + "," + userid);
			  }
			  
			  result = studyService.updateCrewWait(vo);
			  logger.info(vo.toString());
			  logger.info("attend() - result = " + result);
		  }
		   
		  if (result == 1 && attendOverlap == false && waitOverlap == false) { /* detail.jsp �˸�����ϱ� */ /* detail.jsp ��ư ���̱� ó�� */
			  reAttr.addFlashAttribute("attend_result", "success"); 
			  return "redirect:/studyBoard/detail?bno=" + bno + "&page=" + page;
		  } else if (attendOverlap == true) {		// ���̵� �ߺ����� ���� ����!
			  reAttr.addFlashAttribute("attend_result", "attendOverlap"); 
			  logger.info("�̹� ������ �̿���!");
			  return "redirect:/studyBoard/detail?bno=" + bno + "&page=" + page;
		  } else if (waitOverlap == true) {
			  reAttr.addFlashAttribute("attend_result", "waitOverlap");
			  logger.info("�̹� ��������� �̿���!");
			  return "redirect:/studyBoard/detail?bno=" + bno + "&page=" + page;
		  } else { 			// �����ͻ� ������ ���� ����
			  reAttr.addFlashAttribute("attend_result", "fail");
			  logger.info("�����ͻ� ������ ���� ����");
			  return "redirect:/studyBoard/detail?bno=" + bno + "&page=" + page;
		  }
	  } 
	 
	  // ��� > ������
	  @GetMapping("/attend-wait")
	  public String attendWait(Integer member,
			  RedirectAttributes reAttr, String userid, Integer bno,
			  @ModelAttribute("page") int page){ 
		  int result;
		  logger.info("attendWait() ȣ�� userid - " + userid);
		  
		  StudyVO vo = studyService.read(bno);
		  
		  // vo�� �����μ�(member) ���� / userid�� ����ܿ��� �޾ƿ´�.
		 
		  
		  logger.info(vo.toString());
		  
		  String crewWaiter = vo.getStudy_crew_wait(); 			// ����ڸ�� �ʱ�ȭ��
		  String arrWaiter[] = crewWaiter.split(",");
		  int attendNum = arrWaiter.length;
		  
		  /* crewWaiter�� ��ܿ��� �������� ���̵� �����,
		   * crewUser�� �������� ���̵� �߰� ��Ų��. (V)
		   *  */
		  
		  
		  
		  logger.info("arr[i] - ");
		  for (int i = 0; i < arrWaiter.length; i++) {
			  System.out.println(arrWaiter[i]);
		  }
		  
		  // �̹� ���ӿ� ��ϵ� �̿������� Ȯ��
		  int count = 0;
		  String arrCrewUser[] = vo.getStudy_crew_user().split(",");
		  for (int i = 0; i < arrCrewUser.length; i++) {
			  logger.info("arr[" + i + "] = " + arrCrewUser[i]);
			  if (arrCrewUser[i].equals(userid)) { // �̹� ���ӿ� ���ִ� �̿��� - count
				  count = 1;
				  logger.info("count-userid - " + userid);
				  break;
			  }
		  }
		  
		  // crewWaiter���� ������ �̿����� ���̵� ����
		  if (vo.getStudy_crew_wait().split(",").length > 1 || count != 1) {
			  crewWaiter = crewWaiter.replace(userid + ",", ""); // �Ǿ� - �ǳ� ���� ������ ��ȯ
			  crewWaiter = crewWaiter.replace(userid , "0");	 // �ǳ��� ������ ��ȯ
		  } else if (vo.getStudy_crew_wait().split(",").length == 1 || count != 1) {
			  crewWaiter = crewWaiter.replace(userid, "0");
		  }
		  
		  logger.info("userid - " + userid);				// �����ϰ�;��ϴ� ������ id
		  logger.info("crewWaiter - " + crewWaiter);		// ������� ���
		  
		  if (count == 0) {		// ���ӿ� ��ϵǾ� �����ʴ�	
			  member++;
			  vo.setStudy_crew_count(member);  
			  vo.setStudy_crew_wait(crewWaiter);
			  vo.setStudy_crew_user(vo.getStudy_crew_user() + "," + userid); // crewUser�� ������ ���̵� �߰�
			  result = studyService.updateCrew(vo);
		  } else {				// ���ӿ� ��ϵǾ��ִ�
			  result = 0;
		  }
		  
		  if (result == 1) {
			  reAttr.addFlashAttribute("attend_result", "success");
			  return "redirect:/studyBoard/detail-admin?bno=" + bno + "&page=" + page;
		  } else {
			  logger.info("updateCrew() ���� �۵�X!");
			  reAttr.addFlashAttribute("attend_result", "fail");
			  return "redirect:/studyBoard/detail-admin?bno=" + bno + "&page=" + page;
		  }
	  }
	  
	  // ������ ����
	  @GetMapping("/attend-delete")
	  public String attendDelete(Integer member,
			  RedirectAttributes reAttr, String userid, Integer bno,
			  @ModelAttribute("page") int page){ 
		  int result;
		  // member = ������ �̿��� ��, userid = �ش� �̿���, bno = �Խñ� ������ȣ
		  StudyVO vo = studyService.read(bno);
		  
		  String userList = vo.getStudy_crew_user();
		  if (userList.split(",").length == 1) { // ������ ��ܿ� �ϳ��� ���� ���
			  userList = userList.replace(userid + ",", "0");
			  userList = userList.replace(userid, "0");
		  } else if (userList.split(",").length > 1){
			  userList = userList.replace(userid + ",",	"");
			  userList = userList.replace("," + userid, "");
			  userList = userList.replace(userid ,	"");
		  }
		  
		  vo.setStudy_crew_count(member - 1);
		  vo.setStudy_crew_user(userList);
		  
		  result = studyService.deleteCrew(vo);
		  
		  if (result == 1) {
			  reAttr.addFlashAttribute("attend_delete_result", "success");
			  return "redirect:/studyBoard/detail-admin?bno=" + bno + "&page=" + page;
		  } else {
			  logger.info("attendDelete() ���� �۵�X");
			  reAttr.addFlashAttribute("atend_delete_result", "fail");
			  return "redirect:/studyBoard/detail-admin?bno=" + bno + "&page=" + page;
		  }
	  }
	
	  // ��� - ����
	  @GetMapping("/attend-reject")
	  public String attendReject(Integer member, RedirectAttributes reAttr, String userid, Integer bno,
			  	@ModelAttribute("page") int page) {
		  int result;
		  // member = ������ �̿��� ��, userid = �ش� �̿���(��������), bno = �Խñ� ������ȣ
		  StudyVO vo = studyService.read(bno);
		  
		  String userList = vo.getStudy_crew_wait();
		  if (userList.split(",").length == 1) {		// ����ڿ� �Ѹ� ����
			  userList = userList.replace(userid + ",", "0");
			  userList = userList.replace(userid, "0");
		  } else if (userList.split(",").length > 1) {  // ����ڰ� �Ѹ� �̻� ����
			  userList = userList.replace(userid + ",", ""); 	// �� �ڰ� �ƴ� �κп� ������ ��ü
			  userList = userList.replace("," + userid, "");	// �� �ڿ� ���̵� �����Ҷ�
			  userList = userList.replace(userid, "");
		  }
		  
		  vo.setStudy_crew_wait(userList);
		  
		  result = studyService.deleteCrewWait(vo); 		// crewWait, bno
		  
		  if (result == 1) {
			  reAttr.addFlashAttribute("wait_reject_result", "success");
			  return "redirect:/studyBoard/detail-admin?bno=" + bno + "&page=" + page;
		  } else {
			  logger.info("attend-reject ���� �۵� X");
			  reAttr.addFlashAttribute("wait_reject_result", "fail");
			  return "redirect:/studyBoard/detail-admin?bno=" + bno + "&page=" + page;
		  }
	  }
	  
	  // ===========================================================
	  
	 
}
