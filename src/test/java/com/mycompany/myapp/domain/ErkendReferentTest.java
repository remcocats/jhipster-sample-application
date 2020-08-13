package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ErkendReferentTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ErkendReferent.class);
        ErkendReferent erkendReferent1 = new ErkendReferent();
        erkendReferent1.setId(1L);
        ErkendReferent erkendReferent2 = new ErkendReferent();
        erkendReferent2.setId(erkendReferent1.getId());
        assertThat(erkendReferent1).isEqualTo(erkendReferent2);
        erkendReferent2.setId(2L);
        assertThat(erkendReferent1).isNotEqualTo(erkendReferent2);
        erkendReferent1.setId(null);
        assertThat(erkendReferent1).isNotEqualTo(erkendReferent2);
    }
}
