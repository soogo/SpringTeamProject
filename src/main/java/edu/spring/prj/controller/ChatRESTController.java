package edu.spring.prj.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.prj.domain.ChatVO;
import edu.spring.prj.service.ChatService;

@RestController
@RequestMapping(value = "*/chat")
public class ChatRESTController {
	private static final Logger logger = 
			LoggerFactory.getLogger(ChatRESTController.class);
	
	@Autowired
	private ChatService chatService;
	
	// chat �޼��� ����
//	@PostMapping()
//	public ResponseEntity<Integer> saveMsg(@RequestBody ChatVO vo, HttpServletRequest request) {
//		logger.info(vo.toString());
//		int result;
//		// �޼����� table�� ����Ǹ� session�� ����� bno�� �Է½�Ų��.
//		HttpSession session = request.getSession();
//		String loginId = (String)session.getAttribute("loginId");
//		logger.info("loginId : " + loginId);
//		
//		try {
//			result = chatService.create(vo);
//			logger.info("create() ȣ�� : " + vo.toString());
//			
//			return new ResponseEntity<Integer>(result, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<Integer>(0, HttpStatus.OK);
//		}
//	}
	
	// chat ����
	@PutMapping("/{chatBno}")
	public ResponseEntity<String> updateMsg(@PathVariable("chatBno")int chat_bno,
			@RequestBody ChatVO vo){
		logger.info("bno = " + chat_bno);
		vo.setChat_bno(chat_bno);
		logger.info("update vo = " + vo.toString());
		try {
			chatService.update(vo);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("fail", HttpStatus.OK);
		}
	}
	
	// chat �ҷ�����
	@GetMapping("/all/{chatBno}")
	public ResponseEntity<ChatVO> readMsg (@PathVariable("chatBno") int chat_bno,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		ChatVO vo = chatService.read(chat_bno);
		
		logger.info("chatBno = " + chat_bno + ", ChatVO " + vo);
		return new ResponseEntity<ChatVO>(vo, HttpStatus.OK);
	}
}
