package cafe.jjdev.ajaxcrud.member.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cafe.jjdev.ajaxcrud.member.vo.Member;

@Mapper
public interface MemberMapper {
	public List<Member> selectMemberList(Map<String, Object> map);
	public int insertMember(Member member);
	public int deleteMember(Member member);
	public int updateMember(Member member);
	public int selectMemberCount();
	public int idCheck(String memberId);
}
