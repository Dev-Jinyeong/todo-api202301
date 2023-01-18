package com.example.todo.todoapi.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class TodoModifyRequestDTO {

    // 검증은 request 시에만
    @NotBlank
    @Size(min = 2, max = 10)
    private String title;
    private boolean done;

}
