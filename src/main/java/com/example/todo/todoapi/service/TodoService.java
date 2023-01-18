package com.example.todo.todoapi.service;

import com.example.todo.todoapi.dto.request.TodoCreateRequestDTO;
import com.example.todo.todoapi.dto.request.TodoModifyRequestDTO;
import com.example.todo.todoapi.dto.response.TodoDetailResponseDTO;
import com.example.todo.todoapi.dto.response.TodoListResponseDTO;
import com.example.todo.todoapi.entity.TodoEntity;
import com.example.todo.todoapi.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    // 할 일 목록 조회
    public TodoListResponseDTO retrieve() {

        List<TodoEntity> entityList = todoRepository.findAll();

        List<TodoDetailResponseDTO> dtoList = entityList.stream()
                .map(te -> new TodoDetailResponseDTO(te))
                .collect(Collectors.toList());

        return TodoListResponseDTO.builder()
                .todos(dtoList)
                .build();

    }

    // 할 일 등록
    public TodoListResponseDTO create(final TodoCreateRequestDTO createRequestDTO) {

        todoRepository.save(createRequestDTO.toEntity());

        System.out.println("==============================");
        log.info("할 일이 저장되었습니다. 제목 : {}", createRequestDTO.getTitle());
        System.out.println("==============================");

        return retrieve();

    }

    // 할 일 수정 (제목, 할일 완료여부)
    public TodoListResponseDTO update(final String id, final TodoModifyRequestDTO modifyRequestDTO) {

        // 조회를 먼저하고
        Optional<TodoEntity> targetEntity = todoRepository.findById(id);

        // targetEntity가 존재한다면
        targetEntity.ifPresent(entity -> {
            // setter로 수정하고
            entity.setTitle(modifyRequestDTO.getTitle());
            entity.setDone(modifyRequestDTO.isDone());

            // 수정 결과 저장
            todoRepository.save(entity);

        });

        // 수정이 완료된 목록을 반환
        return retrieve();

    }

    // 할 일 삭제
    public TodoListResponseDTO delete(final String id) {

        try {
            todoRepository.deleteById(id);
        }
        catch (Exception e) {
            // 서버 기록용 로그
            log.error("id가 존재하지 않아 삭제에 실패했습니다. - ID: {}, err: {}"
                    , id, e.getMessage());

            // 클라이언트 확인용
            throw  new RuntimeException("id가 존재하지 않아 삭제에 실패했습니다.");

        }

        return retrieve();

    }

}
