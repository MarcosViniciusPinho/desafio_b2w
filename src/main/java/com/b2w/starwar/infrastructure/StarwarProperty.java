package com.b2w.starwar.infrastructure;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("starwar")
@Setter
@Getter
@NoArgsConstructor
public class StarwarProperty {

    private String enviroment;

}
