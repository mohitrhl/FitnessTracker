package com.project.fitness.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.fitness.domain.ActivityType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    private ActivityType type;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "JSON")
    private Map<String , Object> additionalMetrics;

    private Integer duration;

    private Integer caloriesBurned;

    private LocalDateTime startTime;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = true, foreignKey = @ForeignKey(name = "fk_activity_user"))
    private User user;

    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonIgnore
    private List<Recommendation> recommendations = new ArrayList<>();

}
