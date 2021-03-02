# MVC_Pattern

MVC(Model View Controller) 를 이용한 간단한 게시판 예제       

Model : 무엇을 할 것인지 정의(DTO,DAO)         
View : 화면에 무엇을 보여줄 것인지 정의(jsp)   
Controller : Model이 어떻게 처리 할 것인지 정의(servlet)                

사용자는 Controller를 통해 Model의 상태를 바꾸고      
바꾼 상태를 다시 Controller를 통해 사용자에게 보여질 View를 제공                 

jsp + javaBean의 경우  
          장점 : 구조가 단순하다.      
          단점 :      
                 1. 출력과 로직코드가 섞여 코드가 복잡해진다.  
                 2. front 부분과 back 부분이 혼재되어 분업에 비효율적이다.     
                 3. 유지보수가 어렵다.                    
                 
따라서 MVC패턴을 이용하여     
          개발자들의 communication의 효율성을 높이고 
          코드의 재사용성을 높이며       
          코드의 결합도를 낮춘다.                           

index.jsp -> BoardServlet(cmd) -> CommandFactory(cmdIf)               
          -> BoardServlet(cmdif) -> 해당Command(nextPage)               
          -> BoardServlet(nextPage) -> 해당page가 실행(View)
