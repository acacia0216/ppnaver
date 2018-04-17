package kr.co.pp.handler;

import kr.co.pp.controller.Command;

import javax.servlet.http.HttpServletRequest;

public class EditHandler1 implements Command {
    @Override
    public String process(HttpServletRequest request) {
        System.out.println("회원정보 수정페이지 연결완료");
        String url = "./edit_page.jsp";
        return url;
    }
}
