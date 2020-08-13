package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class BekostigingTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Bekostiging.class);
        Bekostiging bekostiging1 = new Bekostiging();
        bekostiging1.setId(1L);
        Bekostiging bekostiging2 = new Bekostiging();
        bekostiging2.setId(bekostiging1.getId());
        assertThat(bekostiging1).isEqualTo(bekostiging2);
        bekostiging2.setId(2L);
        assertThat(bekostiging1).isNotEqualTo(bekostiging2);
        bekostiging1.setId(null);
        assertThat(bekostiging1).isNotEqualTo(bekostiging2);
    }
}
