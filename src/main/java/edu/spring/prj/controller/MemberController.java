package edu.spring.prj.controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.spring.prj.domain.ChatVO;
import edu.spring.prj.domain.MemberVO;
import edu.spring.prj.email.Email;
import edu.spring.prj.email.EmailSender;
import edu.spring.prj.service.ChatService;
import edu.spring.prj.service.MemberService;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService memberService;

	@Autowired
	private ChatService chatService;
	
	/* ȸ������ */
	@GetMapping("/register")
	public void registerGET() {
		logger.info("Member Controller registerGET() ȣ��");
	} // end registerGET()

	@PostMapping("/register")
	public String registerPOST(@Valid MemberVO vo, BindingResult br, RedirectAttributes reAttr) {
		logger.info("Member Controller registerPOST() ȣ��");
		logger.info(vo.toString());
		String userid = vo.getUserid();
		
		if(br.hasErrors()) { 
			logger.info("Member Controller BindingResult has errors");
			return "member/register"; 
		} else { 
			logger.info("Member Controller idCheck ��� ���� ���̵�.");
			memberService.create(vo);
			vo.setPassword(null);
			reAttr.addFlashAttribute("registerVO", vo);

			// ä�� ���̺� ����
			ChatVO cvo = new ChatVO(0, " ", userid);
			chatService.create(cvo);
			return "redirect:/member/register-result";
		}
	} // registerPOST()
	
	/* ȸ������ ���Ȯ�� */
	@GetMapping("/register-result")
	public void registerResult() {
		logger.info("Member Controller registerResult() ȣ��");
	} // registerResult()


	/* ajax ���̵�, �̸��� �ߺ�Ȯ�� */
    @ResponseBody
    @RequestMapping(value = "/checkAjax")
    public int checkAjax(MemberVO vo) {
  	    logger.info("Member Controller checkAjax start");
	    logger.info("Member Controller checkAjax : " + vo.toString());
	    return memberService.checkAjax(vo);
    } // checkAjax()

	
	/* �α��� */
	@GetMapping("/login")
	public void loginGet(Model model, HttpServletRequest request) {
		logger.info("Member Controller - loginGet() ȣ��");
		String referer = request.getHeader("referer");
		logger.info("Member Controller - loginGet() referer : " + referer);
		model.addAttribute("targetUrl", referer);
		model.addAttribute("loginResult", null);
	} // loginGet()

	@PostMapping("/login-post")
	public void loginPost(MemberVO vo, Model model, HttpServletRequest request) {
		logger.info("Member Controller - loginPost() ȣ��");
		MemberVO loginVO = memberService.login(vo);
		logger.info("Member Controller - loginPost() loginVO : " + loginVO);
		String result = null;
		String part = null;
		if (loginVO != null) {
			result = loginVO.getUserid();
			part = loginVO.getPart();
			int blacklist = loginVO.getBlacklist();
			logger.info(">>> ���̵� ��й�ȣ�� ��ġ�մϴ�.");
			logger.info("result : " + result);
			model.addAttribute("result", result);
			model.addAttribute("part", part);
			model.addAttribute("blacklist", blacklist);
			
			HttpSession session = request.getSession();
			ChatVO cvo = chatService.read(result);
			logger.info("cvo = " + cvo);
			session.setAttribute("chatBno", cvo.getChat_bno());
			logger.info("chatBno = " + cvo.getChat_bno());
		}
	} // loginPost()

	/* �α׾ƿ� */
	@RequestMapping(value = "/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.info(">>> Member Controller logout() ȣ��");
		HttpSession session = request.getSession();
		String referer = request.getHeader("referer");
		logger.info("Member Controller referer : " + referer);
		Object check = session.getAttribute("loginId");
		int chatBno = (Integer)session.getAttribute("chatBno");
		ChatVO cvo = chatService.read(chatBno);
		cvo.setChat_content(" ");
		
		if (check != null) {
			session.removeAttribute("loginId");
			session.removeAttribute("part");
			session.removeAttribute("blacklist");
			session.removeAttribute("tableName");
			session.setAttribute("loginResult", "logout");
			session.removeAttribute("chatBno");
//			response.sendRedirect(referer);
			response.sendRedirect("/prj/RecBoard/main");
		} else {
			response.sendRedirect(referer);
		}
	} // logout()

	
	/* �α��� ���� ���� */
    @ResponseBody
    @RequestMapping(value = "/updateLoginInfo")
    public int updateLoginInfo(MemberVO vo) {
  	    logger.info("Member Controller updateLoginInfo start");
	    logger.info("Member Controller updateLoginInfo : " + vo.toString());
	    return memberService.updateLoginInfo(vo);
    } // updateLoginInfo()
    

	/* ����ȸ�������� */
	@RequestMapping(value = "/detail-personal")
	public void detailPersonal(Model model, HttpServletRequest request) {
		// member_table �α��� ���� �ҷ�����
		HttpSession session = request.getSession();
		
		// loginId ���� ��������
		String loginId = session.getAttribute("loginId").toString();
		MemberVO loginVO = memberService.read(loginId);
		model.addAttribute("loginVO", loginVO);

		// part ���� ��������
		String loginPart = session.getAttribute("part").toString();
		logger.info("Member Controller detailPersonal part : " + loginPart);

		// tableName (Ȯ�ο�)
		session.setAttribute("tableName", "detail_personal_table");
		
		// detail_personal_table ���� �ҷ�����
		if (loginPart.equals("personal")) {
			logger.info("Member Controller detailPersonal ȣ�� : userid = " + loginId);
			MemberVO personalVO = memberService.readDetailPersonal(loginId);
			
			model.addAttribute("personalVO", personalVO);
		}
	} // detailPersonal

	/* ajax �������� ���� */
	@ResponseBody
	@PostMapping("/updateDetailPersonal")
	public int updateDetailPersonal(MemberVO vo) {
		logger.info("Member Contoller updateDetailPersonal ȣ��");
		logger.info(vo.toString());
		return memberService.updateDetailPersonal(vo);
	} // updateDetailPersonal

	
	/* ���ȸ�������� */
	@RequestMapping(value = "/detail-company")
	public void detailCompany(String userid, Model model, HttpServletRequest request) {
		// member_table �α��� ���� �ҷ�����
		HttpSession session = request.getSession();
		String loginId = session.getAttribute("loginId").toString();
		MemberVO loginVO = memberService.read(loginId);
		model.addAttribute("loginVO", loginVO);

		// part ���� ���ǿ� ����
		String loginPart = session.getAttribute("part").toString();
		logger.info("part : " + loginPart);
		
		// tableName (Ȯ�ο�)
		session.setAttribute("tableName", "detail_company_table");

		// detail_company_table ���� �ҷ�����
		if (loginPart.equals("company")) {
			logger.info("Member Controller /detailCompany ȣ�� : userid = " + loginId);
			MemberVO companyVO = memberService.readDetailCompany(loginId);
			logger.info(companyVO.toString());
			model.addAttribute("companyVO", companyVO);
		}
	} // detailCompany

	/* ajax ������� ���� */
	@ResponseBody
	@PostMapping("/updateDetailCompany")
	public int updateDetailCompany(MemberVO vo) {
		logger.info("Member Controller updateDetailCompany ȣ��");
		logger.info(vo.toString());
		return memberService.updateDetailCompany(vo);
	} // updateDetailComapny

	/* ���̵� ã�� */
	@RequestMapping(value = "/find-id")
	public void findId() {
		logger.info("���̵� ã�� �� â ����");
	} // findId()

	@PostMapping("/postFindId")
	public String postFindId(MemberVO vo, RedirectAttributes reAttr) {
		// member -> userid
		logger.info("���̵� ã�� ����");
		String result = memberService.findId(vo);
		logger.info("ã�� ���̵��? : " + result);
		if (result != null) {
			reAttr.addFlashAttribute("id", result);
			return "redirect:/member/find-id";
		} else {
			reAttr.addFlashAttribute("id", null);
			return "redirect:/member/find-id";
		}
	} // postFindId()
	
	    
	/* ��й�ȣ ã�� */
	@RequestMapping(value = "/find-pw")
	public void findPw() {
		logger.info("��й�ȣ ã�� �� â ����");
	} // findPw()
	
	@PostMapping("/findPassword")
	public String postFindPw(MemberVO vo, RedirectAttributes reAttr, HttpSession session, HttpServletResponse resp) {
		logger.info("��й�ȣ ã�� ȸ���˻� ����");
		int result = memberService.findPwCheck(vo);
		
		logger.info("��й�ȣ ã�� result : " + result);
		if (result == 1) {
			logger.info("��й�ȣ ã�� ȸ������ Ȯ�ε� : " + vo.getEmail().toString());
			// �ӽú�й�ȣ ����
			logger.info("�ӽú�й�ȣ ���� ����");
			Random r = new Random();
			int num = r.nextInt(89999) + 10000;
			String npassword = "pw" + Integer.toString(num);// ���ο� ��й�ȣ ����
			vo.setPassword(npassword);
			session.setAttribute("memberVO", vo);
			memberService.newPassword(vo);
			logger.info("���ο� ��й�ȣ ��");
			return "redirect:/member/sendPwEmail";
		} else {
			logger.info("��й�ȣ ã�� ȸ������.");
			// ȸ������ ǥ�����ֱ�
			reAttr.addFlashAttribute("findPwCheck", "fail");
			return "redirect:/member/find-pw";
		}
	} // postFindPw()
	    
		  
	  /* ��й�ȣ ã��(���Ϻ�����) */
	  @Autowired
	  private Email email;
	  @Autowired
	  private EmailSender emailSender;
	  
	  // �̸��Ϸ� ��й�ȣ�� �����̵ȴ�.
	  @RequestMapping("/sendPwEmail")
	  public String sendPwEmail(MemberVO vo, HttpSession session) throws Exception {
		  logger.info("�̸��� ������ ����");
		  vo = (MemberVO) session.getAttribute("memberVO");
		  email.setContent("���ο� ��й�ȣ " + vo.getPassword() + " �Դϴ�.");
		  email.setReceiver(vo.getEmail());
		  email.setSubject("��й�ȣ ã�� : "+vo.getUserid() +"��  �缳���� ��й�ȣ�� Ȯ�����ּ���");
		  emailSender.SendEmail(email);
		  session.invalidate();
		  return "redirect:/member/find-pw-result";
	  } // sendPwEmail()
	  
	  @RequestMapping("/find-pw-result")
	  public void findPwResult() {
		  logger.info("��й�ȣ ã�� �������� �Ϸ�");
	  } // findPwResult()

		/* ������������ */
		@RequestMapping(value = "/detail-manager")
		public void detailManager(String userid, Model model, HttpServletRequest request) {
			// member_table �α��� ���� �ҷ�����
			HttpSession session = request.getSession();
			String loginId = session.getAttribute("loginId").toString();
			MemberVO loginVO = memberService.read(loginId);
			model.addAttribute("loginVO", loginVO);

			// part ���� ���ǿ� ����
			String loginPart = session.getAttribute("part").toString();
			logger.info("part : " + loginPart);
			
			// tableName (Ȯ�ο�)
			session.setAttribute("tableName", "member_table");
		} // detailCompany

} // MemberController