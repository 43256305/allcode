package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

/**
 * @program: annotation
 * @description:
 * @author: xjh
 * @create: 2020-03-19 09:43
 **/
public class TextEditor {
    private String name;

    private SpellChecker spellChecker;

    //Autowired可以用于属性，setter方法，构造函数前面，默认是byType
    //默认情况下，autowired注释意味着注入是必须的，如果加上false，则可以不注入（即可以在bean中不实例化SpellChecker）
    //resource注解：在字段或者setter上面，默认byName注入 @Resource(name= "spellChecker")
    @Autowired
    public void setSpellChecker( SpellChecker spellChecker ){
        this.spellChecker = spellChecker;
    }
    public SpellChecker getSpellChecker( ) {
        return spellChecker;
    }
    public void spellCheck() {
        spellChecker.checkSpelling();
    }

    public String getName() {
        return name;
    }

    //required只能注释setter，且注释了setter的属性必须在bean中注入
    @Required
    public void setName(String name) {
        this.name = name;
    }
}
