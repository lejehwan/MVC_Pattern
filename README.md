# MVC_Pattern

MVC(Model View Controller) 를 이용한 간단한 게시판 예제

index.jsp -> BoardServlet(cmd) -> CommandFactory(cmdIf)
          -> BoardServlet(cmdif) -> 해당Command(nextPage)
          -> BoardServlet(nextPage) -> 해당page가 실행
