package com.shopping.feature.termandcondition.ui;

import com.shopping.feature.termandcondition.data.model.TermAndConditionModel;

public class TermAndConditionViewModel {
    private String msg;

    public TermAndConditionViewModel(TermAndConditionModel termandCondition) {
        this.msg = termandCondition.message;
    }

    public String getMessage() {
        return this.msg;
    }
}
