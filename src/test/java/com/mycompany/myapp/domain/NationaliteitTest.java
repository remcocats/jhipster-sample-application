package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class NationaliteitTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Nationaliteit.class);
        Nationaliteit nationaliteit1 = new Nationaliteit();
        nationaliteit1.setId(1L);
        Nationaliteit nationaliteit2 = new Nationaliteit();
        nationaliteit2.setId(nationaliteit1.getId());
        assertThat(nationaliteit1).isEqualTo(nationaliteit2);
        nationaliteit2.setId(2L);
        assertThat(nationaliteit1).isNotEqualTo(nationaliteit2);
        nationaliteit1.setId(null);
        assertThat(nationaliteit1).isNotEqualTo(nationaliteit2);
    }
}
