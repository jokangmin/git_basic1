package member.service;

import java.util.Scanner;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

public class UpdateService implements Member{
	Scanner scan = new Scanner(System.in);
	@Override
	public void execute() {
		MemberDAO memberDAO = MemberDAO.getInstance();
		MemberDTO memberDTO = new MemberDTO();
		System.out.print("정보 수정할 아이디 입력 : ");
		String id = scan.next();
		Boolean exist = memberDAO.shId(id);
		if(exist) {
			System.out.println(memberDAO.gName() + "\t" + id  + "\t" + memberDAO.gPwd()); //추: toString override 이용해 출력
			System.out.print("수정할 이름 입력 : ");
			String name = scan.next();
			System.out.print("수정할 비밀번호 입력 : ");
			String pwd = scan.next();
			memberDAO.Update(name,pwd, id); //Map<String, String> map = new HashMap<>; 이용해서 변수명과 값을 같이 보낼 수 있다.
		}									//map.put("name",name); 과 같은 형태로 보내준다.
		else System.out.println("검색한 아이디가 없습니다.");
		
	}

}
