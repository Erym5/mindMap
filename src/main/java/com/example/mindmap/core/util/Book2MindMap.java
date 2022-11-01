package com.example.mindmap.core.util;

import com.example.mindmap.dao.entity.Children;
import com.example.mindmap.dao.entity.Data;
import com.example.mindmap.dao.entity.Root;
import com.example.mindmap.dto.resp.BookInfoRespDto;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Book2MindMap {
    static String flag = "某";

    public static void util() {

    }
    public static int kindOfBook(List<String> contents) {

        //flagOfBook 书籍格式 4:未知
        int flagOfBook = 4;
        List<String> signs = new ArrayList<>();
        List<String> seqs = new ArrayList<>();
        boolean symbol = true;
        boolean symbol1 = true;
        Pattern bookmarkPattern = Pattern.compile(".*?([\\d\\u4e00\\u4e8c\\u4e09\\u56db\\u4e94\\u516d\\u4e03\\u516b\\u4e5d\\u5341]{1,2})(.)?");
        for (String content : contents) {
            Matcher matcher = bookmarkPattern.matcher(content);
            if (matcher.find()) {
                String seq = matcher.group(1);
                String sign = matcher.group(2);
                signs.add(sign);
                seqs.add(seq);
                if (seq != null && symbol) {
                    flag = sign;
                    symbol = false;
                    continue;
                }
                //flagOfBook = 3: 有序号的小标题1.1
                if (!symbol && seqs.get(seqs.size() - 2) != null && seq !=null && !sign.equals(flag) && symbol1) {
                    flagOfBook = 3;
                    symbol1 = false;
                }
                //flagOfBook = 1 无小标题 1.
                else if (!symbol && seqs.get(seqs.size() - 2) != null && seq !=null && sign.equals(flag) && symbol1) {
                    flagOfBook = 1;
                    symbol1 = false;
                }
//                flagOfBook = 2: 无序号的小标题
                else if (!symbol && seqs.get(seqs.size() - 2) != null && seq == null && symbol1) {
                    flagOfBook = 2;
                    symbol1 = false;
                } else if (symbol) {
                    flagOfBook = 0;
                    symbol1 = false;
                }
            }
        }
        return flagOfBook;
    }


    public static BookInfoRespDto createMap(Integer id) {

        Root root = new Root();
        BookInfoRespDto result = new BookInfoRespDto();
        List<Children> firstTitle = new ArrayList<>();
        List<Children> secondTitle = new ArrayList<>();
        List<String> seqs = new ArrayList<>();

        Pattern bookmarkPattern = Pattern.compile(".*?([\\d\\u4e00\\u4e8c\\u4e09\\u56db\\u4e94\\u516d\\u4e03\\u516b\\u4e5d\\u5341]{1,2})(.)?\\s*(.*)?\\s?");
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:3000/book?id=" + id;
        ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, Map.class);
        Map<String, Object> returnMap = (Map<String, Object>) responseEntity.getBody().get("data");
        List<String> contents = (List<String>) returnMap.get("catalog");
        Data titleD = new Data();
        titleD.setText((String)returnMap.get("title"));
        int flagOfBook = kindOfBook(contents);

        for (Iterator<String> it = contents.iterator(); it.hasNext();) {
            String content = it.next();
            System.out.println(content);
            if (content.matches("\\d+.\\d+.\\d+.*")) {
                it.remove();
                continue;
            } else if (content.matches("(.*?)\\s\\d+")) {
                String[] str = content.split("\\s\\d");
                content = str[0];
            }
            Data catalogD = new Data();
            Children present = new Children();
            Matcher matcher = bookmarkPattern.matcher(content);
            String seq = null;
            String sign = null;
            if (matcher.find()) {
                seq = matcher.group(1);
                sign = matcher.group(2);
            }
            seqs.add(seq);

            if (flagOfBook == 0 || flagOfBook == 4) {
                catalogD.setText(content);
                present.setData(catalogD);
                firstTitle.add(present);
            } else if (flagOfBook == 1) {
                catalogD.setText(content);
                present.setData(catalogD);
                firstTitle.add(present);
            } else if (flagOfBook == 2) {
                int mark = 0;
                if (seq != null) {
                    mark = 1;
                    catalogD.setText(content);
                    present.setData(catalogD);
                    present.setChildren(secondTitle);
                    secondTitle = new ArrayList<Children>();
                    firstTitle.add(present);
                    continue;
                }
                if (mark == 1){
                    catalogD.setText(content);
                    present.setData(catalogD);
                    secondTitle.add(present);
                }
                else {
                    catalogD.setText(content);
                    present.setData(catalogD);
                    firstTitle.add(present);
                }
            }
                //将格式定式，优先设计
                //深克隆与浅克隆
                //list追寻上一个
            else {
                int mark = 0;
                if (seq != null && !sign.equals(flag)) {
                    catalogD.setText(content);
                    present.setData(catalogD);
                    secondTitle.add(present);
                } else if (seq != null && sign.equals(flag)) {
                    catalogD.setText(content);
                    present.setData(catalogD);
                    firstTitle.add(present);
                    if (firstTitle.size() > 1) {
                        firstTitle.get(firstTitle.size() - 2).setChildren(secondTitle);
                    }
                    secondTitle = new ArrayList<Children>();

                }else {
                    catalogD.setText(content);
                    present.setData(catalogD);
                    firstTitle.add(present);
                    if (secondTitle != null && firstTitle.size() > 1) {
                        firstTitle.get(firstTitle.size() - 2).setChildren(secondTitle);
                        secondTitle = new ArrayList<Children>();
                    }
                }
            }
        }
        if (secondTitle != null && firstTitle.size() > 1) {
            firstTitle.get(firstTitle.size() - 1).setChildren(secondTitle);
            secondTitle = new ArrayList<Children>();
        }
        root.setData(titleD);
        root.setChildren(firstTitle);
        result.setRoot(root);
        return result;
    }
}
