package kr.co.pp.handler;

import kr.co.pp.controller.Command;
import kr.co.pp.dao.MemberDAO;
import kr.co.pp.vo.MemberVO;

import javax.servlet.http.HttpServletRequest;

public class EditHandler3 implements Command {
    @Override
    public String process(HttpServletRequest request) {
        MemberDAO dao = new MemberDAO();
        MemberVO vo = new MemberVO();
        vo.setId(request.getParameter("id"));
        vo.setPw(request.getParameter("pw"));
        vo.setName(request.getParameter("name"));
        vo.setGender(request.getParameter("gender"));
        vo.setBirth(request.getParameterValues("birth"));
        vo.setEmail(request.getParameter("email"));
        vo.setHp(request.getParameter("hp"));
        //수정한 회원정보 가져오기
        dao.update(vo);
        System.out.println("DAO에 들어갔다 왔음");
        String url = "./home.jsp";
        return url;
    }
}
