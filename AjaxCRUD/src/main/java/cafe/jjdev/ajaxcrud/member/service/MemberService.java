package cafe.jjdev.ajaxcrud.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cafe.jjdev.ajaxcrud.member.mapper.MemberMapper;
import cafe.jjdev.ajaxcrud.member.vo.Member;

@Service
public class MemberService {
	@Autowired private MemberMapper memberMapper;
	public Map<String, Object> getMemberList(int currentPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		final int ROW_PER_PAGE = 20;//	한 페이지에 15개씩 보여준다.
		int startRow = (currentPage-1)*ROW_PER_PAGE;//첫번째 행을 구하는 방법 : (현재 페이지-1)*15
		int beginRow=(currentPage-1)*ROW_PER_PAGE;
		//map에 시작하는 행과 보여줄 갯수를 담는다. (limit 0,20) 조회할 수 있도록.
		
		map.put("startRow", startRow);
		map.put("rowPerPage", ROW_PER_PAGE);
		

		List<Member> list = memberMapper.selectMemberList(map);
		int memberCount = memberMapper.selectMemberCount();
		int totalPage=memberCount/ROW_PER_PAGE;

		//		시작페이지와 끝페이지, 다음페이지, 이전페이지
		final int startPage = 1;
		int lastPage = totalPage;
		int nextPage = currentPage+1;
		int prevPage = currentPage-1;
		
		//	나눠 떨어지지 않는 경우에는 추가로 페이지가 하나 더 있어야 하므로 +1을 해준다.
		if(memberCount%ROW_PER_PAGE == 0) {
			lastPage = memberCount/ROW_PER_PAGE;
		}else {
			lastPage = (memberCount/ROW_PER_PAGE)+1;
		}
//		그리고 url에서 임의로 page 수를 바꿀 수 있으므로 총 페이지 수보다 높은 페이지를 접근 못하게 한다.
		if(totalPage < currentPage) {
			currentPage=totalPage;
		}
		//	만약 currentPage가 1보다 크면, prevPage를 표시한다.
		if (currentPage > 1) {
			//prevPage
		}
		//	만약 currentPage가 lastPage보다 작으면 
		if (currentPage < lastPage){
			currentPage=lastPage;
		}

		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("list", list);
		returnMap.put("currentPage", currentPage);
		returnMap.put("memberCount", memberCount);
		returnMap.put("lastPage", lastPage);
		
		
		return returnMap;
	}
}
