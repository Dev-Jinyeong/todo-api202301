package com.example.todo.todoapi.dto.request;

import com.example.todo.todoapi.entity.TodoEntity;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
//@Data // @Data 어노테이션은 커스텀 불가능.
@Builder
public class TodoCreateRequestDTO {

    // 검증은 request 시에만
    @NotBlank
    @Size(min = 2, max = 10)
    private String title;

    // 이 dto를 엔터티로 변환
    public TodoEntity toEntity() {
        return TodoEntity.builder()
                .title(this.title)
                .build();
    }
}
