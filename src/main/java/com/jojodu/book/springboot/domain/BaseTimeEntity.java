/*
package com.jojodu.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //JPA Entity 클래스들이 BaseTimeEntity을 상속할 경우 필드들도 칼럼으로 인식
@EntityListeners(AuditingEntityListener.class) //BaseTimeEntity 클래스에 Auding 기능 포함
//Auding -> Entity 값이 생성, 수정될 때 자동으로 값을 넣어줌
public abstract class BaseTimeEntity {

    @CreatedDate //Entity가 생성도어 저장될 때 자동으로 시간 저장
    private LocalDateTime createdDate;

    @LastModifiedDate //조회한 Entity의 값을 변경할 때 자동으로 시간 저장
    private LocalDateTime modifiedDate;
}
*/
