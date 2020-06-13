package org.example;

import javafx.beans.property.Property;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @program: beanandcollection
 * @description:
 * @author: xjh
 * @create: 2020-03-18 16:06
 **/
public class JavaCollection {
    private List list;
    private Set set;
    private Map map;
    private Properties properties;

    public List getList() {
        System.out.println("List Elements is"+list);
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Set getSet() {
        System.out.println("Set Elements is"+set);
        return set;
    }

    public void setSet(Set set) {
        this.set = set;
    }

    public Map getMap() {
        System.out.println("Map Elements is"+map);
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Properties getProperties() {
        System.out.println("Properties Elements is"+properties);
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
