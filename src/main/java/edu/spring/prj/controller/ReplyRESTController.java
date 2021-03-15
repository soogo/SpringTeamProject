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

import edu.spring.prj.domain.ReplyVO;
import edu.spring.prj.service.ReplyService;

@RestController
@RequestMapping(value = "*/replies")
public class ReplyRESTController {
	private static final Logger logger = LoggerFactory.getLogger(ReplyRESTController.class);

	@Autowired
	private ReplyService replyService;

	// ��� �Է�
	@PostMapping() // POST : ��� �Է�
	public ResponseEntity<Integer> createReply(@RequestBody ReplyVO vo) {
		// @RequestBody
		// - Ŭ���̾�Ʈ���� ���۹��� json �����͸� �ڹ� ��ü�� ��ȯ���ִ� annotation
		logger.info(vo.toString());
		int result;
		try {
			result = replyService.create(vo);
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Integer>(0, HttpStatus.OK);
		}

	}

	// ��� �ҷ�����
	@GetMapping("/all/{reply_bno}") // GET : ��� ����(all)
	public ResponseEntity<List<ReplyVO>> readReplies(@PathVariable("reply_bno") int reply_bno) {
		// @PathVariable(name) : {name}���� ������ ������ ����
		List<ReplyVO> list = replyService.read(reply_bno);
		return new ResponseEntity<List<ReplyVO>>(list, HttpStatus.OK);
	}

	@PutMapping("/{reply_no}") // PUT : ��� ����
	public ResponseEntity<String> updateReply(@PathVariable("reply_no") int reply_no, @RequestBody ReplyVO vo) {
		vo.setReply_no(reply_no);
		int result = replyService.update(vo);
		ResponseEntity<String> entity = null;
		if (result == 1) {
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			entity = new ResponseEntity<String>("fail", HttpStatus.OK);
		}
		return entity;
	}

	// ��� ����
	@DeleteMapping("/{reply_no}")
	public ResponseEntity<String> deleteReply(@PathVariable("reply_no") int reply_no, @RequestBody ReplyVO vo) {
		logger.info("reply_bno = " + vo.getReply_bno());
		logger.info("delete vo = " + vo.toString());
		try {
			replyService.delete(reply_no, vo.getReply_bno(), vo.getReply_table_name());
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("fail", HttpStatus.OK);
		}
	} // deleteReply

} // ReplyRESTController
