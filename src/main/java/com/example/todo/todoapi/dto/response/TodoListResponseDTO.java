package com.example.todo.todoapi.dto.response;

import lombok.*;

import java.util.List;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoListResponseDTO {

    private String error;   // 에러 발생 시 클라이언트에게 보낼 메세지
    private List<TodoDetailResponseDTO> todos;

}
