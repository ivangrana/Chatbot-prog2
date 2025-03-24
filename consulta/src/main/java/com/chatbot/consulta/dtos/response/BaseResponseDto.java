package com.chatbot.consulta.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class BaseResponseDto<T> {
    @JsonProperty("msg")
    private String message;

    @JsonProperty("datas")
    private List<T> datas;

    @JsonProperty("data")
    private T data;

    public BaseResponseDto(String message){
        this.message = message;
    }

    public BaseResponseDto(T data){
        this.data = data;
    }

    public BaseResponseDto(List<T> datas){ this.datas = datas; }

    public BaseResponseDto(String message, List<T> datas){this.message = message; this.datas = datas; }

    public BaseResponseDto() {

    }
}
