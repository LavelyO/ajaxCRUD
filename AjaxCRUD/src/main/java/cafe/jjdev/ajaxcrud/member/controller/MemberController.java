package cafe.jjdev.ajaxcrud.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cafe.jjdev.ajaxcrud.member.mapper.MemberMapper;
import cafe.jjdev.ajaxcrud.member.service.MemberService;
import cafe.jjdev.ajaxcrud.member.vo.Member;

@RestController
public class MemberController {
	@Autowired private MemberMapper memberMapper;
	@Autowired private MemberService memberService;
	
	@GetMapping("/getMembers")
	public Map<String, Object> getMembers(@RequestParam(value="currentPage", defaultValue="1") int currentPage) {
		System.out.println("/getMembers 요청");
		System.out.println(currentPage + "currentPage MemberController.java");
		
		Map<String, Object> map = memberService.getMemberList(currentPage);
		return map;
	}

	/*
	@GetMapping("/getMembers")
	public List<Member> getMembers() {
	  	System.out.println("/getMembers 요청");
	  	List<Member> list = memberMapper.selectMemberList();
		System.out.println("memberMapper.selectMemberList.size :"+list.size());
	return list;
	}*/
	@GetMapping("/idCheck")
	public boolean idCheck(@RequestParam(value="id", required=false) String id) {
        System.out.println("/idCheck 요청");
        int result = memberMapper.idCheck(id);
        if (result == 0) {
        	return true;
        } else {
        	return false;
        }
	}
	
	@PostMapping("/addMember")
	public void addMember(Member member) {
		System.out.println("/addMember 요청");
		System.out.println("MemberController.addMember member : "+ member);
		memberMapper.insertMember(member);	
	}
	
	@PostMapping("/removeMember")
	public void removeMember(@RequestParam(value="ck[]") String[] ck) {	//List<String> ck
		System.out.println("/removeMember 요청");
		System.out.println("MemberController.removeMember member : "+ ck.length);
		for(String id : ck) {
			Member member = new Member();
			member.setId(id);
			memberMapper.deleteMember(member);
		}
	}
	@PostMapping("/modifyMember")
	public void modifyMember(Member member) {
		System.out.println("/modifyMember 요청");
		System.out.println("MemberController.modifyMember member : " + member);
		memberMapper.updateMember(member);
	}
}
