package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ReferentstellingTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Referentstelling.class);
        Referentstelling referentstelling1 = new Referentstelling();
        referentstelling1.setId(1L);
        Referentstelling referentstelling2 = new Referentstelling();
        referentstelling2.setId(referentstelling1.getId());
        assertThat(referentstelling1).isEqualTo(referentstelling2);
        referentstelling2.setId(2L);
        assertThat(referentstelling1).isNotEqualTo(referentstelling2);
        referentstelling1.setId(null);
        assertThat(referentstelling1).isNotEqualTo(referentstelling2);
    }
}
