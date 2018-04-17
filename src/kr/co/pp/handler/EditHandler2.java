package kr.co.pp.handler;

import kr.co.pp.controller.Command;
import kr.co.pp.dao.MemberDAO;
import kr.co.pp.vo.MemberVO;

import javax.servlet.http.HttpServletRequest;

public class EditHandler2 implements Command {
    @Override
    public String process(HttpServletRequest request) {
        String id = request.getParameter("id");
        //회원정보 가져오기
        MemberDAO dao = new MemberDAO();
        MemberVO vo = null;
        vo = dao.searchMember(id);
        request.setAttribute("vo",vo);
        System.out.println("회원정보 가져오기 완료");
        String url = "./member_edit.jsp";
        return url;
    }
}
