/**
 * 
 */
package com.ethan.marvel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.ethan.marvel.mffdata.DataLoader;

/**
 * @author GBTW0011
 * created date: 2018年2月12日
 */
public class QuizDB {
    private static QuizDB instance = null;
    private static final Integer LOCK = new Integer(0);

    private List<Quiz> quizs = new ArrayList<Quiz>();
    
    public class Quiz {
        public String quiz;
        public String ans;
    }
    
    private QuizDB() {
        load();
    }
    
    private void load() {
        DataLoader quiz = new DataLoader();
        quiz.loadData(new File("/data/marvel/mffdata/MARVEL_QUIZ.csv.txt.decode.txt"), "UTF-8", "\t", 0);
        
        while(quiz.next()) {
            String id = quiz.getData("QUIZ_ID");
            String ans = quiz.getData("ANSWER");
            
            Quiz q = new Quiz();
            q.quiz=id;
            q.ans=ans;
            
            quizs.add(q);
            
        }
        
    }
    
    
    public List<Quiz> getQuizs() {
        return quizs;
    }
    
    
    public static QuizDB getInstance() {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = new QuizDB();
                }
            }
        }
        return instance;
    }
    
    
    public static void main(String[] args) {
        for(Quiz q:QuizDB.getInstance().getQuizs()) {
            System.out.println(q.quiz+"\t"+q.ans);
        }
    }
    
}
