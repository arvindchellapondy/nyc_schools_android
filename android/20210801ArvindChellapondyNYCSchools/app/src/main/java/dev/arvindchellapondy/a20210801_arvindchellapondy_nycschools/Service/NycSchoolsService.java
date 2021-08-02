//
//  20210801-ArvindChellapondy-NYCSchools
//
//  Created by Arvind Chellapondy on 08/01/21.
//

package dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools.Service;

import java.util.List;

import dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools.Model.SatStats;
import dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools.Model.School;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Service API
 */
public interface NycSchoolsService {

    @GET("resource/s3k6-pzi2.json")
    Single<List<School>> getSchools();

    @GET("resource/f9bf-2cp4.json")
    Single<List<SatStats>> getSatStats();

}
