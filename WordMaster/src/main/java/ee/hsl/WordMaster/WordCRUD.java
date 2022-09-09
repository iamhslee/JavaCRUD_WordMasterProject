package ee.hsl.WordMaster;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD{
    ArrayList<Word> list;
    Scanner crudS = new Scanner(System.in);

    final String FILENAME = "Dictionary.txt";

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

    public ArrayList<Integer> listAll(String keyword) {
        ArrayList<Integer> indexList = new ArrayList<>();
        int count = 0;
        System.out.println("--------------------------------------------------");
        for(int i = 0; i < list.size(); i++) {
            String word = list.get(i).getWord();
            if(!word.contains(keyword)) continue;
            System.out.print((count + 1) + " ");
            System.out.println(list.get(i).toString());
            indexList.add(i);
            count++;
        }
        System.out.println("--------------------------------------------------");

        return indexList;
    }

    public void listAll(int level) {
        int count = 0;
        System.out.println("--------------------------------------------------");
        for(int i = 0; i < list.size(); i++) {
            int iLevel = list.get(i).getLevel();
            if(level != iLevel) continue;
            System.out.print((count + 1) + " ");
            System.out.println(list.get(i).toString());
            count++;
        }
        System.out.println("--------------------------------------------------");
    }

    public void updateWord() {
        System.out.print("=> 수정할 단어 검색 : ");
        String keyword = crudS.next();
        ArrayList<Integer> indexList = this.listAll(keyword);
        System.out.print("=> 수정할 번호 선택 : ");
        int indexNumber = crudS.nextInt();
        crudS.nextLine();
        System.out.print("=> 뜻 입력 : ");
        String meaning = crudS.nextLine();
        Word word = list.get(indexList.get(indexNumber - 1));
        word.setMeaning(meaning);
        System.out.println("단어가 수정되었습니다.");
    }

    public void deleteWord() {
        System.out.print("=> 삭제할 단어 검색 : ");
        String keyword = crudS.next();
        ArrayList<Integer> indexList = this.listAll(keyword);
        System.out.print("=> 삭제할 번호 선택 : ");
        int indexNumber = crudS.nextInt();
        crudS.nextLine();
        System.out.print("=> 정말로 삭제하실래요 (Y/N) : ");
        String choice = crudS.nextLine();
        if(choice.equalsIgnoreCase("Y")) {
            list.remove((int)indexList.get(indexNumber - 1));
            System.out.println("단어가 삭제되었습니다.");
        } else {
            System.out.println("취소되었습니다.");
        }
    }

    public void loadFile() {
        int count = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILENAME));

            while(true) {
                String line = br.readLine();
                if(line == null) break;
                String data[] = line.split("\\|");

                int level = Integer.parseInt(data[0]);
                String word = data[1];
                String meaning = data[2];

                list.add(new Word(0, level, word, meaning));
                count++;
            }
            System.out.println("=> " + count + "개 로딩 완료!");
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveFile() {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(FILENAME));

            for(Word one : list) {
                pw.write(one.toFileString() + "\n");
            }

            System.out.println("=> 저장 완료!");

            pw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchLevel() {
        System.out.print("=> 검색할 난이도 입력 : ");
        int level = crudS.nextInt();
        listAll(level);
    }

    public void searchWord() {
        System.out.print("=> 검색할 단어 입력 : ");
        String keyword = crudS.next();
        listAll(keyword);
    }
}
