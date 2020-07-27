package com.epam.rd.izh.dto;

import java.util.List;

public class FrictionBook {
    private String fictionBookXMLNS;
    private String fictionBookXMLNSXLINK;
    private String description;
    private String body;
    private String binary;
    private String titleInfo;
    private String srcTitleInfo;
    private String documentInfo;
    private String publishInfo;
    private String customInfo;
    //title-info=================================================
    private List<String> genre;
    private List<String> author;
    //author==============================
    private String firstName;
    private String lastName;
    private String middleName;
    private String nickname;
    private String email;
    //====================================
}
