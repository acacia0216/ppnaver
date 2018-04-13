package ppNaver;

import java.sql.*;
import java.util.ArrayList;

public class MemberDAO {
    public boolean insert(MemberVO vo){
        System.out.println(vo.getId());
        boolean flag = false;
        ConnectionManager mgr = new ConnectionManager();
        Connection con = mgr.getConnection();
        String sql = "insert into NAVER_MEMBER_TBL values (?,?,?,?,?,?,?)";
        PreparedStatement pstmt = null;
        if (con == null) {
            System.out.println("connection 연결 실패");
        }else{
            System.out.println("DB접속 완료");
        }
        try {
            pstmt = con.prepareStatement(sql);
            System.out.println("pstmt 준비끝, 데이터전송 시작");
            pstmt.setString(1,vo.getId());
            pstmt.setString(2,vo.getPw());
            pstmt.setString(3,vo.getName());
            pstmt.setString(4,vo.getGender());
            String birth[] = vo.getBirth();
            String temp = birth[0]+"-"+birth[1]+"-"+birth[2];
            pstmt.setString(5,temp);
            pstmt.setString(6,vo.getEmail());
            pstmt.setString(7,vo.getHp());

            int affectedCount = pstmt.executeUpdate();
            if(affectedCount>0){
                flag = true;
                System.out.println("MemberDAO 페이지에서 삽입 완료");
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }finally{
            mgr.connectionClose(null,pstmt,con);
        }

        System.out.println("사용자정보 insert 결과 : "+flag);

        return flag;
    }

    public void delete(String id){
        ConnectionManager mgr = new ConnectionManager();
        Connection con = mgr.getConnection();
        PreparedStatement pstmt = null;
        System.out.println("DAO 삭제 들어옴");
        String sql = "delete from NAVER_MEMBER_TBL where USER_ID='"+id+"'";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.executeUpdate();
            System.out.println("DAO 삭제완료");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            mgr.connectionClose(null,pstmt,con);
        }
    }

    public void update(MemberVO vo){
        ConnectionManager mgr = new ConnectionManager();
        Connection con = mgr.getConnection();
        PreparedStatement pstmt = null;
        System.out.println("DAO 업데이트 들어옴");
        String sql = "update NAVER_MEMBER_TBL set USER_ID=?,USER_PW=?,USER_NAME=?,USER_GENDER=?,USER_BIRTH=?,USER_EMAIL=?,USER_HP=? where USER_ID='"+vo.getId()+"'";
        try {
            pstmt = con.prepareStatement(sql);

            //회원정보 업데이트
            pstmt.setString(1,vo.getId());
            pstmt.setString(2,vo.getPw());
            pstmt.setString(3,vo.getName());
            pstmt.setString(4,vo.getGender());
            String temp[] = vo.getBirth();
            String birth = temp[0]+"-"+temp[1]+"-"+temp[2];
            pstmt.setString(5,birth);
            pstmt.setString(6,vo.getEmail());
            pstmt.setString(7,vo.getHp());

            System.out.println("DAO 업데이트 쿼리 보냄(업데이트 완료)");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            mgr.connectionClose(null,pstmt,con);
        }
    }
    public MemberVO searchMember(String id){
        //DB에서 회원정보 가져다 리턴
        MemberVO vo = null;
        System.out.println("search들어온"+id);
        ConnectionManager mgr = new ConnectionManager();
        Connection con = mgr.getConnection();
        PreparedStatement pstmt = null;
        String sql ="select * from NAVER_MEMBER_TBL where USER_ID = '"+id+"'";
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                System.out.println(rs.getString(1));
                vo.setId(rs.getString(1));
                vo.setPw(rs.getString(2));
                vo.setName(rs.getString(3));
                vo.setGender(rs.getString(4));
                String temp[] = rs.getString(5).split("-");
                vo.setBirth(temp);
                vo.setEmail(rs.getString(6));
                vo.setHp(rs.getString(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            mgr.connectionClose(rs,pstmt,con);
        }


        return vo;
    }
}
