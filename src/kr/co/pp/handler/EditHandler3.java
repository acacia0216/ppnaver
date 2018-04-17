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
        System.out.println(request.getParameter("id"));
        vo.setId(request.getParameter("id"));
        vo.setPw(request.getParameter("pw"));
        vo.setName(request.getParameter("name"));
        String y = request.getParameter("birthyear");
        String m = request.getParameter("birthmonth");
        String d = request.getParameter("birthday");
        String temp[] = {y,m,d};
        vo.setBirth(temp);
        vo.setEmail(request.getParameter("email"));
        vo.setHp(request.getParameter("hp"));
        //수정한 회원정보 가져오기
        dao.update(vo);
        System.out.println("DAO에 들어갔다 왔음");
        String url = "./home.do";
        return url;
    }
}
