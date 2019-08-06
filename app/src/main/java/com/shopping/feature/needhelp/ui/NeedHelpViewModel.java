package com.shopping.feature.needhelp.ui;

import com.shopping.feature.needhelp.data.model.NeedHelpModel;

public class NeedHelpViewModel {
    private String msg1;
private String msg2;
    public NeedHelpViewModel(NeedHelpModel needHelpModel)
    {
        this.msg1= needHelpModel.message1;

    }
    public String getMessage()
    {
        return this.msg1;
    }

}
