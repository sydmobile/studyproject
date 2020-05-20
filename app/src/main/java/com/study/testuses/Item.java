package com.study.testuses;

import java.util.List;

/**
 * 说明：$
 * <p>
 * date: 2020/3/30 15:02
 *
 * @author syd
 * @version 1.0
 */
public class Item {
    private String category_id;
    private String category_name;
    private List image;
    private String message;

    public Item(String category_id,String category_name,List image,String message){
        this.category_id = category_id;
        this.category_name = category_name;
        this.image = image;
        this.message = message;
    }
}
