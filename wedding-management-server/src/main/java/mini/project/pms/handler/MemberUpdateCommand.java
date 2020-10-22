package mini.project.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import mini.project.pms.domain.Member;
import mini.project.util.Prompt;

public class MemberUpdateCommand implements Command {

  List<Member> memberList;

  public MemberUpdateCommand(List<Member> list) {
    this.memberList = list;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
      out.println("[회원 정보 변경]");
      String id = Prompt.inputString("아이디? ", out, in);
      Member member = findById(id);

      if (member == null) {
        out.println("해당 회원이 없습니다.");
        return;
      }

      String name = Prompt.inputString(
          String.format("이름(%s)? ", member.getName()), out, in);

      String tel = Prompt.inputString(
          String.format("전화(%s)? ", member.getTel()), out, in);

      String response = Prompt.inputString("정말 변경하시겠습니까?(y/N) ", out, in);
      if (!response.equalsIgnoreCase("y")) {
        out.println("회원 변경을 취소하였습니다.");
        return;
      }

      member.setName(name);
      member.setTel(tel);

      out.println("회원을 변경하였습니다.");

    } catch (Exception e) {
      out.printf("작업 처리 중 오류 발생! - %s\n", e.getMessage());
    }
  }

  private Member findById(String id) {
    for (int i = 0; i < memberList.size(); i++) {
      Member member = memberList.get(i);
      if (member.getId() == id) {
        return member;
      }
    }
    return null;
  }
}
