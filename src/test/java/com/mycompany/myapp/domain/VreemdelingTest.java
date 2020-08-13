package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class VreemdelingTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Vreemdeling.class);
        Vreemdeling vreemdeling1 = new Vreemdeling();
        vreemdeling1.setId(1L);
        Vreemdeling vreemdeling2 = new Vreemdeling();
        vreemdeling2.setId(vreemdeling1.getId());
        assertThat(vreemdeling1).isEqualTo(vreemdeling2);
        vreemdeling2.setId(2L);
        assertThat(vreemdeling1).isNotEqualTo(vreemdeling2);
        vreemdeling1.setId(null);
        assertThat(vreemdeling1).isNotEqualTo(vreemdeling2);
    }
}
