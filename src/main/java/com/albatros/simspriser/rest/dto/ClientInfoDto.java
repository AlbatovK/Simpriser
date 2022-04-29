package com.albatros.simspriser.rest.dto;

import com.albatros.simspriser.domain.ClientInfo;
import com.albatros.simspriser.domain.QuestionInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientInfoDto {

    private long id;
    private String name;
    private boolean started;
    private int score;
    private HashMap<Integer, QuestionInfo> questionMap = new HashMap<>();

    public ClientInfoDto(String name) {
        this.score = 0;
        this.id = UUID.randomUUID().getMostSignificantBits();
        this.name = name;
        this.started = false;
    }

    public ClientInfoDto(long id, String name, boolean started, int score) {
        this.score = score;
        this.id = id;
        this.name = name;
        this.started = started;
    }

    public static ClientInfo toDomainObject(ClientInfoDto dto) {
        return new ClientInfo(
                dto.id,
                dto.name,
                dto.started,
                dto.score,
                dto.questionMap
        );
    }

    public static ClientInfoDto toDto(ClientInfo obj) {
        return new ClientInfoDto(
                obj.getId(),
                obj.getName(),
                obj.isStarted(),
                obj.getScore(),
                obj.getQuestionMap()
        );
    }
}
