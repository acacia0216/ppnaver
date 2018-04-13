<%@ page import="ppNaver.MemberVO" %>
<%@ page import="java.util.Random" %>
<%@ page import="java.lang.reflect.Member" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>네이버 : 회원가입</title>
</head>
<style>
    table {
        border-collapse: collapse;
    }

    td {
        border: 1px gray solid;
        height: 50px;
    }

    .textcenter {
        text-align: center;
    }

    .textnone {
        border: none;
    }

    span {
        color: red;
    }
</style>
<script>
    function idessential(val) { //아이디필수
        var flag = false;
        if (val == "" || val == null) {
            document.getElementById("idempty").innerHTML = "필수 정보입니다.";
        }else if(val != null){
            if (val.equals("admin")) {
                document.getElementById("idempty").innerHTML = "이미 사용중이거나 탈퇴한 아이디입니다."

            }else{
                document.getElementById("idempty").innerHTML = "사용가능한 아이디입니다."
                flag = true;
            }
        }
        return flag;
    }

    function pwessential(val) { //비밀번호 필수
        var flag = false;
        if (val == "" || val == null) {
            document.getElementById("pwempty").innerHTML = "필수 정보입니다.";
        }else{
            flag = true;
        }
        return flag;
    }

    function repwessential(val) { //비밀번호 확인 필수
        var flag = false;
        if (val == "" || val == null) {
            document.getElementById("repwempty").innerHTML = "필수 정보입니다.";
        }else{
            flag = true;
        }
        return flag;
    }

    function nameessential(val) { //이름 필수
        var flag = false;
        if (val == "" || val == null) {
            document.getElementById("nameempty").innerHTML = "필수 정보입니다.";
        }else{
            flag = true;
        }
        return flag;
    }

    function yearessential(val) { //연도 필수
        var flag = false;
        if (val == "" || val == null) {
            document.getElementById("yearempty").innerHTML = "필수 정보입니다.";
        }else if(val.length < 4){
            document.getElementById("yearempty").innerHTML = "태어난 년도 4자리를 정확하게 입력하세요."
        }else{
            flag = true;
        }
        return flag;
    }

    function dayessential(val) { //생일 필수
        var flag = false;
        if (val == "" || val == null) {
            document.getElementById("dayempty").innerHTML = "필수 정보입니다.";
        }else{
            flag = true;
        }
        return flag;
    }

    function hpessential(val) { //전화번호 필수
        var flag = false;
        if (val == "" || val == null) {
            document.getElementById("hpempty").innerHTML = "필수 정보입니다.";
        }else{
            flag = true;
        }
        return flag;
    }

    function rehpessential(val) { //인증 필수
        var flag = false;
        if (val == "" || val == null) {
            document.getElementById("rehpempty").innerHTML = "필수 정보입니다.";
        } else if(val.equals("1234")){
            document.getElementById("rehpempty").innerHTML = "인증번호를 다시 확인해주세요."
        }else{
            flag = true;
        }
        return flag;
    }

    function hp_confirm() {
        alert("인증번호 4자리를 입력해주세요");
        return false;
    }

    function final_check() {
        var flag = false;

        var id = document.getElementById("id_check").value;
        alert('id='+id);
        var pw = document.getElementById("pw_check").value;
        alert('pw='+pw);
        var repw = document.getElementById("repw_check").value;
        alert('repw='+repw);
        var name = document.getElementById("name_check").value;
        alert('name='+name);
        var birthyear = document.getElementById("birthyear_check").value;
        alert('birthyear='+birthyear);
        var birthday = document.getElementById("birthday_check").value;
        alert('birthday='+birthday);
        var hp = document.getElementById("hp_check").value;
        alert('hp='+hp);
        var rehp = document.getElementById("rehp_check").value;
        alert('rehp='+rehp);

        if (id == true && pw == true && repw == true && name == true && birthyear == true && birthday == true && hp == true && rehp == true) {
            flag = true;
        }
        return flag;
    }
</script>
<body>
<%
    //회원정보 가져오기
//    Random rd = new Random();
//    int randomNum = rd.nextInt(9000)+1000;

    MemberVO vo = (MemberVO)request.getAttribute("vo");
    if(vo == null){
        vo.setId("");
        vo.setPw("");
        vo.setName("");
        vo.setGender("0");
        vo.setBirth(new String[]{"","0",""});//배열로받기
        vo.setEmail("");
        vo.setHp("");
    }
%>
<form action="./controller?cmd=edit_member" method="post">
    <table align="center">
        <tr>
            <td colspan="4" class="textcenter" style="border: none">
                <h1 style="color: lightgreen">NeverNAVER</h1>
            </td>
        </tr>
        <tr>
            <td colspan="3"><input class="textnone" type="text" name="id" id="id" placeholder="아이디" value="<%=vo.getId()%>" onblur="return idessential(document.getElementById(id).value)"><br>
                <span id="idempty"></span><input type="hidden" name="id_check" id="id_check" value="false"></td>
            <td class="textcenter">@naver.com</td>
        </tr>
        <tr>
            <td colspan="3"><input class="textnone" type="text" name="pw" id="pw" placeholder="비밀번호" value="<%=vo.getPw()%>" onblur="return pwessential(document.getElementById(pw).value)"><br>
                <span id="pwempty"></span><input type="hidden" name="pw_check" id="pw_check" value="false"></td>
            <td class="textcenter"><img src="./img/rock.jpg" width=30 height=30></td>
        </tr>
        <tr>
            <td colspan="3"><input class="textnone" type="text" name="repw" id="repw" placeholder="비밀번호 재확인" value="" onblur="return repwessential(document.getElementById(repw).value)"><br>
                <span id="repwempty"></span><input type="hidden" name="repw_check" id="repw_check" value="false"></td>
            <td class="textcenter"><img src="./img/rock.png" width=30 height=30></td>
        </tr>
        <tr>
            <td class="textnone"></td>
        </tr>
        <tr>
            <td colspan="4"><input class="textnone" type="text" name="name" id="name" placeholder="이름" value="<%=vo.getName()%>" onblur="return nameessential(document.getElementById(name).value)"><br>
                <span id="nameempty"></span><input type="hidden" name="name_check" id="name_check" value="false"></td>
        </tr>
        <tr>
            <td colspan="2" class="textcenter"><label for="man">남자</label><input type="radio" name="gender" id="man" value="0" <%=vo.getGender().equals("0")?"checked":""%>></td>
            <td colspan="2" class="textcenter"><label for="woman">여자</label><input type="radio" name="gender" id="woman" value="1" <%=vo.getGender().equals("1")?"checked":""%>></td>
        </tr>
        <tr>
            <td class="textcenter">생일</td>
            <td><input type="text" class="textnone" name="birthyear" id="birthyear" placeholder="년(4자)" value="<%=vo.getBirth()[0]%>" onblur="return yearessential(document.getElementById(birthyear).value)"><br>
                <span id="yearempty"></span><input type="hidden" name="birthyear_check" id="birthyear_check" value="false"></td>
            <td><select name="birthmonth" id="birthmonth">
                <option value="0">월</option>
                <option value="<%=vo.getBirth()[1].equals("1")?"selected":""%>">1</option>
                <option value="<%=vo.getBirth()[1].equals("2")?"selected":""%>">2</option>
                <option value="<%=vo.getBirth()[1].equals("3")?"selected":""%>">3</option>
                <option value="<%=vo.getBirth()[1].equals("4")?"selected":""%>">4</option>
                <option value="<%=vo.getBirth()[1].equals("5")?"selected":""%>">5</option>
                <option value="<%=vo.getBirth()[1].equals("6")?"selected":""%>">6</option>
                <option value="<%=vo.getBirth()[1].equals("7")?"selected":""%>">7</option>
                <option value="<%=vo.getBirth()[1].equals("8")?"selected":""%>">8</option>
                <option value="<%=vo.getBirth()[1].equals("9")?"selected":""%>">9</option>
                <option value="<%=vo.getBirth()[1].equals("10")?"selected":""%>">10</option>
                <option value="<%=vo.getBirth()[1].equals("11")?"selected":""%>">11</option>
                <option value="<%=vo.getBirth()[1].equals("12")?"selected":""%>">12</option>
            </select></td>
            <td width="150"><input class="textnone" type="text" name="birthday" id="birthday" placeholder="일" value="<%=vo.getBirth()[2]%>" onblur="return dayessential(document.getElementById(birthday).value)"><br>
                <span id="dayempty"></span><input type="hidden" name="birthday_check" id="birthday_check" value="false">
            </td>
        </tr>
        <tr>
            <td colspan="4"><input class="textnone" type="text" name="email" id="email" placeholder="본인확인 이메일(선택)" value=""></td>
        </tr>
        <tr>
            <td class="textnone"></td>
        </tr>
        <tr>
            <td><select>
                <option value="0">+82</option>
            </select></td>
            <td colspan="2"><input class="textnone" type="text" name="hp" id="hp" placeholder="휴대전화번호" value="" onblur="return hpessential(document.getElementById(hp).value)"><br>
                <span id="hpempty"></span><input type="hidden" name="hp_check" id="hp_check" value="false"></td>
            <td class="textcenter">
                <button onclick="return hp_confirm()">인증</button>
            </td>
        </tr>
        <tr>
            <td colspan="4"><input class="textnone" type="text" name="rehp" id="rehp" placeholder="인증번호" value="" onblur="return rehpessential(document.getElementById(rehp).value)"><br>
                <span id="rehpempty"></span><input type="hidden" name="rehp_check" id="rehp_check" value="false">
            </td>
        </tr>
        <tr>
            <td colspan="4" style="border: none">
                <center>
                    <input type="submit" value="회원정보 수정하기" onclick="return final_check()">
                    <button onclick="return final_check()">수정버튼</button>
                </center>
            </td>
        </tr>
    </table>
</form>
</body>
</html>