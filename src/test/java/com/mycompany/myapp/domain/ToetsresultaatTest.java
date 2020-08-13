package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ToetsresultaatTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Toetsresultaat.class);
        Toetsresultaat toetsresultaat1 = new Toetsresultaat();
        toetsresultaat1.setId(1L);
        Toetsresultaat toetsresultaat2 = new Toetsresultaat();
        toetsresultaat2.setId(toetsresultaat1.getId());
        assertThat(toetsresultaat1).isEqualTo(toetsresultaat2);
        toetsresultaat2.setId(2L);
        assertThat(toetsresultaat1).isNotEqualTo(toetsresultaat2);
        toetsresultaat1.setId(null);
        assertThat(toetsresultaat1).isNotEqualTo(toetsresultaat2);
    }
}
