package org.example;

/**
 * @program: beanandDI
 * @description:
 * @author: xjh
 * @create: 2020-03-18 09:59
 **/
public class TextEditor {
    private String str;
    private int i1;
    private SpellChecker spellChecker;
    public TextEditor(){

    }

    public SpellChecker getSpellChecker() {
        return spellChecker;
    }

    public void setSpellChecker(SpellChecker spellChecker) {
        System.out.println("Inside setSpellChecker");
        this.spellChecker = spellChecker;
    }

    public TextEditor(SpellChecker spell){
        this.spellChecker=spell;
        System.out.println("Inside TextEditor Constructor");
    }
    public void spellChecker(){
        spellChecker.checkSpelling();
    }

    public TextEditor(String s,int i){
        this.str=s;
        this.i1=i;
    }
    public void desc(){
        System.out.println("TextEditor'int is "+i1+" string is "+str);
    }
}
