package com.lab7.airline.repository;

import com.lab7.airline.model.CrewMember;
import com.lab7.airline.model.CrewRole;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CrewMemberRepository extends JpaRepository<CrewMember, Long> {
    List<CrewMember> findByRoleAndAvailableTrue(CrewRole role);

    List<CrewMember> findByRole(CrewRole role);
}