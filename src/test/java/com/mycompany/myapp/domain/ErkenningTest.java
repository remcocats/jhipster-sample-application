package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ErkenningTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Erkenning.class);
        Erkenning erkenning1 = new Erkenning();
        erkenning1.setId(1L);
        Erkenning erkenning2 = new Erkenning();
        erkenning2.setId(erkenning1.getId());
        assertThat(erkenning1).isEqualTo(erkenning2);
        erkenning2.setId(2L);
        assertThat(erkenning1).isNotEqualTo(erkenning2);
        erkenning1.setId(null);
        assertThat(erkenning1).isNotEqualTo(erkenning2);
    }
}
