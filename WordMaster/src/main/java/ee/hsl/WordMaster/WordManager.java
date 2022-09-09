package ee.hsl.WordMaster;

import java.util.Scanner;

public class WordManager {
    WordCRUD wordCRUD;
    Scanner managerS = new Scanner(System.in);
    public void start() {
        wordCRUD.loadFile();
        while(true) {
            int menu = selectMenu();

            switch(menu) {
                case 0:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                case 1:
                    wordCRUD.listAll();
                    break;
                case 2:
                    wordCRUD.searchLevel();
                    break;
                case 3:
                    wordCRUD.searchWord();
                    break;
                case 4:
                    wordCRUD.addWord();
                    break;
                case 5:
                    wordCRUD.updateWord();
                    break;
                case 6:
                    wordCRUD.deleteWord();
                    break;
                case 7:
                    wordCRUD.saveFile();
                    break;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }

    WordManager() {
        wordCRUD = new WordCRUD(managerS);
    }

    public int selectMenu() {

        System.out.print("\n*** 영단어 마스터 ***\n"
                        + "*************************\n"
                        + "1. 모든 단어 보기\n"
                        + "2. 수준별 단어 보기\n"
                        + "3. 단어 검색\n"
                        + "4. 단어 추가\n"
                        + "5. 단어 수정\n"
                        + "6. 단어 삭제\n"
                        + "7. 파일 저장\n"
                        + "0. 나가기\n"
                        + "*************************\n"
                        + "=> 원하는 메뉴는? ");
        return managerS.nextInt();
    }
}
