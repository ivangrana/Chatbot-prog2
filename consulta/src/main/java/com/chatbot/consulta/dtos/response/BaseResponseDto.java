package com.chatbot.consulta.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class BaseResponseDto<T> {
    @JsonProperty("msg")
    private String msg;

    @JsonProperty("datas")
    private List<T> datas;

    @JsonProperty("data")
    private T data;

    public BaseResponseDto(String msg){
        this.msg = msg;
    }

    public BaseResponseDto(T data){
        this.data = data;
    }

    public BaseResponseDto(List<T> datas){ this.datas = datas; }

    public BaseResponseDto(String msg, List<T> datas){this.msg = msg; this.datas = datas; }

    public BaseResponseDto() {

    }
}
