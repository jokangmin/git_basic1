package member.main;

import member.service.Member;
import member.service.UpdateService;

import java.util.Scanner;

import member.service.DeleteService;
import member.service.LoginService;
import member.service.WriteService;


//hello test kangmin
//hello test kangmin_2
public class IndexMain {
	
	public void menu() {
		Scanner scan = new Scanner(System.in);
		Member member = null;
		int num;
		while(true) {
			System.out.println();
			System.out.println("************");
			System.out.println("1. 회원가입");
			System.out.println("2. 로그인");
			System.out.println("3. 회원정보수정");
			System.out.println("4. 회원탈퇴");
			System.out.println("5. 끝");
			System.out.println("************");
			System.out.print("번호 입력 : ");
			num = scan.nextInt();
			
			if(num == 5) break;
			else if(num == 1) {
				member = new WriteService();
			}
			else if(num == 2) {
				member = new LoginService();
			}
			else if(num == 3) {
				member = new UpdateService();
			}
			else if(num == 4) {
				member = new DeleteService();
			}
			member.execute();
		}
	}

	public static void main(String[] args) {
		IndexMain indexMain = new IndexMain();
		indexMain.menu();
		System.out.println("프로그램 종료");

	}

}

//데이터 : 이름 name, 아이디 id, 비밀번호 pwd, 핸드폰 phone
//테이블 : member
//컬럼 : name 15자리 , id 30자리 , pwd 50자리 , phone 20자리
