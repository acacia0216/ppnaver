package kr.co.pp.handler;

import kr.co.pp.controller.Command;
import kr.co.pp.dao.MemberDAO;

import javax.servlet.http.HttpServletRequest;

public class DeleteHandler2 implements Command {

    @Override
    public String process(HttpServletRequest request) {
        String id = request.getParameter("id");
        MemberDAO dao = new MemberDAO();
        dao.delete(id);
        System.out.println("아이디 삭제되었음");
        String url = "./home.do";
        return url;
    }
}
