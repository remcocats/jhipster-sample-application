package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class KenniswerkTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Kenniswerk.class);
        Kenniswerk kenniswerk1 = new Kenniswerk();
        kenniswerk1.setId(1L);
        Kenniswerk kenniswerk2 = new Kenniswerk();
        kenniswerk2.setId(kenniswerk1.getId());
        assertThat(kenniswerk1).isEqualTo(kenniswerk2);
        kenniswerk2.setId(2L);
        assertThat(kenniswerk1).isNotEqualTo(kenniswerk2);
        kenniswerk1.setId(null);
        assertThat(kenniswerk1).isNotEqualTo(kenniswerk2);
    }
}
