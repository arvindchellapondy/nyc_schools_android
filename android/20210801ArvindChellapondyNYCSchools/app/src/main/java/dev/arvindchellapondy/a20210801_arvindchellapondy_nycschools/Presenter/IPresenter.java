//
//  20210801-ArvindChellapondy-NYCSchools
//
//  Created by Arvind Chellapondy on 08/01/21.
//

package dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools.Presenter;

/**
 * Presenter interface
 *
 * @param <V>
 */
public interface IPresenter <V extends IView>{

    void attachView(V view);
    void detachView();
    boolean isViewAttached();
}
