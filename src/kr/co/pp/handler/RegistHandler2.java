package kr.co.pp.handler;

import kr.co.pp.controller.Command;
import kr.co.pp.dao.MemberDAO;
import kr.co.pp.vo.MemberVO;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class RegistHandler2 implements Command {
    public String process(HttpServletRequest req) {
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ArrayList<MemberVO> list = new ArrayList<MemberVO>();
        MemberVO vo = new MemberVO();
        String url = "";
        boolean flag = false;
        System.out.println("레지스트 들어옴");
        if (vo != null)

        {
            System.out.println("객체화 시작");
            vo.setId(req.getParameter("id"));
            vo.setPw(req.getParameter("pw"));
            vo.setName(req.getParameter("name"));
            vo.setGender(req.getParameter("gender"));
            String by = req.getParameter("birthyear");
            String bm = req.getParameter("birthmonth");
            String bd = req.getParameter("birthday");
            String val[] = {by, bm, bd};
            vo.setBirth(val);
            vo.setEmail(req.getParameter("email"));
            vo.setHp(req.getParameter("hp"));
            System.out.println("객체 생성 완료");

            System.out.println("한글 깨짐 확인 : " + vo.getName());

            list.add(vo);
            System.out.println("리스트 추가 완료");
            MemberDAO dao = new MemberDAO();
            flag = dao.insert(vo);

            if (flag == true) {
                System.out.println(vo.getId() + "님이 등록되었습니다.");
            } else if (flag == false) {
                System.out.println("회원정보가 넘어오지 못함");
                url = "./error.jsp";
            }
            url = "./home.do";
        }

        return url;
    }
}