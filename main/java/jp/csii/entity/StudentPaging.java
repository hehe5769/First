package jp.csii.entity;

import java.util.List;

import lombok.Data;
@Data
public class StudentPaging {
    private int curPage;//当ページ

    private int pageSize;//ページ毎表示される件数

    private int total;//総件数
    
    private int totalPage;//総ページ数
    
    private List<StudentView> list;//DBから取得するデータをlistに格納
}
