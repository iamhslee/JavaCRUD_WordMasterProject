package ee.hsl.WordMaster;

import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD{
    ArrayList<Word> list;
    Scanner crudS = new Scanner(System.in);

    WordCRUD(Scanner input) {
        list = new ArrayList<>();
        this.crudS = input;
    }

    public void addWord() {
        Word one = (Word)add();
        list.add(one);
        System.out.println("새 단어가 단어장에 추가되었습니다.");
    }

    @Override
    public Object add() {
        System.out.print("=> 난이도 (1, 2, 3) & 새 단어 입력 : ");
        int level = crudS.nextInt();
        String word = crudS.nextLine();
        System.out.print("=> 뜻 입력 : ");
        String meaning = crudS.nextLine();

        return new Word(0, level, word, meaning);
    }

    @Override
    public int update(Object obj) {
        return 0;
    }

    @Override
    public int delete(Object obj) {
        return 0;
    }

    @Override
    public void selectOne(int id) {

    }

    public void listAll() {
        System.out.println("--------------------------------------------------");
        for(int i = 0; i < list.size(); i++) {
            System.out.print((i + 1) + " ");
            System.out.println(list.get(i).toString());
        }
        System.out.println("--------------------------------------------------");
    }
}
