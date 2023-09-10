package com.cs.cn.model;

import com.cs.cn.constans.EntityAgentConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Collection;
import java.util.List;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = EntityAgentConstants.TAB_NAME_AGENT)
@Builder
public class Agent extends Person implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = EntityAgentConstants.COL_NAME_ID)
    private Long id;

    @NotEmpty
    @Column(name = EntityAgentConstants.COL_NAME_USERNAME, length = 20, unique = true)
    private String username;

    @NotEmpty
    @Column(name = EntityAgentConstants.COL_NAME_PASSWORD, length = 100)
    private String password;

    @Column(name = EntityAgentConstants.COL_NAME_PHOTO)
    private String photo;

    @JsonIgnore
    @OneToMany(mappedBy = EntityAgentConstants.TAB_NAME_AGENT, fetch = FetchType.LAZY)
    private List<Support> supports;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return super.getState();
    }
    
}
