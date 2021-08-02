//
//  20210801-ArvindChellapondy-NYCSchools
//
//  Created by Arvind Chellapondy on 08/01/21.
//

package dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools.Presenter;

import android.content.Context;

/**
 * Base Presenter
 * @param <V>
 */
public class BasePresenter<V extends IView> implements IPresenter<V>{

    protected V mView;
    protected final Context mContext;

    public BasePresenter(Context context){
        this.mContext = context;
    }

    @Override
    public void attachView(V view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public boolean isViewAttached() {
        return (mView != null);
    }
}
